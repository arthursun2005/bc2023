package berlin;

import battlecode.common.*;
import java.util.*;

public abstract class Robot {
    RobotController rc;
    Random rng;

    Attack attack;
    Tracker tracker;
    Movement movement;

    int creationRound;
    int turnCount;

    Direction[] directions = Direction.values();

    public Robot(RobotController rc) throws GameActionException {
        this.rc = rc;
        Util.rc = rc;
        attack = new Attack(rc, this);
        tracker = new Tracker(rc);
        movement = new Movement(rc);
        rng = new Random(rc.getID() + 369);
        creationRound = rc.getRoundNum();
        turnCount = 0;

        tracker.readHQLocs();
    }

    public void prepare() throws GameActionException {
        if (turnCount > 3 || !rc.getType().equals(RobotType.LAUNCHER))
            tracker.update();
        attack.update();
        turnCount++;
    }

    public void moveTo(MapLocation loc) throws GameActionException {
        movement.moveTo(loc);
    }

    public void greedilyMove(MapLocation loc, int mul) throws GameActionException {
        Direction best = null;
        MapLocation HQLoc = tracker.getClosestHQLoc();
        MapLocation me = rc.getLocation();
        if (loc == null)
            loc = me;
        int dist = me.distanceSquaredTo(loc) * mul * 50 + me.distanceSquaredTo(HQLoc);
        for (Direction dir : directions) {
            if (!rc.canMove(dir))
                continue;
            int w = rc.adjacentLocation(dir).distanceSquaredTo(loc) * mul * 50
                    + rc.adjacentLocation(dir).distanceSquaredTo(HQLoc);
            if (w < dist) {
                dist = w;
                best = dir;
            }
        }
        if (best != null) {
            rc.move(best);
        }
    }

    abstract void run() throws GameActionException;

    MapLocation exploreTarget = null;

    public void explore() throws GameActionException {
        if (exploreTarget == null || rc.getLocation().distanceSquaredTo(exploreTarget) <= 18) {
            int width = rc.getMapWidth();
            int height = rc.getMapHeight();
            exploreTarget = new MapLocation(4 + rng.nextInt(width - 8), 4 + rng.nextInt(height - 8));
        }
        moveTo(exploreTarget);
    }

    public void spreadOut(boolean all) throws GameActionException {
        if (!rc.isMovementReady())
            return;
        int cnt[] = new int[9];
        Direction[] good = new Direction[9];
        RobotInfo[] robots = rc.senseNearbyRobots(-1, rc.getTeam());
        for (RobotInfo ri : robots) {
            if (all || ri.type.equals(rc.getType())) {
                Direction dir = rc.getLocation().directionTo(ri.getLocation());
                cnt[dir.ordinal()]++;
            }
        }
        int least = 696969;
        for (int i = 0; i < 8; i++) {
            least = Math.min(least, cnt[i]);
        }

        int gc = 0;
        for (int i = 0; i < 8; i++) {
            if (cnt[i] == least) {
                good[gc] = directions[i];
                gc++;
            }
        }
        for (int i = 1; i < gc; i++) {
            int j = rng.nextInt(i + 1);
            if (i != j) {
                Direction a = good[i];
                good[i] = good[j];
                good[j] = a;
            }
        }
        for (int i = 0; i < gc; i++) {
            if (rc.canMove(good[i])) {
                rc.move(good[i]);
                break;
            }
        }
    }
}
