#!/bin/bash

file_name="$1"

if [ ! -f "${file_name}" ]; then
	echo "'${file_name}' does not exist. nothing to do."
	exit 1
fi

if [ ! -d processing ]; then
	mkdir processing
fi

if [ ! -d result ]; then
	mkdir result
fi

tmpfile=processing/tmpfile

if [ ! "$2" = "--rev" ]; then
	tac "${file_name}" >${tmpfile}
else
	cp -p "${file_name}" ${tmpfile}
fi

rev() {
	num=$(wc -l <"$1")
	for ((i = 1; i <= "${num}"; i++)); do
		str=$(sed -n "$i"p "$1")
		python tools/rev_str.py "${str}" >>"$2"
	done
}

result_file=result/$(basename "${file_name}")

if [ -f "${result_file}" ]; then
	rm -iv "${result_file}"
fi

rev ${tmpfile} "${result_file}"

echo "result file: ${result_file}"

rm ${tmpfile}
