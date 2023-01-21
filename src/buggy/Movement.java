package buggy;

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

    MapLocation[] path = new MapLocation[269];
    MapLocation prevLocation = null;
    int cur = 0;

    State currentState = State.NORMAL;
    MapLocation lastWall = null;
    boolean turningLeft = false;
    boolean canSwitch = false;

    void reset() throws GameActionException {
        path[0] = rc.getLocation();
        cur = 0;
        currentState = State.NORMAL;
    }

    boolean update(MapLocation loc) throws GameActionException {
        if (loc != prevLocation || cur == 268) {
            reset();
        }
        prevLocation = loc;

        if (path[cur].distanceSquaredTo(loc)<=2) return false;

        for (Direction dir : Direction.values()) {
            MapLocation tmp = path[cur].add(dir);
            if (rc.onTheMap(tmp) && !rc.canSenseLocation(tmp)) return false;
        }

        if (currentState == State.WALL) {
            rc.setIndicatorString("hmmm " + lastWall + " " + loc);
            if (path[cur].distanceSquaredTo(loc) < lastWall.distanceSquaredTo(loc)) {
                currentState = State.NORMAL;
            }
        }

        if (currentState == State.NORMAL) {
            Direction dir = path[cur].directionTo(loc);
            MapLocation tmp = path[cur].add(dir);
            //MapLocation actualtmp = tmp.add(rc.senseMapInfo(tmp).getCurrentDirection());
            if (rc.sensePassability(tmp) && rc.senseMapInfo(tmp).getCurrentDirection() == Direction.CENTER/*actualtmp.distanceSquaredTo(loc) <= tmp.distanceSquaredTo(loc)*/) { //TODO : currents
                path[++cur] = tmp;
                return true;
            }

            tmp = path[cur].add(rc.senseMapInfo(path[cur]).getCurrentDirection());
            if (!tmp.equals(path[cur])) {
                //should hopefully cover some cases
                path[++cur] = tmp;
                return true;
            }

            currentState = State.WALL;
            lastWall = path[cur];
            turningLeft = (rng.nextInt(2) == 1);
            canSwitch = true;

            Direction checkDir = dir;

            for (int i = 0; i < 16; i++) {
                tmp = path[cur].add(checkDir);

                if (!rc.onTheMap(tmp) && canSwitch) {
                    turningLeft = !turningLeft;
                    canSwitch = false;
                }

                if (rc.onTheMap(tmp) && rc.sensePassability(tmp) && rc.senseMapInfo(tmp).getCurrentDirection() == Direction.CENTER) {
                    //TODO: maybe let it go if the current is in the direction to target?
                    path[++cur] = tmp;
                    return true;
                }

                if (turningLeft) {
                    checkDir = checkDir.rotateRight();
                } else {
                    checkDir = checkDir.rotateLeft();
                }
            }
        }

        if (currentState == State.WALL) {
            //should always be true
            Direction checkDir = (cur == 0 ? path[0].directionTo(loc) : path[cur-1].directionTo(path[cur]));

            if (turningLeft) {
                checkDir = checkDir.rotateLeft().rotateLeft();
            } else {
                checkDir = checkDir.rotateRight().rotateRight();
            }

            for (int i = 0; i < 16; i++) {
                MapLocation tmp = path[cur].add(checkDir);

                if (!rc.onTheMap(tmp) && canSwitch) {
                    turningLeft = !turningLeft;
                    canSwitch = false;
                }

                if (rc.onTheMap(tmp) && rc.sensePassability(tmp) && rc.senseMapInfo(tmp).getCurrentDirection() == Direction.CENTER) {
                    //TODO: maybe let it go if the current is in the direction to target?
                    path[++cur] = tmp;
                    return true;
                }

                if (turningLeft) {
                    checkDir = checkDir.rotateRight();
                } else {
                    checkDir = checkDir.rotateLeft();
                }
            }
        }
        //yikes
        if (cur == 0) return false; //at least it shouldn't infinite loop
        path[++cur] = path[cur-1];
        return true;
    }

    boolean tmpMove(MapLocation loc) throws GameActionException {
        Direction dir = rc.getLocation().directionTo(loc);
        if (rc.canMove(dir)) {
            rc.move(dir);
            return true;
        }
        if (rc.canMove(dir.rotateLeft())) {
            rc.move(dir.rotateLeft());
            return true;
        }
        if (rc.canMove(dir.rotateRight())) {
            rc.move(dir.rotateRight());
            return true;
        }
        return false;
    }

    void localMove(MapLocation loc) throws GameActionException {
        if (!rc.isMovementReady()) return;
        boolean toReset = true;
        for (int i = cur; i >= Math.max(0, cur - 7); i--) {
            if (tmpMove(path[cur])) return;
            if (rc.canSenseLocation(path[cur])) toReset = false;
        }

        //ohno probably jammed up
        Direction bestDir = rc.getLocation().directionTo(loc);
        Direction[] directions = {bestDir,
                bestDir.rotateRight(),
                bestDir.rotateLeft(),
                bestDir.rotateLeft().rotateLeft(),
                bestDir.rotateRight().rotateRight(),
                bestDir.rotateRight().rotateRight().rotateRight(),
                bestDir.rotateLeft().rotateLeft().rotateLeft(),
                Direction.CENTER
        };

        for (int i = 0; i < directions.length; i++) {
            if (rc.canMove(directions[i])) {
                rc.move(directions[i]);
                break;
            }
        }
        if (toReset) reset();
    }

    void moveTo(MapLocation loc) throws GameActionException {
        if (rng == null) rng = new Random(rc.getID());
        if (!rc.isMovementReady()) return;
        if (rc.getLocation().distanceSquaredTo(loc) == 0) return;
        if (rc.getLocation().distanceSquaredTo(loc) <= 2 && (rc.senseRobotAtLocation(loc) != null || !rc.sensePassability(loc))) return;
        for (int i = 0; i < 4; i++) {
            if (!update(loc)) break;
            rc.setIndicatorLine(path[Math.max(cur-1,0)], path[cur], 69, 235, 255);
        }
        /*for (int i = 0; i + 1 <= cur; i++) {
            rc.setIndicatorLine(path[i], path[i+1], 225, 235, 255);
        }*/
        localMove(loc);
    }
}
