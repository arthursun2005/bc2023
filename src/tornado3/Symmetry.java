package tornado3;

import battlecode.common.*;
import java.util.*;

class possiLoc {
    MapLocation loc;
    int val, offset;

    possiLoc(MapLocation loc, int val, int offset) {
        this.loc = loc;
        this.val = val;
        this.offset = offset;
    }
}

public class Symmetry {
    MapLocation parentLoc;
    possiLoc target;

    RobotController rc;
    Tracker tracker;

    ArrayList<possiLoc> enemyLocs = new ArrayList<>();

    void put(int loc, int val) {
        tracker.put(loc, val);
    }

    int read(int loc) {
        return tracker.read(loc);
    }

    int encode(int x, int y) {
        return x * 60 + y;
    }

    int uuu;

    public Symmetry(Robot robot) throws GameActionException {
        this.rc = robot.rc;
        this.tracker = robot.tracker;
        int width = rc.getMapWidth();
        int height = rc.getMapHeight();

        parentLoc = tracker.getClosestHQLoc();
        MapLocation oppositeLoc = new MapLocation(width - parentLoc.x - 1,
                height - parentLoc.y - 1);
        uuu = 16;// robot.rng.nextInt(20) == 0 ? 16 : 6900;
        target = new possiLoc(oppositeLoc, 3, uuu);
    }

    MapLocation update() throws GameActionException {
        if (enemyLocs.size() == 0) {
            for (MapLocation hqLoc : tracker.HQLocs) {
                if (hqLoc == null)
                    continue;
                MapLocation oppositeLoc = new MapLocation(rc.getMapWidth() - hqLoc.x - 1,
                        rc.getMapHeight() - hqLoc.y - 1);
                if (tracker.possi[1] == 1)
                    enemyLocs.add(new possiLoc(new MapLocation(oppositeLoc.x, hqLoc.y), 1, 6900));
                if (tracker.possi[2] == 1)
                    enemyLocs.add(new possiLoc(new MapLocation(hqLoc.x, oppositeLoc.y), 2, 6900));
                if (tracker.possi[3] == 1)
                    enemyLocs.add(new possiLoc(oppositeLoc, 3, (hqLoc == parentLoc ? uuu : 6900)));
            }
            Collections.sort(enemyLocs, new Comparator<possiLoc>() {
                public int compare(possiLoc a, possiLoc b) {
                    return (Math.min(rc.getLocation().distanceSquaredTo(b.loc), b.offset)
                        - Math.min(rc.getLocation().distanceSquaredTo(a.loc), a.offset));
                }
            });
        }

        while (enemyLocs.size() > 0) {
            target = enemyLocs.get(enemyLocs.size() - 1);
            if (rc.canSenseLocation(target.loc)) {
                MapLocation loc = target.loc;

                int x = loc.x, y = loc.y;
                int oppx = rc.getMapWidth() - x - 1, oppy = rc.getMapHeight() - y - 1;

                if (read(encode(x, y)) == 0) {

                    RobotInfo possibleHQ = rc.senseRobotAtLocation(loc); // 25
                    MapInfo mapInfo = rc.senseMapInfo(loc); // 10

                    if (possibleHQ != null && possibleHQ.getType().equals(RobotType.HEADQUARTERS)) {
                        put(encode(x, y), (possibleHQ.getTeam() == rc.getTeam() ? 5 : 6));
                    } else if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) {
                        put(encode(x, y), 1);
                    } else if (mapInfo.hasCloud()) {
                        put(encode(x, y), 2);
                    } else if (rc.sensePassability(loc)) {
                        put(encode(x, y), 3);
                    } else {
                        put(encode(x, y), 4);
                    }

                    int nn = read(encode(x, y));
                    int on = read(encode(oppx, y));
                    int no = read(encode(x, oppy));
                    int oo = read(encode(oppx, oppy));

                    if (nn >= 5) {
                        if (on != 0 && on + nn != 11)
                            tracker.possi[1] = 0;
                        if (no != 0 && no + nn != 11)
                            tracker.possi[2] = 0;
                        if (oo != 0 && oo + nn != 11)
                            tracker.possi[3] = 0;
                    } else {
                        if (on != 0 && on != nn)
                            tracker.possi[1] = 0;
                        if (no != 0 && no != nn)
                            tracker.possi[2] = 0;
                        if (oo != 0 && oo != nn)
                            tracker.possi[3] = 0;
                    }
                }
            }
            if (tracker.possi[target.val] == 0) {
                enemyLocs.remove(enemyLocs.size() - 1);
                if (enemyLocs.size() > 0) {
                    Collections.sort(enemyLocs, new Comparator<possiLoc>() {
                        public int compare(possiLoc a, possiLoc b) {
                            return (Math.min(rc.getLocation().distanceSquaredTo(b.loc), b.offset)
                                - Math.min(rc.getLocation().distanceSquaredTo(a.loc), a.offset));
                        }
                    });
                }
                continue;
            }
            if (rc.getLocation().distanceSquaredTo(target.loc) <= 5) {
                enemyLocs.remove(enemyLocs.size() - 1);
                if (enemyLocs.size() > 0) {
                    Collections.sort(enemyLocs, new Comparator<possiLoc>() {
                        public int compare(possiLoc a, possiLoc b) {
                            return (Math.min(rc.getLocation().distanceSquaredTo(b.loc), b.offset)
                                - Math.min(rc.getLocation().distanceSquaredTo(a.loc), a.offset));
                        }
                    });
                }
                continue;
            }
            return target.loc;
        }
        return target.loc;
    }
}
