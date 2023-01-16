package dw;

import battlecode.common.*;

import java.util.*;

// HUGELY TODO

// manages positions of significant sites
// such as wells (basically just wells lol idk)

// manages:
// find the best well to go to (with a lot of computations and heuristics)
// if there are a really good well (for example, no friends has reached yet + far away from enemy)
// then: signal the position in the shared array

enum WellState
{
    ADA,
    MANA,
}

enum IslandState
{
    OCCUPIED,
    EMPTY,
}

public class Tracker
{
    static Random rng;

    static RobotController rc;
    static IslandState[][] islands = new IslandState[69][69];

    // WELL FINDING
    static ArrayList<WellInfo> seenWells = new ArrayList<>();
    static ArrayList<WellInfo> toAdd = new ArrayList<>();
    static int curWellSharedArray = 50;
    static boolean[][] isWell = new boolean[64][64];
    static ArrayList<CustomWell> wells = new ArrayList<>();
    static ArrayList<MapLocation> adaWells = new ArrayList<>();
    static ArrayList<MapLocation> manaWells = new ArrayList<>();
    static ArrayList<MapLocation> elixirWells = new ArrayList<>();

    // END WELL FINDING

    public Tracker(RobotController rc) {
        this.rc = rc;
        rng = new Random(rc.getID());
    }
    static Map<Integer, ResourceType> wellTypeMap = new HashMap<Integer, ResourceType>()
    {
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
    static void updateWells() throws GameActionException {
        while (curWellSharedArray < 64) {
            int wellEncode = rc.readSharedArray(curWellSharedArray)-1;
            if (wellEncode < 0) break;
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
            isWell[wellX][wellY] = true;
            curWellSharedArray++;
        }
        for (WellInfo newWell : seenWells) {
            MapLocation newWellPos = newWell.getMapLocation();
            ResourceType newWellType = newWell.getResourceType();
            int newWellTypeCode = 0;
            if (newWellType == ResourceType.MANA) newWellTypeCode = 1;
            if (newWellType == ResourceType.ELIXIR) newWellTypeCode = 2;
            if (!isWell[newWellPos.x][newWellPos.y] && curWellSharedArray < 64) {
                isWell[newWellPos.x][newWellPos.y] = true;
                wells.add(new CustomWell(newWell.getMapLocation(), newWell.getResourceType()));
                toAdd.add(newWell);
            }
        }
        seenWells.clear();

        if (!rc.canWriteSharedArray(0, 0)) {
            return;
        }
        for (WellInfo newWell : toAdd) {
            if (curWellSharedArray >= 64) return;
            MapLocation newWellPos = newWell.getMapLocation();
            ResourceType newWellType = newWell.getResourceType();
            int newWellTypeCode = 0;
            if (newWellType == ResourceType.MANA) newWellTypeCode = 1;
            if (newWellType == ResourceType.ELIXIR) newWellTypeCode = 2;
            rc.writeSharedArray(curWellSharedArray, (newWellPos.x*69+newWellPos.y)*3+newWellTypeCode+1);
            curWellSharedArray++;
        }
        toAdd.clear();
    }
    static MapLocation getClosestMine() {
        wells.sort(new Comparator<CustomWell>() {
            public int compare(CustomWell a, CustomWell b) {

                int deltaA = 0, deltaB = 0;
                return rc.getLocation().distanceSquaredTo(a.getMapLocation()) + deltaA - rc.getLocation().distanceSquaredTo(b.getMapLocation()) - deltaB;
            }
        });

        return wells.get(rng.nextInt(Math.min(wells.size(), 2))).getMapLocation();
    }
    static void senseWells() throws GameActionException {
        if (rc.getType() != RobotType.AMPLIFIER) {
            WellInfo[] nearbyWells = rc.senseNearbyWells();
            for (int j=0; j<nearbyWells.length; j++) {;
                WellInfo testing = nearbyWells[j];
                seenWells.add(testing);
            }

            updateWells();
        }
    }

//    static void updateWells(RobotController rc) throws GameActionException
//    {
//        Tracker.rc = rc;
//
//        WellInfo[] nearbyWells = rc.senseNearbyWells();
//
//        for (WellInfo wi : nearbyWells)
//        {
//            int x = wi.getMapLocation().x;
//            int y = wi.getMapLocation().y;
//            if (wi.getResourceType().equals(ResourceType.ADAMANTIUM))
//            {
//                wells[x][y] = WellState.ADA;
//            }
//            if (wi.getResourceType().equals(ResourceType.MANA))
//            {
//                wells[x][y] = WellState.MANA;
//            }
//        }
//    }

    static void updateIslands(RobotController rc) throws GameActionException
    {
        Tracker.rc = rc;

        int[] nis = rc.senseNearbyIslands();
        for (int id : nis) {
            boolean occupied = rc.senseTeamOccupyingIsland(id) == rc.getTeam();
            MapLocation[] thisIslandLocs = rc.senseNearbyIslandLocations(id);
            for (MapLocation nLoc : thisIslandLocs)
            {
                if (occupied)
                {
                    islands[nLoc.x][nLoc.y] = IslandState.OCCUPIED;
                }else{
                    islands[nLoc.x][nLoc.y] = IslandState.EMPTY;
                }
            }
        }
    }

    static MapLocation getOptimalIsland() throws GameActionException
    {
        int dist = -1;
        MapLocation target = null;
        int width = rc.getMapWidth();
        int height = rc.getMapHeight();
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                if (islands[x][y] == null || islands[x][y].equals(IslandState.OCCUPIED))
                {
                    continue;
                }
                MapLocation loc = new MapLocation(x, y);
                int w = rc.getLocation().distanceSquaredTo(loc);
                if (dist == -1 || w < dist)
                {
                    dist = w;
                    target = loc;
                }
            }
        }
        return target;
    }

    static MapLocation getOptimalWell() throws GameActionException
    {
        int dist = -1;
        MapLocation target = null;
        int width = rc.getMapWidth();
        int height = rc.getMapHeight();
        for (CustomWell well : wells) {
            MapLocation loc = well.getMapLocation();
            int w = rc.getLocation().distanceSquaredTo(loc);
            if (rc.senseNearbyRobots(loc, 2, rc.getTeam()).length >= 7)
            {
                continue;
            }
            if (rc.getRoundNum() > 80 && rc.getType().equals(RobotType.CARRIER) && rc.getID() % 3 != 0 && well.getResourceType() == ResourceType.ADAMANTIUM)
            {
                continue;
            }
            if (dist == -1 || w < dist)
            {
                dist = w;
                target = loc;
            }
        }

        return target;
    }

    static final int CARRIER = 31;
    static final int LAUNCHER = 32;

    static boolean calcHQ = false;
    static int hqCount = 0;
    static ArrayList<MapLocation> HQLocations = new ArrayList<>();

    static void increaseCarrierCount() throws GameActionException {
        if (!rc.canWriteSharedArray(0, 0)) return;

        int temp = rc.readSharedArray(CARRIER);
        rc.writeSharedArray(CARRIER, temp + 1);
    }

    static void increaseLauncherCount() throws GameActionException {
        if (!rc.canWriteSharedArray(0, 0)) return;

        int temp = rc.readSharedArray(LAUNCHER);
        rc.writeSharedArray(LAUNCHER, temp + 1);
    }

    static int readCarrierCount() throws GameActionException {
        return rc.readSharedArray(CARRIER);
    }
    static int readLauncherCount() throws GameActionException {
        return rc.readSharedArray(LAUNCHER);
    }

    static void updateCount(int resource, int delta) throws GameActionException {
        // 0 = ada, 1 = mana, 2 = elixir
        int cur = rc.readSharedArray(33+resource) + delta;
        if (rc.canWriteSharedArray(33+resource, cur)) {
            rc.writeSharedArray(33+resource, cur);
        }
    }

    static int getCount(int resource) throws GameActionException {
        return rc.readSharedArray(33+resource);
    }

    static void writeHQLoc() throws GameActionException {
        for (int i = 40; i < 50; i++) {
            if (rc.readSharedArray(i) != 0) continue;
            rc.writeSharedArray(i, rc.getLocation().x * 69 + rc.getLocation().y);
            break;
        }
    }

    static void readHQLoc() throws GameActionException {

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
    }

    static MapLocation getClosestHQLoc() {
        Collections.sort(HQLocations, new Comparator<MapLocation>() {
            public int compare(MapLocation a, MapLocation b) {
                return rc.getLocation().distanceSquaredTo(a) - rc.getLocation().distanceSquaredTo(b);
            }
        });

        return HQLocations.get(0);
    }
}
