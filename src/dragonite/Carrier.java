package dragonite;

import battlecode.common.*;

public class Carrier extends Robot
{
    public Carrier(RobotController rc) throws GameActionException
    {
        super(rc);
    }

    public void run() throws GameActionException
    {
        spreadOut(false);
        /*
        WellInfo[] nearbyWells = rc.senseNearbyWells();
        if (nearbyWells.length > 0)
        {
            MapLocation loc = null;
            int dist = -1;
            for (WellInfo wi : nearbyWells)
            {
                int w = rc.getLocation().distanceSquaredTo(wi.getMapLocation());
                if (dist == -1 || w < dist)
                {
                    dist = w;
                    loc = wi.getMapLocation();
                }
            }
            moveTo(loc);
            return;
        }
        spreadOut(false);
        */
    }
}
