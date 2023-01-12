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

    public void runUnit() throws GameActionException {

            Direction dir; MapLocation newLoc;

            do {
                dir = directions[rng.nextInt(directions.length)];
                newLoc = rc.getLocation().add(dir);
            } while (!rc.canBuildRobot(RobotType.CARRIER, newLoc));

            rc.buildRobot(RobotType.CARRIER, newLoc);

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
//                            System.out.println(closest + " " + temp + " " + ideal);
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
