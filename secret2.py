import random

for i in range(269):
    for j in range(100):
        print(f'\\u{random.randrange(j+1)+169:04x}', end='')
