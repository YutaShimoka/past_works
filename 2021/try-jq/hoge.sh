#!/bin/sh

if [ ! -d result ]; then
    mkdir result
fi

jsonq/jq -r '.data[]? | {"id": .ids[]?, str} | map(.) | @csv' <fixtures/example.json >result/hoge.csv
