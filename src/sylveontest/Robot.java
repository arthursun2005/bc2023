package sylveontest;

import battlecode.common.*;

import java.util.Random;

public abstract class Robot {
    RobotController rc;
    Random rng;

    Attack attack;
    Tracker tracker;
    Movement movement;

    int creationRound;
    int turnCount;

    Heap heap;
    Direction[] directions = Direction.values();


    BFS bfs;
    MapLocation prevLocation;
    public Robot(RobotController rc) throws GameActionException {
        this.rc = rc;
        Util.rc = rc;
        attack = new Attack(rc, this);
        tracker = new Tracker(rc);
        movement = new Movement(rc);
        rng = new Random(rc.getID() + 369);
        creationRound = rc.getRoundNum();
        turnCount = 0;
        tracker.readHQLocs();
        bfs = new BFS(rc);
        prevLocation = rc.getLocation();
    }

    static int nx, ny;
    MapInfo mapInfo;
    MapLocation temp;
    public void update(Direction moved, MapLocation curLocation) throws GameActionException {

        int width = rc.getMapWidth(); int height = rc.getMapHeight();

        switch(moved) {
            case SOUTHWEST:
                nx = curLocation.x + -4;
                ny = curLocation.y + -2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -4;
                ny = curLocation.y + -1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -4;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -4;
                ny = curLocation.y + 1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -4;
                ny = curLocation.y + 2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -3;
                ny = curLocation.y + -3;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -3;
                ny = curLocation.y + -2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -2;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -2;
                ny = curLocation.y + -3;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -2;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -1;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -1;
                ny = curLocation.y + -1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -1;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + -2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + -1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 1;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 2;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                break;
            case WEST:
                nx = curLocation.x + -4;
                ny = curLocation.y + -2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -4;
                ny = curLocation.y + -1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -4;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -4;
                ny = curLocation.y + 1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -4;
                ny = curLocation.y + 2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -3;
                ny = curLocation.y + -3;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -3;
                ny = curLocation.y + 3;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -2;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -2;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -2;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -1;
                ny = curLocation.y + -1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -1;
                ny = curLocation.y + 1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + -2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + 2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                break;
            case NORTHWEST:
                nx = curLocation.x + -4;
                ny = curLocation.y + -2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -4;
                ny = curLocation.y + -1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -4;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -4;
                ny = curLocation.y + 1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -4;
                ny = curLocation.y + 2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -3;
                ny = curLocation.y + 2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -3;
                ny = curLocation.y + 3;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -2;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -2;
                ny = curLocation.y + 3;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -2;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -1;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -1;
                ny = curLocation.y + 1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -1;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + 1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + 2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 1;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 2;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                break;
            case SOUTH:
                nx = curLocation.x + -4;
                ny = curLocation.y + -2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -3;
                ny = curLocation.y + -3;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -2;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -2;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -1;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -1;
                ny = curLocation.y + -1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + -2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 1;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 1;
                ny = curLocation.y + -1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 2;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 2;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 3;
                ny = curLocation.y + -3;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + -2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                break;
            case NORTH:
                nx = curLocation.x + -4;
                ny = curLocation.y + 2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -3;
                ny = curLocation.y + 3;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -2;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -2;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -1;
                ny = curLocation.y + 1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -1;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + 2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 1;
                ny = curLocation.y + 1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 1;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 2;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 2;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 3;
                ny = curLocation.y + 3;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + 2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                break;
            case SOUTHEAST:
                nx = curLocation.x + -2;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -1;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + -2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + -1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 1;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 1;
                ny = curLocation.y + -1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 1;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 2;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 2;
                ny = curLocation.y + -3;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 2;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 3;
                ny = curLocation.y + -3;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 3;
                ny = curLocation.y + -2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + -2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + -1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + 1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + 2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                break;
            case EAST:
                nx = curLocation.x + 0;
                ny = curLocation.y + -2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + 2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 1;
                ny = curLocation.y + -1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 1;
                ny = curLocation.y + 1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 2;
                ny = curLocation.y + -4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 2;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 2;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 3;
                ny = curLocation.y + -3;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 3;
                ny = curLocation.y + 3;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + -2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + -1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + 1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + 2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                break;
            case NORTHEAST:
                nx = curLocation.x + -2;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + -1;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + 1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + 2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 0;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 1;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 1;
                ny = curLocation.y + 1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 1;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 2;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 2;
                ny = curLocation.y + 3;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 2;
                ny = curLocation.y + 4;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 3;
                ny = curLocation.y + 2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 3;
                ny = curLocation.y + 3;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + -2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + -1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + 0;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + 1;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }}}
                nx = curLocation.x + 4;
                ny = curLocation.y + 2;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    temp = new MapLocation(nx, ny);
                    if (rc.canSenseLocation(temp)) {
                        mapInfo = rc.senseMapInfo(temp);
                        if (mapInfo.isPassable()) {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + '0'));
                            if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) (1 + mapInfo.getCurrentDirection().getDirectionOrderNum() + '0'));
                        } else {
                            bfs.validLocation.setCharAt((nx % 10) * 10 + (ny % 10), (char) ('0'));
                        }
                    }
                }

                break;

        }
    }

    public void prepare() throws GameActionException {
        if (turnCount > 3 || !rc.getType().equals(RobotType.LAUNCHER))
            tracker.update();
        attack.update();
        turnCount++;

        MapLocation curLocation = rc.getLocation();

        if (rc.getID() == 11534) {
            System.out.println(curLocation + " " + prevLocation);
        }
        if (!curLocation.equals(prevLocation)) {
            if (rc.senseCloud(prevLocation) && !rc.senseCloud(curLocation)) {
                // kinda gotta redo the array
                // TODO: NOW WE DONT RUN BFS PROBABLY CUZ NOT ENOUGH BYTECODE
                bfs.redoMap();

            } else {
                Direction moved = prevLocation.directionTo(curLocation);
                update(moved, curLocation);

                System.out.println(bfs.validLocation);
            }
            prevLocation = curLocation;
        }

//        System.out.println(bruh - Clock.getBytecodesLeft());
    }

    public void moveTo(MapLocation loc) throws GameActionException {
        movement.moveTo(loc);
    }

    public void greedilyMove(MapLocation loc, int mul) throws GameActionException {
        Direction best = null;
        MapLocation HQLoc = tracker.getClosestHQLoc();
        MapLocation me = rc.getLocation();
        if (loc == null)
            loc = me;
        int dist = me.distanceSquaredTo(loc) * mul * 50 + me.distanceSquaredTo(HQLoc);
        for (Direction dir : directions) {
            if (!rc.canMove(dir))
                continue;
            int w = rc.adjacentLocation(dir).distanceSquaredTo(loc) * mul * 50
                    + rc.adjacentLocation(dir).distanceSquaredTo(HQLoc);
            if (w < dist) {
                dist = w;
                best = dir;
            }
        }
        if (best != null) {
            rc.move(best);
        }
    }

    abstract void run() throws GameActionException;

    MapLocation exploreTarget = null;

    public void explore() throws GameActionException {
        if (exploreTarget == null || rc.getLocation().distanceSquaredTo(exploreTarget) <= 18) {
            int width = rc.getMapWidth();
            int height = rc.getMapHeight();
            exploreTarget = new MapLocation(4 + rng.nextInt(width - 8), 4 + rng.nextInt(height - 8));
        }
        moveTo(exploreTarget);
    }

    public void spreadOut(boolean all) throws GameActionException {
        if (!rc.isMovementReady())
            return;
        int cnt[] = new int[9];
        Direction[] good = new Direction[9];
        RobotInfo[] robots = rc.senseNearbyRobots(-1, rc.getTeam());
        for (RobotInfo ri : robots) {
            if (all || ri.type.equals(rc.getType())) {
                Direction dir = rc.getLocation().directionTo(ri.getLocation());
                cnt[dir.ordinal()]++;
            }
        }
        int least = 696969;
        for (int i = 0; i < 8; i++) {
            least = Math.min(least, cnt[i]);
        }

        int gc = 0;
        for (int i = 0; i < 8; i++) {
            if (cnt[i] == least) {
                good[gc] = directions[i];
                gc++;
            }
        }
        for (int i = 1; i < gc; i++) {
            int j = rng.nextInt(i + 1);
            if (i != j) {
                Direction a = good[i];
                good[i] = good[j];
                good[j] = a;
            }
        }
        for (int i = 0; i < gc; i++) {
            if (rc.canMove(good[i])) {
                rc.move(good[i]);
                break;
            }
        }
    }
}
