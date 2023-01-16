package helloworld;

import battlecode.common.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * RobotPlayer is the class that describes your main robot strategy.
 * The run() method inside this class is like your main function: this is what we'll call once your robot
 * is created!
 */
public strictfp class RobotPlayer {

    /**
     * We will use this variable to count the number of turns this robot has been alive.
     * You can use static variables like this to save any information you want. Keep in mind that even though
     * these variables are static, in Battlecode they aren't actually shared between your robots.
     */
    static int turnCount = 0;

    /**
     * A random number generator.
     * We will use this RNG to make some random moves. The Random class is provided by the java.util.Random
     * import at the top of this file. Here, we *seed* the RNG with a constant number (6147); this makes sure
     * we get the same sequence of numbers every time this code is run. This is very useful for debugging!
     */
    static Random rng;

    /** Array containing all the possible movement directions. */
    static final Direction[] directions = {
        Direction.NORTH,
        Direction.NORTHEAST,
        Direction.EAST,
        Direction.SOUTHEAST,
        Direction.SOUTH,
        Direction.SOUTHWEST,
        Direction.WEST,
        Direction.NORTHWEST,
    };

    /**
     * run() is the method that is called when a robot is instantiated in the Battlecode world.
     * It is like the main function for your robot. If this method returns, the robot dies!
     *
     * @param rc  The RobotController object. You use it to perform actions from this robot, and to get
     *            information on its current status. Essentially your portal to interacting with the world.
     **/
    @SuppressWarnings("unused")
    public static void run(RobotController rc) throws GameActionException {

        rng = new Random(rc.getID());

        // Hello world! Standard output is very useful for debugging.
        // Everything you say here will be directly viewable in your terminal when you run a match!

        // You can also use indicators to save debug notes in replays.
        // rc.setIndicatorString("Hello world!");

        while (true) {
            // This code runs during the entire lifespan of the robot, which is why it is in an infinite
            // loop. If we ever leave this loop and return from run(), the robot dies! At the end of the
            // loop, we call Clock.yield(), signifying that we've done everything we want to do.

            turnCount += 1;  // We have now been alive for one more turn!

            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode.
            try {
                // The same run() function is called for every robot on your team, even if they are
                // different types. Here, we separate the control depending on the RobotType, so we can
                // use different strategies on different robots. If you wish, you are free to rewrite
                // this into a different control structure!
                switch (rc.getType()) {
                    case HEADQUARTERS:     runHeadquarters(rc);  break;
                    case CARRIER:      runCarrier(rc);   break;
                    case LAUNCHER:      runLauncher(rc); break;
                    case BOOSTER: // Examplefuncsplayer doesn't use any of these robot types below.
                    case DESTABILIZER: // You might want to give them a try!
                    case AMPLIFIER:       break;
                }

            } catch (GameActionException e) {
                // Oh no! It looks like we did something illegal in the Battlecode world. You should
                // handle GameActionExceptions judiciously, in case unexpected events occur in the game
                // world. Remember, uncaught exceptions cause your robot to explode!
                e.printStackTrace();

            } catch (Exception e) {
                // Oh no! It looks like our code tried to do something bad. This isn't a
                // GameActionException, so it's more likely to be a bug in our code.
                e.printStackTrace();

            } finally {
                // Signify we've done everything we want to do, thereby ending our turn.
                // This will make our code wait until the next turn, and then perform this loop again.
                Clock.yield();
            }
            // End of loop: go back to the top. Clock.yield() has ended, so it's time for another turn!
        }

        // Your code should never reach here (unless it's intentional)! Self-destruction imminent...
    }

    /**
     * Run a single turn for a Headquarters.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void runHeadquarters(RobotController rc) throws GameActionException {
        if (rc.canBuildAnchor(Anchor.STANDARD)) {
            // If we can build an anchor do it!
            rc.buildAnchor(Anchor.STANDARD);
            rc.setIndicatorString("Building anchor! " + rc.getAnchor());
        }
        else if (rc.getAnchor() != null && rc.getResourceAmount(ResourceType.ADAMANTIUM)>=50) {
            Direction dir; MapLocation newLoc;
            do {
                dir = directions[rng.nextInt(directions.length)];
                newLoc = rc.getLocation().add(dir);
            } while (!rc.canBuildRobot(RobotType.CARRIER, newLoc));
            rc.setIndicatorString("Trying to build a carrier");
            rc.buildRobot(RobotType.CARRIER, newLoc);
        }
        if (rc.getResourceAmount(ResourceType.MANA)-rc.getResourceAmount(ResourceType.ADAMANTIUM)>=60) {
            Direction dir; MapLocation newLoc;
            do {
                dir = directions[rng.nextInt(directions.length)];
                newLoc = rc.getLocation().add(dir);
            } while (!rc.canBuildRobot(RobotType.LAUNCHER, newLoc));
            rc.setIndicatorString("Trying to build a launcher");
            rc.buildRobot(RobotType.LAUNCHER, newLoc);
        }
    }

    /**
     * Run a single turn for a Carrier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void tryMove(RobotController rc, Direction dir) throws GameActionException {
        if (!rc.isMovementReady()) return;
        for (int i=0; i<8; i++) {
            if (rc.canMove(dir)) {
                rc.move(dir);
                return;
            }
            dir=dir.rotateRight();
        }
    }
    static int moveCount = 0;
    static Direction curDir = Direction.NORTH;
    static void runCarrier(RobotController rc) throws GameActionException {
        MapLocation me = rc.getLocation();
        if (rc.getAnchor() == null) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    MapLocation hqLoc = new MapLocation(me.x + dx, me.y + dy);
                    if (rc.canTakeAnchor(hqLoc, Anchor.STANDARD)) {
                        rc.setIndicatorString("Taking standard Anchor");
                        rc.takeAnchor(hqLoc, Anchor.STANDARD);
                    }
                }
            }
        }
        if (rc.getAnchor() != null) {
            // If I have an anchor singularly focus on getting it to the first island I see
            int[] islands = rc.senseNearbyIslands();
            int mini = 42069;
            MapLocation minLoc = me;
            for (int id : islands) {
                if (rc.senseAnchor(id)!=null) continue;
                MapLocation[] thisIslandLocs = rc.senseNearbyIslandLocations(id);
                for (MapLocation nLoc : thisIslandLocs) {
                    if (me.distanceSquaredTo(nLoc) < mini) {
                        mini=me.distanceSquaredTo(nLoc);
                        minLoc=nLoc;
                    }
                }
            }
            if (mini!=42069) {
                if (me.equals(minLoc)&&rc.canPlaceAnchor()) {
                    rc.setIndicatorString("Huzzah, placed anchor!");
                    rc.placeAnchor();
                    return;
                }
                rc.setIndicatorString("Moving my anchor towards " + minLoc);
                Direction dir = me.directionTo(minLoc);
                tryMove(rc, dir);
            }
            else {
                if (!rc.isMovementReady()) return;
                if (moveCount==0 || !rc.canMove(curDir)) {
                    moveCount=4;
                    Direction newDirects[] = {
                        curDir,
                        curDir.rotateRight(),
                        curDir.rotateLeft(),
                        curDir.rotateRight().rotateRight(),
                        curDir.rotateLeft().rotateLeft(),
                    };
                    for (int i=0; i<20; i++) {
                        curDir = newDirects[rng.nextInt(newDirects.length)];
                        if (rc.canMove(curDir)) {
                            rc.move(curDir);
                            return;
                        }
                    }
                    while (true) {
                        curDir = directions[rng.nextInt(directions.length)];
                        if (rc.canMove(curDir)) {
                            rc.move(curDir);
                            return;
                        }
                    }
                }
                moveCount--;
                rc.move(curDir);
            }
        }
    }

    static void runLauncher(RobotController rc) throws GameActionException {
        // Try to attack someone
        int radius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);
        if (enemies.length >= 0) {
            for (RobotInfo enemy : enemies) {
                MapLocation toAttack = enemy.location;
                if (rc.canAttack(toAttack)) {
                    rc.setIndicatorString("Attacking");
                    rc.attack(toAttack);
                    return;
                }
            }
        }
        // should probably put this in a function
        if (!rc.isMovementReady()) return;
        if (moveCount==0 || !rc.canMove(curDir)) {
            moveCount=4;
            Direction newDirects[] = {
                curDir,
                curDir.rotateRight(),
                curDir.rotateLeft(),
                curDir.rotateRight().rotateRight(),
                curDir.rotateLeft().rotateLeft(),
            };
            for (int i=0; i<20; i++) {
                curDir = newDirects[rng.nextInt(newDirects.length)];
                if (rc.canMove(curDir)) {
                    rc.move(curDir);
                    return;
                }
            }
            while (true) {
                curDir = directions[rng.nextInt(directions.length)];
                if (rc.canMove(curDir)) {
                    rc.move(curDir);
                    return;
                }
            }
        }
        moveCount--;
        rc.move(curDir);
    }
}

