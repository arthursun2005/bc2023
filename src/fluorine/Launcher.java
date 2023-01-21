package fluorine;

import battlecode.common.*;

public class Launcher extends Robot {
    boolean reached = false;
    MapLocation target, parentLoc;

    MapLocation adjust(MapLocation a) {
        int M = 7;
        if (rng.nextInt(3) != 0)
            return a;
        return new MapLocation(a.x + rng.nextInt(M) - M / 2, a.y + rng.nextInt(M) - M / 2);
    }

    public Launcher(RobotController rc) throws GameActionException {
        super(rc);

        int width = rc.getMapWidth();
        int height = rc.getMapHeight();

        parentLoc = tracker.getClosestHQLoc();
        MapLocation oppositeLoc = new MapLocation(width - parentLoc.x - 1,
                height - parentLoc.y - 1);
        target = adjust(oppositeLoc);
    }

    public void randomizedGreedy(MapLocation target, int mul, int tol) throws GameActionException {
        int hits = rc.getLocation().distanceSquaredTo(target) <= tol
                ? rc.getLocation().distanceSquaredTo(target) * mul
                : 1_000_000_000;
        Direction[] allGood = new Direction[9];
        int gc = 0;

        for (Direction dir : directions) {
            if (!rc.canMove(dir))
                continue;
            MapLocation loc = rc.adjacentLocation(dir);
            if (loc.distanceSquaredTo(target) > tol)
                continue;
            int w = loc.distanceSquaredTo(target) * mul;
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
        MapLocation HQLoc = tracker.getClosestHQLoc();
        MapLocation weakLoc = attack.getWeakLoc();
        int status = attack.getStatus(weakLoc);
        rc.setIndicatorString(status + " " + weakLoc + " " + target);
        if (weakLoc != null) {
            rc.setIndicatorDot(weakLoc, 255, 255, 100);
        }
        if (target != null) {
            rc.setIndicatorLine(target, rc.getLocation(), 225, 235, 255);
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
            if (rc.getLocation().distanceSquaredTo(target) <= 8)
                reached = true;

            int mini = rc.getLocation().distanceSquaredTo(target);
            // int lowerCount = 0;
            MapLocation bestie = null;
            RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());

            for (RobotInfo friend : friends) {
                if (friend.type == RobotType.LAUNCHER) {
                    // if (friend.ID < rc.getID())
                    // lowerCount++;
                    if (friend.location.distanceSquaredTo(target) < mini) {
                        mini = friend.location.distanceSquaredTo(target);
                        bestie = friend.location;
                    }
                }
            }

            attack.tryAttack();

            // if (mini < rc.getID() && lowerCount < 9) {
            // moveTo(bestie);
            // }
            if (bestie != null) {
                // Direction dir = rc.getLocation().directionTo(bestie);
                // tryMove(dir.rotateLeft().rotateLeft());
                // tryMove(dir.rotateRight().rotateRight());
                // tryMove(dir.rotateLeft());
                // tryMove(dir.rotateRight());
                moveTo(bestie);
            }
            if (!reached) {
                moveTo(target);
            } else {
                int width = rc.getMapWidth();
                int height = rc.getMapHeight();
                MapLocation oppositeLoc;
                if (rng.nextBoolean()) {
                    oppositeLoc = new MapLocation(parentLoc.x,
                            height - parentLoc.y - 1);
                } else {
                    oppositeLoc = new MapLocation(width - parentLoc.x - 1,
                            parentLoc.y);
                }
                target = adjust(oppositeLoc);
                reached = false;
            }
            attack.tryAttack();
        } else {
            attack.tryAttack();
            randomizedGreedy(weakLoc, -1, rc.getType().visionRadiusSquared);
            attack.tryAttack();
        }
        attack.snipe();
    }
}
