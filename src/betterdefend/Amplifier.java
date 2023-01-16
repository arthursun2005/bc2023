package betterdefend;

import battlecode.common.*;

// HUGELY TODO

public class Amplifier extends Robot
{
    static Tracker tracker;

    static long arr[] = new long[195]; // 2^63 - 1
    // 3 per location, 21 locations in one long, need 180

    static void put(int loc, int val) {
        arr[loc / 19] |= ((long) val << ((loc % 19) * 3));
    }

    static int read(int loc) {
        return (int) ((arr[loc / 19] >> ((loc % 19)*3))%8);
    }
    static int possi[] = {0,1,1,1};

    static boolean foundSymmetry = false;

    static int symmetry = 0;

    static int encode(int x, int y) {
        return x * 60 + y;
    }

    static RobotInfo possibleHQ = null;
    static MapInfo mapInfo = null;
    static int nn,no,on,oo;

    static void tryFindSymmetry() throws GameActionException {
        if (!foundSymmetry) {
            int radius = 18;
            possi=Tracker.readpossi();

            MapLocation toCheck[] = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(),radius);
            int x, y, oppx, oppy, val;
            MapLocation loc = null;
            // 1 for passable, 2 for impassable
            for (int i = 0; i < toCheck.length; i++) {
                loc = toCheck[i];
                val = rc.getLocation().distanceSquaredTo(loc);

                if (val < 9 || val/2 == 8) continue;

                if (!rc.canSenseLocation(loc)) continue; // 5

                x = loc.x; y = loc.y;
                oppx = rc.getMapWidth()-x-1; oppy = rc.getMapHeight()-y-1;

                possibleHQ = rc.senseRobotAtLocation(loc); // 25
                mapInfo = rc.senseMapInfo(loc); // 10

                if (possibleHQ != null && possibleHQ.getType().equals(RobotType.HEADQUARTERS)) {
                    put(encode(x, y), 1);
                } else if (!mapInfo.getCurrentDirection().equals(Direction.CENTER)) {
                    put(encode(x, y), 2);
                } else if (mapInfo.hasCloud()) {
                    put(encode(x, y), 3);
                } else if (rc.sensePassability(loc)) {
                    put(encode(x, y), 4);
                } else {
                    put(encode(x, y), 5);
                }

                nn=read(encode(x, y));
                on=read(encode(oppx, y));
                no=read(encode(x, oppy));
                oo=read(encode(oppx, oppy));

                if (on != 0 && on != nn) possi[1] = 0;
                if (no != 0 && no != nn) possi[2] = 0;
                if (oo != 0 && oo != nn) possi[3] = 0;
            }

            Tracker.writepossi(possi);

            if (possi[1]+possi[2]+possi[3]==1) {
                rc.setIndicatorString("SYMMETRY FOUND" + (possi[1] == 1 ? 1 : (possi[2] == 1 ? 2 : 3)));
                symmetry = (possi[1] == 1 ? 1 : (possi[2] == 1 ? 2 : 3));
                foundSymmetry = true;
            }
        }
    }

    public Amplifier(RobotController rc) throws GameActionException
    {
        super(rc);
    }

    static int counter=0;

    public void run() throws GameActionException
    {
        if (counter >= 1) tryFindSymmetry();
        counter++;

        if (tracker.isEnemyGroup()) {
            tracker.addEnemyGroup();
            moveTo(tracker.getClosestHQLoc());
            rc.setIndicatorString("Added " + rc.getLocation().x + rc.getLocation().y);
            return;
        }
        //moveRandom();
        MapLocation oppositeLoc = new MapLocation(rc.getMapWidth()-parentLoc.x-1,rc.getMapHeight()-parentLoc.y-1);

        if (true) {
            switch (symmetry) {
                case 1: {
                    moveTo(new MapLocation(oppositeLoc.x,parentLoc.y));
                    break;
                }
                case 2: {
                    moveTo(new MapLocation(parentLoc.x,oppositeLoc.y));
                    break;
                }
                default: {
                    if (possi[3]==1) moveTo(oppositeLoc);
                    else moveTo(new MapLocation(parentLoc.x,oppositeLoc.y));
                    break;
                }
            }
        }
        else {
            moveRandom();
        }
//        moveTo(new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2));
        rc.setIndicatorString("Searching " + rc.senseNearbyRobots(tracker.ENEMY_GROUP_RADIUS, rc.getTeam().opponent()).length);
    }
}
