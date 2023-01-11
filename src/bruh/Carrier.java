package bruh;

import battlecode.common.*;
import static bruh.RobotPlayer.*;

import java.util.ArrayList;

public class Carrier {

    static MapLocation minLoc = null;
    static MapLocation parentLoc = null;
    static boolean takenAnchor = false;

    static MapLocation prevLocation = null;
    static Direction prevDirection = null;
    static ArrayList<MapLocation> wells = new ArrayList<>(), seenWells = new ArrayList<>(); 
    static int curWellSharedArray = 30;
    static int choice = 0;
    static int exploreturns = 0;
    static boolean needInstruction = true;
    static boolean[][] isWell = new boolean[64][64];
    static MapLocation target = null;
    static boolean mined = false;

    static void updateWells(RobotController rc) throws GameActionException {
        while (curWellSharedArray < 64) {
            int wellX = rc.readSharedArray(curWellSharedArray) - 1;
            if (wellX < 0) break;
            int wellY = rc.readSharedArray(curWellSharedArray+1) - 1;
            wells.add(new MapLocation(wellX, wellY));
            isWell[wellX][wellY] = true;
            curWellSharedArray += 2;
        }
        if (!rc.canWriteSharedArray(0, 0)) return;
        for (MapLocation newWell : seenWells) {
            if (!isWell[newWell.x][newWell.y] && curWellSharedArray < 64) {
                isWell[newWell.x][newWell.y] = true;    
                rc.writeSharedArray(curWellSharedArray, newWell.x+1);
                rc.writeSharedArray(curWellSharedArray+1, newWell.y+1);
                curWellSharedArray += 2;
            }
        }
        seenWells.clear();
    }

    static void runCarrier(RobotController rc) throws GameActionException {
        if (prevLocation == null) {
            prevLocation = rc.getLocation();
        } else if (!rc.getLocation().equals(prevLocation)) {
            prevDirection = prevLocation.directionTo(rc.getLocation());
            prevLocation = rc.getLocation();
        }

        if (parentLoc == null) {
            RobotInfo[] friends = rc.senseNearbyRobots(42069,rc.getTeam());
            int mini=42069;
            for (RobotInfo friend : friends) {
                if (friend.type == RobotType.HEADQUARTERS) {
                    if (rc.getLocation().distanceSquaredTo(friend.location)<mini) {
                        mini = rc.getLocation().distanceSquaredTo(friend.location);
                        parentLoc = friend.location;
                        rc.setIndicatorString("parentLoc " + parentLoc);
                    }
                }
            }
            for (int i = 0; i < 10; i++) {
                if (rc.readSharedArray(i)==0&&rc.canWriteSharedArray(i,rc.getID()%60000)) {
                    rc.writeSharedArray(i,rc.getID()%60000);
                    break;
                }
            }
        }
        MapLocation me = rc.getLocation();
        if (rc.getAnchor() == null) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    MapLocation hqLoc = new MapLocation(me.x + dx, me.y + dy);
                    if (rc.canTakeAnchor(hqLoc, Anchor.STANDARD)) {
                        takenAnchor=true;
                        rc.setIndicatorString("Taking standard Anchor");
                        rc.takeAnchor(hqLoc, Anchor.STANDARD);
                    }
                }
            }
        }

        if (rc.getAnchor() != null) {
            if ((rc.senseIsland(rc.getLocation()) != -1) && rc.senseTeamOccupyingIsland(rc.senseIsland(rc.getLocation())) != rc.getTeam() &&rc.canPlaceAnchor()) {
                rc.setIndicatorString("Huzzah, placed anchor!");
                rc.placeAnchor();
                return;
            }

            if (!rc.isMovementReady()) return;
            System.out.println(minLoc + " " + " loc! " + rc.getRoundNum());

            if (minLoc != null) {
                Direction dir = Movement.tryMove(rc, minLoc, prevDirection);
                if (dir == null) {
                    System.out.println("null somehow");
                    minLoc = null;
                } else if (rc.canMove(dir)){
                    rc.move(dir);
                    return;
                }
            }
            // If I have an anchor singularly focus on getting it to the first island I see
            int[] islands = rc.senseNearbyIslands();
            int mini = 42069;
            for (int id : islands) {
                if (rc.senseTeamOccupyingIsland(id) == rc.getTeam())  continue;
//                if (rc.senseAnchor(id). == ) continue;
                MapLocation[] thisIslandLocs = rc.senseNearbyIslandLocations(id);
                for (MapLocation nLoc : thisIslandLocs) {
                    if (me.distanceSquaredTo(nLoc) < mini) {
                        mini=me.distanceSquaredTo(nLoc);
                        minLoc=nLoc;
                    }
                }
            }
            if (mini!=42069) {

                Direction dir = Movement.tryMove(rc, minLoc, prevDirection);
                if (dir == null) {
                    minLoc = null;
                } else if (rc.canMove(dir)){
                    rc.move(dir);
                    return;
                }
            }
            else {
                System.out.println(rc.getRoundNum() + " " + " reached!");
                if (!rc.isMovementReady()) return;
                if (moveCount==0 || !rc.canMove(curDir)) {
                    moveCount=4;
                    Direction newDirects[] = {
                            curDir,
                            curDir.rotateRight(),
                            curDir.rotateLeft(),
                            curDir.rotateRight().rotateRight(),
                            curDir.rotateLeft().rotateLeft(),
                    };
                    for (int i=0; i<20; i++) {
                        curDir = newDirects[rng.nextInt(newDirects.length)];
                        if (rc.canMove(curDir)) {
                            rc.move(curDir);
                            return;
                        }
                    }
                    while (true) {
                        curDir = directions[rng.nextInt(directions.length)];
                        if (rc.canMove(curDir)) {
                            rc.move(curDir);
                            return;
                        }
                    }
                }
                moveCount--;
                if (rc.canMove(curDir)) {
                    rc.move(curDir);
                }
            }
        } else {
            System.out.println("HHHHHHHHHHHHHIIIIIIIIIIIIIIIIIIIII");
            // I guess I'll try to find a well?
            // Shared array locations 30 onwards will be well locations
            updateWells(rc);
            if (needInstruction) {
                target = null;
                needInstruction = false;
                mined = false;
                choice = rng.nextInt(wells.size()+1);
                if (choice != wells.size()) target = wells.get(choice);
                exploreturns = 0;
            }
            if (target == null && wells.size() > 0 && (exploreturns > 40 || rc.getRoundNum() > 500)) {
                // Didn't find anything within 40 rounds, change target
                choice = rng.nextInt(wells.size());
                exploreturns = 0;
            }
            if (target == null) {
                System.out.println("EXPLORINGGgGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n");
                // Explore, find a new well
                exploreturns++;
                Direction newDirects[] = {
                    curDir,
                    curDir.rotateRight(),
                    curDir.rotateLeft(),
                    curDir.rotateRight().rotateRight(),
                    curDir.rotateLeft().rotateLeft(),
                };
                curDir = newDirects[rng.nextInt(newDirects.length)];
                for (int i=0; i<6; i++) {
                    if (rc.canMove(curDir)) {
                        rc.move(curDir);
                        WellInfo[] nearbyWells = rc.senseNearbyWells();
                        for (int j=0; j<nearbyWells.length; j++) {;
                            WellInfo testing = nearbyWells[j];
                            MapLocation loc = testing.getMapLocation();
                            target = loc;
                            seenWells.add(loc);
                        }
                    }
                }
                if (target == null) {
                    rc.setIndicatorString("Exploring");
                    return;
                }
            }


            System.out.println("asjdflkasjklfjasdklfjaklsdfjklasjdklfjaskldfjklasjdflkajslkdfj\n");
            int holding = rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR);
            if (!mined && !rc.getLocation().isWithinDistanceSquared(target, 1)) {
                rc.setIndicatorString("Going to well " + target.toString() + " " + choice + " " + wells.size() + " " + rc.getLocation().isWithinDistanceSquared(target, 1));
                System.out.println(rc.getRoundNum());
                while (!rc.getLocation().isWithinDistanceSquared(target, 1)) {
                    Direction dir = Movement.tryMove(rc, target, prevDirection);
                    if (dir != null && rc.canMove(dir)) {
                        rc.move(dir);
                    } else {
                        break;
                    }
                }
            }
            rc.setIndicatorString("nothing to do " + target.toString() + " " + mined + " " + rc.getLocation().isWithinDistanceSquared(target, 1) + " " + rc.canCollectResource(target, 1) + " " + rc.getActionCooldownTurns() + " " + (rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR)));
            while (!mined && rc.getLocation().isWithinDistanceSquared(target, 1) && rc.canCollectResource(target, 1)) {
                System.out.println("MINININININININININING");
                rc.setIndicatorString("Mining");
                rc.collectResource(target, -1);
                holding = rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR);
                if (holding == GameConstants.CARRIER_CAPACITY) {
                    mined = true;
                    break;
                }
            }
            if (mined) {
                while (!rc.getLocation().isWithinDistanceSquared(parentLoc, 1)) {
                    Direction dir = Movement.tryMove(rc, parentLoc, prevDirection);
                    if (dir != null && rc.canMove(dir)) {
                        rc.move(dir);
                    } else {
                        break;
                    }
                }
                rc.setIndicatorString("Returning to HQ " + rc.getLocation().isWithinDistanceSquared(parentLoc, 1) + " " + (rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR)));
                MapLocation curLoc = rc.getLocation();
                if (curLoc.isWithinDistanceSquared(parentLoc, 1)) {
                    int ada = rc.getResourceAmount(ResourceType.ADAMANTIUM), mana = rc.getResourceAmount(ResourceType.MANA), elixir = rc.getResourceAmount(ResourceType.ELIXIR);
                    if (ada > 0 && rc.canTransferResource(parentLoc, ResourceType.ADAMANTIUM, ada)) {
                        rc.transferResource(parentLoc, ResourceType.ADAMANTIUM, ada);
                    }
                    if (mana > 0 && rc.canTransferResource(parentLoc, ResourceType.MANA, mana)) {
                        rc.transferResource(parentLoc, ResourceType.MANA, mana);
                    }
                    if (elixir > 0 && rc.canTransferResource(parentLoc, ResourceType.ELIXIR, elixir)) {
                        rc.transferResource(parentLoc, ResourceType.ELIXIR, elixir);
                    }
                    holding = rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR);
                    if (holding == 0) {
                        needInstruction = true;
                    }
                }
            }
        }
    }
}
