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

source_file="./test.tsv"

print_func() {
	fruit=$1
	owner=$2
	printf '[DEBUG] %s\t%s\n' "${fruit}" "${owner}"
}

is_header="true"
while IFS=$'\t' read -r fruit owner; do
	if [ ${is_header} = "true" ]; then
		is_header="false"
	else
		if [ "$(echo "${fruit}" | grep ",")" = '' ]; then
			## 1. 通常のフロー
			## ---
			print_func "${fruit}" "${owner}"
		else
			## 2. イレギュラーなフロー
			## ---
			## カンマ区切りで配列に格納
			IFS=, read -ra fruit_array <<<"${fruit}"
			## 配列をループ
			for fruit in "${fruit_array[@]}"; do
				print_func "${fruit}" "${owner}"
			done
		fi
	fi
done <"${source_file}"

echo "[INFO] *****$(basename "$0") END*****"
