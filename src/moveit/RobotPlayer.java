package moveit;

import battlecode.common.Clock;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;

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
                robot = new Destabilizer(rc);
                break;
            case AMPLIFIER:
                robot = new Amplifier(rc);
                break;
        }

        while (true) {
            try {
                int startRound = rc.getRoundNum();
                if (robot != null) {
                    robot.prepare();
                    robot.run();
                }
                if (rc.getRoundNum() != startRound) {
                    rc.setIndicatorDot(rc.getLocation(), 0, 255, 0);
                    System.out.println("NOOOOOOOOOOOO~~~~~");
                }

                int a = Clock.getBytecodeNum();
                StringBuilder s = new StringBuilder(String.format("%30000s", "").replace(' ', '+'));
                int b = Clock.getBytecodeNum();
                int c = Clock.getBytecodeNum();
                System.out.println(s.substring(5, 300));
                int d = Clock.getBytecodeNum();
                System.out.println((b - a) + " " + (c - b) + " " + (d - c));
            } catch (GameActionException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Clock.yield();
            }
        }
    }
}
