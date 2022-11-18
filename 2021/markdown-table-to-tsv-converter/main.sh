#!/bin/sh
path="$1"

if [ -z "${path}" ]; then
    echo "no argument specified"
    exit 1
fi

if [ -f "${path}" ]; then

    echo "start process since '${path}' exists"

    if [ ! -d result ]; then
        mkdir result
    fi

    fileName=$(basename "${path}" .md)

    sed s/\\r\\n/\\n/g <"${path}" | grep ^\| | grep \|$ | sed '2d' | sed s/^\|//g | sed s/\|$//g | sed s/\|/\\t/g | gzip >"result/${fileName}.tsv.gz"

else

    echo "'${path}' does not exist. nothing to do."
    exit 1

fi
