#!/bin/sh

option="$1" # optional

if [ -n "${option}" ] && [ "${option}" != "-f" ] && [ "${option}" != "--force" ]; then
    option="$(echo "${option}" | sed s/^-//g | sed s/^-//g)"
    echo "./rename.sh: unknown option -- ${option}"
    exit 1
fi

files_array=$(find ./ -maxdepth 1 \( -name "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]" -o -name "*[^0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]" -o -name "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][^0-9]*" -o -name "*[^0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][^0-9]*" \) -type f)

if [ -z "${files_array}" ]; then
    exit
fi

for old in ${files_array}; do
    if ./is_not_ambiguous.sh "${old}"; then
        new=$(echo "${old}tmp" | sed "s/\(^.*\)\([^0-9]\+\)\([0-9]\{8\}\)\([^0-9]\+\)\(.*$\)/\1\2$(date +%Y%m%d)\4\5/" | sed s/tmp$//g)
        if [ "${option}" = "-f" ] || [ "${option}" = "--force" ]; then
            mv "${old}" "${new}"
        else
            mv -i "${old}" "${new}"
        fi
    fi
done
