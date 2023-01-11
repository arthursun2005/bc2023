package Mining5;

import battlecode.common.*;

import java.util.ArrayList;
import java.util.Collections;
public class Launcher extends Robot {
    static MapLocation parentLoc = null;
    ArrayList<Integer> randomPermutation = new ArrayList<>();

    static boolean attacking = false;
    public Launcher(RobotController rc) throws GameActionException {
        super(rc);
        for (int i = 0; i < 90; i++) {
            randomPermutation.add(i);
        }

        Collections.shuffle(randomPermutation);

        if (rng.nextInt(4) != 0) {
            attacking = true;
        }
    }
    public void init() throws GameActionException {
    }
    public void runUnit() throws GameActionException {

        System.out.println("bruh");
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
        if (enemies.length > 0) {
            for (RobotInfo enemy : enemies) {
                MapLocation toAttack = enemy.location;
                if (rc.canAttack(toAttack)) {
                    rc.attack(toAttack);
                    break;
                }
            }
        }

        if (attacking) {
            moveRandom();
        } else {

            // Location to guard
            MapLocation toGuard = null;

            for (int i = 0; i < 100; i++) {
                if (randomPermutation.get(i) <= 59) {
                    // it's an island
                    // check .get(i) / 2
                    int current = (randomPermutation.get(i)) / 2 + 1;
                    int comm = rc.readSharedArray(current);

                    if (comm != 0) {
                        comm--;
                        int x = comm / 69, y = comm % 69;
                        toGuard = new MapLocation(x, y);
                        break;
                    }
                } else {
                    // assign to wells
                    int current = (randomPermutation.get(i) - 10);

                    if (current > 63) continue;

                    // [50, 63]

                    int comm = rc.readSharedArray(current);
                    if (comm != 0) {
                        comm--;
                        int x = comm / 69, y = comm % 69;
                        toGuard = new MapLocation(x, y);
                        break;
                    }
                }
            }

            if (toGuard != null) {
                rc.setIndicatorString("I am guarding " + toGuard);
                if (rc.getLocation().distanceSquaredTo(toGuard) <= 5) {
                    // Destination + (3, 0)

                    Direction targetDirection = rc.getLocation().directionTo(toGuard).rotateRight().rotateRight();
                    if (targetDirection == Direction.CENTER) {
                        targetDirection = Direction.NORTH;
                    }
                    Direction[] possible = new Direction[5];
                    for (int i = 0; i < 5; i++) {
                        possible[i] = targetDirection;
                        targetDirection = targetDirection.rotateRight();
                    }

                    moveToLocation(rc.getLocation().add(possible[rng.nextInt(possible.length)]));
                } else{
                    moveToLocation(toGuard);
                }
            } else {
                moveRandom();
            }


        }



    }
}
