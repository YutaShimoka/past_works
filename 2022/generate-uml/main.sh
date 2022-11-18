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

target_package=$1

if [ -z "${target_package}" ]; then
	echo "no argument specified"
	exit 1
fi

if [ ! -d plantuml-dependency-cli-1.4.0 ]; then
	if [ ! -f ~/Downloads/plantuml-dependency-cli-1.4.0-archive-with-bundled-dependencies.tar.gz ]; then
		echo "> download: https://sourceforge.net/projects/plantuml-depend/files/1.4.0/plantuml-dependency-cli-1.4.0-archive-with-bundled-dependencies.tar.gz"
		exit 1
	fi
	cp -p ~/Downloads/plantuml-dependency-cli-1.4.0-archive-with-bundled-dependencies.tar.gz .
	tar -zxvf plantuml-dependency-cli-1.4.0-archive-with-bundled-dependencies.tar.gz
	rm plantuml-dependency-cli-1.4.0-archive-with-bundled-dependencies.tar.gz
fi

java -jar plantuml-dependency-cli-1.4.0/plantuml-dependency-cli-1.4.0-jar-with-dependencies.jar \
	--basedir "${target_package}" \
	-o output.pu

echo "[INFO] end time: $(date "+%Y/%m/%d %T")"
echo "[INFO] execute time: $(($(date +%s) - "${start_time}")) sec"
echo "[INFO] *****$(basename "$0") END*****"
