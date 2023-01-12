package Mining4;
import battlecode.common.*;
public class Headquarter extends Robot {
    /**
     * Run a single turn for a Headquarters.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
//    static Direction globalDir = Direction.CENTER;
    public Headquarter(RobotController rc) throws GameActionException {
        super(rc);
    }
    public void init() {

    }

    static int carrierCount = 0;

    static MapLocation getClosestMine() {
        WellInfo[] wells = rc.senseNearbyWells();

        // IS THIS A BUG ?????
        // IS THIS A BUG ?????
        // IS THIS A BUG ?????
        // IS THIS A BUG ?????
        // IS THIS A BUG ?????
        // IS THIS A BUG ?????

//        if (wells.length == 0) {
//            System.out.println(rc.getType() + " AINT NOOOW AYYYY");
//        }

        int closestDist = 1_000_000, closestIdx = -1;

        for (int i = 0; i < wells.length; i++) {
            // guaranteed to be one
            int dist = wells[i].getMapLocation().distanceSquaredTo(rc.getLocation());
            if (dist < closestDist) {
                closestDist = dist;
                closestIdx = i;
            }
        }

        return wells[closestIdx].getMapLocation();
    }

    public void runUnit() throws GameActionException {
//        if (rc.getRoundNum() % 80 == 1 && rc.readSharedArray(0)==0) {
//            if (rc.getResourceAmount(ResourceType.MANA) >= 40 && rc.getResourceAmount(ResourceType.ADAMANTIUM) >= 40) {
//                Direction dir; MapLocation newLoc;
//                do {
//                    dir = directions[rng.nextInt(directions.length)];
//                    newLoc = rc.getLocation().add(dir);
//                } while (!rc.canBuildRobot(RobotType.AMPLIFIER, newLoc));
//                rc.setIndicatorString("Trying to build a amplifier");
//                rc.buildRobot(RobotType.AMPLIFIER, newLoc);
//                return;
////                globalDir = dir;
//            }
//        }
        if (rc.canBuildAnchor(Anchor.STANDARD) && rc.getRoundNum() >= 100) {
            // If we can build an anchor do it!
            rc.buildAnchor(Anchor.STANDARD);
            rc.setIndicatorString("Building anchor! " + rc.getAnchor());
            return;
        }

        RobotType toMake = null;

        if (rc.getResourceAmount(ResourceType.MANA)-rc.getResourceAmount(ResourceType.ADAMANTIUM) >= 60) {
            toMake = RobotType.LAUNCHER;
        }

        if (rc.getResourceAmount(ResourceType.ADAMANTIUM) >= 50 && (carrierCount < 40 || rc.getAnchor() != null) && (toMake == null || rng.nextInt(2) == 1)) {
            toMake = RobotType.CARRIER;
            carrierCount++;
        }

        if (toMake != null) {
            Direction dir; MapLocation newLoc;

            do {
                dir = directions[rng.nextInt(directions.length)];
                newLoc = rc.getLocation().add(dir);
            } while (!rc.canBuildRobot(toMake, newLoc));

            rc.buildRobot(toMake, newLoc);
        }
    }
}