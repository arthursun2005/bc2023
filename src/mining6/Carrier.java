package mining6;

import battlecode.common.*;

import java.util.ArrayList;
public class Carrier extends Robot {

    static MapLocation minLoc = null;
    static MapLocation parentLoc = null;
    static boolean takenAnchor = false;
    static int choice = 0;
    static int exploreturns = 0;
    static boolean needInstruction = true;
    static MapLocation target = null;
    static boolean mined = false;


    public Carrier(RobotController rc) throws GameActionException {
        super(rc);
    }

    public void init() {

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
                for (int i=1; i<=30; i++) {
                    if (rc.readSharedArray(i)==0 && rc.canWriteSharedArray(i,me.x*69+me.y+1)) {
                        rc.writeSharedArray(i,me.x*69+me.y+1);
                        break;
                    }
                }
                return;
            }

            if (!rc.isMovementReady()) return;
            System.out.println(minLoc + " " + " loc! " + rc.getRoundNum());

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
                System.out.println("Current direction " + curDir);
                System.out.println(rc.getRoundNum() + " " + " reached!");

                moveRandom();
            }
            return;
        } else {
            // I guess I'll try to find a well?
            // Shared array locations 40 onwards will be well locations
            updateWells();
            if (needInstruction) {
                target = null;
                needInstruction = false;
                mined = false;
                choice = rng.nextInt(allWells.size()+1);
                if (choice != allWells.size()) {
                    target = allWells.get(choice);
                    int ada = getCount(0), mana = getCount(1);
                    /*if (Math.abs(ada-mana) > 500 || rng.nextInt(10) == 0) {
                        // 10% chance to always choose the one with less
                        // If difference is too large, always choose the one with less;
                        if (getCount(0) > getCount(1)) {
                            if (manaWells.size() > 0) {
                                target = manaWells.get(rng.nextInt(manaWells.size()));
                            }
                        } else {
                            if (adaWells.size() > 0) {
                                target = adaWells.get(rng.nextInt(adaWells.size()));
                            }
                        }
                    }*/
                }

                exploreturns = 0;
            }
            if (target == null && allWells.size() > 0 && (exploreturns > 40 || rc.getRoundNum() > 500)) {
                // Didn't find anything within 40 rounds, change target
                choice = rng.nextInt(allWells.size());
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
                for (int j=0; j<nearbyWells.length; j++) {
                    WellInfo testing = nearbyWells[j];
                    MapLocation loc = testing.getMapLocation();
                    target = loc;
                }
                if (target == null) {
                    rc.setIndicatorString("Exploring " + allWells.size());
                    return;
                }
            } 
            int holding = rc.getWeight();
            if (!mined && !rc.getLocation().isWithinDistanceSquared(target, 1)) {
                rc.setIndicatorString("Going to well " + getCount(0) + " " + getCount(1) + " " + getCount(2) + " " + target.toString() + " " + choice + " " + allWells.size() + " " + rc.getLocation().isWithinDistanceSquared(target, 1));
                if (!rc.getLocation().isWithinDistanceSquared(target, 1)) {
                    moveToLocation(target);
                }
            }

            rc.setIndicatorString("nothing to do " + getCount(0) + " " + getCount(1) + " " + getCount(2) + " " + target.toString() + " " + target.toString() + " " + allWells.size() + " " + mined + " " + rc.getLocation().isWithinDistanceSquared(target, 1) + " " + rc.canCollectResource(target, 1) + " " + rc.getActionCooldownTurns() + " " + rc.getWeight());
            if (!mined && rc.getLocation().isWithinDistanceSquared(target, 1) && rc.canCollectResource(target, 1)) {
                rc.setIndicatorString("Mining " + getCount(0) + " " + getCount(1) + " " + getCount(2) + " " + allWells.size());
                rc.collectResource(target, -1);
                holding = rc.getWeight();
                if (holding == GameConstants.CARRIER_CAPACITY) {
                    mined = true;
                }
            }
            if (mined) {
                if (!rc.getLocation().isWithinDistanceSquared(parentLoc, 1)) {
                    moveToLocation(parentLoc);
                }

                rc.setIndicatorString("Returning to HQ " + getCount(0) + " " + getCount(1) + " " + getCount(2) + " " + target.toString() + " " + allWells.size() + " " + rc.getLocation().isWithinDistanceSquared(parentLoc, 1) + " " + rc.getWeight());
                MapLocation curLoc = rc.getLocation();
                if (curLoc.isWithinDistanceSquared(parentLoc, 1)) {
                    int ada = rc.getResourceAmount(ResourceType.ADAMANTIUM), mana = rc.getResourceAmount(ResourceType.MANA), elixir = rc.getResourceAmount(ResourceType.ELIXIR);
                    if (ada > 0 && rc.canTransferResource(parentLoc, ResourceType.ADAMANTIUM, ada)) {
                        rc.transferResource(parentLoc, ResourceType.ADAMANTIUM, ada);
                        updateCount(0, ada);
                    }
                    if (mana > 0 && rc.canTransferResource(parentLoc, ResourceType.MANA, mana)) {
                        rc.transferResource(parentLoc, ResourceType.MANA, mana);
                        updateCount(1, mana);
                    }
                    if (elixir > 0 && rc.canTransferResource(parentLoc, ResourceType.ELIXIR, elixir)) {
                        rc.transferResource(parentLoc, ResourceType.ELIXIR, elixir);
                        updateCount(2, elixir);
                    }
                    holding = rc.getWeight();
                    if (holding == 0) {
                        needInstruction = true;
                    }
                }
            }
        }
    }
}
