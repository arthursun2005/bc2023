package bruh;

import battlecode.common.*;
import static bruh.RobotPlayer.*;
public class Headquarter {

    /**
     * Run a single turn for a Headquarters.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */

    static Direction globalDir = Direction.CENTER;
    static void runHeadquarters(RobotController rc) throws GameActionException {
        if (!globalDir.equals(Direction.CENTER)) {

            Direction[] directions = {globalDir,
                    globalDir.rotateLeft(),
                    globalDir.rotateRight(),
                    globalDir.rotateLeft().rotateLeft(),
                    globalDir.rotateRight().rotateRight(),
                    globalDir.rotateLeft().rotateLeft().rotateLeft(),
                    globalDir.rotateRight().rotateRight().rotateRight(),
                    globalDir.rotateLeft().rotateLeft().rotateLeft().rotateLeft()
            };

            for (int i = 0; i < directions.length; i++) {
                if (rc.canBuildRobot(RobotType.LAUNCHER, rc.getLocation().add(directions[i]))) {
                    rc.buildRobot(RobotType.LAUNCHER, rc.getLocation().add(directions[i]));
                    globalDir = Direction.CENTER;
                    break;
                }
            }
        }
        if (rc.canBuildAnchor(Anchor.STANDARD)) {
            // If we can build an anchor do it!
            rc.buildAnchor(Anchor.STANDARD);
            rc.setIndicatorString("Building anchor! " + rc.getAnchor());
        }
        else if (rc.getAnchor() != null && rc.getResourceAmount(ResourceType.ADAMANTIUM) >= 50) {
            Direction dir; MapLocation newLoc;
            do {
                dir = directions[rng.nextInt(directions.length)];
                newLoc = rc.getLocation().add(dir);
            } while (!rc.canBuildRobot(RobotType.CARRIER, newLoc));
            rc.setIndicatorString("Trying to build a carrier");
            System.out.println("Making carrier\n");
            rc.buildRobot(RobotType.CARRIER, newLoc);
            globalDir = dir;
        }
    }
}
