package omegaASS;

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
    public void runUnit() throws GameActionException {
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
        else if (rc.getAnchor() != null && rc.getResourceAmount(ResourceType.ADAMANTIUM) >= 60 && rc.getResourceAmount(ResourceType.MANA) >= 60) {
            Direction dir; MapLocation newLoc;
            do {
                dir = directions[rng.nextInt(directions.length)];
                newLoc = rc.getLocation().add(dir);
            } while (!rc.canBuildRobot(RobotType.CARRIER, newLoc));
            rc.setIndicatorString("Trying to build a carrier");
            rc.buildRobot(RobotType.CARRIER, newLoc);
            globalDir = dir;
        }
    }
}
