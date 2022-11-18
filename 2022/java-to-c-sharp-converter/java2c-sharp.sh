#!/usr/bin/env bash
set -e -o pipefail -o errtrace
err_holder() {
	num="$1"
	echo "[ERROR] Oops, an error occurred. [L${num}]"
	printf '\a'
}
trap 'err_holder $LINENO' ERR

cd "$(dirname "$0")"

echo "[INFO] *****$(basename "$0") START*****"
echo "[INFO] start time: $(date "+%Y/%m/%d %T")"
start_time=$(date +%s)

# Functions

java2c_sharp() {
	java_file=$1
	sed -f c-sharp.sed "${java_file}"
}

# Actions

java_files_array=$(find ./source -maxdepth 1 -type f -name "*.java")

for java_file in ${java_files_array}; do
	echo "[INFO] convert $(basename "${java_file}") to $(basename "${java_file}" .java).cs"
	java2c_sharp "${java_file}" >"finished/$(basename "${java_file}" .java).cs"
done

echo "[INFO] end time: $(date "+%Y/%m/%d %T")"
echo "[INFO] execute time: $(($(date +%s) - "${start_time}")) sec"
echo "[INFO] *****$(basename "$0") END*****"
