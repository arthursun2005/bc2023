package Mining5;

import battlecode.common.*;

import java.util.Collections;
import java.util.Comparator;

public class Carrier extends Robot {

    static MapLocation minLoc = null;
    static MapLocation parentHQ = null;
    static boolean takenAnchor = false;
    static int choice = 0;
    static boolean needInstruction = true;
    static MapLocation target = null;
    static boolean mined = false;


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
    }

    public void runAwayFromDanger() throws GameActionException
    {
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        int dist = -1;
        MapLocation danger = null;
        for (RobotInfo ri : enemies)
        {
            if (ri.type.equals(RobotType.LAUNCHER))
            {
                int w = ri.getLocation().distanceSquaredTo(rc.getLocation());
                if (dist == -1 || w < dist)
                {
                    dist = w;
                    danger = ri.getLocation();
                }
            }
        }

        if (danger == null) return;
        int holding = rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR);
        if (holding > 0) mined = true;
        tryMove(danger.directionTo(rc.getLocation()));

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
        /*if (rc.getAnchor() == null && takenAnchor == false) {
            rc.setIndicatorString("Trying to move to " + parentLoc);
            moveToLocation(parentLoc);
            return;
        }*/

        runAwayFromDanger();

        if (rc.getAnchor() != null) {
            haveAnchor();
        } else {
            // I guess I'll try to find a well?
            // Shared array locations 50 onwards will be well locations
            if (needInstruction) {
                target = null;
                needInstruction = false;
                mined = false;
//                choice = rng.nextInt(wells.size());
//                if (choice != wells.size()) target = wells.get(choice);
            }
            if (target == null && wellUtility.wells.size() > 0) {
                // new target
                choice = rng.nextInt(wellUtility.wells.size());

                Collections.sort(WellUtility.wells, new Comparator<MapLocation>() {
                    public int compare(MapLocation a, MapLocation b) {
                        return rc.getLocation().distanceSquaredTo(a) - rc.getLocation().distanceSquaredTo(b);
                    }
                });

                if (rc.getRoundNum() <= 100) {
                    target = WellUtility.wells.get(0);
                } else {

                    target = wellUtility.wells.get(rng.nextInt(wellUtility.wells.size()));
                }
            } else if (target == null) {
                // bad
                return;
            }


            int holding = rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR);
            if (!mined && !rc.getLocation().isWithinDistanceSquared(target, 2)) {
                rc.setIndicatorString("Going to well " + target.toString() + " " + choice + " " + wellUtility.wells.size() + " " + rc.getLocation().isWithinDistanceSquared(target, 1) + " " + wellUtility.wells.size());
                if (!rc.getLocation().isWithinDistanceSquared(target, 2)) {
                    moveToLocation(target);
                }
            }

            rc.setIndicatorString("nothing to do " + target.toString() + " " + wellUtility.wells.size() + " " + mined + " " + rc.getLocation().isWithinDistanceSquared(target, 1) + " " + rc.canCollectResource(target, 1) + " " + rc.getActionCooldownTurns() + " " + (rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR)));

            if (!mined && rc.getLocation().isWithinDistanceSquared(target, 2) && rc.canCollectResource(target, 1)) {
                rc.setIndicatorString("Mining " + wellUtility.wells.size());
                rc.collectResource(target, -1);
                holding = rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR);
                if (holding == GameConstants.CARRIER_CAPACITY) {
                    mined = true;
                }
            }
            if (mined) {
                // parentHQ
                MapLocation current;
                if (rc.getRoundNum() <= 200) {
                    current = nearestHQ();
                } else {
                    current = HQLocations.get(rng.nextInt(HQLocations.size()));
//                    current = parentHQ;
                }
                if (!rc.getLocation().isWithinDistanceSquared(current, 2)) {
                    moveToLocation(current);
                }

                rc.setIndicatorString("Returning to HQ " + wellUtility.wells.size() + " " + rc.getLocation().isWithinDistanceSquared(current, 2) + " " + (rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR)));
                MapLocation curLoc = rc.getLocation();
                if (curLoc.isWithinDistanceSquared(current, 2)) {
                    int ada = rc.getResourceAmount(ResourceType.ADAMANTIUM), mana = rc.getResourceAmount(ResourceType.MANA), elixir = rc.getResourceAmount(ResourceType.ELIXIR);
                    if (ada > 0 && rc.canTransferResource(current, ResourceType.ADAMANTIUM, ada)) {
                        rc.transferResource(current, ResourceType.ADAMANTIUM, ada);
                    }
                    if (mana > 0 && rc.canTransferResource(current, ResourceType.MANA, mana)) {
                        rc.transferResource(current, ResourceType.MANA, mana);
                    }
                    if (elixir > 0 && rc.canTransferResource(current, ResourceType.ELIXIR, elixir)) {
                        rc.transferResource(current, ResourceType.ELIXIR, elixir);
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
