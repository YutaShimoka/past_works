#!/usr/bin/env bash

set -e          # 1. エラー発生時、即時停止するため。
set -o pipefail # 2. パイプの途中で起きたエラーを検知するため。
set -o errtrace # 3. 関数で発生したエラーを検知するため。

err_holder() {
    num="$1"
    echo "[ERROR] Oops, an error occurred. [L${num}]"
}

trap 'err_holder $LINENO' ERR
