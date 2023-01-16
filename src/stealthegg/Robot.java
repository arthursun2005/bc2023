package stealthegg;

import battlecode.common.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class Robot
{
    static Random rng;
    static RobotController rc;

    static int moveCount = 5;
    static Direction curDir = Direction.CENTER;

    static MapLocation parentLoc = null;
    static MapLocation prevLocation = null;
    static Direction prevDirection = null;

    static Movement movement;
    static Frontier frontier;
    static Direction[] directions = Direction.values();
    static int turnCount = 0;

    static Tracker tracker;
    public Robot(RobotController rc)
    {
        Robot.rc = rc;
        rng = new Random(rc.getID());
        tracker = new Tracker(rc);
    }

    public Direction[] getGreedyDirections(Direction bestDir) throws GameActionException
    {
        Direction[] directions = {
            bestDir,
            bestDir.rotateRight(),
            bestDir.rotateLeft(),
            bestDir.rotateLeft().rotateLeft(),
            bestDir.rotateRight().rotateRight(),
            bestDir.rotateRight().rotateRight().rotateRight(),
            bestDir.rotateLeft().rotateLeft().rotateLeft(),
        };
        return directions;
    }
    public boolean writeCoords(int idx, MapLocation loc) throws GameActionException
    {
        int value = loc.x + loc.y * 69;
        if (rc.canWriteSharedArray(idx, value))
        {
            rc.writeSharedArray(idx, value);
            return true;
        }
        return false;
    }

    public MapLocation readCoords(int idx) throws GameActionException
    {
        int value = rc.readSharedArray(idx);
        int x = value % 69;
        int y = value / 69;
        return new MapLocation(x, y);
    }

    public void greedilyMoveAway(MapLocation loc) throws GameActionException
    {
        int dist = rc.getLocation().distanceSquaredTo(loc);
        Direction best = null;
        for (Direction dir : directions)
        {
            if (!rc.canMove(dir)) continue;
            int w = rc.adjacentLocation(dir).distanceSquaredTo(loc);
            if (w > dist)
            {
                best = dir;
                dist = w;
            }
        }
        if (best != null)
        {
            rc.move(best);
        }
    }

    public void prepare() throws GameActionException
    {
        if (parentLoc == null && !rc.getType().equals(RobotType.HEADQUARTERS))
        {
            RobotInfo[] friends = rc.senseNearbyRobots(42069, rc.getTeam());
            for (RobotInfo ri : friends)
            {
                if (ri.type.equals(RobotType.HEADQUARTERS))
                {
                    parentLoc = ri.getLocation();
                    break;
                }
            }
        }
        if (prevLocation == null) {
            prevLocation = rc.getLocation();
        } else if (!rc.getLocation().equals(prevLocation)) {
            prevDirection = prevLocation.directionTo(rc.getLocation());
            prevLocation = rc.getLocation();
        }

        if (!Tracker.calcHQ) {
            Tracker.readHQLoc();
            Tracker.calcHQ = true;
        }

        if (rc.getRoundNum() == 1 && rc.getType().equals(RobotType.HEADQUARTERS)) {
            Tracker.writeHQLoc();
        }

        tracker.senseWells();

        turnCount++;
    }
    abstract void run() throws GameActionException;

    public boolean tryMove(Direction dir) throws GameActionException
    {
        if (dir == null) return false;
        if (rc.canMove(dir))
        {
            rc.move(dir);
            return true;
        }
        return false;
    }

    public boolean moveTo(MapLocation loc) throws GameActionException
    {
        if (rc.isMovementReady()) {
            Direction dir = movement.tryMove(rc, loc, prevDirection);
            return tryMove(dir);
        }
        return false;
    }

    public void moveAway(MapLocation loc) throws GameActionException
    {
        MapLocation me = rc.getLocation();
        moveTo(new MapLocation(8 * me.x - 7 * loc.x, 8 * me.y - 7 * loc.y));
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

    static void moveRandom() throws GameActionException {
//        if (curDir == Direction.CENTER) curDir = Direction.values()[rng.nextInt(8)+1];
        if (curDir == Direction.CENTER) curDir = rc.getLocation().directionTo(new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2));

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
