#!/usr/bin/env python

import MeCab
import os
import re
import sys
from pathlib import Path

pathDir = 'test/resources'
if len(sys.argv) > 1:
    file_name = sys.argv[1]
    if not os.path.isfile(os.path.join(pathDir, file_name)):
        print('\'' + file_name + '\' does not exist. nothing to do.')
        sys.exit(1)
    else:
        input_file = open(pathDir + '/' + file_name, 'r', encoding="sjis")
else:
    print('no argument specified')
    sys.exit(1)

os.makedirs('result', exist_ok=True)
output_file = open('result/' + Path(input_file.name).stem + '.csv', 'w', encoding='utf-8', newline='\n')

line = input_file.readline()
mecab = MeCab.Tagger()

while line:
    line = re.sub('《.*?》|［.*?］|〔|〕', '', line).replace('｜', '')  # data cleaning
    node = mecab.parseToNode(line)
    while node:
        if node.surface != '':
            output_file.write(node.surface + ',' + node.feature + '\n')
        node = node.next
    line = input_file.readline()

input_file.close()

print('result file: ' + output_file.name)
output_file.close()
