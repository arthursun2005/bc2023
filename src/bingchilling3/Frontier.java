package bingchilling3;

import battlecode.common.*;

// HUGELY TODO
// manages the order of IDs
// since lower ID robots move first
// we sometimes want to sort by ascending order, sometimes by descending order
// for example, when moving forward fast, we sort with the low ID robots at the front
// if we want to retreat fast, we want to sort low ID robots closer to HQ

public class Frontier
{
    public boolean shouldHalt(RobotController rc) throws GameActionException
    {
        RobotInfo[] friends = rc.senseNearbyRobots(2, rc.getTeam());
        return false;
    }
}
