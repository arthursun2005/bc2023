package bonknogg;

import battlecode.common.*;

import java.util.*;

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

    static int symmetry = 0;

    static int encode(int x, int y) {
        return x * 60 + y;
    }

    static RobotInfo possibleHQ = null;
    static MapInfo mapInfo = null;
    static int nn,no,on,oo;

    static void tryFindSymmetry() throws GameActionException {
        if (!foundSymmetry) {
            int radius = 2;

            MapLocation toCheck[] = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(),radius);
            int x, y, oppx, oppy;
            MapLocation loc = null;
            // 1 for passable, 2 for impassable
            for (int i = 0; i < toCheck.length; i++) {
                loc = toCheck[i];

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
                symmetry = (possi[1] == 1 ? 1 : (possi[2] == 1 ? 2 : 3));
                foundSymmetry = true;
            }
        }
    }

    public Launcher(RobotController rc) throws GameActionException
    {
        super(rc);
    }

    static MapLocation attackLoc = null;
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

    static int U = 0;

    public long getEnemyWeaknessMetric(RobotInfo enemy, RobotInfo[] friends) throws GameActionException {
        U = countWithin(friends, enemy.location, rc.getType().visionRadiusSquared);
        long adjustedHealth = (enemy.health - U * 6l) * 66666666l + rc.getLocation().distanceSquaredTo(enemy.location) * 66666l + rc.getID();
        if (enemy.type.equals(RobotType.LAUNCHER)) adjustedHealth -= 66666666666l;
        return adjustedHealth;
    }

    static int O = 0;

    public MapLocation getAttackLoc() throws GameActionException
    {
        if (!rc.isActionReady()) return null;
        // int width = rc.getMapWidth();
        // int height = rc.getMapHeight();
        // for (int x = 0; x < width; x++)
        // {
        //     for (int y = 0; y < height; y++)
        //     {
        //         MapLocation loc = new MapLocation(x, y);
        //         if (rc.canAttack(loc))
        //         {
        //             return loc;
        //         }
        //     }
        // }
        // return null;
        O = 0;
        int radius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);
        attackLoc = null;
        long minHealth = 0;
        for (RobotInfo enemy : enemies) {
            MapLocation toAttack = enemy.location;
            if (rc.canAttack(toAttack)) {
                long adjustedHealth = getEnemyWeaknessMetric(enemy, friends);
                if (attackLoc == null || adjustedHealth < minHealth)
                {
                    minHealth = adjustedHealth;
                    attackLoc = toAttack;
                    O = U;
                }
            }
        }
        if (attackLoc != null)
        {
            return attackLoc;
        }
        MapLocation me = rc.getLocation();
        // If everyone else is doing it then we should :/
        for (int dx = -4; dx <= 4; dx++)
        {
            for (int dy = -4; dy <= 4; dy++)
            {
                MapLocation cheese = new MapLocation(me.x + dx, me.y + dy);
                if (rc.canAttack(cheese))
                {
                    if (attackLoc == null || rc.getLocation().distanceSquaredTo(cheese) < rc.getLocation().distanceSquaredTo(attackLoc))
                    {
                        attackLoc = cheese;
                    }
                }
            }
        }
        return attackLoc;

    }

    static int revenge = 0;
    static boolean shouldHalt = false;
    static int lastHealth = 20;

    public Direction getChaseOrRetreatDir() throws GameActionException
    {
        if (!rc.isMovementReady())
        {
            lastHealth = rc.getHealth();
            return null;
        }

        shouldHalt = false;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, opponent);
        // RobotInfo[] enemies = rc.senseNearbyRobots(rc.getType().actionRadiusSquared, opponent);

        int friendOffensiveCnt = 6 + rc.getHealth();
        int enemyOffensiveCnt = 0;
        int delta = rc.getHealth() - lastHealth;
        lastHealth = rc.getHealth();
        MapLocation weakLoc = null;
        long minHealth = 0;
        boolean hasCarrier = false;
        for (RobotInfo friend : friends)
        {
            if (friend.type.equals(RobotType.LAUNCHER))
            {
                friendOffensiveCnt += 6 + friend.health;
            }
            if (friend.type.equals(RobotType.CARRIER)) hasCarrier = true;
        }
        MapLocation ehq = null;
        for (RobotInfo enemy : enemies)
        {
            if (enemy.type.equals(RobotType.HEADQUARTERS))
            {
                if (creationRound <= 60)
                {
                    enemyOffensiveCnt++;
                    ehq = enemy.location;
                }
                continue;
            }
            if (enemy.type.equals(RobotType.LAUNCHER))
            {
                enemyOffensiveCnt += 6 + enemy.health;
            }

            long adjustedHealth = getEnemyWeaknessMetric(enemy, friends);
            if (weakLoc == null || adjustedHealth < minHealth)
            {
                minHealth = adjustedHealth;
                weakLoc = enemy.location;
            }
        }
        if (weakLoc == null)
        {
            weakLoc = ehq;
        }
        // MapLocation[] clouds = rc.senseNearbyCloudLocations(rc.getType().actionRadiusSquared);
        // for (MapLocation cloud : clouds)
        // {
        //     if (rc.getLocation().distanceSquaredTo(cloud) <= 8) continue;
        //     // if (enemyOffensiveCnt > 0)
        //     enemyOffensiveCnt += 2;
        //     long adjustedHealth = 1234567891011l + rc.getLocation().distanceSquaredTo(cloud);
        //     if (weakLoc == null || adjustedHealth < minHealth)
        //     {
        //         weakLoc = new MapLocation(rc.getMapWidth()-parentLoc.x-1,rc.getMapHeight()-parentLoc.y-1);
        //         // minHealth = adjustedHealth;
        //         // weakLoc = cloud;
        //     }
        // }

        // IDK MAN sob sob sob sob sob
        // enemyOffensiveCnt += rc.senseNearbyCloudLocations().length * 123456789;
        // enemyOffensiveCnt += (rc.senseNearbyCloudLocations(rc.getType().actionRadiusSquared).length - rc.senseNearbyCloudLocations(8).length) * 3;
        // enemyOffensiveCnt = Math.max(enemyOffensiveCnt, revenge - 8);

        if (enemyOffensiveCnt != 0)
        {
            shouldHalt = true;
        }


        revenge = enemyOffensiveCnt;
        if (rc.isActionReady())
        {
            // friendOffensiveCnt += 6;
        }

        if (delta >= 0 && friendOffensiveCnt > 0)
        {
            // attack
            if (weakLoc != null)
            {
                int big = rc.getType().visionRadiusSquared;
                Direction best = null;
                MapLocation hq = tracker.getClosestHQLoc();
                int hits = 123456789;
                for (Direction dir : directions)
                {
                    if (!rc.canMove(dir)) continue;
                    MapLocation loc = rc.adjacentLocation(dir);
                    int w = loc.distanceSquaredTo(weakLoc) * 66666 + countWithin(enemies, loc, big);
                    // int w = loc.distanceSquaredTo(weakLoc) * 66666 + loc.distanceSquaredTo(hq);
                    if (w < hits)
                    {
                        hits = w;
                        best = dir;
                    }
                }
                return best;
            }
        } else if (delta < 0)
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
            return best;
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
                int w = countWithin(enemies, loc, big) * 66666 + loc.distanceSquaredTo(weakLoc);
                if (w < hits)
                {
                    hits = w;
                    best = dir;
                }
            }
            return best;
            // Direction dir = rc.getLocation().directionTo(weakLoc);
            // Direction[] dirs = {
            //     dir.rotateLeft().rotateLeft(),
            //     dir.rotateRight().rotateRight(),
            //     dir.rotateLeft(),
            //     dir.rotateRight(),
            //     dir.rotateLeft().rotateLeft().rotateLeft(),
            //     dir.rotateRight().rotateRight().rotateRight(),
            // };
            // for (Direction d : dirs)
            // {
            //     if (rc.canMove(d))
            //     {
            //         return d;
            //     }
            // }
        }

        return null;
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

    static boolean crossed = false;

    public int ev(RobotInfo ri, MapLocation o) throws GameActionException
    {
        return ri.ID;
        // return ri.location.distanceSquaredTo(o) * 6666 + ri.getID();
    }

    public void run() throws GameActionException
    {
        if (turnCount >= 1) tryFindSymmetry();
        turnCount++;

        rc.setIndicatorString("SYMMETRY: " + symmetry);

        MapLocation ga = getAttackLoc();
        Direction bd = getChaseOrRetreatDir();

        if (bd != null)
        {
            if (ga != null)
            {
                if ((rc.canSenseLocation(ga) && rc.senseRobotAtLocation(ga).getType().equals(RobotType.CARRIER)) || rc.getLocation().distanceSquaredTo(ga) >= rc.adjacentLocation(bd).distanceSquaredTo(ga))
                {
                    rc.move(bd);
                    ga = getAttackLoc();
                    if (ga != null)
                    {
                        rc.attack(ga);
                    }
                }else{
                    rc.attack(ga);
                    // if (true && rc.canSenseLocation(ga) && rc.senseRobotAtLocation(ga) != null)
                    if (O <= 1)
                    {
                        Direction best = null;
                        int dist = rc.getLocation().distanceSquaredTo(ga);
                        for (Direction dir : directions)
                        {
                            if (!rc.canMove(dir)) continue;
                            int w = rc.adjacentLocation(dir).distanceSquaredTo(ga);
                            if (w > dist)
                            {
                                dist = w;
                                best = dir;
                            }
                        }
                        tryMove(best);
                    }
                    bd = getChaseOrRetreatDir();
                    if (bd != null)
                    {
                        rc.move(bd);
                    }
                }
            }else{
                rc.move(bd);
                ga = getAttackLoc();
                if (ga != null)
                {
                    rc.attack(ga);
                }
            }
            return;
        }

        if (true && ga != null)
        {
            rc.attack(ga);
            // if (rc.canSenseLocation(ga) && rc.senseRobotAtLocation(ga) != null)
            if (O <= 1)
            {
                Direction best = null;
                int dist = rc.getLocation().distanceSquaredTo(ga);
                for (Direction dir : directions)
                {
                    if (!rc.canMove(dir)) continue;
                    int w = rc.adjacentLocation(dir).distanceSquaredTo(ga);
                    if (w > dist)
                    {
                        dist = w;
                        best = dir;
                    }
                }
                tryMove(best);
            }
        }

        if (shouldHalt)
        {
            return;
        }

        RobotInfo[] friends = rc.senseNearbyRobots(-1,rc.getTeam());

        MapLocation bestie = null;
        int lowerCount = 0;

        Collections.sort(Tracker.HQLocations, new Comparator<MapLocation>() {
            public int compare(MapLocation a, MapLocation b) {
                int ax = a.x-rc.getMapWidth()/2, ay = a.y-rc.getMapWidth()/2;
                int bx = a.x-rc.getMapWidth()/2, by = a.y-rc.getMapWidth()/2;
                return ay*bx - by*ax; //idk how this works
            }
        });

        MapLocation oppositeLoc = new MapLocation(rc.getMapWidth()-parentLoc.x-1,rc.getMapHeight()-parentLoc.y-1);

        switch (symmetry) {
            case 1: {
                oppositeLoc = new MapLocation(oppositeLoc.x,parentLoc.y);
                break;
            }
            case 2: {
                oppositeLoc = new MapLocation(parentLoc.x,oppositeLoc.y);
                break;
            }
        }

        /*if (true) {
            for (int i = 0; i < Tracker.HQLocations.size(); i++) {
                if (Tracker.HQLocations.get(i).equals(parentLoc)) {
                    MapLocation betterPar = Tracker.HQLocations.get(Tracker.HQLocations.size() - 1 - i);
                    oppositeLoc = new MapLocation(rc.getMapWidth()-betterPar.x-1,rc.getMapHeight()-betterPar.y-1);
                    rc.setIndicatorString(parentLoc+" "+oppositeLoc);
                }
            }
        }*/

        // int sl = rc.getLocation().distanceSquaredTo(oppositeLoc) * 6666 + rc.getID();
        int sl = rc.getID();

        int mini=sl;
        int more = 0;

        for (RobotInfo friend : friends) {
            if (friend.type == RobotType.LAUNCHER) {
                int e = ev(friend, oppositeLoc);
                if (e < sl) lowerCount++;
                more++;
                if (e < mini) {
                    mini=e;
                    bestie=friend.getLocation();
                }
            }
        }

        if (rc.getLocation().distanceSquaredTo(oppositeLoc) <= 18) {
            crossed = true;
        }

        // if (!crossed)
        // {
        //     if (rc.getRoundNum() % 2 == 0)
        //     {
        //         moveTo(oppositeLoc);
        //     }
        // }else{
        //     spreadOut(false);d
        // }

        int dx = oppositeLoc.x - rc.getLocation().x;
        int dy = oppositeLoc.y - rc.getLocation().y;
        int dist = (Math.abs(dx) + Math.abs(dy))/2;
        boolean shouldMove = (dist%3 != 2-(rc.getRoundNum()/5)%3);
        //rc.setIndicatorString("hmmm "+shouldMove);

        if (mini < sl && lowerCount < 9) {
            if (isStuck(bestie)) {
                spreadOut(true);
            }
            else if (rc.getLocation().distanceSquaredTo(bestie) > 2)
            {
                moveTo(bestie);
            }else if (more >= -2)
            {
                if (shouldMove) moveTo(oppositeLoc);
            }
        }
        else {
            if (!crossed) {
                if (more >= -2)
                {
                    if (shouldMove) moveTo(oppositeLoc);
                }
            }else{
                if (rc.getID() % 2 == 0)
                {
                    moveRandom();
                } else {
                    if (shouldMove) moveTo(oppositeLoc);
                }
            }
        }
        // tryProtect();

        /*MapLocation center = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
        moveTo(center);

        spreadOut(true);*/

        ga = getAttackLoc();
        if (ga != null)
        {
            rc.attack(ga);
        }
    }
}
