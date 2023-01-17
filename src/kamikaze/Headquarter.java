package kamikaze;

import battlecode.common.*;

public class Headquarter extends Robot
{
    public Headquarter(RobotController rc) throws GameActionException {
        super(rc);

        int cnt = rc.readSharedArray(63);
        rc.writeSharedArray(63, cnt + 1);
        Util.writeCoords(cnt, rc.getLocation());
    }

    public void run() throws GameActionException
    {
    }
}