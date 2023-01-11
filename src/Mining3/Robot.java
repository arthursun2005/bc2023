package Mining3;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

import java.util.Random;

public abstract class Robot {
    static Random rng;

    /** Array containing all the possible movement directions. */
    static final Direction[] directions = {
            Direction.NORTH,
            Direction.NORTHEAST,
            Direction.EAST,
            Direction.SOUTHEAST,
            Direction.SOUTH,
            Direction.SOUTHWEST,
            Direction.WEST,
            Direction.NORTHWEST,
    };

    static int moveCount = 0;
    static Direction curDir = Direction.NORTH;
    static RobotController rc;

    static int turnCount = 0;
    static int currentRound;

    static Movement movement;

    static MapLocation prevLocation = null;
    static Direction prevDirection = null;
    public Robot(RobotController rc) {
        Robot.rc = rc;
        rng = new Random(rc.getID());

    }
    public void run() throws GameActionException {
        // Before unit runs
        turnCount++;
        currentRound = rc.getRoundNum();

        if (prevLocation == null) {
            prevLocation = rc.getLocation();
        } else if (!rc.getLocation().equals(prevLocation)) {
            prevDirection = prevLocation.directionTo(rc.getLocation());
            prevLocation = rc.getLocation();
        }

        // Does turn
        runUnit();

    }

    abstract void runUnit() throws GameActionException;

    public void moveToLocation(MapLocation loc) throws GameActionException {
        // CHECK IF MOVE IS VALID

        if (rc.isMovementReady()) {
            Direction dir = movement.tryMove(rc, loc, prevDirection);
            if (dir != null && rc.canMove(dir)) {
                rc.move(dir);
            } else return;
        }
    }

    static void moveRandom() throws GameActionException {
        if (rc.isMovementReady()) {
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
