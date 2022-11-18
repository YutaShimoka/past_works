#!/bin/sh

target_url="$1" # required

if [ -z "${target_url}" ]; then
    echo "no argument specified"
    exit 1
fi

result=$(curl -Ss "${target_url}" -o /dev/null -w '%{http_code}\n')
if [ "${result}" != 200 ]; then
    echo "url=${target_url}, http_code=${result}"
    exit 1
else
    echo "${result}"
fi
