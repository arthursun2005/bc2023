package suskamikaze;

import battlecode.common.*;

public class Launcher extends Robot {
    boolean reached = false;
    MapLocation target, parentLoc;

    public Launcher(RobotController rc) throws GameActionException {
        super(rc);

        int M = 10;
        int width = rc.getMapWidth();
        int height = rc.getMapHeight();

        parentLoc = tracker.getClosestHQLoc();
        MapLocation oppositeLoc = new MapLocation(width - parentLoc.x - 1 + rng.nextInt(M) - M / 2,
                height - parentLoc.y - 1 + rng.nextInt(M) - M / 2);
                target = oppositeLoc;
    }

    public boolean shouldMove(MapLocation loc) {
        int dx = target.x - loc.x;
        int dy = target.y - loc.y;
        int dist = (Math.abs(dx) + Math.abs(dy))/2;
        return (dist%3 != 2-(rc.getRoundNum()/3)%3);
    }

    public void run() throws GameActionException {
        MapLocation weakLoc = attack.getWeakLoc();
        int status = attack.getStatus(weakLoc);
        rc.setIndicatorString(status + " " + weakLoc + " " + target);
        if (weakLoc != null) {
            rc.setIndicatorDot(weakLoc, 255, 255, 100);
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

            if (mini < rc.getID() && lowerCount < 9) {
                if (isStuck(bestie)) {
                    spreadOut(true);
                }
                Direction dir = movement.tryMove(target, null);
                if (dir == null) dir = Direction.CENTER;
                MapLocation possLoc = rc.getLocation().add(dir);
                if (possLoc.distanceSquaredTo(bestie) <= 2) {
                    if (rc.canMove(dir)) rc.move(dir);
                }
                if (true) moveTo(bestie);
                //if (shouldMove(rc.getLocation()) && shouldMove(bestie)) moveTo(target);
            }
            else {
                if (!reached) {
                    if (shouldMove(rc.getLocation())) moveTo(target);
                } else {
                    int M = 10;
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
            }
        }
    }
}
