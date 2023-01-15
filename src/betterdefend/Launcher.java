package betterdefend;

import battlecode.common.*;

import java.util.ArrayList;
import java.util.Comparator;

public class Launcher extends Robot
{


    static long arr[] = new long[195]; // 2^63 - 1
    // 3 per location, 21 locations in one long, need 180

    static void put(int loc, int val) {
        arr[loc / 19] |= ((long) val << ((loc % 19) * 3));
    }

    static int read(int loc) {
        return (int) ((arr[loc / 19] >> ((loc % 19)*3))%8);
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

    static boolean isStuck(MapLocation loca) throws GameActionException {
        for (int dx = -1; dx <= 1; dx++ ) {
            for (int dy = -1; dy <= 1; dy++ ) {
                MapLocation tmp = new MapLocation(loca.x + dx, loca.y + dy);
                if (!rc.onTheMap(tmp)) continue;
                if (!rc.canSenseLocation(tmp)) return false;
                if (rc.sensePassability(tmp)&&rc.senseRobotAtLocation(tmp)==null) return false;
            }
        }
        return true;
    }

    static boolean crossed = false;

    static int symmetry = 0;

    static int encode(int x, int y) {
        return x * 60 + y;
    }

    static RobotInfo possibleHQ = null;
    static MapInfo mapInfo = null;
    static int nn,no,on,oo;

    static void tryFindSymmetry() throws GameActionException {
        if (!foundSymmetry) {
            int radius = 18;

            MapLocation toCheck[] = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(),radius);
            int x, y, oppx, oppy, val;
            MapLocation loc = null;
            // 1 for passable, 2 for impassable
            for (int i = 0; i < toCheck.length; i++) {
                loc = toCheck[i];
                val = rc.getLocation().distanceSquaredTo(loc);

                if (val < 9 || val/2 == 8) continue;
                //System.out.println(val);

                if (!rc.canSenseLocation(loc)) continue; // 5

                x = loc.x; y = loc.y;
                oppx = rc.getMapWidth()-x-1; oppy = rc.getMapHeight()-y-1;

                possibleHQ = rc.senseRobotAtLocation(loc); // 25
                mapInfo = rc.senseMapInfo(loc); // 10

                if (possibleHQ != null && possibleHQ.getType().equals(RobotType.HEADQUARTERS)) {
                    put(encode(x, y), 1);
                } else if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) {
                    put(encode(x, y), 2);
                } else if (mapInfo.hasCloud()) {
                    put(encode(x, y), 3);
                } else if (rc.sensePassability(loc)) {
                    put(encode(x, y), 4);
                } else {
                    put(encode(x, y), 5);
                }

                nn=read(encode(x, y));
                on=read(encode(oppx, y));
                no=read(encode(x, oppy));
                oo=read(encode(oppx, oppy));

                if (on != 0 && on != nn) possi[1] = 0;
                if (no != 0 && no != nn) possi[2] = 0;
                if (oo != 0 && oo != nn) possi[3] = 0;
            }

            if (possi[1]+possi[2]+possi[3]==1) {
                System.out.println("SYMMETRY FOUND" + (possi[1] == 1 ? 1 : (possi[2] == 1 ? 2 : 3)));
                rc.setIndicatorString("SYMMETRY FOUND" + (possi[1] == 1 ? 1 : (possi[2] == 1 ? 2 : 3)));
                symmetry = (possi[1] == 1 ? 1 : (possi[2] == 1 ? 2 : 3));
                foundSymmetry = true;
            }
        }
    }

    static int counter = 0;
    static MapLocation toGuard;

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
//
//        if (toGuard == null && rc.getID() % 3 == 0) {
//            toGuard = tracker.getOptimalWell();
//        }
//
//        if (toGuard != null) {
//            rc.setIndicatorString("I am guarding " + toGuard);
//            if (rc.getLocation().distanceSquaredTo(toGuard) <= 15) {
//
//                moveTo(new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2));
//
//            } else{
//                moveTo(toGuard);
//            }
//        }


        RobotInfo[] friends = rc.senseNearbyRobots(42069,rc.getTeam());
        int mini=rc.getID();
        MapLocation bestie = null;
        int lowerCount = 0;
        int launcherCount = 0;

        for (RobotInfo friend : friends) {
            if (friend.type == RobotType.LAUNCHER) {
                launcherCount++;
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
        crossed = true;

        if (mini < rc.getID() && lowerCount < 9) {
            if (isStuck(bestie)) {
                spreadOut(true);
            }
            else if (rc.getLocation().distanceSquaredTo(bestie) > 2)
            {
                moveTo(bestie);
            }
        }
        else {
            if (!crossed) {
                moveTo(oppositeLoc);
            }else{
                ArrayList<MapLocation> groups = tracker.getEnemyGroups();

                groups.sort(new Comparator<MapLocation>() {
                    public int compare(MapLocation a, MapLocation b) {

                        return rc.getLocation().distanceSquaredTo(a) - rc.getLocation().distanceSquaredTo(b);
                    }
                });

//                if (rc.canWriteSharedArray(0, 0)) {
//                    ArrayList<MapLocation> enemyGroups = tracker.getEnemyGroups();
//                    if (enemyGroups.size() > 0 && rc.getRoundNum() % 2 == 0 && rng.nextInt(3) < 2) {
//                        MapLocation closest = enemyGroups.get(0);
//                        MapLocation pos = rc.getLocation();
//                        int optDist = pos.distanceSquaredTo(closest);
//                        for (MapLocation enemyGroup : enemyGroups) {
//                            int curDist = pos.distanceSquaredTo(enemyGroup);
//                            if (curDist < optDist) {
//                                optDist = curDist;
//                                closest = enemyGroup;
//                            }
//                        }
//                        moveTo(closest);
//                        tracker.removeEnemyGroup(closest);
//                        rc.setIndicatorString("Going to " + closest);
//                    } else {
//                        moveRandom();
//                    }
//                } else {
//                    moveRandom();
//                }

                if (!groups.isEmpty()) {
                    moveTo(groups.get(0));
                } else {
                    if (rc.getID()%3==-1) {
                        if (launcherCount > 3) {
                            switch (symmetry) {
                                case 1: {
                                    moveTo(new MapLocation(oppositeLoc.x,parentLoc.y));
                                    break;
                                }
                                case 2: {
                                    moveTo(new MapLocation(parentLoc.x,oppositeLoc.y));
                                    break;
                                }
                                default: {
                                    if (possi[3]==1) moveTo(oppositeLoc);
                                    else moveTo(new MapLocation(parentLoc.x,oppositeLoc.y));
                                    break;
                                }
                            }
                        }
                        else {
                            //moveTo(tracker.getClosestHQLoc());
                            //moveRandom();
                            MapLocation nearHQ = tracker.getClosestHQLoc();
                            if (rc.getLocation().distanceSquaredTo(nearHQ) > 10) {
                                moveTo(nearHQ);
                            }
                            else {
                                spreadOut(true);
                            }
                        }
                    }
                    else {
                        moveRandom();
                    }


//                    System.out.println("I AM DEFENDING");
//                    if (toGuard == null) {
//                        toGuard = tracker.getOptimalWell();
//                    }
//
//                    if (toGuard != null) {
//                        rc.setIndicatorString("I am guarding " + toGuard);
//                        if (rc.getLocation().distanceSquaredTo(toGuard) <= 10) {
//
//
//                            Direction targetDirection = rc.getLocation().directionTo(new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2));
//                            if (targetDirection == Direction.CENTER) {
//                                targetDirection = Direction.NORTH;
//                            }
//
//                            Direction dirs[] = {targetDirection, targetDirection.rotateRight(), targetDirection.rotateLeft()};
//
//                            for (int i = 0; i < 3; i++) {
//                                if (rc.canMove(dirs[i])) {
//                                    rc.move(dirs[i]);
//                                    break;
//                                }
////                                targetDirection = targetDirection.rotateRight();
//                            }
//
////                            moveTo(rc.getLocation().add(possible[rng.nextInt(possible.length)]));
//                        } else{
//                            moveTo(toGuard);
//                        }
//                    }
                }
            }
        }
        // tryProtect();

        /*MapLocation center = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
        moveTo(center);

        spreadOut(true);*/
        tryAttack();
    }
}
