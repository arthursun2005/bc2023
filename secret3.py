for i in range(1, 101):
    print(f'''
          case {i}:
          return s{i}.charAt((cur++) & 255) - 369;''')
