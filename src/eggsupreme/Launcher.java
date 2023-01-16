package eggsupreme;

import java.util.zip.GZIPInputStream;

import battlecode.common.*;

public class Launcher extends Robot
{
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

    public long getEnemyWeaknessMetric(RobotInfo enemy, RobotInfo[] friends) throws GameActionException {
        int U = countWithin(friends, enemy.location, rc.getType().visionRadiusSquared);
        long adjustedHealth = (enemy.health - U * 6l) * 66666666l + rc.getLocation().distanceSquaredTo(enemy.location) * 66666l + rc.getID();
        if (enemy.type.equals(RobotType.LAUNCHER)) adjustedHealth -= 66666666666l;
        return adjustedHealth;
    }

    public MapLocation getAttackLoc() throws GameActionException
    {
        if (!rc.isActionReady()) return null;
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
                }
            }
        }
        if (attackLoc != null)
        {
            return attackLoc;
        }
        // If everyone else is doing it then we should :/
        MapLocation[] cheeses = rc.senseNearbyCloudLocations();
        for (MapLocation cheese : cheeses) {
            if (rc.canAttack(cheese))
            {
                if (attackLoc == null || rc.getLocation().distanceSquaredTo(cheese) < rc.getLocation().distanceSquaredTo(attackLoc))
                {
                    attackLoc = cheese;
                }
            }
        }
        return attackLoc;
    }

    static int revenge = 0;
    static boolean shouldHalt = false;

    public Direction getChaseOrRetreatDir() throws GameActionException
    {
        if (!rc.isMovementReady()) return null;
        shouldHalt = false;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, opponent);
        int friendOffensiveCnt = 6 + rc.getHealth();
        int enemyOffensiveCnt = 0;
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
        for (RobotInfo enemy : enemies)
        {
            if (enemy.type.equals(RobotType.HEADQUARTERS)) continue;
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
        MapLocation[] clouds = rc.senseNearbyCloudLocations(rc.getType().actionRadiusSquared);
        for (MapLocation cloud : clouds)
        {
            if (rc.getLocation().distanceSquaredTo(cloud) <= 8) continue;
            // if (enemyOffensiveCnt > 0) 
            enemyOffensiveCnt += 3;
            long adjustedHealth = 1234567891011l + rc.getLocation().distanceSquaredTo(cloud);
            if (weakLoc == null || adjustedHealth < minHealth)
            {
                weakLoc = new MapLocation(rc.getMapWidth()-parentLoc.x-1,rc.getMapHeight()-parentLoc.y-1);
                // minHealth = adjustedHealth;
                // weakLoc = cloud;
            }
        }

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

        if (enemyOffensiveCnt == 0 || friendOffensiveCnt > enemyOffensiveCnt + 3) {
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
                    // int w = loc.distanceSquaredTo(weakLoc) * 66666 - countWithin(friends, loc, big);
                    int w = loc.distanceSquaredTo(weakLoc) * 66666 + loc.distanceSquaredTo(hq);
                    if (w < hits)
                    {
                        hits = w;
                        best = dir;
                    }
                }
                return best;
            }
        } else if (enemyOffensiveCnt > friendOffensiveCnt + 5)
        {
            // retreat
            // moveTo(tracker.getClosestHQLoc());
            if (rc.getLocation().distanceSquaredTo(weakLoc) > rc.getType().actionRadiusSquared)
            {
                return null;
            }
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
            // int big = rc.getType().visionRadiusSquared;
            // Direction best = null;
            // int hits = 123456789;
            // for (Direction dir : directions)
            // {
            //     if (!rc.canMove(dir)) continue;
            //     MapLocation loc = rc.adjacentLocation(dir);
            //     if (loc.distanceSquaredTo(weakLoc) > big) continue;
            //     int w = countWithin(enemies, loc, big) * 66666 + loc.distanceSquaredTo(weakLoc);
            //     if (w < hits)
            //     {
            //         hits = w;
            //         best = dir;
            //     }
            // }
            // return best;
            Direction dir = rc.getLocation().directionTo(weakLoc);
            Direction[] dirs = {
                dir.rotateLeft().rotateLeft(),
                dir.rotateRight().rotateRight(),
                dir.rotateLeft(),
                dir.rotateRight(),
                dir.rotateLeft().rotateLeft().rotateLeft(),
                dir.rotateRight().rotateRight().rotateRight(),
            };
            for (Direction d : dirs)
            {
                if (rc.canMove(d))
                {
                    return d;
                }
            }
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

    public void run() throws GameActionException
    {
        turnCount++;
        MapLocation ga = getAttackLoc();
        Direction bd = getChaseOrRetreatDir();

        if (bd != null)
        {
            if (ga != null)
            {
                if (rc.getLocation().distanceSquaredTo(ga) >= rc.adjacentLocation(bd).distanceSquaredTo(ga))
                {
                    rc.move(bd);
                    ga = getAttackLoc();
                    if (ga != null)
                    {
                        rc.attack(ga);
                    }
                }else{
                    rc.attack(ga);
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

        if (ga != null)
        {
            rc.attack(ga);
        }
        
        if (shouldHalt)
        {
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
                if (true || rc.getRoundNum() % 5 == 0 || rc.getRoundNum() > 30)
                {
                    moveTo(oppositeLoc);
                }
            }else{
                if (rc.getID() % 2 == 0)
                {
                    moveRandom();
                } else {
                    moveTo(oppositeLoc);
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
