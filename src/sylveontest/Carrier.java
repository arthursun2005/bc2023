package sylveontest;

import battlecode.common.*;
import scala.Int;

import java.util.ArrayList;
import java.util.HashSet;

public class Carrier extends Robot {
    public Carrier(RobotController rc) throws GameActionException {
        super(rc);
        heap = new Heap();
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

    public void checkDanger() throws GameActionException {
        MapLocation weakLoc = attack.getWeakLocCarrier();
        if (weakLoc != null) {
            if (rc.getWeight() >= 5) {
                if (rc.canAttack(weakLoc)) {
                    rc.attack(weakLoc);
                    greedilyMove(weakLoc, -1);
                    greedilyMove(weakLoc, -1);
                }
                greedilyMove(weakLoc, 1);
                greedilyMove(weakLoc, 1);
                if (rc.canAttack(weakLoc))
                    rc.attack(weakLoc);
            }
            if (rc.canAttack(weakLoc) && rc.getWeight() > 0) {
                rc.attack(weakLoc);
            }
            greedilyMove(weakLoc, -1);
            greedilyMove(weakLoc, -1);
        }
    }

    public void run() throws GameActionException {

//        if (rc.canMove(Direction.EAST)) rc.move(Direction.EAST);
        Direction dir = bfs.tryBFS(new MapLocation(5, 20));
        if (dir == null) return;
        if (rc.canMove(dir)) rc.move(dir);
        //        System.out.println(Clock.getBytecodesLeft());
//
//        Direction dir = bfs.tryBFS(new MapLocation(6, 22));
//        if (dir == null) return;
//        rc.setIndicatorLine(rc.getLocation(), rc.getLocation().add(dir), 255, 0, 0);
//        if (rc.canMove(dir)) rc.move(dir);
//        System.out.println(Clock.getBytecodesLeft());
//

//
//        MapLocation HQLoc = tracker.getClosestHQLoc();
//        rc.setIndicatorLine(HQLoc, rc.getLocation(), 100, 100, 255);
//        checkDanger();
//        tryTakeAnchor(HQLoc);
//        if (rc.getAnchor() != null) {
//            tryPlaceAnchor();
//            return;
//        }
//        boolean shouldReturnToHQ = false;
//        if (rc.getWeight() == GameConstants.CARRIER_CAPACITY)
//            shouldReturnToHQ = true;
//
//        MapLocation well = tracker.getBestWell();
//        rc.setIndicatorString("well: " + well);
//        if (well != null) {
//            rc.setIndicatorLine(well, rc.getLocation(), 255, 255, 200);
//        }
//
//        if (shouldReturnToHQ) {
//            moveTo(HQLoc);
//            int ada = rc.getResourceAmount(ResourceType.ADAMANTIUM), mana = rc.getResourceAmount(ResourceType.MANA),
//                    elixir = rc.getResourceAmount(ResourceType.ELIXIR);
//            if (ada > 0 && rc.canTransferResource(HQLoc, ResourceType.ADAMANTIUM, ada))
//                rc.transferResource(HQLoc, ResourceType.ADAMANTIUM, ada);
//            if (mana > 0 && rc.canTransferResource(HQLoc, ResourceType.MANA, mana))
//                rc.transferResource(HQLoc, ResourceType.MANA, mana);
//            if (elixir > 0 && rc.canTransferResource(HQLoc, ResourceType.ELIXIR, elixir))
//                rc.transferResource(HQLoc, ResourceType.ELIXIR, elixir);
//            return;
//        }
//
//        if (well != null) {
//            moveTo(well);
//            checkDanger();
//            moveTo(well);
//            tryMine(well);
//            tryMine(well);
//            return;
//        }
//        explore();
//        checkDanger();
//        explore();
    }
}