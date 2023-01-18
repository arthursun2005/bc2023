package amogus;

import battlecode.common.*;

import java.util.*;

class possiLoc {
    MapLocation loc;
    int val;
    possiLoc(MapLocation loc, int val) {
        this.loc = loc;
        this.val = val;
    }
}

public class Launcher extends Robot {
    public Launcher(RobotController rc) throws GameActionException {
        super(rc);
    }

    ArrayList<possiLoc> enemyLocs = new ArrayList<>();

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
                    if (tracker.possi[1]==1) enemyLocs.add(new possiLoc(new MapLocation(oppositeLoc.x,hqLoc.y),1));
                    if (tracker.possi[2]==1) enemyLocs.add(new possiLoc(new MapLocation(hqLoc.x,oppositeLoc.y),2));
                    if (tracker.possi[3]==1) enemyLocs.add(new possiLoc(oppositeLoc,3));
                }
            }

            Collections.sort(enemyLocs, new Comparator<possiLoc>() {
                public int compare(possiLoc a, possiLoc b) {
                    return (rc.getLocation().distanceSquaredTo(b.loc)-(b.val==3?300:0)) - (rc.getLocation().distanceSquaredTo(a.loc)-(a.val==3?300:0));
                }
            });

            while (enemyLocs.size()>0) {
                possiLoc target = enemyLocs.get(enemyLocs.size()-1);
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
