package optiver;

import battlecode.common.*;
import java.util.Random;

enum State {
    WALL,
    NORMAL
}

public class Movement {
    RobotController rc;
    BFS bfs;
    Movement(Robot rc) {
        this.rc = rc.rc;
        this.bfs = rc.bfs;
    }

    Random rng = null;

    MapLocation[] path = new MapLocation[269];
    MapLocation prevLocation = null;
    int cur = 0;

    State currentState = State.NORMAL;
    MapLocation lastWall = null;
    boolean turningLeft = false;
    boolean canSwitch = false;

    StringBuilder invalid;

    void reset() throws GameActionException {
        path[0] = rc.getLocation();
        cur = 0;
        currentState = State.NORMAL;
        invalid = new StringBuilder(String.format("%3690s",""));
    }

    boolean canGo(MapLocation tar) throws GameActionException {
        if (!rc.onTheMap(tar) || !rc.sensePassability(tar)) return false;
        if (rc.senseMapInfo(tar).getCurrentDirection() == Direction.CENTER) return true;
        MapLocation actualTar = tar.add(rc.senseMapInfo(tar).getCurrentDirection());
        if (invalid.charAt(tar.x*60+tar.y)=='1') return false;
        if (path[cur].distanceSquaredTo(actualTar) > 2) return true;
        invalid.setCharAt(tar.x*60+tar.y, '1');
        return false;
    }

    boolean update(MapLocation loc) throws GameActionException {
        if (loc != prevLocation || cur == 268) {
            reset();
        }
        prevLocation = loc;

        if (path[cur].distanceSquaredTo(loc)<=2) return false;

        if (rc.onTheMap(path[cur].add(Direction.NORTHEAST)) && !rc.canSenseLocation(path[cur].add(Direction.NORTHEAST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.SOUTHEAST)) && !rc.canSenseLocation(path[cur].add(Direction.SOUTHEAST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.SOUTHWEST)) && !rc.canSenseLocation(path[cur].add(Direction.SOUTHWEST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.NORTHWEST)) && !rc.canSenseLocation(path[cur].add(Direction.NORTHWEST))) return false;

        if (currentState == State.WALL) {
            // rc.setIndicatorString("hmmm " + lastWall + " " + loc);
            if (path[cur].distanceSquaredTo(loc) < lastWall.distanceSquaredTo(loc)) {
                currentState = State.NORMAL;
            }
        }

        if (currentState == State.NORMAL) {
            Direction dir = path[cur].directionTo(loc);
            MapLocation tmp = path[cur].add(dir);
            if (canGo(tmp)) {
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

            for (int i = 0; i < 8; i++) {
                tmp = path[cur].add(checkDir);

                if (!rc.onTheMap(tmp) && canSwitch) {
                    checkDir = dir;
                    turningLeft = !turningLeft;
                    canSwitch = false;
                }

                if (canGo(tmp)) {
                    path[++cur] = tmp;
                    return true;
                }

                if (turningLeft) {
                    checkDir = checkDir.rotateRight();
                } else {
                    checkDir = checkDir.rotateLeft();
                }
            }

            //yikes
            if (cur == 0) return false; //at least it shouldn't infinite loop
            path[++cur] = path[cur-1];
            return true;
        }

        if (currentState == State.WALL) {
            //should always be true
            Direction checkDir = (cur == 0 ? path[0].directionTo(loc) : path[cur-1].directionTo(path[cur]));

            if (turningLeft) {
                checkDir = checkDir.rotateLeft().rotateLeft();
            } else {
                checkDir = checkDir.rotateRight().rotateRight();
            }

            for (int i = 0; i < 8; i++) {
                MapLocation tmp = path[cur].add(checkDir);

                if (!rc.onTheMap(tmp) && canSwitch) {
                    if (turningLeft) {
                        checkDir = checkDir.rotateLeft().rotateLeft();
                    } else {
                        checkDir = checkDir.rotateRight().rotateRight();
                    }
                    //this is the right order
                    turningLeft = !turningLeft;
                    canSwitch = false;
                }

                if (canGo(tmp)) {
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

    void localMove(MapLocation loc) throws GameActionException {
        if (!rc.isMovementReady()) return;
        bfs.initBFS(path, cur);
        boolean toReset = true;
        if (!rc.senseCloud(rc.getLocation())) {
            for (int i = cur; i >= Math.max(0, cur - 5); i--) {
                if (rc.getLocation().distanceSquaredTo(path[cur]) > 15) continue;
                toReset = false;
                Direction tmp = bfs.bfs(path[cur]);
                if (tmp != null && rc.canMove(tmp)) {
                    rc.move(tmp);
                    return;
                }
            }
        }
        else toReset = false;
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

    int lastUpdate = -1;

    void moveTo(MapLocation loc) throws GameActionException {
        if (rng == null) rng = new Random(rc.getID());
        if (!rc.isMovementReady()) return;
        if (rc.getLocation().distanceSquaredTo(loc) == 0) return;
        if (rc.getLocation().distanceSquaredTo(loc) <= 2 && (rc.senseRobotAtLocation(loc) != null || !rc.sensePassability(loc))) return;
        if (lastUpdate != rc.getRoundNum()) {
            update(loc);
            update(loc);
            update(loc);
            lastUpdate = rc.getRoundNum();
        }
        /*for (int i = 0; i < 3; i++) {
            int start = Clock.getBytecodesLeft();
            if (!update(loc)) break;
            rc.setIndicatorLine(path[Math.max(cur-1,0)], path[cur], 69, 235, 255);
            System.out.println("Value : " + (start - Clock.getBytecodesLeft()));
        }*/
        /*for (int i = 0; i + 1 <= cur; i++) {
            rc.setIndicatorLine(path[i], path[i+1], 225, 235, 255);
        }*/
        localMove(loc);
    }
}
