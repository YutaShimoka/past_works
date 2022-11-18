#!/bin/bash

arg="$1"

if [ -z "${arg}" ]; then
    exit
fi

if [[ tmp${arg}tmp =~ ^.*[^0-9]+[0-9]{8}[^0-9]+[0-9]{8}[^0-9]+.*$ ]]; then
    echo "8-digit numbers in '${arg}' is ambiguous"
    exit 1
fi
