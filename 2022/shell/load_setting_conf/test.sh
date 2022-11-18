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

environment=$1

expInput="production|admin"
if [[ ! ${environment} =~ ^(${expInput})$ ]]; then
	echo "[ERROR] please use the following command: \`sh $(basename "$0") [${expInput}]\`"
	exit 1
fi

conf_file=./conf/${environment}.conf

if [ -f "${conf_file}" ]; then
	source "${conf_file}"
else
	echo "[ERROR] '${conf_file}' does not exist"
	exit 1
fi

echo "[DEBUG] test_val=${test_val}"

echo "[INFO] *****$(basename "$0") END*****"
