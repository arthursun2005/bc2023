package sapphire;

import battlecode.common.*;

public class Headquarter extends Robot {
    public Headquarter(RobotController rc) throws GameActionException {
        super(rc);

        int cnt = rc.readSharedArray(Constants.SHARED_HQ + 5);
        rc.writeSharedArray(Constants.SHARED_HQ + 5, cnt + 1);
        Util.writeCoords(cnt, rc.getLocation());
    }

    int lastSuicideNote = 0;

    int totalAda = 0;
    int totalMana = 0;
    int anchorsMade = 0;

    public void suicide() throws GameActionException {
        RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        int launcherCnt = 0;
        for (RobotInfo ri : enemies)
            if (ri.type.equals(RobotType.LAUNCHER))
                launcherCnt++;
        boolean xD = friends.length == 0 && enemies.length >= 12 && launcherCnt >= 9;
        int cnt = rc.readSharedArray(Constants.SHARED_HQ + 5);
        int note = rc.readSharedArray(Constants.SUICIDE_NOTE);
        if (xD)
            note++;
        if (note - lastSuicideNote == cnt)
            rc.resign();
        lastSuicideNote = note;
        rc.writeSharedArray(Constants.SUICIDE_NOTE, note);
    }

    public void trySpawn(RobotType type, MapLocation loc) throws GameActionException {
        if (loc == null) {
            int width = rc.getMapWidth();
            int height = rc.getMapHeight();
            loc = new MapLocation(width / 2, height / 2);
        }
        MapLocation[] locs = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(), rc.getType().actionRadiusSquared);
        MapLocation best = null;
        for (MapLocation a : locs) {
            if (rc.canBuildRobot(type, a)) {
                if (best == null || a.distanceSquaredTo(loc) < best.distanceSquaredTo(loc))
                    best = a;
            }
        }
        if (best != null) {
            rc.buildRobot(type, best);
            if (type.equals(RobotType.CARRIER)) {
                totalAda += 50;
            } else if (type.equals(RobotType.LAUNCHER)) {
                totalMana += 60;
            }
        }
    }

    public boolean enemyHQIsDangerouslyCloseLmfao() throws GameActionException {
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        for (RobotInfo ri : enemies) {
            if (ri.type.equals(RobotType.HEADQUARTERS)) {
                return true;
            }
        }
        return false;
    }

    public void run() throws GameActionException {
        for (int k = 0; k < 5; k++) {
            if (totalAda - anchorsMade * 200 >= 1500 && totalMana - anchorsMade * 300 >= 1250 && rc.senseNearbyRobots(-1, rc.getTeam().opponent()).length <= 1) {
                if (rc.canBuildAnchor(Anchor.STANDARD)) {
                    rc.buildAnchor(Anchor.STANDARD);
                    anchorsMade += 1;
                }
                break;
            }

            int ada = rc.getResourceAmount(ResourceType.ADAMANTIUM);
            int mana = rc.getResourceAmount(ResourceType.MANA);
            RobotType toMake = null;

            if (ada - mana >= 150) {
                toMake = RobotType.CARRIER;
            } else if (mana >= 60 && rc.getRoundNum() >= 2) {
                toMake = RobotType.LAUNCHER;
            } else {
                toMake = RobotType.CARRIER;
            }

            if (rc.getRoundNum() < 3 && enemyHQIsDangerouslyCloseLmfao()) {
                toMake = RobotType.LAUNCHER;
            }

            if (toMake.equals(RobotType.CARRIER))
                trySpawn(toMake, tracker.getRandomWell());

            if (toMake.equals(RobotType.LAUNCHER))
                trySpawn(toMake, null);
        }
        suicide();
    }
}