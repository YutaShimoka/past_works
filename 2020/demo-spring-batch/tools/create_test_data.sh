#!/bin/sh

sourcePath="$HOME/Downloads/13_tokyo_all_20201228/13_tokyo_all_20201228.csv"

if [ ! -f "${sourcePath}" ]; then
    echo "'${sourcePath}' does not exist. nothing to do."
    exit 1
fi

destinationDir="/c/local/hos"
testData="${destinationDir}/test_data.csv"

if [ ! -d ${destinationDir} ]; then
    mkdir -p ${destinationDir}
fi

sed '10000,$d' "${sourcePath}" >${testData}
sed -i "1i$(grep "株式会社ヘルメスシステムズ" "${sourcePath}")" ${testData}
gzip ${testData}
