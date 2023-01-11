package Mining3;

import battlecode.common.*;

public class Headquarter extends Robot {

    /**
     * Run a single turn for a Headquarters.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */

    static Direction globalDir = Direction.CENTER;

    public Headquarter(RobotController rc) throws GameActionException {
        super(rc);
    }

    static int carrierCount = 0;

    public void runUnit() throws GameActionException {
        if (rc.getRoundNum() % 80 == 1 && rc.readSharedArray(0)==0) {
            if (rc.getResourceAmount(ResourceType.MANA) >= 40 && rc.getResourceAmount(ResourceType.ADAMANTIUM) >= 40) {
                Direction dir; MapLocation newLoc;
                do {
                    dir = directions[rng.nextInt(directions.length)];
                    newLoc = rc.getLocation().add(dir);
                } while (!rc.canBuildRobot(RobotType.AMPLIFIER, newLoc));
                rc.setIndicatorString("Trying to build a amplifier");
                rc.buildRobot(RobotType.AMPLIFIER, newLoc);
                globalDir = dir;
            }
        }
        if (rc.canBuildAnchor(Anchor.STANDARD) && rc.getRoundNum() >= 100) {
            // If we can build an anchor do it!
            rc.buildAnchor(Anchor.STANDARD);
            rc.setIndicatorString("Building anchor! " + rc.getAnchor());
            return;
        }
        if (rc.getResourceAmount(ResourceType.MANA)-rc.getResourceAmount(ResourceType.ADAMANTIUM) >= 60) {
            Direction dir; MapLocation newLoc;
            do {
                dir = directions[rng.nextInt(directions.length)];
                newLoc = rc.getLocation().add(dir);
            } while (!rc.canBuildRobot(RobotType.LAUNCHER, newLoc));
            rc.setIndicatorString("Trying to build a launcher");
            rc.buildRobot(RobotType.LAUNCHER, newLoc);
            globalDir = dir;
        }
        else if (rc.getResourceAmount(ResourceType.ADAMANTIUM) >= 50 && (carrierCount < 40 || rc.getAnchor() != null)) {
            Direction dir; MapLocation newLoc;
            do {
                dir = directions[rng.nextInt(directions.length)];
                newLoc = rc.getLocation().add(dir);
            } while (!rc.canBuildRobot(RobotType.CARRIER, newLoc));
            rc.setIndicatorString("Trying to build a carrier");
            rc.buildRobot(RobotType.CARRIER, newLoc);
            globalDir = dir;
            carrierCount++;
        }
    }
}
