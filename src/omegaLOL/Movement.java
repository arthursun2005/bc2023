package omegaLOL;

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
    static Direction alongWall(Direction desired) throws GameActionException {
//        if (rc.canMove(desired)) {
//            return desired;
//        }

        if (shouldRight) {
            rc.setIndicatorString("YAY");
            Direction checkDir = lastDirection;

//            if (turningLeft) {
//                checkDir = lastDirection.rotateRight();
//            } else {
//                checkDir = lastDirection.rotateLeft();
//            }


            if (turningLeft) {
                for (int i = 0; i < 8; i++) {
                    if (rc.canMove(checkDir)) break;
                    checkDir = checkDir.rotateRight();
                }
            } else {
                for (int i = 0; i < 8; i++) {
                    if (rc.canMove(checkDir)) break;
                    checkDir = checkDir.rotateLeft();
                }
            }

            shouldRight = false;

            return checkDir;
        }

        Direction checkDir = lastDirection;

        if (turningLeft) {
            checkDir = checkDir.rotateLeft().rotateLeft();
        } else {
            checkDir = checkDir.rotateRight().rotateRight();
        }


        for (int i = 0; i < 8; i++) {
            MapLocation temp = currentLocation.add(checkDir);
            if (!rc.onTheMap(temp)) {
                turningLeft = !turningLeft;
                lastDirection = lastDirection.opposite();
                return alongWall(desired);
            }

            if (rc.canMove(checkDir)) break;

            if (turningLeft) {
                checkDir = checkDir.rotateRight();
            } else {
                checkDir = checkDir.rotateLeft();
            }
        }

        return checkDir;
    }

    static Direction normal(Direction togo) {
        Direction left = togo.rotateLeft();
        Direction right = togo.rotateRight();

        if (rc.canMove(togo)) return togo;

        MapLocation tryLeft = currentLocation.add(left);
        MapLocation tryRight = currentLocation.add(right);

        if (tryLeft.distanceSquaredTo(oldTarget) < tryRight.distanceSquaredTo(oldTarget)) {
            if (rc.canMove(left)) return left;
            if (rc.canMove(right)) return right;
        } else {
            if (rc.canMove(right)) return right;
            if (rc.canMove(left)) return left;
        }

        // bruh rip

        return null;
    }
    static Direction tryMove(RobotController rc, MapLocation currentTarget) throws GameActionException {
        if (!rc.isMovementReady()) return null;
        Movement.rc = rc;

        if (oldTarget == null) oldTarget = currentTarget;

        if (!oldTarget.equals(currentTarget)) {
            oldTarget = currentTarget;
            currentState = State.NORMAL;
            lastDirection = Direction.CENTER;
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
            if (nextMove != null) return lastDirection = nextMove;

            // cant go forwards

            rc.setIndicatorString("WALL");
            currentState = State.WALL;
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
