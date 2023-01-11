package Mining3;

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

        moveRandom();
    }
}
