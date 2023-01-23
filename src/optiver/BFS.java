package optiver;

import battlecode.common.*;

import java.util.Map;

public class BFS {

    static int dist2 = 0;
    static int dist3 = 0;
    static int dist4 = 0;
    static int dist5 = 0;
    static int dist6 = 0;
    static int dist10 = 0;
    static int dist11 = 0;
    static int dist12 = 0;
    static int dist13 = 0;
    static int dist14 = 0;
    static int dist15 = 0;
    static int dist16 = 0;
    static int dist18 = 0;
    static int dist19 = 0;
    static int dist20 = 0;
    static int dist21 = 0;
    static int dist22 = 0;
    static int dist23 = 0;
    static int dist24 = 0;
    static int dist25 = 0;
    static int dist26 = 0;
    static int dist27 = 0;
    static int dist28 = 0;
    static int dist29 = 0;
    static int dist30 = 0;
    static int dist31 = 0;
    static int dist32 = 0;
    static int dist33 = 0;
    static int dist34 = 0;
    static int dist35 = 0;
    static int dist36 = 0;
    static int dist37 = 0;
    static int dist38 = 0;
    static int dist39 = 0;
    static int dist40 = 0;
    static int dist41 = 0;
    static int dist42 = 0;
    static int dist43 = 0;
    static int dist44 = 0;
    static int dist45 = 0;
    static int dist46 = 0;
    static int dist47 = 0;
    static int dist48 = 0;
    static int dist49 = 0;
    static int dist50 = 0;
    static int dist51 = 0;
    static int dist52 = 0;
    static int dist53 = 0;
    static int dist54 = 0;
    static int dist55 = 0;
    static int dist56 = 0;
    static int dist57 = 0;
    static int dist58 = 0;
    static int dist59 = 0;
    static int dist60 = 0;
    static int dist61 = 0;
    static int dist62 = 0;
    static int dist64 = 0;
    static int dist65 = 0;
    static int dist66 = 0;
    static int dist67 = 0;
    static int dist68 = 0;
    static int dist69 = 0;
    static int dist70 = 0;
    static int dist74 = 0;
    static int dist75 = 0;
    static int dist76 = 0;
    static int dist77 = 0;
    static int dist78 = 0;

    RobotController rc;
    StringBuilder validLocation = new StringBuilder();
    StringBuilder seen = new StringBuilder();


    public void redoMap() throws GameActionException {
        MapInfo mapInfo[] = rc.senseNearbyMapInfos();

        MapLocation loc;
        for (MapInfo mapInfo1 : mapInfo) {
            loc = mapInfo1.getMapLocation();
            if (mapInfo1.isPassable()) validLocation.setCharAt((loc.x % 10) * 10 + loc.y % 10, (char) (1 + '0'));
            if (!mapInfo1.getCurrentDirection().equals(Direction.CENTER)) validLocation.setCharAt((loc.x % 10) * 10 + loc.y % 10, (char) (1 + mapInfo1.getCurrentDirection().getDirectionOrderNum() + '0'));
        }
    }

    public BFS(RobotController rc) throws GameActionException {
        this.rc = rc;
        validLocation.append("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        seen.append("000000000000000000000000000000000000000000000000000000000000000000000000000000000");

        redoMap();

//        System.out.println(validLocation);
    }
    public Direction backtrack11() {
        if (seen.charAt(11) != '1') return null;
        if (dist12 == dist11 - 1 && seen.charAt(12) == '1') {
            if (dist12 == 0) {
                return Direction.SOUTH;}
            return backtrack12();
        }
        if (seen.charAt(11) != '1') return null;
        if (dist19 == dist11 - 1 && seen.charAt(19) == '1') {
            if (dist19 == 0) {
                return Direction.NORTHWEST;}
            return backtrack19();
        }
        if (seen.charAt(11) != '1') return null;
        if (dist20 == dist11 - 1 && seen.charAt(20) == '1') {
            if (dist20 == 0) {
                return Direction.WEST;}
            return backtrack20();
        }
        if (seen.charAt(11) != '1') return null;
        if (dist21 == dist11 - 1 && seen.charAt(21) == '1') {
            if (dist21 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack21();
        }
        return null;}
    public Direction backtrack12() {
        if (seen.charAt(12) != '1') return null;
        if (dist11 == dist12 - 1 && seen.charAt(11) == '1') {
            if (dist11 == 0) {
                return Direction.NORTH;}
            return backtrack11();
        }
        if (seen.charAt(12) != '1') return null;
        if (dist13 == dist12 - 1 && seen.charAt(13) == '1') {
            if (dist13 == 0) {
                return Direction.SOUTH;}
            return backtrack13();
        }
        if (seen.charAt(12) != '1') return null;
        if (dist20 == dist12 - 1 && seen.charAt(20) == '1') {
            if (dist20 == 0) {
                return Direction.NORTHWEST;}
            return backtrack20();
        }
        if (seen.charAt(12) != '1') return null;
        if (dist21 == dist12 - 1 && seen.charAt(21) == '1') {
            if (dist21 == 0) {
                return Direction.WEST;}
            return backtrack21();
        }
        if (seen.charAt(12) != '1') return null;
        if (dist22 == dist12 - 1 && seen.charAt(22) == '1') {
            if (dist22 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack22();
        }
        return null;}
    public Direction backtrack13() {
        if (seen.charAt(13) != '1') return null;
        if (dist12 == dist13 - 1 && seen.charAt(12) == '1') {
            if (dist12 == 0) {
                return Direction.NORTH;}
            return backtrack12();
        }
        if (seen.charAt(13) != '1') return null;
        if (dist14 == dist13 - 1 && seen.charAt(14) == '1') {
            if (dist14 == 0) {
                return Direction.SOUTH;}
            return backtrack14();
        }
        if (seen.charAt(13) != '1') return null;
        if (dist21 == dist13 - 1 && seen.charAt(21) == '1') {
            if (dist21 == 0) {
                return Direction.NORTHWEST;}
            return backtrack21();
        }
        if (seen.charAt(13) != '1') return null;
        if (dist22 == dist13 - 1 && seen.charAt(22) == '1') {
            if (dist22 == 0) {
                return Direction.WEST;}
            return backtrack22();
        }
        if (seen.charAt(13) != '1') return null;
        if (dist23 == dist13 - 1 && seen.charAt(23) == '1') {
            if (dist23 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack23();
        }
        return null;}
    public Direction backtrack14() {
        if (seen.charAt(14) != '1') return null;
        if (dist13 == dist14 - 1 && seen.charAt(13) == '1') {
            if (dist13 == 0) {
                return Direction.NORTH;}
            return backtrack13();
        }
        if (seen.charAt(14) != '1') return null;
        if (dist15 == dist14 - 1 && seen.charAt(15) == '1') {
            if (dist15 == 0) {
                return Direction.SOUTH;}
            return backtrack15();
        }
        if (seen.charAt(14) != '1') return null;
        if (dist22 == dist14 - 1 && seen.charAt(22) == '1') {
            if (dist22 == 0) {
                return Direction.NORTHWEST;}
            return backtrack22();
        }
        if (seen.charAt(14) != '1') return null;
        if (dist23 == dist14 - 1 && seen.charAt(23) == '1') {
            if (dist23 == 0) {
                return Direction.WEST;}
            return backtrack23();
        }
        if (seen.charAt(14) != '1') return null;
        if (dist24 == dist14 - 1 && seen.charAt(24) == '1') {
            if (dist24 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack24();
        }
        return null;}
    public Direction backtrack15() {
        if (seen.charAt(15) != '1') return null;
        if (dist14 == dist15 - 1 && seen.charAt(14) == '1') {
            if (dist14 == 0) {
                return Direction.NORTH;}
            return backtrack14();
        }
        if (seen.charAt(15) != '1') return null;
        if (dist23 == dist15 - 1 && seen.charAt(23) == '1') {
            if (dist23 == 0) {
                return Direction.NORTHWEST;}
            return backtrack23();
        }
        if (seen.charAt(15) != '1') return null;
        if (dist24 == dist15 - 1 && seen.charAt(24) == '1') {
            if (dist24 == 0) {
                return Direction.WEST;}
            return backtrack24();
        }
        if (seen.charAt(15) != '1') return null;
        if (dist25 == dist15 - 1 && seen.charAt(25) == '1') {
            if (dist25 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack25();
        }
        return null;}
    public Direction backtrack19() {
        if (seen.charAt(19) != '1') return null;
        if (dist11 == dist19 - 1 && seen.charAt(11) == '1') {
            if (dist11 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack11();
        }
        if (seen.charAt(19) != '1') return null;
        if (dist20 == dist19 - 1 && seen.charAt(20) == '1') {
            if (dist20 == 0) {
                return Direction.SOUTH;}
            return backtrack20();
        }
        if (seen.charAt(19) != '1') return null;
        if (dist28 == dist19 - 1 && seen.charAt(28) == '1') {
            if (dist28 == 0) {
                return Direction.WEST;}
            return backtrack28();
        }
        if (seen.charAt(19) != '1') return null;
        if (dist29 == dist19 - 1 && seen.charAt(29) == '1') {
            if (dist29 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack29();
        }
        return null;}
    public Direction backtrack20() {
        if (seen.charAt(20) != '1') return null;
        if (dist11 == dist20 - 1 && seen.charAt(11) == '1') {
            if (dist11 == 0) {
                return Direction.EAST;}
            return backtrack11();
        }
        if (seen.charAt(20) != '1') return null;
        if (dist12 == dist20 - 1 && seen.charAt(12) == '1') {
            if (dist12 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack12();
        }
        if (seen.charAt(20) != '1') return null;
        if (dist19 == dist20 - 1 && seen.charAt(19) == '1') {
            if (dist19 == 0) {
                return Direction.NORTH;}
            return backtrack19();
        }
        if (seen.charAt(20) != '1') return null;
        if (dist21 == dist20 - 1 && seen.charAt(21) == '1') {
            if (dist21 == 0) {
                return Direction.SOUTH;}
            return backtrack21();
        }
        if (seen.charAt(20) != '1') return null;
        if (dist28 == dist20 - 1 && seen.charAt(28) == '1') {
            if (dist28 == 0) {
                return Direction.NORTHWEST;}
            return backtrack28();
        }
        if (seen.charAt(20) != '1') return null;
        if (dist29 == dist20 - 1 && seen.charAt(29) == '1') {
            if (dist29 == 0) {
                return Direction.WEST;}
            return backtrack29();
        }
        if (seen.charAt(20) != '1') return null;
        if (dist30 == dist20 - 1 && seen.charAt(30) == '1') {
            if (dist30 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack30();
        }
        return null;}
    public Direction backtrack21() {
        if (seen.charAt(21) != '1') return null;
        if (dist11 == dist21 - 1 && seen.charAt(11) == '1') {
            if (dist11 == 0) {
                return Direction.NORTHEAST;}
            return backtrack11();
        }
        if (seen.charAt(21) != '1') return null;
        if (dist12 == dist21 - 1 && seen.charAt(12) == '1') {
            if (dist12 == 0) {
                return Direction.EAST;}
            return backtrack12();
        }
        if (seen.charAt(21) != '1') return null;
        if (dist13 == dist21 - 1 && seen.charAt(13) == '1') {
            if (dist13 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack13();
        }
        if (seen.charAt(21) != '1') return null;
        if (dist20 == dist21 - 1 && seen.charAt(20) == '1') {
            if (dist20 == 0) {
                return Direction.NORTH;}
            return backtrack20();
        }
        if (seen.charAt(21) != '1') return null;
        if (dist22 == dist21 - 1 && seen.charAt(22) == '1') {
            if (dist22 == 0) {
                return Direction.SOUTH;}
            return backtrack22();
        }
        if (seen.charAt(21) != '1') return null;
        if (dist29 == dist21 - 1 && seen.charAt(29) == '1') {
            if (dist29 == 0) {
                return Direction.NORTHWEST;}
            return backtrack29();
        }
        if (seen.charAt(21) != '1') return null;
        if (dist30 == dist21 - 1 && seen.charAt(30) == '1') {
            if (dist30 == 0) {
                return Direction.WEST;}
            return backtrack30();
        }
        if (seen.charAt(21) != '1') return null;
        if (dist31 == dist21 - 1 && seen.charAt(31) == '1') {
            if (dist31 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack31();
        }
        return null;}
    public Direction backtrack22() {
        if (seen.charAt(22) != '1') return null;
        if (dist12 == dist22 - 1 && seen.charAt(12) == '1') {
            if (dist12 == 0) {
                return Direction.NORTHEAST;}
            return backtrack12();
        }
        if (seen.charAt(22) != '1') return null;
        if (dist13 == dist22 - 1 && seen.charAt(13) == '1') {
            if (dist13 == 0) {
                return Direction.EAST;}
            return backtrack13();
        }
        if (seen.charAt(22) != '1') return null;
        if (dist14 == dist22 - 1 && seen.charAt(14) == '1') {
            if (dist14 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack14();
        }
        if (seen.charAt(22) != '1') return null;
        if (dist21 == dist22 - 1 && seen.charAt(21) == '1') {
            if (dist21 == 0) {
                return Direction.NORTH;}
            return backtrack21();
        }
        if (seen.charAt(22) != '1') return null;
        if (dist23 == dist22 - 1 && seen.charAt(23) == '1') {
            if (dist23 == 0) {
                return Direction.SOUTH;}
            return backtrack23();
        }
        if (seen.charAt(22) != '1') return null;
        if (dist30 == dist22 - 1 && seen.charAt(30) == '1') {
            if (dist30 == 0) {
                return Direction.NORTHWEST;}
            return backtrack30();
        }
        if (seen.charAt(22) != '1') return null;
        if (dist31 == dist22 - 1 && seen.charAt(31) == '1') {
            if (dist31 == 0) {
                return Direction.WEST;}
            return backtrack31();
        }
        if (seen.charAt(22) != '1') return null;
        if (dist32 == dist22 - 1 && seen.charAt(32) == '1') {
            if (dist32 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack32();
        }
        return null;}
    public Direction backtrack23() {
        if (seen.charAt(23) != '1') return null;
        if (dist13 == dist23 - 1 && seen.charAt(13) == '1') {
            if (dist13 == 0) {
                return Direction.NORTHEAST;}
            return backtrack13();
        }
        if (seen.charAt(23) != '1') return null;
        if (dist14 == dist23 - 1 && seen.charAt(14) == '1') {
            if (dist14 == 0) {
                return Direction.EAST;}
            return backtrack14();
        }
        if (seen.charAt(23) != '1') return null;
        if (dist15 == dist23 - 1 && seen.charAt(15) == '1') {
            if (dist15 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack15();
        }
        if (seen.charAt(23) != '1') return null;
        if (dist22 == dist23 - 1 && seen.charAt(22) == '1') {
            if (dist22 == 0) {
                return Direction.NORTH;}
            return backtrack22();
        }
        if (seen.charAt(23) != '1') return null;
        if (dist24 == dist23 - 1 && seen.charAt(24) == '1') {
            if (dist24 == 0) {
                return Direction.SOUTH;}
            return backtrack24();
        }
        if (seen.charAt(23) != '1') return null;
        if (dist31 == dist23 - 1 && seen.charAt(31) == '1') {
            if (dist31 == 0) {
                return Direction.NORTHWEST;}
            return backtrack31();
        }
        if (seen.charAt(23) != '1') return null;
        if (dist32 == dist23 - 1 && seen.charAt(32) == '1') {
            if (dist32 == 0) {
                return Direction.WEST;}
            return backtrack32();
        }
        if (seen.charAt(23) != '1') return null;
        if (dist33 == dist23 - 1 && seen.charAt(33) == '1') {
            if (dist33 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack33();
        }
        return null;}
    public Direction backtrack24() {
        if (seen.charAt(24) != '1') return null;
        if (dist14 == dist24 - 1 && seen.charAt(14) == '1') {
            if (dist14 == 0) {
                return Direction.NORTHEAST;}
            return backtrack14();
        }
        if (seen.charAt(24) != '1') return null;
        if (dist15 == dist24 - 1 && seen.charAt(15) == '1') {
            if (dist15 == 0) {
                return Direction.EAST;}
            return backtrack15();
        }
        if (seen.charAt(24) != '1') return null;
        if (dist23 == dist24 - 1 && seen.charAt(23) == '1') {
            if (dist23 == 0) {
                return Direction.NORTH;}
            return backtrack23();
        }
        if (seen.charAt(24) != '1') return null;
        if (dist25 == dist24 - 1 && seen.charAt(25) == '1') {
            if (dist25 == 0) {
                return Direction.SOUTH;}
            return backtrack25();
        }
        if (seen.charAt(24) != '1') return null;
        if (dist32 == dist24 - 1 && seen.charAt(32) == '1') {
            if (dist32 == 0) {
                return Direction.NORTHWEST;}
            return backtrack32();
        }
        if (seen.charAt(24) != '1') return null;
        if (dist33 == dist24 - 1 && seen.charAt(33) == '1') {
            if (dist33 == 0) {
                return Direction.WEST;}
            return backtrack33();
        }
        if (seen.charAt(24) != '1') return null;
        if (dist34 == dist24 - 1 && seen.charAt(34) == '1') {
            if (dist34 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack34();
        }
        return null;}
    public Direction backtrack25() {
        if (seen.charAt(25) != '1') return null;
        if (dist15 == dist25 - 1 && seen.charAt(15) == '1') {
            if (dist15 == 0) {
                return Direction.NORTHEAST;}
            return backtrack15();
        }
        if (seen.charAt(25) != '1') return null;
        if (dist24 == dist25 - 1 && seen.charAt(24) == '1') {
            if (dist24 == 0) {
                return Direction.NORTH;}
            return backtrack24();
        }
        if (seen.charAt(25) != '1') return null;
        if (dist33 == dist25 - 1 && seen.charAt(33) == '1') {
            if (dist33 == 0) {
                return Direction.NORTHWEST;}
            return backtrack33();
        }
        if (seen.charAt(25) != '1') return null;
        if (dist34 == dist25 - 1 && seen.charAt(34) == '1') {
            if (dist34 == 0) {
                return Direction.WEST;}
            return backtrack34();
        }
        return null;}
    public Direction backtrack28() {
        if (seen.charAt(28) != '1') return null;
        if (dist19 == dist28 - 1 && seen.charAt(19) == '1') {
            if (dist19 == 0) {
                return Direction.EAST;}
            return backtrack19();
        }
        if (seen.charAt(28) != '1') return null;
        if (dist20 == dist28 - 1 && seen.charAt(20) == '1') {
            if (dist20 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack20();
        }
        if (seen.charAt(28) != '1') return null;
        if (dist29 == dist28 - 1 && seen.charAt(29) == '1') {
            if (dist29 == 0) {
                return Direction.SOUTH;}
            return backtrack29();
        }
        if (seen.charAt(28) != '1') return null;
        if (dist37 == dist28 - 1 && seen.charAt(37) == '1') {
            if (dist37 == 0) {
                return Direction.WEST;}
            return backtrack37();
        }
        if (seen.charAt(28) != '1') return null;
        if (dist38 == dist28 - 1 && seen.charAt(38) == '1') {
            if (dist38 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack38();
        }
        return null;}
    public Direction backtrack29() {
        if (seen.charAt(29) != '1') return null;
        if (dist19 == dist29 - 1 && seen.charAt(19) == '1') {
            if (dist19 == 0) {
                return Direction.NORTHEAST;}
            return backtrack19();
        }
        if (seen.charAt(29) != '1') return null;
        if (dist20 == dist29 - 1 && seen.charAt(20) == '1') {
            if (dist20 == 0) {
                return Direction.EAST;}
            return backtrack20();
        }
        if (seen.charAt(29) != '1') return null;
        if (dist21 == dist29 - 1 && seen.charAt(21) == '1') {
            if (dist21 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack21();
        }
        if (seen.charAt(29) != '1') return null;
        if (dist28 == dist29 - 1 && seen.charAt(28) == '1') {
            if (dist28 == 0) {
                return Direction.NORTH;}
            return backtrack28();
        }
        if (seen.charAt(29) != '1') return null;
        if (dist30 == dist29 - 1 && seen.charAt(30) == '1') {
            if (dist30 == 0) {
                return Direction.SOUTH;}
            return backtrack30();
        }
        if (seen.charAt(29) != '1') return null;
        if (dist37 == dist29 - 1 && seen.charAt(37) == '1') {
            if (dist37 == 0) {
                return Direction.NORTHWEST;}
            return backtrack37();
        }
        if (seen.charAt(29) != '1') return null;
        if (dist38 == dist29 - 1 && seen.charAt(38) == '1') {
            if (dist38 == 0) {
                return Direction.WEST;}
            return backtrack38();
        }
        if (seen.charAt(29) != '1') return null;
        if (dist39 == dist29 - 1 && seen.charAt(39) == '1') {
            if (dist39 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack39();
        }
        return null;}
    public Direction backtrack30() {
        if (seen.charAt(30) != '1') return null;
        if (dist20 == dist30 - 1 && seen.charAt(20) == '1') {
            if (dist20 == 0) {
                return Direction.NORTHEAST;}
            return backtrack20();
        }
        if (seen.charAt(30) != '1') return null;
        if (dist21 == dist30 - 1 && seen.charAt(21) == '1') {
            if (dist21 == 0) {
                return Direction.EAST;}
            return backtrack21();
        }
        if (seen.charAt(30) != '1') return null;
        if (dist22 == dist30 - 1 && seen.charAt(22) == '1') {
            if (dist22 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack22();
        }
        if (seen.charAt(30) != '1') return null;
        if (dist29 == dist30 - 1 && seen.charAt(29) == '1') {
            if (dist29 == 0) {
                return Direction.NORTH;}
            return backtrack29();
        }
        if (seen.charAt(30) != '1') return null;
        if (dist31 == dist30 - 1 && seen.charAt(31) == '1') {
            if (dist31 == 0) {
                return Direction.SOUTH;}
            return backtrack31();
        }
        if (seen.charAt(30) != '1') return null;
        if (dist38 == dist30 - 1 && seen.charAt(38) == '1') {
            if (dist38 == 0) {
                return Direction.NORTHWEST;}
            return backtrack38();
        }
        if (seen.charAt(30) != '1') return null;
        if (dist39 == dist30 - 1 && seen.charAt(39) == '1') {
            if (dist39 == 0) {
                return Direction.WEST;}
            return backtrack39();
        }
        if (seen.charAt(30) != '1') return null;
        if (dist40 == dist30 - 1 && seen.charAt(40) == '1') {
            if (dist40 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack40();
        }
        return null;}
    public Direction backtrack31() {
        if (seen.charAt(31) != '1') return null;
        if (dist21 == dist31 - 1 && seen.charAt(21) == '1') {
            if (dist21 == 0) {
                return Direction.NORTHEAST;}
            return backtrack21();
        }
        if (seen.charAt(31) != '1') return null;
        if (dist22 == dist31 - 1 && seen.charAt(22) == '1') {
            if (dist22 == 0) {
                return Direction.EAST;}
            return backtrack22();
        }
        if (seen.charAt(31) != '1') return null;
        if (dist23 == dist31 - 1 && seen.charAt(23) == '1') {
            if (dist23 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack23();
        }
        if (seen.charAt(31) != '1') return null;
        if (dist30 == dist31 - 1 && seen.charAt(30) == '1') {
            if (dist30 == 0) {
                return Direction.NORTH;}
            return backtrack30();
        }
        if (seen.charAt(31) != '1') return null;
        if (dist32 == dist31 - 1 && seen.charAt(32) == '1') {
            if (dist32 == 0) {
                return Direction.SOUTH;}
            return backtrack32();
        }
        if (seen.charAt(31) != '1') return null;
        if (dist39 == dist31 - 1 && seen.charAt(39) == '1') {
            if (dist39 == 0) {
                return Direction.NORTHWEST;}
            return backtrack39();
        }
        if (seen.charAt(31) != '1') return null;
        if (dist40 == dist31 - 1 && seen.charAt(40) == '1') {
            if (dist40 == 0) {
                return Direction.WEST;}
            return backtrack40();
        }
        if (seen.charAt(31) != '1') return null;
        if (dist41 == dist31 - 1 && seen.charAt(41) == '1') {
            if (dist41 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack41();
        }
        return null;}
    public Direction backtrack32() {
        if (seen.charAt(32) != '1') return null;
        if (dist22 == dist32 - 1 && seen.charAt(22) == '1') {
            if (dist22 == 0) {
                return Direction.NORTHEAST;}
            return backtrack22();
        }
        if (seen.charAt(32) != '1') return null;
        if (dist23 == dist32 - 1 && seen.charAt(23) == '1') {
            if (dist23 == 0) {
                return Direction.EAST;}
            return backtrack23();
        }
        if (seen.charAt(32) != '1') return null;
        if (dist24 == dist32 - 1 && seen.charAt(24) == '1') {
            if (dist24 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack24();
        }
        if (seen.charAt(32) != '1') return null;
        if (dist31 == dist32 - 1 && seen.charAt(31) == '1') {
            if (dist31 == 0) {
                return Direction.NORTH;}
            return backtrack31();
        }
        if (seen.charAt(32) != '1') return null;
        if (dist33 == dist32 - 1 && seen.charAt(33) == '1') {
            if (dist33 == 0) {
                return Direction.SOUTH;}
            return backtrack33();
        }
        if (seen.charAt(32) != '1') return null;
        if (dist40 == dist32 - 1 && seen.charAt(40) == '1') {
            if (dist40 == 0) {
                return Direction.NORTHWEST;}
            return backtrack40();
        }
        if (seen.charAt(32) != '1') return null;
        if (dist41 == dist32 - 1 && seen.charAt(41) == '1') {
            if (dist41 == 0) {
                return Direction.WEST;}
            return backtrack41();
        }
        if (seen.charAt(32) != '1') return null;
        if (dist42 == dist32 - 1 && seen.charAt(42) == '1') {
            if (dist42 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack42();
        }
        return null;}
    public Direction backtrack33() {
        if (seen.charAt(33) != '1') return null;
        if (dist23 == dist33 - 1 && seen.charAt(23) == '1') {
            if (dist23 == 0) {
                return Direction.NORTHEAST;}
            return backtrack23();
        }
        if (seen.charAt(33) != '1') return null;
        if (dist24 == dist33 - 1 && seen.charAt(24) == '1') {
            if (dist24 == 0) {
                return Direction.EAST;}
            return backtrack24();
        }
        if (seen.charAt(33) != '1') return null;
        if (dist25 == dist33 - 1 && seen.charAt(25) == '1') {
            if (dist25 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack25();
        }
        if (seen.charAt(33) != '1') return null;
        if (dist32 == dist33 - 1 && seen.charAt(32) == '1') {
            if (dist32 == 0) {
                return Direction.NORTH;}
            return backtrack32();
        }
        if (seen.charAt(33) != '1') return null;
        if (dist34 == dist33 - 1 && seen.charAt(34) == '1') {
            if (dist34 == 0) {
                return Direction.SOUTH;}
            return backtrack34();
        }
        if (seen.charAt(33) != '1') return null;
        if (dist41 == dist33 - 1 && seen.charAt(41) == '1') {
            if (dist41 == 0) {
                return Direction.NORTHWEST;}
            return backtrack41();
        }
        if (seen.charAt(33) != '1') return null;
        if (dist42 == dist33 - 1 && seen.charAt(42) == '1') {
            if (dist42 == 0) {
                return Direction.WEST;}
            return backtrack42();
        }
        if (seen.charAt(33) != '1') return null;
        if (dist43 == dist33 - 1 && seen.charAt(43) == '1') {
            if (dist43 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack43();
        }
        return null;}
    public Direction backtrack34() {
        if (seen.charAt(34) != '1') return null;
        if (dist24 == dist34 - 1 && seen.charAt(24) == '1') {
            if (dist24 == 0) {
                return Direction.NORTHEAST;}
            return backtrack24();
        }
        if (seen.charAt(34) != '1') return null;
        if (dist25 == dist34 - 1 && seen.charAt(25) == '1') {
            if (dist25 == 0) {
                return Direction.EAST;}
            return backtrack25();
        }
        if (seen.charAt(34) != '1') return null;
        if (dist33 == dist34 - 1 && seen.charAt(33) == '1') {
            if (dist33 == 0) {
                return Direction.NORTH;}
            return backtrack33();
        }
        if (seen.charAt(34) != '1') return null;
        if (dist42 == dist34 - 1 && seen.charAt(42) == '1') {
            if (dist42 == 0) {
                return Direction.NORTHWEST;}
            return backtrack42();
        }
        if (seen.charAt(34) != '1') return null;
        if (dist43 == dist34 - 1 && seen.charAt(43) == '1') {
            if (dist43 == 0) {
                return Direction.WEST;}
            return backtrack43();
        }
        return null;}
    public Direction backtrack37() {
        if (seen.charAt(37) != '1') return null;
        if (dist28 == dist37 - 1 && seen.charAt(28) == '1') {
            if (dist28 == 0) {
                return Direction.EAST;}
            return backtrack28();
        }
        if (seen.charAt(37) != '1') return null;
        if (dist29 == dist37 - 1 && seen.charAt(29) == '1') {
            if (dist29 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack29();
        }
        if (seen.charAt(37) != '1') return null;
        if (dist38 == dist37 - 1 && seen.charAt(38) == '1') {
            if (dist38 == 0) {
                return Direction.SOUTH;}
            return backtrack38();
        }
        if (seen.charAt(37) != '1') return null;
        if (dist46 == dist37 - 1 && seen.charAt(46) == '1') {
            if (dist46 == 0) {
                return Direction.WEST;}
            return backtrack46();
        }
        if (seen.charAt(37) != '1') return null;
        if (dist47 == dist37 - 1 && seen.charAt(47) == '1') {
            if (dist47 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack47();
        }
        return null;}
    public Direction backtrack38() {
        if (seen.charAt(38) != '1') return null;
        if (dist28 == dist38 - 1 && seen.charAt(28) == '1') {
            if (dist28 == 0) {
                return Direction.NORTHEAST;}
            return backtrack28();
        }
        if (seen.charAt(38) != '1') return null;
        if (dist29 == dist38 - 1 && seen.charAt(29) == '1') {
            if (dist29 == 0) {
                return Direction.EAST;}
            return backtrack29();
        }
        if (seen.charAt(38) != '1') return null;
        if (dist30 == dist38 - 1 && seen.charAt(30) == '1') {
            if (dist30 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack30();
        }
        if (seen.charAt(38) != '1') return null;
        if (dist37 == dist38 - 1 && seen.charAt(37) == '1') {
            if (dist37 == 0) {
                return Direction.NORTH;}
            return backtrack37();
        }
        if (seen.charAt(38) != '1') return null;
        if (dist39 == dist38 - 1 && seen.charAt(39) == '1') {
            if (dist39 == 0) {
                return Direction.SOUTH;}
            return backtrack39();
        }
        if (seen.charAt(38) != '1') return null;
        if (dist46 == dist38 - 1 && seen.charAt(46) == '1') {
            if (dist46 == 0) {
                return Direction.NORTHWEST;}
            return backtrack46();
        }
        if (seen.charAt(38) != '1') return null;
        if (dist47 == dist38 - 1 && seen.charAt(47) == '1') {
            if (dist47 == 0) {
                return Direction.WEST;}
            return backtrack47();
        }
        if (seen.charAt(38) != '1') return null;
        if (dist48 == dist38 - 1 && seen.charAt(48) == '1') {
            if (dist48 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack48();
        }
        return null;}
    public Direction backtrack39() {
        if (seen.charAt(39) != '1') return null;
        if (dist29 == dist39 - 1 && seen.charAt(29) == '1') {
            if (dist29 == 0) {
                return Direction.NORTHEAST;}
            return backtrack29();
        }
        if (seen.charAt(39) != '1') return null;
        if (dist30 == dist39 - 1 && seen.charAt(30) == '1') {
            if (dist30 == 0) {
                return Direction.EAST;}
            return backtrack30();
        }
        if (seen.charAt(39) != '1') return null;
        if (dist31 == dist39 - 1 && seen.charAt(31) == '1') {
            if (dist31 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack31();
        }
        if (seen.charAt(39) != '1') return null;
        if (dist38 == dist39 - 1 && seen.charAt(38) == '1') {
            if (dist38 == 0) {
                return Direction.NORTH;}
            return backtrack38();
        }
        if (seen.charAt(39) != '1') return null;
        if (dist40 == dist39 - 1 && seen.charAt(40) == '1') {
            if (dist40 == 0) {
                return Direction.SOUTH;}
            return backtrack40();
        }
        if (seen.charAt(39) != '1') return null;
        if (dist47 == dist39 - 1 && seen.charAt(47) == '1') {
            if (dist47 == 0) {
                return Direction.NORTHWEST;}
            return backtrack47();
        }
        if (seen.charAt(39) != '1') return null;
        if (dist48 == dist39 - 1 && seen.charAt(48) == '1') {
            if (dist48 == 0) {
                return Direction.WEST;}
            return backtrack48();
        }
        if (seen.charAt(39) != '1') return null;
        if (dist49 == dist39 - 1 && seen.charAt(49) == '1') {
            if (dist49 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack49();
        }
        return null;}
    public Direction backtrack40() {
        if (seen.charAt(40) != '1') return null;
        if (dist30 == dist40 - 1 && seen.charAt(30) == '1') {
            if (dist30 == 0) {
                return Direction.NORTHEAST;}
            return backtrack30();
        }
        if (seen.charAt(40) != '1') return null;
        if (dist31 == dist40 - 1 && seen.charAt(31) == '1') {
            if (dist31 == 0) {
                return Direction.EAST;}
            return backtrack31();
        }
        if (seen.charAt(40) != '1') return null;
        if (dist32 == dist40 - 1 && seen.charAt(32) == '1') {
            if (dist32 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack32();
        }
        if (seen.charAt(40) != '1') return null;
        if (dist39 == dist40 - 1 && seen.charAt(39) == '1') {
            if (dist39 == 0) {
                return Direction.NORTH;}
            return backtrack39();
        }
        if (seen.charAt(40) != '1') return null;
        if (dist41 == dist40 - 1 && seen.charAt(41) == '1') {
            if (dist41 == 0) {
                return Direction.SOUTH;}
            return backtrack41();
        }
        if (seen.charAt(40) != '1') return null;
        if (dist48 == dist40 - 1 && seen.charAt(48) == '1') {
            if (dist48 == 0) {
                return Direction.NORTHWEST;}
            return backtrack48();
        }
        if (seen.charAt(40) != '1') return null;
        if (dist49 == dist40 - 1 && seen.charAt(49) == '1') {
            if (dist49 == 0) {
                return Direction.WEST;}
            return backtrack49();
        }
        if (seen.charAt(40) != '1') return null;
        if (dist50 == dist40 - 1 && seen.charAt(50) == '1') {
            if (dist50 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack50();
        }
        return null;}
    public Direction backtrack41() {
        if (seen.charAt(41) != '1') return null;
        if (dist31 == dist41 - 1 && seen.charAt(31) == '1') {
            if (dist31 == 0) {
                return Direction.NORTHEAST;}
            return backtrack31();
        }
        if (seen.charAt(41) != '1') return null;
        if (dist32 == dist41 - 1 && seen.charAt(32) == '1') {
            if (dist32 == 0) {
                return Direction.EAST;}
            return backtrack32();
        }
        if (seen.charAt(41) != '1') return null;
        if (dist33 == dist41 - 1 && seen.charAt(33) == '1') {
            if (dist33 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack33();
        }
        if (seen.charAt(41) != '1') return null;
        if (dist40 == dist41 - 1 && seen.charAt(40) == '1') {
            if (dist40 == 0) {
                return Direction.NORTH;}
            return backtrack40();
        }
        if (seen.charAt(41) != '1') return null;
        if (dist42 == dist41 - 1 && seen.charAt(42) == '1') {
            if (dist42 == 0) {
                return Direction.SOUTH;}
            return backtrack42();
        }
        if (seen.charAt(41) != '1') return null;
        if (dist49 == dist41 - 1 && seen.charAt(49) == '1') {
            if (dist49 == 0) {
                return Direction.NORTHWEST;}
            return backtrack49();
        }
        if (seen.charAt(41) != '1') return null;
        if (dist50 == dist41 - 1 && seen.charAt(50) == '1') {
            if (dist50 == 0) {
                return Direction.WEST;}
            return backtrack50();
        }
        if (seen.charAt(41) != '1') return null;
        if (dist51 == dist41 - 1 && seen.charAt(51) == '1') {
            if (dist51 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack51();
        }
        return null;}
    public Direction backtrack42() {
        if (seen.charAt(42) != '1') return null;
        if (dist32 == dist42 - 1 && seen.charAt(32) == '1') {
            if (dist32 == 0) {
                return Direction.NORTHEAST;}
            return backtrack32();
        }
        if (seen.charAt(42) != '1') return null;
        if (dist33 == dist42 - 1 && seen.charAt(33) == '1') {
            if (dist33 == 0) {
                return Direction.EAST;}
            return backtrack33();
        }
        if (seen.charAt(42) != '1') return null;
        if (dist34 == dist42 - 1 && seen.charAt(34) == '1') {
            if (dist34 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack34();
        }
        if (seen.charAt(42) != '1') return null;
        if (dist41 == dist42 - 1 && seen.charAt(41) == '1') {
            if (dist41 == 0) {
                return Direction.NORTH;}
            return backtrack41();
        }
        if (seen.charAt(42) != '1') return null;
        if (dist43 == dist42 - 1 && seen.charAt(43) == '1') {
            if (dist43 == 0) {
                return Direction.SOUTH;}
            return backtrack43();
        }
        if (seen.charAt(42) != '1') return null;
        if (dist50 == dist42 - 1 && seen.charAt(50) == '1') {
            if (dist50 == 0) {
                return Direction.NORTHWEST;}
            return backtrack50();
        }
        if (seen.charAt(42) != '1') return null;
        if (dist51 == dist42 - 1 && seen.charAt(51) == '1') {
            if (dist51 == 0) {
                return Direction.WEST;}
            return backtrack51();
        }
        if (seen.charAt(42) != '1') return null;
        if (dist52 == dist42 - 1 && seen.charAt(52) == '1') {
            if (dist52 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack52();
        }
        return null;}
    public Direction backtrack43() {
        if (seen.charAt(43) != '1') return null;
        if (dist33 == dist43 - 1 && seen.charAt(33) == '1') {
            if (dist33 == 0) {
                return Direction.NORTHEAST;}
            return backtrack33();
        }
        if (seen.charAt(43) != '1') return null;
        if (dist34 == dist43 - 1 && seen.charAt(34) == '1') {
            if (dist34 == 0) {
                return Direction.EAST;}
            return backtrack34();
        }
        if (seen.charAt(43) != '1') return null;
        if (dist42 == dist43 - 1 && seen.charAt(42) == '1') {
            if (dist42 == 0) {
                return Direction.NORTH;}
            return backtrack42();
        }
        if (seen.charAt(43) != '1') return null;
        if (dist51 == dist43 - 1 && seen.charAt(51) == '1') {
            if (dist51 == 0) {
                return Direction.NORTHWEST;}
            return backtrack51();
        }
        if (seen.charAt(43) != '1') return null;
        if (dist52 == dist43 - 1 && seen.charAt(52) == '1') {
            if (dist52 == 0) {
                return Direction.WEST;}
            return backtrack52();
        }
        return null;}
    public Direction backtrack46() {
        if (seen.charAt(46) != '1') return null;
        if (dist37 == dist46 - 1 && seen.charAt(37) == '1') {
            if (dist37 == 0) {
                return Direction.EAST;}
            return backtrack37();
        }
        if (seen.charAt(46) != '1') return null;
        if (dist38 == dist46 - 1 && seen.charAt(38) == '1') {
            if (dist38 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack38();
        }
        if (seen.charAt(46) != '1') return null;
        if (dist47 == dist46 - 1 && seen.charAt(47) == '1') {
            if (dist47 == 0) {
                return Direction.SOUTH;}
            return backtrack47();
        }
        if (seen.charAt(46) != '1') return null;
        if (dist55 == dist46 - 1 && seen.charAt(55) == '1') {
            if (dist55 == 0) {
                return Direction.WEST;}
            return backtrack55();
        }
        if (seen.charAt(46) != '1') return null;
        if (dist56 == dist46 - 1 && seen.charAt(56) == '1') {
            if (dist56 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack56();
        }
        return null;}
    public Direction backtrack47() {
        if (seen.charAt(47) != '1') return null;
        if (dist37 == dist47 - 1 && seen.charAt(37) == '1') {
            if (dist37 == 0) {
                return Direction.NORTHEAST;}
            return backtrack37();
        }
        if (seen.charAt(47) != '1') return null;
        if (dist38 == dist47 - 1 && seen.charAt(38) == '1') {
            if (dist38 == 0) {
                return Direction.EAST;}
            return backtrack38();
        }
        if (seen.charAt(47) != '1') return null;
        if (dist39 == dist47 - 1 && seen.charAt(39) == '1') {
            if (dist39 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack39();
        }
        if (seen.charAt(47) != '1') return null;
        if (dist46 == dist47 - 1 && seen.charAt(46) == '1') {
            if (dist46 == 0) {
                return Direction.NORTH;}
            return backtrack46();
        }
        if (seen.charAt(47) != '1') return null;
        if (dist48 == dist47 - 1 && seen.charAt(48) == '1') {
            if (dist48 == 0) {
                return Direction.SOUTH;}
            return backtrack48();
        }
        if (seen.charAt(47) != '1') return null;
        if (dist55 == dist47 - 1 && seen.charAt(55) == '1') {
            if (dist55 == 0) {
                return Direction.NORTHWEST;}
            return backtrack55();
        }
        if (seen.charAt(47) != '1') return null;
        if (dist56 == dist47 - 1 && seen.charAt(56) == '1') {
            if (dist56 == 0) {
                return Direction.WEST;}
            return backtrack56();
        }
        if (seen.charAt(47) != '1') return null;
        if (dist57 == dist47 - 1 && seen.charAt(57) == '1') {
            if (dist57 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack57();
        }
        return null;}
    public Direction backtrack48() {
        if (seen.charAt(48) != '1') return null;
        if (dist38 == dist48 - 1 && seen.charAt(38) == '1') {
            if (dist38 == 0) {
                return Direction.NORTHEAST;}
            return backtrack38();
        }
        if (seen.charAt(48) != '1') return null;
        if (dist39 == dist48 - 1 && seen.charAt(39) == '1') {
            if (dist39 == 0) {
                return Direction.EAST;}
            return backtrack39();
        }
        if (seen.charAt(48) != '1') return null;
        if (dist40 == dist48 - 1 && seen.charAt(40) == '1') {
            if (dist40 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack40();
        }
        if (seen.charAt(48) != '1') return null;
        if (dist47 == dist48 - 1 && seen.charAt(47) == '1') {
            if (dist47 == 0) {
                return Direction.NORTH;}
            return backtrack47();
        }
        if (seen.charAt(48) != '1') return null;
        if (dist49 == dist48 - 1 && seen.charAt(49) == '1') {
            if (dist49 == 0) {
                return Direction.SOUTH;}
            return backtrack49();
        }
        if (seen.charAt(48) != '1') return null;
        if (dist56 == dist48 - 1 && seen.charAt(56) == '1') {
            if (dist56 == 0) {
                return Direction.NORTHWEST;}
            return backtrack56();
        }
        if (seen.charAt(48) != '1') return null;
        if (dist57 == dist48 - 1 && seen.charAt(57) == '1') {
            if (dist57 == 0) {
                return Direction.WEST;}
            return backtrack57();
        }
        if (seen.charAt(48) != '1') return null;
        if (dist58 == dist48 - 1 && seen.charAt(58) == '1') {
            if (dist58 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack58();
        }
        return null;}
    public Direction backtrack49() {
        if (seen.charAt(49) != '1') return null;
        if (dist39 == dist49 - 1 && seen.charAt(39) == '1') {
            if (dist39 == 0) {
                return Direction.NORTHEAST;}
            return backtrack39();
        }
        if (seen.charAt(49) != '1') return null;
        if (dist40 == dist49 - 1 && seen.charAt(40) == '1') {
            if (dist40 == 0) {
                return Direction.EAST;}
            return backtrack40();
        }
        if (seen.charAt(49) != '1') return null;
        if (dist41 == dist49 - 1 && seen.charAt(41) == '1') {
            if (dist41 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack41();
        }
        if (seen.charAt(49) != '1') return null;
        if (dist48 == dist49 - 1 && seen.charAt(48) == '1') {
            if (dist48 == 0) {
                return Direction.NORTH;}
            return backtrack48();
        }
        if (seen.charAt(49) != '1') return null;
        if (dist50 == dist49 - 1 && seen.charAt(50) == '1') {
            if (dist50 == 0) {
                return Direction.SOUTH;}
            return backtrack50();
        }
        if (seen.charAt(49) != '1') return null;
        if (dist57 == dist49 - 1 && seen.charAt(57) == '1') {
            if (dist57 == 0) {
                return Direction.NORTHWEST;}
            return backtrack57();
        }
        if (seen.charAt(49) != '1') return null;
        if (dist58 == dist49 - 1 && seen.charAt(58) == '1') {
            if (dist58 == 0) {
                return Direction.WEST;}
            return backtrack58();
        }
        if (seen.charAt(49) != '1') return null;
        if (dist59 == dist49 - 1 && seen.charAt(59) == '1') {
            if (dist59 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack59();
        }
        return null;}
    public Direction backtrack50() {
        if (seen.charAt(50) != '1') return null;
        if (dist40 == dist50 - 1 && seen.charAt(40) == '1') {
            if (dist40 == 0) {
                return Direction.NORTHEAST;}
            return backtrack40();
        }
        if (seen.charAt(50) != '1') return null;
        if (dist41 == dist50 - 1 && seen.charAt(41) == '1') {
            if (dist41 == 0) {
                return Direction.EAST;}
            return backtrack41();
        }
        if (seen.charAt(50) != '1') return null;
        if (dist42 == dist50 - 1 && seen.charAt(42) == '1') {
            if (dist42 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack42();
        }
        if (seen.charAt(50) != '1') return null;
        if (dist49 == dist50 - 1 && seen.charAt(49) == '1') {
            if (dist49 == 0) {
                return Direction.NORTH;}
            return backtrack49();
        }
        if (seen.charAt(50) != '1') return null;
        if (dist51 == dist50 - 1 && seen.charAt(51) == '1') {
            if (dist51 == 0) {
                return Direction.SOUTH;}
            return backtrack51();
        }
        if (seen.charAt(50) != '1') return null;
        if (dist58 == dist50 - 1 && seen.charAt(58) == '1') {
            if (dist58 == 0) {
                return Direction.NORTHWEST;}
            return backtrack58();
        }
        if (seen.charAt(50) != '1') return null;
        if (dist59 == dist50 - 1 && seen.charAt(59) == '1') {
            if (dist59 == 0) {
                return Direction.WEST;}
            return backtrack59();
        }
        if (seen.charAt(50) != '1') return null;
        if (dist60 == dist50 - 1 && seen.charAt(60) == '1') {
            if (dist60 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack60();
        }
        return null;}
    public Direction backtrack51() {
        if (seen.charAt(51) != '1') return null;
        if (dist41 == dist51 - 1 && seen.charAt(41) == '1') {
            if (dist41 == 0) {
                return Direction.NORTHEAST;}
            return backtrack41();
        }
        if (seen.charAt(51) != '1') return null;
        if (dist42 == dist51 - 1 && seen.charAt(42) == '1') {
            if (dist42 == 0) {
                return Direction.EAST;}
            return backtrack42();
        }
        if (seen.charAt(51) != '1') return null;
        if (dist43 == dist51 - 1 && seen.charAt(43) == '1') {
            if (dist43 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack43();
        }
        if (seen.charAt(51) != '1') return null;
        if (dist50 == dist51 - 1 && seen.charAt(50) == '1') {
            if (dist50 == 0) {
                return Direction.NORTH;}
            return backtrack50();
        }
        if (seen.charAt(51) != '1') return null;
        if (dist52 == dist51 - 1 && seen.charAt(52) == '1') {
            if (dist52 == 0) {
                return Direction.SOUTH;}
            return backtrack52();
        }
        if (seen.charAt(51) != '1') return null;
        if (dist59 == dist51 - 1 && seen.charAt(59) == '1') {
            if (dist59 == 0) {
                return Direction.NORTHWEST;}
            return backtrack59();
        }
        if (seen.charAt(51) != '1') return null;
        if (dist60 == dist51 - 1 && seen.charAt(60) == '1') {
            if (dist60 == 0) {
                return Direction.WEST;}
            return backtrack60();
        }
        if (seen.charAt(51) != '1') return null;
        if (dist61 == dist51 - 1 && seen.charAt(61) == '1') {
            if (dist61 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack61();
        }
        return null;}
    public Direction backtrack52() {
        if (seen.charAt(52) != '1') return null;
        if (dist42 == dist52 - 1 && seen.charAt(42) == '1') {
            if (dist42 == 0) {
                return Direction.NORTHEAST;}
            return backtrack42();
        }
        if (seen.charAt(52) != '1') return null;
        if (dist43 == dist52 - 1 && seen.charAt(43) == '1') {
            if (dist43 == 0) {
                return Direction.EAST;}
            return backtrack43();
        }
        if (seen.charAt(52) != '1') return null;
        if (dist51 == dist52 - 1 && seen.charAt(51) == '1') {
            if (dist51 == 0) {
                return Direction.NORTH;}
            return backtrack51();
        }
        if (seen.charAt(52) != '1') return null;
        if (dist60 == dist52 - 1 && seen.charAt(60) == '1') {
            if (dist60 == 0) {
                return Direction.NORTHWEST;}
            return backtrack60();
        }
        if (seen.charAt(52) != '1') return null;
        if (dist61 == dist52 - 1 && seen.charAt(61) == '1') {
            if (dist61 == 0) {
                return Direction.WEST;}
            return backtrack61();
        }
        return null;}
    public Direction backtrack55() {
        if (seen.charAt(55) != '1') return null;
        if (dist46 == dist55 - 1 && seen.charAt(46) == '1') {
            if (dist46 == 0) {
                return Direction.EAST;}
            return backtrack46();
        }
        if (seen.charAt(55) != '1') return null;
        if (dist47 == dist55 - 1 && seen.charAt(47) == '1') {
            if (dist47 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack47();
        }
        if (seen.charAt(55) != '1') return null;
        if (dist56 == dist55 - 1 && seen.charAt(56) == '1') {
            if (dist56 == 0) {
                return Direction.SOUTH;}
            return backtrack56();
        }
        if (seen.charAt(55) != '1') return null;
        if (dist65 == dist55 - 1 && seen.charAt(65) == '1') {
            if (dist65 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack65();
        }
        return null;}
    public Direction backtrack56() {
        if (seen.charAt(56) != '1') return null;
        if (dist46 == dist56 - 1 && seen.charAt(46) == '1') {
            if (dist46 == 0) {
                return Direction.NORTHEAST;}
            return backtrack46();
        }
        if (seen.charAt(56) != '1') return null;
        if (dist47 == dist56 - 1 && seen.charAt(47) == '1') {
            if (dist47 == 0) {
                return Direction.EAST;}
            return backtrack47();
        }
        if (seen.charAt(56) != '1') return null;
        if (dist48 == dist56 - 1 && seen.charAt(48) == '1') {
            if (dist48 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack48();
        }
        if (seen.charAt(56) != '1') return null;
        if (dist55 == dist56 - 1 && seen.charAt(55) == '1') {
            if (dist55 == 0) {
                return Direction.NORTH;}
            return backtrack55();
        }
        if (seen.charAt(56) != '1') return null;
        if (dist57 == dist56 - 1 && seen.charAt(57) == '1') {
            if (dist57 == 0) {
                return Direction.SOUTH;}
            return backtrack57();
        }
        if (seen.charAt(56) != '1') return null;
        if (dist65 == dist56 - 1 && seen.charAt(65) == '1') {
            if (dist65 == 0) {
                return Direction.WEST;}
            return backtrack65();
        }
        if (seen.charAt(56) != '1') return null;
        if (dist66 == dist56 - 1 && seen.charAt(66) == '1') {
            if (dist66 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack66();
        }
        return null;}
    public Direction backtrack57() {
        if (seen.charAt(57) != '1') return null;
        if (dist47 == dist57 - 1 && seen.charAt(47) == '1') {
            if (dist47 == 0) {
                return Direction.NORTHEAST;}
            return backtrack47();
        }
        if (seen.charAt(57) != '1') return null;
        if (dist48 == dist57 - 1 && seen.charAt(48) == '1') {
            if (dist48 == 0) {
                return Direction.EAST;}
            return backtrack48();
        }
        if (seen.charAt(57) != '1') return null;
        if (dist49 == dist57 - 1 && seen.charAt(49) == '1') {
            if (dist49 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack49();
        }
        if (seen.charAt(57) != '1') return null;
        if (dist56 == dist57 - 1 && seen.charAt(56) == '1') {
            if (dist56 == 0) {
                return Direction.NORTH;}
            return backtrack56();
        }
        if (seen.charAt(57) != '1') return null;
        if (dist58 == dist57 - 1 && seen.charAt(58) == '1') {
            if (dist58 == 0) {
                return Direction.SOUTH;}
            return backtrack58();
        }
        if (seen.charAt(57) != '1') return null;
        if (dist65 == dist57 - 1 && seen.charAt(65) == '1') {
            if (dist65 == 0) {
                return Direction.NORTHWEST;}
            return backtrack65();
        }
        if (seen.charAt(57) != '1') return null;
        if (dist66 == dist57 - 1 && seen.charAt(66) == '1') {
            if (dist66 == 0) {
                return Direction.WEST;}
            return backtrack66();
        }
        if (seen.charAt(57) != '1') return null;
        if (dist67 == dist57 - 1 && seen.charAt(67) == '1') {
            if (dist67 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack67();
        }
        return null;}
    public Direction backtrack58() {
        if (seen.charAt(58) != '1') return null;
        if (dist48 == dist58 - 1 && seen.charAt(48) == '1') {
            if (dist48 == 0) {
                return Direction.NORTHEAST;}
            return backtrack48();
        }
        if (seen.charAt(58) != '1') return null;
        if (dist49 == dist58 - 1 && seen.charAt(49) == '1') {
            if (dist49 == 0) {
                return Direction.EAST;}
            return backtrack49();
        }
        if (seen.charAt(58) != '1') return null;
        if (dist50 == dist58 - 1 && seen.charAt(50) == '1') {
            if (dist50 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack50();
        }
        if (seen.charAt(58) != '1') return null;
        if (dist57 == dist58 - 1 && seen.charAt(57) == '1') {
            if (dist57 == 0) {
                return Direction.NORTH;}
            return backtrack57();
        }
        if (seen.charAt(58) != '1') return null;
        if (dist59 == dist58 - 1 && seen.charAt(59) == '1') {
            if (dist59 == 0) {
                return Direction.SOUTH;}
            return backtrack59();
        }
        if (seen.charAt(58) != '1') return null;
        if (dist66 == dist58 - 1 && seen.charAt(66) == '1') {
            if (dist66 == 0) {
                return Direction.NORTHWEST;}
            return backtrack66();
        }
        if (seen.charAt(58) != '1') return null;
        if (dist67 == dist58 - 1 && seen.charAt(67) == '1') {
            if (dist67 == 0) {
                return Direction.WEST;}
            return backtrack67();
        }
        if (seen.charAt(58) != '1') return null;
        if (dist68 == dist58 - 1 && seen.charAt(68) == '1') {
            if (dist68 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack68();
        }
        return null;}
    public Direction backtrack59() {
        if (seen.charAt(59) != '1') return null;
        if (dist49 == dist59 - 1 && seen.charAt(49) == '1') {
            if (dist49 == 0) {
                return Direction.NORTHEAST;}
            return backtrack49();
        }
        if (seen.charAt(59) != '1') return null;
        if (dist50 == dist59 - 1 && seen.charAt(50) == '1') {
            if (dist50 == 0) {
                return Direction.EAST;}
            return backtrack50();
        }
        if (seen.charAt(59) != '1') return null;
        if (dist51 == dist59 - 1 && seen.charAt(51) == '1') {
            if (dist51 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack51();
        }
        if (seen.charAt(59) != '1') return null;
        if (dist58 == dist59 - 1 && seen.charAt(58) == '1') {
            if (dist58 == 0) {
                return Direction.NORTH;}
            return backtrack58();
        }
        if (seen.charAt(59) != '1') return null;
        if (dist60 == dist59 - 1 && seen.charAt(60) == '1') {
            if (dist60 == 0) {
                return Direction.SOUTH;}
            return backtrack60();
        }
        if (seen.charAt(59) != '1') return null;
        if (dist67 == dist59 - 1 && seen.charAt(67) == '1') {
            if (dist67 == 0) {
                return Direction.NORTHWEST;}
            return backtrack67();
        }
        if (seen.charAt(59) != '1') return null;
        if (dist68 == dist59 - 1 && seen.charAt(68) == '1') {
            if (dist68 == 0) {
                return Direction.WEST;}
            return backtrack68();
        }
        if (seen.charAt(59) != '1') return null;
        if (dist69 == dist59 - 1 && seen.charAt(69) == '1') {
            if (dist69 == 0) {
                return Direction.SOUTHWEST;}
            return backtrack69();
        }
        return null;}
    public Direction backtrack60() {
        if (seen.charAt(60) != '1') return null;
        if (dist50 == dist60 - 1 && seen.charAt(50) == '1') {
            if (dist50 == 0) {
                return Direction.NORTHEAST;}
            return backtrack50();
        }
        if (seen.charAt(60) != '1') return null;
        if (dist51 == dist60 - 1 && seen.charAt(51) == '1') {
            if (dist51 == 0) {
                return Direction.EAST;}
            return backtrack51();
        }
        if (seen.charAt(60) != '1') return null;
        if (dist52 == dist60 - 1 && seen.charAt(52) == '1') {
            if (dist52 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack52();
        }
        if (seen.charAt(60) != '1') return null;
        if (dist59 == dist60 - 1 && seen.charAt(59) == '1') {
            if (dist59 == 0) {
                return Direction.NORTH;}
            return backtrack59();
        }
        if (seen.charAt(60) != '1') return null;
        if (dist61 == dist60 - 1 && seen.charAt(61) == '1') {
            if (dist61 == 0) {
                return Direction.SOUTH;}
            return backtrack61();
        }
        if (seen.charAt(60) != '1') return null;
        if (dist68 == dist60 - 1 && seen.charAt(68) == '1') {
            if (dist68 == 0) {
                return Direction.NORTHWEST;}
            return backtrack68();
        }
        if (seen.charAt(60) != '1') return null;
        if (dist69 == dist60 - 1 && seen.charAt(69) == '1') {
            if (dist69 == 0) {
                return Direction.WEST;}
            return backtrack69();
        }
        return null;}
    public Direction backtrack61() {
        if (seen.charAt(61) != '1') return null;
        if (dist51 == dist61 - 1 && seen.charAt(51) == '1') {
            if (dist51 == 0) {
                return Direction.NORTHEAST;}
            return backtrack51();
        }
        if (seen.charAt(61) != '1') return null;
        if (dist52 == dist61 - 1 && seen.charAt(52) == '1') {
            if (dist52 == 0) {
                return Direction.EAST;}
            return backtrack52();
        }
        if (seen.charAt(61) != '1') return null;
        if (dist60 == dist61 - 1 && seen.charAt(60) == '1') {
            if (dist60 == 0) {
                return Direction.NORTH;}
            return backtrack60();
        }
        if (seen.charAt(61) != '1') return null;
        if (dist69 == dist61 - 1 && seen.charAt(69) == '1') {
            if (dist69 == 0) {
                return Direction.NORTHWEST;}
            return backtrack69();
        }
        return null;}
    public Direction backtrack65() {
        if (seen.charAt(65) != '1') return null;
        if (dist55 == dist65 - 1 && seen.charAt(55) == '1') {
            if (dist55 == 0) {
                return Direction.NORTHEAST;}
            return backtrack55();
        }
        if (seen.charAt(65) != '1') return null;
        if (dist56 == dist65 - 1 && seen.charAt(56) == '1') {
            if (dist56 == 0) {
                return Direction.EAST;}
            return backtrack56();
        }
        if (seen.charAt(65) != '1') return null;
        if (dist57 == dist65 - 1 && seen.charAt(57) == '1') {
            if (dist57 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack57();
        }
        if (seen.charAt(65) != '1') return null;
        if (dist66 == dist65 - 1 && seen.charAt(66) == '1') {
            if (dist66 == 0) {
                return Direction.SOUTH;}
            return backtrack66();
        }
        return null;}
    public Direction backtrack66() {
        if (seen.charAt(66) != '1') return null;
        if (dist56 == dist66 - 1 && seen.charAt(56) == '1') {
            if (dist56 == 0) {
                return Direction.NORTHEAST;}
            return backtrack56();
        }
        if (seen.charAt(66) != '1') return null;
        if (dist57 == dist66 - 1 && seen.charAt(57) == '1') {
            if (dist57 == 0) {
                return Direction.EAST;}
            return backtrack57();
        }
        if (seen.charAt(66) != '1') return null;
        if (dist58 == dist66 - 1 && seen.charAt(58) == '1') {
            if (dist58 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack58();
        }
        if (seen.charAt(66) != '1') return null;
        if (dist65 == dist66 - 1 && seen.charAt(65) == '1') {
            if (dist65 == 0) {
                return Direction.NORTH;}
            return backtrack65();
        }
        if (seen.charAt(66) != '1') return null;
        if (dist67 == dist66 - 1 && seen.charAt(67) == '1') {
            if (dist67 == 0) {
                return Direction.SOUTH;}
            return backtrack67();
        }
        return null;}
    public Direction backtrack67() {
        if (seen.charAt(67) != '1') return null;
        if (dist57 == dist67 - 1 && seen.charAt(57) == '1') {
            if (dist57 == 0) {
                return Direction.NORTHEAST;}
            return backtrack57();
        }
        if (seen.charAt(67) != '1') return null;
        if (dist58 == dist67 - 1 && seen.charAt(58) == '1') {
            if (dist58 == 0) {
                return Direction.EAST;}
            return backtrack58();
        }
        if (seen.charAt(67) != '1') return null;
        if (dist59 == dist67 - 1 && seen.charAt(59) == '1') {
            if (dist59 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack59();
        }
        if (seen.charAt(67) != '1') return null;
        if (dist66 == dist67 - 1 && seen.charAt(66) == '1') {
            if (dist66 == 0) {
                return Direction.NORTH;}
            return backtrack66();
        }
        if (seen.charAt(67) != '1') return null;
        if (dist68 == dist67 - 1 && seen.charAt(68) == '1') {
            if (dist68 == 0) {
                return Direction.SOUTH;}
            return backtrack68();
        }
        return null;}
    public Direction backtrack68() {
        if (seen.charAt(68) != '1') return null;
        if (dist58 == dist68 - 1 && seen.charAt(58) == '1') {
            if (dist58 == 0) {
                return Direction.NORTHEAST;}
            return backtrack58();
        }
        if (seen.charAt(68) != '1') return null;
        if (dist59 == dist68 - 1 && seen.charAt(59) == '1') {
            if (dist59 == 0) {
                return Direction.EAST;}
            return backtrack59();
        }
        if (seen.charAt(68) != '1') return null;
        if (dist60 == dist68 - 1 && seen.charAt(60) == '1') {
            if (dist60 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack60();
        }
        if (seen.charAt(68) != '1') return null;
        if (dist67 == dist68 - 1 && seen.charAt(67) == '1') {
            if (dist67 == 0) {
                return Direction.NORTH;}
            return backtrack67();
        }
        if (seen.charAt(68) != '1') return null;
        if (dist69 == dist68 - 1 && seen.charAt(69) == '1') {
            if (dist69 == 0) {
                return Direction.SOUTH;}
            return backtrack69();
        }
        return null;}
    public Direction backtrack69() {
        if (seen.charAt(69) != '1') return null;
        if (dist59 == dist69 - 1 && seen.charAt(59) == '1') {
            if (dist59 == 0) {
                return Direction.NORTHEAST;}
            return backtrack59();
        }
        if (seen.charAt(69) != '1') return null;
        if (dist60 == dist69 - 1 && seen.charAt(60) == '1') {
            if (dist60 == 0) {
                return Direction.EAST;}
            return backtrack60();
        }
        if (seen.charAt(69) != '1') return null;
        if (dist61 == dist69 - 1 && seen.charAt(61) == '1') {
            if (dist61 == 0) {
                return Direction.SOUTHEAST;}
            return backtrack61();
        }
        if (seen.charAt(69) != '1') return null;
        if (dist68 == dist69 - 1 && seen.charAt(68) == '1') {
            if (dist68 == 0) {
                return Direction.NORTH;}
            return backtrack68();
        }
        return null;}
    public void reset() {
        Queue.reset();
        seen.replace(0, 81, "000000000000000000000000000000000000000000000000000000000000000000000000000000000");
    }

    void initBFS(MapLocation[] path, int cur) throws GameActionException {
        reset();

        MapLocation curLoc = rc.getLocation();

        //if ((currentTarget.x - curLoc.x) * (currentTarget.x - curLoc.x) + (currentTarget.y - curLoc.y) * ((currentTarget.y - curLoc.y)) > 15)
        //    return null;

        int offsetx = curLoc.x % 10, offsety = curLoc.y % 10;

        RobotInfo robotInfos[] = rc.senseNearbyRobots();
        MapLocation loc;

        for (RobotInfo robotInfo : robotInfos) {
            loc = robotInfo.getLocation();
            seen.setCharAt((loc.x - curLoc.x + 4) * 9 + (loc.y - curLoc.y + 4), '2');
        }

        for (int i = cur; i >= Math.max(0, cur - 7); i--) {
            // if dist <= 8 (robot there) conitnue
            if (rc.getLocation().distanceSquaredTo(path[cur]) <= 8 && rc.senseRobotAtLocation(path[cur]) != null) continue;
            if ((path[i].x - curLoc.x) * (path[i].x - curLoc.x) + (path[i].y - curLoc.y) * (path[i].y - curLoc.y) > 15) continue;
            validLocation.setCharAt((path[i].x % 10) * 10 + (path[i].y % 10), '1');
        }

        /*seen.setCharAt((currentTarget.x - curLoc.x + 4) * 9 + (currentTarget.y - curLoc.y + 4), '0');*/

        seen.setCharAt(40, '1');
        Queue.queuePush(40);
        dist40 = 0;

        while (!Queue.queueEmpty()) {
            int current = Queue.queuePop();
            switch(current) {
            case 11:
            if(seen.charAt(12) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(12, '1');
            Queue.queuePush(12);
            dist12 = dist11 + 1;
            }
            if(seen.charAt(19) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(19, '1');
            Queue.queuePush(19);
            dist19 = dist11 + 1;
            }
            if(seen.charAt(20) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(20, '1');
            Queue.queuePush(20);
            dist20 = dist11 + 1;
            }
            if(seen.charAt(21) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(21, '1');
            Queue.queuePush(21);
            dist21 = dist11 + 1;
            }
            break;
            case 12:
            if(seen.charAt(11) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(11, '1');
            Queue.queuePush(11);
            dist11 = dist12 + 1;
            }
            if(seen.charAt(13) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(13, '1');
            Queue.queuePush(13);
            dist13 = dist12 + 1;
            }
            if(seen.charAt(20) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(20, '1');
            Queue.queuePush(20);
            dist20 = dist12 + 1;
            }
            if(seen.charAt(21) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(21, '1');
            Queue.queuePush(21);
            dist21 = dist12 + 1;
            }
            if(seen.charAt(22) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(22, '1');
            Queue.queuePush(22);
            dist22 = dist12 + 1;
            }
            break;
            case 13:
            if(seen.charAt(12) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(12, '1');
            Queue.queuePush(12);
            dist12 = dist13 + 1;
            }
            if(seen.charAt(14) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(14, '1');
            Queue.queuePush(14);
            dist14 = dist13 + 1;
            }
            if(seen.charAt(21) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(21, '1');
            Queue.queuePush(21);
            dist21 = dist13 + 1;
            }
            if(seen.charAt(22) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(22, '1');
            Queue.queuePush(22);
            dist22 = dist13 + 1;
            }
            if(seen.charAt(23) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(23, '1');
            Queue.queuePush(23);
            dist23 = dist13 + 1;
            }
            break;
            case 14:
            if(seen.charAt(13) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(13, '1');
            Queue.queuePush(13);
            dist13 = dist14 + 1;
            }
            if(seen.charAt(15) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(15, '1');
            Queue.queuePush(15);
            dist15 = dist14 + 1;
            }
            if(seen.charAt(22) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(22, '1');
            Queue.queuePush(22);
            dist22 = dist14 + 1;
            }
            if(seen.charAt(23) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(23, '1');
            Queue.queuePush(23);
            dist23 = dist14 + 1;
            }
            if(seen.charAt(24) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(24, '1');
            Queue.queuePush(24);
            dist24 = dist14 + 1;
            }
            break;
            case 15:
            if(seen.charAt(14) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(14, '1');
            Queue.queuePush(14);
            dist14 = dist15 + 1;
            }
            if(seen.charAt(23) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(23, '1');
            Queue.queuePush(23);
            dist23 = dist15 + 1;
            }
            if(seen.charAt(24) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(24, '1');
            Queue.queuePush(24);
            dist24 = dist15 + 1;
            }
            if(seen.charAt(25) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(25, '1');
            Queue.queuePush(25);
            dist25 = dist15 + 1;
            }
            break;
            case 19:
            if(seen.charAt(11) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(11, '1');
            Queue.queuePush(11);
            dist11 = dist19 + 1;
            }
            if(seen.charAt(20) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(20, '1');
            Queue.queuePush(20);
            dist20 = dist19 + 1;
            }
            if(seen.charAt(28) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(28, '1');
            Queue.queuePush(28);
            dist28 = dist19 + 1;
            }
            if(seen.charAt(29) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(29, '1');
            Queue.queuePush(29);
            dist29 = dist19 + 1;
            }
            break;
            case 20:
            if(seen.charAt(11) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(11, '1');
            Queue.queuePush(11);
            dist11 = dist20 + 1;
            }
            if(seen.charAt(12) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(12, '1');
            Queue.queuePush(12);
            dist12 = dist20 + 1;
            }
            if(seen.charAt(19) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(19, '1');
            Queue.queuePush(19);
            dist19 = dist20 + 1;
            }
            if(seen.charAt(21) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(21, '1');
            Queue.queuePush(21);
            dist21 = dist20 + 1;
            }
            if(seen.charAt(28) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(28, '1');
            Queue.queuePush(28);
            dist28 = dist20 + 1;
            }
            if(seen.charAt(29) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(29, '1');
            Queue.queuePush(29);
            dist29 = dist20 + 1;
            }
            if(seen.charAt(30) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(30, '1');
            Queue.queuePush(30);
            dist30 = dist20 + 1;
            }
            break;
            case 21:
            if(seen.charAt(11) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(11, '1');
            Queue.queuePush(11);
            dist11 = dist21 + 1;
            }
            if(seen.charAt(12) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(12, '1');
            Queue.queuePush(12);
            dist12 = dist21 + 1;
            }
            if(seen.charAt(13) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(13, '1');
            Queue.queuePush(13);
            dist13 = dist21 + 1;
            }
            if(seen.charAt(20) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(20, '1');
            Queue.queuePush(20);
            dist20 = dist21 + 1;
            }
            if(seen.charAt(22) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(22, '1');
            Queue.queuePush(22);
            dist22 = dist21 + 1;
            }
            if(seen.charAt(29) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(29, '1');
            Queue.queuePush(29);
            dist29 = dist21 + 1;
            }
            if(seen.charAt(30) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(30, '1');
            Queue.queuePush(30);
            dist30 = dist21 + 1;
            }
            if(seen.charAt(31) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(31, '1');
            Queue.queuePush(31);
            dist31 = dist21 + 1;
            }
            break;
            case 22:
            if(seen.charAt(12) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(12, '1');
            Queue.queuePush(12);
            dist12 = dist22 + 1;
            }
            if(seen.charAt(13) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(13, '1');
            Queue.queuePush(13);
            dist13 = dist22 + 1;
            }
            if(seen.charAt(14) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(14, '1');
            Queue.queuePush(14);
            dist14 = dist22 + 1;
            }
            if(seen.charAt(21) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(21, '1');
            Queue.queuePush(21);
            dist21 = dist22 + 1;
            }
            if(seen.charAt(23) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(23, '1');
            Queue.queuePush(23);
            dist23 = dist22 + 1;
            }
            if(seen.charAt(30) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(30, '1');
            Queue.queuePush(30);
            dist30 = dist22 + 1;
            }
            if(seen.charAt(31) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(31, '1');
            Queue.queuePush(31);
            dist31 = dist22 + 1;
            }
            if(seen.charAt(32) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(32, '1');
            Queue.queuePush(32);
            dist32 = dist22 + 1;
            }
            break;
            case 23:
            if(seen.charAt(13) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(13, '1');
            Queue.queuePush(13);
            dist13 = dist23 + 1;
            }
            if(seen.charAt(14) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(14, '1');
            Queue.queuePush(14);
            dist14 = dist23 + 1;
            }
            if(seen.charAt(15) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(15, '1');
            Queue.queuePush(15);
            dist15 = dist23 + 1;
            }
            if(seen.charAt(22) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(22, '1');
            Queue.queuePush(22);
            dist22 = dist23 + 1;
            }
            if(seen.charAt(24) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(24, '1');
            Queue.queuePush(24);
            dist24 = dist23 + 1;
            }
            if(seen.charAt(31) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(31, '1');
            Queue.queuePush(31);
            dist31 = dist23 + 1;
            }
            if(seen.charAt(32) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(32, '1');
            Queue.queuePush(32);
            dist32 = dist23 + 1;
            }
            if(seen.charAt(33) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(33, '1');
            Queue.queuePush(33);
            dist33 = dist23 + 1;
            }
            break;
            case 24:
            if(seen.charAt(14) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(14, '1');
            Queue.queuePush(14);
            dist14 = dist24 + 1;
            }
            if(seen.charAt(15) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(15, '1');
            Queue.queuePush(15);
            dist15 = dist24 + 1;
            }
            if(seen.charAt(23) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(23, '1');
            Queue.queuePush(23);
            dist23 = dist24 + 1;
            }
            if(seen.charAt(25) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(25, '1');
            Queue.queuePush(25);
            dist25 = dist24 + 1;
            }
            if(seen.charAt(32) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(32, '1');
            Queue.queuePush(32);
            dist32 = dist24 + 1;
            }
            if(seen.charAt(33) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(33, '1');
            Queue.queuePush(33);
            dist33 = dist24 + 1;
            }
            if(seen.charAt(34) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(34, '1');
            Queue.queuePush(34);
            dist34 = dist24 + 1;
            }
            break;
            case 25:
            if(seen.charAt(15) == '0' && validLocation.charAt(((7 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(15, '1');
            Queue.queuePush(15);
            dist15 = dist25 + 1;
            }
            if(seen.charAt(24) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(24, '1');
            Queue.queuePush(24);
            dist24 = dist25 + 1;
            }
            if(seen.charAt(33) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(33, '1');
            Queue.queuePush(33);
            dist33 = dist25 + 1;
            }
            if(seen.charAt(34) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(34, '1');
            Queue.queuePush(34);
            dist34 = dist25 + 1;
            }
            break;
            case 28:
            if(seen.charAt(19) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(19, '1');
            Queue.queuePush(19);
            dist19 = dist28 + 1;
            }
            if(seen.charAt(20) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(20, '1');
            Queue.queuePush(20);
            dist20 = dist28 + 1;
            }
            if(seen.charAt(29) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(29, '1');
            Queue.queuePush(29);
            dist29 = dist28 + 1;
            }
            if(seen.charAt(37) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(37, '1');
            Queue.queuePush(37);
            dist37 = dist28 + 1;
            }
            if(seen.charAt(38) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(38, '1');
            Queue.queuePush(38);
            dist38 = dist28 + 1;
            }
            break;
            case 29:
            if(seen.charAt(19) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(19, '1');
            Queue.queuePush(19);
            dist19 = dist29 + 1;
            }
            if(seen.charAt(20) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(20, '1');
            Queue.queuePush(20);
            dist20 = dist29 + 1;
            }
            if(seen.charAt(21) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(21, '1');
            Queue.queuePush(21);
            dist21 = dist29 + 1;
            }
            if(seen.charAt(28) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(28, '1');
            Queue.queuePush(28);
            dist28 = dist29 + 1;
            }
            if(seen.charAt(30) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(30, '1');
            Queue.queuePush(30);
            dist30 = dist29 + 1;
            }
            if(seen.charAt(37) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(37, '1');
            Queue.queuePush(37);
            dist37 = dist29 + 1;
            }
            if(seen.charAt(38) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(38, '1');
            Queue.queuePush(38);
            dist38 = dist29 + 1;
            }
            if(seen.charAt(39) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(39, '1');
            Queue.queuePush(39);
            dist39 = dist29 + 1;
            }
            break;
            case 30:
            if(seen.charAt(20) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(20, '1');
            Queue.queuePush(20);
            dist20 = dist30 + 1;
            }
            if(seen.charAt(21) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(21, '1');
            Queue.queuePush(21);
            dist21 = dist30 + 1;
            }
            if(seen.charAt(22) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(22, '1');
            Queue.queuePush(22);
            dist22 = dist30 + 1;
            }
            if(seen.charAt(29) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(29, '1');
            Queue.queuePush(29);
            dist29 = dist30 + 1;
            }
            if(seen.charAt(31) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(31, '1');
            Queue.queuePush(31);
            dist31 = dist30 + 1;
            }
            if(seen.charAt(38) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(38, '1');
            Queue.queuePush(38);
            dist38 = dist30 + 1;
            }
            if(seen.charAt(39) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(39, '1');
            Queue.queuePush(39);
            dist39 = dist30 + 1;
            }
            if(seen.charAt(40) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(40, '1');
            Queue.queuePush(40);
            dist40 = dist30 + 1;
            }
            break;
            case 31:
            if(seen.charAt(21) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(21, '1');
            Queue.queuePush(21);
            dist21 = dist31 + 1;
            }
            if(seen.charAt(22) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(22, '1');
            Queue.queuePush(22);
            dist22 = dist31 + 1;
            }
            if(seen.charAt(23) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(23, '1');
            Queue.queuePush(23);
            dist23 = dist31 + 1;
            }
            if(seen.charAt(30) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(30, '1');
            Queue.queuePush(30);
            dist30 = dist31 + 1;
            }
            if(seen.charAt(32) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(32, '1');
            Queue.queuePush(32);
            dist32 = dist31 + 1;
            }
            if(seen.charAt(39) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(39, '1');
            Queue.queuePush(39);
            dist39 = dist31 + 1;
            }
            if(seen.charAt(40) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(40, '1');
            Queue.queuePush(40);
            dist40 = dist31 + 1;
            }
            if(seen.charAt(41) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(41, '1');
            Queue.queuePush(41);
            dist41 = dist31 + 1;
            }
            break;
            case 32:
            if(seen.charAt(22) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(22, '1');
            Queue.queuePush(22);
            dist22 = dist32 + 1;
            }
            if(seen.charAt(23) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(23, '1');
            Queue.queuePush(23);
            dist23 = dist32 + 1;
            }
            if(seen.charAt(24) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(24, '1');
            Queue.queuePush(24);
            dist24 = dist32 + 1;
            }
            if(seen.charAt(31) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(31, '1');
            Queue.queuePush(31);
            dist31 = dist32 + 1;
            }
            if(seen.charAt(33) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(33, '1');
            Queue.queuePush(33);
            dist33 = dist32 + 1;
            }
            if(seen.charAt(40) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(40, '1');
            Queue.queuePush(40);
            dist40 = dist32 + 1;
            }
            if(seen.charAt(41) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(41, '1');
            Queue.queuePush(41);
            dist41 = dist32 + 1;
            }
            if(seen.charAt(42) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(42, '1');
            Queue.queuePush(42);
            dist42 = dist32 + 1;
            }
            break;
            case 33:
            if(seen.charAt(23) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(23, '1');
            Queue.queuePush(23);
            dist23 = dist33 + 1;
            }
            if(seen.charAt(24) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(24, '1');
            Queue.queuePush(24);
            dist24 = dist33 + 1;
            }
            if(seen.charAt(25) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(25, '1');
            Queue.queuePush(25);
            dist25 = dist33 + 1;
            }
            if(seen.charAt(32) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(32, '1');
            Queue.queuePush(32);
            dist32 = dist33 + 1;
            }
            if(seen.charAt(34) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(34, '1');
            Queue.queuePush(34);
            dist34 = dist33 + 1;
            }
            if(seen.charAt(41) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(41, '1');
            Queue.queuePush(41);
            dist41 = dist33 + 1;
            }
            if(seen.charAt(42) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(42, '1');
            Queue.queuePush(42);
            dist42 = dist33 + 1;
            }
            if(seen.charAt(43) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(43, '1');
            Queue.queuePush(43);
            dist43 = dist33 + 1;
            }
            break;
            case 34:
            if(seen.charAt(24) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(24, '1');
            Queue.queuePush(24);
            dist24 = dist34 + 1;
            }
            if(seen.charAt(25) == '0' && validLocation.charAt(((8 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(25, '1');
            Queue.queuePush(25);
            dist25 = dist34 + 1;
            }
            if(seen.charAt(33) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(33, '1');
            Queue.queuePush(33);
            dist33 = dist34 + 1;
            }
            if(seen.charAt(42) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(42, '1');
            Queue.queuePush(42);
            dist42 = dist34 + 1;
            }
            if(seen.charAt(43) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(43, '1');
            Queue.queuePush(43);
            dist43 = dist34 + 1;
            }
            break;
            case 37:
            if(seen.charAt(28) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(28, '1');
            Queue.queuePush(28);
            dist28 = dist37 + 1;
            }
            if(seen.charAt(29) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(29, '1');
            Queue.queuePush(29);
            dist29 = dist37 + 1;
            }
            if(seen.charAt(38) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(38, '1');
            Queue.queuePush(38);
            dist38 = dist37 + 1;
            }
            if(seen.charAt(46) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(46, '1');
            Queue.queuePush(46);
            dist46 = dist37 + 1;
            }
            if(seen.charAt(47) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(47, '1');
            Queue.queuePush(47);
            dist47 = dist37 + 1;
            }
            break;
            case 38:
            if(seen.charAt(28) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(28, '1');
            Queue.queuePush(28);
            dist28 = dist38 + 1;
            }
            if(seen.charAt(29) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(29, '1');
            Queue.queuePush(29);
            dist29 = dist38 + 1;
            }
            if(seen.charAt(30) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(30, '1');
            Queue.queuePush(30);
            dist30 = dist38 + 1;
            }
            if(seen.charAt(37) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(37, '1');
            Queue.queuePush(37);
            dist37 = dist38 + 1;
            }
            if(seen.charAt(39) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(39, '1');
            Queue.queuePush(39);
            dist39 = dist38 + 1;
            }
            if(seen.charAt(46) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(46, '1');
            Queue.queuePush(46);
            dist46 = dist38 + 1;
            }
            if(seen.charAt(47) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(47, '1');
            Queue.queuePush(47);
            dist47 = dist38 + 1;
            }
            if(seen.charAt(48) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(48, '1');
            Queue.queuePush(48);
            dist48 = dist38 + 1;
            }
            break;
            case 39:
            if(seen.charAt(29) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(29, '1');
            Queue.queuePush(29);
            dist29 = dist39 + 1;
            }
            if(seen.charAt(30) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(30, '1');
            Queue.queuePush(30);
            dist30 = dist39 + 1;
            }
            if(seen.charAt(31) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(31, '1');
            Queue.queuePush(31);
            dist31 = dist39 + 1;
            }
            if(seen.charAt(38) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(38, '1');
            Queue.queuePush(38);
            dist38 = dist39 + 1;
            }
            if(seen.charAt(40) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(40, '1');
            Queue.queuePush(40);
            dist40 = dist39 + 1;
            }
            if(seen.charAt(47) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(47, '1');
            Queue.queuePush(47);
            dist47 = dist39 + 1;
            }
            if(seen.charAt(48) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(48, '1');
            Queue.queuePush(48);
            dist48 = dist39 + 1;
            }
            if(seen.charAt(49) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(49, '1');
            Queue.queuePush(49);
            dist49 = dist39 + 1;
            }
            break;
            case 40:
            if(seen.charAt(30) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(30, '1');
            Queue.queuePush(30);
            dist30 = dist40 + 1;
            }
            if(seen.charAt(31) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(31, '1');
            Queue.queuePush(31);
            dist31 = dist40 + 1;
            }
            if(seen.charAt(32) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(32, '1');
            Queue.queuePush(32);
            dist32 = dist40 + 1;
            }
            if(seen.charAt(39) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(39, '1');
            Queue.queuePush(39);
            dist39 = dist40 + 1;
            }
            if(seen.charAt(41) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(41, '1');
            Queue.queuePush(41);
            dist41 = dist40 + 1;
            }
            if(seen.charAt(48) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(48, '1');
            Queue.queuePush(48);
            dist48 = dist40 + 1;
            }
            if(seen.charAt(49) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(49, '1');
            Queue.queuePush(49);
            dist49 = dist40 + 1;
            }
            if(seen.charAt(50) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(50, '1');
            Queue.queuePush(50);
            dist50 = dist40 + 1;
            }
            break;
            case 41:
            if(seen.charAt(31) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(31, '1');
            Queue.queuePush(31);
            dist31 = dist41 + 1;
            }
            if(seen.charAt(32) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(32, '1');
            Queue.queuePush(32);
            dist32 = dist41 + 1;
            }
            if(seen.charAt(33) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(33, '1');
            Queue.queuePush(33);
            dist33 = dist41 + 1;
            }
            if(seen.charAt(40) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(40, '1');
            Queue.queuePush(40);
            dist40 = dist41 + 1;
            }
            if(seen.charAt(42) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(42, '1');
            Queue.queuePush(42);
            dist42 = dist41 + 1;
            }
            if(seen.charAt(49) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(49, '1');
            Queue.queuePush(49);
            dist49 = dist41 + 1;
            }
            if(seen.charAt(50) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(50, '1');
            Queue.queuePush(50);
            dist50 = dist41 + 1;
            }
            if(seen.charAt(51) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(51, '1');
            Queue.queuePush(51);
            dist51 = dist41 + 1;
            }
            break;
            case 42:
            if(seen.charAt(32) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(32, '1');
            Queue.queuePush(32);
            dist32 = dist42 + 1;
            }
            if(seen.charAt(33) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(33, '1');
            Queue.queuePush(33);
            dist33 = dist42 + 1;
            }
            if(seen.charAt(34) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(34, '1');
            Queue.queuePush(34);
            dist34 = dist42 + 1;
            }
            if(seen.charAt(41) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(41, '1');
            Queue.queuePush(41);
            dist41 = dist42 + 1;
            }
            if(seen.charAt(43) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(43, '1');
            Queue.queuePush(43);
            dist43 = dist42 + 1;
            }
            if(seen.charAt(50) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(50, '1');
            Queue.queuePush(50);
            dist50 = dist42 + 1;
            }
            if(seen.charAt(51) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(51, '1');
            Queue.queuePush(51);
            dist51 = dist42 + 1;
            }
            if(seen.charAt(52) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(52, '1');
            Queue.queuePush(52);
            dist52 = dist42 + 1;
            }
            break;
            case 43:
            if(seen.charAt(33) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(33, '1');
            Queue.queuePush(33);
            dist33 = dist43 + 1;
            }
            if(seen.charAt(34) == '0' && validLocation.charAt(((9 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(34, '1');
            Queue.queuePush(34);
            dist34 = dist43 + 1;
            }
            if(seen.charAt(42) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(42, '1');
            Queue.queuePush(42);
            dist42 = dist43 + 1;
            }
            if(seen.charAt(51) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(51, '1');
            Queue.queuePush(51);
            dist51 = dist43 + 1;
            }
            if(seen.charAt(52) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(52, '1');
            Queue.queuePush(52);
            dist52 = dist43 + 1;
            }
            break;
            case 46:
            if(seen.charAt(37) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(37, '1');
            Queue.queuePush(37);
            dist37 = dist46 + 1;
            }
            if(seen.charAt(38) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(38, '1');
            Queue.queuePush(38);
            dist38 = dist46 + 1;
            }
            if(seen.charAt(47) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(47, '1');
            Queue.queuePush(47);
            dist47 = dist46 + 1;
            }
            if(seen.charAt(55) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(55, '1');
            Queue.queuePush(55);
            dist55 = dist46 + 1;
            }
            if(seen.charAt(56) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(56, '1');
            Queue.queuePush(56);
            dist56 = dist46 + 1;
            }
            break;
            case 47:
            if(seen.charAt(37) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(37, '1');
            Queue.queuePush(37);
            dist37 = dist47 + 1;
            }
            if(seen.charAt(38) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(38, '1');
            Queue.queuePush(38);
            dist38 = dist47 + 1;
            }
            if(seen.charAt(39) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(39, '1');
            Queue.queuePush(39);
            dist39 = dist47 + 1;
            }
            if(seen.charAt(46) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(46, '1');
            Queue.queuePush(46);
            dist46 = dist47 + 1;
            }
            if(seen.charAt(48) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(48, '1');
            Queue.queuePush(48);
            dist48 = dist47 + 1;
            }
            if(seen.charAt(55) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(55, '1');
            Queue.queuePush(55);
            dist55 = dist47 + 1;
            }
            if(seen.charAt(56) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(56, '1');
            Queue.queuePush(56);
            dist56 = dist47 + 1;
            }
            if(seen.charAt(57) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(57, '1');
            Queue.queuePush(57);
            dist57 = dist47 + 1;
            }
            break;
            case 48:
            if(seen.charAt(38) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(38, '1');
            Queue.queuePush(38);
            dist38 = dist48 + 1;
            }
            if(seen.charAt(39) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(39, '1');
            Queue.queuePush(39);
            dist39 = dist48 + 1;
            }
            if(seen.charAt(40) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(40, '1');
            Queue.queuePush(40);
            dist40 = dist48 + 1;
            }
            if(seen.charAt(47) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(47, '1');
            Queue.queuePush(47);
            dist47 = dist48 + 1;
            }
            if(seen.charAt(49) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(49, '1');
            Queue.queuePush(49);
            dist49 = dist48 + 1;
            }
            if(seen.charAt(56) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(56, '1');
            Queue.queuePush(56);
            dist56 = dist48 + 1;
            }
            if(seen.charAt(57) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(57, '1');
            Queue.queuePush(57);
            dist57 = dist48 + 1;
            }
            if(seen.charAt(58) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(58, '1');
            Queue.queuePush(58);
            dist58 = dist48 + 1;
            }
            break;
            case 49:
            if(seen.charAt(39) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(39, '1');
            Queue.queuePush(39);
            dist39 = dist49 + 1;
            }
            if(seen.charAt(40) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(40, '1');
            Queue.queuePush(40);
            dist40 = dist49 + 1;
            }
            if(seen.charAt(41) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(41, '1');
            Queue.queuePush(41);
            dist41 = dist49 + 1;
            }
            if(seen.charAt(48) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(48, '1');
            Queue.queuePush(48);
            dist48 = dist49 + 1;
            }
            if(seen.charAt(50) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(50, '1');
            Queue.queuePush(50);
            dist50 = dist49 + 1;
            }
            if(seen.charAt(57) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(57, '1');
            Queue.queuePush(57);
            dist57 = dist49 + 1;
            }
            if(seen.charAt(58) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(58, '1');
            Queue.queuePush(58);
            dist58 = dist49 + 1;
            }
            if(seen.charAt(59) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(59, '1');
            Queue.queuePush(59);
            dist59 = dist49 + 1;
            }
            break;
            case 50:
            if(seen.charAt(40) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(40, '1');
            Queue.queuePush(40);
            dist40 = dist50 + 1;
            }
            if(seen.charAt(41) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(41, '1');
            Queue.queuePush(41);
            dist41 = dist50 + 1;
            }
            if(seen.charAt(42) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(42, '1');
            Queue.queuePush(42);
            dist42 = dist50 + 1;
            }
            if(seen.charAt(49) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(49, '1');
            Queue.queuePush(49);
            dist49 = dist50 + 1;
            }
            if(seen.charAt(51) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(51, '1');
            Queue.queuePush(51);
            dist51 = dist50 + 1;
            }
            if(seen.charAt(58) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(58, '1');
            Queue.queuePush(58);
            dist58 = dist50 + 1;
            }
            if(seen.charAt(59) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(59, '1');
            Queue.queuePush(59);
            dist59 = dist50 + 1;
            }
            if(seen.charAt(60) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(60, '1');
            Queue.queuePush(60);
            dist60 = dist50 + 1;
            }
            break;
            case 51:
            if(seen.charAt(41) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(41, '1');
            Queue.queuePush(41);
            dist41 = dist51 + 1;
            }
            if(seen.charAt(42) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(42, '1');
            Queue.queuePush(42);
            dist42 = dist51 + 1;
            }
            if(seen.charAt(43) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(43, '1');
            Queue.queuePush(43);
            dist43 = dist51 + 1;
            }
            if(seen.charAt(50) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(50, '1');
            Queue.queuePush(50);
            dist50 = dist51 + 1;
            }
            if(seen.charAt(52) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(52, '1');
            Queue.queuePush(52);
            dist52 = dist51 + 1;
            }
            if(seen.charAt(59) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(59, '1');
            Queue.queuePush(59);
            dist59 = dist51 + 1;
            }
            if(seen.charAt(60) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(60, '1');
            Queue.queuePush(60);
            dist60 = dist51 + 1;
            }
            if(seen.charAt(61) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(61, '1');
            Queue.queuePush(61);
            dist61 = dist51 + 1;
            }
            break;
            case 52:
            if(seen.charAt(42) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(42, '1');
            Queue.queuePush(42);
            dist42 = dist52 + 1;
            }
            if(seen.charAt(43) == '0' && validLocation.charAt(((10 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(43, '1');
            Queue.queuePush(43);
            dist43 = dist52 + 1;
            }
            if(seen.charAt(51) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(51, '1');
            Queue.queuePush(51);
            dist51 = dist52 + 1;
            }
            if(seen.charAt(60) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(60, '1');
            Queue.queuePush(60);
            dist60 = dist52 + 1;
            }
            if(seen.charAt(61) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(61, '1');
            Queue.queuePush(61);
            dist61 = dist52 + 1;
            }
            break;
            case 55:
            if(seen.charAt(46) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(46, '1');
            Queue.queuePush(46);
            dist46 = dist55 + 1;
            }
            if(seen.charAt(47) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(47, '1');
            Queue.queuePush(47);
            dist47 = dist55 + 1;
            }
            if(seen.charAt(56) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(56, '1');
            Queue.queuePush(56);
            dist56 = dist55 + 1;
            }
            if(seen.charAt(65) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(65, '1');
            Queue.queuePush(65);
            dist65 = dist55 + 1;
            }
            break;
            case 56:
            if(seen.charAt(46) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(46, '1');
            Queue.queuePush(46);
            dist46 = dist56 + 1;
            }
            if(seen.charAt(47) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(47, '1');
            Queue.queuePush(47);
            dist47 = dist56 + 1;
            }
            if(seen.charAt(48) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(48, '1');
            Queue.queuePush(48);
            dist48 = dist56 + 1;
            }
            if(seen.charAt(55) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(55, '1');
            Queue.queuePush(55);
            dist55 = dist56 + 1;
            }
            if(seen.charAt(57) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(57, '1');
            Queue.queuePush(57);
            dist57 = dist56 + 1;
            }
            if(seen.charAt(65) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(65, '1');
            Queue.queuePush(65);
            dist65 = dist56 + 1;
            }
            if(seen.charAt(66) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(66, '1');
            Queue.queuePush(66);
            dist66 = dist56 + 1;
            }
            break;
            case 57:
            if(seen.charAt(47) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(47, '1');
            Queue.queuePush(47);
            dist47 = dist57 + 1;
            }
            if(seen.charAt(48) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(48, '1');
            Queue.queuePush(48);
            dist48 = dist57 + 1;
            }
            if(seen.charAt(49) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(49, '1');
            Queue.queuePush(49);
            dist49 = dist57 + 1;
            }
            if(seen.charAt(56) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(56, '1');
            Queue.queuePush(56);
            dist56 = dist57 + 1;
            }
            if(seen.charAt(58) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(58, '1');
            Queue.queuePush(58);
            dist58 = dist57 + 1;
            }
            if(seen.charAt(65) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(65, '1');
            Queue.queuePush(65);
            dist65 = dist57 + 1;
            }
            if(seen.charAt(66) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(66, '1');
            Queue.queuePush(66);
            dist66 = dist57 + 1;
            }
            if(seen.charAt(67) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(67, '1');
            Queue.queuePush(67);
            dist67 = dist57 + 1;
            }
            break;
            case 58:
            if(seen.charAt(48) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(48, '1');
            Queue.queuePush(48);
            dist48 = dist58 + 1;
            }
            if(seen.charAt(49) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(49, '1');
            Queue.queuePush(49);
            dist49 = dist58 + 1;
            }
            if(seen.charAt(50) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(50, '1');
            Queue.queuePush(50);
            dist50 = dist58 + 1;
            }
            if(seen.charAt(57) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(57, '1');
            Queue.queuePush(57);
            dist57 = dist58 + 1;
            }
            if(seen.charAt(59) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(59, '1');
            Queue.queuePush(59);
            dist59 = dist58 + 1;
            }
            if(seen.charAt(66) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(66, '1');
            Queue.queuePush(66);
            dist66 = dist58 + 1;
            }
            if(seen.charAt(67) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(67, '1');
            Queue.queuePush(67);
            dist67 = dist58 + 1;
            }
            if(seen.charAt(68) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(68, '1');
            Queue.queuePush(68);
            dist68 = dist58 + 1;
            }
            break;
            case 59:
            if(seen.charAt(49) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(49, '1');
            Queue.queuePush(49);
            dist49 = dist59 + 1;
            }
            if(seen.charAt(50) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(50, '1');
            Queue.queuePush(50);
            dist50 = dist59 + 1;
            }
            if(seen.charAt(51) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(51, '1');
            Queue.queuePush(51);
            dist51 = dist59 + 1;
            }
            if(seen.charAt(58) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(58, '1');
            Queue.queuePush(58);
            dist58 = dist59 + 1;
            }
            if(seen.charAt(60) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(60, '1');
            Queue.queuePush(60);
            dist60 = dist59 + 1;
            }
            if(seen.charAt(67) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(67, '1');
            Queue.queuePush(67);
            dist67 = dist59 + 1;
            }
            if(seen.charAt(68) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(68, '1');
            Queue.queuePush(68);
            dist68 = dist59 + 1;
            }
            if(seen.charAt(69) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(69, '1');
            Queue.queuePush(69);
            dist69 = dist59 + 1;
            }
            break;
            case 60:
            if(seen.charAt(50) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(50, '1');
            Queue.queuePush(50);
            dist50 = dist60 + 1;
            }
            if(seen.charAt(51) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(51, '1');
            Queue.queuePush(51);
            dist51 = dist60 + 1;
            }
            if(seen.charAt(52) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(52, '1');
            Queue.queuePush(52);
            dist52 = dist60 + 1;
            }
            if(seen.charAt(59) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(59, '1');
            Queue.queuePush(59);
            dist59 = dist60 + 1;
            }
            if(seen.charAt(61) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(61, '1');
            Queue.queuePush(61);
            dist61 = dist60 + 1;
            }
            if(seen.charAt(68) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(68, '1');
            Queue.queuePush(68);
            dist68 = dist60 + 1;
            }
            if(seen.charAt(69) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(69, '1');
            Queue.queuePush(69);
            dist69 = dist60 + 1;
            }
            break;
            case 61:
            if(seen.charAt(51) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(51, '1');
            Queue.queuePush(51);
            dist51 = dist61 + 1;
            }
            if(seen.charAt(52) == '0' && validLocation.charAt(((11 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(52, '1');
            Queue.queuePush(52);
            dist52 = dist61 + 1;
            }
            if(seen.charAt(60) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(60, '1');
            Queue.queuePush(60);
            dist60 = dist61 + 1;
            }
            if(seen.charAt(69) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(69, '1');
            Queue.queuePush(69);
            dist69 = dist61 + 1;
            }
            break;
            case 65:
            if(seen.charAt(55) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (7 + offsety) % 10) == '1') {
            seen.setCharAt(55, '1');
            Queue.queuePush(55);
            dist55 = dist65 + 1;
            }
            if(seen.charAt(56) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(56, '1');
            Queue.queuePush(56);
            dist56 = dist65 + 1;
            }
            if(seen.charAt(57) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(57, '1');
            Queue.queuePush(57);
            dist57 = dist65 + 1;
            }
            if(seen.charAt(66) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(66, '1');
            Queue.queuePush(66);
            dist66 = dist65 + 1;
            }
            break;
            case 66:
            if(seen.charAt(56) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(56, '1');
            Queue.queuePush(56);
            dist56 = dist66 + 1;
            }
            if(seen.charAt(57) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(57, '1');
            Queue.queuePush(57);
            dist57 = dist66 + 1;
            }
            if(seen.charAt(58) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(58, '1');
            Queue.queuePush(58);
            dist58 = dist66 + 1;
            }
            if(seen.charAt(65) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (8 + offsety) % 10) == '1') {
            seen.setCharAt(65, '1');
            Queue.queuePush(65);
            dist65 = dist66 + 1;
            }
            if(seen.charAt(67) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(67, '1');
            Queue.queuePush(67);
            dist67 = dist66 + 1;
            }
            break;
            case 67:
            if(seen.charAt(57) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(57, '1');
            Queue.queuePush(57);
            dist57 = dist67 + 1;
            }
            if(seen.charAt(58) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(58, '1');
            Queue.queuePush(58);
            dist58 = dist67 + 1;
            }
            if(seen.charAt(59) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(59, '1');
            Queue.queuePush(59);
            dist59 = dist67 + 1;
            }
            if(seen.charAt(66) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (9 + offsety) % 10) == '1') {
            seen.setCharAt(66, '1');
            Queue.queuePush(66);
            dist66 = dist67 + 1;
            }
            if(seen.charAt(68) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(68, '1');
            Queue.queuePush(68);
            dist68 = dist67 + 1;
            }
            break;
            case 68:
            if(seen.charAt(58) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(58, '1');
            Queue.queuePush(58);
            dist58 = dist68 + 1;
            }
            if(seen.charAt(59) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(59, '1');
            Queue.queuePush(59);
            dist59 = dist68 + 1;
            }
            if(seen.charAt(60) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(60, '1');
            Queue.queuePush(60);
            dist60 = dist68 + 1;
            }
            if(seen.charAt(67) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (10 + offsety) % 10) == '1') {
            seen.setCharAt(67, '1');
            Queue.queuePush(67);
            dist67 = dist68 + 1;
            }
            if(seen.charAt(69) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(69, '1');
            Queue.queuePush(69);
            dist69 = dist68 + 1;
            }
            break;
            case 69:
            if(seen.charAt(59) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(59, '1');
            Queue.queuePush(59);
            dist59 = dist69 + 1;
            }
            if(seen.charAt(60) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (12 + offsety) % 10) == '1') {
            seen.setCharAt(60, '1');
            Queue.queuePush(60);
            dist60 = dist69 + 1;
            }
            if(seen.charAt(61) == '0' && validLocation.charAt(((12 + offsetx) % 10) * 10 + (13 + offsety) % 10) == '1') {
            seen.setCharAt(61, '1');
            Queue.queuePush(61);
            dist61 = dist69 + 1;
            }
            if(seen.charAt(68) == '0' && validLocation.charAt(((13 + offsetx) % 10) * 10 + (11 + offsety) % 10) == '1') {
            seen.setCharAt(68, '1');
            Queue.queuePush(68);
            dist68 = dist69 + 1;
            }
            break;
        }
    }
    }

    Direction bfs(MapLocation currentTarget) {
        int x = currentTarget.x - rc.getLocation().x + 4;
        int y = currentTarget.y - rc.getLocation().y + 4;

        switch (9 * x + y) {
            case 11:
                return backtrack11();
            case 12:
                return backtrack12();
            case 13:
                return backtrack13();
            case 14:
                return backtrack14();
            case 15:
                return backtrack15();
            case 19:
                return backtrack19();
            case 20:
                return backtrack20();
            case 21:
                return backtrack21();
            case 22:
                return backtrack22();
            case 23:
                return backtrack23();
            case 24:
                return backtrack24();
            case 25:
                return backtrack25();
            case 28:
                return backtrack28();
            case 29:
                return backtrack29();
            case 30:
                return backtrack30();
            case 31:
                return backtrack31();
            case 32:
                return backtrack32();
            case 33:
                return backtrack33();
            case 34:
                return backtrack34();
            case 37:
                return backtrack37();
            case 38:
                return backtrack38();
            case 39:
                return backtrack39();
            case 40:
                return backtrack40();
            case 41:
                return backtrack41();
            case 42:
                return backtrack42();
            case 43:
                return backtrack43();
            case 46:
                return backtrack46();
            case 47:
                return backtrack47();
            case 48:
                return backtrack48();
            case 49:
                return backtrack49();
            case 50:
                return backtrack50();
            case 51:
                return backtrack51();
            case 52:
                return backtrack52();
            case 55:
                return backtrack55();
            case 56:
                return backtrack56();
            case 57:
                return backtrack57();
            case 58:
                return backtrack58();
            case 59:
                return backtrack59();
            case 60:
                return backtrack60();
            case 61:
                return backtrack61();
            case 65:
                return backtrack65();
            case 66:
                return backtrack66();
            case 67:
                return backtrack67();
            case 68:
                return backtrack68();
            case 69:
                return backtrack69();
        }

        return null;
    }
}
