package butterflydefend;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

// HUGELY TODO

public class Amplifier extends Robot
{
    static Tracker tracker;
    static int waiting = 0;
    static int cooldown = 0;

    public Amplifier(RobotController rc) throws GameActionException
    {
        super(rc);
    }

    public void run() throws GameActionException
    {
        if (waiting > 0) {
            if (!tracker.isEnemyGroup()) {
                tracker.removeEnemyGroup();
                waiting = 0;
            } else {
                rc.setIndicatorString("Waiting " + waiting);
                waiting--;
                if (waiting == 0) {
                    cooldown = 5;
                }
                return;
            }
        }
        cooldown--;
        if (cooldown <= 0 && tracker.isEnemyGroup()) {
            waiting = 5;
            tracker.addEnemyGroup();
            rc.setIndicatorString("Waiting " + waiting);
            return;
        }
        spreadOut(false);
//        moveTo(new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2));
        rc.setIndicatorString("Searching " + rc.senseNearbyRobots(tracker.ENEMY_GROUP_RADIUS, rc.getTeam().opponent()).length + " " + cooldown);
    }
}
