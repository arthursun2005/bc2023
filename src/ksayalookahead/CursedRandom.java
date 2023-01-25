package ksayalookahead;

import battlecode.common.*;

public class CursedRandom {


    public CursedRandom(int seed) throws GameActionException {
        randIndex = seed % (lookupRand256.length() - MAX_RAND_CALLS);
    }

    public int nextInt(int mod) {
        return secret.charAt(cur++);
    }
}
