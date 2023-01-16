package amplifier;

import battlecode.common.*;

public class Carrier extends Robot {

    static MapLocation minLoc = null;
    static MapLocation parentLoc = null;
    static boolean takenAnchor = false;



    static boolean canMove(Direction desired) throws GameActionException {
        return rc.canMove(desired) && !rc.senseMapInfo(rc.getLocation().add(desired)).getCurrentDirection().equals(desired.opposite());
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
        if (rc.getAnchor() == null && takenAnchor == false) {
            rc.setIndicatorString("Trying to move to " + parentLoc);
            Direction dir = Movement.tryMove(rc, parentLoc, prevDirection);
            if (dir != null) {
                rc.move(dir);
            }
            return;
        }

        if (rc.getAnchor() != null) {
            if ((rc.senseIsland(rc.getLocation()) != -1) && rc.senseTeamOccupyingIsland(rc.senseIsland(rc.getLocation())) != rc.getTeam() &&rc.canPlaceAnchor()) {
                rc.setIndicatorString("Huzzah, placed anchor!");
                rc.placeAnchor();
                return;
            }

            if (!rc.isMovementReady()) return;

            if (minLoc != null) {
                Direction dir = Movement.tryMove(rc, minLoc, prevDirection);
                if (dir == null) {
                    minLoc = null;
                } else {
                    if (rc.canMove(dir)) rc.move(dir);
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
                } else {
                    rc.move(dir);
                    return;
                }
            }
            else {

                moveRandom();
            }
        }
    }
}
