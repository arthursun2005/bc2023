package optiver;

import battlecode.common.*;
import java.util.*;

public class Attack {
    RobotController rc;
    Robot robot;

    public Attack(RobotController rc, Robot robot) throws GameActionException {
        this.rc = rc;
        this.robot = robot;
    }

    final int times = 3;
    final int coef = 100;

    RobotInfo[][] acrossTime = new RobotInfo[times][];

    RobotInfo[] friends;

    public int getEnemyWeaknessMetric(RobotInfo enemy) throws GameActionException {
        if (enemy.type.equals(RobotType.HEADQUARTERS))
            return 1_000_000_000;
        int adjustedHealth = enemy.health * 10000 * coef
                - rc.getLocation().distanceSquaredTo(enemy.location) * 10000
                + rc.getID();
        if (enemy.type.equals(RobotType.LAUNCHER) || enemy.type.equals(RobotType.DESTABILIZER))
            adjustedHealth -= 500_000_000;
        if (rc.canSenseLocation(enemy.location))
            adjustedHealth -= 500_000_000;
        return adjustedHealth;
    }

    public int getEnemyStrengthMetric(RobotInfo enemy) throws GameActionException {
        if (!enemy.type.equals(RobotType.LAUNCHER) && !enemy.type.equals(RobotType.DESTABILIZER))
            return -1_000_000_000;
        int adjustedHealth = -rc.getLocation().distanceSquaredTo(enemy.location) * 1000 + enemy.health;
        return adjustedHealth;
    }

    RobotInfo[] persistentEnemies;

    void updateFriends() throws GameActionException {
        friends = rc.senseNearbyRobots(-1, rc.getTeam());
    }

    public void update() throws GameActionException {
        for (int i = 0; i < times - 1; i++)
            acrossTime[i] = acrossTime[i + 1];
        acrossTime[times - 1] = rc.senseNearbyRobots(-1, rc.getTeam().opponent());

        ArrayList<RobotInfo> using = new ArrayList<>(Arrays.asList(acrossTime[times - 1]));
        StringBuilder hashset = new StringBuilder(String.format("%30000s", ""));
        for (int i = times - 2; i >= 0; i--) {
            if (acrossTime[i] == null)
                continue;
            for (RobotInfo ri : acrossTime[i]) {
                if (!rc.canSenseLocation(ri.location) && hashset.charAt(ri.ID) == ' ') {
                    hashset.setCharAt(ri.ID, '1');
                    using.add(ri);
                }
            }
        }
        persistentEnemies = using.toArray(new RobotInfo[using.size()]);
    }

    public RobotInfo[] getEnemies() throws GameActionException {
        return persistentEnemies;
    }

    boolean shouldTargetHQ() {
        return rc.getRoundNum() < 120 || rc.getID() % 5 == 0;
    }

    public MapLocation getWeakLocCarrier() throws GameActionException {
        RobotInfo[] enemies = getEnemies();
        MapLocation weakLoc = null;
        int weakness = 0;
        for (RobotInfo enemy : enemies) {
            if (!enemy.type.equals(RobotType.LAUNCHER) && !enemy.type.equals(RobotType.DESTABILIZER))
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
            if (rc.getType().equals(RobotType.CARRIER) && enemy.type.equals(RobotType.CARRIER))
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
        MapLocation me = rc.getLocation();
        if (rc.senseCloud(rc.getLocation())) {
            MapLocation loc;

            loc = new MapLocation(me.x + -4, me.y + -4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -4, me.y + -3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -4, me.y + -2);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -4, me.y + -1);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -4, me.y + 0);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -4, me.y + 1);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -4, me.y + 2);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -4, me.y + 3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -4, me.y + 4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -3, me.y + -4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -3, me.y + -3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -3, me.y + -2);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -3, me.y + -1);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -3, me.y + 0);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -3, me.y + 1);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -3, me.y + 2);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -3, me.y + 3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -3, me.y + 4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -2, me.y + -4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -2, me.y + -3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -2, me.y + -2);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -2, me.y + -1);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -2, me.y + 1);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -2, me.y + 2);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -2, me.y + 3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -2, me.y + 4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -1, me.y + -4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -1, me.y + -3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -1, me.y + -2);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -1, me.y + 2);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -1, me.y + 3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + -1, me.y + 4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 0, me.y + -4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 0, me.y + -3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 0, me.y + 3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 0, me.y + 4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 1, me.y + -4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 1, me.y + -3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 1, me.y + -2);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 1, me.y + 2);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 1, me.y + 3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 1, me.y + 4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 2, me.y + -4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 2, me.y + -3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 2, me.y + -2);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 2, me.y + -1);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 2, me.y + 1);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 2, me.y + 2);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 2, me.y + 3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 2, me.y + 4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 3, me.y + -4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 3, me.y + -3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 3, me.y + -2);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 3, me.y + -1);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 3, me.y + 0);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 3, me.y + 1);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 3, me.y + 2);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 3, me.y + 3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 3, me.y + 4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 4, me.y + -4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 4, me.y + -3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 4, me.y + -2);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 4, me.y + -1);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 4, me.y + 0);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 4, me.y + 1);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 4, me.y + 2);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 4, me.y + 3);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }

            loc = new MapLocation(me.x + 4, me.y + 4);
            if (rc.canAttack(loc)) {
                might[mc] = loc;
                mc++;
            }
        } else {
            MapLocation clouds[] = rc.senseNearbyCloudLocations(16);
            for (MapLocation cloud : clouds) {
                if (me.distanceSquaredTo(cloud) <= 4)
                    continue;
                might[mc] = cloud;
                mc++;
            }
        }
        if (mc > 0)
            rc.attack(might[robot.rng.nextInt(mc)]);
    }

    public MapLocation getThreat() throws GameActionException {
        // RobotInfo[] enemies = rc.getType().equals(RobotType.CARRIER) ?
        // rc.senseNearbyRobots(-1, rc.getTeam().opponent())
        // : getEnemies();
        RobotInfo[] enemies = getEnemies();
        // RobotInfo[] enemies = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        MapLocation weakLoc = null;
        int weakness = 0;
        for (RobotInfo enemy : enemies) {
            if (!enemy.type.equals(RobotType.LAUNCHER) && !enemy.type.equals(RobotType.DESTABILIZER))
                continue;
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
        int frens = 0;
        for (RobotInfo friend : friends) {
            // if (friend.type.equals(RobotType.LAUNCHER))
            // friendOffensiveCnt += 3 + friend.health;
            if (friend.type.equals(RobotType.LAUNCHER) || friend.type.equals(RobotType.DESTABILIZER)) {
                frens += friend.health;
            }

            if (weakLoc != null) {
                // if (friend.type.equals(RobotType.LAUNCHER)

                if (friend.location.distanceSquaredTo(weakLoc) < rc.getLocation().distanceSquaredTo(weakLoc)) {
                    // if (Util.rDist(weakLoc, friend.location) < Util.rDist(weakLoc,
                    // rc.adjacentLocation(rc.getLocation().directionTo(weakLoc))))
                    ahead = true;
                }
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
            if (enemy.type.equals(RobotType.LAUNCHER) || enemy.type.equals(RobotType.DESTABILIZER))
                // enemyOffensiveCnt += 15 + enemy.health;
                W += 3;
        }
        int enemyHealth = 0;
        RobotInfo a;
        if (weakLoc != null && rc.canSenseLocation(weakLoc)) {
            a = rc.senseRobotAtLocation(weakLoc);
            if (a != null)
                enemyHealth = a.health;
        }
        if (W != 0) {
            if (delta >= -4 && (ahead || frens == 0 || W <= 1 || rc.getHealth() > enemyHealth)) {
                return 1;
            } else if (delta < -4) {
                return 2;
            } else {
                return 3;
            }
        }
        return 0;
    }
}
