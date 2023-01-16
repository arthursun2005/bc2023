package dragonite;

import battlecode.common.*;

public class Headquarter extends Robot
{
    static RobotType toMake = RobotType.CARRIER;

    static Tracker tracker;

    static int totalAda = 0;
    static int totalMana = 0;
    static int anchorsMade = 1;

    public Headquarter(RobotController rc) throws GameActionException {
        super(rc);

        int cnt = rc.readSharedArray(63);
        rc.writeSharedArray(63, cnt + 1);
        writeCoords(cnt, rc.getLocation());
    }

    public boolean tryMake(Direction bestDir) throws GameActionException
    {
        Direction[] dirs = getGreedyDirections(bestDir);
        for (Direction dir : dirs)
        {
            MapLocation loc = rc.getLocation().add(dir);
            if (rc.canBuildRobot(toMake, loc))
            {
                rc.buildRobot(toMake, loc);
                if (toMake.equals(RobotType.CARRIER))
                {
                    totalAda += 50;
                }else if (toMake.equals(RobotType.LAUNCHER)) {
                    totalMana += 60;
                }
                return true;
            }
        }
        return false;
    }

    public boolean enemyHQIsDangerouslyCloseLmfao() throws GameActionException
    {
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        for (RobotInfo ri : enemies)
        {
            if (ri.type.equals(RobotType.HEADQUARTERS))
            {
                return true;
            }
        }
        return false;
    }

    public void run() throws GameActionException
    {
        tracker.updateWells(rc);
        MapLocation well = tracker.getOptimalWell();
        boolean made = false;

        if (totalAda - anchorsMade * 666 >= 0 && totalMana - anchorsMade * 369 >= 0)
        {
            if (rc.canBuildAnchor(Anchor.STANDARD))
            {
                rc.buildAnchor(Anchor.STANDARD);
                anchorsMade += 1;
            }
            return;
        }

        if ((well == null && rc.getRoundNum() >= 30) || enemyHQIsDangerouslyCloseLmfao())
        {
            toMake = RobotType.LAUNCHER;
        }

        if (toMake.equals(RobotType.CARRIER))
        {
            Direction bestDir = rc.getLocation().directionTo(well);
            if (bestDir == null || bestDir.equals(Direction.CENTER))
            {
                bestDir = Direction.NORTH;
            }
            made = tryMake(bestDir);
        }

        if (toMake.equals(RobotType.LAUNCHER))
        {
            MapLocation center = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
            Direction bestDir = rc.getLocation().directionTo(center);
            if (bestDir == null || bestDir.equals(Direction.CENTER))
            {
                bestDir = Direction.NORTH;
            }
            made = tryMake(bestDir);
        }

        if (made)
        {
            int ada = rc.getResourceAmount(ResourceType.ADAMANTIUM);
            int mana = rc.getResourceAmount(ResourceType.MANA);

            if (ada - mana >= 150)
            {
                toMake = RobotType.CARRIER;
            }else if (mana >= 90 || mana - ada >= 150)
            {
                toMake = RobotType.LAUNCHER;
            }else{
                toMake = RobotType.CARRIER;
            }
        }
    }
}
