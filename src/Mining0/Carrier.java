package Mining0;

import battlecode.common.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Carrier extends Robot {

    static MapLocation minLoc = null;
    static MapLocation parentHQ = null;
    static boolean takenAnchor = false;
    static boolean needInstruction = true;
    static MapLocation target = null;
    static boolean mined = false;
    static boolean mining = false;

    static Direction randDir;
    public Carrier(RobotController rc) throws GameActionException {
        super(rc);
    }

    public void init() {

    }
    public void haveAnchor() throws GameActionException {
        MapLocation me = rc.getLocation();

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
    }

    public boolean hitAndRun() throws GameActionException
    {
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        int dist = 1_000_000;
        MapLocation danger = null;
        for (RobotInfo ri : enemies)
        {
            if (ri.type.equals(RobotType.LAUNCHER))
            {
                int w = ri.getLocation().distanceSquaredTo(rc.getLocation());
                if (w < dist)
                {
                    dist = w;
                    danger = ri.getLocation();
                }
            }
        }

        if (danger == null) return false;
        int holding = rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR);
        if (holding > 0) {
            if (rc.canAttack(danger)) {
                rc.attack(danger);
            }
            needInstruction = true;
            target = null;
            mined = false;
        }
        tryMove(danger.directionTo(rc.getLocation()));
        return true;
    }

    static void sortType(ResourceType resourceType) {
        wellUtility.wells.sort(new Comparator<CustomWell>() {
            public int compare(CustomWell a, CustomWell b) {

                int deltaA = 0, deltaB = 0;
                if (!a.getResourceType().equals(resourceType)) {
                    deltaA += 1_000_000;
                }

                if (!b.getResourceType().equals(resourceType)) {
                    deltaB += 1_000_000;
                }
                return rc.getLocation().distanceSquaredTo(a.getMapLocation()) + deltaA - rc.getLocation().distanceSquaredTo(b.getMapLocation()) - deltaB;
            }
        });
    }

    static MapLocation getTarget() throws GameActionException {
        MapLocation target;

        if (rc.getRoundNum() <= 20) {
            sortType(ResourceType.ELIXIR);
            return wellUtility.wells.get(0).getMapLocation();
        }

        if (rc.getRoundNum() <= 30) {
            sortType(ResourceType.ADAMANTIUM);
            return wellUtility.wells.get(0).getMapLocation();
        }

        if (rc.getID() % 2 == 1) {
            sortType(ResourceType.MANA);
            return wellUtility.wells.get(0).getMapLocation();
        } else {
            sortType(ResourceType.ADAMANTIUM);
            return wellUtility.wells.get(0).getMapLocation();
        }


//        target = wellUtility.wells.get(rng.nextInt(wellUtility.wells.size())).getMapLocation();
//        return target;
    }

    public void runUnit() throws GameActionException {

        if (parentHQ == null) {
            RobotInfo[] friends = rc.senseNearbyRobots(42069,rc.getTeam());
            int mini=42069;
            for (RobotInfo friend : friends) {
                if (friend.type == RobotType.HEADQUARTERS) {
                    if (rc.getLocation().distanceSquaredTo(friend.location)<mini) {
                        mini = rc.getLocation().distanceSquaredTo(friend.location);
                        parentHQ = friend.location;
                        rc.setIndicatorString("parentLoc " + parentHQ);
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
//
//        if (hitAndRun()) {
//            return;
//        }

        if (rc.getAnchor() != null) {
            haveAnchor();
        } else {
            // I guess I'll try to find a well?
            // Shared array locations 50 onwards will be well locations

            if (needInstruction) {
                target = null;
                needInstruction = false;
                mined = false;
                mining = false;
            }

            if (!mined && !mining) {
                // new target

                target = getTarget();
            }


            int holding = rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR);
            if (!mined && !rc.getLocation().isWithinDistanceSquared(target, 2)) {
                rc.setIndicatorString("Going to well " + target.toString() + " " + wellUtility.wells.size() + " " + rc.getLocation().isWithinDistanceSquared(target, 1) + " " + wellUtility.wells.size());
                while (!rc.getLocation().isWithinDistanceSquared(target, 2)) {
                    if (!moveToLocation(target)) break;
                }
            }

            rc.setIndicatorString("nothing to do " + target.toString() + " " + wellUtility.wells.size() + " " + mined + " " + rc.getLocation().isWithinDistanceSquared(target, 1) + " " + rc.canCollectResource(target, 1) + " " + rc.getActionCooldownTurns() + " " + (rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR)));

            if (!mined && rc.getLocation().isWithinDistanceSquared(target, 2) && rc.canCollectResource(target, 1)) {
                mining = true;
                mined = false;
                rc.setIndicatorString("Mining " + wellUtility.wells.size());
                rc.collectResource(target, -1);
                holding = rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR);
                if (holding == GameConstants.CARRIER_CAPACITY) {
                    mined = true;
                }
            }
            if (mined) {
                // parentHQ
                mining = false;
                MapLocation current;
                if (rc.getRoundNum() <= 200) {
                    current = nearestHQ();
                } else {
                    current = HQLocations.get(rng.nextInt(HQLocations.size()));
//                    current = parentHQ;
                }
                while (!rc.getLocation().isWithinDistanceSquared(current, 2) ) {
                    if (!moveToLocation(current)) break;
                }

                rc.setIndicatorString("Returning to HQ " + wellUtility.wells.size() + " " + rc.getLocation().isWithinDistanceSquared(current, 2) + " " + (rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR)));
                MapLocation curLoc = rc.getLocation();
                if (curLoc.isWithinDistanceSquared(current, 2)) {
                    int ada = rc.getResourceAmount(ResourceType.ADAMANTIUM), mana = rc.getResourceAmount(ResourceType.MANA), elixir = rc.getResourceAmount(ResourceType.ELIXIR);
                    if (ada > 0 && rc.canTransferResource(current, ResourceType.ADAMANTIUM, ada)) {
                        rc.transferResource(current, ResourceType.ADAMANTIUM, ada);
                        communication.updateCount(0, ada);
                    }
                    if (mana > 0 && rc.canTransferResource(current, ResourceType.MANA, mana)) {
                        rc.transferResource(current, ResourceType.MANA, mana);
                        communication.updateCount(1, mana);
                    }
                    if (elixir > 0 && rc.canTransferResource(current, ResourceType.ELIXIR, elixir)) {
                        rc.transferResource(current, ResourceType.ELIXIR, elixir);
                        communication.updateCount(2, elixir);
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
