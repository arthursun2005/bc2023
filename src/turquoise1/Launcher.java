package turquoise1;

import battlecode.common.*;

public class Launcher extends Robot
{
    public Launcher(RobotController rc) throws GameActionException
    {
        super(rc);
    }

    public boolean tryAttack() throws GameActionException
    {
        int radius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);
        MapLocation attackLoc = null;
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
            if (rc.getID() < theID)
            {
                moveAway(attackLoc);
            }
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
            moveTo(getClosestHQLoc());
        }else if (friendOffensiveCnt > enemyOffensiveCnt + 8) {
            // attack
            if (weakLoc != null)
            {
                moveTo(weakLoc);
            }
        }
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
                w += 123456789;
            }
            if (w > dist)
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
            return;
        }
        // tryProtect();

        MapLocation center = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
        moveTo(center);

        spreadOut(true);
    }
}
