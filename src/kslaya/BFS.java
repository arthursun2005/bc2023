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
            seen.setCharAt((loc.x - curLoc.x + 3) * 7 + (loc.y - curLoc.y + 3), '1');
        }

        for (int i = cur; i >= Math.max(0, cur - 7); i--) {
            if ((path[i].x - curLoc.x) * (path[i].x - curLoc.x) + (path[i].y - curLoc.y) * (path[i].y - curLoc.y) > 15)
            continue;
            stb.append("^" + ((path[i].x - curLoc.x + 3) * 7 + (path[i].y - curLoc.y + 3)));
            if (rc.getLocation().distanceSquaredTo(path[i]) <= 8 && rc.canSenseLocation(path[i]) && rc.senseRobotAtLocation(path[i]) != null)
            continue;
            seen.setCharAt((path[i].x - curLoc.x + 3) * 7 + (path[i].y - curLoc.y + 3), '0');
        }

        seen.setCharAt(24, '1');
        Queue.queuePush(24 << 6);


        while (!Queue.queueEmpty()) {
            int current = Queue.queuePop();
            int first = (current >> 3) & 7;
            int second = (current & 7);
            int idx = current >> 6;

            if (stb.indexOf(String.valueOf(idx)) != -1) {
                result.append("^" + (idx) + ":" + (first) + "" + (second));
            }

            switch(idx) {
                case 1:
                if(seen.charAt(2) == '0') {
                    seen.setCharAt(2, '1');
                    if (first == 0) Queue.queuePush(152);
                    else if (second == 0) Queue.queuePush((131) | (first << 3));
                    else Queue.queuePush(2 | (first << 3) | second);
                }
                if(seen.charAt(7) == '0') {
                    seen.setCharAt(7, '1');
                    if (first == 0) Queue.queuePush(496);
                    else if (second == 0) Queue.queuePush((454) | (first << 3));
                    else Queue.queuePush(7 | (first << 3) | second);
                }
                if(seen.charAt(8) == '0') {
                    seen.setCharAt(8, '1');
                    if (first == 0) Queue.queuePush(552);
                    else if (second == 0) Queue.queuePush((517) | (first << 3));
                    else Queue.queuePush(8 | (first << 3) | second);
                }
                if(seen.charAt(9) == '0') {
                    seen.setCharAt(9, '1');
                    if (first == 0) Queue.queuePush(608);
                    else if (second == 0) Queue.queuePush((580) | (first << 3));
                    else Queue.queuePush(9 | (first << 3) | second);
                }
                break;
                case 2:
                if(seen.charAt(1) == '0') {
                    seen.setCharAt(1, '1');
                    if (first == 0) Queue.queuePush(120);
                    else if (second == 0) Queue.queuePush((71) | (first << 3));
                    else Queue.queuePush(1 | (first << 3) | second);
                }
                if(seen.charAt(3) == '0') {
                    seen.setCharAt(3, '1');
                    if (first == 0) Queue.queuePush(216);
                    else if (second == 0) Queue.queuePush((195) | (first << 3));
                    else Queue.queuePush(3 | (first << 3) | second);
                }
                if(seen.charAt(8) == '0') {
                    seen.setCharAt(8, '1');
                    if (first == 0) Queue.queuePush(560);
                    else if (second == 0) Queue.queuePush((518) | (first << 3));
                    else Queue.queuePush(8 | (first << 3) | second);
                }
                if(seen.charAt(9) == '0') {
                    seen.setCharAt(9, '1');
                    if (first == 0) Queue.queuePush(616);
                    else if (second == 0) Queue.queuePush((581) | (first << 3));
                    else Queue.queuePush(9 | (first << 3) | second);
                }
                if(seen.charAt(10) == '0') {
                    seen.setCharAt(10, '1');
                    if (first == 0) Queue.queuePush(672);
                    else if (second == 0) Queue.queuePush((644) | (first << 3));
                    else Queue.queuePush(10 | (first << 3) | second);
                }
                break;
                case 3:
                if(seen.charAt(2) == '0') {
                    seen.setCharAt(2, '1');
                    if (first == 0) Queue.queuePush(184);
                    else if (second == 0) Queue.queuePush((135) | (first << 3));
                    else Queue.queuePush(2 | (first << 3) | second);
                }
                if(seen.charAt(4) == '0') {
                    seen.setCharAt(4, '1');
                    if (first == 0) Queue.queuePush(280);
                    else if (second == 0) Queue.queuePush((259) | (first << 3));
                    else Queue.queuePush(4 | (first << 3) | second);
                }
                if(seen.charAt(9) == '0') {
                    seen.setCharAt(9, '1');
                    if (first == 0) Queue.queuePush(624);
                    else if (second == 0) Queue.queuePush((582) | (first << 3));
                    else Queue.queuePush(9 | (first << 3) | second);
                }
                if(seen.charAt(10) == '0') {
                    seen.setCharAt(10, '1');
                    if (first == 0) Queue.queuePush(680);
                    else if (second == 0) Queue.queuePush((645) | (first << 3));
                    else Queue.queuePush(10 | (first << 3) | second);
                }
                if(seen.charAt(11) == '0') {
                    seen.setCharAt(11, '1');
                    if (first == 0) Queue.queuePush(736);
                    else if (second == 0) Queue.queuePush((708) | (first << 3));
                    else Queue.queuePush(11 | (first << 3) | second);
                }
                break;
                case 4:
                if(seen.charAt(3) == '0') {
                    seen.setCharAt(3, '1');
                    if (first == 0) Queue.queuePush(248);
                    else if (second == 0) Queue.queuePush((199) | (first << 3));
                    else Queue.queuePush(3 | (first << 3) | second);
                }
                if(seen.charAt(5) == '0') {
                    seen.setCharAt(5, '1');
                    if (first == 0) Queue.queuePush(344);
                    else if (second == 0) Queue.queuePush((323) | (first << 3));
                    else Queue.queuePush(5 | (first << 3) | second);
                }
                if(seen.charAt(10) == '0') {
                    seen.setCharAt(10, '1');
                    if (first == 0) Queue.queuePush(688);
                    else if (second == 0) Queue.queuePush((646) | (first << 3));
                    else Queue.queuePush(10 | (first << 3) | second);
                }
                if(seen.charAt(11) == '0') {
                    seen.setCharAt(11, '1');
                    if (first == 0) Queue.queuePush(744);
                    else if (second == 0) Queue.queuePush((709) | (first << 3));
                    else Queue.queuePush(11 | (first << 3) | second);
                }
                if(seen.charAt(12) == '0') {
                    seen.setCharAt(12, '1');
                    if (first == 0) Queue.queuePush(800);
                    else if (second == 0) Queue.queuePush((772) | (first << 3));
                    else Queue.queuePush(12 | (first << 3) | second);
                }
                break;
                case 5:
                if(seen.charAt(4) == '0') {
                    seen.setCharAt(4, '1');
                    if (first == 0) Queue.queuePush(312);
                    else if (second == 0) Queue.queuePush((263) | (first << 3));
                    else Queue.queuePush(4 | (first << 3) | second);
                }
                if(seen.charAt(11) == '0') {
                    seen.setCharAt(11, '1');
                    if (first == 0) Queue.queuePush(752);
                    else if (second == 0) Queue.queuePush((710) | (first << 3));
                    else Queue.queuePush(11 | (first << 3) | second);
                }
                if(seen.charAt(12) == '0') {
                    seen.setCharAt(12, '1');
                    if (first == 0) Queue.queuePush(808);
                    else if (second == 0) Queue.queuePush((773) | (first << 3));
                    else Queue.queuePush(12 | (first << 3) | second);
                }
                if(seen.charAt(13) == '0') {
                    seen.setCharAt(13, '1');
                    if (first == 0) Queue.queuePush(864);
                    else if (second == 0) Queue.queuePush((836) | (first << 3));
                    else Queue.queuePush(13 | (first << 3) | second);
                }
                break;
                case 7:
                if(seen.charAt(1) == '0') {
                    seen.setCharAt(1, '1');
                    if (first == 0) Queue.queuePush(80);
                    else if (second == 0) Queue.queuePush((66) | (first << 3));
                    else Queue.queuePush(1 | (first << 3) | second);
                }
                if(seen.charAt(8) == '0') {
                    seen.setCharAt(8, '1');
                    if (first == 0) Queue.queuePush(536);
                    else if (second == 0) Queue.queuePush((515) | (first << 3));
                    else Queue.queuePush(8 | (first << 3) | second);
                }
                if(seen.charAt(14) == '0') {
                    seen.setCharAt(14, '1');
                    if (first == 0) Queue.queuePush(936);
                    else if (second == 0) Queue.queuePush((901) | (first << 3));
                    else Queue.queuePush(14 | (first << 3) | second);
                }
                if(seen.charAt(15) == '0') {
                    seen.setCharAt(15, '1');
                    if (first == 0) Queue.queuePush(992);
                    else if (second == 0) Queue.queuePush((964) | (first << 3));
                    else Queue.queuePush(15 | (first << 3) | second);
                }
                break;
                case 8:
                if(seen.charAt(1) == '0') {
                    seen.setCharAt(1, '1');
                    if (first == 0) Queue.queuePush(72);
                    else if (second == 0) Queue.queuePush((65) | (first << 3));
                    else Queue.queuePush(1 | (first << 3) | second);
                }
                if(seen.charAt(2) == '0') {
                    seen.setCharAt(2, '1');
                    if (first == 0) Queue.queuePush(144);
                    else if (second == 0) Queue.queuePush((130) | (first << 3));
                    else Queue.queuePush(2 | (first << 3) | second);
                }
                if(seen.charAt(7) == '0') {
                    seen.setCharAt(7, '1');
                    if (first == 0) Queue.queuePush(504);
                    else if (second == 0) Queue.queuePush((455) | (first << 3));
                    else Queue.queuePush(7 | (first << 3) | second);
                }
                if(seen.charAt(9) == '0') {
                    seen.setCharAt(9, '1');
                    if (first == 0) Queue.queuePush(600);
                    else if (second == 0) Queue.queuePush((579) | (first << 3));
                    else Queue.queuePush(9 | (first << 3) | second);
                }
                if(seen.charAt(14) == '0') {
                    seen.setCharAt(14, '1');
                    if (first == 0) Queue.queuePush(944);
                    else if (second == 0) Queue.queuePush((902) | (first << 3));
                    else Queue.queuePush(14 | (first << 3) | second);
                }
                if(seen.charAt(15) == '0') {
                    seen.setCharAt(15, '1');
                    if (first == 0) Queue.queuePush(1000);
                    else if (second == 0) Queue.queuePush((965) | (first << 3));
                    else Queue.queuePush(15 | (first << 3) | second);
                }
                if(seen.charAt(16) == '0') {
                    seen.setCharAt(16, '1');
                    if (first == 0) Queue.queuePush(1056);
                    else if (second == 0) Queue.queuePush((1028) | (first << 3));
                    else Queue.queuePush(16 | (first << 3) | second);
                }
                break;
                case 9:
                if(seen.charAt(1) == '0') {
                    seen.setCharAt(1, '1');
                    if (first == 0) Queue.queuePush(64);
                    else if (second == 0) Queue.queuePush((72) | (first << 3));
                    else Queue.queuePush(1 | (first << 3) | second);
                }
                if(seen.charAt(2) == '0') {
                    seen.setCharAt(2, '1');
                    if (first == 0) Queue.queuePush(136);
                    else if (second == 0) Queue.queuePush((129) | (first << 3));
                    else Queue.queuePush(2 | (first << 3) | second);
                }
                if(seen.charAt(3) == '0') {
                    seen.setCharAt(3, '1');
                    if (first == 0) Queue.queuePush(208);
                    else if (second == 0) Queue.queuePush((194) | (first << 3));
                    else Queue.queuePush(3 | (first << 3) | second);
                }
                if(seen.charAt(8) == '0') {
                    seen.setCharAt(8, '1');
                    if (first == 0) Queue.queuePush(568);
                    else if (second == 0) Queue.queuePush((519) | (first << 3));
                    else Queue.queuePush(8 | (first << 3) | second);
                }
                if(seen.charAt(10) == '0') {
                    seen.setCharAt(10, '1');
                    if (first == 0) Queue.queuePush(664);
                    else if (second == 0) Queue.queuePush((643) | (first << 3));
                    else Queue.queuePush(10 | (first << 3) | second);
                }
                if(seen.charAt(15) == '0') {
                    seen.setCharAt(15, '1');
                    if (first == 0) Queue.queuePush(1008);
                    else if (second == 0) Queue.queuePush((966) | (first << 3));
                    else Queue.queuePush(15 | (first << 3) | second);
                }
                if(seen.charAt(16) == '0') {
                    seen.setCharAt(16, '1');
                    if (first == 0) Queue.queuePush(1064);
                    else if (second == 0) Queue.queuePush((1029) | (first << 3));
                    else Queue.queuePush(16 | (first << 3) | second);
                }
                if(seen.charAt(17) == '0') {
                    seen.setCharAt(17, '1');
                    if (first == 0) Queue.queuePush(1120);
                    else if (second == 0) Queue.queuePush((1092) | (first << 3));
                    else Queue.queuePush(17 | (first << 3) | second);
                }
                break;
                case 10:
                if(seen.charAt(2) == '0') {
                    seen.setCharAt(2, '1');
                    if (first == 0) Queue.queuePush(192);
                    else if (second == 0) Queue.queuePush((136) | (first << 3));
                    else Queue.queuePush(2 | (first << 3) | second);
                }
                if(seen.charAt(3) == '0') {
                    seen.setCharAt(3, '1');
                    if (first == 0) Queue.queuePush(200);
                    else if (second == 0) Queue.queuePush((193) | (first << 3));
                    else Queue.queuePush(3 | (first << 3) | second);
                }
                if(seen.charAt(4) == '0') {
                    seen.setCharAt(4, '1');
                    if (first == 0) Queue.queuePush(272);
                    else if (second == 0) Queue.queuePush((258) | (first << 3));
                    else Queue.queuePush(4 | (first << 3) | second);
                }
                if(seen.charAt(9) == '0') {
                    seen.setCharAt(9, '1');
                    if (first == 0) Queue.queuePush(632);
                    else if (second == 0) Queue.queuePush((583) | (first << 3));
                    else Queue.queuePush(9 | (first << 3) | second);
                }
                if(seen.charAt(11) == '0') {
                    seen.setCharAt(11, '1');
                    if (first == 0) Queue.queuePush(728);
                    else if (second == 0) Queue.queuePush((707) | (first << 3));
                    else Queue.queuePush(11 | (first << 3) | second);
                }
                if(seen.charAt(16) == '0') {
                    seen.setCharAt(16, '1');
                    if (first == 0) Queue.queuePush(1072);
                    else if (second == 0) Queue.queuePush((1030) | (first << 3));
                    else Queue.queuePush(16 | (first << 3) | second);
                }
                if(seen.charAt(17) == '0') {
                    seen.setCharAt(17, '1');
                    if (first == 0) Queue.queuePush(1128);
                    else if (second == 0) Queue.queuePush((1093) | (first << 3));
                    else Queue.queuePush(17 | (first << 3) | second);
                }
                if(seen.charAt(18) == '0') {
                    seen.setCharAt(18, '1');
                    if (first == 0) Queue.queuePush(1184);
                    else if (second == 0) Queue.queuePush((1156) | (first << 3));
                    else Queue.queuePush(18 | (first << 3) | second);
                }
                break;
                case 11:
                if(seen.charAt(3) == '0') {
                    seen.setCharAt(3, '1');
                    if (first == 0) Queue.queuePush(192);
                    else if (second == 0) Queue.queuePush((200) | (first << 3));
                    else Queue.queuePush(3 | (first << 3) | second);
                }
                if(seen.charAt(4) == '0') {
                    seen.setCharAt(4, '1');
                    if (first == 0) Queue.queuePush(264);
                    else if (second == 0) Queue.queuePush((257) | (first << 3));
                    else Queue.queuePush(4 | (first << 3) | second);
                }
                if(seen.charAt(5) == '0') {
                    seen.setCharAt(5, '1');
                    if (first == 0) Queue.queuePush(336);
                    else if (second == 0) Queue.queuePush((322) | (first << 3));
                    else Queue.queuePush(5 | (first << 3) | second);
                }
                if(seen.charAt(10) == '0') {
                    seen.setCharAt(10, '1');
                    if (first == 0) Queue.queuePush(696);
                    else if (second == 0) Queue.queuePush((647) | (first << 3));
                    else Queue.queuePush(10 | (first << 3) | second);
                }
                if(seen.charAt(12) == '0') {
                    seen.setCharAt(12, '1');
                    if (first == 0) Queue.queuePush(792);
                    else if (second == 0) Queue.queuePush((771) | (first << 3));
                    else Queue.queuePush(12 | (first << 3) | second);
                }
                if(seen.charAt(17) == '0') {
                    seen.setCharAt(17, '1');
                    if (first == 0) Queue.queuePush(1136);
                    else if (second == 0) Queue.queuePush((1094) | (first << 3));
                    else Queue.queuePush(17 | (first << 3) | second);
                }
                if(seen.charAt(18) == '0') {
                    seen.setCharAt(18, '1');
                    if (first == 0) Queue.queuePush(1192);
                    else if (second == 0) Queue.queuePush((1157) | (first << 3));
                    else Queue.queuePush(18 | (first << 3) | second);
                }
                if(seen.charAt(19) == '0') {
                    seen.setCharAt(19, '1');
                    if (first == 0) Queue.queuePush(1248);
                    else if (second == 0) Queue.queuePush((1220) | (first << 3));
                    else Queue.queuePush(19 | (first << 3) | second);
                }
                break;
                case 12:
                if(seen.charAt(4) == '0') {
                    seen.setCharAt(4, '1');
                    if (first == 0) Queue.queuePush(320);
                    else if (second == 0) Queue.queuePush((264) | (first << 3));
                    else Queue.queuePush(4 | (first << 3) | second);
                }
                if(seen.charAt(5) == '0') {
                    seen.setCharAt(5, '1');
                    if (first == 0) Queue.queuePush(328);
                    else if (second == 0) Queue.queuePush((321) | (first << 3));
                    else Queue.queuePush(5 | (first << 3) | second);
                }
                if(seen.charAt(11) == '0') {
                    seen.setCharAt(11, '1');
                    if (first == 0) Queue.queuePush(760);
                    else if (second == 0) Queue.queuePush((711) | (first << 3));
                    else Queue.queuePush(11 | (first << 3) | second);
                }
                if(seen.charAt(13) == '0') {
                    seen.setCharAt(13, '1');
                    if (first == 0) Queue.queuePush(856);
                    else if (second == 0) Queue.queuePush((835) | (first << 3));
                    else Queue.queuePush(13 | (first << 3) | second);
                }
                if(seen.charAt(18) == '0') {
                    seen.setCharAt(18, '1');
                    if (first == 0) Queue.queuePush(1200);
                    else if (second == 0) Queue.queuePush((1158) | (first << 3));
                    else Queue.queuePush(18 | (first << 3) | second);
                }
                if(seen.charAt(19) == '0') {
                    seen.setCharAt(19, '1');
                    if (first == 0) Queue.queuePush(1256);
                    else if (second == 0) Queue.queuePush((1221) | (first << 3));
                    else Queue.queuePush(19 | (first << 3) | second);
                }
                if(seen.charAt(20) == '0') {
                    seen.setCharAt(20, '1');
                    if (first == 0) Queue.queuePush(1312);
                    else if (second == 0) Queue.queuePush((1284) | (first << 3));
                    else Queue.queuePush(20 | (first << 3) | second);
                }
                break;
                case 13:
                if(seen.charAt(5) == '0') {
                    seen.setCharAt(5, '1');
                    if (first == 0) Queue.queuePush(320);
                    else if (second == 0) Queue.queuePush((328) | (first << 3));
                    else Queue.queuePush(5 | (first << 3) | second);
                }
                if(seen.charAt(12) == '0') {
                    seen.setCharAt(12, '1');
                    if (first == 0) Queue.queuePush(824);
                    else if (second == 0) Queue.queuePush((775) | (first << 3));
                    else Queue.queuePush(12 | (first << 3) | second);
                }
                if(seen.charAt(19) == '0') {
                    seen.setCharAt(19, '1');
                    if (first == 0) Queue.queuePush(1264);
                    else if (second == 0) Queue.queuePush((1222) | (first << 3));
                    else Queue.queuePush(19 | (first << 3) | second);
                }
                if(seen.charAt(20) == '0') {
                    seen.setCharAt(20, '1');
                    if (first == 0) Queue.queuePush(1320);
                    else if (second == 0) Queue.queuePush((1285) | (first << 3));
                    else Queue.queuePush(20 | (first << 3) | second);
                }
                break;
                case 14:
                if(seen.charAt(7) == '0') {
                    seen.setCharAt(7, '1');
                    if (first == 0) Queue.queuePush(456);
                    else if (second == 0) Queue.queuePush((449) | (first << 3));
                    else Queue.queuePush(7 | (first << 3) | second);
                }
                if(seen.charAt(8) == '0') {
                    seen.setCharAt(8, '1');
                    if (first == 0) Queue.queuePush(528);
                    else if (second == 0) Queue.queuePush((514) | (first << 3));
                    else Queue.queuePush(8 | (first << 3) | second);
                }
                if(seen.charAt(15) == '0') {
                    seen.setCharAt(15, '1');
                    if (first == 0) Queue.queuePush(984);
                    else if (second == 0) Queue.queuePush((963) | (first << 3));
                    else Queue.queuePush(15 | (first << 3) | second);
                }
                if(seen.charAt(21) == '0') {
                    seen.setCharAt(21, '1');
                    if (first == 0) Queue.queuePush(1384);
                    else if (second == 0) Queue.queuePush((1349) | (first << 3));
                    else Queue.queuePush(21 | (first << 3) | second);
                }
                if(seen.charAt(22) == '0') {
                    seen.setCharAt(22, '1');
                    if (first == 0) Queue.queuePush(1440);
                    else if (second == 0) Queue.queuePush((1412) | (first << 3));
                    else Queue.queuePush(22 | (first << 3) | second);
                }
                break;
                case 15:
                if(seen.charAt(7) == '0') {
                    seen.setCharAt(7, '1');
                    if (first == 0) Queue.queuePush(448);
                    else if (second == 0) Queue.queuePush((456) | (first << 3));
                    else Queue.queuePush(7 | (first << 3) | second);
                }
                if(seen.charAt(8) == '0') {
                    seen.setCharAt(8, '1');
                    if (first == 0) Queue.queuePush(520);
                    else if (second == 0) Queue.queuePush((513) | (first << 3));
                    else Queue.queuePush(8 | (first << 3) | second);
                }
                if(seen.charAt(9) == '0') {
                    seen.setCharAt(9, '1');
                    if (first == 0) Queue.queuePush(592);
                    else if (second == 0) Queue.queuePush((578) | (first << 3));
                    else Queue.queuePush(9 | (first << 3) | second);
                }
                if(seen.charAt(14) == '0') {
                    seen.setCharAt(14, '1');
                    if (first == 0) Queue.queuePush(952);
                    else if (second == 0) Queue.queuePush((903) | (first << 3));
                    else Queue.queuePush(14 | (first << 3) | second);
                }
                if(seen.charAt(16) == '0') {
                    seen.setCharAt(16, '1');
                    if (first == 0) Queue.queuePush(1048);
                    else if (second == 0) Queue.queuePush((1027) | (first << 3));
                    else Queue.queuePush(16 | (first << 3) | second);
                }
                if(seen.charAt(21) == '0') {
                    seen.setCharAt(21, '1');
                    if (first == 0) Queue.queuePush(1392);
                    else if (second == 0) Queue.queuePush((1350) | (first << 3));
                    else Queue.queuePush(21 | (first << 3) | second);
                }
                if(seen.charAt(22) == '0') {
                    seen.setCharAt(22, '1');
                    if (first == 0) Queue.queuePush(1448);
                    else if (second == 0) Queue.queuePush((1413) | (first << 3));
                    else Queue.queuePush(22 | (first << 3) | second);
                }
                if(seen.charAt(23) == '0') {
                    seen.setCharAt(23, '1');
                    if (first == 0) Queue.queuePush(1504);
                    else if (second == 0) Queue.queuePush((1476) | (first << 3));
                    else Queue.queuePush(23 | (first << 3) | second);
                }
                break;
                case 16:
                if(seen.charAt(8) == '0') {
                    seen.setCharAt(8, '1');
                    if (first == 0) Queue.queuePush(576);
                    else if (second == 0) Queue.queuePush((520) | (first << 3));
                    else Queue.queuePush(8 | (first << 3) | second);
                }
                if(seen.charAt(9) == '0') {
                    seen.setCharAt(9, '1');
                    if (first == 0) Queue.queuePush(584);
                    else if (second == 0) Queue.queuePush((577) | (first << 3));
                    else Queue.queuePush(9 | (first << 3) | second);
                }
                if(seen.charAt(10) == '0') {
                    seen.setCharAt(10, '1');
                    if (first == 0) Queue.queuePush(656);
                    else if (second == 0) Queue.queuePush((642) | (first << 3));
                    else Queue.queuePush(10 | (first << 3) | second);
                }
                if(seen.charAt(15) == '0') {
                    seen.setCharAt(15, '1');
                    if (first == 0) Queue.queuePush(1016);
                    else if (second == 0) Queue.queuePush((967) | (first << 3));
                    else Queue.queuePush(15 | (first << 3) | second);
                }
                if(seen.charAt(17) == '0') {
                    seen.setCharAt(17, '1');
                    if (first == 0) Queue.queuePush(1112);
                    else if (second == 0) Queue.queuePush((1091) | (first << 3));
                    else Queue.queuePush(17 | (first << 3) | second);
                }
                if(seen.charAt(22) == '0') {
                    seen.setCharAt(22, '1');
                    if (first == 0) Queue.queuePush(1456);
                    else if (second == 0) Queue.queuePush((1414) | (first << 3));
                    else Queue.queuePush(22 | (first << 3) | second);
                }
                if(seen.charAt(23) == '0') {
                    seen.setCharAt(23, '1');
                    if (first == 0) Queue.queuePush(1512);
                    else if (second == 0) Queue.queuePush((1477) | (first << 3));
                    else Queue.queuePush(23 | (first << 3) | second);
                }
                if(seen.charAt(24) == '0') {
                    seen.setCharAt(24, '1');
                    if (first == 0) Queue.queuePush(1568);
                    else if (second == 0) Queue.queuePush((1540) | (first << 3));
                    else Queue.queuePush(24 | (first << 3) | second);
                }
                break;
                case 17:
                if(seen.charAt(9) == '0') {
                    seen.setCharAt(9, '1');
                    if (first == 0) Queue.queuePush(576);
                    else if (second == 0) Queue.queuePush((584) | (first << 3));
                    else Queue.queuePush(9 | (first << 3) | second);
                }
                if(seen.charAt(10) == '0') {
                    seen.setCharAt(10, '1');
                    if (first == 0) Queue.queuePush(648);
                    else if (second == 0) Queue.queuePush((641) | (first << 3));
                    else Queue.queuePush(10 | (first << 3) | second);
                }
                if(seen.charAt(11) == '0') {
                    seen.setCharAt(11, '1');
                    if (first == 0) Queue.queuePush(720);
                    else if (second == 0) Queue.queuePush((706) | (first << 3));
                    else Queue.queuePush(11 | (first << 3) | second);
                }
                if(seen.charAt(16) == '0') {
                    seen.setCharAt(16, '1');
                    if (first == 0) Queue.queuePush(1080);
                    else if (second == 0) Queue.queuePush((1031) | (first << 3));
                    else Queue.queuePush(16 | (first << 3) | second);
                }
                if(seen.charAt(18) == '0') {
                    seen.setCharAt(18, '1');
                    if (first == 0) Queue.queuePush(1176);
                    else if (second == 0) Queue.queuePush((1155) | (first << 3));
                    else Queue.queuePush(18 | (first << 3) | second);
                }
                if(seen.charAt(23) == '0') {
                    seen.setCharAt(23, '1');
                    if (first == 0) Queue.queuePush(1520);
                    else if (second == 0) Queue.queuePush((1478) | (first << 3));
                    else Queue.queuePush(23 | (first << 3) | second);
                }
                if(seen.charAt(24) == '0') {
                    seen.setCharAt(24, '1');
                    if (first == 0) Queue.queuePush(1576);
                    else if (second == 0) Queue.queuePush((1541) | (first << 3));
                    else Queue.queuePush(24 | (first << 3) | second);
                }
                if(seen.charAt(25) == '0') {
                    seen.setCharAt(25, '1');
                    if (first == 0) Queue.queuePush(1632);
                    else if (second == 0) Queue.queuePush((1604) | (first << 3));
                    else Queue.queuePush(25 | (first << 3) | second);
                }
                break;
                case 18:
                if(seen.charAt(10) == '0') {
                    seen.setCharAt(10, '1');
                    if (first == 0) Queue.queuePush(704);
                    else if (second == 0) Queue.queuePush((648) | (first << 3));
                    else Queue.queuePush(10 | (first << 3) | second);
                }
                if(seen.charAt(11) == '0') {
                    seen.setCharAt(11, '1');
                    if (first == 0) Queue.queuePush(712);
                    else if (second == 0) Queue.queuePush((705) | (first << 3));
                    else Queue.queuePush(11 | (first << 3) | second);
                }
                if(seen.charAt(12) == '0') {
                    seen.setCharAt(12, '1');
                    if (first == 0) Queue.queuePush(784);
                    else if (second == 0) Queue.queuePush((770) | (first << 3));
                    else Queue.queuePush(12 | (first << 3) | second);
                }
                if(seen.charAt(17) == '0') {
                    seen.setCharAt(17, '1');
                    if (first == 0) Queue.queuePush(1144);
                    else if (second == 0) Queue.queuePush((1095) | (first << 3));
                    else Queue.queuePush(17 | (first << 3) | second);
                }
                if(seen.charAt(19) == '0') {
                    seen.setCharAt(19, '1');
                    if (first == 0) Queue.queuePush(1240);
                    else if (second == 0) Queue.queuePush((1219) | (first << 3));
                    else Queue.queuePush(19 | (first << 3) | second);
                }
                if(seen.charAt(24) == '0') {
                    seen.setCharAt(24, '1');
                    if (first == 0) Queue.queuePush(1584);
                    else if (second == 0) Queue.queuePush((1542) | (first << 3));
                    else Queue.queuePush(24 | (first << 3) | second);
                }
                if(seen.charAt(25) == '0') {
                    seen.setCharAt(25, '1');
                    if (first == 0) Queue.queuePush(1640);
                    else if (second == 0) Queue.queuePush((1605) | (first << 3));
                    else Queue.queuePush(25 | (first << 3) | second);
                }
                if(seen.charAt(26) == '0') {
                    seen.setCharAt(26, '1');
                    if (first == 0) Queue.queuePush(1696);
                    else if (second == 0) Queue.queuePush((1668) | (first << 3));
                    else Queue.queuePush(26 | (first << 3) | second);
                }
                break;
                case 19:
                if(seen.charAt(11) == '0') {
                    seen.setCharAt(11, '1');
                    if (first == 0) Queue.queuePush(704);
                    else if (second == 0) Queue.queuePush((712) | (first << 3));
                    else Queue.queuePush(11 | (first << 3) | second);
                }
                if(seen.charAt(12) == '0') {
                    seen.setCharAt(12, '1');
                    if (first == 0) Queue.queuePush(776);
                    else if (second == 0) Queue.queuePush((769) | (first << 3));
                    else Queue.queuePush(12 | (first << 3) | second);
                }
                if(seen.charAt(13) == '0') {
                    seen.setCharAt(13, '1');
                    if (first == 0) Queue.queuePush(848);
                    else if (second == 0) Queue.queuePush((834) | (first << 3));
                    else Queue.queuePush(13 | (first << 3) | second);
                }
                if(seen.charAt(18) == '0') {
                    seen.setCharAt(18, '1');
                    if (first == 0) Queue.queuePush(1208);
                    else if (second == 0) Queue.queuePush((1159) | (first << 3));
                    else Queue.queuePush(18 | (first << 3) | second);
                }
                if(seen.charAt(20) == '0') {
                    seen.setCharAt(20, '1');
                    if (first == 0) Queue.queuePush(1304);
                    else if (second == 0) Queue.queuePush((1283) | (first << 3));
                    else Queue.queuePush(20 | (first << 3) | second);
                }
                if(seen.charAt(25) == '0') {
                    seen.setCharAt(25, '1');
                    if (first == 0) Queue.queuePush(1648);
                    else if (second == 0) Queue.queuePush((1606) | (first << 3));
                    else Queue.queuePush(25 | (first << 3) | second);
                }
                if(seen.charAt(26) == '0') {
                    seen.setCharAt(26, '1');
                    if (first == 0) Queue.queuePush(1704);
                    else if (second == 0) Queue.queuePush((1669) | (first << 3));
                    else Queue.queuePush(26 | (first << 3) | second);
                }
                if(seen.charAt(27) == '0') {
                    seen.setCharAt(27, '1');
                    if (first == 0) Queue.queuePush(1760);
                    else if (second == 0) Queue.queuePush((1732) | (first << 3));
                    else Queue.queuePush(27 | (first << 3) | second);
                }
                break;
                case 20:
                if(seen.charAt(12) == '0') {
                    seen.setCharAt(12, '1');
                    if (first == 0) Queue.queuePush(832);
                    else if (second == 0) Queue.queuePush((776) | (first << 3));
                    else Queue.queuePush(12 | (first << 3) | second);
                }
                if(seen.charAt(13) == '0') {
                    seen.setCharAt(13, '1');
                    if (first == 0) Queue.queuePush(840);
                    else if (second == 0) Queue.queuePush((833) | (first << 3));
                    else Queue.queuePush(13 | (first << 3) | second);
                }
                if(seen.charAt(19) == '0') {
                    seen.setCharAt(19, '1');
                    if (first == 0) Queue.queuePush(1272);
                    else if (second == 0) Queue.queuePush((1223) | (first << 3));
                    else Queue.queuePush(19 | (first << 3) | second);
                }
                if(seen.charAt(26) == '0') {
                    seen.setCharAt(26, '1');
                    if (first == 0) Queue.queuePush(1712);
                    else if (second == 0) Queue.queuePush((1670) | (first << 3));
                    else Queue.queuePush(26 | (first << 3) | second);
                }
                if(seen.charAt(27) == '0') {
                    seen.setCharAt(27, '1');
                    if (first == 0) Queue.queuePush(1768);
                    else if (second == 0) Queue.queuePush((1733) | (first << 3));
                    else Queue.queuePush(27 | (first << 3) | second);
                }
                break;
                case 21:
                if(seen.charAt(14) == '0') {
                    seen.setCharAt(14, '1');
                    if (first == 0) Queue.queuePush(904);
                    else if (second == 0) Queue.queuePush((897) | (first << 3));
                    else Queue.queuePush(14 | (first << 3) | second);
                }
                if(seen.charAt(15) == '0') {
                    seen.setCharAt(15, '1');
                    if (first == 0) Queue.queuePush(976);
                    else if (second == 0) Queue.queuePush((962) | (first << 3));
                    else Queue.queuePush(15 | (first << 3) | second);
                }
                if(seen.charAt(22) == '0') {
                    seen.setCharAt(22, '1');
                    if (first == 0) Queue.queuePush(1432);
                    else if (second == 0) Queue.queuePush((1411) | (first << 3));
                    else Queue.queuePush(22 | (first << 3) | second);
                }
                if(seen.charAt(28) == '0') {
                    seen.setCharAt(28, '1');
                    if (first == 0) Queue.queuePush(1832);
                    else if (second == 0) Queue.queuePush((1797) | (first << 3));
                    else Queue.queuePush(28 | (first << 3) | second);
                }
                if(seen.charAt(29) == '0') {
                    seen.setCharAt(29, '1');
                    if (first == 0) Queue.queuePush(1888);
                    else if (second == 0) Queue.queuePush((1860) | (first << 3));
                    else Queue.queuePush(29 | (first << 3) | second);
                }
                break;
                case 22:
                if(seen.charAt(14) == '0') {
                    seen.setCharAt(14, '1');
                    if (first == 0) Queue.queuePush(960);
                    else if (second == 0) Queue.queuePush((904) | (first << 3));
                    else Queue.queuePush(14 | (first << 3) | second);
                }
                if(seen.charAt(15) == '0') {
                    seen.setCharAt(15, '1');
                    if (first == 0) Queue.queuePush(968);
                    else if (second == 0) Queue.queuePush((961) | (first << 3));
                    else Queue.queuePush(15 | (first << 3) | second);
                }
                if(seen.charAt(16) == '0') {
                    seen.setCharAt(16, '1');
                    if (first == 0) Queue.queuePush(1040);
                    else if (second == 0) Queue.queuePush((1026) | (first << 3));
                    else Queue.queuePush(16 | (first << 3) | second);
                }
                if(seen.charAt(21) == '0') {
                    seen.setCharAt(21, '1');
                    if (first == 0) Queue.queuePush(1400);
                    else if (second == 0) Queue.queuePush((1351) | (first << 3));
                    else Queue.queuePush(21 | (first << 3) | second);
                }
                if(seen.charAt(23) == '0') {
                    seen.setCharAt(23, '1');
                    if (first == 0) Queue.queuePush(1496);
                    else if (second == 0) Queue.queuePush((1475) | (first << 3));
                    else Queue.queuePush(23 | (first << 3) | second);
                }
                if(seen.charAt(28) == '0') {
                    seen.setCharAt(28, '1');
                    if (first == 0) Queue.queuePush(1840);
                    else if (second == 0) Queue.queuePush((1798) | (first << 3));
                    else Queue.queuePush(28 | (first << 3) | second);
                }
                if(seen.charAt(29) == '0') {
                    seen.setCharAt(29, '1');
                    if (first == 0) Queue.queuePush(1896);
                    else if (second == 0) Queue.queuePush((1861) | (first << 3));
                    else Queue.queuePush(29 | (first << 3) | second);
                }
                if(seen.charAt(30) == '0') {
                    seen.setCharAt(30, '1');
                    if (first == 0) Queue.queuePush(1952);
                    else if (second == 0) Queue.queuePush((1924) | (first << 3));
                    else Queue.queuePush(30 | (first << 3) | second);
                }
                break;
                case 23:
                if(seen.charAt(15) == '0') {
                    seen.setCharAt(15, '1');
                    if (first == 0) Queue.queuePush(960);
                    else if (second == 0) Queue.queuePush((968) | (first << 3));
                    else Queue.queuePush(15 | (first << 3) | second);
                }
                if(seen.charAt(16) == '0') {
                    seen.setCharAt(16, '1');
                    if (first == 0) Queue.queuePush(1032);
                    else if (second == 0) Queue.queuePush((1025) | (first << 3));
                    else Queue.queuePush(16 | (first << 3) | second);
                }
                if(seen.charAt(17) == '0') {
                    seen.setCharAt(17, '1');
                    if (first == 0) Queue.queuePush(1104);
                    else if (second == 0) Queue.queuePush((1090) | (first << 3));
                    else Queue.queuePush(17 | (first << 3) | second);
                }
                if(seen.charAt(22) == '0') {
                    seen.setCharAt(22, '1');
                    if (first == 0) Queue.queuePush(1464);
                    else if (second == 0) Queue.queuePush((1415) | (first << 3));
                    else Queue.queuePush(22 | (first << 3) | second);
                }
                if(seen.charAt(24) == '0') {
                    seen.setCharAt(24, '1');
                    if (first == 0) Queue.queuePush(1560);
                    else if (second == 0) Queue.queuePush((1539) | (first << 3));
                    else Queue.queuePush(24 | (first << 3) | second);
                }
                if(seen.charAt(29) == '0') {
                    seen.setCharAt(29, '1');
                    if (first == 0) Queue.queuePush(1904);
                    else if (second == 0) Queue.queuePush((1862) | (first << 3));
                    else Queue.queuePush(29 | (first << 3) | second);
                }
                if(seen.charAt(30) == '0') {
                    seen.setCharAt(30, '1');
                    if (first == 0) Queue.queuePush(1960);
                    else if (second == 0) Queue.queuePush((1925) | (first << 3));
                    else Queue.queuePush(30 | (first << 3) | second);
                }
                if(seen.charAt(31) == '0') {
                    seen.setCharAt(31, '1');
                    if (first == 0) Queue.queuePush(2016);
                    else if (second == 0) Queue.queuePush((1988) | (first << 3));
                    else Queue.queuePush(31 | (first << 3) | second);
                }
                break;
                case 24:
                if(seen.charAt(16) == '0') {
                    seen.setCharAt(16, '1');
                    if (first == 0) Queue.queuePush(1088);
                    else if (second == 0) Queue.queuePush((1032) | (first << 3));
                    else Queue.queuePush(16 | (first << 3) | second);
                }
                if(seen.charAt(17) == '0') {
                    seen.setCharAt(17, '1');
                    if (first == 0) Queue.queuePush(1096);
                    else if (second == 0) Queue.queuePush((1089) | (first << 3));
                    else Queue.queuePush(17 | (first << 3) | second);
                }
                if(seen.charAt(18) == '0') {
                    seen.setCharAt(18, '1');
                    if (first == 0) Queue.queuePush(1168);
                    else if (second == 0) Queue.queuePush((1154) | (first << 3));
                    else Queue.queuePush(18 | (first << 3) | second);
                }
                if(seen.charAt(23) == '0') {
                    seen.setCharAt(23, '1');
                    if (first == 0) Queue.queuePush(1528);
                    else if (second == 0) Queue.queuePush((1479) | (first << 3));
                    else Queue.queuePush(23 | (first << 3) | second);
                }
                if(seen.charAt(25) == '0') {
                    seen.setCharAt(25, '1');
                    if (first == 0) Queue.queuePush(1624);
                    else if (second == 0) Queue.queuePush((1603) | (first << 3));
                    else Queue.queuePush(25 | (first << 3) | second);
                }
                if(seen.charAt(30) == '0') {
                    seen.setCharAt(30, '1');
                    if (first == 0) Queue.queuePush(1968);
                    else if (second == 0) Queue.queuePush((1926) | (first << 3));
                    else Queue.queuePush(30 | (first << 3) | second);
                }
                if(seen.charAt(31) == '0') {
                    seen.setCharAt(31, '1');
                    if (first == 0) Queue.queuePush(2024);
                    else if (second == 0) Queue.queuePush((1989) | (first << 3));
                    else Queue.queuePush(31 | (first << 3) | second);
                }
                if(seen.charAt(32) == '0') {
                    seen.setCharAt(32, '1');
                    if (first == 0) Queue.queuePush(2080);
                    else if (second == 0) Queue.queuePush((2052) | (first << 3));
                    else Queue.queuePush(32 | (first << 3) | second);
                }
                break;
                case 25:
                if(seen.charAt(17) == '0') {
                    seen.setCharAt(17, '1');
                    if (first == 0) Queue.queuePush(1088);
                    else if (second == 0) Queue.queuePush((1096) | (first << 3));
                    else Queue.queuePush(17 | (first << 3) | second);
                }
                if(seen.charAt(18) == '0') {
                    seen.setCharAt(18, '1');
                    if (first == 0) Queue.queuePush(1160);
                    else if (second == 0) Queue.queuePush((1153) | (first << 3));
                    else Queue.queuePush(18 | (first << 3) | second);
                }
                if(seen.charAt(19) == '0') {
                    seen.setCharAt(19, '1');
                    if (first == 0) Queue.queuePush(1232);
                    else if (second == 0) Queue.queuePush((1218) | (first << 3));
                    else Queue.queuePush(19 | (first << 3) | second);
                }
                if(seen.charAt(24) == '0') {
                    seen.setCharAt(24, '1');
                    if (first == 0) Queue.queuePush(1592);
                    else if (second == 0) Queue.queuePush((1543) | (first << 3));
                    else Queue.queuePush(24 | (first << 3) | second);
                }
                if(seen.charAt(26) == '0') {
                    seen.setCharAt(26, '1');
                    if (first == 0) Queue.queuePush(1688);
                    else if (second == 0) Queue.queuePush((1667) | (first << 3));
                    else Queue.queuePush(26 | (first << 3) | second);
                }
                if(seen.charAt(31) == '0') {
                    seen.setCharAt(31, '1');
                    if (first == 0) Queue.queuePush(2032);
                    else if (second == 0) Queue.queuePush((1990) | (first << 3));
                    else Queue.queuePush(31 | (first << 3) | second);
                }
                if(seen.charAt(32) == '0') {
                    seen.setCharAt(32, '1');
                    if (first == 0) Queue.queuePush(2088);
                    else if (second == 0) Queue.queuePush((2053) | (first << 3));
                    else Queue.queuePush(32 | (first << 3) | second);
                }
                if(seen.charAt(33) == '0') {
                    seen.setCharAt(33, '1');
                    if (first == 0) Queue.queuePush(2144);
                    else if (second == 0) Queue.queuePush((2116) | (first << 3));
                    else Queue.queuePush(33 | (first << 3) | second);
                }
                break;
                case 26:
                if(seen.charAt(18) == '0') {
                    seen.setCharAt(18, '1');
                    if (first == 0) Queue.queuePush(1216);
                    else if (second == 0) Queue.queuePush((1160) | (first << 3));
                    else Queue.queuePush(18 | (first << 3) | second);
                }
                if(seen.charAt(19) == '0') {
                    seen.setCharAt(19, '1');
                    if (first == 0) Queue.queuePush(1224);
                    else if (second == 0) Queue.queuePush((1217) | (first << 3));
                    else Queue.queuePush(19 | (first << 3) | second);
                }
                if(seen.charAt(20) == '0') {
                    seen.setCharAt(20, '1');
                    if (first == 0) Queue.queuePush(1296);
                    else if (second == 0) Queue.queuePush((1282) | (first << 3));
                    else Queue.queuePush(20 | (first << 3) | second);
                }
                if(seen.charAt(25) == '0') {
                    seen.setCharAt(25, '1');
                    if (first == 0) Queue.queuePush(1656);
                    else if (second == 0) Queue.queuePush((1607) | (first << 3));
                    else Queue.queuePush(25 | (first << 3) | second);
                }
                if(seen.charAt(27) == '0') {
                    seen.setCharAt(27, '1');
                    if (first == 0) Queue.queuePush(1752);
                    else if (second == 0) Queue.queuePush((1731) | (first << 3));
                    else Queue.queuePush(27 | (first << 3) | second);
                }
                if(seen.charAt(32) == '0') {
                    seen.setCharAt(32, '1');
                    if (first == 0) Queue.queuePush(2096);
                    else if (second == 0) Queue.queuePush((2054) | (first << 3));
                    else Queue.queuePush(32 | (first << 3) | second);
                }
                if(seen.charAt(33) == '0') {
                    seen.setCharAt(33, '1');
                    if (first == 0) Queue.queuePush(2152);
                    else if (second == 0) Queue.queuePush((2117) | (first << 3));
                    else Queue.queuePush(33 | (first << 3) | second);
                }
                if(seen.charAt(34) == '0') {
                    seen.setCharAt(34, '1');
                    if (first == 0) Queue.queuePush(2208);
                    else if (second == 0) Queue.queuePush((2180) | (first << 3));
                    else Queue.queuePush(34 | (first << 3) | second);
                }
                break;
                case 27:
                if(seen.charAt(19) == '0') {
                    seen.setCharAt(19, '1');
                    if (first == 0) Queue.queuePush(1216);
                    else if (second == 0) Queue.queuePush((1224) | (first << 3));
                    else Queue.queuePush(19 | (first << 3) | second);
                }
                if(seen.charAt(20) == '0') {
                    seen.setCharAt(20, '1');
                    if (first == 0) Queue.queuePush(1288);
                    else if (second == 0) Queue.queuePush((1281) | (first << 3));
                    else Queue.queuePush(20 | (first << 3) | second);
                }
                if(seen.charAt(26) == '0') {
                    seen.setCharAt(26, '1');
                    if (first == 0) Queue.queuePush(1720);
                    else if (second == 0) Queue.queuePush((1671) | (first << 3));
                    else Queue.queuePush(26 | (first << 3) | second);
                }
                if(seen.charAt(33) == '0') {
                    seen.setCharAt(33, '1');
                    if (first == 0) Queue.queuePush(2160);
                    else if (second == 0) Queue.queuePush((2118) | (first << 3));
                    else Queue.queuePush(33 | (first << 3) | second);
                }
                if(seen.charAt(34) == '0') {
                    seen.setCharAt(34, '1');
                    if (first == 0) Queue.queuePush(2216);
                    else if (second == 0) Queue.queuePush((2181) | (first << 3));
                    else Queue.queuePush(34 | (first << 3) | second);
                }
                break;
                case 28:
                if(seen.charAt(21) == '0') {
                    seen.setCharAt(21, '1');
                    if (first == 0) Queue.queuePush(1352);
                    else if (second == 0) Queue.queuePush((1345) | (first << 3));
                    else Queue.queuePush(21 | (first << 3) | second);
                }
                if(seen.charAt(22) == '0') {
                    seen.setCharAt(22, '1');
                    if (first == 0) Queue.queuePush(1424);
                    else if (second == 0) Queue.queuePush((1410) | (first << 3));
                    else Queue.queuePush(22 | (first << 3) | second);
                }
                if(seen.charAt(29) == '0') {
                    seen.setCharAt(29, '1');
                    if (first == 0) Queue.queuePush(1880);
                    else if (second == 0) Queue.queuePush((1859) | (first << 3));
                    else Queue.queuePush(29 | (first << 3) | second);
                }
                if(seen.charAt(35) == '0') {
                    seen.setCharAt(35, '1');
                    if (first == 0) Queue.queuePush(2280);
                    else if (second == 0) Queue.queuePush((2245) | (first << 3));
                    else Queue.queuePush(35 | (first << 3) | second);
                }
                if(seen.charAt(36) == '0') {
                    seen.setCharAt(36, '1');
                    if (first == 0) Queue.queuePush(2336);
                    else if (second == 0) Queue.queuePush((2308) | (first << 3));
                    else Queue.queuePush(36 | (first << 3) | second);
                }
                break;
                case 29:
                if(seen.charAt(21) == '0') {
                    seen.setCharAt(21, '1');
                    if (first == 0) Queue.queuePush(1344);
                    else if (second == 0) Queue.queuePush((1352) | (first << 3));
                    else Queue.queuePush(21 | (first << 3) | second);
                }
                if(seen.charAt(22) == '0') {
                    seen.setCharAt(22, '1');
                    if (first == 0) Queue.queuePush(1416);
                    else if (second == 0) Queue.queuePush((1409) | (first << 3));
                    else Queue.queuePush(22 | (first << 3) | second);
                }
                if(seen.charAt(23) == '0') {
                    seen.setCharAt(23, '1');
                    if (first == 0) Queue.queuePush(1488);
                    else if (second == 0) Queue.queuePush((1474) | (first << 3));
                    else Queue.queuePush(23 | (first << 3) | second);
                }
                if(seen.charAt(28) == '0') {
                    seen.setCharAt(28, '1');
                    if (first == 0) Queue.queuePush(1848);
                    else if (second == 0) Queue.queuePush((1799) | (first << 3));
                    else Queue.queuePush(28 | (first << 3) | second);
                }
                if(seen.charAt(30) == '0') {
                    seen.setCharAt(30, '1');
                    if (first == 0) Queue.queuePush(1944);
                    else if (second == 0) Queue.queuePush((1923) | (first << 3));
                    else Queue.queuePush(30 | (first << 3) | second);
                }
                if(seen.charAt(35) == '0') {
                    seen.setCharAt(35, '1');
                    if (first == 0) Queue.queuePush(2288);
                    else if (second == 0) Queue.queuePush((2246) | (first << 3));
                    else Queue.queuePush(35 | (first << 3) | second);
                }
                if(seen.charAt(36) == '0') {
                    seen.setCharAt(36, '1');
                    if (first == 0) Queue.queuePush(2344);
                    else if (second == 0) Queue.queuePush((2309) | (first << 3));
                    else Queue.queuePush(36 | (first << 3) | second);
                }
                if(seen.charAt(37) == '0') {
                    seen.setCharAt(37, '1');
                    if (first == 0) Queue.queuePush(2400);
                    else if (second == 0) Queue.queuePush((2372) | (first << 3));
                    else Queue.queuePush(37 | (first << 3) | second);
                }
                break;
                case 30:
                if(seen.charAt(22) == '0') {
                    seen.setCharAt(22, '1');
                    if (first == 0) Queue.queuePush(1472);
                    else if (second == 0) Queue.queuePush((1416) | (first << 3));
                    else Queue.queuePush(22 | (first << 3) | second);
                }
                if(seen.charAt(23) == '0') {
                    seen.setCharAt(23, '1');
                    if (first == 0) Queue.queuePush(1480);
                    else if (second == 0) Queue.queuePush((1473) | (first << 3));
                    else Queue.queuePush(23 | (first << 3) | second);
                }
                if(seen.charAt(24) == '0') {
                    seen.setCharAt(24, '1');
                    if (first == 0) Queue.queuePush(1552);
                    else if (second == 0) Queue.queuePush((1538) | (first << 3));
                    else Queue.queuePush(24 | (first << 3) | second);
                }
                if(seen.charAt(29) == '0') {
                    seen.setCharAt(29, '1');
                    if (first == 0) Queue.queuePush(1912);
                    else if (second == 0) Queue.queuePush((1863) | (first << 3));
                    else Queue.queuePush(29 | (first << 3) | second);
                }
                if(seen.charAt(31) == '0') {
                    seen.setCharAt(31, '1');
                    if (first == 0) Queue.queuePush(2008);
                    else if (second == 0) Queue.queuePush((1987) | (first << 3));
                    else Queue.queuePush(31 | (first << 3) | second);
                }
                if(seen.charAt(36) == '0') {
                    seen.setCharAt(36, '1');
                    if (first == 0) Queue.queuePush(2352);
                    else if (second == 0) Queue.queuePush((2310) | (first << 3));
                    else Queue.queuePush(36 | (first << 3) | second);
                }
                if(seen.charAt(37) == '0') {
                    seen.setCharAt(37, '1');
                    if (first == 0) Queue.queuePush(2408);
                    else if (second == 0) Queue.queuePush((2373) | (first << 3));
                    else Queue.queuePush(37 | (first << 3) | second);
                }
                if(seen.charAt(38) == '0') {
                    seen.setCharAt(38, '1');
                    if (first == 0) Queue.queuePush(2464);
                    else if (second == 0) Queue.queuePush((2436) | (first << 3));
                    else Queue.queuePush(38 | (first << 3) | second);
                }
                break;
                case 31:
                if(seen.charAt(23) == '0') {
                    seen.setCharAt(23, '1');
                    if (first == 0) Queue.queuePush(1472);
                    else if (second == 0) Queue.queuePush((1480) | (first << 3));
                    else Queue.queuePush(23 | (first << 3) | second);
                }
                if(seen.charAt(24) == '0') {
                    seen.setCharAt(24, '1');
                    if (first == 0) Queue.queuePush(1544);
                    else if (second == 0) Queue.queuePush((1537) | (first << 3));
                    else Queue.queuePush(24 | (first << 3) | second);
                }
                if(seen.charAt(25) == '0') {
                    seen.setCharAt(25, '1');
                    if (first == 0) Queue.queuePush(1616);
                    else if (second == 0) Queue.queuePush((1602) | (first << 3));
                    else Queue.queuePush(25 | (first << 3) | second);
                }
                if(seen.charAt(30) == '0') {
                    seen.setCharAt(30, '1');
                    if (first == 0) Queue.queuePush(1976);
                    else if (second == 0) Queue.queuePush((1927) | (first << 3));
                    else Queue.queuePush(30 | (first << 3) | second);
                }
                if(seen.charAt(32) == '0') {
                    seen.setCharAt(32, '1');
                    if (first == 0) Queue.queuePush(2072);
                    else if (second == 0) Queue.queuePush((2051) | (first << 3));
                    else Queue.queuePush(32 | (first << 3) | second);
                }
                if(seen.charAt(37) == '0') {
                    seen.setCharAt(37, '1');
                    if (first == 0) Queue.queuePush(2416);
                    else if (second == 0) Queue.queuePush((2374) | (first << 3));
                    else Queue.queuePush(37 | (first << 3) | second);
                }
                if(seen.charAt(38) == '0') {
                    seen.setCharAt(38, '1');
                    if (first == 0) Queue.queuePush(2472);
                    else if (second == 0) Queue.queuePush((2437) | (first << 3));
                    else Queue.queuePush(38 | (first << 3) | second);
                }
                if(seen.charAt(39) == '0') {
                    seen.setCharAt(39, '1');
                    if (first == 0) Queue.queuePush(2528);
                    else if (second == 0) Queue.queuePush((2500) | (first << 3));
                    else Queue.queuePush(39 | (first << 3) | second);
                }
                break;
                case 32:
                if(seen.charAt(24) == '0') {
                    seen.setCharAt(24, '1');
                    if (first == 0) Queue.queuePush(1600);
                    else if (second == 0) Queue.queuePush((1544) | (first << 3));
                    else Queue.queuePush(24 | (first << 3) | second);
                }
                if(seen.charAt(25) == '0') {
                    seen.setCharAt(25, '1');
                    if (first == 0) Queue.queuePush(1608);
                    else if (second == 0) Queue.queuePush((1601) | (first << 3));
                    else Queue.queuePush(25 | (first << 3) | second);
                }
                if(seen.charAt(26) == '0') {
                    seen.setCharAt(26, '1');
                    if (first == 0) Queue.queuePush(1680);
                    else if (second == 0) Queue.queuePush((1666) | (first << 3));
                    else Queue.queuePush(26 | (first << 3) | second);
                }
                if(seen.charAt(31) == '0') {
                    seen.setCharAt(31, '1');
                    if (first == 0) Queue.queuePush(2040);
                    else if (second == 0) Queue.queuePush((1991) | (first << 3));
                    else Queue.queuePush(31 | (first << 3) | second);
                }
                if(seen.charAt(33) == '0') {
                    seen.setCharAt(33, '1');
                    if (first == 0) Queue.queuePush(2136);
                    else if (second == 0) Queue.queuePush((2115) | (first << 3));
                    else Queue.queuePush(33 | (first << 3) | second);
                }
                if(seen.charAt(38) == '0') {
                    seen.setCharAt(38, '1');
                    if (first == 0) Queue.queuePush(2480);
                    else if (second == 0) Queue.queuePush((2438) | (first << 3));
                    else Queue.queuePush(38 | (first << 3) | second);
                }
                if(seen.charAt(39) == '0') {
                    seen.setCharAt(39, '1');
                    if (first == 0) Queue.queuePush(2536);
                    else if (second == 0) Queue.queuePush((2501) | (first << 3));
                    else Queue.queuePush(39 | (first << 3) | second);
                }
                if(seen.charAt(40) == '0') {
                    seen.setCharAt(40, '1');
                    if (first == 0) Queue.queuePush(2592);
                    else if (second == 0) Queue.queuePush((2564) | (first << 3));
                    else Queue.queuePush(40 | (first << 3) | second);
                }
                break;
                case 33:
                if(seen.charAt(25) == '0') {
                    seen.setCharAt(25, '1');
                    if (first == 0) Queue.queuePush(1600);
                    else if (second == 0) Queue.queuePush((1608) | (first << 3));
                    else Queue.queuePush(25 | (first << 3) | second);
                }
                if(seen.charAt(26) == '0') {
                    seen.setCharAt(26, '1');
                    if (first == 0) Queue.queuePush(1672);
                    else if (second == 0) Queue.queuePush((1665) | (first << 3));
                    else Queue.queuePush(26 | (first << 3) | second);
                }
                if(seen.charAt(27) == '0') {
                    seen.setCharAt(27, '1');
                    if (first == 0) Queue.queuePush(1744);
                    else if (second == 0) Queue.queuePush((1730) | (first << 3));
                    else Queue.queuePush(27 | (first << 3) | second);
                }
                if(seen.charAt(32) == '0') {
                    seen.setCharAt(32, '1');
                    if (first == 0) Queue.queuePush(2104);
                    else if (second == 0) Queue.queuePush((2055) | (first << 3));
                    else Queue.queuePush(32 | (first << 3) | second);
                }
                if(seen.charAt(34) == '0') {
                    seen.setCharAt(34, '1');
                    if (first == 0) Queue.queuePush(2200);
                    else if (second == 0) Queue.queuePush((2179) | (first << 3));
                    else Queue.queuePush(34 | (first << 3) | second);
                }
                if(seen.charAt(39) == '0') {
                    seen.setCharAt(39, '1');
                    if (first == 0) Queue.queuePush(2544);
                    else if (second == 0) Queue.queuePush((2502) | (first << 3));
                    else Queue.queuePush(39 | (first << 3) | second);
                }
                if(seen.charAt(40) == '0') {
                    seen.setCharAt(40, '1');
                    if (first == 0) Queue.queuePush(2600);
                    else if (second == 0) Queue.queuePush((2565) | (first << 3));
                    else Queue.queuePush(40 | (first << 3) | second);
                }
                if(seen.charAt(41) == '0') {
                    seen.setCharAt(41, '1');
                    if (first == 0) Queue.queuePush(2656);
                    else if (second == 0) Queue.queuePush((2628) | (first << 3));
                    else Queue.queuePush(41 | (first << 3) | second);
                }
                break;
                case 34:
                if(seen.charAt(26) == '0') {
                    seen.setCharAt(26, '1');
                    if (first == 0) Queue.queuePush(1728);
                    else if (second == 0) Queue.queuePush((1672) | (first << 3));
                    else Queue.queuePush(26 | (first << 3) | second);
                }
                if(seen.charAt(27) == '0') {
                    seen.setCharAt(27, '1');
                    if (first == 0) Queue.queuePush(1736);
                    else if (second == 0) Queue.queuePush((1729) | (first << 3));
                    else Queue.queuePush(27 | (first << 3) | second);
                }
                if(seen.charAt(33) == '0') {
                    seen.setCharAt(33, '1');
                    if (first == 0) Queue.queuePush(2168);
                    else if (second == 0) Queue.queuePush((2119) | (first << 3));
                    else Queue.queuePush(33 | (first << 3) | second);
                }
                if(seen.charAt(40) == '0') {
                    seen.setCharAt(40, '1');
                    if (first == 0) Queue.queuePush(2608);
                    else if (second == 0) Queue.queuePush((2566) | (first << 3));
                    else Queue.queuePush(40 | (first << 3) | second);
                }
                if(seen.charAt(41) == '0') {
                    seen.setCharAt(41, '1');
                    if (first == 0) Queue.queuePush(2664);
                    else if (second == 0) Queue.queuePush((2629) | (first << 3));
                    else Queue.queuePush(41 | (first << 3) | second);
                }
                break;
                case 35:
                if(seen.charAt(28) == '0') {
                    seen.setCharAt(28, '1');
                    if (first == 0) Queue.queuePush(1800);
                    else if (second == 0) Queue.queuePush((1793) | (first << 3));
                    else Queue.queuePush(28 | (first << 3) | second);
                }
                if(seen.charAt(29) == '0') {
                    seen.setCharAt(29, '1');
                    if (first == 0) Queue.queuePush(1872);
                    else if (second == 0) Queue.queuePush((1858) | (first << 3));
                    else Queue.queuePush(29 | (first << 3) | second);
                }
                if(seen.charAt(36) == '0') {
                    seen.setCharAt(36, '1');
                    if (first == 0) Queue.queuePush(2328);
                    else if (second == 0) Queue.queuePush((2307) | (first << 3));
                    else Queue.queuePush(36 | (first << 3) | second);
                }
                if(seen.charAt(43) == '0') {
                    seen.setCharAt(43, '1');
                    if (first == 0) Queue.queuePush(2784);
                    else if (second == 0) Queue.queuePush((2756) | (first << 3));
                    else Queue.queuePush(43 | (first << 3) | second);
                }
                break;
                case 36:
                if(seen.charAt(28) == '0') {
                    seen.setCharAt(28, '1');
                    if (first == 0) Queue.queuePush(1856);
                    else if (second == 0) Queue.queuePush((1800) | (first << 3));
                    else Queue.queuePush(28 | (first << 3) | second);
                }
                if(seen.charAt(29) == '0') {
                    seen.setCharAt(29, '1');
                    if (first == 0) Queue.queuePush(1864);
                    else if (second == 0) Queue.queuePush((1857) | (first << 3));
                    else Queue.queuePush(29 | (first << 3) | second);
                }
                if(seen.charAt(30) == '0') {
                    seen.setCharAt(30, '1');
                    if (first == 0) Queue.queuePush(1936);
                    else if (second == 0) Queue.queuePush((1922) | (first << 3));
                    else Queue.queuePush(30 | (first << 3) | second);
                }
                if(seen.charAt(35) == '0') {
                    seen.setCharAt(35, '1');
                    if (first == 0) Queue.queuePush(2296);
                    else if (second == 0) Queue.queuePush((2247) | (first << 3));
                    else Queue.queuePush(35 | (first << 3) | second);
                }
                if(seen.charAt(37) == '0') {
                    seen.setCharAt(37, '1');
                    if (first == 0) Queue.queuePush(2392);
                    else if (second == 0) Queue.queuePush((2371) | (first << 3));
                    else Queue.queuePush(37 | (first << 3) | second);
                }
                if(seen.charAt(43) == '0') {
                    seen.setCharAt(43, '1');
                    if (first == 0) Queue.queuePush(2792);
                    else if (second == 0) Queue.queuePush((2757) | (first << 3));
                    else Queue.queuePush(43 | (first << 3) | second);
                }
                if(seen.charAt(44) == '0') {
                    seen.setCharAt(44, '1');
                    if (first == 0) Queue.queuePush(2848);
                    else if (second == 0) Queue.queuePush((2820) | (first << 3));
                    else Queue.queuePush(44 | (first << 3) | second);
                }
                break;
                case 37:
                if(seen.charAt(29) == '0') {
                    seen.setCharAt(29, '1');
                    if (first == 0) Queue.queuePush(1856);
                    else if (second == 0) Queue.queuePush((1864) | (first << 3));
                    else Queue.queuePush(29 | (first << 3) | second);
                }
                if(seen.charAt(30) == '0') {
                    seen.setCharAt(30, '1');
                    if (first == 0) Queue.queuePush(1928);
                    else if (second == 0) Queue.queuePush((1921) | (first << 3));
                    else Queue.queuePush(30 | (first << 3) | second);
                }
                if(seen.charAt(31) == '0') {
                    seen.setCharAt(31, '1');
                    if (first == 0) Queue.queuePush(2000);
                    else if (second == 0) Queue.queuePush((1986) | (first << 3));
                    else Queue.queuePush(31 | (first << 3) | second);
                }
                if(seen.charAt(36) == '0') {
                    seen.setCharAt(36, '1');
                    if (first == 0) Queue.queuePush(2360);
                    else if (second == 0) Queue.queuePush((2311) | (first << 3));
                    else Queue.queuePush(36 | (first << 3) | second);
                }
                if(seen.charAt(38) == '0') {
                    seen.setCharAt(38, '1');
                    if (first == 0) Queue.queuePush(2456);
                    else if (second == 0) Queue.queuePush((2435) | (first << 3));
                    else Queue.queuePush(38 | (first << 3) | second);
                }
                if(seen.charAt(43) == '0') {
                    seen.setCharAt(43, '1');
                    if (first == 0) Queue.queuePush(2800);
                    else if (second == 0) Queue.queuePush((2758) | (first << 3));
                    else Queue.queuePush(43 | (first << 3) | second);
                }
                if(seen.charAt(44) == '0') {
                    seen.setCharAt(44, '1');
                    if (first == 0) Queue.queuePush(2856);
                    else if (second == 0) Queue.queuePush((2821) | (first << 3));
                    else Queue.queuePush(44 | (first << 3) | second);
                }
                if(seen.charAt(45) == '0') {
                    seen.setCharAt(45, '1');
                    if (first == 0) Queue.queuePush(2912);
                    else if (second == 0) Queue.queuePush((2884) | (first << 3));
                    else Queue.queuePush(45 | (first << 3) | second);
                }
                break;
                case 38:
                if(seen.charAt(30) == '0') {
                    seen.setCharAt(30, '1');
                    if (first == 0) Queue.queuePush(1984);
                    else if (second == 0) Queue.queuePush((1928) | (first << 3));
                    else Queue.queuePush(30 | (first << 3) | second);
                }
                if(seen.charAt(31) == '0') {
                    seen.setCharAt(31, '1');
                    if (first == 0) Queue.queuePush(1992);
                    else if (second == 0) Queue.queuePush((1985) | (first << 3));
                    else Queue.queuePush(31 | (first << 3) | second);
                }
                if(seen.charAt(32) == '0') {
                    seen.setCharAt(32, '1');
                    if (first == 0) Queue.queuePush(2064);
                    else if (second == 0) Queue.queuePush((2050) | (first << 3));
                    else Queue.queuePush(32 | (first << 3) | second);
                }
                if(seen.charAt(37) == '0') {
                    seen.setCharAt(37, '1');
                    if (first == 0) Queue.queuePush(2424);
                    else if (second == 0) Queue.queuePush((2375) | (first << 3));
                    else Queue.queuePush(37 | (first << 3) | second);
                }
                if(seen.charAt(39) == '0') {
                    seen.setCharAt(39, '1');
                    if (first == 0) Queue.queuePush(2520);
                    else if (second == 0) Queue.queuePush((2499) | (first << 3));
                    else Queue.queuePush(39 | (first << 3) | second);
                }
                if(seen.charAt(44) == '0') {
                    seen.setCharAt(44, '1');
                    if (first == 0) Queue.queuePush(2864);
                    else if (second == 0) Queue.queuePush((2822) | (first << 3));
                    else Queue.queuePush(44 | (first << 3) | second);
                }
                if(seen.charAt(45) == '0') {
                    seen.setCharAt(45, '1');
                    if (first == 0) Queue.queuePush(2920);
                    else if (second == 0) Queue.queuePush((2885) | (first << 3));
                    else Queue.queuePush(45 | (first << 3) | second);
                }
                if(seen.charAt(46) == '0') {
                    seen.setCharAt(46, '1');
                    if (first == 0) Queue.queuePush(2976);
                    else if (second == 0) Queue.queuePush((2948) | (first << 3));
                    else Queue.queuePush(46 | (first << 3) | second);
                }
                break;
                case 39:
                if(seen.charAt(31) == '0') {
                    seen.setCharAt(31, '1');
                    if (first == 0) Queue.queuePush(1984);
                    else if (second == 0) Queue.queuePush((1992) | (first << 3));
                    else Queue.queuePush(31 | (first << 3) | second);
                }
                if(seen.charAt(32) == '0') {
                    seen.setCharAt(32, '1');
                    if (first == 0) Queue.queuePush(2056);
                    else if (second == 0) Queue.queuePush((2049) | (first << 3));
                    else Queue.queuePush(32 | (first << 3) | second);
                }
                if(seen.charAt(33) == '0') {
                    seen.setCharAt(33, '1');
                    if (first == 0) Queue.queuePush(2128);
                    else if (second == 0) Queue.queuePush((2114) | (first << 3));
                    else Queue.queuePush(33 | (first << 3) | second);
                }
                if(seen.charAt(38) == '0') {
                    seen.setCharAt(38, '1');
                    if (first == 0) Queue.queuePush(2488);
                    else if (second == 0) Queue.queuePush((2439) | (first << 3));
                    else Queue.queuePush(38 | (first << 3) | second);
                }
                if(seen.charAt(40) == '0') {
                    seen.setCharAt(40, '1');
                    if (first == 0) Queue.queuePush(2584);
                    else if (second == 0) Queue.queuePush((2563) | (first << 3));
                    else Queue.queuePush(40 | (first << 3) | second);
                }
                if(seen.charAt(45) == '0') {
                    seen.setCharAt(45, '1');
                    if (first == 0) Queue.queuePush(2928);
                    else if (second == 0) Queue.queuePush((2886) | (first << 3));
                    else Queue.queuePush(45 | (first << 3) | second);
                }
                if(seen.charAt(46) == '0') {
                    seen.setCharAt(46, '1');
                    if (first == 0) Queue.queuePush(2984);
                    else if (second == 0) Queue.queuePush((2949) | (first << 3));
                    else Queue.queuePush(46 | (first << 3) | second);
                }
                if(seen.charAt(47) == '0') {
                    seen.setCharAt(47, '1');
                    if (first == 0) Queue.queuePush(3040);
                    else if (second == 0) Queue.queuePush((3012) | (first << 3));
                    else Queue.queuePush(47 | (first << 3) | second);
                }
                break;
                case 40:
                if(seen.charAt(32) == '0') {
                    seen.setCharAt(32, '1');
                    if (first == 0) Queue.queuePush(2112);
                    else if (second == 0) Queue.queuePush((2056) | (first << 3));
                    else Queue.queuePush(32 | (first << 3) | second);
                }
                if(seen.charAt(33) == '0') {
                    seen.setCharAt(33, '1');
                    if (first == 0) Queue.queuePush(2120);
                    else if (second == 0) Queue.queuePush((2113) | (first << 3));
                    else Queue.queuePush(33 | (first << 3) | second);
                }
                if(seen.charAt(34) == '0') {
                    seen.setCharAt(34, '1');
                    if (first == 0) Queue.queuePush(2192);
                    else if (second == 0) Queue.queuePush((2178) | (first << 3));
                    else Queue.queuePush(34 | (first << 3) | second);
                }
                if(seen.charAt(39) == '0') {
                    seen.setCharAt(39, '1');
                    if (first == 0) Queue.queuePush(2552);
                    else if (second == 0) Queue.queuePush((2503) | (first << 3));
                    else Queue.queuePush(39 | (first << 3) | second);
                }
                if(seen.charAt(41) == '0') {
                    seen.setCharAt(41, '1');
                    if (first == 0) Queue.queuePush(2648);
                    else if (second == 0) Queue.queuePush((2627) | (first << 3));
                    else Queue.queuePush(41 | (first << 3) | second);
                }
                if(seen.charAt(46) == '0') {
                    seen.setCharAt(46, '1');
                    if (first == 0) Queue.queuePush(2992);
                    else if (second == 0) Queue.queuePush((2950) | (first << 3));
                    else Queue.queuePush(46 | (first << 3) | second);
                }
                if(seen.charAt(47) == '0') {
                    seen.setCharAt(47, '1');
                    if (first == 0) Queue.queuePush(3048);
                    else if (second == 0) Queue.queuePush((3013) | (first << 3));
                    else Queue.queuePush(47 | (first << 3) | second);
                }
                break;
                case 41:
                if(seen.charAt(33) == '0') {
                    seen.setCharAt(33, '1');
                    if (first == 0) Queue.queuePush(2112);
                    else if (second == 0) Queue.queuePush((2120) | (first << 3));
                    else Queue.queuePush(33 | (first << 3) | second);
                }
                if(seen.charAt(34) == '0') {
                    seen.setCharAt(34, '1');
                    if (first == 0) Queue.queuePush(2184);
                    else if (second == 0) Queue.queuePush((2177) | (first << 3));
                    else Queue.queuePush(34 | (first << 3) | second);
                }
                if(seen.charAt(40) == '0') {
                    seen.setCharAt(40, '1');
                    if (first == 0) Queue.queuePush(2616);
                    else if (second == 0) Queue.queuePush((2567) | (first << 3));
                    else Queue.queuePush(40 | (first << 3) | second);
                }
                if(seen.charAt(47) == '0') {
                    seen.setCharAt(47, '1');
                    if (first == 0) Queue.queuePush(3056);
                    else if (second == 0) Queue.queuePush((3014) | (first << 3));
                    else Queue.queuePush(47 | (first << 3) | second);
                }
                break;
                case 43:
                if(seen.charAt(35) == '0') {
                    seen.setCharAt(35, '1');
                    if (first == 0) Queue.queuePush(2240);
                    else if (second == 0) Queue.queuePush((2248) | (first << 3));
                    else Queue.queuePush(35 | (first << 3) | second);
                }
                if(seen.charAt(36) == '0') {
                    seen.setCharAt(36, '1');
                    if (first == 0) Queue.queuePush(2312);
                    else if (second == 0) Queue.queuePush((2305) | (first << 3));
                    else Queue.queuePush(36 | (first << 3) | second);
                }
                if(seen.charAt(37) == '0') {
                    seen.setCharAt(37, '1');
                    if (first == 0) Queue.queuePush(2384);
                    else if (second == 0) Queue.queuePush((2370) | (first << 3));
                    else Queue.queuePush(37 | (first << 3) | second);
                }
                if(seen.charAt(44) == '0') {
                    seen.setCharAt(44, '1');
                    if (first == 0) Queue.queuePush(2840);
                    else if (second == 0) Queue.queuePush((2819) | (first << 3));
                    else Queue.queuePush(44 | (first << 3) | second);
                }
                break;
                case 44:
                if(seen.charAt(36) == '0') {
                    seen.setCharAt(36, '1');
                    if (first == 0) Queue.queuePush(2368);
                    else if (second == 0) Queue.queuePush((2312) | (first << 3));
                    else Queue.queuePush(36 | (first << 3) | second);
                }
                if(seen.charAt(37) == '0') {
                    seen.setCharAt(37, '1');
                    if (first == 0) Queue.queuePush(2376);
                    else if (second == 0) Queue.queuePush((2369) | (first << 3));
                    else Queue.queuePush(37 | (first << 3) | second);
                }
                if(seen.charAt(38) == '0') {
                    seen.setCharAt(38, '1');
                    if (first == 0) Queue.queuePush(2448);
                    else if (second == 0) Queue.queuePush((2434) | (first << 3));
                    else Queue.queuePush(38 | (first << 3) | second);
                }
                if(seen.charAt(43) == '0') {
                    seen.setCharAt(43, '1');
                    if (first == 0) Queue.queuePush(2808);
                    else if (second == 0) Queue.queuePush((2759) | (first << 3));
                    else Queue.queuePush(43 | (first << 3) | second);
                }
                if(seen.charAt(45) == '0') {
                    seen.setCharAt(45, '1');
                    if (first == 0) Queue.queuePush(2904);
                    else if (second == 0) Queue.queuePush((2883) | (first << 3));
                    else Queue.queuePush(45 | (first << 3) | second);
                }
                break;
                case 45:
                if(seen.charAt(37) == '0') {
                    seen.setCharAt(37, '1');
                    if (first == 0) Queue.queuePush(2368);
                    else if (second == 0) Queue.queuePush((2376) | (first << 3));
                    else Queue.queuePush(37 | (first << 3) | second);
                }
                if(seen.charAt(38) == '0') {
                    seen.setCharAt(38, '1');
                    if (first == 0) Queue.queuePush(2440);
                    else if (second == 0) Queue.queuePush((2433) | (first << 3));
                    else Queue.queuePush(38 | (first << 3) | second);
                }
                if(seen.charAt(39) == '0') {
                    seen.setCharAt(39, '1');
                    if (first == 0) Queue.queuePush(2512);
                    else if (second == 0) Queue.queuePush((2498) | (first << 3));
                    else Queue.queuePush(39 | (first << 3) | second);
                }
                if(seen.charAt(44) == '0') {
                    seen.setCharAt(44, '1');
                    if (first == 0) Queue.queuePush(2872);
                    else if (second == 0) Queue.queuePush((2823) | (first << 3));
                    else Queue.queuePush(44 | (first << 3) | second);
                }
                if(seen.charAt(46) == '0') {
                    seen.setCharAt(46, '1');
                    if (first == 0) Queue.queuePush(2968);
                    else if (second == 0) Queue.queuePush((2947) | (first << 3));
                    else Queue.queuePush(46 | (first << 3) | second);
                }
                break;
                case 46:
                if(seen.charAt(38) == '0') {
                    seen.setCharAt(38, '1');
                    if (first == 0) Queue.queuePush(2496);
                    else if (second == 0) Queue.queuePush((2440) | (first << 3));
                    else Queue.queuePush(38 | (first << 3) | second);
                }
                if(seen.charAt(39) == '0') {
                    seen.setCharAt(39, '1');
                    if (first == 0) Queue.queuePush(2504);
                    else if (second == 0) Queue.queuePush((2497) | (first << 3));
                    else Queue.queuePush(39 | (first << 3) | second);
                }
                if(seen.charAt(40) == '0') {
                    seen.setCharAt(40, '1');
                    if (first == 0) Queue.queuePush(2576);
                    else if (second == 0) Queue.queuePush((2562) | (first << 3));
                    else Queue.queuePush(40 | (first << 3) | second);
                }
                if(seen.charAt(45) == '0') {
                    seen.setCharAt(45, '1');
                    if (first == 0) Queue.queuePush(2936);
                    else if (second == 0) Queue.queuePush((2887) | (first << 3));
                    else Queue.queuePush(45 | (first << 3) | second);
                }
                if(seen.charAt(47) == '0') {
                    seen.setCharAt(47, '1');
                    if (first == 0) Queue.queuePush(3032);
                    else if (second == 0) Queue.queuePush((3011) | (first << 3));
                    else Queue.queuePush(47 | (first << 3) | second);
                }
                break;
                case 47:
                if(seen.charAt(39) == '0') {
                    seen.setCharAt(39, '1');
                    if (first == 0) Queue.queuePush(2496);
                    else if (second == 0) Queue.queuePush((2504) | (first << 3));
                    else Queue.queuePush(39 | (first << 3) | second);
                }
                if(seen.charAt(40) == '0') {
                    seen.setCharAt(40, '1');
                    if (first == 0) Queue.queuePush(2568);
                    else if (second == 0) Queue.queuePush((2561) | (first << 3));
                    else Queue.queuePush(40 | (first << 3) | second);
                }
                if(seen.charAt(41) == '0') {
                    seen.setCharAt(41, '1');
                    if (first == 0) Queue.queuePush(2640);
                    else if (second == 0) Queue.queuePush((2626) | (first << 3));
                    else Queue.queuePush(41 | (first << 3) | second);
                }
                if(seen.charAt(46) == '0') {
                    seen.setCharAt(46, '1');
                    if (first == 0) Queue.queuePush(3000);
                    else if (second == 0) Queue.queuePush((2951) | (first << 3));
                    else Queue.queuePush(46 | (first << 3) | second);
                }
                break;
            }
        }
    }
}
