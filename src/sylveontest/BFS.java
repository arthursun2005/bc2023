        package sylveontest;

import battlecode.common.*;

public class BFS {

    RobotController rc;
    public BFS(RobotController rc) {
        this.rc = rc;
    }

    public void reset() {
        Queue.reset();
    }


    int dist12 = 0;
    int seen12 = 0;
    int arr12 = 0;
    int dist13 = 0;
    int seen13 = 0;
    int arr13 = 0;
    int dist14 = 0;
    int seen14 = 0;
    int arr14 = 0;
    int dist15 = 0;
    int seen15 = 0;
    int arr15 = 0;
    int dist16 = 0;
    int seen16 = 0;
    int arr16 = 0;
    int dist21 = 0;
    int seen21 = 0;
    int arr21 = 0;
    int dist22 = 0;
    int seen22 = 0;
    int arr22 = 0;
    int dist23 = 0;
    int seen23 = 0;
    int arr23 = 0;
    int dist24 = 0;
    int seen24 = 0;
    int arr24 = 0;
    int dist25 = 0;
    int seen25 = 0;
    int arr25 = 0;
    int dist26 = 0;
    int seen26 = 0;
    int arr26 = 0;
    int dist27 = 0;
    int seen27 = 0;
    int arr27 = 0;
    int dist31 = 0;
    int seen31 = 0;
    int arr31 = 0;
    int dist32 = 0;
    int seen32 = 0;
    int arr32 = 0;
    int dist33 = 0;
    int seen33 = 0;
    int arr33 = 0;
    int dist34 = 0;
    int seen34 = 0;
    int arr34 = 0;
    int dist35 = 0;
    int seen35 = 0;
    int arr35 = 0;
    int dist36 = 0;
    int seen36 = 0;
    int arr36 = 0;
    int dist37 = 0;
    int seen37 = 0;
    int arr37 = 0;
    int dist41 = 0;
    int seen41 = 0;
    int arr41 = 0;
    int dist42 = 0;
    int seen42 = 0;
    int arr42 = 0;
    int dist43 = 0;
    int seen43 = 0;
    int arr43 = 0;
    int dist44 = 0;
    int seen44 = 0;
    int arr44 = 0;
    int dist45 = 0;
    int seen45 = 0;
    int arr45 = 0;
    int dist46 = 0;
    int seen46 = 0;
    int arr46 = 0;
    int dist47 = 0;
    int seen47 = 0;
    int arr47 = 0;
    int dist51 = 0;
    int seen51 = 0;
    int arr51 = 0;
    int dist52 = 0;
    int seen52 = 0;
    int arr52 = 0;
    int dist53 = 0;
    int seen53 = 0;
    int arr53 = 0;
    int dist54 = 0;
    int seen54 = 0;
    int arr54 = 0;
    int dist55 = 0;
    int seen55 = 0;
    int arr55 = 0;
    int dist56 = 0;
    int seen56 = 0;
    int arr56 = 0;
    int dist57 = 0;
    int seen57 = 0;
    int arr57 = 0;
    int dist61 = 0;
    int seen61 = 0;
    int arr61 = 0;
    int dist62 = 0;
    int seen62 = 0;
    int arr62 = 0;
    int dist63 = 0;
    int seen63 = 0;
    int arr63 = 0;
    int dist64 = 0;
    int seen64 = 0;
    int arr64 = 0;
    int dist65 = 0;
    int seen65 = 0;
    int arr65 = 0;
    int dist66 = 0;
    int seen66 = 0;
    int arr66 = 0;
    int dist67 = 0;
    int seen67 = 0;
    int arr67 = 0;
    int dist72 = 0;
    int seen72 = 0;
    int arr72 = 0;
    int dist73 = 0;
    int seen73 = 0;
    int arr73 = 0;
    int dist74 = 0;
    int seen74 = 0;
    int arr74 = 0;
    int dist75 = 0;
    int seen75 = 0;
    int arr75 = 0;
    int dist76 = 0;
    int seen76 = 0;
    int arr76 = 0;
    int roundNum = 0;
    public Direction backtrack12() {
        if (dist13 == dist12 - 1) {
            if (dist13 == 0 && seen13 == roundNum) {
                return Direction.SOUTH;}
            return backtrack13();
        }
        if (dist21 == dist12 - 1) {
            if (dist21 == 0 && seen21 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack21();
        }
        if (dist22 == dist12 - 1) {
            if (dist22 == 0 && seen22 == roundNum) {
                return Direction.WEST;}
            return backtrack22();
        }
        if (dist23 == dist12 - 1) {
            if (dist23 == 0 && seen23 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack23();
        }
        return null;}
    public Direction backtrack13() {
        if (dist12 == dist13 - 1) {
            if (dist12 == 0 && seen12 == roundNum) {
                return Direction.NORTH;}
            return backtrack12();
        }
        if (dist14 == dist13 - 1) {
            if (dist14 == 0 && seen14 == roundNum) {
                return Direction.SOUTH;}
            return backtrack14();
        }
        if (dist22 == dist13 - 1) {
            if (dist22 == 0 && seen22 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack22();
        }
        if (dist23 == dist13 - 1) {
            if (dist23 == 0 && seen23 == roundNum) {
                return Direction.WEST;}
            return backtrack23();
        }
        if (dist24 == dist13 - 1) {
            if (dist24 == 0 && seen24 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack24();
        }
        return null;}
    public Direction backtrack14() {
        if (dist13 == dist14 - 1) {
            if (dist13 == 0 && seen13 == roundNum) {
                return Direction.NORTH;}
            return backtrack13();
        }
        if (dist15 == dist14 - 1) {
            if (dist15 == 0 && seen15 == roundNum) {
                return Direction.SOUTH;}
            return backtrack15();
        }
        if (dist23 == dist14 - 1) {
            if (dist23 == 0 && seen23 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack23();
        }
        if (dist24 == dist14 - 1) {
            if (dist24 == 0 && seen24 == roundNum) {
                return Direction.WEST;}
            return backtrack24();
        }
        if (dist25 == dist14 - 1) {
            if (dist25 == 0 && seen25 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack25();
        }
        return null;}
    public Direction backtrack15() {
        if (dist14 == dist15 - 1) {
            if (dist14 == 0 && seen14 == roundNum) {
                return Direction.NORTH;}
            return backtrack14();
        }
        if (dist16 == dist15 - 1) {
            if (dist16 == 0 && seen16 == roundNum) {
                return Direction.SOUTH;}
            return backtrack16();
        }
        if (dist24 == dist15 - 1) {
            if (dist24 == 0 && seen24 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack24();
        }
        if (dist25 == dist15 - 1) {
            if (dist25 == 0 && seen25 == roundNum) {
                return Direction.WEST;}
            return backtrack25();
        }
        if (dist26 == dist15 - 1) {
            if (dist26 == 0 && seen26 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack26();
        }
        return null;}
    public Direction backtrack16() {
        if (dist15 == dist16 - 1) {
            if (dist15 == 0 && seen15 == roundNum) {
                return Direction.NORTH;}
            return backtrack15();
        }
        if (dist25 == dist16 - 1) {
            if (dist25 == 0 && seen25 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack25();
        }
        if (dist26 == dist16 - 1) {
            if (dist26 == 0 && seen26 == roundNum) {
                return Direction.WEST;}
            return backtrack26();
        }
        if (dist27 == dist16 - 1) {
            if (dist27 == 0 && seen27 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack27();
        }
        return null;}
    public Direction backtrack21() {
        if (dist12 == dist21 - 1) {
            if (dist12 == 0 && seen12 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack12();
        }
        if (dist22 == dist21 - 1) {
            if (dist22 == 0 && seen22 == roundNum) {
                return Direction.SOUTH;}
            return backtrack22();
        }
        if (dist31 == dist21 - 1) {
            if (dist31 == 0 && seen31 == roundNum) {
                return Direction.WEST;}
            return backtrack31();
        }
        if (dist32 == dist21 - 1) {
            if (dist32 == 0 && seen32 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack32();
        }
        return null;}
    public Direction backtrack22() {
        if (dist12 == dist22 - 1) {
            if (dist12 == 0 && seen12 == roundNum) {
                return Direction.EAST;}
            return backtrack12();
        }
        if (dist13 == dist22 - 1) {
            if (dist13 == 0 && seen13 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack13();
        }
        if (dist21 == dist22 - 1) {
            if (dist21 == 0 && seen21 == roundNum) {
                return Direction.NORTH;}
            return backtrack21();
        }
        if (dist23 == dist22 - 1) {
            if (dist23 == 0 && seen23 == roundNum) {
                return Direction.SOUTH;}
            return backtrack23();
        }
        if (dist31 == dist22 - 1) {
            if (dist31 == 0 && seen31 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack31();
        }
        if (dist32 == dist22 - 1) {
            if (dist32 == 0 && seen32 == roundNum) {
                return Direction.WEST;}
            return backtrack32();
        }
        if (dist33 == dist22 - 1) {
            if (dist33 == 0 && seen33 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack33();
        }
        return null;}
    public Direction backtrack23() {
        if (dist12 == dist23 - 1) {
            if (dist12 == 0 && seen12 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack12();
        }
        if (dist13 == dist23 - 1) {
            if (dist13 == 0 && seen13 == roundNum) {
                return Direction.EAST;}
            return backtrack13();
        }
        if (dist14 == dist23 - 1) {
            if (dist14 == 0 && seen14 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack14();
        }
        if (dist22 == dist23 - 1) {
            if (dist22 == 0 && seen22 == roundNum) {
                return Direction.NORTH;}
            return backtrack22();
        }
        if (dist24 == dist23 - 1) {
            if (dist24 == 0 && seen24 == roundNum) {
                return Direction.SOUTH;}
            return backtrack24();
        }
        if (dist32 == dist23 - 1) {
            if (dist32 == 0 && seen32 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack32();
        }
        if (dist33 == dist23 - 1) {
            if (dist33 == 0 && seen33 == roundNum) {
                return Direction.WEST;}
            return backtrack33();
        }
        if (dist34 == dist23 - 1) {
            if (dist34 == 0 && seen34 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack34();
        }
        return null;}
    public Direction backtrack24() {
        if (dist13 == dist24 - 1) {
            if (dist13 == 0 && seen13 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack13();
        }
        if (dist14 == dist24 - 1) {
            if (dist14 == 0 && seen14 == roundNum) {
                return Direction.EAST;}
            return backtrack14();
        }
        if (dist15 == dist24 - 1) {
            if (dist15 == 0 && seen15 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack15();
        }
        if (dist23 == dist24 - 1) {
            if (dist23 == 0 && seen23 == roundNum) {
                return Direction.NORTH;}
            return backtrack23();
        }
        if (dist25 == dist24 - 1) {
            if (dist25 == 0 && seen25 == roundNum) {
                return Direction.SOUTH;}
            return backtrack25();
        }
        if (dist33 == dist24 - 1) {
            if (dist33 == 0 && seen33 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack33();
        }
        if (dist34 == dist24 - 1) {
            if (dist34 == 0 && seen34 == roundNum) {
                return Direction.WEST;}
            return backtrack34();
        }
        if (dist35 == dist24 - 1) {
            if (dist35 == 0 && seen35 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack35();
        }
        return null;}
    public Direction backtrack25() {
        if (dist14 == dist25 - 1) {
            if (dist14 == 0 && seen14 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack14();
        }
        if (dist15 == dist25 - 1) {
            if (dist15 == 0 && seen15 == roundNum) {
                return Direction.EAST;}
            return backtrack15();
        }
        if (dist16 == dist25 - 1) {
            if (dist16 == 0 && seen16 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack16();
        }
        if (dist24 == dist25 - 1) {
            if (dist24 == 0 && seen24 == roundNum) {
                return Direction.NORTH;}
            return backtrack24();
        }
        if (dist26 == dist25 - 1) {
            if (dist26 == 0 && seen26 == roundNum) {
                return Direction.SOUTH;}
            return backtrack26();
        }
        if (dist34 == dist25 - 1) {
            if (dist34 == 0 && seen34 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack34();
        }
        if (dist35 == dist25 - 1) {
            if (dist35 == 0 && seen35 == roundNum) {
                return Direction.WEST;}
            return backtrack35();
        }
        if (dist36 == dist25 - 1) {
            if (dist36 == 0 && seen36 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack36();
        }
        return null;}
    public Direction backtrack26() {
        if (dist15 == dist26 - 1) {
            if (dist15 == 0 && seen15 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack15();
        }
        if (dist16 == dist26 - 1) {
            if (dist16 == 0 && seen16 == roundNum) {
                return Direction.EAST;}
            return backtrack16();
        }
        if (dist25 == dist26 - 1) {
            if (dist25 == 0 && seen25 == roundNum) {
                return Direction.NORTH;}
            return backtrack25();
        }
        if (dist27 == dist26 - 1) {
            if (dist27 == 0 && seen27 == roundNum) {
                return Direction.SOUTH;}
            return backtrack27();
        }
        if (dist35 == dist26 - 1) {
            if (dist35 == 0 && seen35 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack35();
        }
        if (dist36 == dist26 - 1) {
            if (dist36 == 0 && seen36 == roundNum) {
                return Direction.WEST;}
            return backtrack36();
        }
        if (dist37 == dist26 - 1) {
            if (dist37 == 0 && seen37 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack37();
        }
        return null;}
    public Direction backtrack27() {
        if (dist16 == dist27 - 1) {
            if (dist16 == 0 && seen16 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack16();
        }
        if (dist26 == dist27 - 1) {
            if (dist26 == 0 && seen26 == roundNum) {
                return Direction.NORTH;}
            return backtrack26();
        }
        if (dist36 == dist27 - 1) {
            if (dist36 == 0 && seen36 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack36();
        }
        if (dist37 == dist27 - 1) {
            if (dist37 == 0 && seen37 == roundNum) {
                return Direction.WEST;}
            return backtrack37();
        }
        return null;}
    public Direction backtrack31() {
        if (dist21 == dist31 - 1) {
            if (dist21 == 0 && seen21 == roundNum) {
                return Direction.EAST;}
            return backtrack21();
        }
        if (dist22 == dist31 - 1) {
            if (dist22 == 0 && seen22 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack22();
        }
        if (dist32 == dist31 - 1) {
            if (dist32 == 0 && seen32 == roundNum) {
                return Direction.SOUTH;}
            return backtrack32();
        }
        if (dist41 == dist31 - 1) {
            if (dist41 == 0 && seen41 == roundNum) {
                return Direction.WEST;}
            return backtrack41();
        }
        if (dist42 == dist31 - 1) {
            if (dist42 == 0 && seen42 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack42();
        }
        return null;}
    public Direction backtrack32() {
        if (dist21 == dist32 - 1) {
            if (dist21 == 0 && seen21 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack21();
        }
        if (dist22 == dist32 - 1) {
            if (dist22 == 0 && seen22 == roundNum) {
                return Direction.EAST;}
            return backtrack22();
        }
        if (dist23 == dist32 - 1) {
            if (dist23 == 0 && seen23 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack23();
        }
        if (dist31 == dist32 - 1) {
            if (dist31 == 0 && seen31 == roundNum) {
                return Direction.NORTH;}
            return backtrack31();
        }
        if (dist33 == dist32 - 1) {
            if (dist33 == 0 && seen33 == roundNum) {
                return Direction.SOUTH;}
            return backtrack33();
        }
        if (dist41 == dist32 - 1) {
            if (dist41 == 0 && seen41 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack41();
        }
        if (dist42 == dist32 - 1) {
            if (dist42 == 0 && seen42 == roundNum) {
                return Direction.WEST;}
            return backtrack42();
        }
        if (dist43 == dist32 - 1) {
            if (dist43 == 0 && seen43 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack43();
        }
        return null;}
    public Direction backtrack33() {
        if (dist22 == dist33 - 1) {
            if (dist22 == 0 && seen22 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack22();
        }
        if (dist23 == dist33 - 1) {
            if (dist23 == 0 && seen23 == roundNum) {
                return Direction.EAST;}
            return backtrack23();
        }
        if (dist24 == dist33 - 1) {
            if (dist24 == 0 && seen24 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack24();
        }
        if (dist32 == dist33 - 1) {
            if (dist32 == 0 && seen32 == roundNum) {
                return Direction.NORTH;}
            return backtrack32();
        }
        if (dist34 == dist33 - 1) {
            if (dist34 == 0 && seen34 == roundNum) {
                return Direction.SOUTH;}
            return backtrack34();
        }
        if (dist42 == dist33 - 1) {
            if (dist42 == 0 && seen42 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack42();
        }
        if (dist43 == dist33 - 1) {
            if (dist43 == 0 && seen43 == roundNum) {
                return Direction.WEST;}
            return backtrack43();
        }
        if (dist44 == dist33 - 1) {
            if (dist44 == 0 && seen44 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack44();
        }
        return null;}
    public Direction backtrack34() {
        if (dist23 == dist34 - 1) {
            if (dist23 == 0 && seen23 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack23();
        }
        if (dist24 == dist34 - 1) {
            if (dist24 == 0 && seen24 == roundNum) {
                return Direction.EAST;}
            return backtrack24();
        }
        if (dist25 == dist34 - 1) {
            if (dist25 == 0 && seen25 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack25();
        }
        if (dist33 == dist34 - 1) {
            if (dist33 == 0 && seen33 == roundNum) {
                return Direction.NORTH;}
            return backtrack33();
        }
        if (dist35 == dist34 - 1) {
            if (dist35 == 0 && seen35 == roundNum) {
                return Direction.SOUTH;}
            return backtrack35();
        }
        if (dist43 == dist34 - 1) {
            if (dist43 == 0 && seen43 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack43();
        }
        if (dist44 == dist34 - 1) {
            if (dist44 == 0 && seen44 == roundNum) {
                return Direction.WEST;}
            return backtrack44();
        }
        if (dist45 == dist34 - 1) {
            if (dist45 == 0 && seen45 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack45();
        }
        return null;}
    public Direction backtrack35() {
        if (dist24 == dist35 - 1) {
            if (dist24 == 0 && seen24 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack24();
        }
        if (dist25 == dist35 - 1) {
            if (dist25 == 0 && seen25 == roundNum) {
                return Direction.EAST;}
            return backtrack25();
        }
        if (dist26 == dist35 - 1) {
            if (dist26 == 0 && seen26 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack26();
        }
        if (dist34 == dist35 - 1) {
            if (dist34 == 0 && seen34 == roundNum) {
                return Direction.NORTH;}
            return backtrack34();
        }
        if (dist36 == dist35 - 1) {
            if (dist36 == 0 && seen36 == roundNum) {
                return Direction.SOUTH;}
            return backtrack36();
        }
        if (dist44 == dist35 - 1) {
            if (dist44 == 0 && seen44 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack44();
        }
        if (dist45 == dist35 - 1) {
            if (dist45 == 0 && seen45 == roundNum) {
                return Direction.WEST;}
            return backtrack45();
        }
        if (dist46 == dist35 - 1) {
            if (dist46 == 0 && seen46 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack46();
        }
        return null;}
    public Direction backtrack36() {
        if (dist25 == dist36 - 1) {
            if (dist25 == 0 && seen25 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack25();
        }
        if (dist26 == dist36 - 1) {
            if (dist26 == 0 && seen26 == roundNum) {
                return Direction.EAST;}
            return backtrack26();
        }
        if (dist27 == dist36 - 1) {
            if (dist27 == 0 && seen27 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack27();
        }
        if (dist35 == dist36 - 1) {
            if (dist35 == 0 && seen35 == roundNum) {
                return Direction.NORTH;}
            return backtrack35();
        }
        if (dist37 == dist36 - 1) {
            if (dist37 == 0 && seen37 == roundNum) {
                return Direction.SOUTH;}
            return backtrack37();
        }
        if (dist45 == dist36 - 1) {
            if (dist45 == 0 && seen45 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack45();
        }
        if (dist46 == dist36 - 1) {
            if (dist46 == 0 && seen46 == roundNum) {
                return Direction.WEST;}
            return backtrack46();
        }
        if (dist47 == dist36 - 1) {
            if (dist47 == 0 && seen47 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack47();
        }
        return null;}
    public Direction backtrack37() {
        if (dist26 == dist37 - 1) {
            if (dist26 == 0 && seen26 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack26();
        }
        if (dist27 == dist37 - 1) {
            if (dist27 == 0 && seen27 == roundNum) {
                return Direction.EAST;}
            return backtrack27();
        }
        if (dist36 == dist37 - 1) {
            if (dist36 == 0 && seen36 == roundNum) {
                return Direction.NORTH;}
            return backtrack36();
        }
        if (dist46 == dist37 - 1) {
            if (dist46 == 0 && seen46 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack46();
        }
        if (dist47 == dist37 - 1) {
            if (dist47 == 0 && seen47 == roundNum) {
                return Direction.WEST;}
            return backtrack47();
        }
        return null;}
    public Direction backtrack41() {
        if (dist31 == dist41 - 1) {
            if (dist31 == 0 && seen31 == roundNum) {
                return Direction.EAST;}
            return backtrack31();
        }
        if (dist32 == dist41 - 1) {
            if (dist32 == 0 && seen32 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack32();
        }
        if (dist42 == dist41 - 1) {
            if (dist42 == 0 && seen42 == roundNum) {
                return Direction.SOUTH;}
            return backtrack42();
        }
        if (dist51 == dist41 - 1) {
            if (dist51 == 0 && seen51 == roundNum) {
                return Direction.WEST;}
            return backtrack51();
        }
        if (dist52 == dist41 - 1) {
            if (dist52 == 0 && seen52 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack52();
        }
        return null;}
    public Direction backtrack42() {
        if (dist31 == dist42 - 1) {
            if (dist31 == 0 && seen31 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack31();
        }
        if (dist32 == dist42 - 1) {
            if (dist32 == 0 && seen32 == roundNum) {
                return Direction.EAST;}
            return backtrack32();
        }
        if (dist33 == dist42 - 1) {
            if (dist33 == 0 && seen33 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack33();
        }
        if (dist41 == dist42 - 1) {
            if (dist41 == 0 && seen41 == roundNum) {
                return Direction.NORTH;}
            return backtrack41();
        }
        if (dist43 == dist42 - 1) {
            if (dist43 == 0 && seen43 == roundNum) {
                return Direction.SOUTH;}
            return backtrack43();
        }
        if (dist51 == dist42 - 1) {
            if (dist51 == 0 && seen51 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack51();
        }
        if (dist52 == dist42 - 1) {
            if (dist52 == 0 && seen52 == roundNum) {
                return Direction.WEST;}
            return backtrack52();
        }
        if (dist53 == dist42 - 1) {
            if (dist53 == 0 && seen53 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack53();
        }
        return null;}
    public Direction backtrack43() {
        if (dist32 == dist43 - 1) {
            if (dist32 == 0 && seen32 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack32();
        }
        if (dist33 == dist43 - 1) {
            if (dist33 == 0 && seen33 == roundNum) {
                return Direction.EAST;}
            return backtrack33();
        }
        if (dist34 == dist43 - 1) {
            if (dist34 == 0 && seen34 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack34();
        }
        if (dist42 == dist43 - 1) {
            if (dist42 == 0 && seen42 == roundNum) {
                return Direction.NORTH;}
            return backtrack42();
        }
        if (dist44 == dist43 - 1) {
            if (dist44 == 0 && seen44 == roundNum) {
                return Direction.SOUTH;}
            return backtrack44();
        }
        if (dist52 == dist43 - 1) {
            if (dist52 == 0 && seen52 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack52();
        }
        if (dist53 == dist43 - 1) {
            if (dist53 == 0 && seen53 == roundNum) {
                return Direction.WEST;}
            return backtrack53();
        }
        if (dist54 == dist43 - 1) {
            if (dist54 == 0 && seen54 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack54();
        }
        return null;}
    public Direction backtrack44() {
        if (dist33 == dist44 - 1) {
            if (dist33 == 0 && seen33 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack33();
        }
        if (dist34 == dist44 - 1) {
            if (dist34 == 0 && seen34 == roundNum) {
                return Direction.EAST;}
            return backtrack34();
        }
        if (dist35 == dist44 - 1) {
            if (dist35 == 0 && seen35 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack35();
        }
        if (dist43 == dist44 - 1) {
            if (dist43 == 0 && seen43 == roundNum) {
                return Direction.NORTH;}
            return backtrack43();
        }
        if (dist45 == dist44 - 1) {
            if (dist45 == 0 && seen45 == roundNum) {
                return Direction.SOUTH;}
            return backtrack45();
        }
        if (dist53 == dist44 - 1) {
            if (dist53 == 0 && seen53 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack53();
        }
        if (dist54 == dist44 - 1) {
            if (dist54 == 0 && seen54 == roundNum) {
                return Direction.WEST;}
            return backtrack54();
        }
        if (dist55 == dist44 - 1) {
            if (dist55 == 0 && seen55 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack55();
        }
        return null;}
    public Direction backtrack45() {
        if (dist34 == dist45 - 1) {
            if (dist34 == 0 && seen34 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack34();
        }
        if (dist35 == dist45 - 1) {
            if (dist35 == 0 && seen35 == roundNum) {
                return Direction.EAST;}
            return backtrack35();
        }
        if (dist36 == dist45 - 1) {
            if (dist36 == 0 && seen36 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack36();
        }
        if (dist44 == dist45 - 1) {
            if (dist44 == 0 && seen44 == roundNum) {
                return Direction.NORTH;}
            return backtrack44();
        }
        if (dist46 == dist45 - 1) {
            if (dist46 == 0 && seen46 == roundNum) {
                return Direction.SOUTH;}
            return backtrack46();
        }
        if (dist54 == dist45 - 1) {
            if (dist54 == 0 && seen54 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack54();
        }
        if (dist55 == dist45 - 1) {
            if (dist55 == 0 && seen55 == roundNum) {
                return Direction.WEST;}
            return backtrack55();
        }
        if (dist56 == dist45 - 1) {
            if (dist56 == 0 && seen56 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack56();
        }
        return null;}
    public Direction backtrack46() {
        if (dist35 == dist46 - 1) {
            if (dist35 == 0 && seen35 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack35();
        }
        if (dist36 == dist46 - 1) {
            if (dist36 == 0 && seen36 == roundNum) {
                return Direction.EAST;}
            return backtrack36();
        }
        if (dist37 == dist46 - 1) {
            if (dist37 == 0 && seen37 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack37();
        }
        if (dist45 == dist46 - 1) {
            if (dist45 == 0 && seen45 == roundNum) {
                return Direction.NORTH;}
            return backtrack45();
        }
        if (dist47 == dist46 - 1) {
            if (dist47 == 0 && seen47 == roundNum) {
                return Direction.SOUTH;}
            return backtrack47();
        }
        if (dist55 == dist46 - 1) {
            if (dist55 == 0 && seen55 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack55();
        }
        if (dist56 == dist46 - 1) {
            if (dist56 == 0 && seen56 == roundNum) {
                return Direction.WEST;}
            return backtrack56();
        }
        if (dist57 == dist46 - 1) {
            if (dist57 == 0 && seen57 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack57();
        }
        return null;}
    public Direction backtrack47() {
        if (dist36 == dist47 - 1) {
            if (dist36 == 0 && seen36 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack36();
        }
        if (dist37 == dist47 - 1) {
            if (dist37 == 0 && seen37 == roundNum) {
                return Direction.EAST;}
            return backtrack37();
        }
        if (dist46 == dist47 - 1) {
            if (dist46 == 0 && seen46 == roundNum) {
                return Direction.NORTH;}
            return backtrack46();
        }
        if (dist56 == dist47 - 1) {
            if (dist56 == 0 && seen56 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack56();
        }
        if (dist57 == dist47 - 1) {
            if (dist57 == 0 && seen57 == roundNum) {
                return Direction.WEST;}
            return backtrack57();
        }
        return null;}
    public Direction backtrack51() {
        if (dist41 == dist51 - 1) {
            if (dist41 == 0 && seen41 == roundNum) {
                return Direction.EAST;}
            return backtrack41();
        }
        if (dist42 == dist51 - 1) {
            if (dist42 == 0 && seen42 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack42();
        }
        if (dist52 == dist51 - 1) {
            if (dist52 == 0 && seen52 == roundNum) {
                return Direction.SOUTH;}
            return backtrack52();
        }
        if (dist61 == dist51 - 1) {
            if (dist61 == 0 && seen61 == roundNum) {
                return Direction.WEST;}
            return backtrack61();
        }
        if (dist62 == dist51 - 1) {
            if (dist62 == 0 && seen62 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack62();
        }
        return null;}
    public Direction backtrack52() {
        if (dist41 == dist52 - 1) {
            if (dist41 == 0 && seen41 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack41();
        }
        if (dist42 == dist52 - 1) {
            if (dist42 == 0 && seen42 == roundNum) {
                return Direction.EAST;}
            return backtrack42();
        }
        if (dist43 == dist52 - 1) {
            if (dist43 == 0 && seen43 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack43();
        }
        if (dist51 == dist52 - 1) {
            if (dist51 == 0 && seen51 == roundNum) {
                return Direction.NORTH;}
            return backtrack51();
        }
        if (dist53 == dist52 - 1) {
            if (dist53 == 0 && seen53 == roundNum) {
                return Direction.SOUTH;}
            return backtrack53();
        }
        if (dist61 == dist52 - 1) {
            if (dist61 == 0 && seen61 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack61();
        }
        if (dist62 == dist52 - 1) {
            if (dist62 == 0 && seen62 == roundNum) {
                return Direction.WEST;}
            return backtrack62();
        }
        if (dist63 == dist52 - 1) {
            if (dist63 == 0 && seen63 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack63();
        }
        return null;}
    public Direction backtrack53() {
        if (dist42 == dist53 - 1) {
            if (dist42 == 0 && seen42 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack42();
        }
        if (dist43 == dist53 - 1) {
            if (dist43 == 0 && seen43 == roundNum) {
                return Direction.EAST;}
            return backtrack43();
        }
        if (dist44 == dist53 - 1) {
            if (dist44 == 0 && seen44 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack44();
        }
        if (dist52 == dist53 - 1) {
            if (dist52 == 0 && seen52 == roundNum) {
                return Direction.NORTH;}
            return backtrack52();
        }
        if (dist54 == dist53 - 1) {
            if (dist54 == 0 && seen54 == roundNum) {
                return Direction.SOUTH;}
            return backtrack54();
        }
        if (dist62 == dist53 - 1) {
            if (dist62 == 0 && seen62 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack62();
        }
        if (dist63 == dist53 - 1) {
            if (dist63 == 0 && seen63 == roundNum) {
                return Direction.WEST;}
            return backtrack63();
        }
        if (dist64 == dist53 - 1) {
            if (dist64 == 0 && seen64 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack64();
        }
        return null;}
    public Direction backtrack54() {
        if (dist43 == dist54 - 1) {
            if (dist43 == 0 && seen43 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack43();
        }
        if (dist44 == dist54 - 1) {
            if (dist44 == 0 && seen44 == roundNum) {
                return Direction.EAST;}
            return backtrack44();
        }
        if (dist45 == dist54 - 1) {
            if (dist45 == 0 && seen45 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack45();
        }
        if (dist53 == dist54 - 1) {
            if (dist53 == 0 && seen53 == roundNum) {
                return Direction.NORTH;}
            return backtrack53();
        }
        if (dist55 == dist54 - 1) {
            if (dist55 == 0 && seen55 == roundNum) {
                return Direction.SOUTH;}
            return backtrack55();
        }
        if (dist63 == dist54 - 1) {
            if (dist63 == 0 && seen63 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack63();
        }
        if (dist64 == dist54 - 1) {
            if (dist64 == 0 && seen64 == roundNum) {
                return Direction.WEST;}
            return backtrack64();
        }
        if (dist65 == dist54 - 1) {
            if (dist65 == 0 && seen65 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack65();
        }
        return null;}
    public Direction backtrack55() {
        if (dist44 == dist55 - 1) {
            if (dist44 == 0 && seen44 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack44();
        }
        if (dist45 == dist55 - 1) {
            if (dist45 == 0 && seen45 == roundNum) {
                return Direction.EAST;}
            return backtrack45();
        }
        if (dist46 == dist55 - 1) {
            if (dist46 == 0 && seen46 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack46();
        }
        if (dist54 == dist55 - 1) {
            if (dist54 == 0 && seen54 == roundNum) {
                return Direction.NORTH;}
            return backtrack54();
        }
        if (dist56 == dist55 - 1) {
            if (dist56 == 0 && seen56 == roundNum) {
                return Direction.SOUTH;}
            return backtrack56();
        }
        if (dist64 == dist55 - 1) {
            if (dist64 == 0 && seen64 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack64();
        }
        if (dist65 == dist55 - 1) {
            if (dist65 == 0 && seen65 == roundNum) {
                return Direction.WEST;}
            return backtrack65();
        }
        if (dist66 == dist55 - 1) {
            if (dist66 == 0 && seen66 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack66();
        }
        return null;}
    public Direction backtrack56() {
        if (dist45 == dist56 - 1) {
            if (dist45 == 0 && seen45 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack45();
        }
        if (dist46 == dist56 - 1) {
            if (dist46 == 0 && seen46 == roundNum) {
                return Direction.EAST;}
            return backtrack46();
        }
        if (dist47 == dist56 - 1) {
            if (dist47 == 0 && seen47 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack47();
        }
        if (dist55 == dist56 - 1) {
            if (dist55 == 0 && seen55 == roundNum) {
                return Direction.NORTH;}
            return backtrack55();
        }
        if (dist57 == dist56 - 1) {
            if (dist57 == 0 && seen57 == roundNum) {
                return Direction.SOUTH;}
            return backtrack57();
        }
        if (dist65 == dist56 - 1) {
            if (dist65 == 0 && seen65 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack65();
        }
        if (dist66 == dist56 - 1) {
            if (dist66 == 0 && seen66 == roundNum) {
                return Direction.WEST;}
            return backtrack66();
        }
        if (dist67 == dist56 - 1) {
            if (dist67 == 0 && seen67 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack67();
        }
        return null;}
    public Direction backtrack57() {
        if (dist46 == dist57 - 1) {
            if (dist46 == 0 && seen46 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack46();
        }
        if (dist47 == dist57 - 1) {
            if (dist47 == 0 && seen47 == roundNum) {
                return Direction.EAST;}
            return backtrack47();
        }
        if (dist56 == dist57 - 1) {
            if (dist56 == 0 && seen56 == roundNum) {
                return Direction.NORTH;}
            return backtrack56();
        }
        if (dist66 == dist57 - 1) {
            if (dist66 == 0 && seen66 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack66();
        }
        if (dist67 == dist57 - 1) {
            if (dist67 == 0 && seen67 == roundNum) {
                return Direction.WEST;}
            return backtrack67();
        }
        return null;}
    public Direction backtrack61() {
        if (dist51 == dist61 - 1) {
            if (dist51 == 0 && seen51 == roundNum) {
                return Direction.EAST;}
            return backtrack51();
        }
        if (dist52 == dist61 - 1) {
            if (dist52 == 0 && seen52 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack52();
        }
        if (dist62 == dist61 - 1) {
            if (dist62 == 0 && seen62 == roundNum) {
                return Direction.SOUTH;}
            return backtrack62();
        }
        if (dist72 == dist61 - 1) {
            if (dist72 == 0 && seen72 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack72();
        }
        return null;}
    public Direction backtrack62() {
        if (dist51 == dist62 - 1) {
            if (dist51 == 0 && seen51 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack51();
        }
        if (dist52 == dist62 - 1) {
            if (dist52 == 0 && seen52 == roundNum) {
                return Direction.EAST;}
            return backtrack52();
        }
        if (dist53 == dist62 - 1) {
            if (dist53 == 0 && seen53 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack53();
        }
        if (dist61 == dist62 - 1) {
            if (dist61 == 0 && seen61 == roundNum) {
                return Direction.NORTH;}
            return backtrack61();
        }
        if (dist63 == dist62 - 1) {
            if (dist63 == 0 && seen63 == roundNum) {
                return Direction.SOUTH;}
            return backtrack63();
        }
        if (dist72 == dist62 - 1) {
            if (dist72 == 0 && seen72 == roundNum) {
                return Direction.WEST;}
            return backtrack72();
        }
        if (dist73 == dist62 - 1) {
            if (dist73 == 0 && seen73 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack73();
        }
        return null;}
    public Direction backtrack63() {
        if (dist52 == dist63 - 1) {
            if (dist52 == 0 && seen52 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack52();
        }
        if (dist53 == dist63 - 1) {
            if (dist53 == 0 && seen53 == roundNum) {
                return Direction.EAST;}
            return backtrack53();
        }
        if (dist54 == dist63 - 1) {
            if (dist54 == 0 && seen54 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack54();
        }
        if (dist62 == dist63 - 1) {
            if (dist62 == 0 && seen62 == roundNum) {
                return Direction.NORTH;}
            return backtrack62();
        }
        if (dist64 == dist63 - 1) {
            if (dist64 == 0 && seen64 == roundNum) {
                return Direction.SOUTH;}
            return backtrack64();
        }
        if (dist72 == dist63 - 1) {
            if (dist72 == 0 && seen72 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack72();
        }
        if (dist73 == dist63 - 1) {
            if (dist73 == 0 && seen73 == roundNum) {
                return Direction.WEST;}
            return backtrack73();
        }
        if (dist74 == dist63 - 1) {
            if (dist74 == 0 && seen74 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack74();
        }
        return null;}
    public Direction backtrack64() {
        if (dist53 == dist64 - 1) {
            if (dist53 == 0 && seen53 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack53();
        }
        if (dist54 == dist64 - 1) {
            if (dist54 == 0 && seen54 == roundNum) {
                return Direction.EAST;}
            return backtrack54();
        }
        if (dist55 == dist64 - 1) {
            if (dist55 == 0 && seen55 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack55();
        }
        if (dist63 == dist64 - 1) {
            if (dist63 == 0 && seen63 == roundNum) {
                return Direction.NORTH;}
            return backtrack63();
        }
        if (dist65 == dist64 - 1) {
            if (dist65 == 0 && seen65 == roundNum) {
                return Direction.SOUTH;}
            return backtrack65();
        }
        if (dist73 == dist64 - 1) {
            if (dist73 == 0 && seen73 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack73();
        }
        if (dist74 == dist64 - 1) {
            if (dist74 == 0 && seen74 == roundNum) {
                return Direction.WEST;}
            return backtrack74();
        }
        if (dist75 == dist64 - 1) {
            if (dist75 == 0 && seen75 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack75();
        }
        return null;}
    public Direction backtrack65() {
        if (dist54 == dist65 - 1) {
            if (dist54 == 0 && seen54 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack54();
        }
        if (dist55 == dist65 - 1) {
            if (dist55 == 0 && seen55 == roundNum) {
                return Direction.EAST;}
            return backtrack55();
        }
        if (dist56 == dist65 - 1) {
            if (dist56 == 0 && seen56 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack56();
        }
        if (dist64 == dist65 - 1) {
            if (dist64 == 0 && seen64 == roundNum) {
                return Direction.NORTH;}
            return backtrack64();
        }
        if (dist66 == dist65 - 1) {
            if (dist66 == 0 && seen66 == roundNum) {
                return Direction.SOUTH;}
            return backtrack66();
        }
        if (dist74 == dist65 - 1) {
            if (dist74 == 0 && seen74 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack74();
        }
        if (dist75 == dist65 - 1) {
            if (dist75 == 0 && seen75 == roundNum) {
                return Direction.WEST;}
            return backtrack75();
        }
        if (dist76 == dist65 - 1) {
            if (dist76 == 0 && seen76 == roundNum) {
                return Direction.SOUTHWEST;}
            return backtrack76();
        }
        return null;}
    public Direction backtrack66() {
        if (dist55 == dist66 - 1) {
            if (dist55 == 0 && seen55 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack55();
        }
        if (dist56 == dist66 - 1) {
            if (dist56 == 0 && seen56 == roundNum) {
                return Direction.EAST;}
            return backtrack56();
        }
        if (dist57 == dist66 - 1) {
            if (dist57 == 0 && seen57 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack57();
        }
        if (dist65 == dist66 - 1) {
            if (dist65 == 0 && seen65 == roundNum) {
                return Direction.NORTH;}
            return backtrack65();
        }
        if (dist67 == dist66 - 1) {
            if (dist67 == 0 && seen67 == roundNum) {
                return Direction.SOUTH;}
            return backtrack67();
        }
        if (dist75 == dist66 - 1) {
            if (dist75 == 0 && seen75 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack75();
        }
        if (dist76 == dist66 - 1) {
            if (dist76 == 0 && seen76 == roundNum) {
                return Direction.WEST;}
            return backtrack76();
        }
        return null;}
    public Direction backtrack67() {
        if (dist56 == dist67 - 1) {
            if (dist56 == 0 && seen56 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack56();
        }
        if (dist57 == dist67 - 1) {
            if (dist57 == 0 && seen57 == roundNum) {
                return Direction.EAST;}
            return backtrack57();
        }
        if (dist66 == dist67 - 1) {
            if (dist66 == 0 && seen66 == roundNum) {
                return Direction.NORTH;}
            return backtrack66();
        }
        if (dist76 == dist67 - 1) {
            if (dist76 == 0 && seen76 == roundNum) {
                return Direction.NORTHWEST;}
            return backtrack76();
        }
        return null;}
    public Direction backtrack72() {
        if (dist61 == dist72 - 1) {
            if (dist61 == 0 && seen61 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack61();
        }
        if (dist62 == dist72 - 1) {
            if (dist62 == 0 && seen62 == roundNum) {
                return Direction.EAST;}
            return backtrack62();
        }
        if (dist63 == dist72 - 1) {
            if (dist63 == 0 && seen63 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack63();
        }
        if (dist73 == dist72 - 1) {
            if (dist73 == 0 && seen73 == roundNum) {
                return Direction.SOUTH;}
            return backtrack73();
        }
        return null;}
    public Direction backtrack73() {
        if (dist62 == dist73 - 1) {
            if (dist62 == 0 && seen62 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack62();
        }
        if (dist63 == dist73 - 1) {
            if (dist63 == 0 && seen63 == roundNum) {
                return Direction.EAST;}
            return backtrack63();
        }
        if (dist64 == dist73 - 1) {
            if (dist64 == 0 && seen64 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack64();
        }
        if (dist72 == dist73 - 1) {
            if (dist72 == 0 && seen72 == roundNum) {
                return Direction.NORTH;}
            return backtrack72();
        }
        if (dist74 == dist73 - 1) {
            if (dist74 == 0 && seen74 == roundNum) {
                return Direction.SOUTH;}
            return backtrack74();
        }
        return null;}
    public Direction backtrack74() {
        if (dist63 == dist74 - 1) {
            if (dist63 == 0 && seen63 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack63();
        }
        if (dist64 == dist74 - 1) {
            if (dist64 == 0 && seen64 == roundNum) {
                return Direction.EAST;}
            return backtrack64();
        }
        if (dist65 == dist74 - 1) {
            if (dist65 == 0 && seen65 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack65();
        }
        if (dist73 == dist74 - 1) {
            if (dist73 == 0 && seen73 == roundNum) {
                return Direction.NORTH;}
            return backtrack73();
        }
        if (dist75 == dist74 - 1) {
            if (dist75 == 0 && seen75 == roundNum) {
                return Direction.SOUTH;}
            return backtrack75();
        }
        return null;}
    public Direction backtrack75() {
        if (dist64 == dist75 - 1) {
            if (dist64 == 0 && seen64 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack64();
        }
        if (dist65 == dist75 - 1) {
            if (dist65 == 0 && seen65 == roundNum) {
                return Direction.EAST;}
            return backtrack65();
        }
        if (dist66 == dist75 - 1) {
            if (dist66 == 0 && seen66 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack66();
        }
        if (dist74 == dist75 - 1) {
            if (dist74 == 0 && seen74 == roundNum) {
                return Direction.NORTH;}
            return backtrack74();
        }
        if (dist76 == dist75 - 1) {
            if (dist76 == 0 && seen76 == roundNum) {
                return Direction.SOUTH;}
            return backtrack76();
        }
        return null;}
    public Direction backtrack76() {
        if (dist65 == dist76 - 1) {
            if (dist65 == 0 && seen65 == roundNum) {
                return Direction.NORTHEAST;}
            return backtrack65();
        }
        if (dist66 == dist76 - 1) {
            if (dist66 == 0 && seen66 == roundNum) {
                return Direction.EAST;}
            return backtrack66();
        }
        if (dist67 == dist76 - 1) {
            if (dist67 == 0 && seen67 == roundNum) {
                return Direction.SOUTHEAST;}
            return backtrack67();
        }
        if (dist75 == dist76 - 1) {
            if (dist75 == 0 && seen75 == roundNum) {
                return Direction.NORTH;}
            return backtrack75();
        }
        return null;}
    Direction tryBFS(MapLocation currentTarget) throws GameActionException {
        reset();
        seen12 = 0;
        arr12 = 0;
        dist12 = 0;
        seen13 = 0;
        arr13 = 0;
        dist13 = 0;
        seen14 = 0;
        arr14 = 0;
        dist14 = 0;
        seen15 = 0;
        arr15 = 0;
        dist15 = 0;
        seen16 = 0;
        arr16 = 0;
        dist16 = 0;
        seen21 = 0;
        arr21 = 0;
        dist21 = 0;
        seen22 = 0;
        arr22 = 0;
        dist22 = 0;
        seen23 = 0;
        arr23 = 0;
        dist23 = 0;
        seen24 = 0;
        arr24 = 0;
        dist24 = 0;
        seen25 = 0;
        arr25 = 0;
        dist25 = 0;
        seen26 = 0;
        arr26 = 0;
        dist26 = 0;
        seen27 = 0;
        arr27 = 0;
        dist27 = 0;
        seen31 = 0;
        arr31 = 0;
        dist31 = 0;
        seen32 = 0;
        arr32 = 0;
        dist32 = 0;
        seen33 = 0;
        arr33 = 0;
        dist33 = 0;
        seen34 = 0;
        arr34 = 0;
        dist34 = 0;
        seen35 = 0;
        arr35 = 0;
        dist35 = 0;
        seen36 = 0;
        arr36 = 0;
        dist36 = 0;
        seen37 = 0;
        arr37 = 0;
        dist37 = 0;
        seen41 = 0;
        arr41 = 0;
        dist41 = 0;
        seen42 = 0;
        arr42 = 0;
        dist42 = 0;
        seen43 = 0;
        arr43 = 0;
        dist43 = 0;
        seen44 = 0;
        arr44 = 0;
        dist44 = 0;
        seen45 = 0;
        arr45 = 0;
        dist45 = 0;
        seen46 = 0;
        arr46 = 0;
        dist46 = 0;
        seen47 = 0;
        arr47 = 0;
        dist47 = 0;
        seen51 = 0;
        arr51 = 0;
        dist51 = 0;
        seen52 = 0;
        arr52 = 0;
        dist52 = 0;
        seen53 = 0;
        arr53 = 0;
        dist53 = 0;
        seen54 = 0;
        arr54 = 0;
        dist54 = 0;
        seen55 = 0;
        arr55 = 0;
        dist55 = 0;
        seen56 = 0;
        arr56 = 0;
        dist56 = 0;
        seen57 = 0;
        arr57 = 0;
        dist57 = 0;
        seen61 = 0;
        arr61 = 0;
        dist61 = 0;
        seen62 = 0;
        arr62 = 0;
        dist62 = 0;
        seen63 = 0;
        arr63 = 0;
        dist63 = 0;
        seen64 = 0;
        arr64 = 0;
        dist64 = 0;
        seen65 = 0;
        arr65 = 0;
        dist65 = 0;
        seen66 = 0;
        arr66 = 0;
        dist66 = 0;
        seen67 = 0;
        arr67 = 0;
        dist67 = 0;
        seen72 = 0;
        arr72 = 0;
        dist72 = 0;
        seen73 = 0;
        arr73 = 0;
        dist73 = 0;
        seen74 = 0;
        arr74 = 0;
        dist74 = 0;
        seen75 = 0;
        arr75 = 0;
        dist75 = 0;
        seen76 = 0;
        arr76 = 0;
        dist76 = 0;

        MapInfo[] infos = rc.senseNearbyMapInfos();


        RobotInfo[] robotInfos = rc.senseNearbyRobots();

        MapLocation starting = rc.getLocation();
        MapLocation mapLocation;

        System.out.println("A: " + Clock.getBytecodesLeft());
        for (MapInfo info : infos) {
            mapLocation = info.getMapLocation();

            int x = mapLocation.x - starting.x + 4, y = mapLocation.y - starting.y + 4;

            switch (10 * x + y) {
                case 12:
                    if (!info.isPassable()) {
                        arr12 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr12 = 2;
                    } else {
                        arr12 = 1;
                    }
                    break;
                case 13:
                    if (!info.isPassable()) {
                        arr13 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr13 = 2;
                    } else {
                        arr13 = 1;
                    }
                    break;
                case 14:
                    if (!info.isPassable()) {
                        arr14 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr14 = 2;
                    } else {
                        arr14 = 1;
                    }
                    break;
                case 15:
                    if (!info.isPassable()) {
                        arr15 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr15 = 2;
                    } else {
                        arr15 = 1;
                    }
                    break;
                case 16:
                    if (!info.isPassable()) {
                        arr16 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr16 = 2;
                    } else {
                        arr16 = 1;
                    }
                    break;
                case 21:
                    if (!info.isPassable()) {
                        arr21 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr21 = 2;
                    } else {
                        arr21 = 1;
                    }
                    break;
                case 22:
                    if (!info.isPassable()) {
                        arr22 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr22 = 2;
                    } else {
                        arr22 = 1;
                    }
                    break;
                case 23:
                    if (!info.isPassable()) {
                        arr23 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr23 = 2;
                    } else {
                        arr23 = 1;
                    }
                    break;
                case 24:
                    if (!info.isPassable()) {
                        arr24 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr24 = 2;
                    } else {
                        arr24 = 1;
                    }
                    break;
                case 25:
                    if (!info.isPassable()) {
                        arr25 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr25 = 2;
                    } else {
                        arr25 = 1;
                    }
                    break;
                case 26:
                    if (!info.isPassable()) {
                        arr26 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr26 = 2;
                    } else {
                        arr26 = 1;
                    }
                    break;
                case 27:
                    if (!info.isPassable()) {
                        arr27 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr27 = 2;
                    } else {
                        arr27 = 1;
                    }
                    break;
                case 31:
                    if (!info.isPassable()) {
                        arr31 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr31 = 2;
                    } else {
                        arr31 = 1;
                    }
                    break;
                case 32:
                    if (!info.isPassable()) {
                        arr32 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr32 = 2;
                    } else {
                        arr32 = 1;
                    }
                    break;
                case 33:
                    if (!info.isPassable()) {
                        arr33 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr33 = 2;
                    } else {
                        arr33 = 1;
                    }
                    break;
                case 34:
                    if (!info.isPassable()) {
                        arr34 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr34 = 2;
                    } else {
                        arr34 = 1;
                    }
                    break;
                case 35:
                    if (!info.isPassable()) {
                        arr35 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr35 = 2;
                    } else {
                        arr35 = 1;
                    }
                    break;
                case 36:
                    if (!info.isPassable()) {
                        arr36 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr36 = 2;
                    } else {
                        arr36 = 1;
                    }
                    break;
                case 37:
                    if (!info.isPassable()) {
                        arr37 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr37 = 2;
                    } else {
                        arr37 = 1;
                    }
                    break;
                case 41:
                    if (!info.isPassable()) {
                        arr41 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr41 = 2;
                    } else {
                        arr41 = 1;
                    }
                    break;
                case 42:
                    if (!info.isPassable()) {
                        arr42 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr42 = 2;
                    } else {
                        arr42 = 1;
                    }
                    break;
                case 43:
                    if (!info.isPassable()) {
                        arr43 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr43 = 2;
                    } else {
                        arr43 = 1;
                    }
                    break;
                case 44:
                    if (!info.isPassable()) {
                        arr44 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr44 = 2;
                    } else {
                        arr44 = 1;
                    }
                    break;
                case 45:
                    if (!info.isPassable()) {
                        arr45 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr45 = 2;
                    } else {
                        arr45 = 1;
                    }
                    break;
                case 46:
                    if (!info.isPassable()) {
                        arr46 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr46 = 2;
                    } else {
                        arr46 = 1;
                    }
                    break;
                case 47:
                    if (!info.isPassable()) {
                        arr47 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr47 = 2;
                    } else {
                        arr47 = 1;
                    }
                    break;
                case 51:
                    if (!info.isPassable()) {
                        arr51 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr51 = 2;
                    } else {
                        arr51 = 1;
                    }
                    break;
                case 52:
                    if (!info.isPassable()) {
                        arr52 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr52 = 2;
                    } else {
                        arr52 = 1;
                    }
                    break;
                case 53:
                    if (!info.isPassable()) {
                        arr53 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr53 = 2;
                    } else {
                        arr53 = 1;
                    }
                    break;
                case 54:
                    if (!info.isPassable()) {
                        arr54 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr54 = 2;
                    } else {
                        arr54 = 1;
                    }
                    break;
                case 55:
                    if (!info.isPassable()) {
                        arr55 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr55 = 2;
                    } else {
                        arr55 = 1;
                    }
                    break;
                case 56:
                    if (!info.isPassable()) {
                        arr56 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr56 = 2;
                    } else {
                        arr56 = 1;
                    }
                    break;
                case 57:
                    if (!info.isPassable()) {
                        arr57 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr57 = 2;
                    } else {
                        arr57 = 1;
                    }
                    break;
                case 61:
                    if (!info.isPassable()) {
                        arr61 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr61 = 2;
                    } else {
                        arr61 = 1;
                    }
                    break;
                case 62:
                    if (!info.isPassable()) {
                        arr62 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr62 = 2;
                    } else {
                        arr62 = 1;
                    }
                    break;
                case 63:
                    if (!info.isPassable()) {
                        arr63 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr63 = 2;
                    } else {
                        arr63 = 1;
                    }
                    break;
                case 64:
                    if (!info.isPassable()) {
                        arr64 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr64 = 2;
                    } else {
                        arr64 = 1;
                    }
                    break;
                case 65:
                    if (!info.isPassable()) {
                        arr65 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr65 = 2;
                    } else {
                        arr65 = 1;
                    }
                    break;
                case 66:
                    if (!info.isPassable()) {
                        arr66 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr66 = 2;
                    } else {
                        arr66 = 1;
                    }
                    break;
                case 67:
                    if (!info.isPassable()) {
                        arr67 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr67 = 2;
                    } else {
                        arr67 = 1;
                    }
                    break;
                case 72:
                    if (!info.isPassable()) {
                        arr72 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr72 = 2;
                    } else {
                        arr72 = 1;
                    }
                    break;
                case 73:
                    if (!info.isPassable()) {
                        arr73 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr73 = 2;
                    } else {
                        arr73 = 1;
                    }
                    break;
                case 74:
                    if (!info.isPassable()) {
                        arr74 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr74 = 2;
                    } else {
                        arr74 = 1;
                    }
                    break;
                case 75:
                    if (!info.isPassable()) {
                        arr75 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr75 = 2;
                    } else {
                        arr75 = 1;
                    }
                    break;
                case 76:
                    if (!info.isPassable()) {
                        arr76 = 2;
                    } else if (info.getCurrentDirection() != Direction.CENTER) {
                        arr76 = 2;
                    } else {
                        arr76 = 1;
                    }
                    break;
            }
        }
        for (RobotInfo robotInfo : robotInfos) {
            mapLocation = robotInfo.getLocation();
            int x = mapLocation.x - starting.x + 4, y = mapLocation.y - starting.y + 4;
            switch(10 * x + y) {
                case 12:
                    arr12 = 2;
                    break;
                case 13:
                    arr13 = 2;
                    break;
                case 14:
                    arr14 = 2;
                    break;
                case 15:
                    arr15 = 2;
                    break;
                case 16:
                    arr16 = 2;
                    break;
                case 21:
                    arr21 = 2;
                    break;
                case 22:
                    arr22 = 2;
                    break;
                case 23:
                    arr23 = 2;
                    break;
                case 24:
                    arr24 = 2;
                    break;
                case 25:
                    arr25 = 2;
                    break;
                case 26:
                    arr26 = 2;
                    break;
                case 27:
                    arr27 = 2;
                    break;
                case 31:
                    arr31 = 2;
                    break;
                case 32:
                    arr32 = 2;
                    break;
                case 33:
                    arr33 = 2;
                    break;
                case 34:
                    arr34 = 2;
                    break;
                case 35:
                    arr35 = 2;
                    break;
                case 36:
                    arr36 = 2;
                    break;
                case 37:
                    arr37 = 2;
                    break;
                case 41:
                    arr41 = 2;
                    break;
                case 42:
                    arr42 = 2;
                    break;
                case 43:
                    arr43 = 2;
                    break;
                case 44:
                    arr44 = 2;
                    break;
                case 45:
                    arr45 = 2;
                    break;
                case 46:
                    arr46 = 2;
                    break;
                case 47:
                    arr47 = 2;
                    break;
                case 51:
                    arr51 = 2;
                    break;
                case 52:
                    arr52 = 2;
                    break;
                case 53:
                    arr53 = 2;
                    break;
                case 54:
                    arr54 = 2;
                    break;
                case 55:
                    arr55 = 2;
                    break;
                case 56:
                    arr56 = 2;
                    break;
                case 57:
                    arr57 = 2;
                    break;
                case 61:
                    arr61 = 2;
                    break;
                case 62:
                    arr62 = 2;
                    break;
                case 63:
                    arr63 = 2;
                    break;
                case 64:
                    arr64 = 2;
                    break;
                case 65:
                    arr65 = 2;
                    break;
                case 66:
                    arr66 = 2;
                    break;
                case 67:
                    arr67 = 2;
                    break;
                case 72:
                    arr72 = 2;
                    break;
                case 73:
                    arr73 = 2;
                    break;
                case 74:
                    arr74 = 2;
                    break;
                case 75:
                    arr75 = 2;
                    break;
                case 76:
                    arr76 = 2;
                    break;
            }
        }
        System.out.println(currentTarget + " " + rc.getLocation());
        switch((currentTarget.x - rc.getLocation().x + 4) * 10 + (currentTarget.y - rc.getLocation().y + 4)) {
            case 12:
                arr12 = 1;
                System.out.println("1, 2");
                break;
            case 13:
                arr13 = 1;
                System.out.println("1, 3");
                break;
            case 14:
                arr14 = 1;
                System.out.println("1, 4");
                break;
            case 15:
                arr15 = 1;
                System.out.println("1, 5");
                break;
            case 16:
                arr16 = 1;
                System.out.println("1, 6");
                break;
            case 21:
                arr21 = 1;
                System.out.println("2, 1");
                break;
            case 22:
                arr22 = 1;
                System.out.println("2, 2");
                break;
            case 23:
                arr23 = 1;
                System.out.println("2, 3");
                break;
            case 24:
                arr24 = 1;
                System.out.println("2, 4");
                break;
            case 25:
                arr25 = 1;
                System.out.println("2, 5");
                break;
            case 26:
                arr26 = 1;
                System.out.println("2, 6");
                break;
            case 27:
                arr27 = 1;
                System.out.println("2, 7");
                break;
            case 31:
                arr31 = 1;
                System.out.println("3, 1");
                break;
            case 32:
                arr32 = 1;
                System.out.println("3, 2");
                break;
            case 33:
                arr33 = 1;
                System.out.println("3, 3");
                break;
            case 34:
                arr34 = 1;
                System.out.println("3, 4");
                break;
            case 35:
                arr35 = 1;
                System.out.println("3, 5");
                break;
            case 36:
                arr36 = 1;
                System.out.println("3, 6");
                break;
            case 37:
                arr37 = 1;
                System.out.println("3, 7");
                break;
            case 41:
                arr41 = 1;
                System.out.println("4, 1");
                break;
            case 42:
                arr42 = 1;
                System.out.println("4, 2");
                break;
            case 43:
                arr43 = 1;
                System.out.println("4, 3");
                break;
            case 44:
                arr44 = 1;
                System.out.println("4, 4");
                break;
            case 45:
                arr45 = 1;
                System.out.println("4, 5");
                break;
            case 46:
                arr46 = 1;
                System.out.println("4, 6");
                break;
            case 47:
                arr47 = 1;
                System.out.println("4, 7");
                break;
            case 51:
                arr51 = 1;
                System.out.println("5, 1");
                break;
            case 52:
                arr52 = 1;
                System.out.println("5, 2");
                break;
            case 53:
                arr53 = 1;
                System.out.println("5, 3");
                break;
            case 54:
                arr54 = 1;
                System.out.println("5, 4");
                break;
            case 55:
                arr55 = 1;
                System.out.println("5, 5");
                break;
            case 56:
                arr56 = 1;
                System.out.println("5, 6");
                break;
            case 57:
                arr57 = 1;
                System.out.println("5, 7");
                break;
            case 61:
                arr61 = 1;
                System.out.println("6, 1");
                break;
            case 62:
                arr62 = 1;
                System.out.println("6, 2");
                break;
            case 63:
                arr63 = 1;
                System.out.println("6, 3");
                break;
            case 64:
                arr64 = 1;
                System.out.println("6, 4");
                break;
            case 65:
                arr65 = 1;
                System.out.println("6, 5");
                break;
            case 66:
                arr66 = 1;
                System.out.println("6, 6");
                break;
            case 67:
                arr67 = 1;
                System.out.println("6, 7");
                break;
            case 72:
                arr72 = 1;
                System.out.println("7, 2");
                break;
            case 73:
                arr73 = 1;
                System.out.println("7, 3");
                break;
            case 74:
                arr74 = 1;
                System.out.println("7, 4");
                break;
            case 75:
                arr75 = 1;
                System.out.println("7, 5");
                break;
            case 76:
                arr76 = 1;
                System.out.println("7, 6");
                break;
        }
        System.out.println("B: " + Clock.getBytecodesLeft());


        int dx = 4, dy = 4;

        Queue.queuePush((dx << 4) + dy);
        roundNum = rc.getRoundNum();
        seen44 = roundNum;
        dist44 = 0;

        while (!Queue.queueEmpty()) {
            int current = Queue.queuePop();

            switch (current) {
                case 18:
                    if(!(arr12 == 0 || arr12 == 2)) {
                        if (arr12 == 1 && seen12 != roundNum) {
                            seen12 = roundNum;
                            Queue.queuePush(18);
                            dist12 = dist12 + 1;
                        }}
                    if(!(arr13 == 0 || arr13 == 2)) {
                        if (arr13 == 1 && seen13 != roundNum) {
                            seen13 = roundNum;
                            Queue.queuePush(19);
                            dist13 = dist12 + 1;
                        }}
                    if(!(arr21 == 0 || arr21 == 2)) {
                        if (arr21 == 1 && seen21 != roundNum) {
                            seen21 = roundNum;
                            Queue.queuePush(33);
                            dist21 = dist12 + 1;
                        }}
                    if(!(arr22 == 0 || arr22 == 2)) {
                        if (arr22 == 1 && seen22 != roundNum) {
                            seen22 = roundNum;
                            Queue.queuePush(34);
                            dist22 = dist12 + 1;
                        }}
                    if(!(arr23 == 0 || arr23 == 2)) {
                        if (arr23 == 1 && seen23 != roundNum) {
                            seen23 = roundNum;
                            Queue.queuePush(35);
                            dist23 = dist12 + 1;
                        }}
                    break;
                case 19:
                    if(!(arr12 == 0 || arr12 == 2)) {
                        if (arr12 == 1 && seen12 != roundNum) {
                            seen12 = roundNum;
                            Queue.queuePush(18);
                            dist12 = dist13 + 1;
                        }}
                    if(!(arr13 == 0 || arr13 == 2)) {
                        if (arr13 == 1 && seen13 != roundNum) {
                            seen13 = roundNum;
                            Queue.queuePush(19);
                            dist13 = dist13 + 1;
                        }}
                    if(!(arr14 == 0 || arr14 == 2)) {
                        if (arr14 == 1 && seen14 != roundNum) {
                            seen14 = roundNum;
                            Queue.queuePush(20);
                            dist14 = dist13 + 1;
                        }}
                    if(!(arr22 == 0 || arr22 == 2)) {
                        if (arr22 == 1 && seen22 != roundNum) {
                            seen22 = roundNum;
                            Queue.queuePush(34);
                            dist22 = dist13 + 1;
                        }}
                    if(!(arr23 == 0 || arr23 == 2)) {
                        if (arr23 == 1 && seen23 != roundNum) {
                            seen23 = roundNum;
                            Queue.queuePush(35);
                            dist23 = dist13 + 1;
                        }}
                    if(!(arr24 == 0 || arr24 == 2)) {
                        if (arr24 == 1 && seen24 != roundNum) {
                            seen24 = roundNum;
                            Queue.queuePush(36);
                            dist24 = dist13 + 1;
                        }}
                    break;
                case 20:
                    if(!(arr13 == 0 || arr13 == 2)) {
                        if (arr13 == 1 && seen13 != roundNum) {
                            seen13 = roundNum;
                            Queue.queuePush(19);
                            dist13 = dist14 + 1;
                        }}
                    if(!(arr14 == 0 || arr14 == 2)) {
                        if (arr14 == 1 && seen14 != roundNum) {
                            seen14 = roundNum;
                            Queue.queuePush(20);
                            dist14 = dist14 + 1;
                        }}
                    if(!(arr15 == 0 || arr15 == 2)) {
                        if (arr15 == 1 && seen15 != roundNum) {
                            seen15 = roundNum;
                            Queue.queuePush(21);
                            dist15 = dist14 + 1;
                        }}
                    if(!(arr23 == 0 || arr23 == 2)) {
                        if (arr23 == 1 && seen23 != roundNum) {
                            seen23 = roundNum;
                            Queue.queuePush(35);
                            dist23 = dist14 + 1;
                        }}
                    if(!(arr24 == 0 || arr24 == 2)) {
                        if (arr24 == 1 && seen24 != roundNum) {
                            seen24 = roundNum;
                            Queue.queuePush(36);
                            dist24 = dist14 + 1;
                        }}
                    if(!(arr25 == 0 || arr25 == 2)) {
                        if (arr25 == 1 && seen25 != roundNum) {
                            seen25 = roundNum;
                            Queue.queuePush(37);
                            dist25 = dist14 + 1;
                        }}
                    break;
                case 21:
                    if(!(arr14 == 0 || arr14 == 2)) {
                        if (arr14 == 1 && seen14 != roundNum) {
                            seen14 = roundNum;
                            Queue.queuePush(20);
                            dist14 = dist15 + 1;
                        }}
                    if(!(arr15 == 0 || arr15 == 2)) {
                        if (arr15 == 1 && seen15 != roundNum) {
                            seen15 = roundNum;
                            Queue.queuePush(21);
                            dist15 = dist15 + 1;
                        }}
                    if(!(arr16 == 0 || arr16 == 2)) {
                        if (arr16 == 1 && seen16 != roundNum) {
                            seen16 = roundNum;
                            Queue.queuePush(22);
                            dist16 = dist15 + 1;
                        }}
                    if(!(arr24 == 0 || arr24 == 2)) {
                        if (arr24 == 1 && seen24 != roundNum) {
                            seen24 = roundNum;
                            Queue.queuePush(36);
                            dist24 = dist15 + 1;
                        }}
                    if(!(arr25 == 0 || arr25 == 2)) {
                        if (arr25 == 1 && seen25 != roundNum) {
                            seen25 = roundNum;
                            Queue.queuePush(37);
                            dist25 = dist15 + 1;
                        }}
                    if(!(arr26 == 0 || arr26 == 2)) {
                        if (arr26 == 1 && seen26 != roundNum) {
                            seen26 = roundNum;
                            Queue.queuePush(38);
                            dist26 = dist15 + 1;
                        }}
                    break;
                case 22:
                    if(!(arr15 == 0 || arr15 == 2)) {
                        if (arr15 == 1 && seen15 != roundNum) {
                            seen15 = roundNum;
                            Queue.queuePush(21);
                            dist15 = dist16 + 1;
                        }}
                    if(!(arr16 == 0 || arr16 == 2)) {
                        if (arr16 == 1 && seen16 != roundNum) {
                            seen16 = roundNum;
                            Queue.queuePush(22);
                            dist16 = dist16 + 1;
                        }}
                    if(!(arr25 == 0 || arr25 == 2)) {
                        if (arr25 == 1 && seen25 != roundNum) {
                            seen25 = roundNum;
                            Queue.queuePush(37);
                            dist25 = dist16 + 1;
                        }}
                    if(!(arr26 == 0 || arr26 == 2)) {
                        if (arr26 == 1 && seen26 != roundNum) {
                            seen26 = roundNum;
                            Queue.queuePush(38);
                            dist26 = dist16 + 1;
                        }}
                    if(!(arr27 == 0 || arr27 == 2)) {
                        if (arr27 == 1 && seen27 != roundNum) {
                            seen27 = roundNum;
                            Queue.queuePush(39);
                            dist27 = dist16 + 1;
                        }}
                    break;
                case 33:
                    if(!(arr12 == 0 || arr12 == 2)) {
                        if (arr12 == 1 && seen12 != roundNum) {
                            seen12 = roundNum;
                            Queue.queuePush(18);
                            dist12 = dist21 + 1;
                        }}
                    if(!(arr21 == 0 || arr21 == 2)) {
                        if (arr21 == 1 && seen21 != roundNum) {
                            seen21 = roundNum;
                            Queue.queuePush(33);
                            dist21 = dist21 + 1;
                        }}
                    if(!(arr22 == 0 || arr22 == 2)) {
                        if (arr22 == 1 && seen22 != roundNum) {
                            seen22 = roundNum;
                            Queue.queuePush(34);
                            dist22 = dist21 + 1;
                        }}
                    if(!(arr31 == 0 || arr31 == 2)) {
                        if (arr31 == 1 && seen31 != roundNum) {
                            seen31 = roundNum;
                            Queue.queuePush(49);
                            dist31 = dist21 + 1;
                        }}
                    if(!(arr32 == 0 || arr32 == 2)) {
                        if (arr32 == 1 && seen32 != roundNum) {
                            seen32 = roundNum;
                            Queue.queuePush(50);
                            dist32 = dist21 + 1;
                        }}
                    break;
                case 34:
                    if(!(arr12 == 0 || arr12 == 2)) {
                        if (arr12 == 1 && seen12 != roundNum) {
                            seen12 = roundNum;
                            Queue.queuePush(18);
                            dist12 = dist22 + 1;
                        }}
                    if(!(arr13 == 0 || arr13 == 2)) {
                        if (arr13 == 1 && seen13 != roundNum) {
                            seen13 = roundNum;
                            Queue.queuePush(19);
                            dist13 = dist22 + 1;
                        }}
                    if(!(arr21 == 0 || arr21 == 2)) {
                        if (arr21 == 1 && seen21 != roundNum) {
                            seen21 = roundNum;
                            Queue.queuePush(33);
                            dist21 = dist22 + 1;
                        }}
                    if(!(arr22 == 0 || arr22 == 2)) {
                        if (arr22 == 1 && seen22 != roundNum) {
                            seen22 = roundNum;
                            Queue.queuePush(34);
                            dist22 = dist22 + 1;
                        }}
                    if(!(arr23 == 0 || arr23 == 2)) {
                        if (arr23 == 1 && seen23 != roundNum) {
                            seen23 = roundNum;
                            Queue.queuePush(35);
                            dist23 = dist22 + 1;
                        }}
                    if(!(arr31 == 0 || arr31 == 2)) {
                        if (arr31 == 1 && seen31 != roundNum) {
                            seen31 = roundNum;
                            Queue.queuePush(49);
                            dist31 = dist22 + 1;
                        }}
                    if(!(arr32 == 0 || arr32 == 2)) {
                        if (arr32 == 1 && seen32 != roundNum) {
                            seen32 = roundNum;
                            Queue.queuePush(50);
                            dist32 = dist22 + 1;
                        }}
                    if(!(arr33 == 0 || arr33 == 2)) {
                        if (arr33 == 1 && seen33 != roundNum) {
                            seen33 = roundNum;
                            Queue.queuePush(51);
                            dist33 = dist22 + 1;
                        }}
                    break;
                case 35:
                    if(!(arr12 == 0 || arr12 == 2)) {
                        if (arr12 == 1 && seen12 != roundNum) {
                            seen12 = roundNum;
                            Queue.queuePush(18);
                            dist12 = dist23 + 1;
                        }}
                    if(!(arr13 == 0 || arr13 == 2)) {
                        if (arr13 == 1 && seen13 != roundNum) {
                            seen13 = roundNum;
                            Queue.queuePush(19);
                            dist13 = dist23 + 1;
                        }}
                    if(!(arr14 == 0 || arr14 == 2)) {
                        if (arr14 == 1 && seen14 != roundNum) {
                            seen14 = roundNum;
                            Queue.queuePush(20);
                            dist14 = dist23 + 1;
                        }}
                    if(!(arr22 == 0 || arr22 == 2)) {
                        if (arr22 == 1 && seen22 != roundNum) {
                            seen22 = roundNum;
                            Queue.queuePush(34);
                            dist22 = dist23 + 1;
                        }}
                    if(!(arr23 == 0 || arr23 == 2)) {
                        if (arr23 == 1 && seen23 != roundNum) {
                            seen23 = roundNum;
                            Queue.queuePush(35);
                            dist23 = dist23 + 1;
                        }}
                    if(!(arr24 == 0 || arr24 == 2)) {
                        if (arr24 == 1 && seen24 != roundNum) {
                            seen24 = roundNum;
                            Queue.queuePush(36);
                            dist24 = dist23 + 1;
                        }}
                    if(!(arr32 == 0 || arr32 == 2)) {
                        if (arr32 == 1 && seen32 != roundNum) {
                            seen32 = roundNum;
                            Queue.queuePush(50);
                            dist32 = dist23 + 1;
                        }}
                    if(!(arr33 == 0 || arr33 == 2)) {
                        if (arr33 == 1 && seen33 != roundNum) {
                            seen33 = roundNum;
                            Queue.queuePush(51);
                            dist33 = dist23 + 1;
                        }}
                    if(!(arr34 == 0 || arr34 == 2)) {
                        if (arr34 == 1 && seen34 != roundNum) {
                            seen34 = roundNum;
                            Queue.queuePush(52);
                            dist34 = dist23 + 1;
                        }}
                    break;
                case 36:
                    if(!(arr13 == 0 || arr13 == 2)) {
                        if (arr13 == 1 && seen13 != roundNum) {
                            seen13 = roundNum;
                            Queue.queuePush(19);
                            dist13 = dist24 + 1;
                        }}
                    if(!(arr14 == 0 || arr14 == 2)) {
                        if (arr14 == 1 && seen14 != roundNum) {
                            seen14 = roundNum;
                            Queue.queuePush(20);
                            dist14 = dist24 + 1;
                        }}
                    if(!(arr15 == 0 || arr15 == 2)) {
                        if (arr15 == 1 && seen15 != roundNum) {
                            seen15 = roundNum;
                            Queue.queuePush(21);
                            dist15 = dist24 + 1;
                        }}
                    if(!(arr23 == 0 || arr23 == 2)) {
                        if (arr23 == 1 && seen23 != roundNum) {
                            seen23 = roundNum;
                            Queue.queuePush(35);
                            dist23 = dist24 + 1;
                        }}
                    if(!(arr24 == 0 || arr24 == 2)) {
                        if (arr24 == 1 && seen24 != roundNum) {
                            seen24 = roundNum;
                            Queue.queuePush(36);
                            dist24 = dist24 + 1;
                        }}
                    if(!(arr25 == 0 || arr25 == 2)) {
                        if (arr25 == 1 && seen25 != roundNum) {
                            seen25 = roundNum;
                            Queue.queuePush(37);
                            dist25 = dist24 + 1;
                        }}
                    if(!(arr33 == 0 || arr33 == 2)) {
                        if (arr33 == 1 && seen33 != roundNum) {
                            seen33 = roundNum;
                            Queue.queuePush(51);
                            dist33 = dist24 + 1;
                        }}
                    if(!(arr34 == 0 || arr34 == 2)) {
                        if (arr34 == 1 && seen34 != roundNum) {
                            seen34 = roundNum;
                            Queue.queuePush(52);
                            dist34 = dist24 + 1;
                        }}
                    if(!(arr35 == 0 || arr35 == 2)) {
                        if (arr35 == 1 && seen35 != roundNum) {
                            seen35 = roundNum;
                            Queue.queuePush(53);
                            dist35 = dist24 + 1;
                        }}
                    break;
                case 37:
                    if(!(arr14 == 0 || arr14 == 2)) {
                        if (arr14 == 1 && seen14 != roundNum) {
                            seen14 = roundNum;
                            Queue.queuePush(20);
                            dist14 = dist25 + 1;
                        }}
                    if(!(arr15 == 0 || arr15 == 2)) {
                        if (arr15 == 1 && seen15 != roundNum) {
                            seen15 = roundNum;
                            Queue.queuePush(21);
                            dist15 = dist25 + 1;
                        }}
                    if(!(arr16 == 0 || arr16 == 2)) {
                        if (arr16 == 1 && seen16 != roundNum) {
                            seen16 = roundNum;
                            Queue.queuePush(22);
                            dist16 = dist25 + 1;
                        }}
                    if(!(arr24 == 0 || arr24 == 2)) {
                        if (arr24 == 1 && seen24 != roundNum) {
                            seen24 = roundNum;
                            Queue.queuePush(36);
                            dist24 = dist25 + 1;
                        }}
                    if(!(arr25 == 0 || arr25 == 2)) {
                        if (arr25 == 1 && seen25 != roundNum) {
                            seen25 = roundNum;
                            Queue.queuePush(37);
                            dist25 = dist25 + 1;
                        }}
                    if(!(arr26 == 0 || arr26 == 2)) {
                        if (arr26 == 1 && seen26 != roundNum) {
                            seen26 = roundNum;
                            Queue.queuePush(38);
                            dist26 = dist25 + 1;
                        }}
                    if(!(arr34 == 0 || arr34 == 2)) {
                        if (arr34 == 1 && seen34 != roundNum) {
                            seen34 = roundNum;
                            Queue.queuePush(52);
                            dist34 = dist25 + 1;
                        }}
                    if(!(arr35 == 0 || arr35 == 2)) {
                        if (arr35 == 1 && seen35 != roundNum) {
                            seen35 = roundNum;
                            Queue.queuePush(53);
                            dist35 = dist25 + 1;
                        }}
                    if(!(arr36 == 0 || arr36 == 2)) {
                        if (arr36 == 1 && seen36 != roundNum) {
                            seen36 = roundNum;
                            Queue.queuePush(54);
                            dist36 = dist25 + 1;
                        }}
                    break;
                case 38:
                    if(!(arr15 == 0 || arr15 == 2)) {
                        if (arr15 == 1 && seen15 != roundNum) {
                            seen15 = roundNum;
                            Queue.queuePush(21);
                            dist15 = dist26 + 1;
                        }}
                    if(!(arr16 == 0 || arr16 == 2)) {
                        if (arr16 == 1 && seen16 != roundNum) {
                            seen16 = roundNum;
                            Queue.queuePush(22);
                            dist16 = dist26 + 1;
                        }}
                    if(!(arr25 == 0 || arr25 == 2)) {
                        if (arr25 == 1 && seen25 != roundNum) {
                            seen25 = roundNum;
                            Queue.queuePush(37);
                            dist25 = dist26 + 1;
                        }}
                    if(!(arr26 == 0 || arr26 == 2)) {
                        if (arr26 == 1 && seen26 != roundNum) {
                            seen26 = roundNum;
                            Queue.queuePush(38);
                            dist26 = dist26 + 1;
                        }}
                    if(!(arr27 == 0 || arr27 == 2)) {
                        if (arr27 == 1 && seen27 != roundNum) {
                            seen27 = roundNum;
                            Queue.queuePush(39);
                            dist27 = dist26 + 1;
                        }}
                    if(!(arr35 == 0 || arr35 == 2)) {
                        if (arr35 == 1 && seen35 != roundNum) {
                            seen35 = roundNum;
                            Queue.queuePush(53);
                            dist35 = dist26 + 1;
                        }}
                    if(!(arr36 == 0 || arr36 == 2)) {
                        if (arr36 == 1 && seen36 != roundNum) {
                            seen36 = roundNum;
                            Queue.queuePush(54);
                            dist36 = dist26 + 1;
                        }}
                    if(!(arr37 == 0 || arr37 == 2)) {
                        if (arr37 == 1 && seen37 != roundNum) {
                            seen37 = roundNum;
                            Queue.queuePush(55);
                            dist37 = dist26 + 1;
                        }}
                    break;
                case 39:
                    if(!(arr16 == 0 || arr16 == 2)) {
                        if (arr16 == 1 && seen16 != roundNum) {
                            seen16 = roundNum;
                            Queue.queuePush(22);
                            dist16 = dist27 + 1;
                        }}
                    if(!(arr26 == 0 || arr26 == 2)) {
                        if (arr26 == 1 && seen26 != roundNum) {
                            seen26 = roundNum;
                            Queue.queuePush(38);
                            dist26 = dist27 + 1;
                        }}
                    if(!(arr27 == 0 || arr27 == 2)) {
                        if (arr27 == 1 && seen27 != roundNum) {
                            seen27 = roundNum;
                            Queue.queuePush(39);
                            dist27 = dist27 + 1;
                        }}
                    if(!(arr36 == 0 || arr36 == 2)) {
                        if (arr36 == 1 && seen36 != roundNum) {
                            seen36 = roundNum;
                            Queue.queuePush(54);
                            dist36 = dist27 + 1;
                        }}
                    if(!(arr37 == 0 || arr37 == 2)) {
                        if (arr37 == 1 && seen37 != roundNum) {
                            seen37 = roundNum;
                            Queue.queuePush(55);
                            dist37 = dist27 + 1;
                        }}
                    break;
                case 49:
                    if(!(arr21 == 0 || arr21 == 2)) {
                        if (arr21 == 1 && seen21 != roundNum) {
                            seen21 = roundNum;
                            Queue.queuePush(33);
                            dist21 = dist31 + 1;
                        }}
                    if(!(arr22 == 0 || arr22 == 2)) {
                        if (arr22 == 1 && seen22 != roundNum) {
                            seen22 = roundNum;
                            Queue.queuePush(34);
                            dist22 = dist31 + 1;
                        }}
                    if(!(arr31 == 0 || arr31 == 2)) {
                        if (arr31 == 1 && seen31 != roundNum) {
                            seen31 = roundNum;
                            Queue.queuePush(49);
                            dist31 = dist31 + 1;
                        }}
                    if(!(arr32 == 0 || arr32 == 2)) {
                        if (arr32 == 1 && seen32 != roundNum) {
                            seen32 = roundNum;
                            Queue.queuePush(50);
                            dist32 = dist31 + 1;
                        }}
                    if(!(arr41 == 0 || arr41 == 2)) {
                        if (arr41 == 1 && seen41 != roundNum) {
                            seen41 = roundNum;
                            Queue.queuePush(65);
                            dist41 = dist31 + 1;
                        }}
                    if(!(arr42 == 0 || arr42 == 2)) {
                        if (arr42 == 1 && seen42 != roundNum) {
                            seen42 = roundNum;
                            Queue.queuePush(66);
                            dist42 = dist31 + 1;
                        }}
                    break;
                case 50:
                    if(!(arr21 == 0 || arr21 == 2)) {
                        if (arr21 == 1 && seen21 != roundNum) {
                            seen21 = roundNum;
                            Queue.queuePush(33);
                            dist21 = dist32 + 1;
                        }}
                    if(!(arr22 == 0 || arr22 == 2)) {
                        if (arr22 == 1 && seen22 != roundNum) {
                            seen22 = roundNum;
                            Queue.queuePush(34);
                            dist22 = dist32 + 1;
                        }}
                    if(!(arr23 == 0 || arr23 == 2)) {
                        if (arr23 == 1 && seen23 != roundNum) {
                            seen23 = roundNum;
                            Queue.queuePush(35);
                            dist23 = dist32 + 1;
                        }}
                    if(!(arr31 == 0 || arr31 == 2)) {
                        if (arr31 == 1 && seen31 != roundNum) {
                            seen31 = roundNum;
                            Queue.queuePush(49);
                            dist31 = dist32 + 1;
                        }}
                    if(!(arr32 == 0 || arr32 == 2)) {
                        if (arr32 == 1 && seen32 != roundNum) {
                            seen32 = roundNum;
                            Queue.queuePush(50);
                            dist32 = dist32 + 1;
                        }}
                    if(!(arr33 == 0 || arr33 == 2)) {
                        if (arr33 == 1 && seen33 != roundNum) {
                            seen33 = roundNum;
                            Queue.queuePush(51);
                            dist33 = dist32 + 1;
                        }}
                    if(!(arr41 == 0 || arr41 == 2)) {
                        if (arr41 == 1 && seen41 != roundNum) {
                            seen41 = roundNum;
                            Queue.queuePush(65);
                            dist41 = dist32 + 1;
                        }}
                    if(!(arr42 == 0 || arr42 == 2)) {
                        if (arr42 == 1 && seen42 != roundNum) {
                            seen42 = roundNum;
                            Queue.queuePush(66);
                            dist42 = dist32 + 1;
                        }}
                    if(!(arr43 == 0 || arr43 == 2)) {
                        if (arr43 == 1 && seen43 != roundNum) {
                            seen43 = roundNum;
                            Queue.queuePush(67);
                            dist43 = dist32 + 1;
                        }}
                    break;
                case 51:
                    if(!(arr22 == 0 || arr22 == 2)) {
                        if (arr22 == 1 && seen22 != roundNum) {
                            seen22 = roundNum;
                            Queue.queuePush(34);
                            dist22 = dist33 + 1;
                        }}
                    if(!(arr23 == 0 || arr23 == 2)) {
                        if (arr23 == 1 && seen23 != roundNum) {
                            seen23 = roundNum;
                            Queue.queuePush(35);
                            dist23 = dist33 + 1;
                        }}
                    if(!(arr24 == 0 || arr24 == 2)) {
                        if (arr24 == 1 && seen24 != roundNum) {
                            seen24 = roundNum;
                            Queue.queuePush(36);
                            dist24 = dist33 + 1;
                        }}
                    if(!(arr32 == 0 || arr32 == 2)) {
                        if (arr32 == 1 && seen32 != roundNum) {
                            seen32 = roundNum;
                            Queue.queuePush(50);
                            dist32 = dist33 + 1;
                        }}
                    if(!(arr33 == 0 || arr33 == 2)) {
                        if (arr33 == 1 && seen33 != roundNum) {
                            seen33 = roundNum;
                            Queue.queuePush(51);
                            dist33 = dist33 + 1;
                        }}
                    if(!(arr34 == 0 || arr34 == 2)) {
                        if (arr34 == 1 && seen34 != roundNum) {
                            seen34 = roundNum;
                            Queue.queuePush(52);
                            dist34 = dist33 + 1;
                        }}
                    if(!(arr42 == 0 || arr42 == 2)) {
                        if (arr42 == 1 && seen42 != roundNum) {
                            seen42 = roundNum;
                            Queue.queuePush(66);
                            dist42 = dist33 + 1;
                        }}
                    if(!(arr43 == 0 || arr43 == 2)) {
                        if (arr43 == 1 && seen43 != roundNum) {
                            seen43 = roundNum;
                            Queue.queuePush(67);
                            dist43 = dist33 + 1;
                        }}
                    if(!(arr44 == 0 || arr44 == 2)) {
                        if (arr44 == 1 && seen44 != roundNum) {
                            seen44 = roundNum;
                            Queue.queuePush(68);
                            dist44 = dist33 + 1;
                        }}
                    break;
                case 52:
                    if(!(arr23 == 0 || arr23 == 2)) {
                        if (arr23 == 1 && seen23 != roundNum) {
                            seen23 = roundNum;
                            Queue.queuePush(35);
                            dist23 = dist34 + 1;
                        }}
                    if(!(arr24 == 0 || arr24 == 2)) {
                        if (arr24 == 1 && seen24 != roundNum) {
                            seen24 = roundNum;
                            Queue.queuePush(36);
                            dist24 = dist34 + 1;
                        }}
                    if(!(arr25 == 0 || arr25 == 2)) {
                        if (arr25 == 1 && seen25 != roundNum) {
                            seen25 = roundNum;
                            Queue.queuePush(37);
                            dist25 = dist34 + 1;
                        }}
                    if(!(arr33 == 0 || arr33 == 2)) {
                        if (arr33 == 1 && seen33 != roundNum) {
                            seen33 = roundNum;
                            Queue.queuePush(51);
                            dist33 = dist34 + 1;
                        }}
                    if(!(arr34 == 0 || arr34 == 2)) {
                        if (arr34 == 1 && seen34 != roundNum) {
                            seen34 = roundNum;
                            Queue.queuePush(52);
                            dist34 = dist34 + 1;
                        }}
                    if(!(arr35 == 0 || arr35 == 2)) {
                        if (arr35 == 1 && seen35 != roundNum) {
                            seen35 = roundNum;
                            Queue.queuePush(53);
                            dist35 = dist34 + 1;
                        }}
                    if(!(arr43 == 0 || arr43 == 2)) {
                        if (arr43 == 1 && seen43 != roundNum) {
                            seen43 = roundNum;
                            Queue.queuePush(67);
                            dist43 = dist34 + 1;
                        }}
                    if(!(arr44 == 0 || arr44 == 2)) {
                        if (arr44 == 1 && seen44 != roundNum) {
                            seen44 = roundNum;
                            Queue.queuePush(68);
                            dist44 = dist34 + 1;
                        }}
                    if(!(arr45 == 0 || arr45 == 2)) {
                        if (arr45 == 1 && seen45 != roundNum) {
                            seen45 = roundNum;
                            Queue.queuePush(69);
                            dist45 = dist34 + 1;
                        }}
                    break;
                case 53:
                    if(!(arr24 == 0 || arr24 == 2)) {
                        if (arr24 == 1 && seen24 != roundNum) {
                            seen24 = roundNum;
                            Queue.queuePush(36);
                            dist24 = dist35 + 1;
                        }}
                    if(!(arr25 == 0 || arr25 == 2)) {
                        if (arr25 == 1 && seen25 != roundNum) {
                            seen25 = roundNum;
                            Queue.queuePush(37);
                            dist25 = dist35 + 1;
                        }}
                    if(!(arr26 == 0 || arr26 == 2)) {
                        if (arr26 == 1 && seen26 != roundNum) {
                            seen26 = roundNum;
                            Queue.queuePush(38);
                            dist26 = dist35 + 1;
                        }}
                    if(!(arr34 == 0 || arr34 == 2)) {
                        if (arr34 == 1 && seen34 != roundNum) {
                            seen34 = roundNum;
                            Queue.queuePush(52);
                            dist34 = dist35 + 1;
                        }}
                    if(!(arr35 == 0 || arr35 == 2)) {
                        if (arr35 == 1 && seen35 != roundNum) {
                            seen35 = roundNum;
                            Queue.queuePush(53);
                            dist35 = dist35 + 1;
                        }}
                    if(!(arr36 == 0 || arr36 == 2)) {
                        if (arr36 == 1 && seen36 != roundNum) {
                            seen36 = roundNum;
                            Queue.queuePush(54);
                            dist36 = dist35 + 1;
                        }}
                    if(!(arr44 == 0 || arr44 == 2)) {
                        if (arr44 == 1 && seen44 != roundNum) {
                            seen44 = roundNum;
                            Queue.queuePush(68);
                            dist44 = dist35 + 1;
                        }}
                    if(!(arr45 == 0 || arr45 == 2)) {
                        if (arr45 == 1 && seen45 != roundNum) {
                            seen45 = roundNum;
                            Queue.queuePush(69);
                            dist45 = dist35 + 1;
                        }}
                    if(!(arr46 == 0 || arr46 == 2)) {
                        if (arr46 == 1 && seen46 != roundNum) {
                            seen46 = roundNum;
                            Queue.queuePush(70);
                            dist46 = dist35 + 1;
                        }}
                    break;
                case 54:
                    if(!(arr25 == 0 || arr25 == 2)) {
                        if (arr25 == 1 && seen25 != roundNum) {
                            seen25 = roundNum;
                            Queue.queuePush(37);
                            dist25 = dist36 + 1;
                        }}
                    if(!(arr26 == 0 || arr26 == 2)) {
                        if (arr26 == 1 && seen26 != roundNum) {
                            seen26 = roundNum;
                            Queue.queuePush(38);
                            dist26 = dist36 + 1;
                        }}
                    if(!(arr27 == 0 || arr27 == 2)) {
                        if (arr27 == 1 && seen27 != roundNum) {
                            seen27 = roundNum;
                            Queue.queuePush(39);
                            dist27 = dist36 + 1;
                        }}
                    if(!(arr35 == 0 || arr35 == 2)) {
                        if (arr35 == 1 && seen35 != roundNum) {
                            seen35 = roundNum;
                            Queue.queuePush(53);
                            dist35 = dist36 + 1;
                        }}
                    if(!(arr36 == 0 || arr36 == 2)) {
                        if (arr36 == 1 && seen36 != roundNum) {
                            seen36 = roundNum;
                            Queue.queuePush(54);
                            dist36 = dist36 + 1;
                        }}
                    if(!(arr37 == 0 || arr37 == 2)) {
                        if (arr37 == 1 && seen37 != roundNum) {
                            seen37 = roundNum;
                            Queue.queuePush(55);
                            dist37 = dist36 + 1;
                        }}
                    if(!(arr45 == 0 || arr45 == 2)) {
                        if (arr45 == 1 && seen45 != roundNum) {
                            seen45 = roundNum;
                            Queue.queuePush(69);
                            dist45 = dist36 + 1;
                        }}
                    if(!(arr46 == 0 || arr46 == 2)) {
                        if (arr46 == 1 && seen46 != roundNum) {
                            seen46 = roundNum;
                            Queue.queuePush(70);
                            dist46 = dist36 + 1;
                        }}
                    if(!(arr47 == 0 || arr47 == 2)) {
                        if (arr47 == 1 && seen47 != roundNum) {
                            seen47 = roundNum;
                            Queue.queuePush(71);
                            dist47 = dist36 + 1;
                        }}
                    break;
                case 55:
                    if(!(arr26 == 0 || arr26 == 2)) {
                        if (arr26 == 1 && seen26 != roundNum) {
                            seen26 = roundNum;
                            Queue.queuePush(38);
                            dist26 = dist37 + 1;
                        }}
                    if(!(arr27 == 0 || arr27 == 2)) {
                        if (arr27 == 1 && seen27 != roundNum) {
                            seen27 = roundNum;
                            Queue.queuePush(39);
                            dist27 = dist37 + 1;
                        }}
                    if(!(arr36 == 0 || arr36 == 2)) {
                        if (arr36 == 1 && seen36 != roundNum) {
                            seen36 = roundNum;
                            Queue.queuePush(54);
                            dist36 = dist37 + 1;
                        }}
                    if(!(arr37 == 0 || arr37 == 2)) {
                        if (arr37 == 1 && seen37 != roundNum) {
                            seen37 = roundNum;
                            Queue.queuePush(55);
                            dist37 = dist37 + 1;
                        }}
                    if(!(arr46 == 0 || arr46 == 2)) {
                        if (arr46 == 1 && seen46 != roundNum) {
                            seen46 = roundNum;
                            Queue.queuePush(70);
                            dist46 = dist37 + 1;
                        }}
                    if(!(arr47 == 0 || arr47 == 2)) {
                        if (arr47 == 1 && seen47 != roundNum) {
                            seen47 = roundNum;
                            Queue.queuePush(71);
                            dist47 = dist37 + 1;
                        }}
                    break;
                case 65:
                    if(!(arr31 == 0 || arr31 == 2)) {
                        if (arr31 == 1 && seen31 != roundNum) {
                            seen31 = roundNum;
                            Queue.queuePush(49);
                            dist31 = dist41 + 1;
                        }}
                    if(!(arr32 == 0 || arr32 == 2)) {
                        if (arr32 == 1 && seen32 != roundNum) {
                            seen32 = roundNum;
                            Queue.queuePush(50);
                            dist32 = dist41 + 1;
                        }}
                    if(!(arr41 == 0 || arr41 == 2)) {
                        if (arr41 == 1 && seen41 != roundNum) {
                            seen41 = roundNum;
                            Queue.queuePush(65);
                            dist41 = dist41 + 1;
                        }}
                    if(!(arr42 == 0 || arr42 == 2)) {
                        if (arr42 == 1 && seen42 != roundNum) {
                            seen42 = roundNum;
                            Queue.queuePush(66);
                            dist42 = dist41 + 1;
                        }}
                    if(!(arr51 == 0 || arr51 == 2)) {
                        if (arr51 == 1 && seen51 != roundNum) {
                            seen51 = roundNum;
                            Queue.queuePush(81);
                            dist51 = dist41 + 1;
                        }}
                    if(!(arr52 == 0 || arr52 == 2)) {
                        if (arr52 == 1 && seen52 != roundNum) {
                            seen52 = roundNum;
                            Queue.queuePush(82);
                            dist52 = dist41 + 1;
                        }}
                    break;
                case 66:
                    if(!(arr31 == 0 || arr31 == 2)) {
                        if (arr31 == 1 && seen31 != roundNum) {
                            seen31 = roundNum;
                            Queue.queuePush(49);
                            dist31 = dist42 + 1;
                        }}
                    if(!(arr32 == 0 || arr32 == 2)) {
                        if (arr32 == 1 && seen32 != roundNum) {
                            seen32 = roundNum;
                            Queue.queuePush(50);
                            dist32 = dist42 + 1;
                        }}
                    if(!(arr33 == 0 || arr33 == 2)) {
                        if (arr33 == 1 && seen33 != roundNum) {
                            seen33 = roundNum;
                            Queue.queuePush(51);
                            dist33 = dist42 + 1;
                        }}
                    if(!(arr41 == 0 || arr41 == 2)) {
                        if (arr41 == 1 && seen41 != roundNum) {
                            seen41 = roundNum;
                            Queue.queuePush(65);
                            dist41 = dist42 + 1;
                        }}
                    if(!(arr42 == 0 || arr42 == 2)) {
                        if (arr42 == 1 && seen42 != roundNum) {
                            seen42 = roundNum;
                            Queue.queuePush(66);
                            dist42 = dist42 + 1;
                        }}
                    if(!(arr43 == 0 || arr43 == 2)) {
                        if (arr43 == 1 && seen43 != roundNum) {
                            seen43 = roundNum;
                            Queue.queuePush(67);
                            dist43 = dist42 + 1;
                        }}
                    if(!(arr51 == 0 || arr51 == 2)) {
                        if (arr51 == 1 && seen51 != roundNum) {
                            seen51 = roundNum;
                            Queue.queuePush(81);
                            dist51 = dist42 + 1;
                        }}
                    if(!(arr52 == 0 || arr52 == 2)) {
                        if (arr52 == 1 && seen52 != roundNum) {
                            seen52 = roundNum;
                            Queue.queuePush(82);
                            dist52 = dist42 + 1;
                        }}
                    if(!(arr53 == 0 || arr53 == 2)) {
                        if (arr53 == 1 && seen53 != roundNum) {
                            seen53 = roundNum;
                            Queue.queuePush(83);
                            dist53 = dist42 + 1;
                        }}
                    break;
                case 67:
                    if(!(arr32 == 0 || arr32 == 2)) {
                        if (arr32 == 1 && seen32 != roundNum) {
                            seen32 = roundNum;
                            Queue.queuePush(50);
                            dist32 = dist43 + 1;
                        }}
                    if(!(arr33 == 0 || arr33 == 2)) {
                        if (arr33 == 1 && seen33 != roundNum) {
                            seen33 = roundNum;
                            Queue.queuePush(51);
                            dist33 = dist43 + 1;
                        }}
                    if(!(arr34 == 0 || arr34 == 2)) {
                        if (arr34 == 1 && seen34 != roundNum) {
                            seen34 = roundNum;
                            Queue.queuePush(52);
                            dist34 = dist43 + 1;
                        }}
                    if(!(arr42 == 0 || arr42 == 2)) {
                        if (arr42 == 1 && seen42 != roundNum) {
                            seen42 = roundNum;
                            Queue.queuePush(66);
                            dist42 = dist43 + 1;
                        }}
                    if(!(arr43 == 0 || arr43 == 2)) {
                        if (arr43 == 1 && seen43 != roundNum) {
                            seen43 = roundNum;
                            Queue.queuePush(67);
                            dist43 = dist43 + 1;
                        }}
                    if(!(arr44 == 0 || arr44 == 2)) {
                        if (arr44 == 1 && seen44 != roundNum) {
                            seen44 = roundNum;
                            Queue.queuePush(68);
                            dist44 = dist43 + 1;
                        }}
                    if(!(arr52 == 0 || arr52 == 2)) {
                        if (arr52 == 1 && seen52 != roundNum) {
                            seen52 = roundNum;
                            Queue.queuePush(82);
                            dist52 = dist43 + 1;
                        }}
                    if(!(arr53 == 0 || arr53 == 2)) {
                        if (arr53 == 1 && seen53 != roundNum) {
                            seen53 = roundNum;
                            Queue.queuePush(83);
                            dist53 = dist43 + 1;
                        }}
                    if(!(arr54 == 0 || arr54 == 2)) {
                        if (arr54 == 1 && seen54 != roundNum) {
                            seen54 = roundNum;
                            Queue.queuePush(84);
                            dist54 = dist43 + 1;
                        }}
                    break;
                case 68:
                    if(!(arr33 == 0 || arr33 == 2)) {
                        if (arr33 == 1 && seen33 != roundNum) {
                            seen33 = roundNum;
                            Queue.queuePush(51);
                            dist33 = dist44 + 1;
                        }}
                    if(!(arr34 == 0 || arr34 == 2)) {
                        if (arr34 == 1 && seen34 != roundNum) {
                            seen34 = roundNum;
                            Queue.queuePush(52);
                            dist34 = dist44 + 1;
                        }}
                    if(!(arr35 == 0 || arr35 == 2)) {
                        if (arr35 == 1 && seen35 != roundNum) {
                            seen35 = roundNum;
                            Queue.queuePush(53);
                            dist35 = dist44 + 1;
                        }}
                    if(!(arr43 == 0 || arr43 == 2)) {
                        if (arr43 == 1 && seen43 != roundNum) {
                            seen43 = roundNum;
                            Queue.queuePush(67);
                            dist43 = dist44 + 1;
                        }}
                    if(!(arr44 == 0 || arr44 == 2)) {
                        if (arr44 == 1 && seen44 != roundNum) {
                            seen44 = roundNum;
                            Queue.queuePush(68);
                            dist44 = dist44 + 1;
                        }}
                    if(!(arr45 == 0 || arr45 == 2)) {
                        if (arr45 == 1 && seen45 != roundNum) {
                            seen45 = roundNum;
                            Queue.queuePush(69);
                            dist45 = dist44 + 1;
                        }}
                    if(!(arr53 == 0 || arr53 == 2)) {
                        if (arr53 == 1 && seen53 != roundNum) {
                            seen53 = roundNum;
                            Queue.queuePush(83);
                            dist53 = dist44 + 1;
                        }}
                    if(!(arr54 == 0 || arr54 == 2)) {
                        if (arr54 == 1 && seen54 != roundNum) {
                            seen54 = roundNum;
                            Queue.queuePush(84);
                            dist54 = dist44 + 1;
                        }}
                    if(!(arr55 == 0 || arr55 == 2)) {
                        if (arr55 == 1 && seen55 != roundNum) {
                            seen55 = roundNum;
                            Queue.queuePush(85);
                            dist55 = dist44 + 1;
                        }}
                    break;
                case 69:
                    if(!(arr34 == 0 || arr34 == 2)) {
                        if (arr34 == 1 && seen34 != roundNum) {
                            seen34 = roundNum;
                            Queue.queuePush(52);
                            dist34 = dist45 + 1;
                        }}
                    if(!(arr35 == 0 || arr35 == 2)) {
                        if (arr35 == 1 && seen35 != roundNum) {
                            seen35 = roundNum;
                            Queue.queuePush(53);
                            dist35 = dist45 + 1;
                        }}
                    if(!(arr36 == 0 || arr36 == 2)) {
                        if (arr36 == 1 && seen36 != roundNum) {
                            seen36 = roundNum;
                            Queue.queuePush(54);
                            dist36 = dist45 + 1;
                        }}
                    if(!(arr44 == 0 || arr44 == 2)) {
                        if (arr44 == 1 && seen44 != roundNum) {
                            seen44 = roundNum;
                            Queue.queuePush(68);
                            dist44 = dist45 + 1;
                        }}
                    if(!(arr45 == 0 || arr45 == 2)) {
                        if (arr45 == 1 && seen45 != roundNum) {
                            seen45 = roundNum;
                            Queue.queuePush(69);
                            dist45 = dist45 + 1;
                        }}
                    if(!(arr46 == 0 || arr46 == 2)) {
                        if (arr46 == 1 && seen46 != roundNum) {
                            seen46 = roundNum;
                            Queue.queuePush(70);
                            dist46 = dist45 + 1;
                        }}
                    if(!(arr54 == 0 || arr54 == 2)) {
                        if (arr54 == 1 && seen54 != roundNum) {
                            seen54 = roundNum;
                            Queue.queuePush(84);
                            dist54 = dist45 + 1;
                        }}
                    if(!(arr55 == 0 || arr55 == 2)) {
                        if (arr55 == 1 && seen55 != roundNum) {
                            seen55 = roundNum;
                            Queue.queuePush(85);
                            dist55 = dist45 + 1;
                        }}
                    if(!(arr56 == 0 || arr56 == 2)) {
                        if (arr56 == 1 && seen56 != roundNum) {
                            seen56 = roundNum;
                            Queue.queuePush(86);
                            dist56 = dist45 + 1;
                        }}
                    break;
                case 70:
                    if(!(arr35 == 0 || arr35 == 2)) {
                        if (arr35 == 1 && seen35 != roundNum) {
                            seen35 = roundNum;
                            Queue.queuePush(53);
                            dist35 = dist46 + 1;
                        }}
                    if(!(arr36 == 0 || arr36 == 2)) {
                        if (arr36 == 1 && seen36 != roundNum) {
                            seen36 = roundNum;
                            Queue.queuePush(54);
                            dist36 = dist46 + 1;
                        }}
                    if(!(arr37 == 0 || arr37 == 2)) {
                        if (arr37 == 1 && seen37 != roundNum) {
                            seen37 = roundNum;
                            Queue.queuePush(55);
                            dist37 = dist46 + 1;
                        }}
                    if(!(arr45 == 0 || arr45 == 2)) {
                        if (arr45 == 1 && seen45 != roundNum) {
                            seen45 = roundNum;
                            Queue.queuePush(69);
                            dist45 = dist46 + 1;
                        }}
                    if(!(arr46 == 0 || arr46 == 2)) {
                        if (arr46 == 1 && seen46 != roundNum) {
                            seen46 = roundNum;
                            Queue.queuePush(70);
                            dist46 = dist46 + 1;
                        }}
                    if(!(arr47 == 0 || arr47 == 2)) {
                        if (arr47 == 1 && seen47 != roundNum) {
                            seen47 = roundNum;
                            Queue.queuePush(71);
                            dist47 = dist46 + 1;
                        }}
                    if(!(arr55 == 0 || arr55 == 2)) {
                        if (arr55 == 1 && seen55 != roundNum) {
                            seen55 = roundNum;
                            Queue.queuePush(85);
                            dist55 = dist46 + 1;
                        }}
                    if(!(arr56 == 0 || arr56 == 2)) {
                        if (arr56 == 1 && seen56 != roundNum) {
                            seen56 = roundNum;
                            Queue.queuePush(86);
                            dist56 = dist46 + 1;
                        }}
                    if(!(arr57 == 0 || arr57 == 2)) {
                        if (arr57 == 1 && seen57 != roundNum) {
                            seen57 = roundNum;
                            Queue.queuePush(87);
                            dist57 = dist46 + 1;
                        }}
                    break;
                case 71:
                    if(!(arr36 == 0 || arr36 == 2)) {
                        if (arr36 == 1 && seen36 != roundNum) {
                            seen36 = roundNum;
                            Queue.queuePush(54);
                            dist36 = dist47 + 1;
                        }}
                    if(!(arr37 == 0 || arr37 == 2)) {
                        if (arr37 == 1 && seen37 != roundNum) {
                            seen37 = roundNum;
                            Queue.queuePush(55);
                            dist37 = dist47 + 1;
                        }}
                    if(!(arr46 == 0 || arr46 == 2)) {
                        if (arr46 == 1 && seen46 != roundNum) {
                            seen46 = roundNum;
                            Queue.queuePush(70);
                            dist46 = dist47 + 1;
                        }}
                    if(!(arr47 == 0 || arr47 == 2)) {
                        if (arr47 == 1 && seen47 != roundNum) {
                            seen47 = roundNum;
                            Queue.queuePush(71);
                            dist47 = dist47 + 1;
                        }}
                    if(!(arr56 == 0 || arr56 == 2)) {
                        if (arr56 == 1 && seen56 != roundNum) {
                            seen56 = roundNum;
                            Queue.queuePush(86);
                            dist56 = dist47 + 1;
                        }}
                    if(!(arr57 == 0 || arr57 == 2)) {
                        if (arr57 == 1 && seen57 != roundNum) {
                            seen57 = roundNum;
                            Queue.queuePush(87);
                            dist57 = dist47 + 1;
                        }}
                    break;
                case 81:
                    if(!(arr41 == 0 || arr41 == 2)) {
                        if (arr41 == 1 && seen41 != roundNum) {
                            seen41 = roundNum;
                            Queue.queuePush(65);
                            dist41 = dist51 + 1;
                        }}
                    if(!(arr42 == 0 || arr42 == 2)) {
                        if (arr42 == 1 && seen42 != roundNum) {
                            seen42 = roundNum;
                            Queue.queuePush(66);
                            dist42 = dist51 + 1;
                        }}
                    if(!(arr51 == 0 || arr51 == 2)) {
                        if (arr51 == 1 && seen51 != roundNum) {
                            seen51 = roundNum;
                            Queue.queuePush(81);
                            dist51 = dist51 + 1;
                        }}
                    if(!(arr52 == 0 || arr52 == 2)) {
                        if (arr52 == 1 && seen52 != roundNum) {
                            seen52 = roundNum;
                            Queue.queuePush(82);
                            dist52 = dist51 + 1;
                        }}
                    if(!(arr61 == 0 || arr61 == 2)) {
                        if (arr61 == 1 && seen61 != roundNum) {
                            seen61 = roundNum;
                            Queue.queuePush(97);
                            dist61 = dist51 + 1;
                        }}
                    if(!(arr62 == 0 || arr62 == 2)) {
                        if (arr62 == 1 && seen62 != roundNum) {
                            seen62 = roundNum;
                            Queue.queuePush(98);
                            dist62 = dist51 + 1;
                        }}
                    break;
                case 82:
                    if(!(arr41 == 0 || arr41 == 2)) {
                        if (arr41 == 1 && seen41 != roundNum) {
                            seen41 = roundNum;
                            Queue.queuePush(65);
                            dist41 = dist52 + 1;
                        }}
                    if(!(arr42 == 0 || arr42 == 2)) {
                        if (arr42 == 1 && seen42 != roundNum) {
                            seen42 = roundNum;
                            Queue.queuePush(66);
                            dist42 = dist52 + 1;
                        }}
                    if(!(arr43 == 0 || arr43 == 2)) {
                        if (arr43 == 1 && seen43 != roundNum) {
                            seen43 = roundNum;
                            Queue.queuePush(67);
                            dist43 = dist52 + 1;
                        }}
                    if(!(arr51 == 0 || arr51 == 2)) {
                        if (arr51 == 1 && seen51 != roundNum) {
                            seen51 = roundNum;
                            Queue.queuePush(81);
                            dist51 = dist52 + 1;
                        }}
                    if(!(arr52 == 0 || arr52 == 2)) {
                        if (arr52 == 1 && seen52 != roundNum) {
                            seen52 = roundNum;
                            Queue.queuePush(82);
                            dist52 = dist52 + 1;
                        }}
                    if(!(arr53 == 0 || arr53 == 2)) {
                        if (arr53 == 1 && seen53 != roundNum) {
                            seen53 = roundNum;
                            Queue.queuePush(83);
                            dist53 = dist52 + 1;
                        }}
                    if(!(arr61 == 0 || arr61 == 2)) {
                        if (arr61 == 1 && seen61 != roundNum) {
                            seen61 = roundNum;
                            Queue.queuePush(97);
                            dist61 = dist52 + 1;
                        }}
                    if(!(arr62 == 0 || arr62 == 2)) {
                        if (arr62 == 1 && seen62 != roundNum) {
                            seen62 = roundNum;
                            Queue.queuePush(98);
                            dist62 = dist52 + 1;
                        }}
                    if(!(arr63 == 0 || arr63 == 2)) {
                        if (arr63 == 1 && seen63 != roundNum) {
                            seen63 = roundNum;
                            Queue.queuePush(99);
                            dist63 = dist52 + 1;
                        }}
                    break;
                case 83:
                    if(!(arr42 == 0 || arr42 == 2)) {
                        if (arr42 == 1 && seen42 != roundNum) {
                            seen42 = roundNum;
                            Queue.queuePush(66);
                            dist42 = dist53 + 1;
                        }}
                    if(!(arr43 == 0 || arr43 == 2)) {
                        if (arr43 == 1 && seen43 != roundNum) {
                            seen43 = roundNum;
                            Queue.queuePush(67);
                            dist43 = dist53 + 1;
                        }}
                    if(!(arr44 == 0 || arr44 == 2)) {
                        if (arr44 == 1 && seen44 != roundNum) {
                            seen44 = roundNum;
                            Queue.queuePush(68);
                            dist44 = dist53 + 1;
                        }}
                    if(!(arr52 == 0 || arr52 == 2)) {
                        if (arr52 == 1 && seen52 != roundNum) {
                            seen52 = roundNum;
                            Queue.queuePush(82);
                            dist52 = dist53 + 1;
                        }}
                    if(!(arr53 == 0 || arr53 == 2)) {
                        if (arr53 == 1 && seen53 != roundNum) {
                            seen53 = roundNum;
                            Queue.queuePush(83);
                            dist53 = dist53 + 1;
                        }}
                    if(!(arr54 == 0 || arr54 == 2)) {
                        if (arr54 == 1 && seen54 != roundNum) {
                            seen54 = roundNum;
                            Queue.queuePush(84);
                            dist54 = dist53 + 1;
                        }}
                    if(!(arr62 == 0 || arr62 == 2)) {
                        if (arr62 == 1 && seen62 != roundNum) {
                            seen62 = roundNum;
                            Queue.queuePush(98);
                            dist62 = dist53 + 1;
                        }}
                    if(!(arr63 == 0 || arr63 == 2)) {
                        if (arr63 == 1 && seen63 != roundNum) {
                            seen63 = roundNum;
                            Queue.queuePush(99);
                            dist63 = dist53 + 1;
                        }}
                    if(!(arr64 == 0 || arr64 == 2)) {
                        if (arr64 == 1 && seen64 != roundNum) {
                            seen64 = roundNum;
                            Queue.queuePush(100);
                            dist64 = dist53 + 1;
                        }}
                    break;
                case 84:
                    if(!(arr43 == 0 || arr43 == 2)) {
                        if (arr43 == 1 && seen43 != roundNum) {
                            seen43 = roundNum;
                            Queue.queuePush(67);
                            dist43 = dist54 + 1;
                        }}
                    if(!(arr44 == 0 || arr44 == 2)) {
                        if (arr44 == 1 && seen44 != roundNum) {
                            seen44 = roundNum;
                            Queue.queuePush(68);
                            dist44 = dist54 + 1;
                        }}
                    if(!(arr45 == 0 || arr45 == 2)) {
                        if (arr45 == 1 && seen45 != roundNum) {
                            seen45 = roundNum;
                            Queue.queuePush(69);
                            dist45 = dist54 + 1;
                        }}
                    if(!(arr53 == 0 || arr53 == 2)) {
                        if (arr53 == 1 && seen53 != roundNum) {
                            seen53 = roundNum;
                            Queue.queuePush(83);
                            dist53 = dist54 + 1;
                        }}
                    if(!(arr54 == 0 || arr54 == 2)) {
                        if (arr54 == 1 && seen54 != roundNum) {
                            seen54 = roundNum;
                            Queue.queuePush(84);
                            dist54 = dist54 + 1;
                        }}
                    if(!(arr55 == 0 || arr55 == 2)) {
                        if (arr55 == 1 && seen55 != roundNum) {
                            seen55 = roundNum;
                            Queue.queuePush(85);
                            dist55 = dist54 + 1;
                        }}
                    if(!(arr63 == 0 || arr63 == 2)) {
                        if (arr63 == 1 && seen63 != roundNum) {
                            seen63 = roundNum;
                            Queue.queuePush(99);
                            dist63 = dist54 + 1;
                        }}
                    if(!(arr64 == 0 || arr64 == 2)) {
                        if (arr64 == 1 && seen64 != roundNum) {
                            seen64 = roundNum;
                            Queue.queuePush(100);
                            dist64 = dist54 + 1;
                        }}
                    if(!(arr65 == 0 || arr65 == 2)) {
                        if (arr65 == 1 && seen65 != roundNum) {
                            seen65 = roundNum;
                            Queue.queuePush(101);
                            dist65 = dist54 + 1;
                        }}
                    break;
                case 85:
                    if(!(arr44 == 0 || arr44 == 2)) {
                        if (arr44 == 1 && seen44 != roundNum) {
                            seen44 = roundNum;
                            Queue.queuePush(68);
                            dist44 = dist55 + 1;
                        }}
                    if(!(arr45 == 0 || arr45 == 2)) {
                        if (arr45 == 1 && seen45 != roundNum) {
                            seen45 = roundNum;
                            Queue.queuePush(69);
                            dist45 = dist55 + 1;
                        }}
                    if(!(arr46 == 0 || arr46 == 2)) {
                        if (arr46 == 1 && seen46 != roundNum) {
                            seen46 = roundNum;
                            Queue.queuePush(70);
                            dist46 = dist55 + 1;
                        }}
                    if(!(arr54 == 0 || arr54 == 2)) {
                        if (arr54 == 1 && seen54 != roundNum) {
                            seen54 = roundNum;
                            Queue.queuePush(84);
                            dist54 = dist55 + 1;
                        }}
                    if(!(arr55 == 0 || arr55 == 2)) {
                        if (arr55 == 1 && seen55 != roundNum) {
                            seen55 = roundNum;
                            Queue.queuePush(85);
                            dist55 = dist55 + 1;
                        }}
                    if(!(arr56 == 0 || arr56 == 2)) {
                        if (arr56 == 1 && seen56 != roundNum) {
                            seen56 = roundNum;
                            Queue.queuePush(86);
                            dist56 = dist55 + 1;
                        }}
                    if(!(arr64 == 0 || arr64 == 2)) {
                        if (arr64 == 1 && seen64 != roundNum) {
                            seen64 = roundNum;
                            Queue.queuePush(100);
                            dist64 = dist55 + 1;
                        }}
                    if(!(arr65 == 0 || arr65 == 2)) {
                        if (arr65 == 1 && seen65 != roundNum) {
                            seen65 = roundNum;
                            Queue.queuePush(101);
                            dist65 = dist55 + 1;
                        }}
                    if(!(arr66 == 0 || arr66 == 2)) {
                        if (arr66 == 1 && seen66 != roundNum) {
                            seen66 = roundNum;
                            Queue.queuePush(102);
                            dist66 = dist55 + 1;
                        }}
                    break;
                case 86:
                    if(!(arr45 == 0 || arr45 == 2)) {
                        if (arr45 == 1 && seen45 != roundNum) {
                            seen45 = roundNum;
                            Queue.queuePush(69);
                            dist45 = dist56 + 1;
                        }}
                    if(!(arr46 == 0 || arr46 == 2)) {
                        if (arr46 == 1 && seen46 != roundNum) {
                            seen46 = roundNum;
                            Queue.queuePush(70);
                            dist46 = dist56 + 1;
                        }}
                    if(!(arr47 == 0 || arr47 == 2)) {
                        if (arr47 == 1 && seen47 != roundNum) {
                            seen47 = roundNum;
                            Queue.queuePush(71);
                            dist47 = dist56 + 1;
                        }}
                    if(!(arr55 == 0 || arr55 == 2)) {
                        if (arr55 == 1 && seen55 != roundNum) {
                            seen55 = roundNum;
                            Queue.queuePush(85);
                            dist55 = dist56 + 1;
                        }}
                    if(!(arr56 == 0 || arr56 == 2)) {
                        if (arr56 == 1 && seen56 != roundNum) {
                            seen56 = roundNum;
                            Queue.queuePush(86);
                            dist56 = dist56 + 1;
                        }}
                    if(!(arr57 == 0 || arr57 == 2)) {
                        if (arr57 == 1 && seen57 != roundNum) {
                            seen57 = roundNum;
                            Queue.queuePush(87);
                            dist57 = dist56 + 1;
                        }}
                    if(!(arr65 == 0 || arr65 == 2)) {
                        if (arr65 == 1 && seen65 != roundNum) {
                            seen65 = roundNum;
                            Queue.queuePush(101);
                            dist65 = dist56 + 1;
                        }}
                    if(!(arr66 == 0 || arr66 == 2)) {
                        if (arr66 == 1 && seen66 != roundNum) {
                            seen66 = roundNum;
                            Queue.queuePush(102);
                            dist66 = dist56 + 1;
                        }}
                    if(!(arr67 == 0 || arr67 == 2)) {
                        if (arr67 == 1 && seen67 != roundNum) {
                            seen67 = roundNum;
                            Queue.queuePush(103);
                            dist67 = dist56 + 1;
                        }}
                    break;
                case 87:
                    if(!(arr46 == 0 || arr46 == 2)) {
                        if (arr46 == 1 && seen46 != roundNum) {
                            seen46 = roundNum;
                            Queue.queuePush(70);
                            dist46 = dist57 + 1;
                        }}
                    if(!(arr47 == 0 || arr47 == 2)) {
                        if (arr47 == 1 && seen47 != roundNum) {
                            seen47 = roundNum;
                            Queue.queuePush(71);
                            dist47 = dist57 + 1;
                        }}
                    if(!(arr56 == 0 || arr56 == 2)) {
                        if (arr56 == 1 && seen56 != roundNum) {
                            seen56 = roundNum;
                            Queue.queuePush(86);
                            dist56 = dist57 + 1;
                        }}
                    if(!(arr57 == 0 || arr57 == 2)) {
                        if (arr57 == 1 && seen57 != roundNum) {
                            seen57 = roundNum;
                            Queue.queuePush(87);
                            dist57 = dist57 + 1;
                        }}
                    if(!(arr66 == 0 || arr66 == 2)) {
                        if (arr66 == 1 && seen66 != roundNum) {
                            seen66 = roundNum;
                            Queue.queuePush(102);
                            dist66 = dist57 + 1;
                        }}
                    if(!(arr67 == 0 || arr67 == 2)) {
                        if (arr67 == 1 && seen67 != roundNum) {
                            seen67 = roundNum;
                            Queue.queuePush(103);
                            dist67 = dist57 + 1;
                        }}
                    break;
                case 97:
                    if(!(arr51 == 0 || arr51 == 2)) {
                        if (arr51 == 1 && seen51 != roundNum) {
                            seen51 = roundNum;
                            Queue.queuePush(81);
                            dist51 = dist61 + 1;
                        }}
                    if(!(arr52 == 0 || arr52 == 2)) {
                        if (arr52 == 1 && seen52 != roundNum) {
                            seen52 = roundNum;
                            Queue.queuePush(82);
                            dist52 = dist61 + 1;
                        }}
                    if(!(arr61 == 0 || arr61 == 2)) {
                        if (arr61 == 1 && seen61 != roundNum) {
                            seen61 = roundNum;
                            Queue.queuePush(97);
                            dist61 = dist61 + 1;
                        }}
                    if(!(arr62 == 0 || arr62 == 2)) {
                        if (arr62 == 1 && seen62 != roundNum) {
                            seen62 = roundNum;
                            Queue.queuePush(98);
                            dist62 = dist61 + 1;
                        }}
                    if(!(arr72 == 0 || arr72 == 2)) {
                        if (arr72 == 1 && seen72 != roundNum) {
                            seen72 = roundNum;
                            Queue.queuePush(114);
                            dist72 = dist61 + 1;
                        }}
                    break;
                case 98:
                    if(!(arr51 == 0 || arr51 == 2)) {
                        if (arr51 == 1 && seen51 != roundNum) {
                            seen51 = roundNum;
                            Queue.queuePush(81);
                            dist51 = dist62 + 1;
                        }}
                    if(!(arr52 == 0 || arr52 == 2)) {
                        if (arr52 == 1 && seen52 != roundNum) {
                            seen52 = roundNum;
                            Queue.queuePush(82);
                            dist52 = dist62 + 1;
                        }}
                    if(!(arr53 == 0 || arr53 == 2)) {
                        if (arr53 == 1 && seen53 != roundNum) {
                            seen53 = roundNum;
                            Queue.queuePush(83);
                            dist53 = dist62 + 1;
                        }}
                    if(!(arr61 == 0 || arr61 == 2)) {
                        if (arr61 == 1 && seen61 != roundNum) {
                            seen61 = roundNum;
                            Queue.queuePush(97);
                            dist61 = dist62 + 1;
                        }}
                    if(!(arr62 == 0 || arr62 == 2)) {
                        if (arr62 == 1 && seen62 != roundNum) {
                            seen62 = roundNum;
                            Queue.queuePush(98);
                            dist62 = dist62 + 1;
                        }}
                    if(!(arr63 == 0 || arr63 == 2)) {
                        if (arr63 == 1 && seen63 != roundNum) {
                            seen63 = roundNum;
                            Queue.queuePush(99);
                            dist63 = dist62 + 1;
                        }}
                    if(!(arr72 == 0 || arr72 == 2)) {
                        if (arr72 == 1 && seen72 != roundNum) {
                            seen72 = roundNum;
                            Queue.queuePush(114);
                            dist72 = dist62 + 1;
                        }}
                    if(!(arr73 == 0 || arr73 == 2)) {
                        if (arr73 == 1 && seen73 != roundNum) {
                            seen73 = roundNum;
                            Queue.queuePush(115);
                            dist73 = dist62 + 1;
                        }}
                    break;
                case 99:
                    if(!(arr52 == 0 || arr52 == 2)) {
                        if (arr52 == 1 && seen52 != roundNum) {
                            seen52 = roundNum;
                            Queue.queuePush(82);
                            dist52 = dist63 + 1;
                        }}
                    if(!(arr53 == 0 || arr53 == 2)) {
                        if (arr53 == 1 && seen53 != roundNum) {
                            seen53 = roundNum;
                            Queue.queuePush(83);
                            dist53 = dist63 + 1;
                        }}
                    if(!(arr54 == 0 || arr54 == 2)) {
                        if (arr54 == 1 && seen54 != roundNum) {
                            seen54 = roundNum;
                            Queue.queuePush(84);
                            dist54 = dist63 + 1;
                        }}
                    if(!(arr62 == 0 || arr62 == 2)) {
                        if (arr62 == 1 && seen62 != roundNum) {
                            seen62 = roundNum;
                            Queue.queuePush(98);
                            dist62 = dist63 + 1;
                        }}
                    if(!(arr63 == 0 || arr63 == 2)) {
                        if (arr63 == 1 && seen63 != roundNum) {
                            seen63 = roundNum;
                            Queue.queuePush(99);
                            dist63 = dist63 + 1;
                        }}
                    if(!(arr64 == 0 || arr64 == 2)) {
                        if (arr64 == 1 && seen64 != roundNum) {
                            seen64 = roundNum;
                            Queue.queuePush(100);
                            dist64 = dist63 + 1;
                        }}
                    if(!(arr72 == 0 || arr72 == 2)) {
                        if (arr72 == 1 && seen72 != roundNum) {
                            seen72 = roundNum;
                            Queue.queuePush(114);
                            dist72 = dist63 + 1;
                        }}
                    if(!(arr73 == 0 || arr73 == 2)) {
                        if (arr73 == 1 && seen73 != roundNum) {
                            seen73 = roundNum;
                            Queue.queuePush(115);
                            dist73 = dist63 + 1;
                        }}
                    if(!(arr74 == 0 || arr74 == 2)) {
                        if (arr74 == 1 && seen74 != roundNum) {
                            seen74 = roundNum;
                            Queue.queuePush(116);
                            dist74 = dist63 + 1;
                        }}
                    break;
                case 100:
                    if(!(arr53 == 0 || arr53 == 2)) {
                        if (arr53 == 1 && seen53 != roundNum) {
                            seen53 = roundNum;
                            Queue.queuePush(83);
                            dist53 = dist64 + 1;
                        }}
                    if(!(arr54 == 0 || arr54 == 2)) {
                        if (arr54 == 1 && seen54 != roundNum) {
                            seen54 = roundNum;
                            Queue.queuePush(84);
                            dist54 = dist64 + 1;
                        }}
                    if(!(arr55 == 0 || arr55 == 2)) {
                        if (arr55 == 1 && seen55 != roundNum) {
                            seen55 = roundNum;
                            Queue.queuePush(85);
                            dist55 = dist64 + 1;
                        }}
                    if(!(arr63 == 0 || arr63 == 2)) {
                        if (arr63 == 1 && seen63 != roundNum) {
                            seen63 = roundNum;
                            Queue.queuePush(99);
                            dist63 = dist64 + 1;
                        }}
                    if(!(arr64 == 0 || arr64 == 2)) {
                        if (arr64 == 1 && seen64 != roundNum) {
                            seen64 = roundNum;
                            Queue.queuePush(100);
                            dist64 = dist64 + 1;
                        }}
                    if(!(arr65 == 0 || arr65 == 2)) {
                        if (arr65 == 1 && seen65 != roundNum) {
                            seen65 = roundNum;
                            Queue.queuePush(101);
                            dist65 = dist64 + 1;
                        }}
                    if(!(arr73 == 0 || arr73 == 2)) {
                        if (arr73 == 1 && seen73 != roundNum) {
                            seen73 = roundNum;
                            Queue.queuePush(115);
                            dist73 = dist64 + 1;
                        }}
                    if(!(arr74 == 0 || arr74 == 2)) {
                        if (arr74 == 1 && seen74 != roundNum) {
                            seen74 = roundNum;
                            Queue.queuePush(116);
                            dist74 = dist64 + 1;
                        }}
                    if(!(arr75 == 0 || arr75 == 2)) {
                        if (arr75 == 1 && seen75 != roundNum) {
                            seen75 = roundNum;
                            Queue.queuePush(117);
                            dist75 = dist64 + 1;
                        }}
                    break;
                case 101:
                    if(!(arr54 == 0 || arr54 == 2)) {
                        if (arr54 == 1 && seen54 != roundNum) {
                            seen54 = roundNum;
                            Queue.queuePush(84);
                            dist54 = dist65 + 1;
                        }}
                    if(!(arr55 == 0 || arr55 == 2)) {
                        if (arr55 == 1 && seen55 != roundNum) {
                            seen55 = roundNum;
                            Queue.queuePush(85);
                            dist55 = dist65 + 1;
                        }}
                    if(!(arr56 == 0 || arr56 == 2)) {
                        if (arr56 == 1 && seen56 != roundNum) {
                            seen56 = roundNum;
                            Queue.queuePush(86);
                            dist56 = dist65 + 1;
                        }}
                    if(!(arr64 == 0 || arr64 == 2)) {
                        if (arr64 == 1 && seen64 != roundNum) {
                            seen64 = roundNum;
                            Queue.queuePush(100);
                            dist64 = dist65 + 1;
                        }}
                    if(!(arr65 == 0 || arr65 == 2)) {
                        if (arr65 == 1 && seen65 != roundNum) {
                            seen65 = roundNum;
                            Queue.queuePush(101);
                            dist65 = dist65 + 1;
                        }}
                    if(!(arr66 == 0 || arr66 == 2)) {
                        if (arr66 == 1 && seen66 != roundNum) {
                            seen66 = roundNum;
                            Queue.queuePush(102);
                            dist66 = dist65 + 1;
                        }}
                    if(!(arr74 == 0 || arr74 == 2)) {
                        if (arr74 == 1 && seen74 != roundNum) {
                            seen74 = roundNum;
                            Queue.queuePush(116);
                            dist74 = dist65 + 1;
                        }}
                    if(!(arr75 == 0 || arr75 == 2)) {
                        if (arr75 == 1 && seen75 != roundNum) {
                            seen75 = roundNum;
                            Queue.queuePush(117);
                            dist75 = dist65 + 1;
                        }}
                    if(!(arr76 == 0 || arr76 == 2)) {
                        if (arr76 == 1 && seen76 != roundNum) {
                            seen76 = roundNum;
                            Queue.queuePush(118);
                            dist76 = dist65 + 1;
                        }}
                    break;
                case 102:
                    if(!(arr55 == 0 || arr55 == 2)) {
                        if (arr55 == 1 && seen55 != roundNum) {
                            seen55 = roundNum;
                            Queue.queuePush(85);
                            dist55 = dist66 + 1;
                        }}
                    if(!(arr56 == 0 || arr56 == 2)) {
                        if (arr56 == 1 && seen56 != roundNum) {
                            seen56 = roundNum;
                            Queue.queuePush(86);
                            dist56 = dist66 + 1;
                        }}
                    if(!(arr57 == 0 || arr57 == 2)) {
                        if (arr57 == 1 && seen57 != roundNum) {
                            seen57 = roundNum;
                            Queue.queuePush(87);
                            dist57 = dist66 + 1;
                        }}
                    if(!(arr65 == 0 || arr65 == 2)) {
                        if (arr65 == 1 && seen65 != roundNum) {
                            seen65 = roundNum;
                            Queue.queuePush(101);
                            dist65 = dist66 + 1;
                        }}
                    if(!(arr66 == 0 || arr66 == 2)) {
                        if (arr66 == 1 && seen66 != roundNum) {
                            seen66 = roundNum;
                            Queue.queuePush(102);
                            dist66 = dist66 + 1;
                        }}
                    if(!(arr67 == 0 || arr67 == 2)) {
                        if (arr67 == 1 && seen67 != roundNum) {
                            seen67 = roundNum;
                            Queue.queuePush(103);
                            dist67 = dist66 + 1;
                        }}
                    if(!(arr75 == 0 || arr75 == 2)) {
                        if (arr75 == 1 && seen75 != roundNum) {
                            seen75 = roundNum;
                            Queue.queuePush(117);
                            dist75 = dist66 + 1;
                        }}
                    if(!(arr76 == 0 || arr76 == 2)) {
                        if (arr76 == 1 && seen76 != roundNum) {
                            seen76 = roundNum;
                            Queue.queuePush(118);
                            dist76 = dist66 + 1;
                        }}
                    break;
                case 103:
                    if(!(arr56 == 0 || arr56 == 2)) {
                        if (arr56 == 1 && seen56 != roundNum) {
                            seen56 = roundNum;
                            Queue.queuePush(86);
                            dist56 = dist67 + 1;
                        }}
                    if(!(arr57 == 0 || arr57 == 2)) {
                        if (arr57 == 1 && seen57 != roundNum) {
                            seen57 = roundNum;
                            Queue.queuePush(87);
                            dist57 = dist67 + 1;
                        }}
                    if(!(arr66 == 0 || arr66 == 2)) {
                        if (arr66 == 1 && seen66 != roundNum) {
                            seen66 = roundNum;
                            Queue.queuePush(102);
                            dist66 = dist67 + 1;
                        }}
                    if(!(arr67 == 0 || arr67 == 2)) {
                        if (arr67 == 1 && seen67 != roundNum) {
                            seen67 = roundNum;
                            Queue.queuePush(103);
                            dist67 = dist67 + 1;
                        }}
                    if(!(arr76 == 0 || arr76 == 2)) {
                        if (arr76 == 1 && seen76 != roundNum) {
                            seen76 = roundNum;
                            Queue.queuePush(118);
                            dist76 = dist67 + 1;
                        }}
                    break;
                case 114:
                    if(!(arr61 == 0 || arr61 == 2)) {
                        if (arr61 == 1 && seen61 != roundNum) {
                            seen61 = roundNum;
                            Queue.queuePush(97);
                            dist61 = dist72 + 1;
                        }}
                    if(!(arr62 == 0 || arr62 == 2)) {
                        if (arr62 == 1 && seen62 != roundNum) {
                            seen62 = roundNum;
                            Queue.queuePush(98);
                            dist62 = dist72 + 1;
                        }}
                    if(!(arr63 == 0 || arr63 == 2)) {
                        if (arr63 == 1 && seen63 != roundNum) {
                            seen63 = roundNum;
                            Queue.queuePush(99);
                            dist63 = dist72 + 1;
                        }}
                    if(!(arr72 == 0 || arr72 == 2)) {
                        if (arr72 == 1 && seen72 != roundNum) {
                            seen72 = roundNum;
                            Queue.queuePush(114);
                            dist72 = dist72 + 1;
                        }}
                    if(!(arr73 == 0 || arr73 == 2)) {
                        if (arr73 == 1 && seen73 != roundNum) {
                            seen73 = roundNum;
                            Queue.queuePush(115);
                            dist73 = dist72 + 1;
                        }}
                    break;
                case 115:
                    if(!(arr62 == 0 || arr62 == 2)) {
                        if (arr62 == 1 && seen62 != roundNum) {
                            seen62 = roundNum;
                            Queue.queuePush(98);
                            dist62 = dist73 + 1;
                        }}
                    if(!(arr63 == 0 || arr63 == 2)) {
                        if (arr63 == 1 && seen63 != roundNum) {
                            seen63 = roundNum;
                            Queue.queuePush(99);
                            dist63 = dist73 + 1;
                        }}
                    if(!(arr64 == 0 || arr64 == 2)) {
                        if (arr64 == 1 && seen64 != roundNum) {
                            seen64 = roundNum;
                            Queue.queuePush(100);
                            dist64 = dist73 + 1;
                        }}
                    if(!(arr72 == 0 || arr72 == 2)) {
                        if (arr72 == 1 && seen72 != roundNum) {
                            seen72 = roundNum;
                            Queue.queuePush(114);
                            dist72 = dist73 + 1;
                        }}
                    if(!(arr73 == 0 || arr73 == 2)) {
                        if (arr73 == 1 && seen73 != roundNum) {
                            seen73 = roundNum;
                            Queue.queuePush(115);
                            dist73 = dist73 + 1;
                        }}
                    if(!(arr74 == 0 || arr74 == 2)) {
                        if (arr74 == 1 && seen74 != roundNum) {
                            seen74 = roundNum;
                            Queue.queuePush(116);
                            dist74 = dist73 + 1;
                        }}
                    break;
                case 116:
                    if(!(arr63 == 0 || arr63 == 2)) {
                        if (arr63 == 1 && seen63 != roundNum) {
                            seen63 = roundNum;
                            Queue.queuePush(99);
                            dist63 = dist74 + 1;
                        }}
                    if(!(arr64 == 0 || arr64 == 2)) {
                        if (arr64 == 1 && seen64 != roundNum) {
                            seen64 = roundNum;
                            Queue.queuePush(100);
                            dist64 = dist74 + 1;
                        }}
                    if(!(arr65 == 0 || arr65 == 2)) {
                        if (arr65 == 1 && seen65 != roundNum) {
                            seen65 = roundNum;
                            Queue.queuePush(101);
                            dist65 = dist74 + 1;
                        }}
                    if(!(arr73 == 0 || arr73 == 2)) {
                        if (arr73 == 1 && seen73 != roundNum) {
                            seen73 = roundNum;
                            Queue.queuePush(115);
                            dist73 = dist74 + 1;
                        }}
                    if(!(arr74 == 0 || arr74 == 2)) {
                        if (arr74 == 1 && seen74 != roundNum) {
                            seen74 = roundNum;
                            Queue.queuePush(116);
                            dist74 = dist74 + 1;
                        }}
                    if(!(arr75 == 0 || arr75 == 2)) {
                        if (arr75 == 1 && seen75 != roundNum) {
                            seen75 = roundNum;
                            Queue.queuePush(117);
                            dist75 = dist74 + 1;
                        }}
                    break;
                case 117:
                    if(!(arr64 == 0 || arr64 == 2)) {
                        if (arr64 == 1 && seen64 != roundNum) {
                            seen64 = roundNum;
                            Queue.queuePush(100);
                            dist64 = dist75 + 1;
                        }}
                    if(!(arr65 == 0 || arr65 == 2)) {
                        if (arr65 == 1 && seen65 != roundNum) {
                            seen65 = roundNum;
                            Queue.queuePush(101);
                            dist65 = dist75 + 1;
                        }}
                    if(!(arr66 == 0 || arr66 == 2)) {
                        if (arr66 == 1 && seen66 != roundNum) {
                            seen66 = roundNum;
                            Queue.queuePush(102);
                            dist66 = dist75 + 1;
                        }}
                    if(!(arr74 == 0 || arr74 == 2)) {
                        if (arr74 == 1 && seen74 != roundNum) {
                            seen74 = roundNum;
                            Queue.queuePush(116);
                            dist74 = dist75 + 1;
                        }}
                    if(!(arr75 == 0 || arr75 == 2)) {
                        if (arr75 == 1 && seen75 != roundNum) {
                            seen75 = roundNum;
                            Queue.queuePush(117);
                            dist75 = dist75 + 1;
                        }}
                    if(!(arr76 == 0 || arr76 == 2)) {
                        if (arr76 == 1 && seen76 != roundNum) {
                            seen76 = roundNum;
                            Queue.queuePush(118);
                            dist76 = dist75 + 1;
                        }}
                    break;
                case 118:
                    if(!(arr65 == 0 || arr65 == 2)) {
                        if (arr65 == 1 && seen65 != roundNum) {
                            seen65 = roundNum;
                            Queue.queuePush(101);
                            dist65 = dist76 + 1;
                        }}
                    if(!(arr66 == 0 || arr66 == 2)) {
                        if (arr66 == 1 && seen66 != roundNum) {
                            seen66 = roundNum;
                            Queue.queuePush(102);
                            dist66 = dist76 + 1;
                        }}
                    if(!(arr67 == 0 || arr67 == 2)) {
                        if (arr67 == 1 && seen67 != roundNum) {
                            seen67 = roundNum;
                            Queue.queuePush(103);
                            dist67 = dist76 + 1;
                        }}
                    if(!(arr75 == 0 || arr75 == 2)) {
                        if (arr75 == 1 && seen75 != roundNum) {
                            seen75 = roundNum;
                            Queue.queuePush(117);
                            dist75 = dist76 + 1;
                        }}
                    if(!(arr76 == 0 || arr76 == 2)) {
                        if (arr76 == 1 && seen76 != roundNum) {
                            seen76 = roundNum;
                            Queue.queuePush(118);
                            dist76 = dist76 + 1;
                        }}
                    break;
            }
        }

        System.out.println("C: " + Clock.getBytecodesLeft());

        int x = currentTarget.x - rc.getLocation().x + 4;
        int y = currentTarget.y - rc.getLocation().y + 4;

        switch (10 * x + y) {
            case 12:
                if (seen12 != roundNum) { return null; }
                return backtrack12();
            case 13:
                if (seen13 != roundNum) { return null; }
                return backtrack13();
            case 14:
                if (seen14 != roundNum) { return null; }
                return backtrack14();
            case 15:
                if (seen15 != roundNum) { return null; }
                return backtrack15();
            case 16:
                if (seen16 != roundNum) { return null; }
                return backtrack16();
            case 21:
                if (seen21 != roundNum) { return null; }
                return backtrack21();
            case 22:
                if (seen22 != roundNum) { return null; }
                return backtrack22();
            case 23:
                if (seen23 != roundNum) { return null; }
                return backtrack23();
            case 24:
                if (seen24 != roundNum) { return null; }
                return backtrack24();
            case 25:
                if (seen25 != roundNum) { return null; }
                return backtrack25();
            case 26:
                if (seen26 != roundNum) { return null; }
                return backtrack26();
            case 27:
                if (seen27 != roundNum) { return null; }
                return backtrack27();
            case 31:
                if (seen31 != roundNum) { return null; }
                return backtrack31();
            case 32:
                if (seen32 != roundNum) { return null; }
                return backtrack32();
            case 33:
                if (seen33 != roundNum) { return null; }
                return backtrack33();
            case 34:
                if (seen34 != roundNum) { return null; }
                return backtrack34();
            case 35:
                if (seen35 != roundNum) { return null; }
                return backtrack35();
            case 36:
                if (seen36 != roundNum) { return null; }
                return backtrack36();
            case 37:
                if (seen37 != roundNum) { return null; }
                return backtrack37();
            case 41:
                if (seen41 != roundNum) { return null; }
                return backtrack41();
            case 42:
                if (seen42 != roundNum) { return null; }
                return backtrack42();
            case 43:
                if (seen43 != roundNum) { return null; }
                return backtrack43();
            case 44:
                if (seen44 != roundNum) { return null; }
                return backtrack44();
            case 45:
                if (seen45 != roundNum) { return null; }
                return backtrack45();
            case 46:
                if (seen46 != roundNum) { return null; }
                return backtrack46();
            case 47:
                if (seen47 != roundNum) { return null; }
                return backtrack47();
            case 51:
                if (seen51 != roundNum) { return null; }
                return backtrack51();
            case 52:
                if (seen52 != roundNum) { return null; }
                return backtrack52();
            case 53:
                if (seen53 != roundNum) { return null; }
                return backtrack53();
            case 54:
                if (seen54 != roundNum) { return null; }
                return backtrack54();
            case 55:
                if (seen55 != roundNum) { return null; }
                return backtrack55();
            case 56:
                if (seen56 != roundNum) { return null; }
                return backtrack56();
            case 57:
                if (seen57 != roundNum) { return null; }
                return backtrack57();
            case 61:
                if (seen61 != roundNum) { return null; }
                return backtrack61();
            case 62:
                if (seen62 != roundNum) { return null; }
                return backtrack62();
            case 63:
                if (seen63 != roundNum) { return null; }
                return backtrack63();
            case 64:
                if (seen64 != roundNum) { return null; }
                return backtrack64();
            case 65:
                if (seen65 != roundNum) { return null; }
                return backtrack65();
            case 66:
                if (seen66 != roundNum) { return null; }
                return backtrack66();
            case 67:
                if (seen67 != roundNum) { return null; }
                return backtrack67();
            case 72:
                if (seen72 != roundNum) { return null; }
                return backtrack72();
            case 73:
                if (seen73 != roundNum) { return null; }
                return backtrack73();
            case 74:
                if (seen74 != roundNum) { return null; }
                return backtrack74();
            case 75:
                if (seen75 != roundNum) { return null; }
                return backtrack75();
            case 76:
                if (seen76 != roundNum) { return null; }
                return backtrack76();
        }

        return null;
    }
}

