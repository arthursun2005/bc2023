package sylveontest;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

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

    static int log2(long bits)
    {
        int log = 0;
        if((bits & 0xffffffff00000000l) != 0) {bits >>>= 32; log = 32;}
        if((bits & 0xffff0000) != 0) {bits >>>= 16; log += 16;}
        if((bits & 0xff00) != 0) {bits >>>= 8; log += 8;}
        if((bits & 0xf0) != 0) {bits >>>= 4; log += 4;}
        if(bits >= 4) {bits >>>= 2; log += 2;}
        return log + (int) (bits >>> 1);
    }
}