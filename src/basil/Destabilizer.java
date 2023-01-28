package basil;

import battlecode.common.*;

public class Destabilizer extends Robot {
    Symmetry symmetry;

    public Destabilizer(RobotController rc) throws GameActionException {
        super(rc);
        symmetry = new Symmetry(this);
    }

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
        MapLocation HQLoc = tracker.getClosestHQLoc();
        MapLocation weakLoc = attack.getWeakLoc();
        int status = attack.getStatus(weakLoc);
        rc.setIndicatorString(status + " " + weakLoc);
        if (weakLoc != null) {
            rc.setIndicatorDot(weakLoc, 255, 255, 100);
        }
        if (status == 1) {
            if (weakLoc != null)
                randomizedGreedy(weakLoc, -1, rc.getType().actionRadiusSquared);
            attack.tryDestabilize();
        } else if (status == 2) {
            attack.tryDestabilize();
            randomizedGreedy(weakLoc, -1, 1_000_000);
            attack.tryDestabilize();
        } else if (status == 0) {
            attack.tryDestabilize();
            symmetry.update();
            moveTo(symmetry.target.loc);
            rc.setIndicatorLine(rc.getLocation(), symmetry.target.loc, 190, 150, 150);
            attack.tryDestabilize();
        } else {
            attack.tryDestabilize();
            randomizedGreedy(weakLoc, -1, rc.getType().visionRadiusSquared);
            attack.tryDestabilize();
        }
        attack.tryAerialBombing();
        tracker.tryFindSymmetry();
    }
}
