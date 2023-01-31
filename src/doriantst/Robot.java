package doriantst;

import battlecode.common.*;

public abstract class Robot {
    RobotController rc;
    CursedRandom rng;

    Attack attack;
    Tracker tracker;
    Movement movement;

    int creationRound;
    int roundNum;
    int turnCount;

    Direction[] directions = Direction.values();

    public Robot(RobotController rc) throws GameActionException {
        rng = new CursedRandom(rc);
        this.rc = rc;
        Util.rc = rc;
        attack = new Attack(rc, this);
        tracker = new Tracker(rc, this);
        movement = new Movement(rc);
        creationRound = rc.getRoundNum();
        turnCount = 0;

        tracker.readHQLocs();
    }

    public void prepare() throws GameActionException {
        rng = new CursedRandom(rc);
        if (turnCount > 3 || (!rc.getType().equals(RobotType.LAUNCHER) && !rc.getType().equals(RobotType.DESTABILIZER)))
            tracker.update();
        attack.updateFriends();
        // if (rc.getType().equals(RobotType.LAUNCHER) ||
        // rc.getType().equals(RobotType.DESTABILIZER))
        attack.update();
        turnCount++;
        roundNum = rc.getRoundNum();
    }

    public void moveTo(MapLocation loc) throws GameActionException {
        movement.moveTo(loc);
    }

    public void moveToBestie(MapLocation loc) throws GameActionException {
        // movement.moveToBestie(loc);
    }

    void tryMove(Direction dir) throws GameActionException {
        if (rc.canMove(dir))
            rc.move(dir);
    }

    public void greedilyMove(MapLocation loc, int mul) throws GameActionException {
        Direction best = null;
        MapLocation HQLoc = tracker.getClosestHQLoc();
        MapLocation me = rc.getLocation();
        if (loc == null)
            loc = me;
        // int dist = me.distanceSquaredTo(loc) * mul * 10000 + me.distanceSquaredTo(HQLoc);
        int dist = Util.rDist(rc.getLocation(), loc) * mul * 10000 + rc.getLocation().distanceSquaredTo(HQLoc);
        for (Direction dir : directions) {
            if (!rc.canMove(dir))
                continue;
            // int w = rc.adjacentLocation(dir).distanceSquaredTo(loc) * mul * 10000
            //         + rc.adjacentLocation(dir).distanceSquaredTo(HQLoc);
            int w = Util.rDist(rc.adjacentLocation(dir), loc) * mul * 10000 + rc.adjacentLocation(dir).distanceSquaredTo(HQLoc);
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

    int moveCount = 5;
    Direction curDir = Direction.CENTER;

    public void moveRandom() throws GameActionException {
        if (curDir == Direction.CENTER) curDir = Direction.values()[rng.nextInt(8)+1];
//        if (curDir == Direction.CENTER) curDir = rc.getLocation().directionTo(new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2));

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
            if (!rc.canMove(directions[i])) continue;
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
