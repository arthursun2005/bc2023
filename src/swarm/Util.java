package swarm;

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
        // ahhhhhhhhhhhhhh why is naive better
        return (int) (Math.log(bits) * 1.442695041);
        // int x;
        // int log = 0;
        // if (bits > 0xffffffffl) {
        // log = 32;
        // x = (int) (bits >>> 32);
        // } else {
        // x = (int) bits;
        // }
        // switch (x) {
        // case 1:
        // return log + 0;

        // case 2:
        // return log + 1;

        // case 4:
        // return log + 2;

        // case 8:
        // return log + 3;

        // case 16:
        // return log + 4;

        // case 32:
        // return log + 5;

        // case 64:
        // return log + 6;

        // case 128:
        // return log + 7;

        // case 256:
        // return log + 8;

        // case 512:
        // return log + 9;

        // case 1024:
        // return log + 10;

        // case 2048:
        // return log + 11;

        // case 4096:
        // return log + 12;

        // case 8192:
        // return log + 13;

        // case 16384:
        // return log + 14;

        // case 32768:
        // return log + 15;

        // case 65536:
        // return log + 16;

        // case 131072:
        // return log + 17;

        // case 262144:
        // return log + 18;

        // case 524288:
        // return log + 19;

        // case 1048576:
        // return log + 20;

        // case 2097152:
        // return log + 21;

        // case 4194304:
        // return log + 22;

        // case 8388608:
        // return log + 23;

        // case 16777216:
        // return log + 24;

        // case 33554432:
        // return log + 25;

        // case 67108864:
        // return log + 26;

        // case 134217728:
        // return log + 27;

        // case 268435456:
        // return log + 28;

        // case 536870912:
        // return log + 29;

        // case 1073741824:
        // return log + 30;

        // case -1:
        // return log + 31;
        // }
        // return 0;
    }
}
