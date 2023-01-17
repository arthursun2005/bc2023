package kamikaze;

import battlecode.common.*;

public class Tracker
{
    RobotController rc;

    int hqCnt;
    MapLocation[] hqs = new MapLocation[5];

    long[] manaWells = new long[64];
    long[] adaWells = new long[64];
    long[] elixirWells = new long[64];

    public void readHQLocs() throws GameActionException {
        int cnt = rc.readSharedArray(Constants.SHARED_HQ + 5);
        hqCnt = cnt;
        for (int i = 0; i < cnt; i++) {
            hqs[i] = Util.readCoords(i);
        }
    }

    public MapLocation getClosestHQ() {
        MapLocation hq = null;
        MapLocation me = rc.getLocation();
        for (int i = 0; i < hqCnt; i++) {
            MapLocation loc = hqs[i];
            if (hq == null || loc.distanceSquaredTo(me) < hq.distanceSquaredTo(me)) {
                hq = loc;
            }
        }
        return hq;
    }
    
    public Tracker(RobotController rc) throws GameActionException {
        this.rc = rc;
    }

    void updateWells() throws GameActionException {
        WellInfo[] wells = rc.senseNearbyWells();
        for (WellInfo well : wells) {
            MapLocation loc = well.getMapLocation();
            if (well.getResourceType().equals(ResourceType.ADAMANTIUM))
                adaWells[loc.x] |= 1l << loc.y;
            if (well.getResourceType().equals(ResourceType.MANA))
                manaWells[loc.x] |= 1l << loc.y;
            if (well.getResourceType().equals(ResourceType.ELIXIR))
                elixirWells[loc.x] |= 1l << loc.y;
        }
    }

    void updateIslands() throws GameActionException {
    }

    void shareWells() throws GameActionException {
    }

    void collectWells() throws GameActionException {
    }

    void shareIslands() throws GameActionException {
    }

    void collectIslands() throws GameActionException {
    }

    void update() throws GameActionException {
        updateWells();
        updateIslands();
        
        shareWells();
        collectWells();

        shareIslands();
        collectIslands();
    }

    MapLocation getBestWell() throws GameActionException {
        int width = rc.getMapWidth();
        MapLocation best = null;
        int pq = 0;
        MapLocation me = rc.getLocation();
        for (int x = 0; x < width; x++) {
            long w = adaWells[x];
            while (w > 0) {
                long u = w & (w - 1);
                long k = w - u;
                int y = Util.log2(k);
                MapLocation loc = new MapLocation(x, y);
                int m = me.distanceSquaredTo(loc);
                if (rc.getType().equals(RobotType.CARRIER)) {
                    if (rc.getID() % 2 == 0) m += 13;
                    else m -= 13;
                }
                if (best == null || m < pq) { best = loc; pq = m; }
                w = u;
            }
        }
        for (int x = 0; x < width; x++) {
            long w = manaWells[x];
            while (w > 0) {
                long u = w & (w - 1);
                long k = w - u;
                int y = Util.log2(k);
                MapLocation loc = new MapLocation(x, y);
                int m = me.distanceSquaredTo(loc);
                if (best == null || m < pq) { best = loc; pq = m; }
                w = u;
            }
        }
        for (int x = 0; x < width; x++) {
            long w = elixirWells[x];
            while (w > 0) {
                long u = w & (w - 1);
                long k = w - u;
                int y = Util.log2(k);
                MapLocation loc = new MapLocation(x, y);
                int m = me.distanceSquaredTo(loc);
                if (best == null || m < pq) { best = loc; pq = m; }
                w = u;
            }
        }
        return best;
    }

    MapLocation getBestIsland() throws GameActionException {
        return null;
    }
}