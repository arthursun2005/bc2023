package buggy;

import battlecode.common.*;

// HUGELY TODO

// manages positions of significant sites
// such as wells (basically just wells lol idk)

// manages:
// find the best well to go to (with a lot of computations and heuristics)
// if there are a really good well (for example, no friends has reached yet + far away from enemy)
// then: signal the position in the shared array

public class Tracker {
    RobotController rc;
    Robot robot;

    long[] wellA = new long[64];
    long[] wellB = new long[64];
    long[] rA = new long[64];
    long[] rB = new long[64];
    long[] islands = new long[64];

    public Tracker(RobotController rc, Robot robot) {
        this.rc = rc;
        this.robot = robot;
    }

    void clearDistress() throws GameActionException {
        if (!rc.canWriteSharedArray(0, 0))
            return;
        for (int i = 0; i < 4; i++) {
            rc.writeSharedArray(Constants.DISTRESS + i, 0);
        }
    }

    void signalDistress(MapLocation enemy) throws GameActionException {
        if (!rc.canWriteSharedArray(0, 0))
            return;
        for (int i = 0; i < 4; i++) {
            if (rc.readSharedArray(Constants.DISTRESS + i) != 0)
                continue;
            int val = enemy.x * 69 + enemy.y + 1;
            rc.writeSharedArray(Constants.DISTRESS + i, val);
            break;
        }
    }

    MapLocation pls() throws GameActionException {
        MapLocation best = null;
        for (int i = 0; i < 4; i++) {
            int val = rc.readSharedArray(Constants.DISTRESS + i);
            if (val == 0)
                continue;
            val--;
            MapLocation loc = new MapLocation(val / 69, val % 69);
            if (best == null || loc.distanceSquaredTo(rc.getLocation()) < best.distanceSquaredTo(rc.getLocation()))
                best = loc;
        }
        return best;
    }

    void update() throws GameActionException {
        senseWells();
        senseIslands();
        if (!rc.getType().equals(RobotType.LAUNCHER) &&
                !rc.getType().equals(RobotType.DESTABILIZER))
            readWells();
        shareWells();
    }

    int wellIdx = 0;

    void readWells() throws GameActionException {
        for (int i = 0; i < Constants.WELLS_CNT; i++) {
            int val = rc.readSharedArray(i + Constants.WELLS_START);
            if (val == 0)
                break;
            val--;
            int wellType = val / 5000;
            val -= wellType * 5000;
            int x = val / 69;
            int y = val - x * 69;
            switch (wellType) {
                case 1:
                    wellA[x] |= (1l << y);
                    rA[x] &= ~(1l << y);
                    break;
                case 2:
                    wellB[x] |= (1l << y);
                    rB[x] &= ~(1l << y);
                    break;
                case 3:
                    wellA[x] |= (1l << y);
                    wellB[x] |= (1l << y);
                    rA[x] &= ~(1l << y);
                    rB[x] &= ~(1l << y);
                    break;
            }
        }
    }

    void shareWells() throws GameActionException {
        while (wellIdx < Constants.WELLS_CNT && rc.readSharedArray(wellIdx + Constants.WELLS_START) != 0)
            wellIdx++;

        if (!rc.canWriteSharedArray(0, 0))
            return;

        int width = rc.getMapWidth();
        int offset = robot.rng.nextInt(width);
        long w;
        for (int fx = 0; fx < width; fx++) {
            int x = (fx + offset) % width;
            w = rA[x] | rB[x];
            long A = rA[x];
            long B = rB[x];
            while (w > 0) {
                long r = w & -w;
                w -= r;
                int y = Util.log2(r);
                int idx = wellIdx;
                int val = x * 69 + y;
                if (((A >> y) & 1) != 0)
                    val += 5000;
                if (((B >> y) & 1) != 0)
                    val += 10000;
                if (idx == Constants.WELLS_CNT) {
                    idx = robot.rng.nextInt(Constants.WELLS_CNT);
                } else {
                    wellIdx++;
                }
                rc.writeSharedArray(idx + Constants.WELLS_START, val + 1);
            }
        }
    }

    void senseWells() throws GameActionException {
        WellInfo[] wells = rc.senseNearbyWells();
        for (WellInfo well : wells) {
            MapLocation loc = well.getMapLocation();
            ResourceType type = well.getResourceType();
            if ((type == ResourceType.ADAMANTIUM || type == ResourceType.ELIXIR)
                    && ((wellA[loc.x] >> loc.y) & 1) == 0) {
                wellA[loc.x] |= (1l << loc.y);
                rA[loc.x] |= (1l << loc.y);
            }
            if ((type == ResourceType.MANA || type == ResourceType.ELIXIR) && ((wellB[loc.x] >> loc.y) & 1) == 0) {
                wellB[loc.x] |= (1l << loc.y);
                rB[loc.x] |= (1l << loc.y);
            }
        }
    }

    MapLocation bestWell = null;

    MapLocation getBestWell() throws GameActionException {
        // if (bestWell != null && rc.senseNearbyRobots(bestWell, 2, rc.getTeam()).length >= 9)
        //     bestWell = null;
        // if (bestWell != null)
        //     return bestWell;
        MapLocation best = null;
        int width = rc.getMapWidth();
        int height = rc.getMapHeight();
        long w;
        boolean ignoreAda = false, ignoreMana = false;
        boolean elixirOnly = false;
        if (rc.getType().equals(RobotType.CARRIER)) {
            if (rc.getRoundNum() <= 25 && width <= 25 && height <= 25
                    && rc.getID() % 3 != 0) {
                ignoreAda = true;
            }
            if (rc.getRoundNum() <= 35 && width > 25 && height > 25) {
                ignoreMana = true;
            }
            if (rc.getRoundNum() > 100 && rc.getID() % 2 != 0) {
                ignoreAda = true;
            }
            if (rc.getID() % 3 != 0)
                elixirOnly = true;
        }
        MapLocation me = rc.getLocation();
        for (int x = 0; x < width; x++) {
            w = wellA[x] & wellB[x];
            while (w > 0) {
                long r = w & -w;
                w -= r;
                int y = Util.log2(r);
                MapLocation loc = new MapLocation(x, y);
                if (rc.canSenseLocation(loc) && rc.senseNearbyRobots(loc, 2, rc.getTeam()).length >= 9)
                    continue;
                if (best == null || me.distanceSquaredTo(loc) < me.distanceSquaredTo(best))
                    best = loc;
            }
        }
        if (elixirOnly && best != null)
            return bestWell = best;
        for (int x = 0; x < width; x++) {
            w = wellA[x] & ~wellB[x];
            if (!ignoreAda) {
                while (w > 0) {
                    long r = w & -w;
                    w -= r;
                    int y = Util.log2(r);
                    MapLocation loc = new MapLocation(x, y);
                    if (best == null || me.distanceSquaredTo(loc) < me.distanceSquaredTo(best))
                        best = loc;
                }
            }
            w = wellB[x] & ~wellA[x];
            if (!ignoreMana) {
                while (w > 0) {
                    long r = w & -w;
                    w -= r;
                    int y = Util.log2(r);
                    MapLocation loc = new MapLocation(x, y);
                    if (best == null || me.distanceSquaredTo(loc) < me.distanceSquaredTo(best))
                        best = loc;
                }
            }
        }
        return bestWell = best;
    }

    MapLocation getBestAdaWell() throws GameActionException {
        MapLocation best = null;
        long w;
        int width = rc.getMapWidth();
        MapLocation me = rc.getLocation();
        for (int x = 0; x < width; x++) {
            w = wellA[x] & ~wellB[x];
            while (w > 0) {
                long r = w & -w;
                w -= r;
                int y = Util.log2(r);
                MapLocation loc = new MapLocation(x, y);
                if (best == null || me.distanceSquaredTo(loc) < me.distanceSquaredTo(best))
                    best = loc;
            }
        }
        return best;
    }

    MapLocation getBestManaWell() throws GameActionException {
        MapLocation best = null;
        long w;
        int width = rc.getMapWidth();
        MapLocation me = rc.getLocation();
        for (int x = 0; x < width; x++) {
            w = wellB[x] & ~wellA[x];
            while (w > 0) {
                long r = w & -w;
                w -= r;
                int y = Util.log2(r);
                MapLocation loc = new MapLocation(x, y);
                if (best == null || me.distanceSquaredTo(loc) < me.distanceSquaredTo(best))
                    best = loc;
            }
        }
        return best;
    }

    int HQCount = 0;
    MapLocation HQLocs[] = new MapLocation[5];

    MapLocation getRandomWell() {
        MapLocation best = null;
        int width = rc.getMapWidth();
        int height = rc.getMapHeight();
        long w;
        boolean ignoreMana = false;
        if (rc.getRoundNum() <= 20) {
            ignoreMana = true;
        }
        MapLocation me = rc.getLocation();
        for (int x = 0; x < width; x++) {
            w = wellA[x];
            while (w > 0) {
                long r = w & -w;
                w -= r;
                int y = Util.log2(r);
                MapLocation loc = new MapLocation(x, y);
                if (best == null || me.distanceSquaredTo(loc) < me.distanceSquaredTo(best))
                    best = loc;
            }
        }
        if (best != null && ignoreMana)
            return best;
        for (int x = 0; x < width; x++) {
            w = wellB[x];
            while (w > 0) {
                long r = w & -w;
                w -= r;
                int y = Util.log2(r);
                MapLocation loc = new MapLocation(x, y);
                if (best == null || me.distanceSquaredTo(loc) < me.distanceSquaredTo(best))
                    best = loc;
            }
        }
        return best;
    }

    void readHQLocs() throws GameActionException {
        int cnt = rc.readSharedArray(Constants.SHARED_HQ + 5);
        HQCount = cnt;
        for (int i = 0; i < cnt; i++) {
            HQLocs[i] = Util.readCoords(i);
        }
    }

    void senseIslands() throws GameActionException {
        int[] nids = rc.senseNearbyIslands();
        for (int id : nids) {
            MapLocation[] thisIslandLocs = rc.senseNearbyIslandLocations(id);
            boolean alr = rc.senseTeamOccupyingIsland(id) == rc.getTeam();
            for (MapLocation nLoc : thisIslandLocs) {
                if (alr) {
                    islands[nLoc.x] &= ~(1l << nLoc.y);
                } else {
                    islands[nLoc.x] |= (1l << nLoc.y);
                }
            }
        }
    }

    MapLocation getBestIsland() throws GameActionException {
        MapLocation best = null;
        int width = rc.getMapWidth();
        MapLocation me = rc.getLocation();
        for (int x = 0; x < width; x++) {
            long w = islands[x];
            while (w > 0) {
                long r = w & -w;
                w -= r;
                int y = Util.log2(r);
                MapLocation loc = new MapLocation(x, y);
                if (best == null || me.distanceSquaredTo(loc) < me.distanceSquaredTo(best))
                    best = loc;
            }
        }
        return best;
    }

    MapLocation getClosestHQLoc() {
        MapLocation best = HQLocs[0];
        for (int i = 1; i < HQCount; i++) {
            MapLocation loc = HQLocs[i];
            if (rc.getLocation().distanceSquaredTo(loc) < rc.getLocation().distanceSquaredTo(best))
                best = loc;
        }
        return best;
    }
}
