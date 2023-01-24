package kslaya;

import battlecode.common.*;

import java.util.Map;

public class BFS {
    RobotController rc;
    StringBuilder seen = new StringBuilder();
    StringBuilder result = new StringBuilder();
    StringBuilder stb = new StringBuilder();

    public void redoMap() throws GameActionException {
        MapInfo mapInfo[] = rc.senseNearbyMapInfos();
        MapLocation curLoc = rc.getLocation();
        MapLocation loc;
        for (MapInfo mapInfo1 : mapInfo) {
            loc = mapInfo1.getMapLocation();
            if (curLoc.distanceSquaredTo(loc) > 15) continue;
            if (!mapInfo1.isPassable()) seen.setCharAt((loc.x - curLoc.x + 3) * 7 + (loc.y - curLoc.y + 3), '1');
        }
    }

    public BFS(RobotController rc) throws GameActionException {
        this.rc = rc;
        seen.append("0000000000000000000000000000000000000000000000000");
    }

    public void reset() throws GameActionException {
        Queue.reset();
        seen.replace(0, 49, "0000000000000000000000000000000000000000000000000");
        redoMap();
        stb.setLength(0);
        result.setLength(0);
    }

    void initBFS(MapLocation[] path, int cur) throws GameActionException {
        reset();

        MapLocation curLoc = rc.getLocation();
        RobotInfo robotInfos[] = rc.senseNearbyRobots();
        MapLocation loc;

        for (RobotInfo robotInfo : robotInfos) {
            loc = robotInfo.getLocation();
            if (curLoc.distanceSquaredTo(loc) > 15) continue;
            seen.setCharAt((loc.x - curLoc.x + 3) * 7 + (loc.y - curLoc.y + 3), '1');
        }

        for (int i = cur; i >= Math.max(0, cur - 7); i--) {
            if ((path[i].x - curLoc.x) * (path[i].x - curLoc.x) + (path[i].y - curLoc.y) * (path[i].y - curLoc.y) > 15) continue;
            stb.append("^" + ((path[i].x - curLoc.x + 3) * 7 + (path[i].y - curLoc.y + 3)));
            if (rc.getLocation().distanceSquaredTo(path[i]) <= 8 && rc.canSenseLocation(path[i]) && rc.senseRobotAtLocation(path[i]) != null) continue;
//            seen.setCharAt((path[i].x - curLoc.x + 3) * 7 + (path[i].y - curLoc.y + 3), '0');
        }

        seen.setCharAt(24, '1');
        Queue.queuePush(24 << 8);

        if (rc.getID() == 11267) {
            System.out.println(seen);
        }

        while (!Queue.queueEmpty()) {
            int current = Queue.queuePop();
            int first = (current >> 4) & 15;
            int second = (current & 15);
            int idx = current >> 8;

            if (rc.getID() == 11267) {
//                System.out.println(curLoc);
                System.out.println(new MapLocation(curLoc.x + idx / 7 - 3, curLoc.y + idx % 7 - 3));
                System.out.println(Direction.DIRECTION_ORDER[first] + " " + Direction.DIRECTION_ORDER[second]);
            }

            if (stb.indexOf(String.valueOf(idx)) != -1) {
                result.append("^" + (idx) + ":" + (first) + "" + (second));
            }

            switch(idx) {
                case 1:
                    if(seen.charAt(2) == '0') {
                        seen.setCharAt(2, '1');
                        if (first == 0) Queue.queuePush(560);
                        else if (second == 0) Queue.queuePush((515) | (first << 4));
                        else Queue.queuePush(512 | (first << 4) | second);
                    }
                    if(seen.charAt(7) == '0') {
                        seen.setCharAt(7, '1');
                        if (first == 0) Queue.queuePush(1888);
                        else if (second == 0) Queue.queuePush((1798) | (first << 4));
                        else Queue.queuePush(1792 | (first << 4) | second);
                    }
                    if(seen.charAt(8) == '0') {
                        seen.setCharAt(8, '1');
                        if (first == 0) Queue.queuePush(2128);
                        else if (second == 0) Queue.queuePush((2053) | (first << 4));
                        else Queue.queuePush(2048 | (first << 4) | second);
                    }
                    if(seen.charAt(9) == '0') {
                        seen.setCharAt(9, '1');
                        if (first == 0) Queue.queuePush(2368);
                        else if (second == 0) Queue.queuePush((2308) | (first << 4));
                        else Queue.queuePush(2304 | (first << 4) | second);
                    }
                    break;
                case 2:
                    if(seen.charAt(1) == '0') {
                        seen.setCharAt(1, '1');
                        if (first == 0) Queue.queuePush(368);
                        else if (second == 0) Queue.queuePush((263) | (first << 4));
                        else Queue.queuePush(256 | (first << 4) | second);
                    }
                    if(seen.charAt(3) == '0') {
                        seen.setCharAt(3, '1');
                        if (first == 0) Queue.queuePush(816);
                        else if (second == 0) Queue.queuePush((771) | (first << 4));
                        else Queue.queuePush(768 | (first << 4) | second);
                    }
                    if(seen.charAt(8) == '0') {
                        seen.setCharAt(8, '1');
                        if (first == 0) Queue.queuePush(2144);
                        else if (second == 0) Queue.queuePush((2054) | (first << 4));
                        else Queue.queuePush(2048 | (first << 4) | second);
                    }
                    if(seen.charAt(9) == '0') {
                        seen.setCharAt(9, '1');
                        if (first == 0) Queue.queuePush(2384);
                        else if (second == 0) Queue.queuePush((2309) | (first << 4));
                        else Queue.queuePush(2304 | (first << 4) | second);
                    }
                    if(seen.charAt(10) == '0') {
                        seen.setCharAt(10, '1');
                        if (first == 0) Queue.queuePush(2624);
                        else if (second == 0) Queue.queuePush((2564) | (first << 4));
                        else Queue.queuePush(2560 | (first << 4) | second);
                    }
                    break;
                case 3:
                    if(seen.charAt(2) == '0') {
                        seen.setCharAt(2, '1');
                        if (first == 0) Queue.queuePush(624);
                        else if (second == 0) Queue.queuePush((519) | (first << 4));
                        else Queue.queuePush(512 | (first << 4) | second);
                    }
                    if(seen.charAt(4) == '0') {
                        seen.setCharAt(4, '1');
                        if (first == 0) Queue.queuePush(1072);
                        else if (second == 0) Queue.queuePush((1027) | (first << 4));
                        else Queue.queuePush(1024 | (first << 4) | second);
                    }
                    if(seen.charAt(9) == '0') {
                        seen.setCharAt(9, '1');
                        if (first == 0) Queue.queuePush(2400);
                        else if (second == 0) Queue.queuePush((2310) | (first << 4));
                        else Queue.queuePush(2304 | (first << 4) | second);
                    }
                    if(seen.charAt(10) == '0') {
                        seen.setCharAt(10, '1');
                        if (first == 0) Queue.queuePush(2640);
                        else if (second == 0) Queue.queuePush((2565) | (first << 4));
                        else Queue.queuePush(2560 | (first << 4) | second);
                    }
                    if(seen.charAt(11) == '0') {
                        seen.setCharAt(11, '1');
                        if (first == 0) Queue.queuePush(2880);
                        else if (second == 0) Queue.queuePush((2820) | (first << 4));
                        else Queue.queuePush(2816 | (first << 4) | second);
                    }
                    break;
                case 4:
                    if(seen.charAt(3) == '0') {
                        seen.setCharAt(3, '1');
                        if (first == 0) Queue.queuePush(880);
                        else if (second == 0) Queue.queuePush((775) | (first << 4));
                        else Queue.queuePush(768 | (first << 4) | second);
                    }
                    if(seen.charAt(5) == '0') {
                        seen.setCharAt(5, '1');
                        if (first == 0) Queue.queuePush(1328);
                        else if (second == 0) Queue.queuePush((1283) | (first << 4));
                        else Queue.queuePush(1280 | (first << 4) | second);
                    }
                    if(seen.charAt(10) == '0') {
                        seen.setCharAt(10, '1');
                        if (first == 0) Queue.queuePush(2656);
                        else if (second == 0) Queue.queuePush((2566) | (first << 4));
                        else Queue.queuePush(2560 | (first << 4) | second);
                    }
                    if(seen.charAt(11) == '0') {
                        seen.setCharAt(11, '1');
                        if (first == 0) Queue.queuePush(2896);
                        else if (second == 0) Queue.queuePush((2821) | (first << 4));
                        else Queue.queuePush(2816 | (first << 4) | second);
                    }
                    if(seen.charAt(12) == '0') {
                        seen.setCharAt(12, '1');
                        if (first == 0) Queue.queuePush(3136);
                        else if (second == 0) Queue.queuePush((3076) | (first << 4));
                        else Queue.queuePush(3072 | (first << 4) | second);
                    }
                    break;
                case 5:
                    if(seen.charAt(4) == '0') {
                        seen.setCharAt(4, '1');
                        if (first == 0) Queue.queuePush(1136);
                        else if (second == 0) Queue.queuePush((1031) | (first << 4));
                        else Queue.queuePush(1024 | (first << 4) | second);
                    }
                    if(seen.charAt(11) == '0') {
                        seen.setCharAt(11, '1');
                        if (first == 0) Queue.queuePush(2912);
                        else if (second == 0) Queue.queuePush((2822) | (first << 4));
                        else Queue.queuePush(2816 | (first << 4) | second);
                    }
                    if(seen.charAt(12) == '0') {
                        seen.setCharAt(12, '1');
                        if (first == 0) Queue.queuePush(3152);
                        else if (second == 0) Queue.queuePush((3077) | (first << 4));
                        else Queue.queuePush(3072 | (first << 4) | second);
                    }
                    if(seen.charAt(13) == '0') {
                        seen.setCharAt(13, '1');
                        if (first == 0) Queue.queuePush(3392);
                        else if (second == 0) Queue.queuePush((3332) | (first << 4));
                        else Queue.queuePush(3328 | (first << 4) | second);
                    }
                    break;
                case 7:
                    if(seen.charAt(1) == '0') {
                        seen.setCharAt(1, '1');
                        if (first == 0) Queue.queuePush(288);
                        else if (second == 0) Queue.queuePush((258) | (first << 4));
                        else Queue.queuePush(256 | (first << 4) | second);
                    }
                    if(seen.charAt(8) == '0') {
                        seen.setCharAt(8, '1');
                        if (first == 0) Queue.queuePush(2096);
                        else if (second == 0) Queue.queuePush((2051) | (first << 4));
                        else Queue.queuePush(2048 | (first << 4) | second);
                    }
                    if(seen.charAt(14) == '0') {
                        seen.setCharAt(14, '1');
                        if (first == 0) Queue.queuePush(3664);
                        else if (second == 0) Queue.queuePush((3589) | (first << 4));
                        else Queue.queuePush(3584 | (first << 4) | second);
                    }
                    if(seen.charAt(15) == '0') {
                        seen.setCharAt(15, '1');
                        if (first == 0) Queue.queuePush(3904);
                        else if (second == 0) Queue.queuePush((3844) | (first << 4));
                        else Queue.queuePush(3840 | (first << 4) | second);
                    }
                    break;
                case 8:
                    if(seen.charAt(1) == '0') {
                        seen.setCharAt(1, '1');
                        if (first == 0) Queue.queuePush(272);
                        else if (second == 0) Queue.queuePush((257) | (first << 4));
                        else Queue.queuePush(256 | (first << 4) | second);
                    }
                    if(seen.charAt(2) == '0') {
                        seen.setCharAt(2, '1');
                        if (first == 0) Queue.queuePush(544);
                        else if (second == 0) Queue.queuePush((514) | (first << 4));
                        else Queue.queuePush(512 | (first << 4) | second);
                    }
                    if(seen.charAt(7) == '0') {
                        seen.setCharAt(7, '1');
                        if (first == 0) Queue.queuePush(1904);
                        else if (second == 0) Queue.queuePush((1799) | (first << 4));
                        else Queue.queuePush(1792 | (first << 4) | second);
                    }
                    if(seen.charAt(9) == '0') {
                        seen.setCharAt(9, '1');
                        if (first == 0) Queue.queuePush(2352);
                        else if (second == 0) Queue.queuePush((2307) | (first << 4));
                        else Queue.queuePush(2304 | (first << 4) | second);
                    }
                    if(seen.charAt(14) == '0') {
                        seen.setCharAt(14, '1');
                        if (first == 0) Queue.queuePush(3680);
                        else if (second == 0) Queue.queuePush((3590) | (first << 4));
                        else Queue.queuePush(3584 | (first << 4) | second);
                    }
                    if(seen.charAt(15) == '0') {
                        seen.setCharAt(15, '1');
                        if (first == 0) Queue.queuePush(3920);
                        else if (second == 0) Queue.queuePush((3845) | (first << 4));
                        else Queue.queuePush(3840 | (first << 4) | second);
                    }
                    if(seen.charAt(16) == '0') {
                        seen.setCharAt(16, '1');
                        if (first == 0) Queue.queuePush(4160);
                        else if (second == 0) Queue.queuePush((4100) | (first << 4));
                        else Queue.queuePush(4096 | (first << 4) | second);
                    }
                    break;
                case 9:
                    if(seen.charAt(1) == '0') {
                        seen.setCharAt(1, '1');
                        if (first == 0) Queue.queuePush(384);
                        else if (second == 0) Queue.queuePush((264) | (first << 4));
                        else Queue.queuePush(256 | (first << 4) | second);
                    }
                    if(seen.charAt(2) == '0') {
                        seen.setCharAt(2, '1');
                        if (first == 0) Queue.queuePush(528);
                        else if (second == 0) Queue.queuePush((513) | (first << 4));
                        else Queue.queuePush(512 | (first << 4) | second);
                    }
                    if(seen.charAt(3) == '0') {
                        seen.setCharAt(3, '1');
                        if (first == 0) Queue.queuePush(800);
                        else if (second == 0) Queue.queuePush((770) | (first << 4));
                        else Queue.queuePush(768 | (first << 4) | second);
                    }
                    if(seen.charAt(8) == '0') {
                        seen.setCharAt(8, '1');
                        if (first == 0) Queue.queuePush(2160);
                        else if (second == 0) Queue.queuePush((2055) | (first << 4));
                        else Queue.queuePush(2048 | (first << 4) | second);
                    }
                    if(seen.charAt(10) == '0') {
                        seen.setCharAt(10, '1');
                        if (first == 0) Queue.queuePush(2608);
                        else if (second == 0) Queue.queuePush((2563) | (first << 4));
                        else Queue.queuePush(2560 | (first << 4) | second);
                    }
                    if(seen.charAt(15) == '0') {
                        seen.setCharAt(15, '1');
                        if (first == 0) Queue.queuePush(3936);
                        else if (second == 0) Queue.queuePush((3846) | (first << 4));
                        else Queue.queuePush(3840 | (first << 4) | second);
                    }
                    if(seen.charAt(16) == '0') {
                        seen.setCharAt(16, '1');
                        if (first == 0) Queue.queuePush(4176);
                        else if (second == 0) Queue.queuePush((4101) | (first << 4));
                        else Queue.queuePush(4096 | (first << 4) | second);
                    }
                    if(seen.charAt(17) == '0') {
                        seen.setCharAt(17, '1');
                        if (first == 0) Queue.queuePush(4416);
                        else if (second == 0) Queue.queuePush((4356) | (first << 4));
                        else Queue.queuePush(4352 | (first << 4) | second);
                    }
                    break;
                case 10:
                    if(seen.charAt(2) == '0') {
                        seen.setCharAt(2, '1');
                        if (first == 0) Queue.queuePush(640);
                        else if (second == 0) Queue.queuePush((520) | (first << 4));
                        else Queue.queuePush(512 | (first << 4) | second);
                    }
                    if(seen.charAt(3) == '0') {
                        seen.setCharAt(3, '1');
                        if (first == 0) Queue.queuePush(784);
                        else if (second == 0) Queue.queuePush((769) | (first << 4));
                        else Queue.queuePush(768 | (first << 4) | second);
                    }
                    if(seen.charAt(4) == '0') {
                        seen.setCharAt(4, '1');
                        if (first == 0) Queue.queuePush(1056);
                        else if (second == 0) Queue.queuePush((1026) | (first << 4));
                        else Queue.queuePush(1024 | (first << 4) | second);
                    }
                    if(seen.charAt(9) == '0') {
                        seen.setCharAt(9, '1');
                        if (first == 0) Queue.queuePush(2416);
                        else if (second == 0) Queue.queuePush((2311) | (first << 4));
                        else Queue.queuePush(2304 | (first << 4) | second);
                    }
                    if(seen.charAt(11) == '0') {
                        seen.setCharAt(11, '1');
                        if (first == 0) Queue.queuePush(2864);
                        else if (second == 0) Queue.queuePush((2819) | (first << 4));
                        else Queue.queuePush(2816 | (first << 4) | second);
                    }
                    if(seen.charAt(16) == '0') {
                        seen.setCharAt(16, '1');
                        if (first == 0) Queue.queuePush(4192);
                        else if (second == 0) Queue.queuePush((4102) | (first << 4));
                        else Queue.queuePush(4096 | (first << 4) | second);
                    }
                    if(seen.charAt(17) == '0') {
                        seen.setCharAt(17, '1');
                        if (first == 0) Queue.queuePush(4432);
                        else if (second == 0) Queue.queuePush((4357) | (first << 4));
                        else Queue.queuePush(4352 | (first << 4) | second);
                    }
                    if(seen.charAt(18) == '0') {
                        seen.setCharAt(18, '1');
                        if (first == 0) Queue.queuePush(4672);
                        else if (second == 0) Queue.queuePush((4612) | (first << 4));
                        else Queue.queuePush(4608 | (first << 4) | second);
                    }
                    break;
                case 11:
                    if(seen.charAt(3) == '0') {
                        seen.setCharAt(3, '1');
                        if (first == 0) Queue.queuePush(896);
                        else if (second == 0) Queue.queuePush((776) | (first << 4));
                        else Queue.queuePush(768 | (first << 4) | second);
                    }
                    if(seen.charAt(4) == '0') {
                        seen.setCharAt(4, '1');
                        if (first == 0) Queue.queuePush(1040);
                        else if (second == 0) Queue.queuePush((1025) | (first << 4));
                        else Queue.queuePush(1024 | (first << 4) | second);
                    }
                    if(seen.charAt(5) == '0') {
                        seen.setCharAt(5, '1');
                        if (first == 0) Queue.queuePush(1312);
                        else if (second == 0) Queue.queuePush((1282) | (first << 4));
                        else Queue.queuePush(1280 | (first << 4) | second);
                    }
                    if(seen.charAt(10) == '0') {
                        seen.setCharAt(10, '1');
                        if (first == 0) Queue.queuePush(2672);
                        else if (second == 0) Queue.queuePush((2567) | (first << 4));
                        else Queue.queuePush(2560 | (first << 4) | second);
                    }
                    if(seen.charAt(12) == '0') {
                        seen.setCharAt(12, '1');
                        if (first == 0) Queue.queuePush(3120);
                        else if (second == 0) Queue.queuePush((3075) | (first << 4));
                        else Queue.queuePush(3072 | (first << 4) | second);
                    }
                    if(seen.charAt(17) == '0') {
                        seen.setCharAt(17, '1');
                        if (first == 0) Queue.queuePush(4448);
                        else if (second == 0) Queue.queuePush((4358) | (first << 4));
                        else Queue.queuePush(4352 | (first << 4) | second);
                    }
                    if(seen.charAt(18) == '0') {
                        seen.setCharAt(18, '1');
                        if (first == 0) Queue.queuePush(4688);
                        else if (second == 0) Queue.queuePush((4613) | (first << 4));
                        else Queue.queuePush(4608 | (first << 4) | second);
                    }
                    if(seen.charAt(19) == '0') {
                        seen.setCharAt(19, '1');
                        if (first == 0) Queue.queuePush(4928);
                        else if (second == 0) Queue.queuePush((4868) | (first << 4));
                        else Queue.queuePush(4864 | (first << 4) | second);
                    }
                    break;
                case 12:
                    if(seen.charAt(4) == '0') {
                        seen.setCharAt(4, '1');
                        if (first == 0) Queue.queuePush(1152);
                        else if (second == 0) Queue.queuePush((1032) | (first << 4));
                        else Queue.queuePush(1024 | (first << 4) | second);
                    }
                    if(seen.charAt(5) == '0') {
                        seen.setCharAt(5, '1');
                        if (first == 0) Queue.queuePush(1296);
                        else if (second == 0) Queue.queuePush((1281) | (first << 4));
                        else Queue.queuePush(1280 | (first << 4) | second);
                    }
                    if(seen.charAt(11) == '0') {
                        seen.setCharAt(11, '1');
                        if (first == 0) Queue.queuePush(2928);
                        else if (second == 0) Queue.queuePush((2823) | (first << 4));
                        else Queue.queuePush(2816 | (first << 4) | second);
                    }
                    if(seen.charAt(13) == '0') {
                        seen.setCharAt(13, '1');
                        if (first == 0) Queue.queuePush(3376);
                        else if (second == 0) Queue.queuePush((3331) | (first << 4));
                        else Queue.queuePush(3328 | (first << 4) | second);
                    }
                    if(seen.charAt(18) == '0') {
                        seen.setCharAt(18, '1');
                        if (first == 0) Queue.queuePush(4704);
                        else if (second == 0) Queue.queuePush((4614) | (first << 4));
                        else Queue.queuePush(4608 | (first << 4) | second);
                    }
                    if(seen.charAt(19) == '0') {
                        seen.setCharAt(19, '1');
                        if (first == 0) Queue.queuePush(4944);
                        else if (second == 0) Queue.queuePush((4869) | (first << 4));
                        else Queue.queuePush(4864 | (first << 4) | second);
                    }
                    if(seen.charAt(20) == '0') {
                        seen.setCharAt(20, '1');
                        if (first == 0) Queue.queuePush(5184);
                        else if (second == 0) Queue.queuePush((5124) | (first << 4));
                        else Queue.queuePush(5120 | (first << 4) | second);
                    }
                    break;
                case 13:
                    if(seen.charAt(5) == '0') {
                        seen.setCharAt(5, '1');
                        if (first == 0) Queue.queuePush(1408);
                        else if (second == 0) Queue.queuePush((1288) | (first << 4));
                        else Queue.queuePush(1280 | (first << 4) | second);
                    }
                    if(seen.charAt(12) == '0') {
                        seen.setCharAt(12, '1');
                        if (first == 0) Queue.queuePush(3184);
                        else if (second == 0) Queue.queuePush((3079) | (first << 4));
                        else Queue.queuePush(3072 | (first << 4) | second);
                    }
                    if(seen.charAt(19) == '0') {
                        seen.setCharAt(19, '1');
                        if (first == 0) Queue.queuePush(4960);
                        else if (second == 0) Queue.queuePush((4870) | (first << 4));
                        else Queue.queuePush(4864 | (first << 4) | second);
                    }
                    if(seen.charAt(20) == '0') {
                        seen.setCharAt(20, '1');
                        if (first == 0) Queue.queuePush(5200);
                        else if (second == 0) Queue.queuePush((5125) | (first << 4));
                        else Queue.queuePush(5120 | (first << 4) | second);
                    }
                    break;
                case 14:
                    if(seen.charAt(7) == '0') {
                        seen.setCharAt(7, '1');
                        if (first == 0) Queue.queuePush(1808);
                        else if (second == 0) Queue.queuePush((1793) | (first << 4));
                        else Queue.queuePush(1792 | (first << 4) | second);
                    }
                    if(seen.charAt(8) == '0') {
                        seen.setCharAt(8, '1');
                        if (first == 0) Queue.queuePush(2080);
                        else if (second == 0) Queue.queuePush((2050) | (first << 4));
                        else Queue.queuePush(2048 | (first << 4) | second);
                    }
                    if(seen.charAt(15) == '0') {
                        seen.setCharAt(15, '1');
                        if (first == 0) Queue.queuePush(3888);
                        else if (second == 0) Queue.queuePush((3843) | (first << 4));
                        else Queue.queuePush(3840 | (first << 4) | second);
                    }
                    if(seen.charAt(21) == '0') {
                        seen.setCharAt(21, '1');
                        if (first == 0) Queue.queuePush(5456);
                        else if (second == 0) Queue.queuePush((5381) | (first << 4));
                        else Queue.queuePush(5376 | (first << 4) | second);
                    }
                    if(seen.charAt(22) == '0') {
                        seen.setCharAt(22, '1');
                        if (first == 0) Queue.queuePush(5696);
                        else if (second == 0) Queue.queuePush((5636) | (first << 4));
                        else Queue.queuePush(5632 | (first << 4) | second);
                    }
                    break;
                case 15:
                    if(seen.charAt(7) == '0') {
                        seen.setCharAt(7, '1');
                        if (first == 0) Queue.queuePush(1920);
                        else if (second == 0) Queue.queuePush((1800) | (first << 4));
                        else Queue.queuePush(1792 | (first << 4) | second);
                    }
                    if(seen.charAt(8) == '0') {
                        seen.setCharAt(8, '1');
                        if (first == 0) Queue.queuePush(2064);
                        else if (second == 0) Queue.queuePush((2049) | (first << 4));
                        else Queue.queuePush(2048 | (first << 4) | second);
                    }
                    if(seen.charAt(9) == '0') {
                        seen.setCharAt(9, '1');
                        if (first == 0) Queue.queuePush(2336);
                        else if (second == 0) Queue.queuePush((2306) | (first << 4));
                        else Queue.queuePush(2304 | (first << 4) | second);
                    }
                    if(seen.charAt(14) == '0') {
                        seen.setCharAt(14, '1');
                        if (first == 0) Queue.queuePush(3696);
                        else if (second == 0) Queue.queuePush((3591) | (first << 4));
                        else Queue.queuePush(3584 | (first << 4) | second);
                    }
                    if(seen.charAt(16) == '0') {
                        seen.setCharAt(16, '1');
                        if (first == 0) Queue.queuePush(4144);
                        else if (second == 0) Queue.queuePush((4099) | (first << 4));
                        else Queue.queuePush(4096 | (first << 4) | second);
                    }
                    if(seen.charAt(21) == '0') {
                        seen.setCharAt(21, '1');
                        if (first == 0) Queue.queuePush(5472);
                        else if (second == 0) Queue.queuePush((5382) | (first << 4));
                        else Queue.queuePush(5376 | (first << 4) | second);
                    }
                    if(seen.charAt(22) == '0') {
                        seen.setCharAt(22, '1');
                        if (first == 0) Queue.queuePush(5712);
                        else if (second == 0) Queue.queuePush((5637) | (first << 4));
                        else Queue.queuePush(5632 | (first << 4) | second);
                    }
                    if(seen.charAt(23) == '0') {
                        seen.setCharAt(23, '1');
                        if (first == 0) Queue.queuePush(5952);
                        else if (second == 0) Queue.queuePush((5892) | (first << 4));
                        else Queue.queuePush(5888 | (first << 4) | second);
                    }
                    break;
                case 16:
                    if(seen.charAt(8) == '0') {
                        seen.setCharAt(8, '1');
                        if (first == 0) Queue.queuePush(2176);
                        else if (second == 0) Queue.queuePush((2056) | (first << 4));
                        else Queue.queuePush(2048 | (first << 4) | second);
                    }
                    if(seen.charAt(9) == '0') {
                        seen.setCharAt(9, '1');
                        if (first == 0) Queue.queuePush(2320);
                        else if (second == 0) Queue.queuePush((2305) | (first << 4));
                        else Queue.queuePush(2304 | (first << 4) | second);
                    }
                    if(seen.charAt(10) == '0') {
                        seen.setCharAt(10, '1');
                        if (first == 0) Queue.queuePush(2592);
                        else if (second == 0) Queue.queuePush((2562) | (first << 4));
                        else Queue.queuePush(2560 | (first << 4) | second);
                    }
                    if(seen.charAt(15) == '0') {
                        seen.setCharAt(15, '1');
                        if (first == 0) Queue.queuePush(3952);
                        else if (second == 0) Queue.queuePush((3847) | (first << 4));
                        else Queue.queuePush(3840 | (first << 4) | second);
                    }
                    if(seen.charAt(17) == '0') {
                        seen.setCharAt(17, '1');
                        if (first == 0) Queue.queuePush(4400);
                        else if (second == 0) Queue.queuePush((4355) | (first << 4));
                        else Queue.queuePush(4352 | (first << 4) | second);
                    }
                    if(seen.charAt(22) == '0') {
                        seen.setCharAt(22, '1');
                        if (first == 0) Queue.queuePush(5728);
                        else if (second == 0) Queue.queuePush((5638) | (first << 4));
                        else Queue.queuePush(5632 | (first << 4) | second);
                    }
                    if(seen.charAt(23) == '0') {
                        seen.setCharAt(23, '1');
                        if (first == 0) Queue.queuePush(5968);
                        else if (second == 0) Queue.queuePush((5893) | (first << 4));
                        else Queue.queuePush(5888 | (first << 4) | second);
                    }
                    if(seen.charAt(24) == '0') {
                        seen.setCharAt(24, '1');
                        if (first == 0) Queue.queuePush(6208);
                        else if (second == 0) Queue.queuePush((6148) | (first << 4));
                        else Queue.queuePush(6144 | (first << 4) | second);
                    }
                    break;
                case 17:
                    if(seen.charAt(9) == '0') {
                        seen.setCharAt(9, '1');
                        if (first == 0) Queue.queuePush(2432);
                        else if (second == 0) Queue.queuePush((2312) | (first << 4));
                        else Queue.queuePush(2304 | (first << 4) | second);
                    }
                    if(seen.charAt(10) == '0') {
                        seen.setCharAt(10, '1');
                        if (first == 0) Queue.queuePush(2576);
                        else if (second == 0) Queue.queuePush((2561) | (first << 4));
                        else Queue.queuePush(2560 | (first << 4) | second);
                    }
                    if(seen.charAt(11) == '0') {
                        seen.setCharAt(11, '1');
                        if (first == 0) Queue.queuePush(2848);
                        else if (second == 0) Queue.queuePush((2818) | (first << 4));
                        else Queue.queuePush(2816 | (first << 4) | second);
                    }
                    if(seen.charAt(16) == '0') {
                        seen.setCharAt(16, '1');
                        if (first == 0) Queue.queuePush(4208);
                        else if (second == 0) Queue.queuePush((4103) | (first << 4));
                        else Queue.queuePush(4096 | (first << 4) | second);
                    }
                    if(seen.charAt(18) == '0') {
                        seen.setCharAt(18, '1');
                        if (first == 0) Queue.queuePush(4656);
                        else if (second == 0) Queue.queuePush((4611) | (first << 4));
                        else Queue.queuePush(4608 | (first << 4) | second);
                    }
                    if(seen.charAt(23) == '0') {
                        seen.setCharAt(23, '1');
                        if (first == 0) Queue.queuePush(5984);
                        else if (second == 0) Queue.queuePush((5894) | (first << 4));
                        else Queue.queuePush(5888 | (first << 4) | second);
                    }
                    if(seen.charAt(24) == '0') {
                        seen.setCharAt(24, '1');
                        if (first == 0) Queue.queuePush(6224);
                        else if (second == 0) Queue.queuePush((6149) | (first << 4));
                        else Queue.queuePush(6144 | (first << 4) | second);
                    }
                    if(seen.charAt(25) == '0') {
                        seen.setCharAt(25, '1');
                        if (first == 0) Queue.queuePush(6464);
                        else if (second == 0) Queue.queuePush((6404) | (first << 4));
                        else Queue.queuePush(6400 | (first << 4) | second);
                    }
                    break;
                case 18:
                    if(seen.charAt(10) == '0') {
                        seen.setCharAt(10, '1');
                        if (first == 0) Queue.queuePush(2688);
                        else if (second == 0) Queue.queuePush((2568) | (first << 4));
                        else Queue.queuePush(2560 | (first << 4) | second);
                    }
                    if(seen.charAt(11) == '0') {
                        seen.setCharAt(11, '1');
                        if (first == 0) Queue.queuePush(2832);
                        else if (second == 0) Queue.queuePush((2817) | (first << 4));
                        else Queue.queuePush(2816 | (first << 4) | second);
                    }
                    if(seen.charAt(12) == '0') {
                        seen.setCharAt(12, '1');
                        if (first == 0) Queue.queuePush(3104);
                        else if (second == 0) Queue.queuePush((3074) | (first << 4));
                        else Queue.queuePush(3072 | (first << 4) | second);
                    }
                    if(seen.charAt(17) == '0') {
                        seen.setCharAt(17, '1');
                        if (first == 0) Queue.queuePush(4464);
                        else if (second == 0) Queue.queuePush((4359) | (first << 4));
                        else Queue.queuePush(4352 | (first << 4) | second);
                    }
                    if(seen.charAt(19) == '0') {
                        seen.setCharAt(19, '1');
                        if (first == 0) Queue.queuePush(4912);
                        else if (second == 0) Queue.queuePush((4867) | (first << 4));
                        else Queue.queuePush(4864 | (first << 4) | second);
                    }
                    if(seen.charAt(24) == '0') {
                        seen.setCharAt(24, '1');
                        if (first == 0) Queue.queuePush(6240);
                        else if (second == 0) Queue.queuePush((6150) | (first << 4));
                        else Queue.queuePush(6144 | (first << 4) | second);
                    }
                    if(seen.charAt(25) == '0') {
                        seen.setCharAt(25, '1');
                        if (first == 0) Queue.queuePush(6480);
                        else if (second == 0) Queue.queuePush((6405) | (first << 4));
                        else Queue.queuePush(6400 | (first << 4) | second);
                    }
                    if(seen.charAt(26) == '0') {
                        seen.setCharAt(26, '1');
                        if (first == 0) Queue.queuePush(6720);
                        else if (second == 0) Queue.queuePush((6660) | (first << 4));
                        else Queue.queuePush(6656 | (first << 4) | second);
                    }
                    break;
                case 19:
                    if(seen.charAt(11) == '0') {
                        seen.setCharAt(11, '1');
                        if (first == 0) Queue.queuePush(2944);
                        else if (second == 0) Queue.queuePush((2824) | (first << 4));
                        else Queue.queuePush(2816 | (first << 4) | second);
                    }
                    if(seen.charAt(12) == '0') {
                        seen.setCharAt(12, '1');
                        if (first == 0) Queue.queuePush(3088);
                        else if (second == 0) Queue.queuePush((3073) | (first << 4));
                        else Queue.queuePush(3072 | (first << 4) | second);
                    }
                    if(seen.charAt(13) == '0') {
                        seen.setCharAt(13, '1');
                        if (first == 0) Queue.queuePush(3360);
                        else if (second == 0) Queue.queuePush((3330) | (first << 4));
                        else Queue.queuePush(3328 | (first << 4) | second);
                    }
                    if(seen.charAt(18) == '0') {
                        seen.setCharAt(18, '1');
                        if (first == 0) Queue.queuePush(4720);
                        else if (second == 0) Queue.queuePush((4615) | (first << 4));
                        else Queue.queuePush(4608 | (first << 4) | second);
                    }
                    if(seen.charAt(20) == '0') {
                        seen.setCharAt(20, '1');
                        if (first == 0) Queue.queuePush(5168);
                        else if (second == 0) Queue.queuePush((5123) | (first << 4));
                        else Queue.queuePush(5120 | (first << 4) | second);
                    }
                    if(seen.charAt(25) == '0') {
                        seen.setCharAt(25, '1');
                        if (first == 0) Queue.queuePush(6496);
                        else if (second == 0) Queue.queuePush((6406) | (first << 4));
                        else Queue.queuePush(6400 | (first << 4) | second);
                    }
                    if(seen.charAt(26) == '0') {
                        seen.setCharAt(26, '1');
                        if (first == 0) Queue.queuePush(6736);
                        else if (second == 0) Queue.queuePush((6661) | (first << 4));
                        else Queue.queuePush(6656 | (first << 4) | second);
                    }
                    if(seen.charAt(27) == '0') {
                        seen.setCharAt(27, '1');
                        if (first == 0) Queue.queuePush(6976);
                        else if (second == 0) Queue.queuePush((6916) | (first << 4));
                        else Queue.queuePush(6912 | (first << 4) | second);
                    }
                    break;
                case 20:
                    if(seen.charAt(12) == '0') {
                        seen.setCharAt(12, '1');
                        if (first == 0) Queue.queuePush(3200);
                        else if (second == 0) Queue.queuePush((3080) | (first << 4));
                        else Queue.queuePush(3072 | (first << 4) | second);
                    }
                    if(seen.charAt(13) == '0') {
                        seen.setCharAt(13, '1');
                        if (first == 0) Queue.queuePush(3344);
                        else if (second == 0) Queue.queuePush((3329) | (first << 4));
                        else Queue.queuePush(3328 | (first << 4) | second);
                    }
                    if(seen.charAt(19) == '0') {
                        seen.setCharAt(19, '1');
                        if (first == 0) Queue.queuePush(4976);
                        else if (second == 0) Queue.queuePush((4871) | (first << 4));
                        else Queue.queuePush(4864 | (first << 4) | second);
                    }
                    if(seen.charAt(26) == '0') {
                        seen.setCharAt(26, '1');
                        if (first == 0) Queue.queuePush(6752);
                        else if (second == 0) Queue.queuePush((6662) | (first << 4));
                        else Queue.queuePush(6656 | (first << 4) | second);
                    }
                    if(seen.charAt(27) == '0') {
                        seen.setCharAt(27, '1');
                        if (first == 0) Queue.queuePush(6992);
                        else if (second == 0) Queue.queuePush((6917) | (first << 4));
                        else Queue.queuePush(6912 | (first << 4) | second);
                    }
                    break;
                case 21:
                    if(seen.charAt(14) == '0') {
                        seen.setCharAt(14, '1');
                        if (first == 0) Queue.queuePush(3600);
                        else if (second == 0) Queue.queuePush((3585) | (first << 4));
                        else Queue.queuePush(3584 | (first << 4) | second);
                    }
                    if(seen.charAt(15) == '0') {
                        seen.setCharAt(15, '1');
                        if (first == 0) Queue.queuePush(3872);
                        else if (second == 0) Queue.queuePush((3842) | (first << 4));
                        else Queue.queuePush(3840 | (first << 4) | second);
                    }
                    if(seen.charAt(22) == '0') {
                        seen.setCharAt(22, '1');
                        if (first == 0) Queue.queuePush(5680);
                        else if (second == 0) Queue.queuePush((5635) | (first << 4));
                        else Queue.queuePush(5632 | (first << 4) | second);
                    }
                    if(seen.charAt(28) == '0') {
                        seen.setCharAt(28, '1');
                        if (first == 0) Queue.queuePush(7248);
                        else if (second == 0) Queue.queuePush((7173) | (first << 4));
                        else Queue.queuePush(7168 | (first << 4) | second);
                    }
                    if(seen.charAt(29) == '0') {
                        seen.setCharAt(29, '1');
                        if (first == 0) Queue.queuePush(7488);
                        else if (second == 0) Queue.queuePush((7428) | (first << 4));
                        else Queue.queuePush(7424 | (first << 4) | second);
                    }
                    break;
                case 22:
                    if(seen.charAt(14) == '0') {
                        seen.setCharAt(14, '1');
                        if (first == 0) Queue.queuePush(3712);
                        else if (second == 0) Queue.queuePush((3592) | (first << 4));
                        else Queue.queuePush(3584 | (first << 4) | second);
                    }
                    if(seen.charAt(15) == '0') {
                        seen.setCharAt(15, '1');
                        if (first == 0) Queue.queuePush(3856);
                        else if (second == 0) Queue.queuePush((3841) | (first << 4));
                        else Queue.queuePush(3840 | (first << 4) | second);
                    }
                    if(seen.charAt(16) == '0') {
                        seen.setCharAt(16, '1');
                        if (first == 0) Queue.queuePush(4128);
                        else if (second == 0) Queue.queuePush((4098) | (first << 4));
                        else Queue.queuePush(4096 | (first << 4) | second);
                    }
                    if(seen.charAt(21) == '0') {
                        seen.setCharAt(21, '1');
                        if (first == 0) Queue.queuePush(5488);
                        else if (second == 0) Queue.queuePush((5383) | (first << 4));
                        else Queue.queuePush(5376 | (first << 4) | second);
                    }
                    if(seen.charAt(23) == '0') {
                        seen.setCharAt(23, '1');
                        if (first == 0) Queue.queuePush(5936);
                        else if (second == 0) Queue.queuePush((5891) | (first << 4));
                        else Queue.queuePush(5888 | (first << 4) | second);
                    }
                    if(seen.charAt(28) == '0') {
                        seen.setCharAt(28, '1');
                        if (first == 0) Queue.queuePush(7264);
                        else if (second == 0) Queue.queuePush((7174) | (first << 4));
                        else Queue.queuePush(7168 | (first << 4) | second);
                    }
                    if(seen.charAt(29) == '0') {
                        seen.setCharAt(29, '1');
                        if (first == 0) Queue.queuePush(7504);
                        else if (second == 0) Queue.queuePush((7429) | (first << 4));
                        else Queue.queuePush(7424 | (first << 4) | second);
                    }
                    if(seen.charAt(30) == '0') {
                        seen.setCharAt(30, '1');
                        if (first == 0) Queue.queuePush(7744);
                        else if (second == 0) Queue.queuePush((7684) | (first << 4));
                        else Queue.queuePush(7680 | (first << 4) | second);
                    }
                    break;
                case 23:
                    if(seen.charAt(15) == '0') {
                        seen.setCharAt(15, '1');
                        if (first == 0) Queue.queuePush(3968);
                        else if (second == 0) Queue.queuePush((3848) | (first << 4));
                        else Queue.queuePush(3840 | (first << 4) | second);
                    }
                    if(seen.charAt(16) == '0') {
                        seen.setCharAt(16, '1');
                        if (first == 0) Queue.queuePush(4112);
                        else if (second == 0) Queue.queuePush((4097) | (first << 4));
                        else Queue.queuePush(4096 | (first << 4) | second);
                    }
                    if(seen.charAt(17) == '0') {
                        seen.setCharAt(17, '1');
                        if (first == 0) Queue.queuePush(4384);
                        else if (second == 0) Queue.queuePush((4354) | (first << 4));
                        else Queue.queuePush(4352 | (first << 4) | second);
                    }
                    if(seen.charAt(22) == '0') {
                        seen.setCharAt(22, '1');
                        if (first == 0) Queue.queuePush(5744);
                        else if (second == 0) Queue.queuePush((5639) | (first << 4));
                        else Queue.queuePush(5632 | (first << 4) | second);
                    }
                    if(seen.charAt(24) == '0') {
                        seen.setCharAt(24, '1');
                        if (first == 0) Queue.queuePush(6192);
                        else if (second == 0) Queue.queuePush((6147) | (first << 4));
                        else Queue.queuePush(6144 | (first << 4) | second);
                    }
                    if(seen.charAt(29) == '0') {
                        seen.setCharAt(29, '1');
                        if (first == 0) Queue.queuePush(7520);
                        else if (second == 0) Queue.queuePush((7430) | (first << 4));
                        else Queue.queuePush(7424 | (first << 4) | second);
                    }
                    if(seen.charAt(30) == '0') {
                        seen.setCharAt(30, '1');
                        if (first == 0) Queue.queuePush(7760);
                        else if (second == 0) Queue.queuePush((7685) | (first << 4));
                        else Queue.queuePush(7680 | (first << 4) | second);
                    }
                    if(seen.charAt(31) == '0') {
                        seen.setCharAt(31, '1');
                        if (first == 0) Queue.queuePush(8000);
                        else if (second == 0) Queue.queuePush((7940) | (first << 4));
                        else Queue.queuePush(7936 | (first << 4) | second);
                    }
                    break;
                case 24:
                    if(seen.charAt(16) == '0') {
                        seen.setCharAt(16, '1');
                        if (first == 0) Queue.queuePush(4224);
                        else if (second == 0) Queue.queuePush((4104) | (first << 4));
                        else Queue.queuePush(4096 | (first << 4) | second);
                    }
                    if(seen.charAt(17) == '0') {
                        seen.setCharAt(17, '1');
                        if (first == 0) Queue.queuePush(4368);
                        else if (second == 0) Queue.queuePush((4353) | (first << 4));
                        else Queue.queuePush(4352 | (first << 4) | second);
                    }
                    if(seen.charAt(18) == '0') {
                        seen.setCharAt(18, '1');
                        if (first == 0) Queue.queuePush(4640);
                        else if (second == 0) Queue.queuePush((4610) | (first << 4));
                        else Queue.queuePush(4608 | (first << 4) | second);
                    }
                    if(seen.charAt(23) == '0') {
                        seen.setCharAt(23, '1');
                        if (first == 0) Queue.queuePush(6000);
                        else if (second == 0) Queue.queuePush((5895) | (first << 4));
                        else Queue.queuePush(5888 | (first << 4) | second);
                    }
                    if(seen.charAt(25) == '0') {
                        seen.setCharAt(25, '1');
                        if (first == 0) Queue.queuePush(6448);
                        else if (second == 0) Queue.queuePush((6403) | (first << 4));
                        else Queue.queuePush(6400 | (first << 4) | second);
                    }
                    if(seen.charAt(30) == '0') {
                        seen.setCharAt(30, '1');
                        if (first == 0) Queue.queuePush(7776);
                        else if (second == 0) Queue.queuePush((7686) | (first << 4));
                        else Queue.queuePush(7680 | (first << 4) | second);
                    }
                    if(seen.charAt(31) == '0') {
                        seen.setCharAt(31, '1');
                        if (first == 0) Queue.queuePush(8016);
                        else if (second == 0) Queue.queuePush((7941) | (first << 4));
                        else Queue.queuePush(7936 | (first << 4) | second);
                    }
                    if(seen.charAt(32) == '0') {
                        seen.setCharAt(32, '1');
                        if (first == 0) Queue.queuePush(8256);
                        else if (second == 0) Queue.queuePush((8196) | (first << 4));
                        else Queue.queuePush(8192 | (first << 4) | second);
                    }
                    break;
                case 25:
                    if(seen.charAt(17) == '0') {
                        seen.setCharAt(17, '1');
                        if (first == 0) Queue.queuePush(4480);
                        else if (second == 0) Queue.queuePush((4360) | (first << 4));
                        else Queue.queuePush(4352 | (first << 4) | second);
                    }
                    if(seen.charAt(18) == '0') {
                        seen.setCharAt(18, '1');
                        if (first == 0) Queue.queuePush(4624);
                        else if (second == 0) Queue.queuePush((4609) | (first << 4));
                        else Queue.queuePush(4608 | (first << 4) | second);
                    }
                    if(seen.charAt(19) == '0') {
                        seen.setCharAt(19, '1');
                        if (first == 0) Queue.queuePush(4896);
                        else if (second == 0) Queue.queuePush((4866) | (first << 4));
                        else Queue.queuePush(4864 | (first << 4) | second);
                    }
                    if(seen.charAt(24) == '0') {
                        seen.setCharAt(24, '1');
                        if (first == 0) Queue.queuePush(6256);
                        else if (second == 0) Queue.queuePush((6151) | (first << 4));
                        else Queue.queuePush(6144 | (first << 4) | second);
                    }
                    if(seen.charAt(26) == '0') {
                        seen.setCharAt(26, '1');
                        if (first == 0) Queue.queuePush(6704);
                        else if (second == 0) Queue.queuePush((6659) | (first << 4));
                        else Queue.queuePush(6656 | (first << 4) | second);
                    }
                    if(seen.charAt(31) == '0') {
                        seen.setCharAt(31, '1');
                        if (first == 0) Queue.queuePush(8032);
                        else if (second == 0) Queue.queuePush((7942) | (first << 4));
                        else Queue.queuePush(7936 | (first << 4) | second);
                    }
                    if(seen.charAt(32) == '0') {
                        seen.setCharAt(32, '1');
                        if (first == 0) Queue.queuePush(8272);
                        else if (second == 0) Queue.queuePush((8197) | (first << 4));
                        else Queue.queuePush(8192 | (first << 4) | second);
                    }
                    if(seen.charAt(33) == '0') {
                        seen.setCharAt(33, '1');
                        if (first == 0) Queue.queuePush(8512);
                        else if (second == 0) Queue.queuePush((8452) | (first << 4));
                        else Queue.queuePush(8448 | (first << 4) | second);
                    }
                    break;
                case 26:
                    if(seen.charAt(18) == '0') {
                        seen.setCharAt(18, '1');
                        if (first == 0) Queue.queuePush(4736);
                        else if (second == 0) Queue.queuePush((4616) | (first << 4));
                        else Queue.queuePush(4608 | (first << 4) | second);
                    }
                    if(seen.charAt(19) == '0') {
                        seen.setCharAt(19, '1');
                        if (first == 0) Queue.queuePush(4880);
                        else if (second == 0) Queue.queuePush((4865) | (first << 4));
                        else Queue.queuePush(4864 | (first << 4) | second);
                    }
                    if(seen.charAt(20) == '0') {
                        seen.setCharAt(20, '1');
                        if (first == 0) Queue.queuePush(5152);
                        else if (second == 0) Queue.queuePush((5122) | (first << 4));
                        else Queue.queuePush(5120 | (first << 4) | second);
                    }
                    if(seen.charAt(25) == '0') {
                        seen.setCharAt(25, '1');
                        if (first == 0) Queue.queuePush(6512);
                        else if (second == 0) Queue.queuePush((6407) | (first << 4));
                        else Queue.queuePush(6400 | (first << 4) | second);
                    }
                    if(seen.charAt(27) == '0') {
                        seen.setCharAt(27, '1');
                        if (first == 0) Queue.queuePush(6960);
                        else if (second == 0) Queue.queuePush((6915) | (first << 4));
                        else Queue.queuePush(6912 | (first << 4) | second);
                    }
                    if(seen.charAt(32) == '0') {
                        seen.setCharAt(32, '1');
                        if (first == 0) Queue.queuePush(8288);
                        else if (second == 0) Queue.queuePush((8198) | (first << 4));
                        else Queue.queuePush(8192 | (first << 4) | second);
                    }
                    if(seen.charAt(33) == '0') {
                        seen.setCharAt(33, '1');
                        if (first == 0) Queue.queuePush(8528);
                        else if (second == 0) Queue.queuePush((8453) | (first << 4));
                        else Queue.queuePush(8448 | (first << 4) | second);
                    }
                    if(seen.charAt(34) == '0') {
                        seen.setCharAt(34, '1');
                        if (first == 0) Queue.queuePush(8768);
                        else if (second == 0) Queue.queuePush((8708) | (first << 4));
                        else Queue.queuePush(8704 | (first << 4) | second);
                    }
                    break;
                case 27:
                    if(seen.charAt(19) == '0') {
                        seen.setCharAt(19, '1');
                        if (first == 0) Queue.queuePush(4992);
                        else if (second == 0) Queue.queuePush((4872) | (first << 4));
                        else Queue.queuePush(4864 | (first << 4) | second);
                    }
                    if(seen.charAt(20) == '0') {
                        seen.setCharAt(20, '1');
                        if (first == 0) Queue.queuePush(5136);
                        else if (second == 0) Queue.queuePush((5121) | (first << 4));
                        else Queue.queuePush(5120 | (first << 4) | second);
                    }
                    if(seen.charAt(26) == '0') {
                        seen.setCharAt(26, '1');
                        if (first == 0) Queue.queuePush(6768);
                        else if (second == 0) Queue.queuePush((6663) | (first << 4));
                        else Queue.queuePush(6656 | (first << 4) | second);
                    }
                    if(seen.charAt(33) == '0') {
                        seen.setCharAt(33, '1');
                        if (first == 0) Queue.queuePush(8544);
                        else if (second == 0) Queue.queuePush((8454) | (first << 4));
                        else Queue.queuePush(8448 | (first << 4) | second);
                    }
                    if(seen.charAt(34) == '0') {
                        seen.setCharAt(34, '1');
                        if (first == 0) Queue.queuePush(8784);
                        else if (second == 0) Queue.queuePush((8709) | (first << 4));
                        else Queue.queuePush(8704 | (first << 4) | second);
                    }
                    break;
                case 28:
                    if(seen.charAt(21) == '0') {
                        seen.setCharAt(21, '1');
                        if (first == 0) Queue.queuePush(5392);
                        else if (second == 0) Queue.queuePush((5377) | (first << 4));
                        else Queue.queuePush(5376 | (first << 4) | second);
                    }
                    if(seen.charAt(22) == '0') {
                        seen.setCharAt(22, '1');
                        if (first == 0) Queue.queuePush(5664);
                        else if (second == 0) Queue.queuePush((5634) | (first << 4));
                        else Queue.queuePush(5632 | (first << 4) | second);
                    }
                    if(seen.charAt(29) == '0') {
                        seen.setCharAt(29, '1');
                        if (first == 0) Queue.queuePush(7472);
                        else if (second == 0) Queue.queuePush((7427) | (first << 4));
                        else Queue.queuePush(7424 | (first << 4) | second);
                    }
                    if(seen.charAt(35) == '0') {
                        seen.setCharAt(35, '1');
                        if (first == 0) Queue.queuePush(9040);
                        else if (second == 0) Queue.queuePush((8965) | (first << 4));
                        else Queue.queuePush(8960 | (first << 4) | second);
                    }
                    if(seen.charAt(36) == '0') {
                        seen.setCharAt(36, '1');
                        if (first == 0) Queue.queuePush(9280);
                        else if (second == 0) Queue.queuePush((9220) | (first << 4));
                        else Queue.queuePush(9216 | (first << 4) | second);
                    }
                    break;
                case 29:
                    if(seen.charAt(21) == '0') {
                        seen.setCharAt(21, '1');
                        if (first == 0) Queue.queuePush(5504);
                        else if (second == 0) Queue.queuePush((5384) | (first << 4));
                        else Queue.queuePush(5376 | (first << 4) | second);
                    }
                    if(seen.charAt(22) == '0') {
                        seen.setCharAt(22, '1');
                        if (first == 0) Queue.queuePush(5648);
                        else if (second == 0) Queue.queuePush((5633) | (first << 4));
                        else Queue.queuePush(5632 | (first << 4) | second);
                    }
                    if(seen.charAt(23) == '0') {
                        seen.setCharAt(23, '1');
                        if (first == 0) Queue.queuePush(5920);
                        else if (second == 0) Queue.queuePush((5890) | (first << 4));
                        else Queue.queuePush(5888 | (first << 4) | second);
                    }
                    if(seen.charAt(28) == '0') {
                        seen.setCharAt(28, '1');
                        if (first == 0) Queue.queuePush(7280);
                        else if (second == 0) Queue.queuePush((7175) | (first << 4));
                        else Queue.queuePush(7168 | (first << 4) | second);
                    }
                    if(seen.charAt(30) == '0') {
                        seen.setCharAt(30, '1');
                        if (first == 0) Queue.queuePush(7728);
                        else if (second == 0) Queue.queuePush((7683) | (first << 4));
                        else Queue.queuePush(7680 | (first << 4) | second);
                    }
                    if(seen.charAt(35) == '0') {
                        seen.setCharAt(35, '1');
                        if (first == 0) Queue.queuePush(9056);
                        else if (second == 0) Queue.queuePush((8966) | (first << 4));
                        else Queue.queuePush(8960 | (first << 4) | second);
                    }
                    if(seen.charAt(36) == '0') {
                        seen.setCharAt(36, '1');
                        if (first == 0) Queue.queuePush(9296);
                        else if (second == 0) Queue.queuePush((9221) | (first << 4));
                        else Queue.queuePush(9216 | (first << 4) | second);
                    }
                    if(seen.charAt(37) == '0') {
                        seen.setCharAt(37, '1');
                        if (first == 0) Queue.queuePush(9536);
                        else if (second == 0) Queue.queuePush((9476) | (first << 4));
                        else Queue.queuePush(9472 | (first << 4) | second);
                    }
                    break;
                case 30:
                    if(seen.charAt(22) == '0') {
                        seen.setCharAt(22, '1');
                        if (first == 0) Queue.queuePush(5760);
                        else if (second == 0) Queue.queuePush((5640) | (first << 4));
                        else Queue.queuePush(5632 | (first << 4) | second);
                    }
                    if(seen.charAt(23) == '0') {
                        seen.setCharAt(23, '1');
                        if (first == 0) Queue.queuePush(5904);
                        else if (second == 0) Queue.queuePush((5889) | (first << 4));
                        else Queue.queuePush(5888 | (first << 4) | second);
                    }
                    if(seen.charAt(24) == '0') {
                        seen.setCharAt(24, '1');
                        if (first == 0) Queue.queuePush(6176);
                        else if (second == 0) Queue.queuePush((6146) | (first << 4));
                        else Queue.queuePush(6144 | (first << 4) | second);
                    }
                    if(seen.charAt(29) == '0') {
                        seen.setCharAt(29, '1');
                        if (first == 0) Queue.queuePush(7536);
                        else if (second == 0) Queue.queuePush((7431) | (first << 4));
                        else Queue.queuePush(7424 | (first << 4) | second);
                    }
                    if(seen.charAt(31) == '0') {
                        seen.setCharAt(31, '1');
                        if (first == 0) Queue.queuePush(7984);
                        else if (second == 0) Queue.queuePush((7939) | (first << 4));
                        else Queue.queuePush(7936 | (first << 4) | second);
                    }
                    if(seen.charAt(36) == '0') {
                        seen.setCharAt(36, '1');
                        if (first == 0) Queue.queuePush(9312);
                        else if (second == 0) Queue.queuePush((9222) | (first << 4));
                        else Queue.queuePush(9216 | (first << 4) | second);
                    }
                    if(seen.charAt(37) == '0') {
                        seen.setCharAt(37, '1');
                        if (first == 0) Queue.queuePush(9552);
                        else if (second == 0) Queue.queuePush((9477) | (first << 4));
                        else Queue.queuePush(9472 | (first << 4) | second);
                    }
                    if(seen.charAt(38) == '0') {
                        seen.setCharAt(38, '1');
                        if (first == 0) Queue.queuePush(9792);
                        else if (second == 0) Queue.queuePush((9732) | (first << 4));
                        else Queue.queuePush(9728 | (first << 4) | second);
                    }
                    break;
                case 31:
                    if(seen.charAt(23) == '0') {
                        seen.setCharAt(23, '1');
                        if (first == 0) Queue.queuePush(6016);
                        else if (second == 0) Queue.queuePush((5896) | (first << 4));
                        else Queue.queuePush(5888 | (first << 4) | second);
                    }
                    if(seen.charAt(24) == '0') {
                        seen.setCharAt(24, '1');
                        if (first == 0) Queue.queuePush(6160);
                        else if (second == 0) Queue.queuePush((6145) | (first << 4));
                        else Queue.queuePush(6144 | (first << 4) | second);
                    }
                    if(seen.charAt(25) == '0') {
                        seen.setCharAt(25, '1');
                        if (first == 0) Queue.queuePush(6432);
                        else if (second == 0) Queue.queuePush((6402) | (first << 4));
                        else Queue.queuePush(6400 | (first << 4) | second);
                    }
                    if(seen.charAt(30) == '0') {
                        seen.setCharAt(30, '1');
                        if (first == 0) Queue.queuePush(7792);
                        else if (second == 0) Queue.queuePush((7687) | (first << 4));
                        else Queue.queuePush(7680 | (first << 4) | second);
                    }
                    if(seen.charAt(32) == '0') {
                        seen.setCharAt(32, '1');
                        if (first == 0) Queue.queuePush(8240);
                        else if (second == 0) Queue.queuePush((8195) | (first << 4));
                        else Queue.queuePush(8192 | (first << 4) | second);
                    }
                    if(seen.charAt(37) == '0') {
                        seen.setCharAt(37, '1');
                        if (first == 0) Queue.queuePush(9568);
                        else if (second == 0) Queue.queuePush((9478) | (first << 4));
                        else Queue.queuePush(9472 | (first << 4) | second);
                    }
                    if(seen.charAt(38) == '0') {
                        seen.setCharAt(38, '1');
                        if (first == 0) Queue.queuePush(9808);
                        else if (second == 0) Queue.queuePush((9733) | (first << 4));
                        else Queue.queuePush(9728 | (first << 4) | second);
                    }
                    if(seen.charAt(39) == '0') {
                        seen.setCharAt(39, '1');
                        if (first == 0) Queue.queuePush(10048);
                        else if (second == 0) Queue.queuePush((9988) | (first << 4));
                        else Queue.queuePush(9984 | (first << 4) | second);
                    }
                    break;
                case 32:
                    if(seen.charAt(24) == '0') {
                        seen.setCharAt(24, '1');
                        if (first == 0) Queue.queuePush(6272);
                        else if (second == 0) Queue.queuePush((6152) | (first << 4));
                        else Queue.queuePush(6144 | (first << 4) | second);
                    }
                    if(seen.charAt(25) == '0') {
                        seen.setCharAt(25, '1');
                        if (first == 0) Queue.queuePush(6416);
                        else if (second == 0) Queue.queuePush((6401) | (first << 4));
                        else Queue.queuePush(6400 | (first << 4) | second);
                    }
                    if(seen.charAt(26) == '0') {
                        seen.setCharAt(26, '1');
                        if (first == 0) Queue.queuePush(6688);
                        else if (second == 0) Queue.queuePush((6658) | (first << 4));
                        else Queue.queuePush(6656 | (first << 4) | second);
                    }
                    if(seen.charAt(31) == '0') {
                        seen.setCharAt(31, '1');
                        if (first == 0) Queue.queuePush(8048);
                        else if (second == 0) Queue.queuePush((7943) | (first << 4));
                        else Queue.queuePush(7936 | (first << 4) | second);
                    }
                    if(seen.charAt(33) == '0') {
                        seen.setCharAt(33, '1');
                        if (first == 0) Queue.queuePush(8496);
                        else if (second == 0) Queue.queuePush((8451) | (first << 4));
                        else Queue.queuePush(8448 | (first << 4) | second);
                    }
                    if(seen.charAt(38) == '0') {
                        seen.setCharAt(38, '1');
                        if (first == 0) Queue.queuePush(9824);
                        else if (second == 0) Queue.queuePush((9734) | (first << 4));
                        else Queue.queuePush(9728 | (first << 4) | second);
                    }
                    if(seen.charAt(39) == '0') {
                        seen.setCharAt(39, '1');
                        if (first == 0) Queue.queuePush(10064);
                        else if (second == 0) Queue.queuePush((9989) | (first << 4));
                        else Queue.queuePush(9984 | (first << 4) | second);
                    }
                    if(seen.charAt(40) == '0') {
                        seen.setCharAt(40, '1');
                        if (first == 0) Queue.queuePush(10304);
                        else if (second == 0) Queue.queuePush((10244) | (first << 4));
                        else Queue.queuePush(10240 | (first << 4) | second);
                    }
                    break;
                case 33:
                    if(seen.charAt(25) == '0') {
                        seen.setCharAt(25, '1');
                        if (first == 0) Queue.queuePush(6528);
                        else if (second == 0) Queue.queuePush((6408) | (first << 4));
                        else Queue.queuePush(6400 | (first << 4) | second);
                    }
                    if(seen.charAt(26) == '0') {
                        seen.setCharAt(26, '1');
                        if (first == 0) Queue.queuePush(6672);
                        else if (second == 0) Queue.queuePush((6657) | (first << 4));
                        else Queue.queuePush(6656 | (first << 4) | second);
                    }
                    if(seen.charAt(27) == '0') {
                        seen.setCharAt(27, '1');
                        if (first == 0) Queue.queuePush(6944);
                        else if (second == 0) Queue.queuePush((6914) | (first << 4));
                        else Queue.queuePush(6912 | (first << 4) | second);
                    }
                    if(seen.charAt(32) == '0') {
                        seen.setCharAt(32, '1');
                        if (first == 0) Queue.queuePush(8304);
                        else if (second == 0) Queue.queuePush((8199) | (first << 4));
                        else Queue.queuePush(8192 | (first << 4) | second);
                    }
                    if(seen.charAt(34) == '0') {
                        seen.setCharAt(34, '1');
                        if (first == 0) Queue.queuePush(8752);
                        else if (second == 0) Queue.queuePush((8707) | (first << 4));
                        else Queue.queuePush(8704 | (first << 4) | second);
                    }
                    if(seen.charAt(39) == '0') {
                        seen.setCharAt(39, '1');
                        if (first == 0) Queue.queuePush(10080);
                        else if (second == 0) Queue.queuePush((9990) | (first << 4));
                        else Queue.queuePush(9984 | (first << 4) | second);
                    }
                    if(seen.charAt(40) == '0') {
                        seen.setCharAt(40, '1');
                        if (first == 0) Queue.queuePush(10320);
                        else if (second == 0) Queue.queuePush((10245) | (first << 4));
                        else Queue.queuePush(10240 | (first << 4) | second);
                    }
                    if(seen.charAt(41) == '0') {
                        seen.setCharAt(41, '1');
                        if (first == 0) Queue.queuePush(10560);
                        else if (second == 0) Queue.queuePush((10500) | (first << 4));
                        else Queue.queuePush(10496 | (first << 4) | second);
                    }
                    break;
                case 34:
                    if(seen.charAt(26) == '0') {
                        seen.setCharAt(26, '1');
                        if (first == 0) Queue.queuePush(6784);
                        else if (second == 0) Queue.queuePush((6664) | (first << 4));
                        else Queue.queuePush(6656 | (first << 4) | second);
                    }
                    if(seen.charAt(27) == '0') {
                        seen.setCharAt(27, '1');
                        if (first == 0) Queue.queuePush(6928);
                        else if (second == 0) Queue.queuePush((6913) | (first << 4));
                        else Queue.queuePush(6912 | (first << 4) | second);
                    }
                    if(seen.charAt(33) == '0') {
                        seen.setCharAt(33, '1');
                        if (first == 0) Queue.queuePush(8560);
                        else if (second == 0) Queue.queuePush((8455) | (first << 4));
                        else Queue.queuePush(8448 | (first << 4) | second);
                    }
                    if(seen.charAt(40) == '0') {
                        seen.setCharAt(40, '1');
                        if (first == 0) Queue.queuePush(10336);
                        else if (second == 0) Queue.queuePush((10246) | (first << 4));
                        else Queue.queuePush(10240 | (first << 4) | second);
                    }
                    if(seen.charAt(41) == '0') {
                        seen.setCharAt(41, '1');
                        if (first == 0) Queue.queuePush(10576);
                        else if (second == 0) Queue.queuePush((10501) | (first << 4));
                        else Queue.queuePush(10496 | (first << 4) | second);
                    }
                    break;
                case 35:
                    if(seen.charAt(28) == '0') {
                        seen.setCharAt(28, '1');
                        if (first == 0) Queue.queuePush(7184);
                        else if (second == 0) Queue.queuePush((7169) | (first << 4));
                        else Queue.queuePush(7168 | (first << 4) | second);
                    }
                    if(seen.charAt(29) == '0') {
                        seen.setCharAt(29, '1');
                        if (first == 0) Queue.queuePush(7456);
                        else if (second == 0) Queue.queuePush((7426) | (first << 4));
                        else Queue.queuePush(7424 | (first << 4) | second);
                    }
                    if(seen.charAt(36) == '0') {
                        seen.setCharAt(36, '1');
                        if (first == 0) Queue.queuePush(9264);
                        else if (second == 0) Queue.queuePush((9219) | (first << 4));
                        else Queue.queuePush(9216 | (first << 4) | second);
                    }
                    if(seen.charAt(43) == '0') {
                        seen.setCharAt(43, '1');
                        if (first == 0) Queue.queuePush(11072);
                        else if (second == 0) Queue.queuePush((11012) | (first << 4));
                        else Queue.queuePush(11008 | (first << 4) | second);
                    }
                    break;
                case 36:
                    if(seen.charAt(28) == '0') {
                        seen.setCharAt(28, '1');
                        if (first == 0) Queue.queuePush(7296);
                        else if (second == 0) Queue.queuePush((7176) | (first << 4));
                        else Queue.queuePush(7168 | (first << 4) | second);
                    }
                    if(seen.charAt(29) == '0') {
                        seen.setCharAt(29, '1');
                        if (first == 0) Queue.queuePush(7440);
                        else if (second == 0) Queue.queuePush((7425) | (first << 4));
                        else Queue.queuePush(7424 | (first << 4) | second);
                    }
                    if(seen.charAt(30) == '0') {
                        seen.setCharAt(30, '1');
                        if (first == 0) Queue.queuePush(7712);
                        else if (second == 0) Queue.queuePush((7682) | (first << 4));
                        else Queue.queuePush(7680 | (first << 4) | second);
                    }
                    if(seen.charAt(35) == '0') {
                        seen.setCharAt(35, '1');
                        if (first == 0) Queue.queuePush(9072);
                        else if (second == 0) Queue.queuePush((8967) | (first << 4));
                        else Queue.queuePush(8960 | (first << 4) | second);
                    }
                    if(seen.charAt(37) == '0') {
                        seen.setCharAt(37, '1');
                        if (first == 0) Queue.queuePush(9520);
                        else if (second == 0) Queue.queuePush((9475) | (first << 4));
                        else Queue.queuePush(9472 | (first << 4) | second);
                    }
                    if(seen.charAt(43) == '0') {
                        seen.setCharAt(43, '1');
                        if (first == 0) Queue.queuePush(11088);
                        else if (second == 0) Queue.queuePush((11013) | (first << 4));
                        else Queue.queuePush(11008 | (first << 4) | second);
                    }
                    if(seen.charAt(44) == '0') {
                        seen.setCharAt(44, '1');
                        if (first == 0) Queue.queuePush(11328);
                        else if (second == 0) Queue.queuePush((11268) | (first << 4));
                        else Queue.queuePush(11264 | (first << 4) | second);
                    }
                    break;
                case 37:
                    if(seen.charAt(29) == '0') {
                        seen.setCharAt(29, '1');
                        if (first == 0) Queue.queuePush(7552);
                        else if (second == 0) Queue.queuePush((7432) | (first << 4));
                        else Queue.queuePush(7424 | (first << 4) | second);
                    }
                    if(seen.charAt(30) == '0') {
                        seen.setCharAt(30, '1');
                        if (first == 0) Queue.queuePush(7696);
                        else if (second == 0) Queue.queuePush((7681) | (first << 4));
                        else Queue.queuePush(7680 | (first << 4) | second);
                    }
                    if(seen.charAt(31) == '0') {
                        seen.setCharAt(31, '1');
                        if (first == 0) Queue.queuePush(7968);
                        else if (second == 0) Queue.queuePush((7938) | (first << 4));
                        else Queue.queuePush(7936 | (first << 4) | second);
                    }
                    if(seen.charAt(36) == '0') {
                        seen.setCharAt(36, '1');
                        if (first == 0) Queue.queuePush(9328);
                        else if (second == 0) Queue.queuePush((9223) | (first << 4));
                        else Queue.queuePush(9216 | (first << 4) | second);
                    }
                    if(seen.charAt(38) == '0') {
                        seen.setCharAt(38, '1');
                        if (first == 0) Queue.queuePush(9776);
                        else if (second == 0) Queue.queuePush((9731) | (first << 4));
                        else Queue.queuePush(9728 | (first << 4) | second);
                    }
                    if(seen.charAt(43) == '0') {
                        seen.setCharAt(43, '1');
                        if (first == 0) Queue.queuePush(11104);
                        else if (second == 0) Queue.queuePush((11014) | (first << 4));
                        else Queue.queuePush(11008 | (first << 4) | second);
                    }
                    if(seen.charAt(44) == '0') {
                        seen.setCharAt(44, '1');
                        if (first == 0) Queue.queuePush(11344);
                        else if (second == 0) Queue.queuePush((11269) | (first << 4));
                        else Queue.queuePush(11264 | (first << 4) | second);
                    }
                    if(seen.charAt(45) == '0') {
                        seen.setCharAt(45, '1');
                        if (first == 0) Queue.queuePush(11584);
                        else if (second == 0) Queue.queuePush((11524) | (first << 4));
                        else Queue.queuePush(11520 | (first << 4) | second);
                    }
                    break;
                case 38:
                    if(seen.charAt(30) == '0') {
                        seen.setCharAt(30, '1');
                        if (first == 0) Queue.queuePush(7808);
                        else if (second == 0) Queue.queuePush((7688) | (first << 4));
                        else Queue.queuePush(7680 | (first << 4) | second);
                    }
                    if(seen.charAt(31) == '0') {
                        seen.setCharAt(31, '1');
                        if (first == 0) Queue.queuePush(7952);
                        else if (second == 0) Queue.queuePush((7937) | (first << 4));
                        else Queue.queuePush(7936 | (first << 4) | second);
                    }
                    if(seen.charAt(32) == '0') {
                        seen.setCharAt(32, '1');
                        if (first == 0) Queue.queuePush(8224);
                        else if (second == 0) Queue.queuePush((8194) | (first << 4));
                        else Queue.queuePush(8192 | (first << 4) | second);
                    }
                    if(seen.charAt(37) == '0') {
                        seen.setCharAt(37, '1');
                        if (first == 0) Queue.queuePush(9584);
                        else if (second == 0) Queue.queuePush((9479) | (first << 4));
                        else Queue.queuePush(9472 | (first << 4) | second);
                    }
                    if(seen.charAt(39) == '0') {
                        seen.setCharAt(39, '1');
                        if (first == 0) Queue.queuePush(10032);
                        else if (second == 0) Queue.queuePush((9987) | (first << 4));
                        else Queue.queuePush(9984 | (first << 4) | second);
                    }
                    if(seen.charAt(44) == '0') {
                        seen.setCharAt(44, '1');
                        if (first == 0) Queue.queuePush(11360);
                        else if (second == 0) Queue.queuePush((11270) | (first << 4));
                        else Queue.queuePush(11264 | (first << 4) | second);
                    }
                    if(seen.charAt(45) == '0') {
                        seen.setCharAt(45, '1');
                        if (first == 0) Queue.queuePush(11600);
                        else if (second == 0) Queue.queuePush((11525) | (first << 4));
                        else Queue.queuePush(11520 | (first << 4) | second);
                    }
                    if(seen.charAt(46) == '0') {
                        seen.setCharAt(46, '1');
                        if (first == 0) Queue.queuePush(11840);
                        else if (second == 0) Queue.queuePush((11780) | (first << 4));
                        else Queue.queuePush(11776 | (first << 4) | second);
                    }
                    break;
                case 39:
                    if(seen.charAt(31) == '0') {
                        seen.setCharAt(31, '1');
                        if (first == 0) Queue.queuePush(8064);
                        else if (second == 0) Queue.queuePush((7944) | (first << 4));
                        else Queue.queuePush(7936 | (first << 4) | second);
                    }
                    if(seen.charAt(32) == '0') {
                        seen.setCharAt(32, '1');
                        if (first == 0) Queue.queuePush(8208);
                        else if (second == 0) Queue.queuePush((8193) | (first << 4));
                        else Queue.queuePush(8192 | (first << 4) | second);
                    }
                    if(seen.charAt(33) == '0') {
                        seen.setCharAt(33, '1');
                        if (first == 0) Queue.queuePush(8480);
                        else if (second == 0) Queue.queuePush((8450) | (first << 4));
                        else Queue.queuePush(8448 | (first << 4) | second);
                    }
                    if(seen.charAt(38) == '0') {
                        seen.setCharAt(38, '1');
                        if (first == 0) Queue.queuePush(9840);
                        else if (second == 0) Queue.queuePush((9735) | (first << 4));
                        else Queue.queuePush(9728 | (first << 4) | second);
                    }
                    if(seen.charAt(40) == '0') {
                        seen.setCharAt(40, '1');
                        if (first == 0) Queue.queuePush(10288);
                        else if (second == 0) Queue.queuePush((10243) | (first << 4));
                        else Queue.queuePush(10240 | (first << 4) | second);
                    }
                    if(seen.charAt(45) == '0') {
                        seen.setCharAt(45, '1');
                        if (first == 0) Queue.queuePush(11616);
                        else if (second == 0) Queue.queuePush((11526) | (first << 4));
                        else Queue.queuePush(11520 | (first << 4) | second);
                    }
                    if(seen.charAt(46) == '0') {
                        seen.setCharAt(46, '1');
                        if (first == 0) Queue.queuePush(11856);
                        else if (second == 0) Queue.queuePush((11781) | (first << 4));
                        else Queue.queuePush(11776 | (first << 4) | second);
                    }
                    if(seen.charAt(47) == '0') {
                        seen.setCharAt(47, '1');
                        if (first == 0) Queue.queuePush(12096);
                        else if (second == 0) Queue.queuePush((12036) | (first << 4));
                        else Queue.queuePush(12032 | (first << 4) | second);
                    }
                    break;
                case 40:
                    if(seen.charAt(32) == '0') {
                        seen.setCharAt(32, '1');
                        if (first == 0) Queue.queuePush(8320);
                        else if (second == 0) Queue.queuePush((8200) | (first << 4));
                        else Queue.queuePush(8192 | (first << 4) | second);
                    }
                    if(seen.charAt(33) == '0') {
                        seen.setCharAt(33, '1');
                        if (first == 0) Queue.queuePush(8464);
                        else if (second == 0) Queue.queuePush((8449) | (first << 4));
                        else Queue.queuePush(8448 | (first << 4) | second);
                    }
                    if(seen.charAt(34) == '0') {
                        seen.setCharAt(34, '1');
                        if (first == 0) Queue.queuePush(8736);
                        else if (second == 0) Queue.queuePush((8706) | (first << 4));
                        else Queue.queuePush(8704 | (first << 4) | second);
                    }
                    if(seen.charAt(39) == '0') {
                        seen.setCharAt(39, '1');
                        if (first == 0) Queue.queuePush(10096);
                        else if (second == 0) Queue.queuePush((9991) | (first << 4));
                        else Queue.queuePush(9984 | (first << 4) | second);
                    }
                    if(seen.charAt(41) == '0') {
                        seen.setCharAt(41, '1');
                        if (first == 0) Queue.queuePush(10544);
                        else if (second == 0) Queue.queuePush((10499) | (first << 4));
                        else Queue.queuePush(10496 | (first << 4) | second);
                    }
                    if(seen.charAt(46) == '0') {
                        seen.setCharAt(46, '1');
                        if (first == 0) Queue.queuePush(11872);
                        else if (second == 0) Queue.queuePush((11782) | (first << 4));
                        else Queue.queuePush(11776 | (first << 4) | second);
                    }
                    if(seen.charAt(47) == '0') {
                        seen.setCharAt(47, '1');
                        if (first == 0) Queue.queuePush(12112);
                        else if (second == 0) Queue.queuePush((12037) | (first << 4));
                        else Queue.queuePush(12032 | (first << 4) | second);
                    }
                    break;
                case 41:
                    if(seen.charAt(33) == '0') {
                        seen.setCharAt(33, '1');
                        if (first == 0) Queue.queuePush(8576);
                        else if (second == 0) Queue.queuePush((8456) | (first << 4));
                        else Queue.queuePush(8448 | (first << 4) | second);
                    }
                    if(seen.charAt(34) == '0') {
                        seen.setCharAt(34, '1');
                        if (first == 0) Queue.queuePush(8720);
                        else if (second == 0) Queue.queuePush((8705) | (first << 4));
                        else Queue.queuePush(8704 | (first << 4) | second);
                    }
                    if(seen.charAt(40) == '0') {
                        seen.setCharAt(40, '1');
                        if (first == 0) Queue.queuePush(10352);
                        else if (second == 0) Queue.queuePush((10247) | (first << 4));
                        else Queue.queuePush(10240 | (first << 4) | second);
                    }
                    if(seen.charAt(47) == '0') {
                        seen.setCharAt(47, '1');
                        if (first == 0) Queue.queuePush(12128);
                        else if (second == 0) Queue.queuePush((12038) | (first << 4));
                        else Queue.queuePush(12032 | (first << 4) | second);
                    }
                    break;
                case 43:
                    if(seen.charAt(35) == '0') {
                        seen.setCharAt(35, '1');
                        if (first == 0) Queue.queuePush(9088);
                        else if (second == 0) Queue.queuePush((8968) | (first << 4));
                        else Queue.queuePush(8960 | (first << 4) | second);
                    }
                    if(seen.charAt(36) == '0') {
                        seen.setCharAt(36, '1');
                        if (first == 0) Queue.queuePush(9232);
                        else if (second == 0) Queue.queuePush((9217) | (first << 4));
                        else Queue.queuePush(9216 | (first << 4) | second);
                    }
                    if(seen.charAt(37) == '0') {
                        seen.setCharAt(37, '1');
                        if (first == 0) Queue.queuePush(9504);
                        else if (second == 0) Queue.queuePush((9474) | (first << 4));
                        else Queue.queuePush(9472 | (first << 4) | second);
                    }
                    if(seen.charAt(44) == '0') {
                        seen.setCharAt(44, '1');
                        if (first == 0) Queue.queuePush(11312);
                        else if (second == 0) Queue.queuePush((11267) | (first << 4));
                        else Queue.queuePush(11264 | (first << 4) | second);
                    }
                    break;
                case 44:
                    if(seen.charAt(36) == '0') {
                        seen.setCharAt(36, '1');
                        if (first == 0) Queue.queuePush(9344);
                        else if (second == 0) Queue.queuePush((9224) | (first << 4));
                        else Queue.queuePush(9216 | (first << 4) | second);
                    }
                    if(seen.charAt(37) == '0') {
                        seen.setCharAt(37, '1');
                        if (first == 0) Queue.queuePush(9488);
                        else if (second == 0) Queue.queuePush((9473) | (first << 4));
                        else Queue.queuePush(9472 | (first << 4) | second);
                    }
                    if(seen.charAt(38) == '0') {
                        seen.setCharAt(38, '1');
                        if (first == 0) Queue.queuePush(9760);
                        else if (second == 0) Queue.queuePush((9730) | (first << 4));
                        else Queue.queuePush(9728 | (first << 4) | second);
                    }
                    if(seen.charAt(43) == '0') {
                        seen.setCharAt(43, '1');
                        if (first == 0) Queue.queuePush(11120);
                        else if (second == 0) Queue.queuePush((11015) | (first << 4));
                        else Queue.queuePush(11008 | (first << 4) | second);
                    }
                    if(seen.charAt(45) == '0') {
                        seen.setCharAt(45, '1');
                        if (first == 0) Queue.queuePush(11568);
                        else if (second == 0) Queue.queuePush((11523) | (first << 4));
                        else Queue.queuePush(11520 | (first << 4) | second);
                    }
                    break;
                case 45:
                    if(seen.charAt(37) == '0') {
                        seen.setCharAt(37, '1');
                        if (first == 0) Queue.queuePush(9600);
                        else if (second == 0) Queue.queuePush((9480) | (first << 4));
                        else Queue.queuePush(9472 | (first << 4) | second);
                    }
                    if(seen.charAt(38) == '0') {
                        seen.setCharAt(38, '1');
                        if (first == 0) Queue.queuePush(9744);
                        else if (second == 0) Queue.queuePush((9729) | (first << 4));
                        else Queue.queuePush(9728 | (first << 4) | second);
                    }
                    if(seen.charAt(39) == '0') {
                        seen.setCharAt(39, '1');
                        if (first == 0) Queue.queuePush(10016);
                        else if (second == 0) Queue.queuePush((9986) | (first << 4));
                        else Queue.queuePush(9984 | (first << 4) | second);
                    }
                    if(seen.charAt(44) == '0') {
                        seen.setCharAt(44, '1');
                        if (first == 0) Queue.queuePush(11376);
                        else if (second == 0) Queue.queuePush((11271) | (first << 4));
                        else Queue.queuePush(11264 | (first << 4) | second);
                    }
                    if(seen.charAt(46) == '0') {
                        seen.setCharAt(46, '1');
                        if (first == 0) Queue.queuePush(11824);
                        else if (second == 0) Queue.queuePush((11779) | (first << 4));
                        else Queue.queuePush(11776 | (first << 4) | second);
                    }
                    break;
                case 46:
                    if(seen.charAt(38) == '0') {
                        seen.setCharAt(38, '1');
                        if (first == 0) Queue.queuePush(9856);
                        else if (second == 0) Queue.queuePush((9736) | (first << 4));
                        else Queue.queuePush(9728 | (first << 4) | second);
                    }
                    if(seen.charAt(39) == '0') {
                        seen.setCharAt(39, '1');
                        if (first == 0) Queue.queuePush(10000);
                        else if (second == 0) Queue.queuePush((9985) | (first << 4));
                        else Queue.queuePush(9984 | (first << 4) | second);
                    }
                    if(seen.charAt(40) == '0') {
                        seen.setCharAt(40, '1');
                        if (first == 0) Queue.queuePush(10272);
                        else if (second == 0) Queue.queuePush((10242) | (first << 4));
                        else Queue.queuePush(10240 | (first << 4) | second);
                    }
                    if(seen.charAt(45) == '0') {
                        seen.setCharAt(45, '1');
                        if (first == 0) Queue.queuePush(11632);
                        else if (second == 0) Queue.queuePush((11527) | (first << 4));
                        else Queue.queuePush(11520 | (first << 4) | second);
                    }
                    if(seen.charAt(47) == '0') {
                        seen.setCharAt(47, '1');
                        if (first == 0) Queue.queuePush(12080);
                        else if (second == 0) Queue.queuePush((12035) | (first << 4));
                        else Queue.queuePush(12032 | (first << 4) | second);
                    }
                    break;
                case 47:
                    if(seen.charAt(39) == '0') {
                        seen.setCharAt(39, '1');
                        if (first == 0) Queue.queuePush(10112);
                        else if (second == 0) Queue.queuePush((9992) | (first << 4));
                        else Queue.queuePush(9984 | (first << 4) | second);
                    }
                    if(seen.charAt(40) == '0') {
                        seen.setCharAt(40, '1');
                        if (first == 0) Queue.queuePush(10256);
                        else if (second == 0) Queue.queuePush((10241) | (first << 4));
                        else Queue.queuePush(10240 | (first << 4) | second);
                    }
                    if(seen.charAt(41) == '0') {
                        seen.setCharAt(41, '1');
                        if (first == 0) Queue.queuePush(10528);
                        else if (second == 0) Queue.queuePush((10498) | (first << 4));
                        else Queue.queuePush(10496 | (first << 4) | second);
                    }
                    if(seen.charAt(46) == '0') {
                        seen.setCharAt(46, '1');
                        if (first == 0) Queue.queuePush(11888);
                        else if (second == 0) Queue.queuePush((11783) | (first << 4));
                        else Queue.queuePush(11776 | (first << 4) | second);
                    }
                    break;
            }
        }
    }
}
