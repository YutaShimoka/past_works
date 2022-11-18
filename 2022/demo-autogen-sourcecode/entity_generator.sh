#!/usr/bin/env bash
set -e -o pipefail -o errtrace
err_holder() {
	num="$1"
	echo "[ERROR] Oops, an error occurred. [L${num}]"
	printf '\a'
}
trap 'err_holder $LINENO' ERR

cd "$(dirname "$0")"

# Initial Setting

CSVQ_DIR="csvq/"

DEST_DIR=$1 # required

# Functions

snake_case_to_camelCase() {
	snake_case=$1
	echo "${snake_case}" | sed -r 's/_./\U\0/g; s/_//g'
}

replace_with_java_data_type() {
	DATA_TYPE=$1
	if [[ ${DATA_TYPE} =~ ^decimal.*$ ]]; then
		echo "BigDecimal"
	elif [ "${DATA_TYPE}" = "bigint" ]; then
		echo "Long"
	elif [ "${DATA_TYPE}" = "bit(1)" ]; then
		echo "boolean"
	elif [ "${DATA_TYPE}" = "date" ]; then
		echo "LocalDate"
	elif [ "${DATA_TYPE}" = "datetime(6)" ]; then
		echo "LocalDateTime"
	elif [[ ${DATA_TYPE} =~ ^varchar.*$ ]]; then
		echo "String"
	fi
}

print_tsv() {
	val="{print"
	for i in $(seq 1 "$1"); do
		if [ "$i" != "$1" ]; then
			val="$val \$$i \"\t\""
		else
			val="$val \$$i}"
		fi
	done
	echo "$val"
}

# Actions

echo "[INFO] *****$(basename "$0") START*****"
echo "[INFO] start time: $(date "+%Y/%m/%d %T")"
startTime=$(date +%s)

## prior confirmation

if [ ! -f ${CSVQ_DIR}csvq.exe ]; then
	echo "[ERROR] csvq does not exist. [L$LINENO]"
	exit 1
fi

if [ -z "${DEST_DIR}" ]; then
	echo "[ERROR] no argument specified. [L$LINENO]"
	exit 1
fi

if [ ! -f source/a5m2_TABLES.csv ]; then
	echo "[ERROR] file 'source/a5m2_TABLES.csv' does not exist. [L$LINENO]"
	exit 1
fi

if [ ! -f source/a5m2_COLUMNS.csv ]; then
	echo "[ERROR] file 'source/a5m2_COLUMNS.csv' does not exist. [L$LINENO]"
	exit 1
fi

## preparation

if [ ! -d processing ]; then
	mkdir processing
fi

### extract table name
awk -v FPAT='([^,]*)|(\"[^\"]+\")' '{print $3}' <source/a5m2_TABLES.csv | sed -e 's/^"//g;s/"$//g' | tail -n+2 >processing/table_name.csv

### create entities
while IFS=, read -r TABLE_NAME; do

	if [ -f processing/columns.csv ]; then
		rm processing/columns.csv
	fi

	${CSVQ_DIR}csvq.exe -o processing/columns.csv -f CSV -E UTF8 "select * from stdin where TABLE_NAME = \"${TABLE_NAME}\" order by ORDINAL_POSITION asc;" <source/a5m2_COLUMNS.csv

	### change csv to tsv
	awk -v FPAT='([^,]*)|(\"[^\"]+\")' "$(print_tsv 11)" processing/columns.csv | sed -e 's/\t\t/\t-\t/g;s/\t"/\t/g;s/"\t/\t/g' >processing/columns.tsv
	rm processing/columns.csv

	row_count=$(($(wc -l <processing/columns.tsv) - 1))

	is_header=true
	while IFS=$'\t' read -r TABLE_CATALOG TABLE_SCHEMA TABLE_NAME COLUMN_NAME LOGICAL_NAME ORDINAL_POSITION COLUMN_DEFAULT IS_NULLABLE DATA_TYPE KEY_POSITION DESCRIPTION; do
		if [ ${is_header} = true ]; then
			is_header=false
		else
			java_class_name=$(snake_case_to_camelCase "${TABLE_NAME^}")
			dest_path="${DEST_DIR}${java_class_name}".java
			DATA_TYPE=${DATA_TYPE/ auto_increment/}
			if [ "${ORDINAL_POSITION}" = 1 ]; then
				cat <<EOF >"${dest_path}"
package sample.model.entity;

import java.io.Serializable;
EOF
				if [ "$(awk -F "\t" '{print $9}' <processing/columns.tsv | grep -c decimal)" != 0 ]; then
					cat <<EOF >>"${dest_path}"
import java.math.BigDecimal;
EOF
				fi
				if [ "$(awk -F "\t" '{print $9}' <processing/columns.tsv | grep -c ^date$)" != 0 ]; then
					cat <<EOF >>"${dest_path}"
import java.time.LocalDate;
EOF
				fi
				if [ "$(awk -F "\t" '{print $9}' <processing/columns.tsv | grep -c datetime)" != 0 ]; then
					cat <<EOF >>"${dest_path}"
import java.time.LocalDateTime;
EOF
				fi
				cat <<EOF >>"${dest_path}"

import javax.persistence.Entity;
EOF
				if [ "${DATA_TYPE}" = bigint ]; then
					cat <<EOF >>"${dest_path}"
import javax.persistence.GeneratedValue;
EOF
				fi
				cat <<EOF >>"${dest_path}"
import javax.persistence.Id;
EOF
				if [ "$(awk -F "\t" '{print $8}' <processing/columns.tsv | grep -c NO)" != 0 ]; then
					cat <<EOF >>"${dest_path}"
import javax.validation.constraints.NotNull;
EOF
				fi
				if [ "$(awk -F "\t" '{print $9}' <processing/columns.tsv | grep -c varchar)" != 0 ]; then
					cat <<EOF >>"${dest_path}"
import javax.validation.constraints.Size;
EOF
				fi
				cat <<EOF >>"${dest_path}"

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class ${java_class_name} implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ${LOGICAL_NAME} */
    @Id
EOF
				if [ "${DATA_TYPE}" = bigint ]; then
					cat <<EOF >>"${dest_path}"
    @GeneratedValue
EOF
				fi
			else
				cat <<EOF >>"${dest_path}"
    /** ${LOGICAL_NAME} */
EOF
			fi
			if [ "${IS_NULLABLE}" = NO ]; then
				cat <<EOF >>"${dest_path}"
    @NotNull
EOF
			fi
			if [[ ${DATA_TYPE} =~ varchar\([0-9].*\) ]]; then
				cat <<EOF >>"${dest_path}"
    @${DATA_TYPE/varchar(/Size(max = }
EOF
			fi
			cat <<EOF >>"${dest_path}"
    private $(replace_with_java_data_type "${DATA_TYPE}") $(snake_case_to_camelCase "${COLUMN_NAME}");
EOF
			if [ "${ORDINAL_POSITION}" = ${row_count} ]; then
				cat <<EOF >>"${dest_path}"

}
EOF
			fi
		fi
	done <processing/columns.tsv
	rm processing/columns.tsv

	echo "[DEBUG] created '${java_class_name}.java'"

done <processing/table_name.csv
rm processing/table_name.csv

echo "[INFO] end time: $(date "+%Y/%m/%d %T")"
echo "[INFO] execute time: $(($(date +%s) - "${startTime}")) sec"
echo "[INFO] *****$(basename "$0") END*****"
