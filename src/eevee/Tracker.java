package eevee;

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
    Random rng;
    RobotController rc;

    // WELL FINDING
    ArrayList<WellInfo> seenWells = new ArrayList<>();
    ArrayList<WellInfo> toAdd = new ArrayList<>();
    int curWellSharedArray = Constants.START_WELL_IDX;
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
                if (rc.getRoundNum() <= 25 && width <= 25 && height <= 25
                && well.getResourceType() == ResourceType.ADAMANTIUM
                && rc.getID() % 3 != 0) {
                    continue;
                }
                if (rc.getRoundNum() <= 25 && width > 25 && height > 25
                && well.getResourceType() == ResourceType.MANA) {
                    continue;
                }
                if (rc.getRoundNum() > 95 && rc.getID() % 3 != 0
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

    int hqCount = 0;
    ArrayList<MapLocation> HQLocations = new ArrayList<>();

    MapLocation getRandomWell() {
        int dist = 1000000;
        MapLocation target = null;

        for (CustomWell well : wells) {
            MapLocation loc = well.getMapLocation();
            int w = rc.getLocation().distanceSquaredTo(loc);
            if (rc.getRoundNum() <= 35 && well.getResourceType() == ResourceType.MANA) {
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

        int temp = rc.readSharedArray(Constants.BUILD_CARRIER_IDX);
        rc.writeSharedArray(Constants.BUILD_CARRIER_IDX, temp + 1);
    }

    void increaseLauncherCount() throws GameActionException {
        if (!rc.canWriteSharedArray(0, 0))
        return;

        int temp = rc.readSharedArray(Constants.BUILD_LAUNCHER_IDX);
        rc.writeSharedArray(Constants.BUILD_LAUNCHER_IDX, temp + 1);
    }

    int readCarrierCount() throws GameActionException {
        return rc.readSharedArray(Constants.BUILD_CARRIER_IDX);
    }

    int readLauncherCount() throws GameActionException {
        return rc.readSharedArray(Constants.BUILD_LAUNCHER_IDX);
    }

    void updateCount(int resource, int delta) throws GameActionException {
        // 0 = ada, 1 = mana, 2 = elixir
        int cur = rc.readSharedArray(Constants.RESOURCE_IDX + resource) + delta;
        if (rc.canWriteSharedArray(Constants.RESOURCE_IDX + resource, cur)) {
            rc.writeSharedArray(Constants.RESOURCE_IDX + resource, cur);
        }
    }

    int getCount(int resource) throws GameActionException {
        return rc.readSharedArray(Constants.RESOURCE_IDX + resource);
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


    //SYMMETRY

    void writepossi(int[] x) throws GameActionException {
        if (!rc.canWriteSharedArray(0, 0)) return;
        rc.writeSharedArray(Constants.POSSI, (1-x[1]) + 2*(1-x[2]) + 4*(1-x[3]));
    }

    int[] readpossi() throws GameActionException {
        int val=rc.readSharedArray(Constants.POSSI);
        int vals[]={0,1-val%2,1-(val/2)%2,1-val/4};
        return vals;
    }

    long arr[] = new long[195]; // 2^63 - 1
    // 3 per location, 21 locations in one long, need 180

    void put(int loc, int val) {
        arr[loc / 19] |= ((long) val << ((loc % 19) * 3));
    }

    int read(int loc) {
        return (int) ((arr[loc / 19] >> ((loc % 19)*3))%8);
    }
    int possi[] = {0,1,1,1};

    boolean foundSymmetry = false;

    int symmetry = 0;

    int encode(int x, int y) {
        return x * 60 + y;
    }

    RobotInfo possibleHQ = null;
    MapInfo mapInfo = null;
    int nn,no,on,oo;

    boolean doneHQs = false;

    public void checkHQs() throws GameActionException {
        if (!doneHQs) {
            int x, y, oppx, oppy, val;
            for (MapLocation loc: HQLocations) {
                x = loc.x; y = loc.y;
                oppx = rc.getMapWidth()-x-1; oppy = rc.getMapHeight()-y-1;

                if (read(encode(x,y))!=0) continue;

                put(encode(x, y), 5);

                nn=read(encode(x, y));
                on=read(encode(oppx, y));
                no=read(encode(x, oppy));
                oo=read(encode(oppx, oppy));

                if (on != 0 && on+nn != 11) possi[1] = 0;
                if (no != 0 && no+nn != 11) possi[2] = 0;
                if (oo != 0 && oo+nn != 11) possi[3] = 0;
            }
            doneHQs = true;
        }
    }

    public void tryFindSymmetry() throws GameActionException {
        checkHQs();
        if (!foundSymmetry) {
            readpossi();

            MapLocation toCheck[] = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(),18);
            int x, y, oppx, oppy, val;
            MapLocation loc = null;
            // 1 for passable, 2 for impassable
            if (Clock.getBytecodesLeft() >= 5500) {
                //System.out.println(Clock.getBytecodesLeft());
                for (int i = 0; i < toCheck.length; i++) {
                    loc = toCheck[i];

                    val = rc.getLocation().distanceSquaredTo(loc);
                    if (val > 8 || val <= 2) continue;

                    x = loc.x; y = loc.y;
                    oppx = rc.getMapWidth()-x-1; oppy = rc.getMapHeight()-y-1;

                    if (read(encode(x,y))!=0) continue;

                    possibleHQ = rc.senseRobotAtLocation(loc); // 25
                    mapInfo = rc.senseMapInfo(loc); // 10

                    if (possibleHQ != null && possibleHQ.getType().equals(RobotType.HEADQUARTERS)) {
                        put(encode(x, y), 6);
                    } else if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) {
                        put(encode(x, y), 1);
                    } else if (mapInfo.hasCloud()) {
                        put(encode(x, y), 2);
                    } else if (rc.sensePassability(loc)) {
                        put(encode(x, y), 3);
                    } else {
                        put(encode(x, y), 4);
                    }

                    nn=read(encode(x, y));
                    on=read(encode(oppx, y));
                    no=read(encode(x, oppy));
                    oo=read(encode(oppx, oppy));

                    if (nn == 6) {
                        if (on != 5) possi[1] = 0;
                        if (no != 5) possi[2] = 0;
                        if (oo != 5) possi[3] = 0;
                    }
                    else {
                        if (on != 0 && on != nn) possi[1] = 0;
                        if (no != 0 && no != nn) possi[2] = 0;
                        if (oo != 0 && oo != nn) possi[3] = 0;
                    }
                }
            }
            while (Clock.getBytecodesLeft() >= 500) {
                //System.out.println(Clock.getBytecodesLeft());
                //:skull:
                loc = toCheck[rng.nextInt(toCheck.length)];

                x = loc.x; y = loc.y;
                oppx = rc.getMapWidth()-x-1; oppy = rc.getMapHeight()-y-1;

                if (read(encode(x,y))!=0) continue;

                possibleHQ = rc.senseRobotAtLocation(loc); // 25
                mapInfo = rc.senseMapInfo(loc); // 10

                if (possibleHQ != null && possibleHQ.getType().equals(RobotType.HEADQUARTERS)) {
                    put(encode(x, y), 6);
                } else if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) {
                    put(encode(x, y), 1);
                } else if (mapInfo.hasCloud()) {
                    put(encode(x, y), 2);
                } else if (rc.sensePassability(loc)) {
                    put(encode(x, y), 3);
                } else {
                    put(encode(x, y), 4);
                }

                nn=read(encode(x, y));
                on=read(encode(oppx, y));
                no=read(encode(x, oppy));
                oo=read(encode(oppx, oppy));

                if (nn == 6) {
                    if (on != 5) possi[1] = 0;
                    if (no != 5) possi[2] = 0;
                    if (oo != 5) possi[3] = 0;
                }
                else {
                    if (on != 0 && on != nn) possi[1] = 0;
                    if (no != 0 && no != nn) possi[2] = 0;
                    if (oo != 0 && oo != nn) possi[3] = 0;
                }
            }

            if (possi[1]+possi[2]+possi[3]==1) {
                //rc.setIndicatorString("SYMMETRY FOUND" + (possi[1] == 1 ? 1 : (possi[2] == 1 ? 2 : 3)));
                symmetry = (possi[1] == 1 ? 1 : (possi[2] == 1 ? 2 : 3));
                foundSymmetry = true;
            }
        }
        writepossi(possi);
    }
}
