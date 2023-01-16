package cleanegg;

import battlecode.common.*;

public class Launcher extends Robot
{
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
                // int adjustedHealth = (enemy.health - U * 6) * 66666 - enemy.getID();
                int adjustedHealth = (enemy.health - U * 6) * 66666 - rc.getLocation().distanceSquaredTo(toAttack);
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
        MapLocation[] cheeses = rc.senseNearbyCloudLocations();
        for (MapLocation cheese : cheeses) {
            if (rc.canAttack(cheese))
            {
                rc.attack(cheese);
                attackLoc = cheese;
                return true;
            }
        }
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
                int w = countWithin(enemies, loc, big) * 66666 + loc.distanceSquaredTo(weakLoc);
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

    static boolean crossed = false;

    public void run() throws GameActionException
    {
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
