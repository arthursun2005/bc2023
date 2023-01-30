package nullplayerTESTING;

import battlecode.common.*;

public class Carrier extends Robot {
    public Carrier(RobotController rc) throws GameActionException {
        super(rc);
    }

    public void runUnit() throws GameActionException {

        int temp = Clock.getBytecodesLeft();
        rng.nextInt();
        System.out.println("OMEGASUS: " + (temp - Clock.getBytecodesLeft()));
    }
}
