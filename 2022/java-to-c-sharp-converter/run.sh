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

# Actions

## preparation

if [ -d tmp ]; then
	rm -r tmp
fi

mkdir tmp && cd "$_"

dotnet new console

## execution

cs_files_array=$(find ../finished -maxdepth 1 -type f -name "*.cs")

for cs_file in ${cs_files_array}; do
	echo "[INFO] run $(basename "${cs_file}")"
	cat "${cs_file}" >Program.cs
	dotnet run
	echo ""
done

rm -r ../"$(basename "$(pwd)")" # It means "rm -r ../tmp".

echo "[INFO] end time: $(date "+%Y/%m/%d %T")"
echo "[INFO] execute time: $(($(date +%s) - "${start_time}")) sec"
echo "[INFO] *****$(basename "$0") END*****"
