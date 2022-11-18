#!/usr/bin/env python

import csv
import glob
import os
import subprocess
import sys
import time

if len(sys.argv) > 1:
    pathDir = sys.argv[1]
    if os.path.exists(pathDir):
        target_files = pathDir + '\\*.csv'
    else:
        print('no such directory : ' + pathDir)
        sys.exit(1)
else:
    target_files = '*.csv'


def isUtf8():
    result = str(subprocess.check_output('chardetect ' + csv_file))
    if 'utf-8' in result or 'ascii' in result:
        print('[debug] isUtf8=true')
        return True
    else:
        print('[debug] isUtf8=false')
        return False


csv_files = sorted(glob.glob(target_files))

if not csv_files:
    print('there is no csv file')
else:
    os.makedirs('result', exist_ok=True)
    output_file = open('result/output_' + time.strftime('%Y%m%d%H%M%S') + '.csv', 'w', newline='\n')
    ## e.g. result/output_20211129003324.csv

    i = 0
    for csv_file in csv_files:
        print('[debug] ##### start ' + csv_file)
        if isUtf8():
            input_file = open(csv_file, 'r')
            j = 0
            for row in csv.reader(input_file):
                writer = csv.writer(output_file, lineterminator='\n')
                if i == 0 and j == 23:
                    row.append('source_file_name')
                    writer.writerow(row)
                    i += 1
                if j >= 24:
                    row.append(os.path.basename(input_file.name))
                    writer.writerow(row)
                j += 1
            input_file.close()
        else:
            print('skip process since file encoding is not UTF-8')
    print('result file: ' + output_file.name)
    output_file.close()
