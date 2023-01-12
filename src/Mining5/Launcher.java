package Mining5;

import battlecode.common.*;

import java.util.ArrayList;
import java.util.Collections;
public class Launcher extends Robot {
    static MapLocation parentLoc = null;
    ArrayList<Integer> randomPermutation = new ArrayList<>();
    MapLocation toGuard = null;

    MapLocation lastSeen = null;
    static boolean attacking = false;
    public Launcher(RobotController rc) throws GameActionException {
        super(rc);
        for (int i = 0; i < 14; i++) {
            randomPermutation.add(i);
        }
        Collections.shuffle(randomPermutation);

        int next = rng.nextInt(4);

        if (next == 0) {
            rc.setIndicatorString(Integer.toString(next));
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
            rc.setIndicatorString("I am ATTACKING ");

            moveRandom();
        } else {

            // Location to guard
            if (toGuard == null) {
                for (int i = 0; i < 14; i++) {

                    int current = (randomPermutation.get(i));

                    // [50, 63]

                    int comm = rc.readSharedArray(current + 50);
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

                RobotInfo[] robotInfos = rc.senseNearbyRobots();

                for (int i = 0; i  < robotInfos.length; i++) {
                    if (robotInfos[i].getTeam().equals(rc.getTeam().opponent())) {
                        lastSeen = robotInfos[i].location;
                    }
                }

                if (rc.getLocation().distanceSquaredTo(toGuard) <= 2) {
                    // Destination + (3, 0)

                    if (lastSeen == null) {
                        Direction targetDirection = toGuard.directionTo(parentLoc).rotateRight().rotateRight();
                        if (targetDirection == Direction.CENTER) {
                            targetDirection = Direction.NORTH;
                        }
                        Direction[] possible = new Direction[5];
                        for (int i = 0; i < 5; i++) {
                            possible[i] = targetDirection;
                            targetDirection = targetDirection.rotateRight();
                        }

                        moveToLocation(rc.getLocation().add(possible[rng.nextInt(possible.length)]));
                    } else {
                        moveToLocation(lastSeen);
                    }

                } else{
                    moveToLocation(toGuard);
                }
            } else {
                rc.setIndicatorString("I am randoming ");

                moveRandom();
            }


        }



    }
}
