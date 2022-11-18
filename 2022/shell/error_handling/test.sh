#!/usr/bin/env bash
source ./sample_handle_err.sh

arg=$1

echo "[INFO] *****$(basename "$0") START*****"

# Functions
sub_func() {
    echo "[DEBUG] sub function : start"
    if [ "${arg}" = "test_03" ]; then
        echo "[INFO] $((LINENO + 1))行目でエラーが発生し、即時停止します。"
        true | false | true | true
    fi
    echo "[DEBUG] sub function : end"
}

main() {
    echo "[DEBUG] main function : start"
    if [ "${arg}" = "test_02" ]; then
        echo "[INFO] $((LINENO + 1))行目でエラーが発生し、即時停止します。"
        true | true | true | false
    fi
    sub_func
    echo "[DEBUG] main function : end"
}

# Actions
main
if [ "${arg}" = "test_01" ]; then
    echo "[INFO] $((LINENO + 1))行目でエラーが発生し、即時停止します。"
    false | false | false | true
fi

echo "[INFO] *****$(basename "$0") END*****"
