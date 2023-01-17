package kamikaze;

import battlecode.common.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class Robot
{
    RobotController rc;
    Random rng;

    int creationRound;
    int turnCount;

    public Robot(RobotController rc)
    {
        this.rc = rc;
        Util.rc = rc;
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
