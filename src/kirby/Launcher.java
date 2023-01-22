package kirby;

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

public class Launcher extends Robot {
    boolean reached = false;
    MapLocation parentLoc;
    possiLoc target;

    ArrayList<possiLoc> enemyLocs = new ArrayList<>();

    int realDist(MapLocation tmpLoc) {
        return Math.max(Math.abs(tmpLoc.x - rc.getLocation().x), Math.abs(tmpLoc.y - rc.getLocation().y));
    }

    void put(int loc, int val) {
        tracker.put(loc, val);
    }

    int read(int loc) {
        return tracker.read(loc);
    }

    int encode(int x, int y) {
        return x * 60 + y;
    }

    MapLocation adjust(MapLocation a) {
        int M = 1;
        if (rng.nextInt(3) != 0)
            return a;
        return new MapLocation(a.x + rng.nextInt(M) - M / 2, a.y + rng.nextInt(M) - M / 2);
    }

    public Launcher(RobotController rc) throws GameActionException {
        super(rc);

        int width = rc.getMapWidth();
        int height = rc.getMapHeight();

        parentLoc = tracker.getClosestHQLoc();
        MapLocation oppositeLoc = new MapLocation(width - parentLoc.x - 1,
                height - parentLoc.y - 1);
        target = new possiLoc(oppositeLoc, 3, 69);
    }

    MapLocation HQLoc;

    int eval(MapLocation loc, MapLocation target, int mul) throws GameActionException {
        int base = target.distanceSquaredTo(loc) * mul;// * 1_000_000 - loc.distanceSquaredTo(HQLoc);
        if (rc.senseCloud(loc))
            base += 1_000_000_000;
        return base;
    }

    public void randomizedGreedy(MapLocation target, int mul, int tol) throws GameActionException {
        int hits = rc.getLocation().distanceSquaredTo(target) <= tol
                ? eval(rc.getLocation(), target, mul)
                : 1_000_000_000;
        Direction[] allGood = new Direction[9];
        int gc = 0;

        for (Direction dir : directions) {
            if (!rc.canMove(dir))
                continue;
            MapLocation loc = rc.adjacentLocation(dir);
            if (loc.distanceSquaredTo(target) > tol)
                continue;
            int w = eval(loc, target, mul);
            if (w < hits) {
                hits = w;
                allGood[0] = dir;
                gc = 1;
            } else if (w == hits) {
                allGood[gc] = dir;
                gc++;
            }
        }

        if (gc != 0)
            rc.move(allGood[rng.nextInt(gc)]);
    }

    public void run() throws GameActionException {
        HQLoc = tracker.getClosestHQLoc();
        MapLocation weakLoc = attack.getWeakLoc();
        int status = attack.getStatus(weakLoc);
        rc.setIndicatorString(status + " " + weakLoc);
        if (weakLoc != null) {
            rc.setIndicatorDot(weakLoc, 255, 255, 100);
        }
        if (target != null) {
            rc.setIndicatorLine(target.loc, rc.getLocation(), 225, 235, 255);
        }

        // if (status == 0 && rc.canSenseLocation(target.loc))
        //     status = 1;

        if (status == 1) {
            if (weakLoc != null) {
                randomizedGreedy(weakLoc, -1, rc.getType().actionRadiusSquared);
            }
            attack.tryAttack();
        } else if (status == 2) {
            attack.tryAttack();
            randomizedGreedy(weakLoc, -1, 1_000_000);
            attack.tryAttack();
        } else if (status == 0) {
            if (rc.getLocation().distanceSquaredTo(target.loc) <= 8)
                reached = true;

            int mini = rc.getLocation().distanceSquaredTo(target.loc);
            // int lowerCount = 0;
            MapLocation bestie = null;
            RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());
            int count = 0;

            for (RobotInfo friend : friends) {
                if (friend.type == RobotType.LAUNCHER) {
                    // if (friend.ID < rc.getID())
                    // lowerCount++;
                    count++;
                    if (friend.location.distanceSquaredTo(target.loc) < mini) {
                        mini = friend.location.distanceSquaredTo(target.loc);
                        bestie = friend.location;
                    }
                }
            }

            attack.tryAttack();

            MapLocation site = tracker.pls();
            if (site != null
                    && rc.getLocation().distanceSquaredTo(site) < 2 * rc.getLocation().distanceSquaredTo(target.loc)) {
                moveTo(site);
                rc.setIndicatorLine(site, rc.getLocation(), 255, 0, 0);
            }

            int req = 0;// (int) (rc.getRoundNum() / 100) + 1;

            // if (mini < rc.getID() && lowerCount < 9) {
            // moveTo(bestie);
            // }
            if (bestie != null) {
                // Direction dir = rc.getLocation().directionTo(bestie);
                // tryMove(dir.rotateLeft().rotateLeft());
                // tryMove(dir.rotateRight().rotateRight());
                // tryMove(dir.rotateLeft());
                // tryMove(dir.rotateRight());
                moveTo(bestie);
            }

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
                        enemyLocs.add(new possiLoc(oppositeLoc, 3, (hqLoc == parentLoc ? 6900 : 6900)));
                }
            }

            Collections.sort(enemyLocs, new Comparator<possiLoc>() {
                public int compare(possiLoc a, possiLoc b) {
                    // return (Math.min(rc.getLocation().distanceSquaredTo(b.loc), b.offset)
                    // - Math.min(rc.getLocation().distanceSquaredTo(a.loc), a.offset));
                    return (Math.min(Util.l1(rc.getLocation(), b.loc), b.offset)
                            - Math.min(Util.l1(rc.getLocation(), a.loc), a.offset));
                }
            });

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
                    continue;
                }
                if (rc.getLocation().distanceSquaredTo(target.loc) <= 5) {
                    enemyLocs.remove(enemyLocs.size() - 1);
                    continue;
                }
                if (count == 0 || count >= req)
                    moveTo(target.loc);
                break;
            }

            attack.tryAttack();
        } else {
            attack.tryAttack();
            randomizedGreedy(weakLoc, -1, rc.getType().visionRadiusSquared);
            attack.tryAttack();
        }
        if (weakLoc != null && rc.canAttack(weakLoc))
            rc.attack(weakLoc);
        attack.snipe();

        tracker.tryFindSymmetry();
    }
}
