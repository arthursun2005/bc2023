package moveit;

import battlecode.common.*;
import java.util.Random;

enum State {
    WALL,
    NORMAL
}

public class Movement {
    RobotController rc;

    Movement(RobotController rc) {
        this.rc = rc;
    }

    Random rng = null;

    MapLocation prevLocation = null;
    // int cur = 0;

    State currentState = State.NORMAL;
    MapLocation lastWall = null;
    boolean turningLeft = false;
    boolean canSwitch = false;
    MapLocation pp = null;

    Direction lastDir = null;
    MapLocation pl = null;

    void reset() throws GameActionException {
        pp = rc.getLocation();
        // cur = 0;
        currentState = State.NORMAL;
    }

    boolean doIt(MapLocation loc) throws GameActionException {
        if (loc != prevLocation) {
            reset();
        }
        prevLocation = loc;

        lastDir = pl.directionTo(pp);
        if (lastDir.equals(Direction.CENTER))
            lastDir = pp.directionTo(loc);
        // System.out.println(lastDir + " " + pl + " " + pp);
        if (pp.distanceSquaredTo(loc) <= 2)
            return false;

        for (Direction dir : Direction.values()) {
            MapLocation tmp = pp.add(dir);
            if (rc.onTheMap(tmp) && !rc.canSenseLocation(tmp))
                return false;
        }

        // System.out.println(currentState + " " + lastWall);

        if (currentState == State.WALL) {
            if (pp.distanceSquaredTo(loc) < lastWall.distanceSquaredTo(loc)) {
                currentState = State.NORMAL;
                // System.out.println("huh");
            }
        }

        if (currentState == State.NORMAL) {
            Direction dir = pp.directionTo(loc);
            MapLocation tmp = pp.add(dir);
            // MapLocation actualtmp = tmp.add(rc.senseMapInfo(tmp).getCurrentDirection());
            if (rc.canMove(dir) && rc.sensePassability(tmp)
                    && rc.senseMapInfo(tmp).getCurrentDirection() == Direction.CENTER) {
                // actualtmp.distanceSquaredTo(loc) <= tmp.distanceSquaredTo(loc)*/) { //TODO :
                // currents
                pl = pp;
                pp = tmp;
                rc.move(dir);
                return true;
            }

            // tmp = pp.add(rc.senseMapInfo(pp).getCurrentDirection());
            // if (!tmp.equals(pp)) {
            // // should hopefully cover some cases
            // pp = tmp;
            // return true;
            // }

            currentState = State.WALL;
            lastWall = pp;
            // turningLeft = (rng.nextInt(2) == 1);
            if (rc.adjacentLocation(dir.rotateLeft()).distanceSquaredTo(loc) < rc.adjacentLocation(dir.rotateRight())
                    .distanceSquaredTo(loc))
                turningLeft = false;
            else
                turningLeft = true;
            // turningLeft = true;

            canSwitch = true;

            Direction checkDir = dir;

            for (int i = 0; i < 16; i++) {
                tmp = pp.add(checkDir);

                if (!rc.onTheMap(tmp) && canSwitch) {
                    turningLeft = !turningLeft;
                    canSwitch = false;
                }

                if (rc.canMove(checkDir) && rc.onTheMap(tmp) && rc.sensePassability(tmp)
                        && rc.senseMapInfo(tmp).getCurrentDirection() == Direction.CENTER) {
                    // TODO: maybe let it go if the current is in the direction to target?
                    // path[++cur] = tmp;
                    pl = pp;
                    pp = tmp;
                    rc.move(checkDir);
                    return true;
                }

                if (turningLeft) {
                    checkDir = checkDir.rotateRight();
                } else {
                    checkDir = checkDir.rotateLeft();
                }
            }
        }

        if (currentState == State.WALL)

        {
            // should always be true
            Direction checkDir = lastDir;// pp.directionTo(loc);// (cur == 0 ? path[0].directionTo(loc) : path[cur -
                                         // 1].directionTo(pp));
            // System.out.println(checkDir);
            if (turningLeft) {
                checkDir = checkDir.rotateLeft().rotateLeft();
            } else {
                checkDir = checkDir.rotateRight().rotateRight();
            }

            for (int i = 0; i < 16; i++) {
                MapLocation tmp = pp.add(checkDir);

                if (!rc.onTheMap(tmp) && canSwitch) {
                    turningLeft = !turningLeft;
                    canSwitch = false;
                }

                if (rc.canMove(checkDir) && rc.onTheMap(tmp) && rc.sensePassability(tmp)
                        && rc.senseMapInfo(tmp).getCurrentDirection() == Direction.CENTER) {
                    // TODO: maybe let it go if the current is in the direction to target?
                    // path[++cur] = tmp;
                    rc.move(checkDir);
                    pl = pp;
                    pp = tmp;
                    return true;
                }

                if (turningLeft) {
                    checkDir = checkDir.rotateRight();
                } else {
                    checkDir = checkDir.rotateLeft();
                }
            }
        }
        // yikes
        // if (cur == 0)
        // return false; // at least it shouldn't infinite loop
        // path[++cur] = path[cur - 1];
        // return true;
        return false;
    }

    boolean tmpMove(MapLocation loc) throws GameActionException {
        Direction dir = rc.getLocation().directionTo(loc);
        if (rc.canMove(dir)) {
            rc.move(dir);
            return true;
        }
        // if (rc.canMove(dir.rotateLeft())) {
        // rc.move(dir.rotateLeft());
        // return true;
        // }
        // if (rc.canMove(dir.rotateRight())) {
        // rc.move(dir.rotateRight());
        // return true;
        // }
        return false;
    }

    void moveTo(MapLocation loc) throws GameActionException {
        if (rng == null)
            rng = new Random(rc.getID());
        if (!rc.isMovementReady())
            return;
        if (rc.getLocation().distanceSquaredTo(loc) == 0)
            return;
        if (rc.getLocation().distanceSquaredTo(loc) <= 2
                && (rc.senseRobotAtLocation(loc) != null || !rc.sensePassability(loc)))
            return;
        if (pl == null)
            pl = rc.getLocation();
        doIt(loc);
    }
}
