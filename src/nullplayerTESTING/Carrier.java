package nullplayerTESTING;

import battlecode.common.*;

public class Carrier extends Robot {
    public Carrier(RobotController rc) throws GameActionException {
        super(rc);
    }

    public void runUnit() throws GameActionException {

        MapLocation loc;
        int x = 2, y = 3;
        int width = rc.getMapWidth(), height= rc.getMapHeight();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("        ");
        int temp = Clock.getBytecodesLeft();


        stringBuilder.replace(3, 4, "a");
       System.out.println(Clock.getBytecodesLeft() - temp);
    }
}
