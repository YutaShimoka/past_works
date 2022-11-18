#!/usr/bin/env bash
set -eu -o pipefail -o errtrace
err_holder() {
    num="$1"
    echo "[ERROR] Oops, an error occurred. [L${num}]"
    printf '\a'
}
trap 'err_holder $LINENO' ERR

cd "$(dirname "$0")"

echo "[INFO] *****$(basename "$0") START*****"
echo "[INFO] start time: $(date "+%Y/%m/%d %T")"
startTime=$(date +%s)

user="$1" # required

JQ_DIR="/c/jsonq"

if [ ! -f "${JQ_DIR}/jq" ]; then
    echo "'jq' does not exist"
    exit 1
fi

DEST_DIR=$HOME/Documents/Qiita

if [ ! -d "${DEST_DIR}" ]; then
    mkdir "${DEST_DIR}"
fi

cd "${DEST_DIR}"

targetUrl="https://qiita.com/api/v2/users/${user}"

httpCode="$(curl -Ss "${targetUrl}" -o /dev/null -w '%{http_code}\n')"
if [ "${httpCode}" != 200 ]; then
    echo "url=${targetUrl}, http_code=${httpCode}"
    exit 1
fi

itemsCount=$(curl -Ss "https://qiita.com/api/v2/users/${user}/items?per_page=1" | ${JQ_DIR}/jq -r ".[].user.items_count")
if [ "${itemsCount}" = '' ]; then
    echo "there is no target to import"
    exit
fi

if [ ! -d "${user}" ]; then
    mkdir "${user}"
else
    rm -r "${user}"
    mkdir "${user}"
fi

for ((i = 1; i <= $(("${itemsCount}" / 100 + 1)); i++)); do
    curl -Ss "https://qiita.com/api/v2/users/${user}/items?page=${i}&per_page=100" |
        ${JQ_DIR}/jq -r ".[].url" |
        xargs -I{} bash -c "curl -Ss  {}.md > ${user}/\$(echo {} | awk -F / '{print \$NF}').md"
done

# find target files in pattern
filesArray=$(find ./"${user}" -maxdepth 1 -name "*.md" -type f)

if [ ! -d "${user}"/data ]; then
    mkdir "${user}"/data
fi
jsonPath="${user}/data/${user}-qiita.json"

cat <<EOF >"${jsonPath}"
{
    "qiita": [
EOF

for fileName in ${filesArray}; do
    cat <<EOF >>"${jsonPath}"
        {
            "source_hash_file_name": "$(basename "${fileName}")",
            "title": "$(sed -n 2p <"${fileName}" | sed "s/title\: //g" | sed "s/\"/\\\\\"/g")",
            "tags": "$(sed -n 3p <"${fileName}" | sed "s/tags\: //g")",
            "author": "$(sed -n 4p <"${fileName}" | sed "s/author\: //g")",
            "slide": "$(sed -n 5p <"${fileName}" | sed "s/slide\: //g")"
        },
EOF
done

sed "$ s/,//g" <"${jsonPath}" >tmpfile && mv tmpfile "${jsonPath}"

cat <<EOF >>"${jsonPath}"
    ]
EOF

echo -n "}" >>"${jsonPath}"

# debug
echo "created '${jsonPath}'"

# create workspace
if [ ! -d "${user}"/processing ]; then
    mkdir "${user}"/processing
fi

csvPath="${user}/processing/file_name_mapping.csv"
${JQ_DIR}/jq -r '.qiita[] | {source_hash_file_name, title} | map(.) | @csv' <"${jsonPath}" >"${csvPath}"

while IFS=, read -r source_hash_file_name title; do
    ## remove double quotation
    sourceHashFileName=$(echo "${source_hash_file_name}" | sed s/^\"//g | sed s/\"$//g)
    title=$(echo "${title}" | sed s/^\"//g | sed s/\"$//g | sed "s/\//／/g" | sed s/\"\"/\"/g)
    ## rename
    mv -v "${user}/${sourceHashFileName}" "${user}/${title}.md"
done <"${csvPath}"

rm -r "${user}"/processing

tagsLstPath="${user}/data/tags.lst"
${JQ_DIR}/jq -r '.qiita[].tags' <"${jsonPath}" >"${tagsLstPath}"
sed "s/ /\\n/g" <"${tagsLstPath}" | LANG=C sort | uniq >tmpfile && mv tmpfile "${tagsLstPath}"
# add header
sed -i "1i# ${user}-qiita tags list" "${tagsLstPath}"

if [ ! -d "${user}"/tags ]; then
    mkdir "${user}"/tags
fi

LF=$'\n'
tags_func() {
    tagsPath="${user}/tags/$1.md"
    ${JQ_DIR}/jq -r ".qiita[] | select (.tags | split(\" \")[] == \"$1\") | (\"* [\"+ .title + \".md](../\" + (.title | gsub(\" \"; \"%20\") | gsub(\":\"; \"\") | gsub(\"/\"; \"／\") | gsub(\"#\"; \"%23\") | gsub(\"\\\"\"; \"\") | gsub(\"\\\\?\"; \"\")) + \".md)\")" <"${jsonPath}" | LANG=C sort >"${tagsPath}"
    sed -i "1i**$1**" "${tagsPath}"
    sed -i "2i$LF" "${tagsPath}"
    ## debug
    echo "created '${tagsPath}'"
}

isHeader=true
while read -r tag; do
    if [ ${isHeader} = true ]; then
        isHeader=false
    else
        tags_func "${tag}"
    fi
done <"${tagsLstPath}"

if [ ! -d .git ]; then
    git init
fi
git add .
if [ "$(git status -s)" != '' ]; then
    git commit -a -m "Commit ${user} articles from $(basename "$0")"
fi

echo "[INFO] end time: $(date "+%Y/%m/%d %T")"
echo "[INFO] execute time: $(($(date +%s) - "${startTime}")) sec"
echo "[INFO] *****$(basename "$0") END*****"
