package butterfly2;

import battlecode.common.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Launcher extends Robot
{


    static long arr[] = new long[185]; // 2^63 - 1
    // 3 per location, 21 locations in one long, need 180

    static void put(int loc, int val) {
        int idx = loc / 19, remainder = loc % 19;

        arr[idx] |= ((long) val << ((remainder) * 3));
    }

    static int read(int loc) {
        int idx = loc / 19, remainder = loc % 19;

        return (int) ((arr[idx] >> (remainder*3))%8);
    }
    static int possi[] = {0,1,1,1};

    static boolean foundSymmetry = false;

    public Launcher(RobotController rc) throws GameActionException
    {
        super(rc);
    }

    static MapLocation attackLoc = null;

    public boolean tryAttack() throws GameActionException
    {
        int radius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);
        attackLoc = null;
        int minHealth = -1;
        int theID = -1;
        for (RobotInfo enemy : enemies) {
            MapLocation toAttack = enemy.location;
            if (rc.canAttack(toAttack)) {
                int adjustedHealth = enemy.getHealth() * 123456 + enemy.getID();
                if (enemy.type.equals(RobotType.LAUNCHER)) adjustedHealth -= 123456789;
                if (minHealth == -1 || adjustedHealth < minHealth)
                {
                    minHealth = adjustedHealth;
                    theID = enemy.getID();
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
        int friendOffensiveCnt = 5 + rc.getHealth();
        int enemyOffensiveCnt = 0;
        MapLocation weakLoc = null;
        int minHealth = -1;
        for (RobotInfo friend : friends)
        {
            if (friend.type.equals(RobotType.LAUNCHER))
            {
                friendOffensiveCnt += 5 + friend.health;
            }
        }
        for (RobotInfo enemy : enemies)
        {
            if (enemy.type.equals(RobotType.HEADQUARTERS)) continue;
            if (enemy.type.equals(RobotType.LAUNCHER))
            {
                enemyOffensiveCnt += 5 + enemy.health;
            }

            int adjustedHealth = enemy.getHealth() * 123456 + enemy.getID();
            if (enemy.type.equals(RobotType.LAUNCHER)) adjustedHealth -= 123456789;
            if (minHealth == -1 || adjustedHealth < minHealth)
            {
                minHealth = adjustedHealth;
                weakLoc = enemy.getLocation();
            }
        }

        /*if (enemyOffensiveCnt > friendOffensiveCnt + 5)
        {
            // retreat
            moveTo(tracker.getClosestHQLoc());
        }else */
        if (true) {
            // attack
            if (weakLoc != null)
            {
                moveTo(weakLoc);
            }
        }

        if (enemies.length == 1 && enemies[0].type.equals(RobotType.HEADQUARTERS)) return false;
        return enemies.length > 0;
    }

    public void tryProtect() throws GameActionException
    {
        RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());
        MapLocation HQLoc = tracker.getClosestHQLoc();
        int dist = rc.getLocation().distanceSquaredTo(HQLoc);
        MapLocation weakLoc = null;
        MapLocation center = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
        for (RobotInfo friend : friends)
        {
            int w = friend.getLocation().distanceSquaredTo(center);
            if (friend.type.equals(RobotType.CARRIER))
            {
                // w -= 123456789;
            }
            if (w < dist)
            {
                dist = w;
                weakLoc = friend.getLocation();
            }
        }

        if (weakLoc != null)
        {
            moveTo(weakLoc);
        }
    }

    static boolean crossed = false;

    static int encode(int x, int y) {
        return x * 60 + y;
    }
    static void tryFindSymmetry() throws GameActionException {
        if (!foundSymmetry) {
            int radius = rc.getType().visionRadiusSquared;

            MapLocation toCheck[] = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(),radius);
            // 1 for passable, 2 for impassable
            for (int i = 0; i < toCheck.length; i++) {
                MapLocation loc = toCheck[i];

                if (rc.getLocation().distanceSquaredTo(loc) < 18) continue;

                if (!rc.canSenseLocation(loc)) continue; // 5

                int x = loc.x, y = loc.y;
                int oppx = rc.getMapWidth()-x-1, oppy = rc.getMapHeight()-y-1;

                RobotInfo possibleHQ = rc.senseRobotAtLocation(loc); // 25
                MapInfo mapInfo = rc.senseMapInfo(loc); // 10

                if (possibleHQ != null && possibleHQ.getType().equals(RobotType.HEADQUARTERS)) {
                    put(encode(x, y), 1);
                } else if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) {
                    put(encode(x, y), 2);

                    // CHECK FOR CLOUD AFTER THIS
                } else if (mapInfo.hasCloud()) {
                    put(encode(x, y), 3);
                } else if (rc.sensePassability(loc)) {
                    put(encode(x, y), 4);
                } else {
                    put(encode(x, y), 5);
                }

                if (rc.getID() == 12950) {
                    System.out.println("Explore " + x + " " + y);
                }
                if (read(encode(oppx, y)) != 0 && read(encode(oppx, y)) !=  read(encode(x, y))) possi[1] = 0;
                if (read(encode(x, oppy)) != 0  && read(encode(x, oppy)) != read(encode(x, y))) possi[2] = 0;
                if (read(encode(oppx, oppy)) != 0 && read(encode(oppx, oppy)) != read(encode(x, y))) possi[3] = 0;

                if (rc.getID() == 12950) System.out.println(x + " " + y + ": " + read(encode(x, y)));
            }

            if (possi[1]+possi[2]+possi[3]==1) {
                System.out.println("SYMMETRY FOUND" + (possi[1] == 1 ? 1 : (possi[2] == 1 ? 2 : 3)));
                rc.setIndicatorString("SYMMETRY FOUND" + (possi[1] == 1 ? 1 : (possi[2] == 1 ? 2 : 3)));
                foundSymmetry = true;
            }
        }
    }

    static int counter = 0;
    public void run() throws GameActionException
    {
        if (counter >= 1) tryFindSymmetry();
        counter++;

        if (tryAttack()) {
            Direction dir = rc.getLocation().directionTo(attackLoc).opposite();
            Direction dirs[] = {
                dir,
                dir.rotateLeft(),
                dir.rotateRight(),
                dir.rotateLeft().rotateLeft(),
                dir.rotateRight().rotateRight(),
            };
            for (Direction tryDir : dirs) {
                if (tryMove(tryDir)) return;
            }
        }
        if (tryChaseOrRetreat())
        {
            tryAttack();
            return;
        }

        RobotInfo[] friends = rc.senseNearbyRobots(42069,rc.getTeam());
        int mini=rc.getID();
        MapLocation bestie = null;
        int lowerCount = 0;

        for (RobotInfo friend : friends) {
            if (friend.type == RobotType.LAUNCHER) {
                if (friend.getID() < rc.getID()) lowerCount++;
                if (friend.getID() < mini) {
                    mini=friend.getID();
                    bestie=friend.getLocation();
                }
            }
        }

        MapLocation oppositeLoc = new MapLocation(rc.getMapWidth()-parentLoc.x-1,rc.getMapHeight()-parentLoc.y-1);
        if (rc.getLocation().distanceSquaredTo(oppositeLoc)*2<=rc.getLocation().distanceSquaredTo(parentLoc)) {
            crossed = true;
        }

        if (mini < rc.getID() && lowerCount < 9) {
            if (rc.getLocation().distanceSquaredTo(bestie) > 2)
            {
                moveTo(bestie);
            }
        }
        else {
            if (!crossed) {
                if (rc.getRoundNum() % 2 == 0)
                {
                    moveTo(oppositeLoc);
                }
            }else{
                moveRandom();
            }
        }
        // tryProtect();

        /*MapLocation center = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
        moveTo(center);

        spreadOut(true);*/
        tryAttack();
    }
}
