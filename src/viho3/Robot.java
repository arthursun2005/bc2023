package viho3;

import battlecode.common.*;
import battlecode.world.Well;

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
    static ArrayList<WellInfo> seenWells = new ArrayList<>();
    static int curWellSharedArray = 50;
    static boolean[][] isWell = new boolean[64][64];
    static ArrayList<MapLocation> wells = new ArrayList<>();
    static ArrayList<MapLocation> adaWells = new ArrayList<>();
    static ArrayList<MapLocation> manaWells = new ArrayList<>();
    static ArrayList<MapLocation> elixirWells = new ArrayList<>();

// END WELL FINDING

    public Robot(RobotController rc) {
        Robot.rc = rc;
        rng = new Random(rc.getID());

    }

    static void updateWells() throws GameActionException {
        while (curWellSharedArray < 64) {
            int wellEncode = rc.readSharedArray(curWellSharedArray)-1;
            if (wellEncode < 0) break;
            int wellPos = wellEncode / 3;
            int wellType = wellEncode % 3;
            int wellX = wellPos / 69;
            int wellY = wellPos % 69;
            wells.add(new MapLocation(wellX, wellY));
            if (wellType == 0) {
                adaWells.add(new MapLocation(wellX, wellY));
            }
            if (wellType == 1) {
                manaWells.add(new MapLocation(wellX, wellY));
            }
            if (wellType == 2) {
                elixirWells.add(new MapLocation(wellX, wellY));
            }
            isWell[wellX][wellY] = true;
            curWellSharedArray++;
        }
        if (!rc.canWriteSharedArray(0, 0)) return;
        for (WellInfo newWell : seenWells) {
            MapLocation newWellPos = newWell.getMapLocation();
            ResourceType newWellType = newWell.getResourceType();
            int newWellTypeCode = 0;
            if (newWellType == ResourceType.MANA) newWellTypeCode = 1;
            if (newWellType == ResourceType.ELIXIR) newWellTypeCode = 2;
            if (!isWell[newWellPos.x][newWellPos.y] && curWellSharedArray < 64) {
                isWell[newWellPos.x][newWellPos.y] = true;
                rc.writeSharedArray(curWellSharedArray, (newWellPos.x*69+newWellPos.y)*3+newWellTypeCode+1);
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
                seenWells.add(testing);
            }

            updateWells();
        }
    }

    public void updateCount(int resource, int delta) throws GameActionException {
        // 0 = ada, 1 = mana, 2 = elixir
        int cur = rc.readSharedArray(31+resource) + delta;
        if (rc.canWriteSharedArray(31+resource, cur)) {
            rc.writeSharedArray(31+resource, cur);
        }
    }   

    public int getCount(int resource) throws GameActionException {
        return rc.readSharedArray(31+resource);
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
