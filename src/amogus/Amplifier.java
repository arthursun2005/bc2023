package amogus;

import battlecode.common.*;

public class Amplifier extends Robot
{
    MapLocation target, parentLoc, oppositeLoc;

    public Amplifier(RobotController rc) throws GameActionException {
        super(rc);

        int width = rc.getMapWidth();
        int height = rc.getMapHeight();

        parentLoc = tracker.getClosestHQLoc();
        oppositeLoc = new MapLocation(width - parentLoc.x - 1, height - parentLoc.y - 1);
        target = oppositeLoc;
    }

    public void run() throws GameActionException {
        if (tracker.possi[3]==0) {
            MapLocation opp1 = new MapLocation(oppositeLoc.x,parentLoc.y);
            MapLocation opp2 = new MapLocation(parentLoc.x,oppositeLoc.y);
            if (tracker.possi[1]==0) target = opp2;
            if (tracker.possi[2]==0) target = opp1;
            else {
                if (rc.getLocation().distanceSquaredTo(opp1) < rc.getLocation().distanceSquaredTo(opp2)) {
                    target = opp1;
                }
                else {
                    target = opp2;
                }
            }
        }

        moveTo(target);
        tracker.tryFindSymmetry();
    }
}
