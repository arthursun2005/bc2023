package sapphire;

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
    long[] islands = new long[64];

    public Tracker(RobotController rc, Robot robot) {
        this.rc = rc;
        this.robot = robot;
    }

    void update() throws GameActionException {
        senseWells();
        senseIslands();
    }

    void senseWells() throws GameActionException {
        WellInfo[] wells = rc.senseNearbyWells();
        for (WellInfo well : wells) {
            MapLocation loc = well.getMapLocation();
            ResourceType type = well.getResourceType();
            if (type == ResourceType.ADAMANTIUM || type == ResourceType.ELIXIR) {
                wellA[loc.x] |= (1l << loc.y);
            }
            if (type == ResourceType.MANA || type == ResourceType.ELIXIR) {
                wellB[loc.x] |= (1l << loc.y);
            }
        }
    }

    MapLocation getBestWell() throws GameActionException {
        MapLocation best = null;
        int width = rc.getMapWidth();
        int height = rc.getMapHeight();
        long w;
        boolean ignoreAda = false, ignoreMana = false;
        if (rc.getType().equals(RobotType.CARRIER)) {
            if (rc.getRoundNum() <= 25 && width <= 25 && height <= 25
                    && rc.getID() % 3 != 0) {
                ignoreAda = true;
            }
            if (rc.getRoundNum() <= 25 && width > 25 && height > 25) {
                ignoreMana = true;
            }
            if (rc.getRoundNum() > 75 && rc.getID() % 3 != 0) {
                ignoreAda = true;
            }
        }
        MapLocation me = rc.getLocation();
        for (int x = 0; x < width; x++) {
            w = wellA[x] & ~wellB[x];
            if (!ignoreAda) {
                while (w > 0) {
                    long k = w & (w - 1);
                    long r = w - k;
                    int y = Util.log2(r);
                    MapLocation loc = new MapLocation(x, y);
                    if (best == null || me.distanceSquaredTo(loc) < me.distanceSquaredTo(best))
                        best = loc;
                    w = k;
                }
            }
            w = wellB[x] & ~wellA[x];
            if (!ignoreMana) {
                while (w > 0) {
                    long k = w & (w - 1);
                    long r = w - k;
                    int y = Util.log2(r);
                    MapLocation loc = new MapLocation(x, y);
                    if (best == null || me.distanceSquaredTo(loc) < me.distanceSquaredTo(best))
                        best = loc;
                    w = k;
                }
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
        boolean ignoreAda = false, ignoreMana = false;
        if (rc.getRoundNum() <= 35) {
            ignoreMana = true;
        }
        MapLocation me = rc.getLocation();
        for (int x = 0; x < width; x++) {
            w = wellA[x] & ~wellB[x];
            if (!ignoreAda) {
                while (w > 0) {
                    long k = w & (w - 1);
                    long r = w - k;
                    int y = Util.log2(r);
                    MapLocation loc = new MapLocation(x, y);
                    if (best == null || me.distanceSquaredTo(loc) < me.distanceSquaredTo(best))
                        best = loc;
                    w = k;
                }
            }
            w = wellB[x] & ~wellA[x];
            if (!ignoreMana) {
                while (w > 0) {
                    long k = w & (w - 1);
                    long r = w - k;
                    int y = Util.log2(r);
                    MapLocation loc = new MapLocation(x, y);
                    if (best == null || me.distanceSquaredTo(loc) < me.distanceSquaredTo(best))
                        best = loc;
                    w = k;
                }
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
                long k = w & (w - 1);
                long r = w - k;
                int y = Util.log2(r);
                MapLocation loc = new MapLocation(x, y);
                if (best == null || me.distanceSquaredTo(loc) < me.distanceSquaredTo(best))
                    best = loc;
                w = k;
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
