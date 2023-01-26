package cucumber;

import battlecode.common.*;

public class Carrier extends Robot {
    Symmetry symmetry;

    public Carrier(RobotController rc) throws GameActionException {
        super(rc);
        symmetry = new Symmetry(this);
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
        if (threat == null)
            threat = tracker.pls();
        if (threat != null) {
            tracker.signalDistress(threat);
            if (rc.getLocation().distanceSquaredTo(threat) > rc.getType().visionRadiusSquared)
                return false;
            // if (rc.getWeight() >= 20)
            // return true;
            if (rc.getWeight() >= 4000) {
                attack.tryAttack();
                if (rc.isActionReady()) {
                    greedilyMove(threat, 1);
                    greedilyMove(threat, 1);
                    attack.tryAttack();
                }
            }
            attack.tryAttack();
            greedilyMove(threat, -1);
            if (rc.getLocation().distanceSquaredTo(threat) <= rc.getType().visionRadiusSquared)
                greedilyMove(threat, -1);
        }
        return false;
    }

    public void run() throws GameActionException {
        MapLocation HQLoc = tracker.getClosestHQLoc();
        rc.setIndicatorLine(HQLoc, rc.getLocation(), 100, 100, 255);

        boolean shouldReturn = false;

        int aggro = rc.readSharedArray(Constants.AGGRO_CARRIERS);
        if (creationRound >= aggro && aggro != 0 && rc.getID() % 2 != 0) {
            boolean ahead = false;
            for (RobotInfo ri : attack.friends) {
                // if (ri.type.equals(RobotType.LAUNCHER) ||
                // ri.type.equals(RobotType.DESTABILIZER)) {
                if (ri.location.distanceSquaredTo(symmetry.target.loc) < rc.getLocation()
                        .distanceSquaredTo(symmetry.target.loc))
                    ahead = true;
                // }
            }
            if (ahead) {
                moveTo(symmetry.update());
                attack.tryAttack();
                tracker.tryFindSymmetry();
                return;
            }
        }

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

        if (ada >= GameConstants.CARRIER_CAPACITY / 4 + 5 && rc.getRoundNum() < 19)
            shouldReturn = true;

        // if (huhh >= 2 * GameConstants.CARRIER_CAPACITY / 4 && rc.getRoundNum() < 42)
        // shouldReturn = true;

        if (huhh >= 3 * GameConstants.CARRIER_CAPACITY / 4 && rc.getRoundNum() < 89)
            shouldReturn = true;

        MapLocation well = tracker.getBestWell(attack.getThreat());

        if (well != null) {
            tryMine(well);
            tryMine(well);
            rc.setIndicatorLine(well, rc.getLocation(), 255, 255, 200);

            if (rc.getLocation().distanceSquaredTo(well) > rc.getLocation().distanceSquaredTo(HQLoc) && huhh >= 20)
                shouldReturn = true;
        }

        boolean makeElixir = rc.readSharedArray(Constants.MAKE_ELIXIR) == 1;
        boolean wantToDump = makeElixir && rc.getID() % 3 == 0;

        rc.setIndicatorString("well: " + well + " " + makeElixir + " " + wantToDump + " " + attack.getThreat());

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
            well = tracker.getBestWell(attack.getThreat());
            tryMine(well);
            tryMine(well);
            return;
        }
        explore();
        checkDanger();
        explore();

        tracker.tryFindSymmetry();
    }
}
