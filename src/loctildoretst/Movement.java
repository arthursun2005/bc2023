// package loctildoretst;
//
// import battlecode.common.*;
//
// public class Movement {
//     RobotController rc;
//
//     boolean rotateRight = true;
//     int ever;
//     MapLocation wall;
//     MapLocation prevTarget;
//
//     void reset() {
//         ever = 696969;
//         wall = null;
//         prevTarget = null;
//         rotateRight = rc.getID() % 2 == 0;
//     }
//
//     Movement(RobotController rc) {
//         this.rc = rc;
//         reset();
//     }
//
//     void moveTo(MapLocation target) throws GameActionException {
//         if (target == null) return;
//         if (rc.getLocation().equals(target)) return;
//         if (rc.getLocation().distanceSquaredTo(target) <= 2 && rc.canSenseRobotAtLocation(target)) return;
//
//         //different target? ==> previous data does not help!
//         if (prevTarget == null || !target.equals(prevTarget)) reset();
//
//         //If I'm at a minimum distance to the target, I'm free!
//         MapLocation myLoc = rc.getLocation();
//         int d = myLoc.distanceSquaredTo(target);
//         if (d <= ever) reset();
//
//         //Update data
//         prevTarget = target;
//         ever = Math.min(d, ever);
//
//         //If there's an obstacle I try to go around it [until I'm free] instead of going to the target directly
//         Direction dir = myLoc.directionTo(target);
//         if (wall != null) dir = myLoc.directionTo(wall);
//
//         //This should not happen for a single unit, but whatever
//         if (rc.canMove(dir)) reset();
//
//         //I rotate clockwise or counterclockwise (depends on 'rotateRight'). If I try to go out of the map I change the orientation
//         //Note that we have to try at most 16 times since we can switch orientation in the middle of the loop. (It can be done more efficiently)
//         for (int i = 0; i < 16; ++i){
//             if (rc.canMove(dir)){
//                 rc.move(dir);
//                 return;
//             }
//             MapLocation newLoc = myLoc.add(dir);
//             if (!rc.onTheMap(newLoc)) rotateRight = !rotateRight;
//             //If I could not go in that direction and it was not outside of the map, then this is the latest obstacle found
//             else wall = myLoc.add(dir);
//             if (rotateRight) dir = dir.rotateRight();
//             else dir = dir.rotateLeft();
//         }
//
//         if (rc.canMove(dir)) rc.move(dir);
//     }
// }

package loctildoretst;

import battlecode.common.*;
import java.util.Random;

enum State {
    WALL,
    NORMAL
}

public class Movement {

    MapLocation oldTarget;
    MapLocation lastWall;
    State currentState = State.NORMAL;

    RobotController rc;
    MapLocation currentLocation;
    Direction lastDirection = Direction.CENTER;

    boolean turningLeft = true;
    boolean shouldRight = true;

    boolean switchable = false;

    Random rng = null;

    int bugLength = 0;

    Movement(RobotController rc) {
        this.rc = rc;
        turningLeft = rc.getID()%2 == 1;
    }

    StringBuilder invalid = new StringBuilder(String.format("%3690s",""));

    MapLocation bestieLoc = null;

    void calcTurn() {
        int ax = rc.getMapWidth() / 2 - rc.getLocation().x;
        int ay = rc.getMapHeight() / 2 - rc.getLocation().y;
        int bx = rc.getMapWidth() / 2 - oldTarget.x;
        int by = rc.getMapHeight() / 2 - oldTarget.y;
        int cprod = ax * by - bx * ay;
        turningLeft = (cprod < 0);
    }

    boolean canMove(Direction desired) throws GameActionException {
        if (!rc.canMove(desired)) return false;

        if (bestieLoc != null && (bestieLoc.distanceSquaredTo(rc.getLocation().add(desired))) >= 15) return false;

        MapLocation tar = rc.getLocation().add(desired);
        Direction current = rc.senseMapInfo(tar).getCurrentDirection();
        if (current == Direction.CENTER) return true;

        if (invalid.charAt(tar.x*60+tar.y)=='1') return false;

        if (current == desired || current == desired.rotateLeft() || current == desired.rotateRight()) return true;

        invalid.setCharAt(tar.x*60+tar.y, '1');
        return false;
    }

    void hardReset() {
        lastDirection = Direction.CENTER;
        currentState = State.NORMAL;

        invalid = new StringBuilder(String.format("%3690s",""));

        //turningLeft = rc.getID()%2 == 1;
        calcTurn();
        shouldRight = true;
        bugLength = 0;
        switchable = false;
    }

    Direction getGreedyDirection() {
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
    Direction alongWall(Direction desired) throws GameActionException {
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
                lastDirection = rc.getLocation().directionTo(oldTarget);//Direction.values()[rng.nextInt(8)+1];
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
            if (canMove(checkDir.rotateLeft())) {
                currentState = State.NORMAL;
                bugLength = 0;
                shouldRight = true;
                return tryMove(oldTarget, checkDir.rotateLeft());
            }
        } else {
            checkDir = checkDir.rotateRight().rotateRight();
            if (canMove(checkDir.rotateRight())) {
                currentState = State.NORMAL;
                bugLength = 0;
                shouldRight = true;
                return tryMove(oldTarget, checkDir.rotateRight());
            }
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

    Direction normal(Direction togo) throws GameActionException {
        // Direction left = togo.rotateLeft();
        // Direction right = togo.rotateRight();

        if (rc.onTheMap(currentLocation.add(togo)) && rc.canSenseRobotAtLocation(currentLocation.add(togo)))  {
//            hardReset();
//            return getGreedyDirection();
//            return Direction.CENTER;
        }
        if (canMove(togo)) {
            return togo;
        }

        Direction left = togo.rotateLeft();
        Direction right = togo.rotateRight();

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

        return null;
    }

    MapLocation prevLocation = null;
    public boolean[][] inbound = new boolean[9][9];
//
//    Direction tryBFS(MapLocation currentTarget) throws GameActionException {
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


    Direction tryMove(MapLocation currentTarget, Direction previous) throws GameActionException {
        if (rng == null) rng = new Random(rc.getID());
        //rc.setIndicatorString((currentState==State.NORMAL?"Normal":"Wall"));
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

        if (oldTarget == null) {
            oldTarget = currentTarget;

            calcTurn();
        }

        if (!oldTarget.equals(currentTarget)) {
            oldTarget = currentTarget;
            currentState = State.NORMAL;
            bugLength = 0;
            lastDirection = Direction.CENTER;
            invalid = new StringBuilder(String.format("%3690s",""));

            calcTurn();

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

    void moveTo(MapLocation loc) throws GameActionException {
        bestieLoc = null;
        if (!rc.isMovementReady()) return;
        if (rc.getLocation().distanceSquaredTo(loc) == 0) return;
        if (rc.getLocation().distanceSquaredTo(loc) <= 2 && (rc.senseRobotAtLocation(loc) != null || !rc.sensePassability(loc))) return;
        Direction dir = tryMove(loc, null);
        if (dir != null && rc.canMove(dir)) rc.move(dir);
    }

    void moveToBestie(MapLocation loc) throws GameActionException {
        bestieLoc = loc;
        if (!rc.isMovementReady()) return;
        if (rc.getLocation().distanceSquaredTo(loc) == 0) return;
        if (rc.getLocation().distanceSquaredTo(loc) <= 2 && (rc.senseRobotAtLocation(loc) != null || !rc.sensePassability(loc))) return;
        Direction dir = tryMove(loc, null);
        if (dir != null && rc.canMove(dir)) rc.move(dir);
    }
}
