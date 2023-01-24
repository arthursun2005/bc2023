rad = 15

def invalid(x, y):
    if ((4 - x) ** 2 + (4 - y) ** 2 > rad): return True
    return False

from collections import defaultdict

new_dic = defaultdict(dict)

new_dic[-1][-1] = "Direction.SOUTHWEST"
new_dic[0][1] = "Direction.NORTH"
new_dic[1][1] = "Direction.NORTHEAST"
new_dic[1][0] = "Direction.EAST"
new_dic[1][-1] = "Direction.SOUTHEAST"
new_dic[0][-1] = "Direction.SOUTH"
new_dic[-1][0] = "Direction.WEST"
new_dic[-1][1] = "Direction.NORTHWEST"

print("""package sylveontest;

import battlecode.common.*;

public class BFS {

    RobotController rc;
    public BFS(RobotController rc) {
        this.rc = rc;
    }

    public void reset() {
        Queue.reset();
    }

    """)

for i in range(0, 9):
    for j in range(0, 9):
        if ((i-4)**2 + (j-4)**2 > rad): continue


        print("int dist{} = 0;".format(10 * i + j));
        print("int seen{} = 0;".format(10 * i + j));
        print("int arr{} = 0;".format(10 * i + j));

print("int roundNum = 0;");

for i in range(0, 9):
    for j in range(0, 9):
        if ((i-4)**2 + (j-4)**2 > rad): continue

        print("public Direction backtrack{}() {{".format(i * 10 + j))
        for dx in range(-1, 2):
            for dy in range(-1, 2):
                if invalid(i + dx, j + dy): continue
                if (dx == 0 and dy == 0): continue

                nx = i + dx
                ny = j + dy

                print("if (dist{} == dist{} - 1) {{".format(nx * 10 + ny, i * 10 + j))
                print("if (dist{} == 0 && seen{} == roundNum) {{".format(nx * 10 + ny, nx * 10 + ny))
                print("return {};}}".format(new_dic[-dx][-dy]))
                print("return backtrack{}();".format(nx * 10 + ny))
                print("}")
        print("return null;"
              "}")


print(""" Direction tryBFS(MapLocation currentTarget) throws GameActionException {
    reset();""")

for i in range(0, 9):
    for j in range(0, 9):
        if ((i-4)**2 + (j-4)**2 > rad): continue


        # print("seen{} = 0;".format(10 * i + j));
        # print("arr{} = 0;".format(10 * i + j));
        # print("dist{} = 0;".format(10 * i + j));

print("""
        MapInfo[] infos = rc.senseNearbyMapInfos();


        RobotInfo[] robotInfos = rc.senseNearbyRobots();

        MapLocation starting = rc.getLocation();
        MapLocation mapLocation;
        """)


print("""       System.out.println("A: " + Clock.getBytecodesLeft());""")

print("""for (MapInfo info : infos) {
            mapLocation = info.getMapLocation();

            int x = mapLocation.x - starting.x + 4, y = mapLocation.y - starting.y + 4;

            switch (10 * x + y) {""")

for i in range(0, 9):
    for j in range(0, 9):
        if ((i-4)**2 + (j-4)**2 > rad): continue

        print("case {}:".format((i * 10) + j))
        # print("                if (arr{} == 2) {{".format(10 * i + j))
        # print("            arr{} = 2;".format(10 * i + j))
        # print("        }")
        print("""        if (!info.isPassable()) {""")
        print("            arr{} = 2;".format(10 * i + j))
        print("        } else if (info.getCurrentDirection() != Direction.CENTER) {")
        print("            arr{} = 2;".format(10 * i + j))
        print("        } else {")
        print("            arr{} = 1;".format(10 * i + j))
        print("""        }""")
        print("break;")


print("""            }
        }""")

print("""for (RobotInfo robotInfo : robotInfos) {
            mapLocation = robotInfo.getLocation();
            int x = mapLocation.x - starting.x + 4, y = mapLocation.y - starting.y + 4;
            switch(10 * x + y) {""")

for i in range(0, 9):
    for j in range(0, 9):
        if ((i-4)**2 + (j-4)**2 > rad): continue
        print("case {}:".format((i * 10) + j))
        print("arr{} = 2;".format(10 * i + j))
        print("break;")

print("""            }
        }""")

print("System.out.println(currentTarget + \" \" + rc.getLocation());");
print("switch((currentTarget.x - rc.getLocation().x + 4) * 10 + (currentTarget.y - rc.getLocation().y + 4)) {")
for i in range(0, 9):
    for j in range(0, 9):
        if ((i-4)**2 + (j-4)**2 > rad): continue
        print("case {}:".format((i * 10) + j))
        print("arr{} = 1;".format(10 * i + j))
        print("System.out.println(\"{}, {}\");".format(i, j))
        print("break;")
print("""            }""")

print("""        System.out.println("B: " + Clock.getBytecodesLeft());


        int dx = 4, dy = 4;

        Queue.queuePush((dx << 4) + dy);""")

print("roundNum = rc.getRoundNum();");
print("seen{} = roundNum;".format(4 * 10  +4));
print("dist44 = 0;")

print("""
        while (!Queue.queueEmpty()) {
            int current = Queue.queuePop();

            switch (current) {""")

for i in range(0, 9):
    for j in range(0, 9):
        if ((i - 4) ** 2 + (j - 4) ** 2 > rad): continue

        print("case {}:".format((i << 4) + j))

        for dx in range(-1, 2):
            for dy in range(-1, 2):
                if invalid(i + dx, j + dy): continue

                nx = i + dx;
                ny = j + dy;
                print("if(!(arr{} == 0 || arr{} == 2)) {{".format(10 * nx + ny, 10 * nx + ny))
                print("if (arr{} == 1 && seen{} != roundNum) {{".format(10 * nx + ny, 10 * nx + ny))
                print("seen{} = roundNum;".format(10 * nx + ny))
                print("Queue.queuePush({});".format((nx << 4) + ny))
                print("dist{} = dist{} + 1;".format(10 * nx + ny, 10 * i + j))
                print("}}")
        print("break;")

print("""}
}

        System.out.println("C: " + Clock.getBytecodesLeft());
""")


print("""        int x = currentTarget.x - rc.getLocation().x + 4;
        int y = currentTarget.y - rc.getLocation().y + 4;

        switch (10 * x + y) {""")

for i in range(0, 9):
    for j in range(0, 9):
        if ((i - 4) ** 2 + (j - 4) ** 2 > rad): continue

        print("case {}:".format((i * 10) + j))
        print("if (seen{} != roundNum) {{ return null; }}".format(i * 10 + j))
        print("return backtrack{}();".format(i * 10 + j))

print("}")

print("""
        return null;
    }
}
""")
