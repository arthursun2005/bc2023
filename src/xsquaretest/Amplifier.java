package xsquaretest;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class Amplifier extends Robot
{
    public Amplifier(RobotController rc) throws GameActionException
    {
        super(rc);
    }

    public void run() throws GameActionException {
        moveTo(new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2));
        rc.setIndicatorString(String.valueOf(tracker.symmetry));
        tracker.tryFindSymmetry();
    }
}
