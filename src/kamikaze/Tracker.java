package kamikaze;

import battlecode.common.*;

import java.util.*;

// HUGELY TODO

// manages positions of significant sites
// such as wells (basically just wells lol idk)

// manages:
// find the best well to go to (with a lot of computations and heuristics)
// if there are a really good well (for example, no friends has reached yet + far away from enemy)
// then: signal the position in the shared array

enum WellState {
    ADA,
    MANA,
}

public class Tracker {
    Random rng;
    RobotController rc;

    // WELL FINDING
    ArrayList<WellInfo> seenWells = new ArrayList<>();
    ArrayList<WellInfo> toAdd = new ArrayList<>();
    int curWellSharedArray = 50;
    long[] isWell = new long[64];
    ArrayList<CustomWell> wells = new ArrayList<>();
    ArrayList<MapLocation> adaWells = new ArrayList<>();
    ArrayList<MapLocation> manaWells = new ArrayList<>();
    ArrayList<MapLocation> elixirWells = new ArrayList<>();

    // END WELL FINDING
    // ISLAND FINDING
    long[] islands = new long[64];

    public Tracker(RobotController rc) {
        this.rc = rc;
        rng = new Random(rc.getID() + 13);
    }

    Map<Integer, ResourceType> wellTypeMap = new HashMap<Integer, ResourceType>() {
        {
            put(0, ResourceType.ADAMANTIUM);
            put(1, ResourceType.MANA);
            put(2, ResourceType.ELIXIR);
        }
    };

    MapLocation getWellLocation(int wellEncode) {
        int wellPos = wellEncode / 3;
        int wellType = wellEncode % 3;
        int wellX = wellPos / 69;
        int wellY = wellPos % 69;

        return new MapLocation(wellX, wellY);
    }

    int getWellType(int wellEncode) {
        int wellPos = wellEncode / 3;
        int wellType = wellEncode % 3;
        int wellX = wellPos / 69;
        int wellY = wellPos % 69;

        return wellType;
    }

    void update() throws GameActionException {
        senseWells();
        senseIslands();
    }

    void updateWells() throws GameActionException {
        while (curWellSharedArray < 64) {
            int wellEncode = rc.readSharedArray(curWellSharedArray) - 1;
            if (wellEncode < 0)
                break;
            int wellPos = wellEncode / 3;
            int wellType = wellEncode % 3;
            int wellX = wellPos / 69;
            int wellY = wellPos % 69;
            wells.add(new CustomWell(new MapLocation(wellX, wellY), wellTypeMap.get(wellType)));
            if (wellType == 0) {
                adaWells.add(new MapLocation(wellX, wellY));
            }
            if (wellType == 1) {
                manaWells.add(new MapLocation(wellX, wellY));
            }
            if (wellType == 2) {
                elixirWells.add(new MapLocation(wellX, wellY));
            }
            isWell[wellX] |= (1l << wellY);
            curWellSharedArray++;
        }
        for (WellInfo newWell : seenWells) {
            MapLocation newWellPos = newWell.getMapLocation();
            ResourceType newWellType = newWell.getResourceType();
            if (((isWell[newWellPos.x] >> newWellPos.y) & 1) != 1 && curWellSharedArray < 64) {
                isWell[newWellPos.x] |= (1l << newWellPos.y);
                wells.add(new CustomWell(newWell.getMapLocation(), newWell.getResourceType()));
                toAdd.add(newWell);
            }
        }
        seenWells.clear();

        if (!rc.canWriteSharedArray(0, 0)) {
            return;
        }
        for (WellInfo newWell : toAdd) {
            if (curWellSharedArray >= 64)
                return;
            MapLocation newWellPos = newWell.getMapLocation();
            ResourceType newWellType = newWell.getResourceType();
            int newWellTypeCode = 0;
            if (newWellType == ResourceType.MANA)
                newWellTypeCode = 1;
            if (newWellType == ResourceType.ELIXIR)
                newWellTypeCode = 2;
            rc.writeSharedArray(curWellSharedArray, (newWellPos.x * 69 + newWellPos.y) * 3 + newWellTypeCode + 1);
            curWellSharedArray++;
        }
        toAdd.clear();
    }

    WellInfo[] nearbyWells;

    void senseWells() throws GameActionException {
        if (rc.getType() != RobotType.AMPLIFIER) {
            nearbyWells = rc.senseNearbyWells();
            for (int j = 0; j < nearbyWells.length; j++) {
                ;
                WellInfo testing = nearbyWells[j];
                seenWells.add(testing);
            }

            updateWells();
        }
    }

    MapLocation getBestWell() throws GameActionException {
        int dist = 1000000;
        MapLocation target = null;
        int width = rc.getMapWidth();
        int height = rc.getMapHeight();
        for (CustomWell well : wells) {
            MapLocation loc = well.getMapLocation();
            int w = rc.getLocation().distanceSquaredTo(loc);
            // if (rc.senseNearbyRobots(loc, 2, rc.getTeam()).length >= 8) {
            // continue;
            // }
            if (rc.getType().equals(RobotType.CARRIER)) {
                if (rc.getRoundNum() <= 20 && width <= 25 && height <= 25
                        && well.getResourceType() == ResourceType.ADAMANTIUM
                        && rc.getID() % 3 != 0) {
                    continue;
                }
                if (rc.getRoundNum() <= 20 && width > 25 && height > 25
                        && well.getResourceType() == ResourceType.MANA) {
                    continue;
                }
                if (rc.getRoundNum() > 20 && rc.getID() % 3 != 0
                        && well.getResourceType() == ResourceType.ADAMANTIUM) {
                    continue;
                }
            }
            if (w < dist) {
                dist = w;
                target = loc;
            }
        }
        return target;
    }

    final int CARRIER = 31;
    final int LAUNCHER = 32;

    int hqCount = 0;
    ArrayList<MapLocation> HQLocations = new ArrayList<>();

    MapLocation getRandomWell() {
        int dist = 1000000;
        MapLocation target = null;

        for (CustomWell well : wells) {
            MapLocation loc = well.getMapLocation();
            int w = rc.getLocation().distanceSquaredTo(loc);
            if (rc.getRoundNum() <= 20 && well.getResourceType() == ResourceType.MANA) {
                continue;
            }
            if (w < dist) {
                dist = w;
                target = loc;
            }
        }

        return target;
        // wells.sort(new Comparator<CustomWell>() {
        // public int compare(CustomWell a, CustomWell b) {

        // int deltaA = 0, deltaB = 0;
        // return rc.getLocation().distanceSquaredTo(a.getMapLocation()) + deltaA
        // - rc.getLocation().distanceSquaredTo(b.getMapLocation()) - deltaB;
        // }
        // });

        // if (wells.size() == 0)
        // return rc.getLocation();

        // return wells.get(rng.nextInt(Math.min(wells.size(), 2))).getMapLocation();
    }

    void increaseCarrierCount() throws GameActionException {
        if (!rc.canWriteSharedArray(0, 0))
            return;

        int temp = rc.readSharedArray(CARRIER);
        rc.writeSharedArray(CARRIER, temp + 1);
    }

    void increaseLauncherCount() throws GameActionException {
        if (!rc.canWriteSharedArray(0, 0))
            return;

        int temp = rc.readSharedArray(LAUNCHER);
        rc.writeSharedArray(LAUNCHER, temp + 1);
    }

    int readCarrierCount() throws GameActionException {
        return rc.readSharedArray(CARRIER);
    }

    int readLauncherCount() throws GameActionException {
        return rc.readSharedArray(LAUNCHER);
    }

    void updateCount(int resource, int delta) throws GameActionException {
        // 0 = ada, 1 = mana, 2 = elixir
        int cur = rc.readSharedArray(33 + resource) + delta;
        if (rc.canWriteSharedArray(33 + resource, cur)) {
            rc.writeSharedArray(33 + resource, cur);
        }
    }

    int getCount(int resource) throws GameActionException {
        return rc.readSharedArray(33 + resource);
    }

    void readHQLocs() throws GameActionException {
        int cnt = rc.readSharedArray(Constants.SHARED_HQ + 5);
        for (int i = 0; i < cnt; i++) {
            HQLocations.add(Util.readCoords(i));
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
        Collections.sort(HQLocations, new Comparator<MapLocation>() {
            public int compare(MapLocation a, MapLocation b) {
                return rc.getLocation().distanceSquaredTo(a) - rc.getLocation().distanceSquaredTo(b);
            }
        });

        return HQLocations.get(0);
    }
}
