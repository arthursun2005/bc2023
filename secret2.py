import random

for j in range(100):
    print(f"StringBuilder s{j + 1} = new StringBuilder(\"", end='')
    for i in range(256):
        print(f'\\u{random.randrange(j+1):04x}', end='')
    print("\");")
