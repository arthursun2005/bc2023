package omegalul;

import battlecode.common.*;
import static omegalul.RobotPlayer.*;
public class Headquarter {

    /**
     * Run a single turn for a Headquarters.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void runHeadquarters(RobotController rc) throws GameActionException {
        if (rc.canBuildAnchor(Anchor.STANDARD)) {
            // If we can build an anchor do it!
            rc.buildAnchor(Anchor.STANDARD);
            rc.setIndicatorString("Building anchor! " + rc.getAnchor());
        }
        else if (rc.getAnchor() != null && rc.getResourceAmount(ResourceType.ADAMANTIUM)>=50) {
            Direction dir; MapLocation newLoc;
            do {
                dir = directions[rng.nextInt(directions.length)];
                newLoc = rc.getLocation().add(dir);
            } while (!rc.canBuildRobot(RobotType.CARRIER, newLoc));
            rc.setIndicatorString("Trying to build a carrier");
            rc.buildRobot(RobotType.CARRIER, newLoc);
        }
        if (rc.getResourceAmount(ResourceType.MANA)-rc.getResourceAmount(ResourceType.ADAMANTIUM)>=60) {
            Direction dir; MapLocation newLoc;
            do {
                dir = directions[rng.nextInt(directions.length)];
                newLoc = rc.getLocation().add(dir);
            } while (!rc.canBuildRobot(RobotType.LAUNCHER, newLoc));
            rc.setIndicatorString("Trying to build a launcher");
            rc.buildRobot(RobotType.LAUNCHER, newLoc);
        }
    }
}
