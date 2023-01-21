package fluorine;

import battlecode.common.*;

public class Carrier extends Robot {
    public Carrier(RobotController rc) throws GameActionException {
        super(rc);
    }

    public void tryMine(MapLocation well) throws GameActionException {
        if (well == null)
            return;
        if (rc.canCollectResource(well, -1))
            rc.collectResource(well, -1);
    }

    public void tryTakeAnchor(MapLocation HQLoc) throws GameActionException {
        if (rc.canTakeAnchor(HQLoc, Anchor.STANDARD))
            rc.takeAnchor(HQLoc, Anchor.STANDARD);
    }

    public void tryPlaceAnchor() throws GameActionException {
        if ((rc.senseIsland(rc.getLocation()) != -1)
                && rc.senseTeamOccupyingIsland(rc.senseIsland(rc.getLocation())) != rc.getTeam()
                && rc.canPlaceAnchor()) {
            rc.placeAnchor();
            return;
        }

        if (!rc.isMovementReady())
            return;

        MapLocation island = tracker.getBestIsland();
        if (island != null) {
            rc.setIndicatorLine(island, rc.getLocation(), 255, 100, 0);
        }
        rc.setIndicatorString("island: " + island);

        if (island != null) {
            moveTo(island);
        } else {
            explore();
        }

        if ((rc.senseIsland(rc.getLocation()) != -1)
                && rc.senseTeamOccupyingIsland(rc.senseIsland(rc.getLocation())) != rc.getTeam()
                && rc.canPlaceAnchor()) {
            rc.placeAnchor();
        }
    }

    public boolean checkDanger() throws GameActionException {
        MapLocation threat = attack.getThreat();
        if (threat != null) {
            tracker.signalDistress(threat);
            if (rc.getWeight() < 20)
                return true;
            if (rc.getWeight() >= 1) {
                attack.tryAttack();
                if (rc.isActionReady()) {
                    greedilyMove(threat, 1);
                    greedilyMove(threat, 1);
                    attack.tryAttack();
                }
            }
            greedilyMove(threat, -1);
            greedilyMove(threat, -1);
        }
        return false;
    }

    public void run() throws GameActionException {
        MapLocation HQLoc = tracker.getClosestHQLoc();
        rc.setIndicatorLine(HQLoc, rc.getLocation(), 100, 100, 255);

        boolean shouldReturn = false;

        shouldReturn = checkDanger();
        tryTakeAnchor(HQLoc);

        if (rc.getAnchor() != null) {
            tryPlaceAnchor();
            return;
        }

        int ada = rc.getResourceAmount(ResourceType.ADAMANTIUM), mana = rc.getResourceAmount(ResourceType.MANA),
                elixir = rc.getResourceAmount(ResourceType.ELIXIR);

        if (rc.getWeight() == GameConstants.CARRIER_CAPACITY)
            shouldReturn = true;

        int huhh = rc.getWeight();

        if (ada >= GameConstants.CARRIER_CAPACITY / 4 && rc.getRoundNum() < 19)
            shouldReturn = true;

        // if (huhh >= 2 * GameConstants.CARRIER_CAPACITY / 4 && rc.getRoundNum() < 42)
        // shouldReturn = true;

        if (huhh >= 3 * GameConstants.CARRIER_CAPACITY / 4 && rc.getRoundNum() < 89)
            shouldReturn = true;

        MapLocation well = tracker.getBestWell();

        if (well != null) {
            rc.setIndicatorLine(well, rc.getLocation(), 255, 255, 200);
        }

        boolean makeElixir = rc.readSharedArray(Constants.MAKE_ELIXIR) == 1;
        boolean wantToDump = makeElixir && rc.getID() % 3 == 0;

        rc.setIndicatorString("well: " + well + " " + makeElixir + " " + wantToDump);

        if (shouldReturn) {
            if (wantToDump) {
                MapLocation adaWell = tracker.getBestAdaWell();
                if (adaWell == null)
                    wantToDump = false;
                else
                    well = adaWell;
            }
            if (wantToDump) {
                moveTo(well);
                // if (ada > 0 && rc.canTransferResource(HQLoc, ResourceType.ADAMANTIUM, ada))
                // rc.transferResource(HQLoc, ResourceType.ADAMANTIUM, ada);
                if (mana > 0 && rc.canTransferResource(well, ResourceType.MANA, mana))
                    rc.transferResource(well, ResourceType.MANA, mana);
                // if (elixir > 0 && rc.canTransferResource(HQLoc, ResourceType.ELIXIR, elixir))
                // rc.transferResource(HQLoc, ResourceType.ELIXIR, elixir);
            } else {
                moveTo(HQLoc);
                if (ada > 0 && rc.canTransferResource(HQLoc, ResourceType.ADAMANTIUM, ada))
                    rc.transferResource(HQLoc, ResourceType.ADAMANTIUM, ada);
                if (mana > 0 && rc.canTransferResource(HQLoc, ResourceType.MANA, mana))
                    rc.transferResource(HQLoc, ResourceType.MANA, mana);
                if (elixir > 0 && rc.canTransferResource(HQLoc, ResourceType.ELIXIR, elixir))
                    rc.transferResource(HQLoc, ResourceType.ELIXIR, elixir);
            }
            tracker.bestWell = null;
            return;
        }

        if (well != null) {
            if (wantToDump) {
                MapLocation manaWell = tracker.getBestManaWell();
                if (manaWell != null)
                    well = manaWell;
            }
            moveTo(well);
            checkDanger();
            moveTo(well);
            tryMine(well);
            tryMine(well);
            return;
        }
        explore();
        checkDanger();
        explore();
    }
}
