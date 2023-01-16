package cleanegg;

import battlecode.common.*;

public class Headquarter extends Robot
{
    static RobotType toMake = RobotType.CARRIER;

    static Tracker tracker;

    static int totalAda = 0;
    static int totalMana = 0;
    static int anchorsMade = 0;

    public Headquarter(RobotController rc) throws GameActionException {
        super(rc);

        int cnt = rc.readSharedArray(63);
        rc.writeSharedArray(63, cnt + 1);
        writeCoords(cnt, rc.getLocation());
    }

    public boolean tryMake(Direction bestDir) throws GameActionException
    {
        Direction[] dirs = getGreedyDirections(bestDir);
        for (Direction dir : dirs)
        {
            MapLocation loc = rc.getLocation().add(dir);
            if (rc.canBuildRobot(toMake, loc))
            {
                rc.buildRobot(toMake, loc);
                if (toMake.equals(RobotType.CARRIER))
                {
                    totalAda += 50;
                }else if (toMake.equals(RobotType.LAUNCHER)) {
                    totalMana += 60;
                }
                return true;
            }
        }
        return false;
    }

    public boolean tryMake(MapLocation loc) throws GameActionException
    {
        if (loc==null) return false;

        if (rc.canBuildRobot(toMake, loc))
        {
            rc.buildRobot(toMake, loc);
            if (toMake.equals(RobotType.CARRIER))
            {
                totalAda += 50;
            }else if (toMake.equals(RobotType.LAUNCHER)) {
                totalMana += 60;
            }
            return true;
        }

        return false;
    }

    public boolean enemyHQIsDangerouslyCloseLmfao() throws GameActionException
    {
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        for (RobotInfo ri : enemies)
        {
            if (ri.type.equals(RobotType.HEADQUARTERS))
            {
                return true;
            }
        }
        return false;
    }


    public MapLocation getOptimalMine() throws GameActionException {

        MapLocation ideal = tracker.getClosestMine();

        int closest = 1_000_000;
        MapLocation closestLoc = null;

        for (int dx = -3; dx <= 3; dx++ ) {
            for (int dy = -3; dy <= 3; dy++ ) {
                if (dx * dx + dy * dy > 10) continue;
                MapLocation temp = new MapLocation(rc.getLocation().x + dx, rc.getLocation().y + dy);
                if (rc.onTheMap(temp) && rc.canBuildRobot(RobotType.CARRIER, temp) && temp.distanceSquaredTo(ideal) < closest) {
                    System.out.println(closest + " " + temp + " " + ideal);
                    closest = temp.distanceSquaredTo(ideal);
                    closestLoc = temp;
                }
            }
        }

        return closestLoc;
    }

    public MapLocation getOptimalSpawn() throws GameActionException {

        MapLocation ideal = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);

        int closest = 1_000_000;
        MapLocation closestLoc = null;

        for (int dx = -3; dx <= 3; dx++ ) {
            for (int dy = -3; dy <= 3; dy++ ) {
                if (dx * dx + dy * dy > 10) continue;
                MapLocation temp = new MapLocation(rc.getLocation().x + dx, rc.getLocation().y + dy);
                if (rc.onTheMap(temp) && rc.canBuildRobot(RobotType.LAUNCHER, temp) && temp.distanceSquaredTo(ideal) < closest) {
                    System.out.println(closest + " " + temp + " " + ideal);
                    closest = temp.distanceSquaredTo(ideal);
                    closestLoc = temp;
                }
            }
        }
        return closestLoc;
    }

    public void run() throws GameActionException
    {
        Tracker.updateWells();
        MapLocation well = Tracker.getClosestMine();
        boolean made = false;

        if (totalAda - anchorsMade * 500 >= 800 && totalMana - anchorsMade * 200 >= 500)
        {
            if (rc.canBuildAnchor(Anchor.STANDARD))
            {
                rc.buildAnchor(Anchor.STANDARD);
                System.out.println("I have built some anchor UwU poggers!!!!!");
                anchorsMade += 1;
            }
            return;
        }

        int ada = rc.getResourceAmount(ResourceType.ADAMANTIUM);
        int mana = rc.getResourceAmount(ResourceType.MANA);

        if (ada - mana >= 150)
        {
            toMake = RobotType.CARRIER;
        }else if ((mana >= 75 || mana - ada >= 150) && rc.getRoundNum() >= 5)
        {
            toMake = RobotType.LAUNCHER;
        }else{
            toMake = RobotType.CARRIER;
        }

        if (rc.getRoundNum() < 3 && enemyHQIsDangerouslyCloseLmfao())
        {
            toMake = RobotType.LAUNCHER;
        }

        if (toMake.equals(RobotType.CARRIER))
        {
            MapLocation loc = getOptimalMine();
            made = tryMake(loc);
        }

        if (toMake.equals(RobotType.LAUNCHER))
        {
            MapLocation loc = getOptimalSpawn();
            made = tryMake(loc);
        }
    }
}
