package nullplayer;

import battlecode.common.*;

public strictfp class RobotPlayer {
    @SuppressWarnings("unused")
    public static void run(RobotController rc) throws GameActionException 
    {
        while (true) {
            try {
                int width = rc.getMapWidth();
                int height = rc.getMapHeight();
                int a = height / 5;
                int b = 2 * height / 5;
                int c = 3 * height / 5;
                int d = 4 * height / 5;
                for (int y = 0; y < a; y++)
                {
                    MapLocation A = new MapLocation(0, y);
                    MapLocation B = new MapLocation(width - 1, y);
                    rc.setIndicatorLine(A, B, 91, 206, 250);
                }
                for (int y = a; y < b; y++)
                {
                    MapLocation A = new MapLocation(0, y);
                    MapLocation B = new MapLocation(width - 1, y);
                    rc.setIndicatorLine(A, B, 245, 169, 184);
                }
                for (int y = b; y < c; y++)
                {
                    MapLocation A = new MapLocation(0, y);
                    MapLocation B = new MapLocation(width - 1, y);
                    rc.setIndicatorLine(A, B, 255, 255, 255);
                }
                for (int y = d; y < height; y++)
                {
                    MapLocation A = new MapLocation(0, y);
                    MapLocation B = new MapLocation(width - 1, y);
                    rc.setIndicatorLine(A, B, 91, 206, 250);
                }
                for (int y = c; y < d; y++)
                {
                    MapLocation A = new MapLocation(0, y);
                    MapLocation B = new MapLocation(width - 1, y);
                    rc.setIndicatorLine(A, B, 245, 169, 184);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Clock.yield();
            }
        }
    }
}
