#!/usr/bin/env bash
set -e -o pipefail -o errtrace
err_holder() {
	num="$1"
	echo "[ERROR] Oops, an error occurred. [L${num}]"
	printf '\a'
}
trap 'err_holder $LINENO' ERR

cd "$(dirname "$0")"

echo "[INFO] *****$(basename "$0") START*****"
echo "[INFO] start time: $(date "+%Y/%m/%d %T")"
start_time=$(date +%s)

# Initial Setting

base_url="https://github.com"
jq_path="/c/jsonq/jq"

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

user=$1 # required

if [ -z "${user}" ]; then
	echo "[ERROR] no argument specified. [L$LINENO]"
	printf '\a' && exit 1
fi

if [ ! -d "${user}" ]; then
	echo "[INFO] directory '${user}' does not exist. nothing to do. [L$LINENO]"
	exit
fi

if [ ! -f ${jq_path} ]; then
	echo "[ERROR] jq does not exist. [L$LINENO]"
	printf '\a' && exit 1
fi

## main
(
	cd "${user}"

	if [ -d processing ]; then
		rm -r processing
	fi
	mkdir -p processing/{old,new}

	old="processing/old/${user}_repos_name.lst"
	new="processing/new/${user}_repos_name.lst"
	diff="${user}_extracted_diff_$(date +%Y%m%d).txt"

	ls -1 >"${old}"

	i=1
	while true; do
		source_path="${user}_repos_info_$(date +%Y%m%d)_${i}.json"
		curl -Ss "https://api.github.com/users/${user}/repos?page=${i}" >"${source_path}"
		${jq_path} -r '.[].name' <"${source_path}" >>"${new}"
		if [ "$(${jq_path} -r '[.[].name] | length' <"${source_path}")" -ge 30 ]; then
			i=$((i += 1))
		else
			break
		fi
	done

	LC_ALL=C sort "${old}" | sed s/tmp/tmp/g >tmpfile && mv tmpfile "${old}"
	LC_ALL=C sort "${new}" | sed s/tmp/tmp/g >tmpfile && mv tmpfile "${new}"

	diff --old-line-format='' --unchanged-line-format='' --new-line-format='%L' "${old}" "${new}" >"${diff}" || if [ $? = 1 ]; then true; fi

	rm -r processing

	while read -r repository_name; do
		contains_value=false
		for exclude_repo in "${exclude_repos[@]}"; do
			if [ "${exclude_repo}" = "${user}/${repository_name}" ]; then
				contains_value=true
			fi
		done
		if ! ${contains_value}; then
			git clone "${base_url}/${user}/${repository_name}"
		fi
	done <"${diff}"
)

echo "[INFO] end time: $(date "+%Y/%m/%d %T")"
echo "[INFO] execute time: $(($(date +%s) - "${start_time}")) sec"
echo "[INFO] *****$(basename "$0") END*****"
