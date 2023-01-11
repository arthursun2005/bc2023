package amplifier;

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
                arr[x][y] = (rc.sensePassability(loc)?2:1);
                if (arr[oppx][y]+arr[x][y]==3) possi[1] = 0;
                if (arr[x][oppy]+arr[x][y]==3) possi[2] = 0;
                if (arr[oppx][oppy]+arr[x][y]==3) possi[3] = 0;
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
                rc.move(curDir);
            }
        }
    }
}
