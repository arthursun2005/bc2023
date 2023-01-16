package Mining0;

import battlecode.common.*;
import battlecode.world.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WellUtility {

    static RobotController rc;

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
    static Map<Integer, ResourceType> wellTypeMap = new HashMap<Integer, ResourceType>()
    {
        {
            put(0, ResourceType.ADAMANTIUM);
            put(1, ResourceType.MANA);
            put(2, ResourceType.ELIXIR);
        }
    };

    public WellUtility(RobotController rc) {
        this.rc = rc;
        return;
    }

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
}
