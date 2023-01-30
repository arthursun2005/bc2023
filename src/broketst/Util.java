package broketst;

import battlecode.common.*;

public class Util {
    static RobotController rc;

    static int rDist(MapLocation a, MapLocation b) {
        return Math.max(Math.abs(b.x - a.x), Math.abs(b.y - a.y));
    }

    static int l1(MapLocation a, MapLocation b) {
        return Math.abs(b.x - a.x) + Math.abs(b.y - a.y);
    }

    static boolean writeCoords(int idx, MapLocation loc) throws GameActionException {
        int value = loc.x + loc.y * 69;
        if (rc.canWriteSharedArray(idx, value)) {
            rc.writeSharedArray(idx, value);
            return true;
        }
        return false;
    }

    static MapLocation readCoords(int idx) throws GameActionException {
        int value = rc.readSharedArray(idx);
        int x = value % 69;
        int y = value / 69;
        return new MapLocation(x, y);
    }

    static int log2(long bits) {
        return (int) (Math.log(bits) * 1.442695041);
    }
}
