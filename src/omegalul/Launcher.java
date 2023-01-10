package omegalul;

import battlecode.common.*;
import static omegalul.RobotPlayer.*;
public class Launcher {
    static void runLauncher(RobotController rc) throws GameActionException {
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
