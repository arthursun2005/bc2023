package Mining2;

import battlecode.common.*;
public class Amplifier extends Robot {
    static MapLocation parentLoc = null;
    static MapLocation oppositeLoc = null;

    public Amplifier(RobotController rc) throws GameActionException {
        super(rc);

    }
    static int arr[][] = new int[69][69];
    static int possi[] = {0,1,1,1};

    public void runUnit() throws GameActionException {
        rc.setIndicatorString("Symmetry is " + rc.readSharedArray(63));

        if (parentLoc == null) {
            RobotInfo[] friends = rc.senseNearbyRobots(42069,rc.getTeam());
            int mini=42069;
            for (RobotInfo friend : friends) {
                if (friend.type == RobotType.HEADQUARTERS) {
                    if (rc.getLocation().distanceSquaredTo(friend.location)<mini) {
                        mini = rc.getLocation().distanceSquaredTo(friend.location);
                        parentLoc = friend.location;
                    }
                }
            }
            oppositeLoc = new MapLocation(rc.getMapWidth()-parentLoc.x-1,rc.getMapHeight()-parentLoc.y-1);
        }

        if (rc.readSharedArray(63)==0) {
            int radius = rc.getType().visionRadiusSquared;
            MapLocation toCheck[] = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(),radius);
            // 1 for passable, 2 for impassable
            for (MapLocation loc : toCheck) {
                if (!rc.canSenseLocation(loc)) continue;

                int x = loc.x, y = loc.y;
                int oppx = rc.getMapWidth()-x-1, oppy = rc.getMapHeight()-y-1;

                RobotInfo possibleHQ = rc.senseRobotAtLocation(loc);
                MapInfo mapInfo = rc.senseMapInfo(loc);

                if (possibleHQ != null && possibleHQ.getType().equals(RobotType.HEADQUARTERS)) {
                    arr[x][y] = 1;
                } else if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) {
                    arr[x][y] = 2;

                    // CHECK FOR CLOUD AFTER THIS
                } else if (mapInfo.hasCloud()) {
                    arr[x][y] = 3;
                } else if (rc.sensePassability(loc)) {
                    arr[x][y] = 4;
                } else {
                    arr[x][y] = 5;
                }

                if (arr[oppx][y] != 0 && arr[oppx][y] != arr[x][y]) possi[1] = 0;
                if (arr[x][oppy] != 0  && arr[x][oppy] != arr[x][y]) possi[2] = 0;
                if (arr[oppx][oppy]  != 0 && arr[oppx][oppy] != arr[x][y]) possi[3] = 0;
            }

            if (possi[1]+possi[2]+possi[3]==1) {
                for (int i = 1; i <= 3; i++) {
                    if (possi[i]==1&&rc.canWriteSharedArray(63,i)) {
                        rc.writeSharedArray(63,i);
                    }
                }
                return;
            }

            if (arr[oppositeLoc.x][oppositeLoc.y]==0) moveToLocation(oppositeLoc);
            else {
                moveRandom();
            }
        }
    }
}
