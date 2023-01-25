for x in range(-4, 5):
    for y in range(-4, 5):
        if x * x + y * y <= 4: continue
        if x * x + y * y > 16: continue
        a = f'''
fx = me.x + {x};
fy = me.y + {y};
if (fx >= 0 && fx < width && fy >= 0 && fy < height) {{
    if (rng.nextInt(++mc) == 0) {{ mx = fx; my = fy; }}
}}'''
        print(a)
