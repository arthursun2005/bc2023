package amogus;

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
    MapLocation parentLoc;
    public Launcher(RobotController rc) throws GameActionException {
        super(rc);

        parentLoc = tracker.getClosestHQLoc();
    }

    ArrayList<possiLoc> enemyLocs = new ArrayList<>();

    int realDist(MapLocation tmpLoc) {
        return Math.max(Math.abs(tmpLoc.x-rc.getLocation().x),Math.abs(tmpLoc.y-rc.getLocation().y));
    }

    void put(int loc, int val) {
        tracker.put(loc,val);
    }

    int read(int loc) {
        return tracker.read(loc);
    }

    int encode(int x, int y) {
        return x * 60 + y;
    }

    public void run() throws GameActionException {
        MapLocation weakLoc = attack.getWeakLoc();
        int status = attack.getStatus(weakLoc);
        rc.setIndicatorString(status + " " + weakLoc);
        if (weakLoc != null) {
            rc.setIndicatorDot(weakLoc, 255, 255, 100);
        }
        if (status == 1) {
            if (weakLoc != null)
                greedilyMove(weakLoc, 1);
            weakLoc = attack.getWeakLoc();
            if (weakLoc != null && rc.canAttack(weakLoc))
                rc.attack(weakLoc);
        } else if (status == 2) {
            attack.tryAttack();
            greedilyMove(attack.getThreat(), -1);
            attack.tryAttack();
        } else if (status == 0) {
            int mini = rc.getID();
            int lowerCount = 0;
            MapLocation bestie = null;
            RobotInfo[] friends = rc.senseNearbyRobots(-1, rc.getTeam());

            for (RobotInfo friend : friends) {
                if (friend.type == RobotType.LAUNCHER) {
                    if (friend.ID < rc.getID())
                        lowerCount++;
                    if (friend.ID < mini) {
                        mini = friend.ID;
                        bestie = friend.getLocation();
                    }
                }
            }

            if (mini < rc.getID() && lowerCount < 9) {
                moveTo(bestie);
            }

            if (enemyLocs.size()==0) {
                for (MapLocation hqLoc : tracker.HQLocations) {
                    MapLocation oppositeLoc = new MapLocation(rc.getMapWidth() - hqLoc.x - 1, rc.getMapHeight() - hqLoc.y - 1);
                    if (tracker.possi[1]==1) enemyLocs.add(new possiLoc(new MapLocation(oppositeLoc.x,hqLoc.y),1,6900));
                    if (tracker.possi[2]==1) enemyLocs.add(new possiLoc(new MapLocation(hqLoc.x,oppositeLoc.y),2,6900));
                    if (tracker.possi[3]==1) enemyLocs.add(new possiLoc(oppositeLoc,3,(hqLoc==parentLoc?69:6900)));
                }
            }

            Collections.sort(enemyLocs, new Comparator<possiLoc>() {
                public int compare(possiLoc a, possiLoc b) {
                    return (Math.min(rc.getLocation().distanceSquaredTo(b.loc),b.offset) - Math.min(rc.getLocation().distanceSquaredTo(a.loc),a.offset));
                }
            });

            while (enemyLocs.size()>0) {
                possiLoc target = enemyLocs.get(enemyLocs.size()-1);
                if (rc.canSenseLocation(target.loc)) {
                    MapLocation loc = target.loc;

                    int x = loc.x, y = loc.y;
                    int oppx = rc.getMapWidth()-x-1, oppy = rc.getMapHeight()-y-1;

                    if (read(encode(x,y))==0) {

                        RobotInfo possibleHQ = rc.senseRobotAtLocation(loc); // 25
                        MapInfo mapInfo = rc.senseMapInfo(loc); // 10

                        if (possibleHQ != null && possibleHQ.getType().equals(RobotType.HEADQUARTERS)) {
                            put(encode(x, y), (possibleHQ.getTeam()==rc.getTeam()?5:6));
                        } else if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) {
                            put(encode(x, y), 1);
                        } else if (mapInfo.hasCloud()) {
                            put(encode(x, y), 2);
                        } else if (rc.sensePassability(loc)) {
                            put(encode(x, y), 3);
                        } else {
                            put(encode(x, y), 4);
                        }

                        int nn=read(encode(x, y));
                        int on=read(encode(oppx, y));
                        int no=read(encode(x, oppy));
                        int oo=read(encode(oppx, oppy));

                        if (nn >= 5) {
                            if (on != 0 && on+nn != 11) tracker.possi[1] = 0;
                            if (no != 0 && no+nn != 11) tracker.possi[2] = 0;
                            if (oo != 0 && oo+nn != 11) tracker.possi[3] = 0;
                        }
                        else {
                            if (on != 0 && on != nn) tracker.possi[1] = 0;
                            if (no != 0 && no != nn) tracker.possi[2] = 0;
                            if (oo != 0 && oo != nn) tracker.possi[3] = 0;
                        }
                    }
                }
                if (tracker.possi[target.val]==0) {
                    enemyLocs.remove(enemyLocs.size()-1);
                    continue;
                }
                if (rc.getLocation().distanceSquaredTo(target.loc)<=5) {
                    enemyLocs.remove(enemyLocs.size()-1);
                    continue;
                }
                rc.setIndicatorString("Ree "+target.loc);
                moveTo(target.loc);
                break;
            }

            attack.tryAttack();
        }
        tracker.tryFindSymmetry();
    }
}
