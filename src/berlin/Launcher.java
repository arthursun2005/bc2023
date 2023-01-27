package berlin;

import battlecode.common.*;

public class Launcher extends Robot {
    boolean reached = false;
    MapLocation target, parentLoc;

    public Launcher(RobotController rc) throws GameActionException {
        super(rc);

        int M = 1;
        int width = rc.getMapWidth();
        int height = rc.getMapHeight();

        parentLoc = tracker.getClosestHQLoc();
        MapLocation oppositeLoc = new MapLocation(width - parentLoc.x - 1 + rng.nextInt(M) - M / 2,
                height - parentLoc.y - 1 + rng.nextInt(M) - M / 2);
        target = oppositeLoc;
    }

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
            if (loc.distanceSquaredTo(target) > tol)
                continue;
            int w = eval(loc, target, mul);
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
            if (weakLoc != null)
                randomizedGreedy(weakLoc, -1, rc.getType().actionRadiusSquared);
                // greedilyMove(weakLoc, 1);
            // weakLoc = attack.getWeakLoc();
            // if (weakLoc != null && rc.canAttack(weakLoc))
            //     rc.attack(weakLoc);
            attack.tryAttack();
        } else if (status == 2) {
            attack.tryAttack();
            // greedilyMove(attack.getThreat(), -1);
            randomizedGreedy(weakLoc, -1, 1_000_000);
            attack.tryAttack();
        } else if (status == 0) {
            if (rc.getLocation().distanceSquaredTo(target) <= 8)
                reached = true;

            int lowest = Util.rDist(rc.getLocation(), target);
            RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());

            for (RobotInfo friend : friends) {
                if (friend.type == RobotType.LAUNCHER) {
                    int w = Util.rDist(friend.location, target);
                    if (w < lowest)
                        lowest = w;
                }
            }

            if (!reached) {
                if (Util.rDist(rc.getLocation(), target) > lowest || friends.length >= 15)
                    moveTo(target);
            } else {
                int M = 1;
                int width = rc.getMapWidth();
                int height = rc.getMapHeight();
                MapLocation oppositeLoc;
                if (rng.nextBoolean()) {
                    oppositeLoc = new MapLocation(parentLoc.x + rng.nextInt(M) - M / 2,
                            height - parentLoc.y - 1 + rng.nextInt(M) - M / 2);
                } else {
                    oppositeLoc = new MapLocation(width - parentLoc.x - 1 + rng.nextInt(M) - M / 2,
                            parentLoc.y + rng.nextInt(M) - M / 2);
                }
                target = oppositeLoc;
                reached = false;
            }
            attack.tryAttack();
        } else {
            attack.tryAttack();
            randomizedGreedy(weakLoc, -1, rc.getType().visionRadiusSquared);
            attack.tryAttack();
        }
    }
}
