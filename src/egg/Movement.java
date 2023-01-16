package egg;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

import java.util.Random;

enum State {
    WALL,
    NORMAL
}
public class Movement {

    static MapLocation oldTarget;
    static MapLocation lastWall;
    static State currentState = State.NORMAL;

    static RobotController rc;
    static MapLocation currentLocation;
    static Direction lastDirection = Direction.CENTER;

    static boolean turningLeft = true;
    static boolean shouldRight = true;

    static boolean switchable = false;

    static Random rng = null;

    static int bugLength = 0;

    static boolean canMove(Direction desired) throws GameActionException {
        if (currentState == State.WALL) return rc.canMove(desired) && rc.senseMapInfo(currentLocation.add(desired)).getCurrentDirection().equals(Direction.CENTER);
        return rc.canMove(desired) && !rc.senseMapInfo(currentLocation.add(desired)).getCurrentDirection().equals(desired.opposite());
    }

    static void hardReset() {
        lastDirection = Direction.CENTER;
        currentState = State.NORMAL;

        turningLeft = true;
        shouldRight = true;
        bugLength = 0;
        switchable = false;
    }

    static Direction getGreedyDirection() {
        Direction bestDir = rc.getLocation().directionTo(oldTarget);

        Direction[] directions = {bestDir,
                bestDir.rotateRight(),
                bestDir.rotateLeft(),
                bestDir.rotateLeft().rotateLeft(),
                bestDir.rotateRight().rotateRight(),
                bestDir.rotateRight().rotateRight().rotateRight(),
                bestDir.rotateLeft().rotateLeft().rotateLeft(),
                Direction.CENTER
        };

        for (int i = 0; i < directions.length; i++) {
            if (rc.canMove(directions[i])) {
                return directions[i];
            }
        }

        return Direction.CENTER;
    }
    static Direction alongWall(Direction desired) throws GameActionException {
        if (rng == null) rng = new Random(rc.getID());
//        if (canMove(desired)) {
//            return desired;
//        }\

        if (bugLength > Math.max(rc.getMapHeight(), rc.getMapWidth()) / 1.5) {
            hardReset();
            return getGreedyDirection();
        }

        if (shouldRight) {
            Direction checkDir = lastDirection;

            if (lastDirection == Direction.CENTER) {
                lastDirection = Direction.values()[rng.nextInt(8)+1];
            }

//            if (turningLeft) {
//                checkDir = lastDirection.rotateRight();
//            } else {
//                checkDir = lastDirection.rotateLeft();
//            }


            if (turningLeft) {
                for (int i = 0; i < 8; i++) {
                    if (rc.onTheMap(currentLocation.add(checkDir)) && rc.canSenseRobotAtLocation(currentLocation.add(checkDir)))  {
//                        hardReset();
//                        return getGreedyDirection();
//                        return Direction.CENTER;
                    }
                    if (canMove(checkDir)) break;
                    checkDir = checkDir.rotateRight();
                }
            } else {
                for (int i = 0; i < 8; i++) {
                    if (rc.onTheMap(currentLocation.add(checkDir)) && rc.canSenseRobotAtLocation(currentLocation.add(checkDir)))  {
//                        hardReset();
//                        return getGreedyDirection();
//                        return Direction.CENTER;
                    }
                    if (canMove(checkDir)) break;
                    checkDir = checkDir.rotateLeft();
                }
            }

            shouldRight = false;

            return checkDir;
        }

        Direction checkDir = lastDirection;

        /*if (!rc.onTheMap(currentLocation.add(checkDir))) {
            turningLeft = !turningLeft;
        }*/

        if (turningLeft) {
            checkDir = checkDir.rotateLeft().rotateLeft();
        } else {
            checkDir = checkDir.rotateRight().rotateRight();
        }


        for (int i = 0; i < 8; i++) {
            MapLocation temp = currentLocation.add(checkDir);

            if (!rc.onTheMap(temp) && switchable) {
                turningLeft = !turningLeft;
                lastDirection = lastDirection.opposite();
                switchable = false;
                return alongWall(desired);
            }

            if (rc.onTheMap(currentLocation.add(checkDir)) && rc.canSenseRobotAtLocation(currentLocation.add(checkDir)))  {
//                hardReset();
//                return getGreedyDirection();
            }
            if (canMove(checkDir)) {
                break;
            }

            if (turningLeft) {
                checkDir = checkDir.rotateRight();
            } else {
                checkDir = checkDir.rotateLeft();
            }
        }

        return checkDir;
    }

    static Direction normal(Direction togo) throws GameActionException {
        Direction left = togo.rotateLeft();
        Direction right = togo.rotateRight();

        if (rc.onTheMap(currentLocation.add(togo)) && rc.canSenseRobotAtLocation(currentLocation.add(togo)))  {
//            hardReset();
//            return getGreedyDirection();
//            return Direction.CENTER;
        }
        if (canMove(togo)) {
            return togo;
        }
/*
        MapLocation tryLeft = currentLocation.add(left);
        MapLocation tryRight = currentLocation.add(right);

        if (tryLeft.distanceSquaredTo(oldTarget) < tryRight.distanceSquaredTo(oldTarget)) {
            if (rc.onTheMap(currentLocation.add(left)) && rc.canSenseRobotAtLocation(currentLocation.add(left))
                    && rc.senseRobotAtLocation(currentLocation.add(left)).getTeam() == rc.getTeam().opponent())  {
                return Direction.CENTER;
            }
            if (canMove(left)) {
                return left;
            }
            if (rc.onTheMap(currentLocation.add(right)) && rc.canSenseRobotAtLocation(currentLocation.add(right))
                    && rc.senseRobotAtLocation(currentLocation.add(right)).getTeam() == rc.getTeam().opponent())  {
                return Direction.CENTER;
            }
            if (canMove(right)) {
                return right;
            }
        } else {
            if (rc.onTheMap(currentLocation.add(right)) && rc.canSenseRobotAtLocation(currentLocation.add(right))
                    && rc.senseRobotAtLocation(currentLocation.add(right)).getTeam() == rc.getTeam().opponent())  {
                return Direction.CENTER;
            }
            if (canMove(right)) {
                return right;
            }
            if (rc.onTheMap(currentLocation.add(left)) && rc.canSenseRobotAtLocation(currentLocation.add(left))
                    && rc.senseRobotAtLocation(currentLocation.add(left)).getTeam() == rc.getTeam().opponent())  {
                return Direction.CENTER;
            }
            if (canMove(left)) {
                return left;
            }
        }

        // bruh rip
*/
        return null;
    }

    static MapLocation prevLocation = null;
    public boolean[][] inbound = new boolean[9][9];
//
//    static Direction tryBFS(MapLocation currentTarget) throws GameActionException {
//        MapInfo[] infos = rc.senseNearbyMapInfos();
//
//        int arr[][] = new int[9][9];
//        boolean seen[][] = new boolean[9][9];
//        int from[][] = new int[9][9];
//
//        MapLocation starting = rc.getLocation();
//
//        for (int i = 0; i < infos.length; i++) {
//            MapLocation mapLocation = infos[i].getMapLocation();
//
//            if (!infos[i].isPassable()) {
//                arr[mapLocation.x - starting.x + 4][mapLocation.y - starting.y + 4] = 2;
//            } else if(rc.senseRobotAtLocation(mapLocation) != null) {
//                arr[mapLocation.x - starting.x + 4][mapLocation.y - starting.y + 4] = 2;
//            } else if (infos[i].getCurrentDirection() != Direction.CENTER) {
//                arr[mapLocation.x - starting.x + 4][mapLocation.y - starting.y + 4] = 2 + infos[i].getCurrentDirection().getDirectionOrderNum();
//            } else {
//                arr[mapLocation.x - starting.x + 4][mapLocation.y - starting.y + 4] = 1;
//            }
//        }
//
//        int dx = 4, dy = 4;
//
//        Queue.queuePush(dx * 10 + dy);
//
//        while (!Queue.queueEmpty()) {
//            int current = Queue.queuePop();
//
//            int x = current / 10, y = current % 10;
//
//            for (int cx = -1; cx <= 1; cx++) {
//                for (int cy = -1; cy <= 1; cy++) {
//                    int nx = x + cx, ny = y + cy;
//                    if (nx < 0 || nx >= 9 || ny < 0 || ny >= 9) continue;
//                    if (arr[nx][ny] == 0 || arr[nx][ny] == 2) continue;
//
//                    // if current or empty
//                    if (arr[nx][ny] == 1 && !seen[nx][ny]) {
//                        seen[nx][ny] = true;
//                        Queue.queuePush(nx * 10 + ny);
//                        from[nx][ny] = current;
//                    }
//
//                    // current
//
//                    Direction currentDir = Direction.DIRECTION_ORDER[arr[nx][ny]];
//                    int nnx = currentDir.getDeltaX() + nx, nny = currentDir.getDeltaY() + ny;
//                    if (nnx < 0 || nnx >= 9 || nny < 0 || nny >= 9) continue;
//                    if (arr[nnx][nny] == 0) continue;
//
//                    if (arr[nnx][nny] == 1) {
//                        nnx = nx; nny = ny;
//                    }
//
//                    if (!seen[nnx][nny]) {
//                        seen[nnx][nny] = true;
//                        Queue.queuePush(nnx * 10 + nny);
//                        from[nnx][nny] = nx * 10 + ny;
//                        from[nx][ny] = current;
//                    }
//                }
//            }
//        }
//
//        int cx = currentTarget.x - starting.x + 4, cy = currentTarget.y - starting.y + 4;
//        int lastLoc = 0;
//        while (from[cx][cy] != 0) {
//            lastLoc = cx * 10 + cy;
//            int pos = from[cx][cy];
//            cx = pos / 10;
//            cy = pos % 10;
//        }
//
//        if (lastLoc == 0) {
//            // cannot find path (probs shoudl do something later)
//            return Direction.CENTER;
//        } else {
//            return (new MapLocation(4, 4).directionTo(new MapLocation(lastLoc / 10, lastLoc % 10)));
//        }
//    }


    static Direction tryMove(RobotController rc, MapLocation currentTarget, Direction previous) throws GameActionException {
        if (rng == null) rng = new Random(rc.getID());
        rc.setIndicatorString((currentState==State.NORMAL?"Normal":"Wall"));
//        if (rc.canSenseLocation(currentTarget)) {
//            hardReset();
//            if (rc.getType() == RobotType.CARRIER) {
//                int holding = rc.getResourceAmount(ResourceType.ADAMANTIUM) + rc.getResourceAmount(ResourceType.MANA) + rc.getResourceAmount(ResourceType.ELIXIR);
//                if (rc.getMovementCooldownTurns() + (3 * holding / 8) + 5 < 10) {
//                    return tryBFS(currentTarget);
//                }
//            }
//            return tryBFS(currentTarget);
//        }



        if (previous != null && lastDirection == Direction.CENTER) lastDirection = previous;
        else {
            if (prevLocation == null) {
                prevLocation = rc.getLocation();
            } else if (!rc.getLocation().equals(prevLocation)) {
                lastDirection = prevLocation.directionTo(rc.getLocation());
                prevLocation = rc.getLocation();
            }
        }
        if (lastDirection == Direction.CENTER) lastDirection = Direction.values()[rng.nextInt(8)+1];

        if (!rc.isMovementReady()) return null;
        Movement.rc = rc;

        if (oldTarget == null) oldTarget = currentTarget;

        if (!oldTarget.equals(currentTarget)) {
            oldTarget = currentTarget;
            currentState = State.NORMAL;
            bugLength = 0;
            lastDirection = Direction.CENTER;

            if (previous != null) lastDirection = previous;
        }

        currentLocation = rc.getLocation();
        Direction togo = currentLocation.directionTo(oldTarget);
        if (togo.equals(Direction.CENTER)) return null;

        if (currentState.equals(State.WALL)) {
            //rc.setIndicatorString(currentLocation.distanceSquaredTo(oldTarget) + " " + lastWall.distanceSquaredTo(oldTarget));
            if (currentLocation.distanceSquaredTo(oldTarget) < lastWall.distanceSquaredTo(oldTarget)) {
                currentState = State.NORMAL;
                bugLength = 0;
                shouldRight = true;
            }
        }


        if (currentState.equals(State.NORMAL)) {
            //rc.setIndicatorString("Became Normal");
            Direction nextMove = normal(togo);
            if (nextMove != null) {
                bugLength = 0;
                return lastDirection = nextMove;
            }
            else {
                lastDirection = togo;
            }

            // cant go forwards

            currentState = State.WALL;
            switchable = true;
            lastWall = currentLocation;
            shouldRight = true;
        }

        if (currentState.equals(State.WALL)) {
            bugLength++;
            Direction nextMove = alongWall(togo);

            return lastDirection = nextMove;
        }

        return null;
    }

}
