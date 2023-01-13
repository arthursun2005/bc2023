package umbreon;

import battlecode.common.*;
import java.util.*;

import java.lang.Math.*;
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
    static int turnCount = 0;

    static Direction[] directions = Direction.values();

    public Robot(RobotController rc)
    {
        Robot.rc = rc;
        rng = new Random(rc.getID());
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

    public boolean tryAttack() throws GameActionException
    {
        int radius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);
        MapLocation attackLoc = null;
        int minHealth = -1;
        int theID = -1;
        for (RobotInfo enemy : enemies) {
            MapLocation toAttack = enemy.location;
            if (rc.canAttack(toAttack)) {
                int adjustedHealth = ((enemy.getHealth() + 5) / 6) * 123456 + enemy.getID();
                if (enemy.type.equals(RobotType.LAUNCHER)) adjustedHealth -= 123456789;
                if (minHealth == -1 || adjustedHealth < minHealth)
                {
                    minHealth = adjustedHealth;
                    theID = enemy.getID();
                    attackLoc = toAttack;
                }
            }
        }
        if (attackLoc != null)
        {
            rc.attack(attackLoc);
            return true;
        }
        return false;
    }

    public MapLocation getClosestHQLoc() throws GameActionException
    {
        int dist = -1;
        MapLocation loc = null;
        int cnt = rc.readSharedArray(63);
        for (int i = 0; i < cnt; i++)
        {
            MapLocation c = readCoords(i);
            int w = rc.getLocation().distanceSquaredTo(c);
            if (dist == -1 || w < dist)
            {
                dist = w;
                loc = c;
            }
        }
        return loc;
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

    public void prepare() throws GameActionException
    {
        if (parentLoc == null && !rc.getType().equals(RobotType.HEADQUARTERS))
        {
            RobotInfo[] friends = rc.senseNearbyRobots(2, rc.getTeam());
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

        turnCount += 1;
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

    public void moveTo(MapLocation loc) throws GameActionException
    {
        if (rc.isMovementReady()) {
            Direction dir = movement.tryMove(rc, loc, prevDirection);
            tryMove(dir);
        }
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
