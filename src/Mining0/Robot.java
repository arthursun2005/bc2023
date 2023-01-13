package Mining0;

import battlecode.common.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    static Direction curDir = Direction.CENTER;
    static RobotController rc;

    static Communication communication;
    static int turnCount = 0;
    static int currentRound;

    static int hqCount = 0;
    static boolean calcHQ = false;
    static Movement movement;
    static MapLocation prevLocation = null;
    static Direction prevDirection = null;

    static ArrayList<MapLocation> HQLocations = new ArrayList<>();
    // WELL FINDING
    static WellUtility wellUtility;

// END WELL FINDING

    public Robot(RobotController rc) {
        Robot.rc = rc;
        rng = new Random(rc.getID());

        wellUtility = new WellUtility(rc);
        communication = new Communication(rc);
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

        if (rc.getType().equals(RobotType.CARRIER)) {
            wellUtility.senseWells();
        }

        if (rc.getType().equals(RobotType.HEADQUARTERS) && rc.getRoundNum() == 1) {
            for (int i = 40; i < 50; i++) {
                if (rc.readSharedArray(i) != 0) continue;
                rc.writeSharedArray(i, rc.getLocation().x * 69 + rc.getLocation().y);
                break;
            }
            wellUtility.senseWells();
        }

        if (!calcHQ && rc.canWriteSharedArray(0, 0)) {
            calcHQ = true;
            for (int i = 40; i < 50; i++) {
                if (rc.readSharedArray(i) != 0) {
                    int decode = rc.readSharedArray(i);
                    hqCount++;
                    HQLocations.add(new MapLocation(decode / 69, decode % 69));
                }
            }
        }


        // Robot init
        init();
        // Does turn
        runUnit();

        if (Clock.getBytecodesLeft() >= 500) {
            wellUtility.senseWells();
        }
    }

    abstract void runUnit() throws GameActionException;

    MapLocation nearestHQ() {
        Collections.sort(HQLocations, new Comparator<MapLocation>() {
            public int compare(MapLocation a, MapLocation b) {
                return rc.getLocation().distanceSquaredTo(a) - rc.getLocation().distanceSquaredTo(b);
            }
        });

        return HQLocations.get(0);
    }
    public void moveTo(MapLocation loc) throws GameActionException
    {
        if (rc.isMovementReady()) {
            Direction dir = movement.tryMove(rc, loc, prevDirection);
            tryMove(dir);
        }
    }
    public void moveToLocation(MapLocation loc) throws GameActionException {
        // CHECK IF MOVE IS VALID

        if (rc.isMovementReady()) {
            Direction dir = movement.tryMove(rc, loc, prevDirection);
            if (dir != null && rc.canMove(dir)) {
                rc.move(dir);
            } else return;
        }
    }

    public void moveToLocationRepeat(MapLocation loc) throws GameActionException {
        // CHECK IF MOVE IS VALID

        while (rc.isMovementReady()) {
            Direction dir = movement.tryMove(rc, loc, prevDirection);
            if (dir != null && rc.canMove(dir)) {
                rc.move(dir);
            } else return;
        }
    }

    static void moveRandom() throws GameActionException {
        if (curDir == Direction.CENTER) curDir = Direction.values()[rng.nextInt(8)+1];
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
    public boolean tryMove(Direction dir) throws GameActionException
    {
        boolean moved = false;
        if (dir == null) return false;
        while (rc.canMove(dir))
        {
            moved = true;
            rc.move(dir);
        }

        return moved;
    }

    public void spreadOut(boolean all) throws GameActionException
    {
        // move in the direction with the least friendly robots
        // if all is true, consider robots of every type instead of just your own

        int cnt[] = new int[9];
        RobotInfo[] robots = rc.senseNearbyRobots(-1, rc.getTeam());
        for (RobotInfo ri : robots)
        {
            if (all || ri.type.equals(rc.getType()))
            {
                Direction dir = rc.getLocation().directionTo(ri.getLocation());
                cnt[dir.ordinal()]++;
            }
        }
        int least = 696969;
        for (int i = 0; i < 8; i++)
        {
            least = Math.min(least, cnt[i]);
        }
        ArrayList<Direction> dirs = new ArrayList<Direction>();
        for (int i = 0; i < 8; i++)
        {
            if (cnt[i] == least)
            {
                dirs.add(directions[i]);
            }
        }
        Collections.shuffle(dirs);
        for (Direction dir : dirs)
        {
            if (tryMove(dir))
            {
                break;
            }
        }
    }
}
