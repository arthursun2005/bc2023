package kamikaze;

import battlecode.common.*;

public class Headquarter extends Robot
{
    public Headquarter(RobotController rc) throws GameActionException {
        super(rc);

        int cnt = rc.readSharedArray(Constants.SHARED_HQ + 5);
        rc.writeSharedArray(Constants.SHARED_HQ + 5, cnt + 1);
        Util.writeCoords(cnt, rc.getLocation());
    }

    int lastSuicideNote = 0;

    public void suicide() throws GameActionException {
        RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        int launcherCnt = 0;
        for (RobotInfo ri : enemies) if (ri.type.equals(RobotType.LAUNCHER)) launcherCnt++;
        boolean xD = friends.length == 0 && enemies.length >= 9 && launcherCnt >= 6;
        int cnt = rc.readSharedArray(Constants.SHARED_HQ + 5);
        int note = rc.readSharedArray(Constants.SUICIDE_NOTE);
        if (xD) note++;
        if (note - lastSuicideNote == cnt) rc.resign();
        lastSuicideNote = note;
        rc.writeSharedArray(Constants.SUICIDE_NOTE, note);
    }

    public void trySpawn(RobotType type, MapLocation loc) throws GameActionException {
        if (loc == null)
        {
            int width = rc.getMapWidth();
            int height = rc.getMapHeight();
            loc = new MapLocation(width / 2, height / 2);
        }
        MapLocation[] locs = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(), rc.getType().actionRadiusSquared);
        MapLocation best = null;
        for (MapLocation a : locs) {
            if (rc.canBuildRobot(type, a)) {
                if (best == null || a.distanceSquaredTo(loc) < best.distanceSquaredTo(loc)) best = a;
            }
        }
        if (best != null) rc.buildRobot(type, best);
    }

    public void run() throws GameActionException
    {
        trySpawn(RobotType.CARRIER, tracker.getBestWell());
        suicide();
    }
}