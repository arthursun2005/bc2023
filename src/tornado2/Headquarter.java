package tornado2;

import battlecode.common.*;

public class Headquarter extends Robot {
    public Headquarter(RobotController rc) throws GameActionException {
        super(rc);

        int cnt = rc.readSharedArray(Constants.SHARED_HQ + 5);
        pompom = cnt;
        rc.writeSharedArray(Constants.SHARED_HQ + 5, cnt + 1);
        Util.writeCoords(cnt, rc.getLocation());
    }

    int lastSuicideNote = 0;

    int anchorsMade = 0;
    int jerrysMade = 0;

    int pompom = 0;

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

    int getTotalAda() throws GameActionException {
        return rc.readSharedArray(Constants.TOTAL_ADA);
    }

    int getTotalMana() throws GameActionException {
        return rc.readSharedArray(Constants.TOTAL_MANA);
    }

    int getTotalElixir() throws GameActionException {
        return rc.readSharedArray(Constants.TOTAL_ELIXIR);
    }

    MapLocation[] locs;

    public void trySpawn(RobotType type, MapLocation loc, int mul) throws GameActionException {
        if (loc == null) {
            int width = rc.getMapWidth();
            int height = rc.getMapHeight();
            loc = new MapLocation(width / 2, height / 2);
        }
        MapLocation best = null;
        for (MapLocation a : locs) {
            if (rc.canBuildRobot(type, a)) {
                if (best == null || a.distanceSquaredTo(loc) * mul < best.distanceSquaredTo(loc) * mul)
                    best = a;
            }
        }
        if (best != null) {
            rc.buildRobot(type, best);
            if (type.equals(RobotType.CARRIER)) {
                rc.writeSharedArray(Constants.TOTAL_ADA, getTotalAda() + 50);
            } else if (type.equals(RobotType.LAUNCHER)) {
                rc.writeSharedArray(Constants.TOTAL_MANA, getTotalMana() + 60);
            } else if (type.equals(RobotType.DESTABILIZER)) {
                rc.writeSharedArray(Constants.TOTAL_ELIXIR, getTotalElixir() + 200);
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
        // int width = rc.getMapWidth();
        // int height = rc.getMapHeight();
        // MapLocation center = new MapLocation(width / 2, height / 2);
        // RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());
        // return friends.length == 0
        // && Math.min(Math.abs(rc.getLocation().x - center.x),
        // Math.abs(rc.getLocation().y - center.y)) < 8;
    }

    int countCarriersNearby() throws GameActionException {
        return 0;
        // int cnt = 0;
        // RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());
        // for (RobotInfo ri : friends) {
        // if (ri.type.equals(RobotType.CARRIER)) {
        // cnt++;
        // }
        // }
        // return cnt;
    }

    boolean lol;

    void helpMe() throws GameActionException {
        if (pompom == 0)
            tracker.clearDistress();

        MapLocation best = null;
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        MapLocation me = rc.getLocation();
        for (RobotInfo a : enemies) {
            if (best == null || a.location.distanceSquaredTo(me) < best.distanceSquaredTo(me))
                best = a.location;
        }
        if (best != null)
            tracker.signalDistress(best);
    }

    public void run() throws GameActionException {
        if (getTotalAda() >= 6000 && getTotalMana() >= 8000 && rc.getRobotCount() > 75) {
            rc.writeSharedArray(Constants.MAKE_ELIXIR, 1);
        }
        if (getTotalAda() >= 1250 && rc.readSharedArray(Constants.AGGRO_CARRIERS) == 0) {
            rc.writeSharedArray(Constants.AGGRO_CARRIERS, rc.getRoundNum());
        }
        if (getTotalAda() >= 1750 && rc.readSharedArray(Constants.AGGRO_CARRIERS) < 5000) {
            rc.writeSharedArray(Constants.AGGRO_CARRIERS, rc.getRoundNum() + 5000);
        }
        if (rc.getRoundNum() == 1)
            lol = enemyHQIsDangerouslyCloseLmfao();
        MapLocation well = tracker.getRandomWell();
        locs = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(), rc.getType().actionRadiusSquared);
        for (int k = 0; k < 5; k++) {
            int ada = rc.getResourceAmount(ResourceType.ADAMANTIUM);
            int mana = rc.getResourceAmount(ResourceType.MANA);
            int elixir = rc.getResourceAmount(ResourceType.ELIXIR);

            if (getTotalAda() - anchorsMade * 600 >= 5000 && getTotalMana() - anchorsMade * 1000 >= 7000
                    && rc.senseNearbyRobots(-1, rc.getTeam().opponent()).length <= 1 && elixir < 200) {
                rc.setIndicatorString("trying to build anchor ...");
                if (rc.canBuildAnchor(Anchor.STANDARD)) {
                    rc.buildAnchor(Anchor.STANDARD);
                    anchorsMade += 1;
                }
                break;
            }

            RobotType toMake = null;

            if (mana >= 60 && (rc.getRoundNum() >= 2 || k == 4)) {
                toMake = RobotType.LAUNCHER;
            } else if (countCarriersNearby() < 1_000_000) {
                toMake = RobotType.CARRIER;
            }

            if (rc.getRoundNum() == 1 && lol) {
                toMake = RobotType.LAUNCHER;
            }

            if (elixir >= 200) {
                toMake = RobotType.DESTABILIZER;
            }

            rc.setIndicatorString("trying to make " + toMake + " totals: " + getTotalAda() + " " + getTotalMana() + " "
                    + locs.length);

            if (toMake == null)
                continue;

            if (toMake.equals(RobotType.CARRIER))
                trySpawn(toMake, well, 1);
            else
                trySpawn(toMake, null, 1);
        }
        helpMe();
        suicide();
    }
}
