#!/usr/bin/env bash
set -eu -o pipefail -o errtrace
err_holder() {
    num="$1"
    echo "[ERROR] Oops, an error occurred. [L${num}]"
    printf '\a'
}
trap 'err_holder $LINENO' ERR

cd "$(dirname "$0")"

echo "[INFO] *****$(basename "$0") START*****"
echo "[INFO] start time: $(date "+%Y/%m/%d %T")"
startTime=$(date +%s)

# Initial Setting

DEST_DIR=$HOME/Documents/Qiita

# Functions

import_func() {
    user=$1
    if [ ! -d "${user}" ]; then
        mkdir "${user}"
    fi
    source_hash_file_name=$2
    (
        cd "${user}"
        curl -Ss "https://qiita.com/${user}/items/${source_hash_file_name}.md" >"${source_hash_file_name}.md"
    )
    if [ ! -d .git ]; then
        git init
    fi
    git add .
    if [ "$(git status -s)" != '' ]; then
        git commit -a -m "Commit ${user}#${source_hash_file_name}.md from $(basename "$0")"
    fi
}

# Actions

user=$1                  # required
source_hash_file_name=$2 # required

## preparation
if [ ! -d "${DEST_DIR}" ]; then
    mkdir "${DEST_DIR}"
fi

cd "${DEST_DIR}"

## execute
import_func "${user}" "${source_hash_file_name}"

echo "[INFO] end time: $(date "+%Y/%m/%d %T")"
echo "[INFO] execute time: $(($(date +%s) - "${startTime}")) sec"
echo "[INFO] *****$(basename "$0") END*****"
