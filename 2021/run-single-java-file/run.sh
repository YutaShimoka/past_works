#!/bin/bash

path="$1" # required

if [ -z "${path}" ]; then
    echo "no argument specified"
    exit 1
fi

regexp="^.*\.java$"

if [[ ! ${path} =~ ${regexp} ]]; then
    echo "must match \"${regexp}\""
    exit 1
fi

if [ ! -f "${path}" ]; then
    echo "'${path}' does not exist. nothing to do."
    exit 1
fi

compile_func() {
    echo "[debug] #####compile"
    javac -encoding utf-8 "${path}"
}

echo "[debug] #####start ${path}#####"
if compile_func; then
    cd "$(dirname "${path}")" || exit

    echo "[debug] #####execute"
    java "$(basename "${path}" .java)"

    rm "$(basename "${path}" .java)".class
fi
