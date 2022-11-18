#!/usr/bin/env python

import secrets
import string
import sys

# Initial Setting
sequence = string.ascii_letters + string.digits
length = 8  # パスワードの長さ
count = 1   # 生成数

if len(sys.argv) == 2:
    if sys.argv[1] == '-y':  # e.g. ./pwgen.py -y
        sequence = sequence + string.punctuation
    else:                    # e.g. ./pwgen.py 20
        length = int(sys.argv[1])
elif len(sys.argv) == 3:
    if sys.argv[1] == '-y':  # e.g. ./pwgen.py -y 20
        sequence = sequence + string.punctuation
        length = int(sys.argv[2])
    else:                    # e.g. ./pwgen.py 20 4
        length = int(sys.argv[1])
        count = int(sys.argv[2])
elif len(sys.argv) == 4:     # e.g. ./pwgen.py -y 20 4
    sequence = sequence + string.punctuation
    length = int(sys.argv[2])
    count = int(sys.argv[3])


def password_generator(length):
    return ''.join(secrets.choice(sequence) for x in range(length))


i = 0
while(i < count):
    print(password_generator(length))
    i += 1
