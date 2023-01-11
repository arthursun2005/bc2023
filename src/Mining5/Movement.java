package Mining5;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

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

    static boolean canMove(Direction desired) throws GameActionException {
        if (currentState == State.WALL) return rc.canMove(desired) && rc.senseMapInfo(currentLocation.add(desired)).getCurrentDirection().equals(Direction.CENTER);
        return rc.canMove(desired) && !rc.senseMapInfo(currentLocation.add(desired)).getCurrentDirection().equals(desired.opposite());
    }

    static Direction alongWall(Direction desired) throws GameActionException {
//        if (canMove(desired)) {
//            return desired;
//        }

        if (shouldRight) {
            Direction checkDir = lastDirection;

            if (lastDirection == Direction.CENTER) {
                lastDirection = Direction.NORTH;
            }

//            if (turningLeft) {
//                checkDir = lastDirection.rotateRight();
//            } else {
//                checkDir = lastDirection.rotateLeft();
//            }


            if (turningLeft) {
                for (int i = 0; i < 8; i++) {
                    /*if (rc.onTheMap(currentLocation.add(checkDir)) && rc.canSenseRobotAtLocation(currentLocation.add(checkDir))
                            && rc.senseRobotAtLocation(currentLocation.add(checkDir)).getTeam() == rc.getTeam().opponent())  {
                        return Direction.CENTER;
                    }*/
                    if (canMove(checkDir)) break;
                    checkDir = checkDir.rotateRight();
                }
            } else {
                for (int i = 0; i < 8; i++) {
                    /*if (rc.onTheMap(currentLocation.add(checkDir)) && rc.canSenseRobotAtLocation(currentLocation.add(checkDir))
                            && rc.senseRobotAtLocation(currentLocation.add(checkDir)).getTeam() == rc.getTeam().opponent())  {
                        return Direction.CENTER;
                    }*/
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

            /*if (rc.onTheMap(currentLocation.add(checkDir)) && rc.canSenseRobotAtLocation(currentLocation.add(checkDir))
                    && rc.senseRobotAtLocation(currentLocation.add(checkDir)).getTeam() == rc.getTeam().opponent())  {
                return Direction.CENTER;
            }*/
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

        /*if (rc.onTheMap(currentLocation.add(togo)) && rc.canSenseRobotAtLocation(currentLocation.add(togo))
                && rc.senseRobotAtLocation(currentLocation.add(togo)).getTeam() == rc.getTeam().opponent())  {
            return Direction.CENTER;
        }*/
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

    static Direction tryMove(RobotController rc, MapLocation currentTarget, Direction previous) throws GameActionException {
        if (previous != null && lastDirection == Direction.CENTER) lastDirection = previous;
        else {
            if (prevLocation == null) {
                prevLocation = rc.getLocation();
            } else if (!rc.getLocation().equals(prevLocation)) {
                lastDirection = prevLocation.directionTo(rc.getLocation());
                prevLocation = rc.getLocation();
            }
        }
        if (lastDirection == Direction.CENTER) lastDirection = Direction.NORTH;

        if (!rc.isMovementReady()) return null;
        Movement.rc = rc;

        if (oldTarget == null) oldTarget = currentTarget;

        if (!oldTarget.equals(currentTarget)) {
            oldTarget = currentTarget;
            currentState = State.NORMAL;
            lastDirection = Direction.CENTER;

            if (previous != null) lastDirection = previous;
        }

        currentLocation = rc.getLocation();
        Direction togo = currentLocation.directionTo(oldTarget);
        if (togo.equals(Direction.CENTER)) return null;

        if (currentState.equals(State.WALL)) {
            if (currentLocation.distanceSquaredTo(oldTarget) < lastWall.distanceSquaredTo(oldTarget)) {
                currentState = State.NORMAL;
                shouldRight = true;
            }
        }


        if (currentState.equals(State.NORMAL)) {
            Direction nextMove = normal(togo);
            if (nextMove != null) {
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
            Direction nextMove = alongWall(togo);

            return lastDirection = nextMove;
        }

        return null;
    }

}
