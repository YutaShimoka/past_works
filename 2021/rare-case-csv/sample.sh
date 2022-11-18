#!/bin/sh

target_directory="$1" # optional

if [ -z "${target_directory}" ]; then
    target_directory="./" # set default value (current directory)
fi

if [ ! -d ${target_directory} ]; then
    echo "no such directory : ${target_directory}"
    exit 1
fi

csv_files=$(find ${target_directory} -maxdepth 1 -name "*.csv" -type f | sort)

if [ "${csv_files}" = '' ]; then
    echo "there is no csv file"
    exit 1
fi

if [ ! -d result ]; then
    mkdir result
fi

path="result/output_$(date +%Y%m%d%H%M%S).csv"

first_flag="true"

isUtf8() {
    if [ "$(file -i "$1" | grep utf-8)" != '' ] || [ "$(file -i "$1" | grep ascii)" != '' ]; then
        echo "[debug] isUtf8=true"
    else
        echo "[debug] isUtf8=false"
        return 1
    fi
}

for csv_file in ${csv_files}; do
    echo "[debug] ##### start ${csv_file}"
    if ! isUtf8 "${csv_file}"; then
        echo "skip process since file encoding is not UTF-8"
    else
        if [ ${first_flag} = 'true' ]; then
            sed -n '24p' <"${csv_file}" | sed s/$/,source_file_name/g >"${path}"
            first_flag="false"
        fi
        sed '1,24d' "${csv_file}" | sed s/$/\\n/g | sed '/^$/d' | sed s/$/,"$(basename "${csv_file}")"/g >>"${path}"
    fi
done

echo "result file: ${path}"
