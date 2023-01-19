package sylveontest;

import battlecode.common.*;

public class Launcher extends Robot {
    boolean reached = false;
    MapLocation target, parentLoc;

    public Launcher(RobotController rc) throws GameActionException {
        super(rc);

        int M = 6;
        int width = rc.getMapWidth();
        int height = rc.getMapHeight();

        parentLoc = tracker.getClosestHQLoc();
        MapLocation oppositeLoc = new MapLocation(width - parentLoc.x - 1 + rng.nextInt(M) - M / 2,
                height - parentLoc.y - 1 + rng.nextInt(M) - M / 2);
        target = oppositeLoc;
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
                greedilyMove(weakLoc, 1);
            weakLoc = attack.getWeakLoc();
            if (weakLoc != null && rc.canAttack(weakLoc))
                rc.attack(weakLoc);
        } else if (status == 2) {
            attack.tryAttack();
            greedilyMove(attack.getThreat(), -1);
            attack.tryAttack();
        } else if (status == 0) {
            if (rc.getLocation().distanceSquaredTo(target) <= 8)
                reached = true;

            int mini = rc.getID();
            int lowerCount = 0;
            MapLocation bestie = null;
            RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());

            for (RobotInfo friend : friends) {
                if (friend.type == RobotType.LAUNCHER) {
                    if (friend.ID < rc.getID())
                        lowerCount++;
                    if (friend.ID < mini) {
                        mini = friend.ID;
                        bestie = friend.getLocation();
                    }
                }
            }

            attack.tryAttack();

            if (mini < rc.getID() && lowerCount < 9) {
                moveTo(bestie);
            }
            if (!reached) {
                moveTo(target);
            } else {
                int M = 6;
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
            int big = rc.getType().visionRadiusSquared;
            Direction best = null;
            int hits = -1_000_000_000;
            for (Direction dir : directions) {
                if (!rc.canMove(dir))
                    continue;
                MapLocation loc = rc.adjacentLocation(dir);
                if (loc.distanceSquaredTo(weakLoc) > big)
                    continue;
                int w = loc.distanceSquaredTo(weakLoc);
                if (w > hits) {
                    hits = w;
                    best = dir;
                }
            }
            if (best != null)
                rc.move(best);
            attack.tryAttack();
        }
    }
}
