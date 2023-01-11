package dragonite;

import battlecode.common.*;

import java.util.Random;

public abstract class Robot
{
    static Random rng;
    static RobotController rc;

    public Robot(RobotController rc)
    {
        Robot.rc = rc;
        rng = new Random(rc.getID());
    }

    abstract void run() throws GameActionException;

    public void moveTo(MapLocation loc) throws GameActionException
    {
        Direction dir = rc.getLocation().directionTo(loc);
        if (rc.canMove(dir))
        {
            rc.move(dir);
        }
    }

    static void spreadOut(boolean all) throws GameActionException
    {
    }
}
