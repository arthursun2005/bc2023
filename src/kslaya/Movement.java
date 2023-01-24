package kslaya;

import battlecode.common.*;
import java.util.Random;

enum State {
    WALL,
    NORMAL
}

public class Movement {
    RobotController rc;
    Robot robot;
    BFS bfs;
    Movement(Robot rc) {
        this.rc = rc.rc;
        this.robot = rc;
        this.bfs = rc.bfs;
    }

    int setDir = -1;

    MapLocation[] path = new MapLocation[269];
    MapLocation prevLocation = null;
    int cur = 0;

    State currentState = State.NORMAL;
    MapLocation lastWall = null;
    boolean turningLeft = false;
    boolean canSwitch = false;

    StringBuilder invalid;

    void reset() throws GameActionException {
        rc.setIndicatorDot(rc.getLocation(),69,255,69);
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

        if (path[cur].equals(loc)) return false;

        if (rc.onTheMap(path[cur].add(Direction.NORTH)) && !rc.canSenseLocation(path[cur].add(Direction.NORTH))) return false;
        if (rc.onTheMap(path[cur].add(Direction.NORTHEAST)) && !rc.canSenseLocation(path[cur].add(Direction.NORTHEAST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.EAST)) && !rc.canSenseLocation(path[cur].add(Direction.EAST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.SOUTHEAST)) && !rc.canSenseLocation(path[cur].add(Direction.SOUTHEAST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.SOUTH)) && !rc.canSenseLocation(path[cur].add(Direction.SOUTH))) return false;
        if (rc.onTheMap(path[cur].add(Direction.SOUTHWEST)) && !rc.canSenseLocation(path[cur].add(Direction.SOUTHWEST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.WEST)) && !rc.canSenseLocation(path[cur].add(Direction.WEST))) return false;
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

            if (rc.getType()!=RobotType.CARRIER) {
                MapLocation ctmp = path[cur].add(rc.senseMapInfo(path[cur]).getCurrentDirection());
                if (!ctmp.equals(path[cur])) {
                    path[++cur] = ctmp;
                    return true;
                }
            }

            if (canGo(tmp)) {
                path[++cur] = tmp;
                return true;
            }

            if (rc.getType()==RobotType.CARRIER) {
                MapLocation ctmp = path[cur].add(rc.senseMapInfo(path[cur]).getCurrentDirection());
                if (!ctmp.equals(path[cur])) {
                    path[++cur] = ctmp;
                    return true;
                }
            }

            currentState = State.WALL;
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

    int resetCnt = 0;

    Direction nextMove = null;

    void localMove(MapLocation loc) throws GameActionException {
        if (!rc.isMovementReady()) return;
        MapLocation curLoc = rc.getLocation();
        if (!rc.senseCloud(rc.getLocation())) {
            bfs.initBFS(path, cur);
            for (int i = cur; i >= Math.max(0, cur-7); i--) {
                if (rc.getLocation().equals(path[i])) {
                    nextMove = Direction.CENTER;
                }
                if (rc.getLocation().distanceSquaredTo(path[i]) <= 15) {
                    rc.setIndicatorDot(path[i], 255, 69, 69);
                    int idx = bfs.result.indexOf("^" + ((path[i].x - curLoc.x + 3) * 7 + (path[i].y - curLoc.y + 3)));
                    if (idx != -1) {
                        int comidx = bfs.result.indexOf(":", idx);
                        System.out.println(bfs.result.charAt(comidx+1));
                        Direction firstDir = Direction.DIRECTION_ORDER[bfs.result.charAt(comidx+1) - '0'];
                        if (rc.canMove(firstDir)) {
                            rc.setIndicatorLine(path[i], rc.getLocation(), 255, 255, 69);
                            rc.setIndicatorString("Trying to go to " + path[i] + " by heading " + firstDir);
                            nextMove = Direction.DIRECTION_ORDER[bfs.result.charAt(comidx+2) - '0'];
                            rc.move(firstDir);
                            resetCnt = 0;
                            return;
                        }
                    }
                }
            }
        }
        else {
            for (int i = cur; i >= Math.max(0, cur-7); i--) {
                if (rc.getLocation().equals(path[i])) {
                    nextMove = Direction.CENTER;
                }
                if (rc.getLocation().distanceSquaredTo(path[i]) <= 2) {
                    rc.setIndicatorDot(path[i], 255, 69, 69);
                    if (rc.canMove(curLoc.directionTo(path[i]))) {
                        rc.setIndicatorLine(path[i], rc.getLocation(), 255, 255, 69);
                        rc.move(curLoc.directionTo(path[i]));
                        resetCnt = 0;
                        return;
                    }
                }
            }
        }
        resetCnt++;
        //ohno probably jammed up
        Direction bestDir = rc.getLocation().directionTo(loc);
        Direction[] directions = {bestDir,
                bestDir.rotateRight(),
                bestDir.rotateLeft(),
                bestDir.rotateLeft().rotateLeft(),
                bestDir.rotateRight().rotateRight(),
                bestDir.rotateRight().rotateRight().rotateRight(),
                bestDir.rotateLeft().rotateLeft().rotateLeft(),
                bestDir.opposite(),
        };

        for (int i = 0; i < directions.length; i++) {
            if (rc.canMove(directions[i])) {
                rc.move(directions[i]);
                break;
            }
        }
        if (resetCnt >= 3) {
            rc.setIndicatorString("bruh");
            reset();
            resetCnt = 0;
        }
    }

    int lastUpdate = -1;

    void moveTo(MapLocation loc) throws GameActionException {
        if (setDir == -1) {
            setDir = rc.getID()%2;
            turningLeft = (setDir == 1);
        }
        if (!rc.isMovementReady()) return;
        if (nextMove != null) {
            if (rc.canMove(nextMove)) rc.move(nextMove);
            return;
        }
        if (rc.getLocation().distanceSquaredTo(loc) == 0) return;
        if (rc.getLocation().distanceSquaredTo(loc) <= 2 && (rc.senseRobotAtLocation(loc) != null || !rc.sensePassability(loc))) return;
        if (lastUpdate != rc.getRoundNum()) {
            if (update(loc))
            if (update(loc))
            if (update(loc))
//            if (update(loc))
            if (update(loc));
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
        rc.setIndicatorLine(loc, rc.getLocation(), 69, 235, 255);
        //rc.setIndicatorLine(path[cur], rc.getLocation(), 235, 69, 255);
        //rc.setIndicatorString("value " + cur + " sus " + turningLeft + ":" + (setDir == 1));
    }
}
