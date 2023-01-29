package broke;

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
                + enemy.ID;
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

    boolean shouldTargetHQ(MapLocation loc) throws GameActionException {
        return rc.getRoundNum() > 180 && rc.getID() % 8 == 0;
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
            if (enemy.type.equals(RobotType.HEADQUARTERS) && !shouldTargetHQ(enemy.location))
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

    MapLocation might = null;

    public void snipe() throws GameActionException {
        if (!rc.isActionReady()) return;
        if (rc.getRoundNum() == robot.creationRound) return;
        int mc = 0;
        MapLocation me = rc.getLocation();
        CursedRandom rng = robot.rng;
        int mx = 0, my = 0;
        int width = rc.getMapWidth();
        int height = rc.getMapHeight();

        if (rc.senseCloud(rc.getLocation())) {
            int fx, fy;

            fx = me.x + -4;
            fy = me.y + 0;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + -3;
            fy = me.y + -2;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + -3;
            fy = me.y + -1;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + -3;
            fy = me.y + 0;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + -3;
            fy = me.y + 1;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + -3;
            fy = me.y + 2;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + -2;
            fy = me.y + -3;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + -2;
            fy = me.y + -2;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + -2;
            fy = me.y + -1;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + -2;
            fy = me.y + 1;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + -2;
            fy = me.y + 2;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + -2;
            fy = me.y + 3;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + -1;
            fy = me.y + -3;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + -1;
            fy = me.y + -2;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + -1;
            fy = me.y + 2;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + -1;
            fy = me.y + 3;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 0;
            fy = me.y + -4;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 0;
            fy = me.y + -3;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 0;
            fy = me.y + 3;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 0;
            fy = me.y + 4;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 1;
            fy = me.y + -3;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 1;
            fy = me.y + -2;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 1;
            fy = me.y + 2;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 1;
            fy = me.y + 3;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 2;
            fy = me.y + -3;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 2;
            fy = me.y + -2;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 2;
            fy = me.y + -1;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 2;
            fy = me.y + 1;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 2;
            fy = me.y + 2;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 2;
            fy = me.y + 3;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 3;
            fy = me.y + -2;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 3;
            fy = me.y + -1;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 3;
            fy = me.y + 0;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 3;
            fy = me.y + 1;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 3;
            fy = me.y + 2;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }

            fx = me.x + 4;
            fy = me.y + 0;
            if (fx >= 0 && fx < width && fy >= 0 && fy < height) {
                if (rng.nextInt(++mc) == 0) { mx = fx; my = fy; }
            }
            might = new MapLocation(mx, my);
        } else {
            MapLocation clouds[] = rc.senseNearbyCloudLocations(16);
            for (MapLocation cloud : clouds) {
                if (me.distanceSquaredTo(cloud) <= 4)
                continue;
                if (rng.nextInt(++mc) == 0) might = cloud;
            }
        }
        if (mc > 0) rc.attack(might);
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
            if (!enemy.type.equals(RobotType.LAUNCHER) || !enemy.type.equals(RobotType.DESTABILIZER))
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

        int L = 20 + rc.getHealth();
        int W = 0;
        int lc = 0;
        int delta = rc.getHealth() - lastHealth;
        lastHealth = rc.getHealth();
        boolean ahead = false;
        for (RobotInfo friend : friends) {
            if (friend.type.equals(RobotType.LAUNCHER) || friend.type.equals(RobotType.DESTABILIZER)) {
                L += 20 + friend.health;
                lc++;
            }

            if (weakLoc != null) {
                if (friend.location.distanceSquaredTo(weakLoc) < rc.getLocation().distanceSquaredTo(weakLoc)) {
                    ahead = true;
                }
            }
        }
        for (RobotInfo enemy : enemies) {
            if (enemy.type.equals(RobotType.HEADQUARTERS)) {
                if (shouldTargetHQ(enemy.location))
                    W += 4;
                continue;
            }
            if (enemy.type.equals(RobotType.LAUNCHER) || enemy.type.equals(RobotType.DESTABILIZER)) {
                W += 20 + enemy.health;
            }
        }
        int enemyHealth = 0;
        RobotInfo a;
        if (weakLoc != null && rc.canSenseLocation(weakLoc)) {
            a = rc.senseRobotAtLocation(weakLoc);
            if (a != null)
                enemyHealth = a.health;
        }
        if (W != 0) {
            if (delta >= -12 && (ahead || W <= 4 || (lc <= 1 && L - W > 20)) && L - W > -79) {
                return 1;
            } else if (delta < -12) {
                return 2;
            } else {
                return 3;
            }
        }
        return 0;
    }
}
