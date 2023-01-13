package umbreon;

import battlecode.common.*;

public class Launcher extends Robot
{
    public Launcher(RobotController rc) throws GameActionException
    {
        super(rc);
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

        if (enemyOffensiveCnt > friendOffensiveCnt + 10)
        {
            // retreat
            moveTo(getClosestHQLoc());
        }else if (friendOffensiveCnt > enemyOffensiveCnt + 20) {
            // attack
            if (weakLoc != null)
            {
                moveTo(weakLoc);
            }
        }

        if (enemies.length == 1 && enemies[0].type.equals(RobotType.HEADQUARTERS) && rc.getID() % 5 != 0) return false;
        return enemies.length > 0;
    }

    public void tryProtect() throws GameActionException
    {
        RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());
        MapLocation HQLoc = getClosestHQLoc();
        int dist = rc.getLocation().distanceSquaredTo(HQLoc);
        MapLocation weakLoc = null;
        for (RobotInfo friend : friends)
        {
            int w = friend.getLocation().distanceSquaredTo(HQLoc);
            if (friend.type.equals(RobotType.CARRIER))
            {
                w -= 123456789;
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

    public void run() throws GameActionException
    {
        tryAttack();
        if (tryChaseOrRetreat())
        {
            tryAttack();
            return;
        }

        MapLocation HQLoc = getClosestHQLoc();

        RobotInfo[] friends = rc.senseNearbyRobots(8, rc.getTeam());
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

        if (mini < rc.getID() && lowerCount < 9) {
            if (rc.getLocation().distanceSquaredTo(bestie) > 2)
            {
                moveTo(bestie);
            }
        }
        else{

            if (turnCount < 20)
            {
                if (rc.getRoundNum() % 5 == 0)
                {
                    MapLocation center = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
                    moveTo(center);
                }
            }else{
                moveRandom();
            }

            /*
            if (true)//rc.getID() % 3 == 0)
            {
                moveRandom();
                // spreadOut(false);
            }else{
                MapLocation center = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
                moveTo(center);
            }
            */
        }
        // tryProtect();

        /*
        MapLocation center = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
        moveTo(center);

        spreadOut(true);
        */
        tryAttack();
    }
}
