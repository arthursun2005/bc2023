package dragonite;

import battlecode.common.*;

// HUGELY TODO

// manages positions of significant sites
// such as wells (basically just wells lol idk)

// manages:
// find the best well to go to (with a lot of computations and heuristics)
// if there are a really good well (for example, no friends has reached yet + far away from enemy)
// then: signal the position in the shared array

public class Tracker
{
    static MapLocation bestWellLoc = null;
    static MapLocation bestIslandLoc = null;

    static void updateWells(RobotController rc) throws GameActionException
    {
        WellInfo[] nearbyWells = rc.senseNearbyWells();

        bestWellLoc = null;
        if (nearbyWells.length > 0)
        {
            int dist = -1;
            for (WellInfo wi : nearbyWells)
            {
                if (rc.senseNearbyRobots(wi.getMapLocation(), 2, rc.getTeam()).length >= 6)
                {
                    continue;
                }
                int w = rc.getLocation().distanceSquaredTo(wi.getMapLocation());
                if (dist == -1 || w < dist)
                {
                    dist = w;
                    bestWellLoc = wi.getMapLocation();
                }
            }
        }
    }

    static void updateIslands(RobotController rc) throws GameActionException
    {
        int[] islands = rc.senseNearbyIslands();
        bestIslandLoc = null;
        int dist = -1;
        for (int id : islands) {
            if (rc.senseTeamOccupyingIsland(id) == rc.getTeam()) continue;
            MapLocation[] thisIslandLocs = rc.senseNearbyIslandLocations(id);
            for (MapLocation nLoc : thisIslandLocs) {
                int w = rc.getLocation().distanceSquaredTo(nLoc);
                if (dist == -1 || w < dist)
                {
                    dist = w;
                    bestIslandLoc = nLoc;
                }
            }
        }
    }

    static MapLocation getOptimalWell() throws GameActionException
    {
        return bestWellLoc;
    }

    static MapLocation getOptimalIsland() throws GameActionException
    {
        return bestIslandLoc;
    }
}
