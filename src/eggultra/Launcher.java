package eggultra;

import java.util.ArrayList;
import java.util.Comparator;

import battlecode.common.*;

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

    static boolean shouldRun = false;
    static boolean cheesed = false;
    static int turnCount = 0;

    public int countWithin(RobotInfo[] backup, MapLocation a, int tolerance)
    {
        int cnt = 0;
        for (RobotInfo ri : backup)
        {
            if (!ri.type.equals(RobotType.LAUNCHER)) continue;
            if (a.distanceSquaredTo(ri.location) <= tolerance)
            {
                cnt++;
            }
        }
        return cnt;
    }

    public boolean tryAttack() throws GameActionException
    {
        int radius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);
        attackLoc = null;
        int minHealth = 123456789;
        int theID = -1;
        cheesed = false;
        for (RobotInfo enemy : enemies) {
            MapLocation toAttack = enemy.location;
            if (rc.canAttack(toAttack)) {
                int U = countWithin(friends, toAttack, rc.getType().visionRadiusSquared);
                // int adjustedHealth = -U * 66666 + enemy.health;
                // int adjustedHealth = enemy.getHealth() * 66666 - enemy.getID();
                int adjustedHealth = (enemy.health - U * 6) * 66666 - enemy.getID();
                // int adjustedHealth = enemy.getHealth() * 123456 + enemy.getID();
                if (enemy.type.equals(RobotType.LAUNCHER)) adjustedHealth -= 123456789;
                if (adjustedHealth < minHealth)
                {
                    minHealth = adjustedHealth;
                    theID = enemy.getID();
                    attackLoc = toAttack;
                }
            }
        }
        if (attackLoc != null)
        {
            shouldRun = (minHealth < -69000000);
            rc.attack(attackLoc);
            return true;
        }
        // If everyone else is doing it then we should :/
        /*MapLocation[] cheeses = rc.senseNearbyCloudLocations();
        for (MapLocation cheese : cheeses) {
            if (rc.canAttack(cheese))
            {
                rc.attack(cheese);
                attackLoc = cheese;
                System.out.println("cheesed");
                return true;
            }
        }*/
        return false;
    }

    static int revenge = 0;

    public boolean tryChaseOrRetreat() throws GameActionException
    {
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, opponent);
        int friendOffensiveCnt = 6 + rc.getHealth();
        int enemyOffensiveCnt = 0;
        MapLocation weakLoc = null;
        int minHealth = -1;
        boolean hasCarrier = false;
        for (RobotInfo friend : friends)
        {
            if (friend.type.equals(RobotType.LAUNCHER))
            {
                friendOffensiveCnt += 6 + friend.health;
            }
            if (friend.type.equals(RobotType.CARRIER)) hasCarrier = true;
        }
        for (RobotInfo enemy : enemies)
        {
            if (enemy.type.equals(RobotType.HEADQUARTERS)) continue;
            if (enemy.type.equals(RobotType.LAUNCHER))
            {
                enemyOffensiveCnt += 6 + enemy.health;
            }

            // int adjustedHealth = enemy.getHealth() * 123456 + enemy.getID();
            int U = countWithin(friends, enemy.location, rc.getType().visionRadiusSquared);
            // int adjustedHealth = -U * 66666 + enemy.health;
            // int adjustedHealth = enemy.getHealth() * 66666 - enemy.getID();
            int adjustedHealth = (enemy.health - U * 6) * 66666 - enemy.getID();
            if (enemy.type.equals(RobotType.LAUNCHER)) adjustedHealth -= 123456789;
            if (minHealth == -1 || adjustedHealth < minHealth)
            {
                minHealth = adjustedHealth;
                weakLoc = enemy.getLocation();
            }
        }

        // IDK MAN sob sob sob sob sob
        // enemyOffensiveCnt += rc.senseNearbyCloudLocations().length * 123456789;
        enemyOffensiveCnt = Math.max(enemyOffensiveCnt, revenge - 8);
        if (rc.isActionReady())
        {
            // friendOffensiveCnt += 6;
        }

        if (enemyOffensiveCnt == 0) {
            // attack
            if (weakLoc != null)
            {
                moveTo(weakLoc);
            }
        } else if (enemyOffensiveCnt > friendOffensiveCnt - 5)
        {
            // retreat
            // moveTo(tracker.getClosestHQLoc());
            int big = rc.getType().visionRadiusSquared;
            Direction best = null;
            MapLocation hq = tracker.getClosestHQLoc();
            int hits = 123456789;
            for (Direction dir : directions)
            {
                if (!rc.canMove(dir)) continue;
                MapLocation loc = rc.adjacentLocation(dir);
                int w = countWithin(enemies, loc, big) * 66666 + loc.distanceSquaredTo(hq);
                if (w < hits)
                {
                    hits = w;
                    best = dir;
                }
            }
            if (best != null)
            {
                rc.move(best);
            }
        } else if (weakLoc != null)
        {
            // rotate
            int big = rc.getType().visionRadiusSquared;
            Direction best = null;
            int hits = 123456789;
            for (Direction dir : directions)
            {
                if (!rc.canMove(dir)) continue;
                MapLocation loc = rc.adjacentLocation(dir);
                if (loc.distanceSquaredTo(weakLoc) > big) continue;
                int w = countWithin(enemies, loc, big) * 66666 - loc.distanceSquaredTo(weakLoc);
                if (w < hits)
                {
                    hits = w;
                    best = dir;
                }
            }
            if (best != null)
            {
                rc.move(best);
            }
        }

        revenge = enemyOffensiveCnt;

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

    static boolean isStuck(MapLocation loc) throws GameActionException {
        for (int dx = -1; dx <= 1; dx++ ) {
            for (int dy = -1; dy <= 1; dy++ ) {
                MapLocation tmp = new MapLocation(loc.x + dx, loc.y + dy);
                if (!rc.onTheMap(tmp)) continue;
                if (!rc.canSenseLocation(tmp)) return false;
                if (rc.sensePassability(tmp)&&rc.senseRobotAtLocation(tmp)==null) return false;
            }
        }
        return true;
    }

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
            possi=Tracker.readpossi();

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

            Tracker.writepossi(possi);

            if (possi[1]+possi[2]+possi[3]==1) {
                System.out.println("SYMMETRY FOUND" + (possi[1] == 1 ? 1 : (possi[2] == 1 ? 2 : 3)));
                rc.setIndicatorString("SYMMETRY FOUND" + (possi[1] == 1 ? 1 : (possi[2] == 1 ? 2 : 3)));
                symmetry = (possi[1] == 1 ? 1 : (possi[2] == 1 ? 2 : 3));
                foundSymmetry = true;
            }
        }
    }

    static boolean crossed = false;

    public void run() throws GameActionException
    {
        if (turnCount >= 1) tryFindSymmetry();
        turnCount++;
        if (tryAttack()) {
            if (cheesed) return;
            Direction dir = rc.getLocation().directionTo(attackLoc);
            if (shouldRun) dir = dir.opposite();
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
            /*
            if (!crossed) {
                if (rc.getRoundNum() % 2 == 0)
                {
                    moveTo(oppositeLoc);
                }
            }else{
                moveRandom();
            }
            */
            if (!crossed) {
                moveTo(oppositeLoc);
            } else {
                if (true) {
                    if (launcherCount > 3) {
                        ArrayList<MapLocation> groups = tracker.getEnemyGroups();

                        groups.sort(new Comparator<MapLocation>() {
                            public int compare(MapLocation a, MapLocation b) {

                                return rc.getLocation().distanceSquaredTo(a) - rc.getLocation().distanceSquaredTo(b);
                            }
                        });

                        if (!groups.isEmpty()) {
                            moveTo(groups.get(0));
                        } else if (rc.getID()%3!=0) {
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
                    } else {
                        //moveTo(tracker.getClosestHQLoc());
                        //moveRandom();
                        MapLocation nearHQ = tracker.getClosestHQLoc();
                        if (rc.getLocation().distanceSquaredTo(nearHQ) > 10 && rc.getID() % 5 == 0) {
                            moveTo(nearHQ);
                        }
                        else {
                            spreadOut(false);
                        }
                    }
                }
                else {
                    moveRandom();
                }
            }
        }
        tryAttack();
    }
}
