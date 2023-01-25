import random

for i in range(1069):
    print(f'\\u{random.randrange(65536):04x}', end='')
