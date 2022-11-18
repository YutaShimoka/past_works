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

argOne=$1

if [ ! -d finished ]; then
	mkdir finished
fi

cat <<EOF >finished/Hello.java
public class Hello {

    public static void main(String[] args) {

        System.out.println("${argOne}");
    }

}
EOF

java finished/Hello.java

echo "[INFO] *****$(basename "$0") END*****"
