package nullplayer;

import battlecode.common.*;

public strictfp class RobotPlayer {
    @SuppressWarnings("unused")
    public static void run(RobotController rc) throws GameActionException 
    {
        while (true) {
            try {
                MapLocation A = new MapLocation(0, 0);
                MapLocation B = new MapLocation(30, 30);
                rc.setIndicatorLine(A, B, 255, 0, 0);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Clock.yield();
            }
        }
    }
}
