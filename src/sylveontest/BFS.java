package sylveontest;

import battlecode.common.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class BFS {
    RobotController rc;

    public BFS(RobotController rc) {
        this.rc = rc;
    }

    public class BetterPriorityQueue {
        StringBuilder stb;

        int lastdist = 0;
        public BetterPriorityQueue() {
            stb = new StringBuilder();
            stb.append("                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ");
            lastdist = 0;
        }

        public void insert(int dist, int loc) {
            String temp = stb.substring(loc << 2, (loc << 2) +4);
            if (Objects.equals(temp, "    ")) stb.replace(loc << 2, (loc << 2) + 4, Integer.toString((dist + 10) * 100)); // 14, 18
            else if (Integer.parseInt(temp)/ 100 - 10 > dist) stb.replace(loc << 2, (loc << 2) + 4, Integer.toString((dist + 10) * 100));
        }

        public int pop() {
//            System.out.println(stb);
            for (; lastdist < 69; lastdist++) {
                int cur = stb.indexOf(Integer.toString((lastdist + 10) * 100));

                if (cur != -1) {
                    stb.replace(cur, cur + 4, "    ");
                    return cur / 4;
                }
            }
            return -1;
        }
    }

    public void tryBFS(MapLocation currentTarget) throws GameActionException {


        RobotInfo[] robotInfos = rc.senseNearbyRobots();
        MapLocation curLoc = rc.getLocation();

        MapLocation starting = rc.getLocation();
        MapLocation mapLocation;

        boolean seen[] = new boolean[100];

        BetterPriorityQueue pq = new BetterPriorityQueue();

        //
        pq.insert(1, 44);

        int target = (currentTarget.x - curLoc.x + 4) * 10 + (currentTarget.y - curLoc.y + 4);

        while (true) {
            System.out.println("A: " + Clock.getBytecodesLeft());
            int cur = pq.pop();
            System.out.println("B: " + Clock.getBytecodesLeft());
            if (cur == -1) break;

            if (seen[cur]) continue;
            seen[cur] = true;

            if (cur == target) {
                System.out.println("DONE");

            }
            // cur - 11, cur - 10, cur - 9, cur - 1, cur + 1, cur + 9, cur + 10, cur + 11

            int x = cur / 10, y = cur % 10;

            int dist = pq.lastdist - Math.max(Math.abs(curLoc.x + (x - 4) - currentTarget.x), Math.abs(curLoc.y + (y - 4) - currentTarget.y));
            System.out.println("C: " + Clock.getBytecodesLeft());
            MapLocation mapLoc;

            mapLoc = new MapLocation(curLoc.x + ((x + -1) - 4), curLoc.y + ((y + -1) - 4));
            if (rc.canSenseLocation(mapLoc) && rc.sensePassability(mapLoc) && !seen[10 * (x + -1) + (y + -1)] && !rc.canSenseRobotAtLocation(mapLoc)) {
                pq.insert(dist + Math.max(Math.abs(curLoc.x + ((x + -1) - 4) - currentTarget.x), Math.abs(curLoc.y + ((y + -1) - 4) - currentTarget.y)), (x + -1) * 10 + (y + -1));
            }

            System.out.println("D: " + Clock.getBytecodesLeft());

            mapLoc = new MapLocation(curLoc.x + ((x + -1) - 4), curLoc.y + ((y + 0) - 4));
            if (rc.canSenseLocation(mapLoc) && rc.sensePassability(mapLoc) && !seen[10 * (x + -1) + (y + 0)] && !rc.canSenseRobotAtLocation(mapLoc)) {
                pq.insert(dist + Math.max(Math.abs(curLoc.x + ((x + -1) - 4) - currentTarget.x), Math.abs(curLoc.y + ((y + 0) - 4) - currentTarget.y)), (x + -1) * 10 + (y + 0));
            }

            System.out.println("E: " + Clock.getBytecodesLeft());

            mapLoc = new MapLocation(curLoc.x + ((x + -1) - 4), curLoc.y + ((y + 1) - 4));
            if (rc.canSenseLocation(mapLoc) && rc.sensePassability(mapLoc) && !seen[10 * (x + -1) + (y + 1)] && !rc.canSenseRobotAtLocation(mapLoc)) {
                pq.insert(dist + Math.max(Math.abs(curLoc.x + ((x + -1) - 4) - currentTarget.x), Math.abs(curLoc.y + ((y + 1) - 4) - currentTarget.y)), (x + -1) * 10 + (y + 1));
            }

            System.out.println("F: " + Clock.getBytecodesLeft());

            mapLoc = new MapLocation(curLoc.x + ((x + 0) - 4), curLoc.y + ((y + -1) - 4));
            if (rc.canSenseLocation(mapLoc) && rc.sensePassability(mapLoc) && !seen[10 * (x + 0) + (y + -1)] && !rc.canSenseRobotAtLocation(mapLoc)) {
                pq.insert(dist + Math.max(Math.abs(curLoc.x + ((x + 0) - 4) - currentTarget.x), Math.abs(curLoc.y + ((y + -1) - 4) - currentTarget.y)), (x + 0) * 10 + (y + -1));
            }
            mapLoc = new MapLocation(curLoc.x + ((x + 0) - 4), curLoc.y + ((y + 0) - 4));
            if (rc.canSenseLocation(mapLoc) && rc.sensePassability(mapLoc) && !seen[10 * (x + 0) + (y + 0)] && !rc.canSenseRobotAtLocation(mapLoc)) {
                pq.insert(dist + Math.max(Math.abs(curLoc.x + ((x + 0) - 4) - currentTarget.x), Math.abs(curLoc.y + ((y + 0) - 4) - currentTarget.y)), (x + 0) * 10 + (y + 0));
            }
            mapLoc = new MapLocation(curLoc.x + ((x + 0) - 4), curLoc.y + ((y + 1) - 4));
            if (rc.canSenseLocation(mapLoc) && rc.sensePassability(mapLoc) && !seen[10 * (x + 0) + (y + 1)] && !rc.canSenseRobotAtLocation(mapLoc)) {
                pq.insert(dist + Math.max(Math.abs(curLoc.x + ((x + 0) - 4) - currentTarget.x), Math.abs(curLoc.y + ((y + 1) - 4) - currentTarget.y)), (x + 0) * 10 + (y + 1));
            }
            mapLoc = new MapLocation(curLoc.x + ((x + 1) - 4), curLoc.y + ((y + -1) - 4));
            if (rc.canSenseLocation(mapLoc) && rc.sensePassability(mapLoc) && !seen[10 * (x + 1) + (y + -1)] && !rc.canSenseRobotAtLocation(mapLoc)) {
                pq.insert(dist + Math.max(Math.abs(curLoc.x + ((x + 1) - 4) - currentTarget.x), Math.abs(curLoc.y + ((y + -1) - 4) - currentTarget.y)), (x + 1) * 10 + (y + -1));
            }
            mapLoc = new MapLocation(curLoc.x + ((x + 1) - 4), curLoc.y + ((y + 0) - 4));
            if (rc.canSenseLocation(mapLoc) && rc.sensePassability(mapLoc) && !seen[10 * (x + 1) + (y + 0)] && !rc.canSenseRobotAtLocation(mapLoc)) {
                pq.insert(dist + Math.max(Math.abs(curLoc.x + ((x + 1) - 4) - currentTarget.x), Math.abs(curLoc.y + ((y + 0) - 4) - currentTarget.y)), (x + 1) * 10 + (y + 0));
            }
            mapLoc = new MapLocation(curLoc.x + ((x + 1) - 4), curLoc.y + ((y + 1) - 4));
            if (rc.canSenseLocation(mapLoc) && rc.sensePassability(mapLoc) && !seen[10 * (x + 1) + (y + 1)] && !rc.canSenseRobotAtLocation(mapLoc)) {
                pq.insert(dist + Math.max(Math.abs(curLoc.x + ((x + 1) - 4) - currentTarget.x), Math.abs(curLoc.y + ((y + 1) - 4) - currentTarget.y)), (x + 1) * 10 + (y + 1));
            }

            System.out.println("G: " + Clock.getBytecodesLeft());

        }

    }
}