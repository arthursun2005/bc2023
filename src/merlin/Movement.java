package merlin;

import battlecode.common.*;

enum State {
    WALL,
    NORMAL
}

class GameState {
    State state = State.NORMAL;
    MapLocation lastWall = null;
    boolean turningLeft = true;
    boolean canSwitch = false;
    StringBuilder invalid = new StringBuilder(String.format("%3690s",""));
    public GameState(GameState bruh) {
        this.state = bruh.state;
        this.lastWall = bruh.lastWall;
        this.turningLeft = bruh.turningLeft;
        this.canSwitch = bruh.canSwitch;
        this.invalid = new StringBuilder(bruh.invalid);
    }
    public GameState(boolean turningLeft) {
        this.turningLeft = turningLeft;
        return;
    }
}

public class Movement {
    RobotController rc;

    Movement(RobotController rc) {
        this.rc = rc;
    }

    MapLocation[] path = new MapLocation[369];
    GameState[] gameStates = new GameState[369];
    MapLocation prevLocation = null;
    int cur = 0;

    StringBuilder invalid;

    void reset() throws GameActionException {
        rc.setIndicatorDot(rc.getLocation(),69,255,69);
        path[0] = rc.getLocation();
        gameStates[0] = new GameState(rc.getID()%2 == 1);
        cur = 0;
    }

    boolean canGo(MapLocation tar) throws GameActionException {
        if (!rc.onTheMap(tar) || !rc.sensePassability(tar)) return false;
        if (rc.canSenseRobotAtLocation(tar) && !rc.getLocation().equals(tar)) return false;
        if (rc.senseMapInfo(tar).getCurrentDirection() == Direction.CENTER) return true;
        MapLocation actualTar = tar.add(rc.senseMapInfo(tar).getCurrentDirection());
        if (gameStates[cur].invalid.charAt(tar.x*60+tar.y)=='1') return false;
        if (path[cur].distanceSquaredTo(actualTar) > 2) return true;
        gameStates[cur].invalid.setCharAt(tar.x*60+tar.y, '1');
        return false;
    }

    boolean update(MapLocation loc) throws GameActionException {
        if (!loc.equals(prevLocation) || cur == 368) {
            reset();
        }
        prevLocation = loc;

        if (path[cur].distanceSquaredTo(loc) <= 2) return false;

        if (cur >= 5 && path[cur].equals(path[cur-4]) && path[cur-1].equals(path[cur-5])) {
            gameStates[cur].state = State.NORMAL;
        }

        if (rc.onTheMap(path[cur].add(Direction.NORTH)) && !rc.canSenseLocation(path[cur].add(Direction.NORTH))) return false;
        if (rc.onTheMap(path[cur].add(Direction.NORTHEAST)) && !rc.canSenseLocation(path[cur].add(Direction.NORTHEAST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.EAST)) && !rc.canSenseLocation(path[cur].add(Direction.EAST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.SOUTHEAST)) && !rc.canSenseLocation(path[cur].add(Direction.SOUTHEAST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.SOUTH)) && !rc.canSenseLocation(path[cur].add(Direction.SOUTH))) return false;
        if (rc.onTheMap(path[cur].add(Direction.SOUTHWEST)) && !rc.canSenseLocation(path[cur].add(Direction.SOUTHWEST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.WEST)) && !rc.canSenseLocation(path[cur].add(Direction.WEST))) return false;
        if (rc.onTheMap(path[cur].add(Direction.NORTHWEST)) && !rc.canSenseLocation(path[cur].add(Direction.NORTHWEST))) return false;

        if (gameStates[cur].state == State.WALL) {
            // rc.setIndicatorString("hmmm " + lastWall + " " + loc);
            if (path[cur].distanceSquaredTo(loc) < gameStates[cur].lastWall.distanceSquaredTo(loc)) {
                gameStates[cur].state = State.NORMAL;
            }
        }

        if (gameStates[cur].state == State.NORMAL) {
            Direction dir = path[cur].directionTo(loc);
            MapLocation best = null, tmp = null;

            tmp = path[cur].add(dir);
            if (canGo(tmp)) {
                if (rc.senseMapInfo(tmp).getCurrentDirection() != Direction.CENTER) {
                    gameStates[cur+1] = new GameState(gameStates[cur]);
                    path[++cur] = tmp;
                    return true;
                }
                best = tmp;
            }

            //TODO: use currents if next to friends

            /*tmp = path[cur].add(dir.rotateLeft());
            if (canGo(tmp)) {
                if (rc.senseMapInfo(tmp).getCurrentDirection() != Direction.CENTER) {
                    gameStates[cur+1] = new GameState(gameStates[cur]);
                    path[++cur] = tmp;
                    return true;
                }
                if (best == null) best = tmp;
            }

            tmp = path[cur].add(dir.rotateRight());
            if (canGo(tmp)) {
                if (rc.senseMapInfo(tmp).getCurrentDirection() != Direction.CENTER) {
                    gameStates[cur+1] = new GameState(gameStates[cur]);
                    path[++cur] = tmp;
                    return true;
                }
                if (best == null) best = tmp;
            }*/

            if (best != null) {
                gameStates[cur+1] = new GameState(gameStates[cur]);
                path[++cur] = best;
                return true;
            }

            gameStates[cur].state = State.WALL;
            gameStates[cur].lastWall = path[cur];
            gameStates[cur].canSwitch = true;

            Direction checkDir = dir;

            for (int i = 0; i < 8; i++) {
                tmp = path[cur].add(checkDir);

                if (!rc.onTheMap(tmp) && gameStates[cur].canSwitch) {
                    checkDir = dir;
                    gameStates[cur].turningLeft = !gameStates[cur].turningLeft;
                    gameStates[cur].canSwitch = false;
                }

                if (canGo(tmp)) {
                    gameStates[cur+1] = new GameState(gameStates[cur]);
                    path[++cur] = tmp;
                    return true;
                }

                if (gameStates[cur].turningLeft) {
                    checkDir = checkDir.rotateRight();
                } else {
                    checkDir = checkDir.rotateLeft();
                }
            }

            //yikes
            if (cur == 0) return false; //at least it shouldn't infinite loop
            gameStates[cur+1] = new GameState(gameStates[cur]);
            path[cur+1] = path[cur-1];
            cur++;
            return true;
        }

        if (gameStates[cur].state == State.WALL) {
            //should always be true
            Direction checkDir = (cur == 0 ? path[0].directionTo(loc) : path[cur-1].directionTo(path[cur]));

            if (gameStates[cur].turningLeft) {
                checkDir = checkDir.rotateLeft().rotateLeft();
            } else {
                checkDir = checkDir.rotateRight().rotateRight();
            }

            for (int i = 0; i < 8; i++) {
                MapLocation tmp = path[cur].add(checkDir);

                if (!rc.onTheMap(tmp) && gameStates[cur].canSwitch) {
                    if (gameStates[cur].turningLeft) {
                        checkDir = checkDir.rotateLeft().rotateLeft();
                    } else {
                        checkDir = checkDir.rotateRight().rotateRight();
                    }
                    //this is the right order
                    gameStates[cur].turningLeft = !gameStates[cur].turningLeft;
                    gameStates[cur].canSwitch = false;
                }

                if (canGo(tmp)) {
                    gameStates[cur+1] = new GameState(gameStates[cur]);
                    path[++cur] = tmp;
                    return true;
                }

                if (gameStates[cur].turningLeft) {
                    checkDir = checkDir.rotateRight();
                } else {
                    checkDir = checkDir.rotateLeft();
                }
            }
        }
        //yikes
        if (cur == 0) return false; //at least it shouldn't infinite loop
        gameStates[cur+1] = new GameState(gameStates[cur]);
        path[cur+1] = path[cur-1];
        cur++;
        return true;
    }

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
                if (update(loc));
                //if (update(loc));
                //if (update(loc));
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
        //rc.setIndicatorString("value " + cur + " sus " + gameStates[cur].turningLeft + " state " + gameStates[cur].state);
    }
}
