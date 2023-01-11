package Mining;

import battlecode.common.*;
public class Launcher extends Robot {
    static MapLocation parentLoc = null;
    static int partnerID = -1;
    public Launcher(RobotController rc) throws GameActionException {
        super(rc);

    }
    public void runUnit() throws GameActionException {

        if (parentLoc == null) {
            RobotInfo[] friends = rc.senseNearbyRobots(42069,rc.getTeam());
            int mini=42069;
            for (RobotInfo friend : friends) {
                if (friend.type == RobotType.HEADQUARTERS) {
                    if (rc.getLocation().distanceSquaredTo(friend.location)<mini) {
                        mini = rc.getLocation().distanceSquaredTo(friend.location);
                        parentLoc = friend.location;
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
                    rc.attack(toAttack);
                    return;
                }
            }
        }
        if (rc.canSenseRobot(partnerID)) {
            RobotInfo partner = rc.senseRobot(partnerID);
            Direction dir = Movement.tryMove(rc, partner.location, prevDirection);
            if (dir != null && rc.canMove(dir)) {
                rc.move(dir);
            }
            return;
        }
        // should probably put this in a function
        moveRandom();
    }
}
