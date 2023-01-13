package umbreon;

import battlecode.common.*;

public class Carrier extends Robot
{
    static Tracker tracker;
    static int NEAR_HQ_DIST = 15;
    static int PRETTY_NEAR_HQ_DIST = 35;

    public Carrier(RobotController rc) throws GameActionException
    {
        super(rc);
    }

    public void tryMine(MapLocation well) throws GameActionException
    {
        if (rc.canCollectResource(well, -1))
        {
            rc.collectResource(well, -1);
        }
    }

    public void tryTakeAnchor(MapLocation HQLoc) throws GameActionException
    {
        if (rc.canTakeAnchor(HQLoc, Anchor.STANDARD))
        {
            System.out.println("Taking standard Anchor UwU");
            rc.takeAnchor(HQLoc, Anchor.STANDARD);
        }
    }

    public void IWantToPlaceMyAnchor() throws GameActionException
    {
        if ((rc.senseIsland(rc.getLocation()) != -1) && rc.senseTeamOccupyingIsland(rc.senseIsland(rc.getLocation())) != rc.getTeam() && rc.canPlaceAnchor()) {
            System.out.println("Lessgo I placed my favourite anchor!!!");
            rc.placeAnchor();
            return;
        }

        if (!rc.isMovementReady())
            return;

        MapLocation island = tracker.getOptimalIsland();

        if (island != null)
        {
            moveTo(island);
        }else{
            spreadOut(false);
        }
    }

    public void runAwayFromDanger(MapLocation HQLoc) throws GameActionException
    {
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        int dist = -1;
        MapLocation danger = null;
        for (RobotInfo ri : enemies)
        {
            if (ri.type.equals(RobotType.LAUNCHER))
            {
                int w = ri.getLocation().distanceSquaredTo(rc.getLocation());
                if (dist == -1 || w < dist)
                {
                    dist = w;
                    danger = ri.getLocation();
                }
            }
        }
        if (danger != null)
        {
            moveTo(HQLoc);
        }
    }

    public void run() throws GameActionException
    {
        tracker.updateWells(rc);
        tracker.updateIslands(rc);
        MapLocation HQLoc = getClosestHQLoc();
        runAwayFromDanger(HQLoc);
        tryTakeAnchor(HQLoc);
        if (rc.getAnchor() != null)
        {
            IWantToPlaceMyAnchor();
            return;
        }
        boolean shouldReturnToHQ = false;
        if (rc.getWeight() == GameConstants.CARRIER_CAPACITY)
        {
            shouldReturnToHQ = true;
        }
        if (rc.getWeight() >= 8 * GameConstants.CARRIER_CAPACITY / 11 && rc.getLocation().distanceSquaredTo(HQLoc) <= PRETTY_NEAR_HQ_DIST)
        {
            shouldReturnToHQ = true;
        }
        if (rc.getWeight() >= GameConstants.CARRIER_CAPACITY / 3 && rc.getLocation().distanceSquaredTo(HQLoc) <= NEAR_HQ_DIST)
        {
            shouldReturnToHQ = true;
        }
        if (shouldReturnToHQ)
        {
            int ada = rc.getResourceAmount(ResourceType.ADAMANTIUM), mana = rc.getResourceAmount(ResourceType.MANA), elixir = rc.getResourceAmount(ResourceType.ELIXIR);
            if (ada > 0 && rc.canTransferResource(HQLoc, ResourceType.ADAMANTIUM, ada))
            {
                rc.transferResource(HQLoc, ResourceType.ADAMANTIUM, ada);
            }
            if (mana > 0 && rc.canTransferResource(HQLoc, ResourceType.MANA, mana))
            {
                rc.transferResource(HQLoc, ResourceType.MANA, mana);
            }
            if (elixir > 0 && rc.canTransferResource(HQLoc, ResourceType.ELIXIR, elixir))
            {
                rc.transferResource(HQLoc, ResourceType.ELIXIR, elixir);
            }
            moveTo(HQLoc);
            return;
        }
        MapLocation well = tracker.getOptimalWell();
        if (well != null)
        {
            tryMine(well);
            moveTo(well);
            tryMine(well);
            return;
        }
        spreadOut(false);
    }
}
