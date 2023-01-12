package viho;

import battlecode.common.*;

import java.util.ArrayList;
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


// WELL FINDING
    static ArrayList<MapLocation> seenWells = new ArrayList<>();
    static int curWellSharedArray = 50;
    static boolean[][] isWell = new boolean[64][64];

// END WELL FINDING

    public Robot(RobotController rc) {
        Robot.rc = rc;
        rng = new Random(rc.getID());

    }

    static void updateWells() throws GameActionException {
        while (curWellSharedArray < 64) {
            int wellEncode = rc.readSharedArray(curWellSharedArray)-1;
            if (wellEncode < 0) break;
            int wellX = wellEncode / 69;
            int wellY = wellEncode % 69;
            isWell[wellX][wellY] = true;
            curWellSharedArray++;
        }
        if (!rc.canWriteSharedArray(0, 0)) return;
        for (MapLocation newWell : seenWells) {
            if (!isWell[newWell.x][newWell.y] && curWellSharedArray < 64) {
                isWell[newWell.x][newWell.y] = true;
                rc.writeSharedArray(curWellSharedArray, newWell.x*69+newWell.y+1);
//                System.out.println(rc.getType() + " WROTE TO THE ARRAY! YAY");
                curWellSharedArray++;
            }
        }
        seenWells.clear();
    }

    public void wells() throws GameActionException {
        if (rc.getType() != RobotType.AMPLIFIER) {
            WellInfo[] nearbyWells = rc.senseNearbyWells();
            for (int j=0; j<nearbyWells.length; j++) {;
                WellInfo testing = nearbyWells[j];
                MapLocation loc = testing.getMapLocation();
                seenWells.add(loc);
            }

            updateWells();
        }
    }
    abstract void init() throws GameActionException;
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

        // Robot init
        init();
        // Does turn
        runUnit();

        if (Clock.getBytecodesLeft() >= 500) {
            wells();
        }
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
