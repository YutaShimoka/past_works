#!/bin/sh
path="$1" # required

if [ -z "${path}" ]; then
    echo "no argument specified"
    exit 1
fi

if [ ! -f "${path}" ]; then
    echo "'${path}' does not exist. nothing to do."
    exit 1
fi

if [ "$(file -i "${path}" | grep unknown-8bit)" != '' ] || [ "$(file -i "${path}" | grep ascii)" != '' ]; then
    echo "[debug] isSjis=true"
else
    echo "[debug] isSjis=false"
    exit 1
fi
