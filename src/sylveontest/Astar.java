package sylveontest;

import battlecode.common.*;

public class Astar {
    RobotController rc;
    int validLocation[] = new int[120];

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
            if (temp.equals("    ")) stb.replace(loc << 2, (loc << 2) + 4, Integer.toString((dist + 10) * 100)); // 14, 18
            else if (Integer.parseInt(temp)/ 100 - 10 > dist) {
                stb.replace(loc << 2, (loc << 2) + 4, Integer.toString((dist + 10) * 100));
            }
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
    BetterPriorityQueue pq = new BetterPriorityQueue();
    public Astar(RobotController rc) throws GameActionException {
        this.rc = rc;

        MapInfo mapInfo[] = rc.senseNearbyMapInfos();

        MapLocation loc;
        for (MapInfo mapInfo1 : mapInfo) {
            loc = mapInfo1.getMapLocation();
            if (mapInfo1.isPassable()) validLocation[(loc.x % 10) * 10 + loc.y % 10] = 1;
            if (!mapInfo1.getCurrentDirection().equals(Direction.CENTER)) validLocation[(loc.x % 10) * 10 + loc.y % 10] = 1 + mapInfo1.getCurrentDirection().getDirectionOrderNum();
        }
    }


    public void tryBFS(MapLocation currentTarget) throws GameActionException {


        // INIT TAKES 420 BYTECODES


//        System.out.println("start init: " + Clock.getBytecodesLeft());
        MapLocation curLoc = rc.getLocation();
        boolean invalid[] = new boolean[81];

        RobotInfo robotInfos[] = rc.senseNearbyRobots();
        MapLocation loc;

        for (RobotInfo robotInfo : robotInfos) {
            loc = robotInfo.getLocation();
            invalid[(loc.x - curLoc.x + 4) * 9 + (loc.y - curLoc.y + 4)] = true;
        }

        pq.insert(Math.max(Math.abs(curLoc.x - currentTarget.x), Math.abs(curLoc.y - currentTarget.y)), 4 * 9 + 4);
        pq.lastdist = Math.max(Math.abs(curLoc.x - currentTarget.x), Math.abs(curLoc.y - currentTarget.y));

        int target = (currentTarget.x - curLoc.x + 4) * 9 + (currentTarget.y - curLoc.y + 4);
        int offsetx = curLoc.x % 10, offsety = curLoc.y % 10;

//        System.out.println("end init: " + Clock.getBytecodesLeft());


        while (true) {
            int cur = pq.pop();

            System.out.println(cur);
            if (cur == -1) break;

            if (invalid[cur]) continue;
            invalid[cur] = true;

            if (cur == target) {
                System.out.println("DONE");
                return;
            }

            // cur - 11, cur
            // - 10, cur - 9, cur - 1, cur + 1, cur + 9, cur + 10, cur + 11
            int x = cur / 9, y = cur % 9;

            int dist = pq.lastdist - Math.max(Math.abs(curLoc.x + (x - 4) - currentTarget.x), Math.abs(curLoc.y + (y - 4) - currentTarget.y)) + 1;
            if (rc.getID() == 11160) {
                System.out.println(x + " " + y);
            }

            switch(cur) {
                case 2:
                    if (!invalid[3] && validLocation[((-4 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 3);
                    }
                    if (!invalid[10] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 10);
                    }
                    if (!invalid[11] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 11);
                    }
                    if (!invalid[12] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 12);
                    }
                    break;
                case 3:
                    if (!invalid[2] && validLocation[((-4 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 2);
                    }
                    if (!invalid[4] && validLocation[((-4 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 4);
                    }
                    if (!invalid[11] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 11);
                    }
                    if (!invalid[12] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 12);
                    }
                    if (!invalid[13] && validLocation[((-3 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 13);
                    }
                    break;
                case 4:
                    if (!invalid[3] && validLocation[((-4 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 3);
                    }
                    if (!invalid[5] && validLocation[((-4 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 5);
                    }
                    if (!invalid[12] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 12);
                    }
                    if (!invalid[13] && validLocation[((-3 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 13);
                    }
                    if (!invalid[14] && validLocation[((-3 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 14);
                    }
                    break;
                case 5:
                    if (!invalid[4] && validLocation[((-4 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 4);
                    }
                    if (!invalid[6] && validLocation[((-4 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 6);
                    }
                    if (!invalid[13] && validLocation[((-3 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 13);
                    }
                    if (!invalid[14] && validLocation[((-3 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 14);
                    }
                    if (!invalid[15] && validLocation[((-3 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 15);
                    }
                    break;
                case 6:
                    if (!invalid[5] && validLocation[((-4 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 5);
                    }
                    if (!invalid[14] && validLocation[((-3 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 14);
                    }
                    if (!invalid[15] && validLocation[((-3 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 15);
                    }
                    if (!invalid[16] && validLocation[((-3 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 16);
                    }
                    break;
                case 10:
                    if (!invalid[2] && validLocation[((-4 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 2);
                    }
                    if (!invalid[11] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 11);
                    }
                    if (!invalid[18] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 18);
                    }
                    if (!invalid[19] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 19);
                    }
                    if (!invalid[20] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 20);
                    }
                    break;
                case 11:
                    if (!invalid[2] && validLocation[((-4 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 2);
                    }
                    if (!invalid[3] && validLocation[((-4 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 3);
                    }
                    if (!invalid[10] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 10);
                    }
                    if (!invalid[12] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 12);
                    }
                    if (!invalid[19] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 19);
                    }
                    if (!invalid[20] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 20);
                    }
                    if (!invalid[21] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 21);
                    }
                    break;
                case 12:
                    if (!invalid[2] && validLocation[((-4 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 2);
                    }
                    if (!invalid[3] && validLocation[((-4 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 3);
                    }
                    if (!invalid[4] && validLocation[((-4 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 4);
                    }
                    if (!invalid[11] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 11);
                    }
                    if (!invalid[13] && validLocation[((-3 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 13);
                    }
                    if (!invalid[20] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 20);
                    }
                    if (!invalid[21] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 21);
                    }
                    if (!invalid[22] && validLocation[((-2 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 22);
                    }
                    break;
                case 13:
                    if (!invalid[3] && validLocation[((-4 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 3);
                    }
                    if (!invalid[4] && validLocation[((-4 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 4);
                    }
                    if (!invalid[5] && validLocation[((-4 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 5);
                    }
                    if (!invalid[12] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 12);
                    }
                    if (!invalid[14] && validLocation[((-3 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 14);
                    }
                    if (!invalid[21] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 21);
                    }
                    if (!invalid[22] && validLocation[((-2 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 22);
                    }
                    if (!invalid[23] && validLocation[((-2 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 23);
                    }
                    break;
                case 14:
                    if (!invalid[4] && validLocation[((-4 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 4);
                    }
                    if (!invalid[5] && validLocation[((-4 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 5);
                    }
                    if (!invalid[6] && validLocation[((-4 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 6);
                    }
                    if (!invalid[13] && validLocation[((-3 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 13);
                    }
                    if (!invalid[15] && validLocation[((-3 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 15);
                    }
                    if (!invalid[22] && validLocation[((-2 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 22);
                    }
                    if (!invalid[23] && validLocation[((-2 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 23);
                    }
                    if (!invalid[24] && validLocation[((-2 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 24);
                    }
                    break;
                case 15:
                    if (!invalid[5] && validLocation[((-4 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 5);
                    }
                    if (!invalid[6] && validLocation[((-4 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 6);
                    }
                    if (!invalid[14] && validLocation[((-3 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 14);
                    }
                    if (!invalid[16] && validLocation[((-3 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 16);
                    }
                    if (!invalid[23] && validLocation[((-2 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 23);
                    }
                    if (!invalid[24] && validLocation[((-2 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 24);
                    }
                    if (!invalid[25] && validLocation[((-2 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 25);
                    }
                    break;
                case 16:
                    if (!invalid[6] && validLocation[((-4 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -4 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 6);
                    }
                    if (!invalid[15] && validLocation[((-3 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 15);
                    }
                    if (!invalid[24] && validLocation[((-2 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 24);
                    }
                    if (!invalid[25] && validLocation[((-2 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 25);
                    }
                    if (!invalid[26] && validLocation[((-2 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 26);
                    }
                    break;
                case 18:
                    if (!invalid[10] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 10);
                    }
                    if (!invalid[19] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 19);
                    }
                    if (!invalid[27] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 27);
                    }
                    if (!invalid[28] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 28);
                    }
                    break;
                case 19:
                    if (!invalid[10] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 10);
                    }
                    if (!invalid[11] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 11);
                    }
                    if (!invalid[18] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 18);
                    }
                    if (!invalid[20] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 20);
                    }
                    if (!invalid[27] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 27);
                    }
                    if (!invalid[28] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 28);
                    }
                    if (!invalid[29] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 29);
                    }
                    break;
                case 20:
                    if (!invalid[10] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 10);
                    }
                    if (!invalid[11] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 11);
                    }
                    if (!invalid[12] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 12);
                    }
                    if (!invalid[19] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 19);
                    }
                    if (!invalid[21] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 21);
                    }
                    if (!invalid[28] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 28);
                    }
                    if (!invalid[29] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 29);
                    }
                    if (!invalid[30] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 30);
                    }
                    break;
                case 21:
                    if (!invalid[11] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 11);
                    }
                    if (!invalid[12] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 12);
                    }
                    if (!invalid[13] && validLocation[((-3 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 13);
                    }
                    if (!invalid[20] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 20);
                    }
                    if (!invalid[22] && validLocation[((-2 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 22);
                    }
                    if (!invalid[29] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 29);
                    }
                    if (!invalid[30] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 30);
                    }
                    if (!invalid[31] && validLocation[((-1 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 31);
                    }
                    break;
                case 22:
                    if (!invalid[12] && validLocation[((-3 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 12);
                    }
                    if (!invalid[13] && validLocation[((-3 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 13);
                    }
                    if (!invalid[14] && validLocation[((-3 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 14);
                    }
                    if (!invalid[21] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 21);
                    }
                    if (!invalid[23] && validLocation[((-2 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 23);
                    }
                    if (!invalid[30] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 30);
                    }
                    if (!invalid[31] && validLocation[((-1 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 31);
                    }
                    if (!invalid[32] && validLocation[((-1 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 32);
                    }
                    break;
                case 23:
                    if (!invalid[13] && validLocation[((-3 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 13);
                    }
                    if (!invalid[14] && validLocation[((-3 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 14);
                    }
                    if (!invalid[15] && validLocation[((-3 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 15);
                    }
                    if (!invalid[22] && validLocation[((-2 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 22);
                    }
                    if (!invalid[24] && validLocation[((-2 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 24);
                    }
                    if (!invalid[31] && validLocation[((-1 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 31);
                    }
                    if (!invalid[32] && validLocation[((-1 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 32);
                    }
                    if (!invalid[33] && validLocation[((-1 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 33);
                    }
                    break;
                case 24:
                    if (!invalid[14] && validLocation[((-3 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 14);
                    }
                    if (!invalid[15] && validLocation[((-3 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 15);
                    }
                    if (!invalid[16] && validLocation[((-3 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 16);
                    }
                    if (!invalid[23] && validLocation[((-2 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 23);
                    }
                    if (!invalid[25] && validLocation[((-2 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 25);
                    }
                    if (!invalid[32] && validLocation[((-1 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 32);
                    }
                    if (!invalid[33] && validLocation[((-1 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 33);
                    }
                    if (!invalid[34] && validLocation[((-1 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 34);
                    }
                    break;
                case 25:
                    if (!invalid[15] && validLocation[((-3 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 15);
                    }
                    if (!invalid[16] && validLocation[((-3 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 16);
                    }
                    if (!invalid[24] && validLocation[((-2 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 24);
                    }
                    if (!invalid[26] && validLocation[((-2 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 26);
                    }
                    if (!invalid[33] && validLocation[((-1 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 33);
                    }
                    if (!invalid[34] && validLocation[((-1 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 34);
                    }
                    if (!invalid[35] && validLocation[((-1 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 35);
                    }
                    break;
                case 26:
                    if (!invalid[16] && validLocation[((-3 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -3 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 16);
                    }
                    if (!invalid[25] && validLocation[((-2 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 25);
                    }
                    if (!invalid[34] && validLocation[((-1 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 34);
                    }
                    if (!invalid[35] && validLocation[((-1 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 35);
                    }
                    break;
                case 27:
                    if (!invalid[18] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 18);
                    }
                    if (!invalid[19] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 19);
                    }
                    if (!invalid[28] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 28);
                    }
                    if (!invalid[36] && validLocation[((0 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 36);
                    }
                    if (!invalid[37] && validLocation[((0 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 37);
                    }
                    break;
                case 28:
                    if (!invalid[18] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 18);
                    }
                    if (!invalid[19] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 19);
                    }
                    if (!invalid[20] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 20);
                    }
                    if (!invalid[27] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 27);
                    }
                    if (!invalid[29] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 29);
                    }
                    if (!invalid[36] && validLocation[((0 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 36);
                    }
                    if (!invalid[37] && validLocation[((0 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 37);
                    }
                    if (!invalid[38] && validLocation[((0 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 38);
                    }
                    break;
                case 29:
                    if (!invalid[19] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 19);
                    }
                    if (!invalid[20] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 20);
                    }
                    if (!invalid[21] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 21);
                    }
                    if (!invalid[28] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 28);
                    }
                    if (!invalid[30] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 30);
                    }
                    if (!invalid[37] && validLocation[((0 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 37);
                    }
                    if (!invalid[38] && validLocation[((0 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 38);
                    }
                    if (!invalid[39] && validLocation[((0 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 39);
                    }
                    break;
                case 30:
                    if (!invalid[20] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 20);
                    }
                    if (!invalid[21] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 21);
                    }
                    if (!invalid[22] && validLocation[((-2 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 22);
                    }
                    if (!invalid[29] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 29);
                    }
                    if (!invalid[31] && validLocation[((-1 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 31);
                    }
                    if (!invalid[38] && validLocation[((0 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 38);
                    }
                    if (!invalid[39] && validLocation[((0 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 39);
                    }
                    if (!invalid[40] && validLocation[((0 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 40);
                    }
                    break;
                case 31:
                    if (!invalid[21] && validLocation[((-2 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 21);
                    }
                    if (!invalid[22] && validLocation[((-2 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 22);
                    }
                    if (!invalid[23] && validLocation[((-2 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 23);
                    }
                    if (!invalid[30] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 30);
                    }
                    if (!invalid[32] && validLocation[((-1 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 32);
                    }
                    if (!invalid[39] && validLocation[((0 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 39);
                    }
                    if (!invalid[40] && validLocation[((0 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 40);
                    }
                    if (!invalid[41] && validLocation[((0 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 41);
                    }
                    break;
                case 32:
                    if (!invalid[22] && validLocation[((-2 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 22);
                    }
                    if (!invalid[23] && validLocation[((-2 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 23);
                    }
                    if (!invalid[24] && validLocation[((-2 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 24);
                    }
                    if (!invalid[31] && validLocation[((-1 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 31);
                    }
                    if (!invalid[33] && validLocation[((-1 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 33);
                    }
                    if (!invalid[40] && validLocation[((0 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 40);
                    }
                    if (!invalid[41] && validLocation[((0 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 41);
                    }
                    if (!invalid[42] && validLocation[((0 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 42);
                    }
                    break;
                case 33:
                    if (!invalid[23] && validLocation[((-2 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 23);
                    }
                    if (!invalid[24] && validLocation[((-2 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 24);
                    }
                    if (!invalid[25] && validLocation[((-2 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 25);
                    }
                    if (!invalid[32] && validLocation[((-1 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 32);
                    }
                    if (!invalid[34] && validLocation[((-1 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 34);
                    }
                    if (!invalid[41] && validLocation[((0 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 41);
                    }
                    if (!invalid[42] && validLocation[((0 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 42);
                    }
                    if (!invalid[43] && validLocation[((0 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 43);
                    }
                    break;
                case 34:
                    if (!invalid[24] && validLocation[((-2 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 24);
                    }
                    if (!invalid[25] && validLocation[((-2 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 25);
                    }
                    if (!invalid[26] && validLocation[((-2 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 26);
                    }
                    if (!invalid[33] && validLocation[((-1 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 33);
                    }
                    if (!invalid[35] && validLocation[((-1 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 35);
                    }
                    if (!invalid[42] && validLocation[((0 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 42);
                    }
                    if (!invalid[43] && validLocation[((0 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 43);
                    }
                    if (!invalid[44] && validLocation[((0 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 44);
                    }
                    break;
                case 35:
                    if (!invalid[25] && validLocation[((-2 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 25);
                    }
                    if (!invalid[26] && validLocation[((-2 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -2 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 26);
                    }
                    if (!invalid[34] && validLocation[((-1 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 34);
                    }
                    if (!invalid[43] && validLocation[((0 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 43);
                    }
                    if (!invalid[44] && validLocation[((0 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 44);
                    }
                    break;
                case 36:
                    if (!invalid[27] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 27);
                    }
                    if (!invalid[28] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 28);
                    }
                    if (!invalid[37] && validLocation[((0 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 37);
                    }
                    if (!invalid[45] && validLocation[((1 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 45);
                    }
                    if (!invalid[46] && validLocation[((1 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 46);
                    }
                    break;
                case 37:
                    if (!invalid[27] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 27);
                    }
                    if (!invalid[28] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 28);
                    }
                    if (!invalid[29] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 29);
                    }
                    if (!invalid[36] && validLocation[((0 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 36);
                    }
                    if (!invalid[38] && validLocation[((0 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 38);
                    }
                    if (!invalid[45] && validLocation[((1 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 45);
                    }
                    if (!invalid[46] && validLocation[((1 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 46);
                    }
                    if (!invalid[47] && validLocation[((1 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 47);
                    }
                    break;
                case 38:
                    if (!invalid[28] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 28);
                    }
                    if (!invalid[29] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 29);
                    }
                    if (!invalid[30] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 30);
                    }
                    if (!invalid[37] && validLocation[((0 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 37);
                    }
                    if (!invalid[39] && validLocation[((0 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 39);
                    }
                    if (!invalid[46] && validLocation[((1 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 46);
                    }
                    if (!invalid[47] && validLocation[((1 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 47);
                    }
                    if (!invalid[48] && validLocation[((1 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 48);
                    }
                    break;
                case 39:
                    if (!invalid[29] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 29);
                    }
                    if (!invalid[30] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 30);
                    }
                    if (!invalid[31] && validLocation[((-1 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 31);
                    }
                    if (!invalid[38] && validLocation[((0 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 38);
                    }
                    if (!invalid[40] && validLocation[((0 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 40);
                    }
                    if (!invalid[47] && validLocation[((1 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 47);
                    }
                    if (!invalid[48] && validLocation[((1 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 48);
                    }
                    if (!invalid[49] && validLocation[((1 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 49);
                    }
                    break;
                case 40:
                    if (!invalid[30] && validLocation[((-1 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 30);
                    }
                    if (!invalid[31] && validLocation[((-1 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 31);
                    }
                    if (!invalid[32] && validLocation[((-1 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 32);
                    }
                    if (!invalid[39] && validLocation[((0 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 39);
                    }
                    if (!invalid[41] && validLocation[((0 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 41);
                    }
                    if (!invalid[48] && validLocation[((1 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 48);
                    }
                    if (!invalid[49] && validLocation[((1 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 49);
                    }
                    if (!invalid[50] && validLocation[((1 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 50);
                    }
                    break;
                case 41:
                    if (!invalid[31] && validLocation[((-1 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 31);
                    }
                    if (!invalid[32] && validLocation[((-1 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 32);
                    }
                    if (!invalid[33] && validLocation[((-1 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 33);
                    }
                    if (!invalid[40] && validLocation[((0 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 40);
                    }
                    if (!invalid[42] && validLocation[((0 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 42);
                    }
                    if (!invalid[49] && validLocation[((1 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 49);
                    }
                    if (!invalid[50] && validLocation[((1 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 50);
                    }
                    if (!invalid[51] && validLocation[((1 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 51);
                    }
                    break;
                case 42:
                    if (!invalid[32] && validLocation[((-1 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 32);
                    }
                    if (!invalid[33] && validLocation[((-1 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 33);
                    }
                    if (!invalid[34] && validLocation[((-1 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 34);
                    }
                    if (!invalid[41] && validLocation[((0 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 41);
                    }
                    if (!invalid[43] && validLocation[((0 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 43);
                    }
                    if (!invalid[50] && validLocation[((1 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 50);
                    }
                    if (!invalid[51] && validLocation[((1 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 51);
                    }
                    if (!invalid[52] && validLocation[((1 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 52);
                    }
                    break;
                case 43:
                    if (!invalid[33] && validLocation[((-1 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 33);
                    }
                    if (!invalid[34] && validLocation[((-1 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 34);
                    }
                    if (!invalid[35] && validLocation[((-1 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 35);
                    }
                    if (!invalid[42] && validLocation[((0 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 42);
                    }
                    if (!invalid[44] && validLocation[((0 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 44);
                    }
                    if (!invalid[51] && validLocation[((1 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 51);
                    }
                    if (!invalid[52] && validLocation[((1 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 52);
                    }
                    if (!invalid[53] && validLocation[((1 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 53);
                    }
                    break;
                case 44:
                    if (!invalid[34] && validLocation[((-1 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 34);
                    }
                    if (!invalid[35] && validLocation[((-1 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + -1 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 35);
                    }
                    if (!invalid[43] && validLocation[((0 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 43);
                    }
                    if (!invalid[52] && validLocation[((1 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 52);
                    }
                    if (!invalid[53] && validLocation[((1 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 53);
                    }
                    break;
                case 45:
                    if (!invalid[36] && validLocation[((0 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 36);
                    }
                    if (!invalid[37] && validLocation[((0 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 37);
                    }
                    if (!invalid[46] && validLocation[((1 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 46);
                    }
                    if (!invalid[54] && validLocation[((2 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 54);
                    }
                    if (!invalid[55] && validLocation[((2 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 55);
                    }
                    break;
                case 46:
                    if (!invalid[36] && validLocation[((0 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 36);
                    }
                    if (!invalid[37] && validLocation[((0 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 37);
                    }
                    if (!invalid[38] && validLocation[((0 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 38);
                    }
                    if (!invalid[45] && validLocation[((1 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 45);
                    }
                    if (!invalid[47] && validLocation[((1 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 47);
                    }
                    if (!invalid[54] && validLocation[((2 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 54);
                    }
                    if (!invalid[55] && validLocation[((2 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 55);
                    }
                    if (!invalid[56] && validLocation[((2 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 56);
                    }
                    break;
                case 47:
                    if (!invalid[37] && validLocation[((0 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 37);
                    }
                    if (!invalid[38] && validLocation[((0 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 38);
                    }
                    if (!invalid[39] && validLocation[((0 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 39);
                    }
                    if (!invalid[46] && validLocation[((1 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 46);
                    }
                    if (!invalid[48] && validLocation[((1 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 48);
                    }
                    if (!invalid[55] && validLocation[((2 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 55);
                    }
                    if (!invalid[56] && validLocation[((2 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 56);
                    }
                    if (!invalid[57] && validLocation[((2 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 57);
                    }
                    break;
                case 48:
                    if (!invalid[38] && validLocation[((0 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 38);
                    }
                    if (!invalid[39] && validLocation[((0 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 39);
                    }
                    if (!invalid[40] && validLocation[((0 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 40);
                    }
                    if (!invalid[47] && validLocation[((1 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 47);
                    }
                    if (!invalid[49] && validLocation[((1 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 49);
                    }
                    if (!invalid[56] && validLocation[((2 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 56);
                    }
                    if (!invalid[57] && validLocation[((2 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 57);
                    }
                    if (!invalid[58] && validLocation[((2 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 58);
                    }
                    break;
                case 49:
                    if (!invalid[39] && validLocation[((0 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 39);
                    }
                    if (!invalid[40] && validLocation[((0 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 40);
                    }
                    if (!invalid[41] && validLocation[((0 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 41);
                    }
                    if (!invalid[48] && validLocation[((1 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 48);
                    }
                    if (!invalid[50] && validLocation[((1 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 50);
                    }
                    if (!invalid[57] && validLocation[((2 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 57);
                    }
                    if (!invalid[58] && validLocation[((2 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 58);
                    }
                    if (!invalid[59] && validLocation[((2 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 59);
                    }
                    break;
                case 50:
                    if (!invalid[40] && validLocation[((0 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 40);
                    }
                    if (!invalid[41] && validLocation[((0 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 41);
                    }
                    if (!invalid[42] && validLocation[((0 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 42);
                    }
                    if (!invalid[49] && validLocation[((1 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 49);
                    }
                    if (!invalid[51] && validLocation[((1 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 51);
                    }
                    if (!invalid[58] && validLocation[((2 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 58);
                    }
                    if (!invalid[59] && validLocation[((2 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 59);
                    }
                    if (!invalid[60] && validLocation[((2 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 60);
                    }
                    break;
                case 51:
                    if (!invalid[41] && validLocation[((0 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 41);
                    }
                    if (!invalid[42] && validLocation[((0 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 42);
                    }
                    if (!invalid[43] && validLocation[((0 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 43);
                    }
                    if (!invalid[50] && validLocation[((1 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 50);
                    }
                    if (!invalid[52] && validLocation[((1 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 52);
                    }
                    if (!invalid[59] && validLocation[((2 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 59);
                    }
                    if (!invalid[60] && validLocation[((2 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 60);
                    }
                    if (!invalid[61] && validLocation[((2 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 61);
                    }
                    break;
                case 52:
                    if (!invalid[42] && validLocation[((0 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 42);
                    }
                    if (!invalid[43] && validLocation[((0 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 43);
                    }
                    if (!invalid[44] && validLocation[((0 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 44);
                    }
                    if (!invalid[51] && validLocation[((1 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 51);
                    }
                    if (!invalid[53] && validLocation[((1 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 53);
                    }
                    if (!invalid[60] && validLocation[((2 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 60);
                    }
                    if (!invalid[61] && validLocation[((2 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 61);
                    }
                    if (!invalid[62] && validLocation[((2 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 62);
                    }
                    break;
                case 53:
                    if (!invalid[43] && validLocation[((0 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 43);
                    }
                    if (!invalid[44] && validLocation[((0 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 0 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 44);
                    }
                    if (!invalid[52] && validLocation[((1 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 52);
                    }
                    if (!invalid[61] && validLocation[((2 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 61);
                    }
                    if (!invalid[62] && validLocation[((2 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 62);
                    }
                    break;
                case 54:
                    if (!invalid[45] && validLocation[((1 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 45);
                    }
                    if (!invalid[46] && validLocation[((1 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 46);
                    }
                    if (!invalid[55] && validLocation[((2 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 55);
                    }
                    if (!invalid[64] && validLocation[((3 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 64);
                    }
                    break;
                case 55:
                    if (!invalid[45] && validLocation[((1 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 45);
                    }
                    if (!invalid[46] && validLocation[((1 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 46);
                    }
                    if (!invalid[47] && validLocation[((1 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 47);
                    }
                    if (!invalid[54] && validLocation[((2 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 54);
                    }
                    if (!invalid[56] && validLocation[((2 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 56);
                    }
                    if (!invalid[64] && validLocation[((3 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 64);
                    }
                    if (!invalid[65] && validLocation[((3 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 65);
                    }
                    break;
                case 56:
                    if (!invalid[46] && validLocation[((1 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 46);
                    }
                    if (!invalid[47] && validLocation[((1 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 47);
                    }
                    if (!invalid[48] && validLocation[((1 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 48);
                    }
                    if (!invalid[55] && validLocation[((2 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 55);
                    }
                    if (!invalid[57] && validLocation[((2 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 57);
                    }
                    if (!invalid[64] && validLocation[((3 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 64);
                    }
                    if (!invalid[65] && validLocation[((3 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 65);
                    }
                    if (!invalid[66] && validLocation[((3 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 66);
                    }
                    break;
                case 57:
                    if (!invalid[47] && validLocation[((1 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 47);
                    }
                    if (!invalid[48] && validLocation[((1 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 48);
                    }
                    if (!invalid[49] && validLocation[((1 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 49);
                    }
                    if (!invalid[56] && validLocation[((2 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 56);
                    }
                    if (!invalid[58] && validLocation[((2 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 58);
                    }
                    if (!invalid[65] && validLocation[((3 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 65);
                    }
                    if (!invalid[66] && validLocation[((3 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 66);
                    }
                    if (!invalid[67] && validLocation[((3 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 67);
                    }
                    break;
                case 58:
                    if (!invalid[48] && validLocation[((1 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 48);
                    }
                    if (!invalid[49] && validLocation[((1 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 49);
                    }
                    if (!invalid[50] && validLocation[((1 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 50);
                    }
                    if (!invalid[57] && validLocation[((2 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 57);
                    }
                    if (!invalid[59] && validLocation[((2 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 59);
                    }
                    if (!invalid[66] && validLocation[((3 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 66);
                    }
                    if (!invalid[67] && validLocation[((3 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 67);
                    }
                    if (!invalid[68] && validLocation[((3 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 68);
                    }
                    break;
                case 59:
                    if (!invalid[49] && validLocation[((1 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 49);
                    }
                    if (!invalid[50] && validLocation[((1 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 50);
                    }
                    if (!invalid[51] && validLocation[((1 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 51);
                    }
                    if (!invalid[58] && validLocation[((2 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 58);
                    }
                    if (!invalid[60] && validLocation[((2 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 60);
                    }
                    if (!invalid[67] && validLocation[((3 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 67);
                    }
                    if (!invalid[68] && validLocation[((3 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 68);
                    }
                    if (!invalid[69] && validLocation[((3 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 69);
                    }
                    break;
                case 60:
                    if (!invalid[50] && validLocation[((1 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 50);
                    }
                    if (!invalid[51] && validLocation[((1 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 51);
                    }
                    if (!invalid[52] && validLocation[((1 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 52);
                    }
                    if (!invalid[59] && validLocation[((2 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 59);
                    }
                    if (!invalid[61] && validLocation[((2 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 61);
                    }
                    if (!invalid[68] && validLocation[((3 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 68);
                    }
                    if (!invalid[69] && validLocation[((3 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 69);
                    }
                    if (!invalid[70] && validLocation[((3 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 70);
                    }
                    break;
                case 61:
                    if (!invalid[51] && validLocation[((1 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 51);
                    }
                    if (!invalid[52] && validLocation[((1 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 52);
                    }
                    if (!invalid[53] && validLocation[((1 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 53);
                    }
                    if (!invalid[60] && validLocation[((2 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 60);
                    }
                    if (!invalid[62] && validLocation[((2 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 62);
                    }
                    if (!invalid[69] && validLocation[((3 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 69);
                    }
                    if (!invalid[70] && validLocation[((3 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 70);
                    }
                    break;
                case 62:
                    if (!invalid[52] && validLocation[((1 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 52);
                    }
                    if (!invalid[53] && validLocation[((1 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 1 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 53);
                    }
                    if (!invalid[61] && validLocation[((2 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 61);
                    }
                    if (!invalid[70] && validLocation[((3 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 70);
                    }
                    break;
                case 64:
                    if (!invalid[54] && validLocation[((2 + offsetx) % 10 ) * 10 + (-4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -4 - currentTarget.y)), 54);
                    }
                    if (!invalid[55] && validLocation[((2 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 55);
                    }
                    if (!invalid[56] && validLocation[((2 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 56);
                    }
                    if (!invalid[65] && validLocation[((3 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 65);
                    }
                    if (!invalid[74] && validLocation[((4 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 74);
                    }
                    break;
                case 65:
                    if (!invalid[55] && validLocation[((2 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 55);
                    }
                    if (!invalid[56] && validLocation[((2 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 56);
                    }
                    if (!invalid[57] && validLocation[((2 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 57);
                    }
                    if (!invalid[64] && validLocation[((3 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 64);
                    }
                    if (!invalid[66] && validLocation[((3 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 66);
                    }
                    if (!invalid[74] && validLocation[((4 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 74);
                    }
                    if (!invalid[75] && validLocation[((4 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 75);
                    }
                    break;
                case 66:
                    if (!invalid[56] && validLocation[((2 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 56);
                    }
                    if (!invalid[57] && validLocation[((2 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 57);
                    }
                    if (!invalid[58] && validLocation[((2 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 58);
                    }
                    if (!invalid[65] && validLocation[((3 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 65);
                    }
                    if (!invalid[67] && validLocation[((3 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 67);
                    }
                    if (!invalid[74] && validLocation[((4 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 74);
                    }
                    if (!invalid[75] && validLocation[((4 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 75);
                    }
                    if (!invalid[76] && validLocation[((4 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 76);
                    }
                    break;
                case 67:
                    if (!invalid[57] && validLocation[((2 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 57);
                    }
                    if (!invalid[58] && validLocation[((2 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 58);
                    }
                    if (!invalid[59] && validLocation[((2 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 59);
                    }
                    if (!invalid[66] && validLocation[((3 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 66);
                    }
                    if (!invalid[68] && validLocation[((3 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 68);
                    }
                    if (!invalid[75] && validLocation[((4 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 75);
                    }
                    if (!invalid[76] && validLocation[((4 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 76);
                    }
                    if (!invalid[77] && validLocation[((4 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 77);
                    }
                    break;
                case 68:
                    if (!invalid[58] && validLocation[((2 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 58);
                    }
                    if (!invalid[59] && validLocation[((2 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 59);
                    }
                    if (!invalid[60] && validLocation[((2 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 60);
                    }
                    if (!invalid[67] && validLocation[((3 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 67);
                    }
                    if (!invalid[69] && validLocation[((3 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 69);
                    }
                    if (!invalid[76] && validLocation[((4 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 76);
                    }
                    if (!invalid[77] && validLocation[((4 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 77);
                    }
                    if (!invalid[78] && validLocation[((4 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 78);
                    }
                    break;
                case 69:
                    if (!invalid[59] && validLocation[((2 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 59);
                    }
                    if (!invalid[60] && validLocation[((2 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 60);
                    }
                    if (!invalid[61] && validLocation[((2 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 61);
                    }
                    if (!invalid[68] && validLocation[((3 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 68);
                    }
                    if (!invalid[70] && validLocation[((3 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 70);
                    }
                    if (!invalid[77] && validLocation[((4 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 77);
                    }
                    if (!invalid[78] && validLocation[((4 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 78);
                    }
                    break;
                case 70:
                    if (!invalid[60] && validLocation[((2 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 60);
                    }
                    if (!invalid[61] && validLocation[((2 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 61);
                    }
                    if (!invalid[62] && validLocation[((2 + offsetx) % 10 ) * 10 + (4 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 2 - currentTarget.x), Math.abs(curLoc.y + 4 - currentTarget.y)), 62);
                    }
                    if (!invalid[69] && validLocation[((3 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 69);
                    }
                    if (!invalid[78] && validLocation[((4 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 78);
                    }
                    break;
                case 74:
                    if (!invalid[64] && validLocation[((3 + offsetx) % 10 ) * 10 + (-3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -3 - currentTarget.y)), 64);
                    }
                    if (!invalid[65] && validLocation[((3 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 65);
                    }
                    if (!invalid[66] && validLocation[((3 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 66);
                    }
                    if (!invalid[75] && validLocation[((4 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 75);
                    }
                    break;
                case 75:
                    if (!invalid[65] && validLocation[((3 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 65);
                    }
                    if (!invalid[66] && validLocation[((3 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 66);
                    }
                    if (!invalid[67] && validLocation[((3 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 67);
                    }
                    if (!invalid[74] && validLocation[((4 + offsetx) % 10 ) * 10 + (-2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + -2 - currentTarget.y)), 74);
                    }
                    if (!invalid[76] && validLocation[((4 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 76);
                    }
                    break;
                case 76:
                    if (!invalid[66] && validLocation[((3 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 66);
                    }
                    if (!invalid[67] && validLocation[((3 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 67);
                    }
                    if (!invalid[68] && validLocation[((3 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 68);
                    }
                    if (!invalid[75] && validLocation[((4 + offsetx) % 10 ) * 10 + (-1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + -1 - currentTarget.y)), 75);
                    }
                    if (!invalid[77] && validLocation[((4 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 77);
                    }
                    break;
                case 77:
                    if (!invalid[67] && validLocation[((3 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 67);
                    }
                    if (!invalid[68] && validLocation[((3 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 68);
                    }
                    if (!invalid[69] && validLocation[((3 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 69);
                    }
                    if (!invalid[76] && validLocation[((4 + offsetx) % 10 ) * 10 + (0 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + 0 - currentTarget.y)), 76);
                    }
                    if (!invalid[78] && validLocation[((4 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 78);
                    }
                    break;
                case 78:
                    if (!invalid[68] && validLocation[((3 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 68);
                    }
                    if (!invalid[69] && validLocation[((3 + offsetx) % 10 ) * 10 + (2 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 2 - currentTarget.y)), 69);
                    }
                    if (!invalid[70] && validLocation[((3 + offsetx) % 10 ) * 10 + (3 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 3 - currentTarget.x), Math.abs(curLoc.y + 3 - currentTarget.y)), 70);
                    }
                    if (!invalid[77] && validLocation[((4 + offsetx) % 10 ) * 10 + (1 + offsety) % 10] == 1) {
                        pq.insert(dist + Math.max(Math.abs(curLoc.x + 4 - currentTarget.x), Math.abs(curLoc.y + 1 - currentTarget.y)), 77);
                    }
                    break;
            }
        }

    }
}