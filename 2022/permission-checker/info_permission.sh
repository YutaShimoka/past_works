#!/usr/bin/env bash
set -e -o pipefail -o errtrace
err_holder() {
    num="$1"
    echo "[ERROR] Oops, an error occurred. [L${num}]"
    printf '\a'
}
trap 'err_holder $LINENO' ERR

file_name=$1 # required

if [ -z "${file_name}" ]; then
    echo "no argument specified"
    exit 1
fi

if [ ! -f "${file_name}" ]; then
    echo "'${file_name}' does not exist."
    exit 1
fi

file_detail_info=$(ls -l "${file_name}")
permission=$(echo "${file_detail_info}" | awk -F ' ' '{print $1}')

user=$(echo "${permission}" | cut -c 2-4)
group=$(echo "${permission}" | cut -c 5-7)
others=$(echo "${permission}" | cut -c 8-10)

elements=("${user}" "${group}" "${others}")
for e in "${elements[@]}"; do
    permission_human+=$(echo "${e}" | sed -e 's/---/0/g;s/--x/1/g;s/-w-/2/g;s/-wx/3/g;s/r--/4/g;s/r-x/5/g;s/rw-/6/g;s/rwx/7/;')
done

echo "permission_human:${permission_human}"
