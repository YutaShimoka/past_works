#!/bin/sh

CSVQ_DIR="csvq"

if [ ! -f ${CSVQ_DIR}/csvq.exe ]; then
    echo "csvq does not exist"
    exit 1
fi

target_receipts=$(find data -type f)

if [ -d result ]; then
    rm -r result
fi

mkdir -p result/total_due

for target_receipt in ${target_receipts}; do
    prefix=$(basename "${target_receipt}" .csv)_

    ## create a total due list
    ${CSVQ_DIR}/csvq.exe -o result/total_due/"${prefix}"total_due_list.csv -f CSV -E UTF8 -s sql/total_due/create_total_due_list.sql <"${target_receipt}"

    ## create a list of merges by date
    ${CSVQ_DIR}/csvq.exe -o result/total_due/"${prefix}"merged_by_date.csv -f CSV -E UTF8 -s sql/total_due/merge_by_date.sql <"${target_receipt}"
done
