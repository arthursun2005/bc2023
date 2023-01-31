package loners;

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

        // if (status == 0 && rc.canSenseLocation(target.loc))
        //     status = 1;

        if (status == 1) {
            if (weakLoc != null) {
                RobotInfo bot = null;
                if (rc.canSenseLocation(weakLoc))
                    bot = rc.senseRobotAtLocation(weakLoc);
                if (bot != null && bot.type == RobotType.CARRIER)
                    randomizedGreedy(weakLoc, 1, rc.getType().actionRadiusSquared);
                randomizedGreedy(weakLoc, -1, rc.getType().actionRadiusSquared);
            }
            attack.tryAttack();
        } else if (status == 2) {
            attack.tryAttack();
            randomizedGreedy(weakLoc, -1, 1_000_000);
            attack.tryAttack();
        } else if (status == 0) {
            int mini = Util.rDist(rc.getLocation(), symmetry.target.loc);
            // int lowerCount = 0;
            MapLocation bestie = null;
            RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());
            int count = 0;
            int ahead = 0;

            for (RobotInfo friend : friends) {
                if (friend.type == RobotType.LAUNCHER) {
                    // if (friend.ID < rc.getID())
                    // lowerCount++;
                    count++;
                    int w = Util.rDist(friend.location, symmetry.target.loc);
                    if (w < mini) {
                        ahead++;
                        // mini = w;
                        bestie = friend.location;
                    }
                }
            }

            attack.tryAttack();

            MapLocation site = tracker.pls();
            if (site != null
                    && rc.getLocation().distanceSquaredTo(site) <= 64) {
                moveTo(site);
                rc.setIndicatorLine(site, rc.getLocation(), 255, 0, 0);
            }

            int req = 0;// (int) (rc.getRoundNum() / 100) + 1;

            // if (mini < rc.getID() && lowerCount < 9) {
            // moveTo(bestie);
            // }
            if (bestie != null) {
                // Someone is closer
                moveTo(symmetry.update());
            } else {
                if (count == 0) {
                    // moveTo(HQLoc);
                    if (rc.getRoundNum() % 10 == 0)
                        moveTo(symmetry.update());
                } else if (rc.getRoundNum() % 3 == 0) {
                    // ur closest
                    moveTo(symmetry.update());
                }
            }

            attack.tryAttack();
        } else {
            attack.tryAttack();
            randomizedGreedy(weakLoc, -1, rc.getType().visionRadiusSquared);
            attack.tryAttack();
        }
        if (weakLoc != null && rc.canAttack(weakLoc))
            rc.attack(weakLoc);
        attack.snipe();

        tracker.tryFindSymmetry();
    }
}
