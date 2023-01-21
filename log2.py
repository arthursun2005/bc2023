for i in range(0, 32):
    x = f'''case {2 ** i}:
return log + {i};
    '''
    print(x)