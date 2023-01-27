package asianbanana;

import battlecode.common.*;

import java.util.*;

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

    long wellX = 0;

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
        int val = enemy.x * 69 + enemy.y + 1;
        for (int i = 0; i < 4; i++) {
            if (rc.readSharedArray(Constants.DISTRESS + i) != 0)
                continue;
            if (rc.readSharedArray(Constants.DISTRESS + i) == val)
                break;
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
        readWells();
        shareWells();
    }

    int wellIdx = 0;

    void readWells() throws GameActionException {
        for (; wellIdx < Constants.WELLS_CNT; wellIdx++) {
            int val = rc.readSharedArray(wellIdx + Constants.WELLS_START);
            if (val == 0)
                break;
            val--;
            int wellType = val / 5000;
            val -= wellType * 5000;
            int x = val / 69;
            int y = val - x * 69;
            wellX |= (1l << x);
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
                if (wellIdx == Constants.WELLS_CNT) {
                    wellIdx = 0;
                }
                int idx = wellIdx;
                wellIdx++;
                int val = x * 69 + y;
                if (((A >> y) & 1) != 0)
                    val += 5000;
                if (((B >> y) & 1) != 0)
                    val += 10000;
                rc.writeSharedArray(idx + Constants.WELLS_START, val + 1);
            }
            rA[x] = rB[x] = 0;
        }
    }

    void senseWells() throws GameActionException {
        WellInfo[] wells = rc.senseNearbyWells();
        for (WellInfo well : wells) {
            MapLocation loc = well.getMapLocation();
            ResourceType type = well.getResourceType();
            wellX |= (1l << loc.x);
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

    MapLocation getBestWell(MapLocation threat) throws GameActionException {
        // if (bestWell != null && rc.senseNearbyRobots(bestWell, 2,
        // rc.getTeam()).length >= 9)
        // bestWell = null;
        // if (bestWell != null)
        // return bestWell;
        int cnt = rc.getRobotCount();
        MapLocation best = null;
        int width = rc.getMapWidth();
        int height = rc.getMapHeight();
        long w;
        boolean ignoreAda = false;
        boolean elixirOnly = false;
        if (rc.getType().equals(RobotType.CARRIER)) {
            // if (rc.getRoundNum() <= 25 && width <= 25 && height <= 25
            // && rc.getID() % 3 != 0) {
            // ignoreAda = true;
            // }
            // if (rc.getRoundNum() <= 35 && width > 25 && height > 25) {
            // ignoreMana = true;
            // }
            // if (rc.getRoundNum() > 100 && rc.getID() % 2 != 0) {
            // ignoreAda = true;
            // }
            if (rc.getRoundNum() < 35 || cnt < 18) {
                ignoreAda = true;
            } else {
                if (rc.getID() % 3 != 0) {
                    ignoreAda = true;
                }
            }
            if (rc.getID() % 3 != 0)
                elixirOnly = true;
        }
        MapLocation me = rc.getLocation();
        long f;
        f = wellX;
        while (f > 0) {
            long h = f & -f;
            f -= h;
            int x = Util.log2(h);
            w = wellA[x] & wellB[x];
            while (w > 0) {
                long r = w & -w;
                w -= r;
                int y = Util.log2(r);
                MapLocation loc = new MapLocation(x, y);
                if (rc.canSenseLocation(loc) && rc.senseNearbyRobots(loc, 2, rc.getTeam()).length >= 9)
                    continue;
                if (threat != null && loc.distanceSquaredTo(threat) < 26)
                    continue;
                if (best == null || me.distanceSquaredTo(loc) < me.distanceSquaredTo(best))
                    best = loc;
            }
        }
        if (elixirOnly && best != null)
            return bestWell = best;
        f = wellX;
        while (f > 0) {
            long h = f & -f;
            f -= h;
            int x = Util.log2(h);
            w = wellB[x] & ~wellA[x];
            while (w > 0) {
                long r = w & -w;
                w -= r;
                int y = Util.log2(r);
                MapLocation loc = new MapLocation(x, y);
                if (rc.canSenseLocation(loc) && rc.senseNearbyRobots(loc, 2, rc.getTeam()).length >= 9)
                    continue;
                if (threat != null && loc.distanceSquaredTo(threat) < 26)
                    continue;
                if (best == null || me.distanceSquaredTo(loc) < me.distanceSquaredTo(best))
                    best = loc;
            }
        }
        if (ignoreAda && best != null)
            return bestWell = best;
        f = wellX;
        while (f > 0) {
            long h = f & -f;
            f -= h;
            int x = Util.log2(h);
            w = wellA[x] & ~wellB[x];
            while (w > 0) {
                long r = w & -w;
                w -= r;
                int y = Util.log2(r);
                MapLocation loc = new MapLocation(x, y);
                if (rc.canSenseLocation(loc) && rc.senseNearbyRobots(loc, 2, rc.getTeam()).length >= 9)
                    continue;
                if (threat != null && loc.distanceSquaredTo(threat) < 26)
                    continue;
                if (best == null || me.distanceSquaredTo(loc) < me.distanceSquaredTo(best))
                    best = loc;
            }
        }
        if (ignoreAda && rc.getID() % 2 == 0)
            return null;
        return bestWell = best;
    }

    MapLocation getBestAdaWell() throws GameActionException {
        MapLocation best = null;
        long w;
        int width = rc.getMapWidth();
        MapLocation me = rc.getLocation();
        long f;
        f = wellX;
        while (f > 0) {
            long h = f & -f;
            f -= h;
            int x = Util.log2(h);
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
        long f;
        f = wellX;
        while (f > 0) {
            long h = f & -f;
            f -= h;
            int x = Util.log2(h);
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
        boolean ignoreAda = false;
        if (rc.getRoundNum() <= 35) {
            ignoreAda = true;
        }
        MapLocation me = rc.getLocation();
        long f;
        f = wellX;
        while (f > 0) {
            long h = f & -f;
            f -= h;
            int x = Util.log2(h);
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
        if (best != null && ignoreAda)
            return best;
        f = wellX;
        while (f > 0) {
            long h = f & -f;
            f -= h;
            int x = Util.log2(h);
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

    // SYMMETRY
    final int POSSI = Constants.POSSI;

    void writepossi(int[] x) throws GameActionException {
        if (!rc.canWriteSharedArray(0, 0))
            return;
        rc.writeSharedArray(POSSI, (1 - x[1]) + 2 * (1 - x[2]) + 4 * (1 - x[3]));
    }

    int[] readpossi() throws GameActionException {
        int val = rc.readSharedArray(POSSI);
//        System.out.println(val);
        int vals[] = { 0, 1 - (val % 2), 1 - ((val / 2) % 2), 1 - (val / 4) };
        return vals;
    }

    long arr[] = new long[195]; // 2^63 - 1
    // 3 per location, 21 locations in one long, need 180

    void put(int loc, int val) {
        arr[loc / 19] |= ((long) val << ((loc % 19) * 3));
    }

    int read(int loc) {
        return (int) ((arr[loc / 19] >> ((loc % 19) * 3)) % 8);
    }

    int possi[] = { 0, 1, 1, 1 };

    boolean foundSymmetry = false;

    int symmetry = 0;

    int encode(int x, int y) {
        return x * 60 + y;
    }

    RobotInfo possibleHQ = null;
    MapInfo mapInfo = null;
    int nn, no, on, oo;

    boolean doneHQs = false;

    public void tryFindSymmetry() throws GameActionException {
        if (!doneHQs) {
            int x, y, oppx, oppy, val;
            for (MapLocation loc : HQLocs) {
                if (loc == null)
                    continue;
                x = loc.x;
                y = loc.y;
                oppx = rc.getMapWidth() - x - 1;
                oppy = rc.getMapHeight() - y - 1;

                if (read(encode(x, y)) != 0)
                    continue;

                put(encode(x, y), 5);

                nn = read(encode(x, y));
                on = read(encode(oppx, y));
                no = read(encode(x, oppy));
                oo = read(encode(oppx, oppy));

                if (on != 0 && on + nn != 11)
                    possi[1] = 0;
                if (no != 0 && no + nn != 11)
                    possi[2] = 0;
                if (oo != 0 && oo + nn != 11)
                    possi[3] = 0;
            }
            doneHQs = true;
        }
        if (!foundSymmetry) {
            possi = readpossi();

            MapLocation toCheck[] = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(), 18);
            int x, y, oppx, oppy, val;
            MapLocation loc = null;
            // 1 for passable, 2 for impassable
            if (Clock.getBytecodesLeft() >= 5500) {
                // System.out.println(Clock.getBytecodesLeft());
                for (int i = 0; i < toCheck.length; i++) {
                    loc = toCheck[i];

                    val = rc.getLocation().distanceSquaredTo(loc);
                    if (val > 8 || val <= 2)
                        continue;

                    x = loc.x;
                    y = loc.y;
                    oppx = rc.getMapWidth() - x - 1;
                    oppy = rc.getMapHeight() - y - 1;

                    if (read(encode(x, y)) != 0)
                        continue;

                    possibleHQ = rc.senseRobotAtLocation(loc); // 25
                    mapInfo = rc.senseMapInfo(loc); // 10

                    if (possibleHQ != null && possibleHQ.getType().equals(RobotType.HEADQUARTERS)) {
                        put(encode(x, y), (possibleHQ.getTeam() == rc.getTeam() ? 5 : 6));
                    } else if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) {
                        put(encode(x, y), 1);
                    } else if (mapInfo.hasCloud()) {
                        put(encode(x, y), 2);
                    } else if (rc.sensePassability(loc)) {
                        put(encode(x, y), 3);
                    } else {
                        put(encode(x, y), 4);
                    }

                    nn = read(encode(x, y));
                    on = read(encode(oppx, y));
                    no = read(encode(x, oppy));
                    oo = read(encode(oppx, oppy));

                    if (nn >= 5) {
                        if (on != 0 && on + nn != 11)
                            possi[1] = 0;
                        if (no != 0 && no + nn != 11)
                            possi[2] = 0;
                        if (oo != 0 && oo + nn != 11)
                            possi[3] = 0;
                    } else {
                        if (on != 0 && on != nn)
                            possi[1] = 0;
                        if (no != 0 && no != nn)
                            possi[2] = 0;
                        if (oo != 0 && oo != nn)
                            possi[3] = 0;
                    }
                }
            }
            while (Clock.getBytecodesLeft() >= 500) {
                // System.out.println(Clock.getBytecodesLeft());
                // :skull:
                loc = toCheck[robot.rng.nextInt(toCheck.length)];

                x = loc.x;
                y = loc.y;
                oppx = rc.getMapWidth() - x - 1;
                oppy = rc.getMapHeight() - y - 1;

                if (read(encode(x, y)) != 0)
                    continue;

                possibleHQ = rc.senseRobotAtLocation(loc); // 25
                mapInfo = rc.senseMapInfo(loc); // 10

                if (possibleHQ != null && possibleHQ.getType().equals(RobotType.HEADQUARTERS)) {
                    put(encode(x, y), (possibleHQ.getTeam() == rc.getTeam() ? 5 : 6));
                } else if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) {
                    put(encode(x, y), 1);
                } else if (mapInfo.hasCloud()) {
                    put(encode(x, y), 2);
                } else if (rc.sensePassability(loc)) {
                    put(encode(x, y), 3);
                } else {
                    put(encode(x, y), 4);
                }

                nn = read(encode(x, y));
                on = read(encode(oppx, y));
                no = read(encode(x, oppy));
                oo = read(encode(oppx, oppy));

                if (nn >= 5) {
                    if (on != 0 && on + nn != 11)
                        possi[1] = 0;
                    if (no != 0 && no + nn != 11)
                        possi[2] = 0;
                    if (oo != 0 && oo + nn != 11)
                        possi[3] = 0;
                } else {
                    if (on != 0 && on != nn)
                        possi[1] = 0;
                    if (no != 0 && no != nn)
                        possi[2] = 0;
                    if (oo != 0 && oo != nn)
                        possi[3] = 0;
                }
            }

            if (possi[1] + possi[2] + possi[3] == 1) {
                // rc.setIndicatorString("SYMMETRY FOUND" + (possi[1] == 1 ? 1 : (possi[2] == 1
                // ? 2 : 3)));
                symmetry = (possi[1] == 1 ? 1 : (possi[2] == 1 ? 2 : 3));
                foundSymmetry = true;
            }
        }
        writepossi(possi);
    }

    final int ENEMY_GROUP_RADIUS = 34; // How wide is a group
    final int ENEMY_GROUP_SEPARATION = 50; // How far apart groups are
    final int ENEMY_GROUP_THRESHOLD = 1; // Minimum enemies in a group

    ArrayList<MapLocation> getEnemyGroups() throws GameActionException {
        ArrayList<MapLocation> enemyCoords = new ArrayList<>();
        for (int i=Constants.ENEMY_GROUP_START; i<=Constants.ENEMY_GROUP_END; i++) {
            if (rc.readSharedArray(i)==0) continue;
            int coord = (rc.readSharedArray(i) - 1) % 5000;
            int coordX = coord / 69;
            int coordY = coord % 69;
            enemyCoords.add(new MapLocation(coordX, coordY));
        }
        return enemyCoords;
    }
    boolean addEnemyGroup() throws GameActionException {
        // Add a new enemy group sighting, return false if unsuccessful
        // Unsuccessful if array is full OR nearby group already
        MapLocation pos = rc.getLocation();
        ArrayList<MapLocation> enemyCoords = getEnemyGroups();
        for (MapLocation group : enemyCoords) {
            if (pos.isWithinDistanceSquared(group, ENEMY_GROUP_SEPARATION)) return false;
        }
        for (int i=Constants.ENEMY_GROUP_START; i<=Constants.ENEMY_GROUP_END; i++) {
            if (rc.readSharedArray(i) == 0) {
                rc.writeSharedArray(i, pos.x * 69 + pos.y + ((rc.getRoundNum()/3) % 13) * 5000 + 1);
                return true;
            }
        }
        rc.writeSharedArray(Constants.ENEMY_GROUP_START + robot.rng.nextInt(Constants.ENEMY_GROUP_END - Constants.ENEMY_GROUP_START + 1),
            pos.x * 69 + pos.y + ((rc.getRoundNum()/3) % 13) * 5000 + 1);
        return true;
    }

    boolean isEnemyGroup() throws GameActionException {
        RobotInfo[] enemies = rc.senseNearbyRobots(ENEMY_GROUP_RADIUS, rc.getTeam().opponent());
        return enemies.length >= ENEMY_GROUP_THRESHOLD;
    }

    void clearEnemyGroups() throws GameActionException {
        if (rc.getRoundNum()%3!=0) return;
        if (!rc.canWriteSharedArray(0, 0)) {
            return;
        }
        for (int i=Constants.ENEMY_GROUP_START; i<=Constants.ENEMY_GROUP_END; i++) {
            if (rc.readSharedArray(i) == 0) continue;
            if ((rc.readSharedArray(i) - 1) / 5000 == (rc.getRoundNum()/3) % 13) {
                int coord = (rc.readSharedArray(i) - 1) % 5000;
                int coordX = coord / 69;
                int coordY = coord % 69;
                //System.out.println("removed " + coordX + " " + coordY);
                rc.writeSharedArray(i, 0);
            }
        }
    }
}
