package sapphire;

import battlecode.common.*;

public class Attack {
    RobotController rc;
    Robot robot;

    public Attack(RobotController rc, Robot robot) throws GameActionException {
        this.rc = rc;
        this.robot = robot;
    }

    int times = 6;

    RobotInfo[][] acrossTime = new RobotInfo[times][];

    public int getEnemyWeaknessMetric(RobotInfo enemy) throws GameActionException {
        if (enemy.type.equals(RobotType.HEADQUARTERS))
            return 1_000_000_000;
        int adjustedHealth = enemy.health * 20000 * 80 - rc.getLocation().distanceSquaredTo(enemy.location) * 20000
                + rc.getID();
        if (enemy.type.equals(RobotType.LAUNCHER))
            adjustedHealth -= 1_000_000_000;
        return adjustedHealth;
    }

    public int getEnemyStrengthMetric(RobotInfo enemy) throws GameActionException {
        if (!enemy.type.equals(RobotType.LAUNCHER))
            return -1_000_000_000;
        int adjustedHealth = -rc.getLocation().distanceSquaredTo(enemy.location) * 80 + enemy.health;
        return adjustedHealth;
    }

    public void update() throws GameActionException {
        for (int i = 0; i < times - 1; i++)
            acrossTime[0] = acrossTime[i + 1];
        acrossTime[times - 1] = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
    }

    public RobotInfo[] getEnemies() throws GameActionException {
        RobotInfo[] using = acrossTime[times - 1];
        for (int i = times - 2; i >= 0; i--)
            if (using.length == 0 && acrossTime[i] != null)
                using = acrossTime[i];
        return using;
    }

    boolean shouldTargetHQ() {
        return rc.getID() % 8 == 0;
    }

    public MapLocation getWeakLocCarrier() throws GameActionException {
        RobotInfo[] enemies = getEnemies();
        MapLocation weakLoc = null;
        int weakness = 0;
        for (RobotInfo enemy : enemies) {
            if (!enemy.type.equals(RobotType.LAUNCHER))
                continue;
            MapLocation toAttack = enemy.location;
            int adjustedHealth = getEnemyWeaknessMetric(enemy);
            if (weakLoc == null || adjustedHealth < weakness) {
                weakness = adjustedHealth;
                weakLoc = toAttack;
            }
        }
        return weakLoc;
    }

    public MapLocation getWeakLoc() throws GameActionException {
        RobotInfo[] enemies = getEnemies();
        MapLocation weakLoc = null;
        int weakness = 0;
        for (RobotInfo enemy : enemies) {
            if (enemy.type.equals(RobotType.HEADQUARTERS) && !shouldTargetHQ())
                continue;
            MapLocation toAttack = enemy.location;
            int adjustedHealth = getEnemyWeaknessMetric(enemy);
            if (weakLoc == null || adjustedHealth < weakness) {
                weakness = adjustedHealth;
                weakLoc = toAttack;
            }
        }
        return weakLoc;
    }

    public void tryAttack() throws GameActionException {
        if (!rc.isActionReady())
            return;
        RobotInfo[] enemies = rc.senseNearbyRobots(rc.getType().actionRadiusSquared, rc.getTeam().opponent());
        MapLocation weakLoc = null;
        int weakness = 0;
        for (RobotInfo enemy : enemies) {
            MapLocation toAttack = enemy.location;
            if (!rc.canAttack(toAttack) || enemy.type == RobotType.HEADQUARTERS)
                continue;
            int adjustedHealth = getEnemyWeaknessMetric(enemy);
            if (weakLoc == null || adjustedHealth < weakness) {
                weakness = adjustedHealth;
                weakLoc = toAttack;
            }
        }
        if (weakLoc != null) {
            rc.attack(weakLoc);
        }
    }

    public void tryDestabilize() throws GameActionException {
        if (!rc.isActionReady())
            return;
        RobotInfo[] enemies = rc.senseNearbyRobots(rc.getType().actionRadiusSquared, rc.getTeam().opponent());
        MapLocation weakLoc = null;
        int weakness = 0;
        for (RobotInfo enemy : enemies) {
            MapLocation toAttack = enemy.location;
            if (!rc.canDestabilize(toAttack))
                continue;
            int adjustedHealth = getEnemyWeaknessMetric(enemy);
            if (weakLoc == null || adjustedHealth < weakness) {
                weakness = adjustedHealth;
                weakLoc = toAttack;
            }
        }
        if (weakLoc == null)
            return;
        MapLocation HQLoc = robot.tracker.getClosestHQLoc();
        MapLocation best = null;
        MapLocation[] As = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(), -1);
        for (MapLocation loc : As) {
            if (!rc.canDestabilize(loc))
                continue;
            if (weakLoc.distanceSquaredTo(loc) > 15)
                continue;
            if (best == null || loc.distanceSquaredTo(HQLoc) > best.distanceSquaredTo(HQLoc))
                best = loc;
        }
        if (best != null)
            rc.destabilize(best);
    }

    void tryAerialBombing() throws GameActionException {
        if (!rc.isActionReady())
            return;
        MapLocation HQLoc = robot.tracker.getClosestHQLoc();
        MapLocation best = null;
        MapLocation[] As = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(), -1);
        for (MapLocation loc : As) {
            if (!rc.canDestabilize(loc))
                continue;
            if (best == null || loc.distanceSquaredTo(HQLoc) > best.distanceSquaredTo(HQLoc))
                best = loc;
        }
        if (best != null)
            rc.destabilize(best);
    }

    MapLocation[] might = new MapLocation[101];

    public void snipe() throws GameActionException {
        if (!rc.isActionReady())
            return;
        int mc = 0;
        if (rc.senseCloud(rc.getLocation())) {
            for (int x = -4; x <= 4; x++) {
                for (int y = -4; y <= 4; y++) {
                    MapLocation loc = new MapLocation(x, y);
                    if (rc.onTheMap(loc) && rc.getLocation().distanceSquaredTo(loc) > 4
                            && rc.getLocation().distanceSquaredTo(loc) <= 16) {
                        might[mc] = loc;
                        mc++;
                    }
                }
            }
        } else {
            MapLocation clouds[] = rc.senseNearbyCloudLocations(16);
            for (MapLocation cloud : clouds) {
                if (rc.getLocation().distanceSquaredTo(cloud) <= 4)
                    continue;
                might[mc] = cloud;
                mc++;
            }
        }
        if (mc > 0)
            rc.attack(might[robot.rng.nextInt(mc)]);
    }

    public MapLocation getThreat() throws GameActionException {
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        MapLocation weakLoc = null;
        int weakness = 0;
        for (RobotInfo enemy : enemies) {
            MapLocation toAttack = enemy.location;
            int adjustedHealth = getEnemyStrengthMetric(enemy);
            if (weakLoc == null || adjustedHealth > weakness) {
                weakness = adjustedHealth;
                weakLoc = toAttack;
            }
        }
        return weakLoc;
    }

    int lastHealth = 200;

    public int getStatus(MapLocation weakLoc) throws GameActionException {

        RobotInfo[] enemies = getEnemies();
        RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());

        // int friendOffensiveCnt = 3 + rc.getHealth();
        // int enemyOffensiveCnt = 0;
        int W = 0;
        int delta = rc.getHealth() - lastHealth;
        lastHealth = rc.getHealth();
        boolean ahead = false;
        for (RobotInfo friend : friends) {
            // if (friend.type.equals(RobotType.LAUNCHER))
            // friendOffensiveCnt += 3 + friend.health;
            if (weakLoc != null) {
                if (friend.location.distanceSquaredTo(weakLoc) < rc.getLocation().distanceSquaredTo(weakLoc))
                    ahead = true;
            }
        }
        for (RobotInfo enemy : enemies) {
            if (enemy.type.equals(RobotType.HEADQUARTERS)) {
                // if (shouldTargetHQ())
                // enemyOffensiveCnt += 3;
                if (shouldTargetHQ())
                    W++;
                continue;
            }
            // if (enemy.type.equals(RobotType.LAUNCHER))
            // enemyOffensiveCnt += 15 + enemy.health;
            W += 3;
        }
        if (W != 0) {
            if (delta >= 0 && (ahead || W == 1)) {
                return 1;
            } else if (delta < 0) {
                return 2;
            } else {
                return 3;
            }
        }
        return 0;
    }
}