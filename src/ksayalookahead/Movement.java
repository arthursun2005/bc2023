package ksayalookahead;

import battlecode.common.*;

enum State {
    WALL,
    NORMAL
}

public class Movement {
    RobotController rc;

    Movement(RobotController rc) {
        this.rc = rc;
    }

    int setDir = -1;

    MapLocation[] path = new MapLocation[269];
    State[] state = new State[269];
    MapLocation prevLocation = null;
    int cur = 0;

    MapLocation lastWall = null;
    boolean turningLeft = false;
    boolean canSwitch = false;

    StringBuilder invalid;

    void reset() throws GameActionException {
        rc.setIndicatorDot(rc.getLocation(),69,255,69);
        path[0] = rc.getLocation();
        state[0] = State.NORMAL;
        cur = 0;
        invalid = new StringBuilder(String.format("%3690s",""));
    }

    boolean canGo(MapLocation tar) throws GameActionException {
        if (!rc.onTheMap(tar) || !rc.sensePassability(tar)) return false;
        if (rc.canSenseRobotAtLocation(tar)) return false;
        if (rc.senseMapInfo(tar).getCurrentDirection() == Direction.CENTER) return true;
        MapLocation actualTar = tar.add(rc.senseMapInfo(tar).getCurrentDirection());
        if (invalid.charAt(tar.x*60+tar.y)=='1') return false;
        if (path[cur].distanceSquaredTo(actualTar) > 2) return true;
        invalid.setCharAt(tar.x*60+tar.y, '1');
        return false;
    }

    boolean update(MapLocation loc) throws GameActionException {
        if (loc != prevLocation) {
            reset();
        }
        if (cur == 268) {
            System.out.println("poggers UwU it happened.");
            path[0]=path[267];
            state[0]=state[267];
            path[1]=path[268];
            state[1]=state[268];
            cur = 1;
        }
        prevLocation = loc;

        if (path[cur].distanceSquaredTo(loc) <= 2) return false;

        if (rc.onTheMap(path[cur].add(Direction.NORTH)) && !rc.canSenseLocation(path[cur].add(Direction.NORTH))) return false;
        if (rc.onTheMap(path[cur].add(Direction.NORTHEAST)) && !rc.canSenseLocation(path[cur].add(Direction.NORTHEAST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.EAST)) && !rc.canSenseLocation(path[cur].add(Direction.EAST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.SOUTHEAST)) && !rc.canSenseLocation(path[cur].add(Direction.SOUTHEAST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.SOUTH)) && !rc.canSenseLocation(path[cur].add(Direction.SOUTH))) return false;
        if (rc.onTheMap(path[cur].add(Direction.SOUTHWEST)) && !rc.canSenseLocation(path[cur].add(Direction.SOUTHWEST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.WEST)) && !rc.canSenseLocation(path[cur].add(Direction.WEST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.NORTHWEST)) && !rc.canSenseLocation(path[cur].add(Direction.NORTHWEST))) return false;

        if (state[cur] == State.WALL) {
            // rc.setIndicatorString("hmmm " + lastWall + " " + loc);
            if (path[cur].distanceSquaredTo(loc) < lastWall.distanceSquaredTo(loc)) {
                state[cur] = State.NORMAL;
            }
        }

        if (state[cur] == State.NORMAL) {
            Direction dir = path[cur].directionTo(loc);
            MapLocation best = null, tmp = null;

            tmp = path[cur].add(dir);
            if (canGo(tmp)) {
                if (rc.senseMapInfo(tmp).getCurrentDirection() != Direction.CENTER) {
                    state[cur+1] = state[cur];
                    path[++cur] = tmp;
                    return true;
                }
                best = tmp;
            }

            tmp = path[cur].add(dir.rotateLeft());
            if (canGo(tmp)) {
                if (rc.senseMapInfo(tmp).getCurrentDirection() != Direction.CENTER) {
                    state[cur+1] = state[cur];
                    path[++cur] = tmp;
                    return true;
                }
                if (best == null) best = tmp;
            }

            tmp = path[cur].add(dir.rotateRight());
            if (canGo(tmp)) {
                if (rc.senseMapInfo(tmp).getCurrentDirection() != Direction.CENTER) {
                    state[cur+1] = state[cur];
                    path[++cur] = tmp;
                    return true;
                }
                if (best == null) best = tmp;
            }

            if (best != null) {
                state[cur+1] = state[cur];
                path[++cur] = best;
                return true;
            }

            state[cur] = State.WALL;
            lastWall = path[cur];
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
                    state[cur+1] = state[cur];
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
            state[cur+1] = state[cur];
            path[++cur] = path[cur-1];
            return true;
        }

        if (state[cur] == State.WALL) {
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
                    state[cur+1] = state[cur];
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
        state[cur+1] = state[cur];
        path[++cur] = path[cur-1];
        return true;
    }

    int resetCnt = 0;

    void tryMove() throws GameActionException {
        if (!rc.isMovementReady()) return;
        while (cur >= 0) {
            if (rc.getLocation().distanceSquaredTo(path[cur]) <= 2) {
                if (rc.canMove(rc.getLocation().directionTo(path[cur]))) {
                    rc.move(rc.getLocation().directionTo(path[cur]));
                }
                return;
            }
            cur--;
        }
        reset();
    }

    int lastUpdate = -1;

    void moveTo(MapLocation loc) throws GameActionException {
        if (cur < 0) reset();
        if (setDir == -1) {
            setDir = rc.getID()%2;
            turningLeft = true;//(setDir == 1);
        }
        if (!rc.isMovementReady()) return;
        if (rc.getLocation().distanceSquaredTo(loc) == 0) return;
        if (rc.getLocation().distanceSquaredTo(loc) <= 2) {
            if (rc.canMove(rc.getLocation().directionTo(loc))) {
                rc.move(rc.getLocation().directionTo(loc));
            }
            return;
        }
        if (lastUpdate != rc.getRoundNum()) {
            if (lastUpdate == -1) {
                if (update(loc))
                if (update(loc));
            }
            else {
                if (update(loc))
                if (update(loc))
                if (update(loc))
                if (update(loc))
                if (update(loc))
                if (update(loc))
                if (update(loc));
            }
            lastUpdate = rc.getRoundNum();
        }
        /*for (int i = 0; i < 3; i++) {
            int start = Clock.getBytecodesLeft();
            if (!update(loc)) break;
            rc.setIndicatorLine(path[Math.max(cur-1,0)], path[cur], 69, 235, 255);
            System.out.println("Value : " + (start - Clock.getBytecodesLeft()));
        }*/
        /*for (int i = Math.max(0,cur-7); i + 1 <= cur; i++) {
            rc.setIndicatorLine(path[i], path[i+1], 225, 235, 255);
        }*/
        tryMove();
        rc.setIndicatorLine(loc, rc.getLocation(), 69, 235, 255);
        //rc.setIndicatorLine(path[cur], rc.getLocation(), 235, 69, 255);
        //rc.setIndicatorString("value " + cur + " sus " + turningLeft + ":" + (setDir == 1));
    }
}
