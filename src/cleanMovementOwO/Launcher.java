package omegaASS;

import battlecode.common.*;
public class Launcher extends Robot {
    static MapLocation parentLoc = null;
    static int partnerID = -1;

    static MapLocation prevLocation = null;
    static Direction prevDirection = null;

    public Launcher(RobotController rc) throws GameActionException {
        super(rc);

    }
    public void runUnit() throws GameActionException {
        if (prevLocation == null) {
            prevLocation = rc.getLocation();
        } else if (!rc.getLocation().equals(prevLocation)) {
            prevDirection = prevLocation.directionTo(rc.getLocation());
            prevLocation = rc.getLocation();
        }

        if (parentLoc == null) {
            RobotInfo[] friends = rc.senseNearbyRobots(42069,rc.getTeam());
            int mini=42069;
            for (RobotInfo friend : friends) {
                if (friend.type == RobotType.HEADQUARTERS) {
                    if (rc.getLocation().distanceSquaredTo(friend.location)<mini) {
                        mini = rc.getLocation().distanceSquaredTo(friend.location);
                        parentLoc = friend.location;
                        rc.setIndicatorString("parentLoc " + parentLoc);
                    }
                }
            }
        }

        if (partnerID == -1) {
            int mini=42069;
            int best=0;
            for (int i=0; i<10; i++) {
                int tmp = rc.readSharedArray(i);
                if (tmp!=0 && rc.canSenseRobot(tmp)) {
                    RobotInfo friend = rc.senseRobot(tmp);
                    if (parentLoc.distanceSquaredTo(friend.location)<mini) {
                        mini = parentLoc.distanceSquaredTo(friend.location);
                        best = i;
                    }
                }
            }
            partnerID = rc.readSharedArray(best);
            if (rc.canWriteSharedArray(best,0)) {
                rc.writeSharedArray(best,0);
            }
        }
        // Try to attack someone
        int radius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);
        if (enemies.length >= 0) {
            for (RobotInfo enemy : enemies) {
                MapLocation toAttack = enemy.location;
                if (rc.canAttack(toAttack)) {
                    rc.setIndicatorString("Attacking");
                    rc.attack(toAttack);
                    return;
                }
            }
        }
        if (rc.canSenseRobot(partnerID)) {
            RobotInfo partner = rc.senseRobot(partnerID);
            Direction dir = Movement.tryMove(rc, partner.location,prevDirection);
            if (dir != null && rc.canMove(dir)) {
                rc.move(dir);
            }
            return;
        }
        // should probably put this in a function
        if (!rc.isMovementReady()) return;
        if (moveCount==0 || !rc.canMove(curDir)) {
            moveCount=4;
            Direction newDirects[] = {
                    curDir,
                    curDir.rotateRight(),
                    curDir.rotateLeft(),
                    curDir.rotateRight().rotateRight(),
                    curDir.rotateLeft().rotateLeft(),
            };
            for (int i=0; i<20; i++) {
                curDir = newDirects[rng.nextInt(newDirects.length)];
                if (rc.canMove(curDir)) {
                    rc.move(curDir);
                    return;
                }
            }
            while (true) {
                curDir = directions[rng.nextInt(directions.length)];
                if (rc.canMove(curDir)) {
                    rc.move(curDir);
                    return;
                }
            }
        }
        moveCount--;
        rc.move(curDir);
    }
}
