with open('src/memtest/RobotPlayer.java') as fp:
    contents = fp.read()
contents = contents.replace('<fat>', 10000 * 'A')
with open('src/memtest/RobotPlayer.java', 'w') as fp:
    fp.write(contents)
