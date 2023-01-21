package memtest;

import battlecode.common.*;

public strictfp class RobotPlayer {
    @SuppressWarnings("unused")
    String a = new String("<fat>");
    public static void run(RobotController rc) throws GameActionException 
    {
        while (true) {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Clock.yield();
            }
        }
    }
}
