package nullplayerTESTING;

import battlecode.common.*;

public class Headquarter extends Robot {

    /**
     * Run a single turn for a Headquarters.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */

    static Direction globalDir = Direction.CENTER;

    public Headquarter(RobotController rc) throws GameActionException {
        super(rc);
    }
    public void runUnit() throws GameActionException {


        Direction dir; MapLocation newLoc;
        do {
            dir = directions[rng.nextInt(directions.length)];
            newLoc = rc.getLocation().add(dir);
        } while (!rc.canBuildRobot(RobotType.CARRIER, newLoc));
        rc.setIndicatorString("Trying to build a carrier");
        rc.buildRobot(RobotType.CARRIER, newLoc);
    }
}
