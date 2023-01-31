package loners;

import battlecode.common.*;

public class Movement {
    RobotController rc;

    boolean rotateRight = true;
    int ever;
    MapLocation wall;
    MapLocation prevTarget;

    void reset() {
        ever = 696969;
        wall = null;
        prevTarget = null;
        rotateRight = rc.getRoundNum() % 2 == 0;
    }

    Movement(RobotController rc) {
        this.rc = rc;
        reset();
    }

    void moveTo(MapLocation target) throws GameActionException {
        if (target == null) return;
        if (rc.getLocation().equals(target)) return;
        if (rc.getLocation().distanceSquaredTo(target) <= 2 && rc.canSenseRobotAtLocation(target)) return;

        //different target? ==> previous data does not help!
        if (prevTarget == null || !target.equals(prevTarget)) reset();

        //If I'm at a minimum distance to the target, I'm free!
        MapLocation myLoc = rc.getLocation();
        int d = myLoc.distanceSquaredTo(target);
        if (d <= ever) reset();

        //Update data
        prevTarget = target;
        ever = Math.min(d, ever);

        //If there's an obstacle I try to go around it [until I'm free] instead of going to the target directly
        Direction dir = myLoc.directionTo(target);
        if (wall != null) dir = myLoc.directionTo(wall);

        //This should not happen for a single unit, but whatever
        if (rc.canMove(dir)) reset();

        //I rotate clockwise or counterclockwise (depends on 'rotateRight'). If I try to go out of the map I change the orientation
        //Note that we have to try at most 16 times since we can switch orientation in the middle of the loop. (It can be done more efficiently)
        for (int i = 0; i < 16; ++i){
            if (rc.canMove(dir)){
                rc.move(dir);
                return;
            }
            MapLocation newLoc = myLoc.add(dir);
            if (!rc.onTheMap(newLoc)) rotateRight = !rotateRight;
            //If I could not go in that direction and it was not outside of the map, then this is the latest obstacle found
            else wall = myLoc.add(dir);
            if (rotateRight) dir = dir.rotateRight();
            else dir = dir.rotateLeft();
        }

        if (rc.canMove(dir)) rc.move(dir);
    }
}
