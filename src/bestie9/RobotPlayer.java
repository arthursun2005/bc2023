package bestie9;

import battlecode.common.*;

import java.util.Random;

public strictfp class RobotPlayer {
    @SuppressWarnings("unused")
    public static void run(RobotController rc) throws GameActionException {
        Robot robot = null;
        switch (rc.getType()) {
            case HEADQUARTERS:
                robot = new Headquarter(rc);
                break;
            case CARRIER:
                robot = new Carrier(rc);
                break;
            case LAUNCHER:
                robot = new Launcher(rc);
                break;
            case BOOSTER:
                robot = new Booster(rc);
                break;
            case DESTABILIZER:
                break;
            case AMPLIFIER:
                robot = new Amplifier(rc);
                break;
        }

        while (true) {
            try {
                if (robot != null) {
                    robot.prepare();
                    robot.run();
                }
            } catch (GameActionException e) {
                System.out.println(rc.getType() + " Exception");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println(rc.getType() + " Exception");
                e.printStackTrace();
            } finally {
                Clock.yield();
            }
        }
    }
}
