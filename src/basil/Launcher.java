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
        int base = target.distanceSquaredTo(loc) * mul;// * 1_000_000 - loc.distanceSquaredTo(HQLoc);
        if (rc.senseCloud(loc))
            base += 1_000_000_000;
        return base;
    }

    public void randomizedGreedy(MapLocation target, int mul, int tol) throws GameActionException {
        int hits = rc.getLocation().distanceSquaredTo(target) <= tol
                ? eval(rc.getLocation(), target, mul)
                : 1_000_000_000;
        Direction[] allGood = new Direction[9];
        int gc = 0;

        for (Direction dir : directions) {
            if (!rc.canMove(dir))
                continue;
            MapLocation loc = rc.adjacentLocation(dir);
            //loc = loc.add(rc.senseMapInfo(loc).getCurrentDirection());
            if (loc.distanceSquaredTo(target) > tol)
                continue;
            int w = eval(loc, target, mul);
            int sum = Math.abs(dir.dx) + Math.abs(dir.dy);
            if (sum == 2) w += 10000;
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
                randomizedGreedy(weakLoc, -1, rc.getType().actionRadiusSquared);
            }
            attack.tryAttack();
        } else if (status == 2) {
            attack.tryAttack();
            randomizedGreedy(weakLoc, -1, 1_000_000);
            attack.tryAttack();
        } else if (status == 0) {
            symmetry.update();
            int mini = rc.getLocation().distanceSquaredTo(symmetry.target.loc);
            // int lowerCount = 0;
            MapLocation bestie = null;
            RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());
            int count = 0;

            for (RobotInfo friend : friends) {
                if (friend.type == RobotType.LAUNCHER) {
                    // if (friend.ID < rc.getID())
                    // lowerCount++;
                    count++;
                    if (friend.location.distanceSquaredTo(symmetry.target.loc) < mini) {
                        mini = friend.location.distanceSquaredTo(symmetry.target.loc);
                        bestie = friend.location;
                    }
                }
            }

            attack.tryAttack();

            MapLocation site = tracker.pls();
            if (site != null
                    && rc.getLocation().distanceSquaredTo(site) < 100) {
                moveTo(site);
                rc.setIndicatorLine(site, rc.getLocation(), 255, 0, 0);
            }

            int req = 0;// (int) (rc.getRoundNum() / 100) + 1;

            // if (mini < rc.getID() && lowerCount < 9) {
            // moveTo(bestie);
            // }
            if (/*movement.currentState == Movement.State.NORMAL && */bestie != null && rc.getLocation().distanceSquaredTo(bestie) > 2) {
                // Direction dir = rc.getLocation().directionTo(bestie);
                // tryMove(dir.rotateLeft().rotateLeft());
                // tryMove(dir.rotateRight().rotateRight());
                // tryMove(dir.rotateLeft());
                // tryMove(dir.rotateRight());
                moveTo(bestie);
                rc.setIndicatorLine(bestie, rc.getLocation(), 122, 69, 69);
            } else if (/*true || */rc.getRoundNum()%2 == 0) {
                int dist = Util.rDist(symmetry.target.loc, HQLoc);
                if (dist <= 15/* rc.getRoundNum() < 100*/ || count > (dist > 28 ? 4 : 3)) {
                    moveTo(symmetry.target.loc);
                }
                else {
                    moveTo(HQLoc);
                }
                /*if (Util.rDist(rc.getLocation(), symmetry.target.loc) <= 8) {
                    moveTo(symmetry.target.loc);
                }
                else {
                    int tarX = (symmetry.parentLoc.x + symmetry.target.loc.x)/2;
                    int tarY = (symmetry.parentLoc.y + symmetry.target.loc.y)/2;
                    moveTo(new MapLocation(tarX, tarY));
                    rc.setIndicatorLine(new MapLocation(tarX, tarY), rc.getLocation(), 122, 69, 69);
                }*/
            }

            attack.tryAttack();
        } else {
            attack.tryAttack();
            randomizedGreedy(weakLoc, -1, rc.getType().visionRadiusSquared);
            //moveTo(HQLoc);
            attack.tryAttack();
        }
        if (weakLoc != null && rc.canAttack(weakLoc))
            rc.attack(weakLoc);
        attack.snipe();

        tracker.tryFindSymmetry();
    }
}
