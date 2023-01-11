package Betterbug;

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

    public Robot(RobotController rc) {
        Robot.rc = rc;
        rng = new Random(rc.getID());

    }
    public void run() throws GameActionException {
        // Before unit runs
        turnCount++;
        currentRound = rc.getRoundNum();

        // Does turn
        runUnit();

    }

    enum State {
        NORMAL,
        WALL
    }

    abstract void runUnit() throws GameActionException;
    static MapLocation target;
    static boolean firstTime = true;
    static MapLocation startLoc;

    static State state = State.NORMAL;
    static void resetMovement(MapLocation newLoc) {
        firstTime = true;
        target = newLoc;
        startLoc = rc.getLocation();
        state = State.NORMAL;
    }

    static boolean blockedByEnemy(MapLocation loc) throws GameActionException {
        return (rc.onTheMap(loc) && rc.canSenseRobotAtLocation(loc)
                && rc.senseRobotAtLocation(loc).getTeam() == rc.getTeam().opponent());
    }

    static boolean isBoundary(MapLocation ahead) throws GameActionException {
        Direction temp = Direction.NORTH;

        for (int i = 0; i < 9; i++) {
            MapLocation adjacent = ahead.add(temp);
            if (blockedByEnemy(adjacent)) continue;
            if (!rc.canMove(temp)) return true;
            if (!rc.sensePassability(adjacent)) return true;
        }

        return false;
    }
    static Direction tryMove(MapLocation newLoc) throws GameActionException {
        MapLocation currentLocation = rc.getLocation();
        if (currentLocation.equals(newLoc)) return null;

        if (newLoc != target) {
            resetMovement(newLoc);
        }

        if (firstTime) {
            Direction optimal = currentLocation.directionTo(newLoc);
            MapLocation ahead = currentLocation.add(optimal);

            if (blockedByEnemy(ahead)) return Direction.CENTER;
            if (isBoundary(ahead)) {
                // append ahead locatino to boundary
            }
            if (rc.canMove(optimal)) {
                return optimal;
            }
            // blocked bruh

            firstTime = false;
        }

        if (currentLocation.equals(startLoc)) {
            state = State.WALL;
        }

        if (state == State.NORMAL) {

        }
    }
}
