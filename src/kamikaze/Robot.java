package kamikaze;

import battlecode.common.*;
import java.util.*;

public abstract class Robot
{
    RobotController rc;
    Random rng;

    Attack attack;
    Tracker tracker;

    int creationRound;
    int turnCount;

    public Robot(RobotController rc) throws GameActionException
    {
        this.rc = rc;
        Util.rc = rc;
        attack = new Attack(rc);
        tracker = new Tracker(rc);
        rng = new Random(rc.getID() + 369);
        creationRound = rc.getRoundNum();
        turnCount = 0;
    }

    public void prepare() throws GameActionException
    {
        turnCount++;
    }

    abstract void run() throws GameActionException;
}
