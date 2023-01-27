package asianbanana;

import battlecode.common.*;

public class Destabilizer extends Robot {
    boolean reached = false;
    MapLocation target, parentLoc;

    public Destabilizer(RobotController rc) throws GameActionException {
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
            if (weakLoc != null)
                greedilyMove(weakLoc, 1);
            attack.tryDestabilize();
        } else if (status == 2) {
            attack.tryDestabilize();
            greedilyMove(attack.getThreat(), -1);
            attack.tryDestabilize();
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

            attack.tryDestabilize();

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
            attack.tryDestabilize();
        } else {
            attack.tryDestabilize();
            int big = rc.getType().visionRadiusSquared;
            int hits = -1_000_000_000;
            Direction[] allGood = new Direction[9];
            int gc = 0;
            for (Direction dir : directions) {
                if (!rc.canMove(dir))
                    continue;
                MapLocation loc = rc.adjacentLocation(dir);
                if (loc.distanceSquaredTo(weakLoc) > big)
                    continue;
                int w = -loc.distanceSquaredTo(HQLoc);
                if (w > hits) {
                    hits = w;
                    allGood[gc] = dir;
                    gc = 1;
                } else if (w == hits) {
                    allGood[gc] = dir;
                    gc++;
                }
            }
            if (gc != 0)
                rc.move(allGood[rng.nextInt(gc)]);
            attack.tryDestabilize();
        }
        attack.tryAerialBombing();
    }
}
