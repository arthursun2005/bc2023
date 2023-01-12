package dragonite;

import battlecode.common.*;

public class Headquarter extends Robot
{
    static RobotType toMake = RobotType.CARRIER;

    public Headquarter(RobotController rc) throws GameActionException {
        super(rc);
    }

    public void run() throws GameActionException
    {
        Direction dir = Direction.NORTH;
        for (int i = 0; i < 8; i++)
        {
            MapLocation loc = rc.getLocation().add(dir);
            if (rc.canBuildRobot(toMake, loc))
            {
                rc.buildRobot(toMake, loc);
                break;
            }
            dir = dir.rotateLeft();
        }
    }
}
