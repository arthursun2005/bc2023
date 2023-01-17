package kamikaze;

import battlecode.common.*;

public class Util {
    static RobotController rc;

    static boolean writeCoords(int idx, MapLocation loc) throws GameActionException
    {
        int value = loc.x + loc.y * 69;
        if (rc.canWriteSharedArray(idx, value))
        {
            rc.writeSharedArray(idx, value);
            return true;
        }
        return false;
    }

    static MapLocation readCoords(int idx) throws GameActionException
    {
        int value = rc.readSharedArray(idx);
        int x = value % 69;
        int y = value / 69;
        return new MapLocation(x, y);
    }
}
