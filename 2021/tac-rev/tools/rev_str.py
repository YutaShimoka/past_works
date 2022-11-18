import sys

if len(sys.argv) > 1:
    str = sys.argv[1]
else:
    sys.exit()

rev_str = str[::-1]

sys.stdout.write(rev_str + '\n')
