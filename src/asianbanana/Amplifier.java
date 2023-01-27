package asianbanana;

import battlecode.common.*;

public class Amplifier extends Robot
{
    Symmetry symmetry;

    public Amplifier(RobotController rc) throws GameActionException
    {
        super(rc);
        symmetry = new Symmetry(this);
    }

    public void run() throws GameActionException {
        if (tracker.isEnemyGroup()) {
            tracker.addEnemyGroup();
            moveTo(tracker.getClosestHQLoc());
            rc.setIndicatorString("Added " + rc.getLocation().x + rc.getLocation().y);
            return;
        }

        MapLocation threat = attack.getThreat();

        moveTo(symmetry.update());//randomUpdate());

        rc.setIndicatorString(String.valueOf(tracker.symmetry));
        tracker.tryFindSymmetry();
    }
}
