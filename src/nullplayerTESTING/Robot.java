package nullplayerTESTING;

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

    BFS bfs;
    static MapLocation prevLocation = null;
    static Direction prevDirection = null;
    public Robot(RobotController rc) throws GameActionException {
        Robot.rc = rc;
        rng = new Random(rc.getID());
        bfs = new BFS(rc);
    }
    public void run() throws GameActionException {
        runUnit();
    }

    abstract void runUnit() throws GameActionException;

}
