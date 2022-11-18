#!/usr/bin/env bash
set -euo pipefail

source_path="$1"
destination_path="result/output.txt"

dup_list() {
    LC_ALL=C sort <"$1" | uniq -d
}

if [ ! -f "${source_path}" ]; then
    echo "[ERROR] '${source_path}' は存在しません。"
    exit 1
fi

if [ ! -d result ]; then
    mkdir result
fi

LC_ALL=C sort "${source_path}" | uniq >${destination_path}
echo "created '${destination_path}'"

# 重複チェック
if [ "$(dup_list "${source_path}")" != '' ]; then
    echo "[WARN] 入力ファイルでは以下のデータが重複していました。"
    dup_list "${source_path}"
fi
