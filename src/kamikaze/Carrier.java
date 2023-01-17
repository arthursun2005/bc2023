package kamikaze;

import battlecode.common.*;

public class Carrier extends Robot
{
    public Carrier(RobotController rc) throws GameActionException {
        super(rc);
    }

    public void tryMine(MapLocation well) throws GameActionException {
        if (well == null) return;
        if (rc.canCollectResource(well, -1)) rc.collectResource(well, -1);
    }

    public void tryTakeAnchor(MapLocation HQLoc) throws GameActionException
    {
        if (rc.canTakeAnchor(HQLoc, Anchor.STANDARD)) rc.takeAnchor(HQLoc, Anchor.STANDARD);
    }

    public void tryPlaceAnchor() throws GameActionException
    {
        if ((rc.senseIsland(rc.getLocation()) != -1) && rc.senseTeamOccupyingIsland(rc.senseIsland(rc.getLocation())) != rc.getTeam() && rc.canPlaceAnchor()) {
            rc.placeAnchor();
            return;
        }

        if (!rc.isMovementReady())
            return;

        MapLocation island = tracker.getBestIsland();
        rc.setIndicatorString("island: " + island);

        if (island != null)
        {
            moveTo(island);
        }else{
            spreadOut(false);
        }
    }

    public void checkDanger() throws GameActionException
    {
    }

    public void run() throws GameActionException
    {
        MapLocation HQLoc = tracker.getClosestHQ();
        checkDanger();
        tryTakeAnchor(HQLoc);
        if (rc.getAnchor() != null)
        {
            tryPlaceAnchor();
            return;
        }
        boolean shouldReturnToHQ = false;
        if (rc.getWeight() == GameConstants.CARRIER_CAPACITY)
            shouldReturnToHQ = true;
        
        MapLocation well = tracker.getBestWell();
        rc.setIndicatorString("well: " + well);

        if (shouldReturnToHQ)
        {
            moveTo(HQLoc);
            int ada = rc.getResourceAmount(ResourceType.ADAMANTIUM), mana = rc.getResourceAmount(ResourceType.MANA), elixir = rc.getResourceAmount(ResourceType.ELIXIR);
            if (ada > 0 && rc.canTransferResource(HQLoc, ResourceType.ADAMANTIUM, ada))
                rc.transferResource(HQLoc, ResourceType.ADAMANTIUM, ada);
            if (mana > 0 && rc.canTransferResource(HQLoc, ResourceType.MANA, mana))
                rc.transferResource(HQLoc, ResourceType.MANA, mana);
            if (elixir > 0 && rc.canTransferResource(HQLoc, ResourceType.ELIXIR, elixir))
                rc.transferResource(HQLoc, ResourceType.ELIXIR, elixir);
            return;
        }

        if (well != null)
        {
            moveTo(well);
            moveTo(well);
            tryMine(well);
            return;
        }
        spreadOut(false);
        spreadOut(false);
    }
}