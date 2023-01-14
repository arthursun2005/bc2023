package daddy;

import battlecode.common.*;

public class Launcher extends Robot
{
    public Launcher(RobotController rc) throws GameActionException
    {
        super(rc);
    }

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
        MapLocation attackLoc = null;
        int minHealth = 123456789;
        for (RobotInfo enemy : enemies) {
            MapLocation toAttack = enemy.location;
            if (rc.canAttack(toAttack))
            {
                int U = countWithin(friends, toAttack, rc.getType().visionRadiusSquared);
                // int adjustedHealth = -U * 66666 + enemy.health;
                // int adjustedHealth = enemy.getHealth() * 66666 - enemy.getID();
                int adjustedHealth = (enemy.health - U * 6) * 66666 - enemy.getID();
                if (enemy.type.equals(RobotType.LAUNCHER)) adjustedHealth -= 123456789;
                if (adjustedHealth < minHealth)
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
        int radius = rc.getType().actionRadiusSquared;
        RobotInfo[] allFriends = rc.senseNearbyRobots(-1, rc.getTeam());
        RobotInfo[] friends = rc.senseNearbyRobots(3, rc.getTeam());
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, opponent);
        int friendOffensiveCnt = (5 + rc.getHealth()) / 6;
        int enemyOffensiveCnt = 0;
        MapLocation weakLoc = null;
        int minHealth = 123456789;
        for (RobotInfo friend : friends)
        {
            if (friend.type.equals(RobotType.LAUNCHER))
            {
                friendOffensiveCnt += (5 + friend.health) / 6;
            }
        }
        for (RobotInfo enemy : enemies)
        {
            if (enemy.type.equals(RobotType.HEADQUARTERS)) continue;
            if (enemy.type.equals(RobotType.LAUNCHER))
            {
                enemyOffensiveCnt += (5 + enemy.health) / 6;
            }
            if (rc.getID() > enemy.getID())
            {
                enemyOffensiveCnt++;
            }else{
                friendOffensiveCnt++;
            }

            // int adjustedHealth = -countWithin(allFriends, enemy.location, radius) * 66666 + enemy.health;
            int adjustedHealth = enemy.getHealth() * 66666 - enemy.getID();
            if (enemy.type.equals(RobotType.LAUNCHER)) adjustedHealth -= 123456789;
            if (adjustedHealth < minHealth)
            {
                minHealth = adjustedHealth;
                weakLoc = enemy.location;
            }
        }

        if (enemyOffensiveCnt > friendOffensiveCnt + 1)
        {
            // retreat
            moveTo(tracker.getClosestHQLoc());
        }else if (friendOffensiveCnt > enemyOffensiveCnt + 1) {
            // attack
            if (weakLoc != null)
            {
                moveTo(weakLoc);
            }
        }else if (weakLoc != null) {
            // rotate
            int big = rc.getType().visionRadiusSquared;
            Direction best = null;
            int hits = 123456789;
            for (Direction dir : directions)
            {
                if (!rc.canMove(dir)) continue;
                MapLocation loc = rc.adjacentLocation(dir);
                if (loc.distanceSquaredTo(weakLoc) > radius) continue;
                int w = countWithin(enemies, loc, big) * 6666 + loc.distanceSquaredTo(weakLoc);
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

    public void run() throws GameActionException
    {
        tryAttack();
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
                if (rc.getRoundNum() % 5 == 0 || rc.getRoundNum() >= -1)
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
