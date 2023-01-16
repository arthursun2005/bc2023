package Mining3;

import battlecode.common.*;

import java.util.ArrayList;
public class Carrier extends Robot {

    static MapLocation minLoc = null;
    static MapLocation parentLoc = null;
    static boolean takenAnchor = false;
    static ArrayList<MapLocation> wells = new ArrayList<>(), seenWells = new ArrayList<>();
    static int curWellSharedArray = 50;
    static int choice = 0;
    static int exploreturns = 0;
    static boolean needInstruction = true;
    static boolean[][] isWell = new boolean[64][64];
    static MapLocation target = null;
    static boolean mined = false;

    static void updateWells(RobotController rc) throws GameActionException {
        while (curWellSharedArray < 64) {
            int wellEncode = rc.readSharedArray(curWellSharedArray)-1;
            if (wellEncode < 0) break;
            int wellX = wellEncode / 64;
            int wellY = wellEncode % 64;
            wells.add(new MapLocation(wellX, wellY));
            isWell[wellX][wellY] = true;
            curWellSharedArray++;
        }
        if (!rc.canWriteSharedArray(0, 0)) return;
        for (MapLocation newWell : seenWells) {
            if (!isWell[newWell.x][newWell.y] && curWellSharedArray < 64) {
                isWell[newWell.x][newWell.y] = true;
                rc.writeSharedArray(curWellSharedArray, newWell.x*64+newWell.y+1);
                curWellSharedArray++;
            }
        }
        seenWells.clear();
    }

    public Carrier(RobotController rc) throws GameActionException {
        super(rc);
    }

    public void runUnit() throws GameActionException {

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
        /*if (rc.getAnchor() == null && takenAnchor == false) {
            rc.setIndicatorString("Trying to move to " + parentLoc);
            moveToLocation(parentLoc);
            return;
        }*/

        if (rc.getAnchor() != null) {
            if ((rc.senseIsland(rc.getLocation()) != -1) && rc.senseTeamOccupyingIsland(rc.senseIsland(rc.getLocation())) != rc.getTeam() &&rc.canPlaceAnchor()) {
                rc.setIndicatorString("Huzzah, placed anchor!");
                rc.placeAnchor();
                for (int i=1; i<=20; i++) {
                    if (rc.readSharedArray(i)==0 && rc.canWriteSharedArray(i,me.x*69+me.y)) {
                        rc.writeSharedArray(i,me.x*69+me.y);
                        break;
                    }
                }
                return;
            }

            if (!rc.isMovementReady()) return;

            if (minLoc != null) {
                moveToLocation(minLoc);
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

                moveToLocation(minLoc);

            }
            else {

                moveRandom();
            }
            return;
        } else {
            // I guess I'll try to find a well?
            // Shared array locations 50 onwards will be well locations
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
                // Explore, find a new well
                exploreturns++;
                /*Direction newDirects[] = {
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
                }*/
                moveRandom();
                WellInfo[] nearbyWells = rc.senseNearbyWells();
                for (int j=0; j<nearbyWells.length; j++) {;
                    WellInfo testing = nearbyWells[j];
                    MapLocation loc = testing.getMapLocation();
                    target = loc;
                    seenWells.add(loc);
                }
                if (target == null) {
                    rc.setIndicatorString("Exploring " + wells.size());
                    return;
                }
            } else {
                WellInfo[] nearbyWells = rc.senseNearbyWells();
                for (int j=0; j<nearbyWells.length; j++) {;
                    seenWells.add(nearbyWells[j].getMapLocation());
                }
            }



            int holding = rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR);
            if (!mined && !rc.getLocation().isWithinDistanceSquared(target, 1)) {
                rc.setIndicatorString("Going to well " + target.toString() + " " + choice + " " + wells.size() + " " + rc.getLocation().isWithinDistanceSquared(target, 1) + " " + wells.size());
                if (!rc.getLocation().isWithinDistanceSquared(target, 1)) {
                    moveToLocation(target);
                }
            }

            rc.setIndicatorString("nothing to do " + target.toString() + " " + wells.size() + " " + mined + " " + rc.getLocation().isWithinDistanceSquared(target, 1) + " " + rc.canCollectResource(target, 1) + " " + rc.getActionCooldownTurns() + " " + (rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR)));

            if (!mined && rc.getLocation().isWithinDistanceSquared(target, 1) && rc.canCollectResource(target, 1)) {
                rc.setIndicatorString("Mining " + wells.size());
                rc.collectResource(target, -1);
                holding = rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR);
                if (holding == GameConstants.CARRIER_CAPACITY) {
                    mined = true;
                }
            }
            if (mined) {
                if (!rc.getLocation().isWithinDistanceSquared(parentLoc, 1)) {
                    moveToLocation(parentLoc);
                }

                rc.setIndicatorString("Returning to HQ " + wells.size() + " " + rc.getLocation().isWithinDistanceSquared(parentLoc, 1) + " " + (rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR)));
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
