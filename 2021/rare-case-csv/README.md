rare case csv
====

「24行目がヘッダー、25行目以降がデータ」というレアケースを想定したCSV読み込み/書き込み(I/O)のサンプルコード。主な仕様は以下の通りである。

* 複数ファイルに分割されているので、ひとつのファイルにマージする。
* 結果ファイルの最後の列に、取得元ファイル名 `source_file_name` を出力する。

> テストデータ：[test/resources/](test/resources/)

## 使い方

* Python版

```bash
$ pip install chardet # only the first time
$ ./sample.py test\\resources
```

* ShellScript版

```bash
$ ./sample.sh ./test/resources
```

## 出力ファイル確認

```bash
$ ls -1 -tr result
## output_20211201204927.csv

$ cat result/output_20211201204927.csv
## id,name,source_file_name
## 1,test 1,test_data.csv
## 2,test 2,test_data.csv
## 3,test 3,test_data.csv
## 4,test 4,test_data1.csv
## 5,test 5,test_data1.csv
## 6,test 6,test_data1.csv
## 7,test 7,test_data2.csv
## 8,test 8,test_data2.csv
## 9,test 9,test_data2.csv
```
