package Mining5;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class Communication {

    public final int CARRIER = 31;
    public final int LAUNCHER = 32;
    RobotController rc;
    public Communication(RobotController rc) {
        this.rc = rc;
    }
    public void increaseCarrierCount() throws GameActionException {
        if (!rc.canWriteSharedArray(0, 0)) return;

        int temp = rc.readSharedArray(CARRIER);
        rc.writeSharedArray(CARRIER, temp + 1);
    }

    public void increaseLauncherCount() throws GameActionException {
        if (!rc.canWriteSharedArray(0, 0)) return;

        int temp = rc.readSharedArray(LAUNCHER);
        rc.writeSharedArray(LAUNCHER, temp + 1);
    }

    public int readCarrierCount() throws GameActionException {
        return rc.readSharedArray(CARRIER);
    }
    public int readLauncherCount() throws GameActionException {
        return rc.readSharedArray(LAUNCHER);
    }


}
