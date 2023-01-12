package Mining4;

import battlecode.common.*;

import java.util.ArrayList;
public class Carrier extends Robot {

    static MapLocation minLoc = null;
    static MapLocation parentLoc = null;
    static boolean takenAnchor = false;
    static ArrayList<MapLocation> wells = new ArrayList<>();
    static int curWellSharedArray = 50;
    static int choice = 0;
    static int exploreturns = 0;
    static boolean needInstruction = true;
    static MapLocation target = null;
    static boolean mined = false;


    public Carrier(RobotController rc) throws GameActionException {
        super(rc);
    }

    public void init() {

    }

    public void runUnit() throws GameActionException {
        moveToLocation(new MapLocation(5, 18));
    }
}
