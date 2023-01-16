package Mining0;

import battlecode.common.*;

import java.util.Collections;
import java.util.Comparator;

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

    static int anchorCount = 0;
    static MapLocation getClosestMine() {

        // IS THIS A BUG ?????
        // IS THIS A BUG ?????
        // IS THIS A BUG ?????
        // IS THIS A BUG ?????
        // IS THIS A BUG ?????
        // IS THIS A BUG ?????

//        if (wells.length == 0) {
//        }


        wellUtility.wells.sort(new Comparator<CustomWell>() {
            public int compare(CustomWell a, CustomWell b) {

                int deltaA = 0, deltaB = 0;
                return rc.getLocation().distanceSquaredTo(a.getMapLocation()) + deltaA - rc.getLocation().distanceSquaredTo(b.getMapLocation()) - deltaB;
            }
        });

        return wellUtility.wells.get(rng.nextInt(Math.min(wellUtility.wells.size(), 2))).getMapLocation();
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

        int launcherCount = communication.readLauncherCount();
        int carrierCount = communication.readCarrierCount();


        if (rc.getRoundNum() == 500) {
            rc.resign();
        }
        if (rc.canBuildAnchor(Anchor.STANDARD) && launcherCount + carrierCount >= 100 && anchorCount <= 2) {
            // If we can build an anchor do it!

            if (!rc.canBuildAnchor(Anchor.STANDARD)) return;
            rc.buildAnchor(Anchor.STANDARD);
            rc.setIndicatorString("Building anchor! " + rc.getAnchor());
            anchorCount++;
            return;
        }

        RobotType toMake = null;
        if (rc.getRoundNum() <= 4) {
            toMake = RobotType.CARRIER;
            communication.increaseCarrierCount();
        }

        if (toMake == null && ((launcherCount <= carrierCount) || (rc.getRoundNum() >= 120 && launcherCount <= carrierCount * 8)) && rc.getResourceAmount(ResourceType.MANA) >= 60) {
            toMake = RobotType.LAUNCHER;
            communication.increaseLauncherCount();
        } else if (toMake == null && rc.getResourceAmount(ResourceType.MANA)-rc.getResourceAmount(ResourceType.ADAMANTIUM) >= 60) {
            toMake = RobotType.LAUNCHER;
            communication.increaseLauncherCount();
            //should probably make sure we build anchors now
        }

        if (toMake == null && rc.getResourceAmount(ResourceType.ADAMANTIUM) >= 50 && carrierCount <= hqCount * 40 + rc.getRoundNum() / 10) {
            toMake = RobotType.CARRIER;
            communication.increaseCarrierCount();
        }

        if (toMake == RobotType.CARRIER && rc.getRoundNum() <= 500) {
            // spwan it close to the mine

            MapLocation ideal = getClosestMine();
            MapLocation newLoc = null;
            Direction dir;

            boolean found = false;

            int closest = 1_000_000;
            MapLocation closestLoc = null;

            for (int dx = -3; dx <= 3; dx++ ) {
                for (int dy = -3; dy <= 3; dy++ ) {
                    if (dx * dx + dy * dy > 9) continue;
                    MapLocation temp = new MapLocation(ideal.x + dx, ideal.y + dy);
                    if (rc.onTheMap(temp) && rc.canBuildRobot(RobotType.CARRIER, temp) && temp.distanceSquaredTo(ideal) < closest) {
                        closest = temp.distanceSquaredTo(ideal);
                        closestLoc = temp;
                        found = true;
                    }
                }
            }
            if (found) {
                newLoc = closestLoc;
            } else {
                // eh just do it anywhere
                do {
                    dir = directions[rng.nextInt(directions.length)];
                    newLoc = rc.getLocation().add(dir);
                } while (!rc.canBuildRobot(RobotType.CARRIER, newLoc));
            }

            rc.buildRobot(RobotType.CARRIER, newLoc);
            return;
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



// DONT REMOVE YET

//            if (rc.getRoundNum() >= 2 && rc.getRoundNum() <= 50) {
//                // spwan it close to the mine
//
//                MapLocation ideal = getClosestMine();
//                boolean found = false;
//
//                int closest = 1_000_000;
//                MapLocation closestLoc = null;
//
//                for (int dx = -3; dx <= 3; dx++ ) {
//                    for (int dy = -3; dy <= 3; dy++ ) {
//                        if (dx * dx + dy * dy > 9) continue;
//                        MapLocation temp = new MapLocation(ideal.x + dx, ideal.y + dy);
//                        if (rc.onTheMap(temp) && rc.canBuildRobot(RobotType.CARRIER, temp) && temp.distanceSquaredTo(ideal) < closest) {
//                            closest = temp.distanceSquaredTo(ideal);
//                            closestLoc = temp;
//                            found = true;
//                        }
//                    }
//                }
//                if (found) {
//                    newLoc = closestLoc;
//                } else {
//                    // eh just do it anywhere
//                    do {
//                        dir = directions[rng.nextInt(directions.length)];
//                        newLoc = rc.getLocation().add(dir);
//                    } while (!rc.canBuildRobot(RobotType.CARRIER, newLoc));
//                }
//
//            } else {
