import random

for i in range(20000):
    print(f'\\u{random.randrange(65536):04x}', end='')
