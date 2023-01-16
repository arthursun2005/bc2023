package Mining5;

import battlecode.common.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Launcher extends Robot {
    static MapLocation parentLoc = null;
    ArrayList<Integer> randomPermutation = new ArrayList<>();
    MapLocation toGuard = null;

    MapLocation lastSeen = null;
    static boolean attacking = false;
    public boolean tryAttack() throws GameActionException
    {
        int radius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);
        MapLocation attackLoc = null;
        int minHealth = -1;
        for (RobotInfo enemy : enemies) {
            MapLocation toAttack = enemy.location;
            if (rc.canAttack(toAttack)) {
                int adjustedHealth = enemy.getHealth() * 123456 + enemy.getID();
                if (enemy.type.equals(RobotType.LAUNCHER)) adjustedHealth -= 123456789;
                if (minHealth == -1 || adjustedHealth < minHealth)
                {
                    minHealth = adjustedHealth;
                    attackLoc = toAttack;
                }
            }
        }
        if (attackLoc != null)
        {
            rc.attack(attackLoc);
            return true;
        }
        return false;
    }

    public boolean tryChaseOrRetreat() throws GameActionException
    {
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, opponent);
        int friendOffensiveCnt = 0;
        int enemyOffensiveCnt = 0;
        MapLocation weakLoc = null;
        int minHealth = -1;
        for (RobotInfo friend : friends)
        {
            if (friend.type.equals(RobotType.LAUNCHER))
            {
                friendOffensiveCnt++;
            }
        }
        for (RobotInfo enemy : enemies)
        {
            if (enemy.type.equals(RobotType.LAUNCHER))
            {
                enemyOffensiveCnt++;
            }

            int adjustedHealth = enemy.getHealth() * 123456 + enemy.getID();
            if (enemy.type.equals(RobotType.LAUNCHER)) adjustedHealth -= 123456789;
            if (minHealth == -1 || adjustedHealth < minHealth)
            {
                minHealth = adjustedHealth;
                weakLoc = enemy.getLocation();
            }
        }

        if (enemyOffensiveCnt > friendOffensiveCnt + 3)
        {
            // retreat
            moveTo(nearestHQ());
        }else if (friendOffensiveCnt > enemyOffensiveCnt + 5) {
            // attack
            if (weakLoc != null)
            {
                moveTo(weakLoc);
            }
        }
        return enemies.length > 0;
    }

    public Launcher(RobotController rc) throws GameActionException {
        super(rc);
        for (int i = 0; i < 14; i++) {
            randomPermutation.add(i);
        }
        Collections.shuffle(randomPermutation);

        int next = rng.nextInt(15);

        if (rc.getRoundNum() <= 3) return;
        if (next != 0) {
            rc.setIndicatorString(Integer.toString(next));
            attacking = true;
        }
    }
    public void init() throws GameActionException {
    }
    public void runUnit() throws GameActionException {

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

        if (attacking) {
            rc.setIndicatorString("I am ATTACKING ");


            tryAttack();
            if (tryChaseOrRetreat()) {
                return;
            }
            // tryProtect();
            if (rng.nextBoolean()) {
                MapLocation center = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
                moveTo(center);
                return;
            }
            if (rc.getRoundNum() <= 400) spreadOut(true);
            else {
                moveRandom();
            }
        }

        // Try to attack someone
        int radius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);

        if (enemies.length > 0) {
            MapLocation toAttack = null;
            for (RobotInfo enemy : enemies) {

                MapLocation temp = enemy.location;
                if (rc.canAttack(temp)) {
                    if (enemy.getType() == RobotType.LAUNCHER) {
                        toAttack = temp;
                        break;
                    }
                    toAttack = temp;
                }
            }

            if (toAttack != null) rc.attack(toAttack);
        }

        if (attacking) {
            rc.setIndicatorString("I am ATTACKING ");


            tryAttack();
            if (tryChaseOrRetreat())
            {
                return;
            }
            // tryProtect();
            if (rng.nextBoolean() && rc.getRoundNum() <= 200)
            {
                MapLocation center = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
                moveTo(center);
                return;
            }
            spreadOut(true);

        } else {

            // Location to guard
            if (toGuard == null) {
                ArrayList<MapLocation> mapLocations = new ArrayList<>();

                for (int i = 0; i < 14; i++) {

                    int current = (randomPermutation.get(i));

                    // [50, 63]

                    int comm = rc.readSharedArray(current + 50);
                    if (comm != 0) {
                        comm--;
                        mapLocations.add(wellUtility.getWellLocation(comm));
                    }
                }

                Collections.sort(mapLocations, new Comparator<MapLocation>() {
                    public int compare(MapLocation a, MapLocation b) {
                        return parentLoc.distanceSquaredTo(a) - parentLoc.distanceSquaredTo(b);
                    }
                });

                if (rc.getRoundNum() <= 3) {
                    toGuard = mapLocations.get(0);
                } else {
                    toGuard = mapLocations.get(rng.nextInt(mapLocations.size()));
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

                if (rc.getLocation().distanceSquaredTo(toGuard) <= 4) {
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

                spreadOut(false);
            }


        }



    }
}
