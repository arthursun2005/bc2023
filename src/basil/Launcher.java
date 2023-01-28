package basil;

import battlecode.common.*;

import java.util.*;

public class Launcher extends Robot {
    Symmetry symmetry;

    MapLocation adjust(MapLocation a) {
        int M = 1;
        if (rng.nextInt(3) != 0)
            return a;
        return new MapLocation(a.x + rng.nextInt(M) - M / 2, a.y + rng.nextInt(M) - M / 2);
    }

    public Launcher(RobotController rc) throws GameActionException {
        super(rc);

        int width = rc.getMapWidth();
        int height = rc.getMapHeight();

        symmetry = new Symmetry(this);
    }

    MapLocation HQLoc;

    int eval(MapLocation loc, MapLocation target, int mul) throws GameActionException {
        int base = target.distanceSquaredTo(loc) * mul;
        if (rc.senseCloud(loc))
            base += 1_000;
        return base;
    }

    public void randomizedGreedy(MapLocation target, int mul, int tol) throws GameActionException {
        int hits = rc.getLocation().distanceSquaredTo(target) <= tol
                ? eval(rc.getLocation(), target, mul)
                : 2_000_000_000;
        Direction[] allGood = new Direction[9];
        int gc = 0;

        for (Direction dir : directions) {
            if (!rc.canMove(dir))
                continue;
            MapLocation loc = rc.adjacentLocation(dir);
            int w = eval(loc, target, mul);
            int sum = Math.abs(dir.dx) + Math.abs(dir.dy);
            if (sum == 2) w += 10_000;
            if (loc.distanceSquaredTo(target) > tol)
                w += 1_000_000 * loc.distanceSquaredTo(target);
            if (w < hits) {
                hits = w;
                allGood[0] = dir;
                gc = 1;
            } else if (w == hits) {
                allGood[gc] = dir;
                gc++;
            }
        }

        if (gc != 0)
            rc.move(allGood[rng.nextInt(gc)]);
    }

    public void run() throws GameActionException {
        HQLoc = tracker.getClosestHQLoc();
        MapLocation weakLoc = attack.getWeakLoc();
        int status = attack.getStatus(weakLoc);
        rc.setIndicatorString(status + " " + weakLoc);
        if (weakLoc != null) {
            rc.setIndicatorDot(weakLoc, 255, 255, 100);
        }
        if (symmetry.target != null) {
            rc.setIndicatorLine(symmetry.target.loc, rc.getLocation(), 225, 235, 255);
        }

        MapLocation bestie = null;
        int bid = rc.getID();
        RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());
        int cnt = 0;
        int bigcnt = 0;
        for (RobotInfo ri : friends) {
            if (!ri.type.equals(RobotType.LAUNCHER)) continue;
            if (ri.location.distanceSquaredTo(rc.getLocation()) <= 8)
                cnt++;
            bigcnt++;
            // if (bestie == null || rc.getLocation().distanceSquaredTo(ri.location) < rc.getLocation().distanceSquaredTo(bestie)) {
            if (ri.ID < bid) {
                bestie = ri.location;
                bid = ri.ID;
            }
        }

        if (status == 1) {
            if (weakLoc != null) {
                randomizedGreedy(weakLoc, -1, rc.getType().actionRadiusSquared);
            }
            attack.tryAttack();
        } else if (status == 2) {
            attack.tryAttack();
            randomizedGreedy(weakLoc, -1, 1_000_000);
            attack.tryAttack();
        } else if (status == 0) {
            symmetry.update();
            attack.tryAttack();
            int Rigged = Math.min(Math.abs(rc.getMapWidth() / 2 - HQLoc.x), Math.abs(rc.getMapHeight() / 2 - HQLoc.y));
            int cutoff = 3 * Rigged / 2;
            boolean mapsmall = Rigged < 8;
            if (bestie != null && cnt < 4 && (rc.getRoundNum() % 3 != 0 && turnCount > cutoff)) {
                // moveTo(bestie);
                randomizedGreedy(bestie, 1, 1000000);
                rc.setIndicatorLine(rc.getLocation(), bestie, 190, 50, 50);
            } else if (bigcnt == 0 || (turnCount < cutoff && mapsmall)) {
                MapLocation center = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
                moveTo(center);
                rc.setIndicatorLine(rc.getLocation(), center, 190, 50, 150);
            } else if (rc.getRoundNum() % 2 == 0) {
                moveTo(symmetry.target.loc);
                rc.setIndicatorLine(rc.getLocation(), symmetry.target.loc, 190, 150, 150);
            }
            attack.tryAttack();
        } else {
            attack.tryAttack();
            if (bestie != null && false)
                randomizedGreedy(bestie, 1, 1_000_000);
            else
                randomizedGreedy(weakLoc, -1, rc.getType().visionRadiusSquared);
            attack.tryAttack();
        }
        if (weakLoc != null && rc.canAttack(weakLoc))
            rc.attack(weakLoc);
        attack.snipe();

        tracker.tryFindSymmetry();
    }
}
