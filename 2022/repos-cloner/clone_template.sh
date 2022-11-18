#!/usr/bin/env bash
set -e -o pipefail -o errtrace
err_holder() {
    num="$1"
    echo "[ERROR] Oops, an error occurred. [L${num}]"
    printf '\a'
}
trap 'err_holder $LINENO' ERR

cd "$(dirname "$0")"
source_dir=$(pwd)

echo "[INFO] *****$(basename "$0") START*****"
echo "[INFO] start time: $(date "+%Y/%m/%d %T")"
start_time=$(date +%s)

# Initial Setting

base_url="https://github.com"
jq_path="/c/jsonq/jq"

# Functions

before_func() {
    user=$1
    if [ ! -d "${user}" ]; then
        mkdir "${user}"
    fi
}

clone_func() {
    user=$1
    repository_name=$2
    if [ -d "${repository_name}" ]; then
        if [ "$(ls "${repository_name}")" != "" ]; then
            echo "Updating '${user}/${repository_name}'"
            (
                cd "${repository_name}"
                git pull
            )
        fi
    else
        contains_value=false
        for exclude_repo in "${exclude_repos[@]}"; do
            if [ "${exclude_repo}" = "${user}/${repository_name}" ]; then
                contains_value=true
            fi
        done
        if ! ${contains_value}; then
            git clone "${base_url}/${user}/${repository_name}"
        fi
    fi
}

clone_func_v1() {
    user=$1
    repos_array=("$@")
    before_func "${user}"
    (
        cd "${user}"
        is_user_name=true
        for repository_name in "${repos_array[@]}"; do
            if ${is_user_name}; then
                is_user_name=false
            else
                clone_func "${user}" "${repository_name}"
            fi
        done
    )
}

clone_func_v2() {
    user=$1
    before_func "${user}"
    (
        cd "${user}"
        i=1
        while true; do
            source_path="${user}_repos_info_$(date +%Y%m%d)_${i}.json"
            curl -Ss "https://api.github.com/users/${user}/repos?page=${i}" >"${source_path}"
            repos=$(${jq_path} -r '.[].name' <"${source_path}")
            for repository_name in ${repos}; do
                repository_name=$(echo "${repository_name}" | xargs)
                clone_func "${user}" "${repository_name}"
            done
            if [ "$(${jq_path} -r ' [.[].name] | length' <"${source_path}")" -ge 30 ]; then
                i=$((i += 1))
            else
                break
            fi
        done
    )
}

bak_func() {
    file_name="$(basename "$0" .sh)"
    bak_dir="${source_dir}/bak"
    if [ ! -d "${bak_dir}" ]; then
        mkdir -p "${bak_dir}"
    fi
    files_array=$(find "${bak_dir}" -maxdepth 1 -name "${file_name}*" -type f | sort -r)
    for file in ${files_array}; do
        old="${file}"
        break
    done
    bak_file_name="${bak_dir}/${file_name}_$(date +%Y%m%d%H%M%S).sh"
    copy_bak() {
        cp -p "${source_dir}/${file_name}.sh" "${bak_file_name}"
    }
    if [ "${old}" != '' ]; then
        if [ "$(diff "${old}" "${file_name}.sh")" != '' ]; then
            copy_bak
        fi
    else
        copy_bak
    fi
}

# Actions

## preparation

if [ ! -f exclude_repos.sh ]; then
    cat <<EOF >exclude_repos.sh
#!/usr/bin/env bash

### クローン除外リポジトリ (クローン対象からの除外)
### 配列の要素には[\${user}/\${repository_name}]を指定してください。
exclude_repos=("")
EOF
fi

source ./exclude_repos.sh

dest_dir="$HOME/Documents/GitHub/others"

if [ ! -d "${dest_dir}" ]; then
    mkdir "${dest_dir}"
fi

cd "${dest_dir}"

## prior confirmation

if [ ! -f ${jq_path} ]; then
    echo "[ERROR] jq does not exist. [L$LINENO]"
    printf '\a' && exit 1
fi

## execution

user=""
repos=("")
# clone_func_v1 ${user} "${repos[@]}" # commented out by default
# clone_func_v2 ${user}               # commented out by default

### create a backup of clone.sh
# bak_func # commented out by default

echo "[INFO] end time: $(date "+%Y/%m/%d %T")"
echo "[INFO] execute time: $(($(date +%s) - "${start_time}")) sec"
echo "[INFO] *****$(basename "$0") END*****"
