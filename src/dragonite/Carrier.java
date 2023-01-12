package dragonite;

import battlecode.common.*;

public class Carrier extends Robot
{
    static Tracker tracker;

    public Carrier(RobotController rc) throws GameActionException
    {
        super(rc);
    }

    public void run() throws GameActionException
    {
        moveTo(new MapLocation(5, 18));
        // spreadOut(false);
        // tracker.updateWells();
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
