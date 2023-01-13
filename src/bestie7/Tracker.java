package bestie7;

import battlecode.common.*;

// HUGELY TODO

// manages positions of significant sites
// such as wells (basically just wells lol idk)

// manages:
// find the best well to go to (with a lot of computations and heuristics)
// if there are a really good well (for example, no friends has reached yet + far away from enemy)
// then: signal the position in the shared array

enum WellState
{
    ADA,
    MANA,
}

enum IslandState
{
    OCCUPIED,
    EMPTY,
}

public class Tracker
{
    static RobotController rc;

    static WellState[][] wells = new WellState[69][69];
    static IslandState[][] islands = new IslandState[69][69];

    static void updateWells(RobotController rc) throws GameActionException
    {
        Tracker.rc = rc;

        WellInfo[] nearbyWells = rc.senseNearbyWells();

        for (WellInfo wi : nearbyWells)
        {
            int x = wi.getMapLocation().x;
            int y = wi.getMapLocation().y;
            if (wi.getResourceType().equals(ResourceType.ADAMANTIUM))
            {
                wells[x][y] = WellState.ADA;
            }
            if (wi.getResourceType().equals(ResourceType.MANA))
            {
                wells[x][y] = WellState.MANA;
            }
        }
    }

    static void updateIslands(RobotController rc) throws GameActionException
    {
        Tracker.rc = rc;

        int[] nis = rc.senseNearbyIslands();
        for (int id : nis) {
            boolean occupied = rc.senseTeamOccupyingIsland(id) == rc.getTeam();
            MapLocation[] thisIslandLocs = rc.senseNearbyIslandLocations(id);
            for (MapLocation nLoc : thisIslandLocs)
            {
                if (occupied)
                {
                    islands[nLoc.x][nLoc.y] = IslandState.OCCUPIED;
                }else{
                    islands[nLoc.x][nLoc.y] = IslandState.EMPTY;
                }
            }
        }
    }

    static MapLocation getOptimalIsland() throws GameActionException
    {
        int dist = -1;
        MapLocation target = null;
        int width = rc.getMapWidth();
        int height = rc.getMapHeight();
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                if (islands[x][y] == null || islands[x][y].equals(IslandState.OCCUPIED))
                {
                    continue;
                }
                MapLocation loc = new MapLocation(x, y);
                int w = rc.getLocation().distanceSquaredTo(loc);
                if (dist == -1 || w < dist)
                {
                    dist = w;
                    target = loc;
                }
            }
        }
        System.out.println("I'm trying to reach " + target + " which I think is an island");
        return target;
    }

    static MapLocation getOptimalWell() throws GameActionException
    {
        int dist = -1;
        MapLocation target = null;
        int width = rc.getMapWidth();
        int height = rc.getMapHeight();
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                if (wells[x][y] == null)
                {
                    continue;
                }
                MapLocation loc = new MapLocation(x, y);
                int w = rc.getLocation().distanceSquaredTo(loc);
                if (rc.senseNearbyRobots(loc, 2, rc.getTeam()).length >= 5)
                {
                    continue;
                }
                if (rc.getRoundNum() > 80 && rc.getType().equals(RobotType.CARRIER) && rc.getID() % 3 != 0 && wells[x][y].equals(WellState.ADA))
                {
                    continue;
                }
                if (dist == -1 || w < dist)
                {
                    dist = w;
                    target = loc;
                }
            }
        }
        return target;
    }
}
