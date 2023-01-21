for x in range(-4, 5):
    for y in range(-4, 5):
        if x * x + y * y <= 4: continue
        a = f'''
loc = new MapLocation(me.x + {x}, me.y + {y});
if (rc.canAttack(loc)) {{
    might[mc] = loc;
    mc++;
}}'''
        print(a)