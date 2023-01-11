package omegaASS;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
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

    abstract void runUnit() throws GameActionException;
}
