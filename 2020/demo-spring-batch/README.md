# demo-spring-batch

The NTA Corporate Number Data Import
---

This is an example of a from csv to db implementation without using the [univocity_parsers](https://www.univocity.com/pages/univocity_parsers_tutorial.html).

# prerequisites

* Java11 or later.
* Spring boot + Spring Batch + Lombok + Gradle
* PostgreSQL

# File Interface

|#|Item|Value|Note|
|---|---|---|---|
|1|File Name|test_data.csv.gz|-|
|2|File Path|C:\local\hos|-|
|3|Format|csv|-|
|4|Separeated By|comma|-|
|5|Quotation|" (double quotation)|escape sequence|
|6|Encoding|UTF-8|-|
|7|New Line Code|LF|-|
|8|Header Row|ON|-|

details: https://www.houjin-bangou.nta.go.jp/documents/k-resource-dl.pdf

# create test data

1. Download the CSV file of the [corporate number](https://www.houjin-bangou.nta.go.jp/download/zenken/#csv-unicode) (Tokyo, Unicode) data from the National Tax Agency (NTA) website.
1. The downloaded file is a ZIP file, so unzip it.
1. Do the 10,000 items extract from the file in the next step.

<details><summary>Click to expand</summary><br>

```bash
$ cd "C:\Users\5h1m0kayu02\Downloads\13_tokyo_all_20201228"

$ ls -1
## 13_tokyo_all_20201228.csv
## 13_tokyo_all_20201228.csv.asc

$ FILE_NAME=13_tokyo_all_20201228.csv

$ echo ${FILE_NAME}
## 13_tokyo_all_20201228.csv

$ wc -l ${FILE_NAME}
## 1112056 13_tokyo_all_20201228.csv

$ cat ${FILE_NAME} | sed -n 1,5p
## 1,1000011000005,01,1,2018-04-02,2015-10-05,"国立国会図書館",,101,"東京都","千代田区","永田町１丁目１０－１",,13,101,1000014,,,,,,,2015-10-05,1,"National Diet Library","Tokyo","1-10-1,Nagatacho, Chiyoda ku",,"コクリツコッカイトショカン",0
## 2,1000012010003,01,1,2018-04-02,2015-10-05,"内閣法制局",,101,"東京都","千代田区","霞が関３丁目１－１中央合同庁舎第４号館",,13,101,1000013,,,,,,,2015-10-05,1,"Cabinet Legislation Bureau","Tokyo","3-1-1,Kasumigaseki, Chiyoda ku",,"ナイカクホウセイキョク",0
## 3,1000012010011,01,1,2018-04-02,2015-10-05,"郵政民営化推進本部",,101,"東京都","千代田区","永田町１丁目１１－３９",,13,101,1000014,,,,,,,2015-10-05,1,"Headquarters for the Promotion of Privatization of the Postal Services","Tokyo","1-11-39 Nagatacho, Chiyoda ku",,"ユウセイミンエイカスイシンホンブ",0
## 4,1000012010028,01,1,2018-04-02,2015-10-05,"国土強靱化推進本部",,101,"東京都","千代田区","永田町１丁目６－１",,13,101,1000014,,,,,,,2015-10-05,1,"the National Resilience Promotion Headquarters","Tokyo","1-6-1, Nagata cho, Chiyoda ku",,"コクドキョウジンカスイシンホンブ",0
## 5,1000012010036,01,1,2018-04-02,2017-12-06,"特定複合観光施設区域整備推進本部",,101,"東京都","千代田区","霞が関３丁目２－５霞が関ビルディング１２階",,13,101,1000013,,,,,,,2017-12-06,1,"Headquarters for Promoting Development of Specified Complex Tourist Facilities Areas","Tokyo","12th Fl.3-2-5 kasumigaseki, Chiyoda ku",,"トクテイフクゴウカンコウシセツクイキセイビスイシンホンブ",0

$ cat ${FILE_NAME} | grep ヘルメスシステムズ
## 420920,4010801014350,01,1,2018-04-17,2015-10-05,"株式会社ヘルメスシステムズ",,301,"東京都","港区","海岸１丁目１６番１号ニューピア竹芝サウスタワー６階",,13,103,1050022,,,,,,,2015-10-05,1,,,,,"ヘルメスシステムズ",0

$ cat ${FILE_NAME} | sed -n 420920p
## 420920,4010801014350,01,1,2018-04-17,2015-10-05,"株式会社ヘルメスシステムズ",,301,"東京都","港区","海岸１丁目１６番１号ニューピア竹芝サウスタワー６階",,13,103,1050022,,,,,,,2015-10-05,1,,,,,"ヘルメスシステムズ",0

$ sed '10000,$d' 13_tokyo_all_20201228.csv > tmp.csv

$ wc -l tmp.csv
## 9999 tmp.csv

$ sed -i 1i`cat ${FILE_NAME} | sed -n 420920p` tmp.csv

$ wc -l tmp.csv
## 10000 tmp.csv

$ cat tmp.csv | sed -n 1,2p
## 420920,4010801014350,01,1,2018-04-17,2015-10-05,"株式会社ヘルメスシステムズ",,301,"東京都","港区","海岸１丁目１６番１号ニューピア竹芝サウスタワー６階",,13,103,1050022,,,,,,,2015-10-05,1,,,,,"ヘルメスシステムズ",0
## 1,1000011000005,01,1,2018-04-02,2015-10-05,"国立国会図書館",,101,"東京都","千代田区","永田町１丁目１０－１",,13,101,1000014,,,,,,,2015-10-05,1,"National Diet Library","Tokyo","1-10-1,Nagatacho, Chiyoda ku",,"コクリツコッカイトショカン",0

$ mkdir -p "C:\local\hos"

$ cat tmp.csv | gzip > "C:\local\hos\test_data.csv.gz"

$ zcat "C:\local\hos\test_data.csv.gz" | wc -l
## 10000

$ zcat "C:\local\hos\test_data.csv.gz" | sed -n 1,2p
## 420920,4010801014350,01,1,2018-04-17,2015-10-05,"株式会社ヘルメスシステムズ",,301,"東京都","港区","海岸１丁目１６番１号ニューピア竹芝サウスタワー６階",,13,103,1050022,,,,,,,2015-10-05,1,,,,,"ヘルメスシステムズ",0
## 1,1000011000005,01,1,2018-04-02,2015-10-05,"国立国会図書館",,101,"東京都","千代田区","永田町１丁目１０－１",,13,101,1000014,,,,,,,2015-10-05,1,"National Diet Library","Tokyo","1-10-1,Nagatacho, Chiyoda ku",,"コクリツコッカイトショカン",0

$ ls -1
## 13_tokyo_all_20201228.csv
## 13_tokyo_all_20201228.csv.asc
## tmp.csv

$ rm -iv tmp.csv
## rm: remove regular file 'tmp.csv'? y
## removed 'tmp.csv'

$ ls -1
## 13_tokyo_all_20201228.csv
## 13_tokyo_all_20201228.csv.asc

$ exit
## logout
```

</details>

the short version is this:

```bash
$ cd "C:\Users\5h1m0kayu02\Downloads\13_tokyo_all_20201228"
$ FILE_NAME=13_tokyo_all_20201228.csv
$ sed '10000,$d' ${FILE_NAME} > tmp.csv | sed -i 1i`cat ${FILE_NAME} | sed -n 420920p` tmp.csv
$ mkdir -p "C:\local\hos"
$ cat tmp.csv | gzip > "C:\local\hos\test_data.csv.gz"
$ rm tmp.csv
$ exit
## logout
```

# DDL

```sql
create table public.company (
  corporate_number bigint not null
  , name character varying(150) default ''
  , address character varying(330) default ''
  , prefecture_code integer
  , city_code integer
  , post_code integer
  , created_date timestamp(6) without time zone
  , last_modified_date timestamp(6) without time zone
  , primary key (corporate_number)
);
```

# Tips

(References)

* https://jsug.doorkeeper.jp/events/106493
* https://youtu.be/t1TxvEutXbs
* https://www.slideshare.net/HideyukiSASAKURA/batch-spring-batch
* https://www.slideshare.net/ikeyat/spring-batch-tips-233698138/ikeyat/spring-batch-tips-233698138
