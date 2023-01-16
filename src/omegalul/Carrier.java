package omegalul;

import battlecode.common.*;
import static omegalul.RobotPlayer.*;

public class Carrier {

    static MapLocation minLoc = null;
    static void runCarrier(RobotController rc) throws GameActionException {
        MapLocation me = rc.getLocation();
        if (rc.getAnchor() == null) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    MapLocation hqLoc = new MapLocation(me.x + dx, me.y + dy);
                    if (rc.canTakeAnchor(hqLoc, Anchor.STANDARD)) {
                        rc.setIndicatorString("Taking standard Anchor");
                        rc.takeAnchor(hqLoc, Anchor.STANDARD);
                    }
                }
            }
        }

        if (rc.getAnchor() != null) {
            if (!rc.isMovementReady()) return;

            if (minLoc != null) {
                Direction dir = Movement.tryMove(rc, minLoc);
                if (dir == null) {
                    minLoc = null;
                } else {
                    rc.move(dir);
                    return;
                }
            }
            // If I have an anchor singularly focus on getting it to the first island I see
            int[] islands = rc.senseNearbyIslands();
            int mini = 42069;
            for (int id : islands) {
                if (rc.senseAnchor(id)!=null) continue;
                MapLocation[] thisIslandLocs = rc.senseNearbyIslandLocations(id);
                for (MapLocation nLoc : thisIslandLocs) {
                    if (me.distanceSquaredTo(nLoc) < mini) {
                        mini=me.distanceSquaredTo(nLoc);
                        minLoc=nLoc;
                    }
                }
            }
            if (mini!=42069) {
                if (me.equals(minLoc)&&rc.canPlaceAnchor()) {
                    rc.setIndicatorString("Huzzah, placed anchor!");
                    rc.placeAnchor();
                    return;
                }
                Direction dir = Movement.tryMove(rc, minLoc);
                if (dir == null) {
                    minLoc = null;
                } else {
                    rc.move(dir);
                    return;
                }
            }
            else {
                if (!rc.isMovementReady()) return;
                if (moveCount==0 || !rc.canMove(curDir)) {
                    moveCount=4;
                    Direction newDirects[] = {
                            curDir,
                            curDir.rotateRight(),
                            curDir.rotateLeft(),
                            curDir.rotateRight().rotateRight(),
                            curDir.rotateLeft().rotateLeft(),
                    };
                    for (int i=0; i<20; i++) {
                        curDir = newDirects[rng.nextInt(newDirects.length)];
                        if (rc.canMove(curDir)) {
                            rc.move(curDir);
                            return;
                        }
                    }
                    while (true) {
                        curDir = directions[rng.nextInt(directions.length)];
                        if (rc.canMove(curDir)) {
                            rc.move(curDir);
                            return;
                        }
                    }
                }
                moveCount--;
                rc.move(curDir);
            }
        }
    }
}
