# training_criteria-builder

# prerequisites

* Java11 or later.
* Spring boot + Lombok + Maven

## テストデータ

<details><summary>Click to expand</summary><br>

サーバ起動 ( Eclipse ) 後、`http://localhost:8080/h2-console` へアクセスし、H2 データベースにログインする。

**Generic H2 (Embedded)の接続情報**

|#|Item|Value|Note|
|---|---|---|---|
|1|Driver Class|org.h2.Driver|-|
|2|JDBC URL|jdbc:h2:mem:testdb|-|
|3|User Name|sa|-|
|4|Password|-|パスワード設定なし|

H2ログイン後、以下のSQLを実行する。

```sql
insert into 貸出表 (貸出番号,ISBNコード,従業員番号,貸出日,返却予定日,返却日) values (1,'ISBN978-4-905318-63-7','1001','2015-12-16','2016-01-16','2016-01-09');
insert into 貸出表 (貸出番号,ISBNコード,従業員番号,貸出日,返却予定日,返却日) values (2,'ISBN978-4-905318-63-7','1004','2016-07-18','2016-08-18','2016-07-20');
insert into 貸出表 (貸出番号,ISBNコード,従業員番号,貸出日,返却予定日,返却日) values (3,'ISBN978-4-905318-63-7','2001','2018-06-11','2018-07-11','2018-06-30');
insert into 貸出表 (貸出番号,ISBNコード,従業員番号,貸出日,返却予定日,返却日) values (4,'ISBN978-4-905318-63-7','1004','2019-04-08','2018-05-08',null);
```

</details>

# Service

* 貸出表

## 貸出表

| API一覧 |
|:-----------|

| # | API機能No. | API名 | 処理 |
|:----------:|:-----------|:-----------|:-----------|
| 1 | libcard-001 | 貸出し状態の取得 | 書籍の貸出し状態を取得する |

## 貸出し状態の取得API

| API機能No. | libcard-001 |
|:-----------|:------------|
| API名 | 貸出し状態の取得 |
| 更新日/更新者 | 2021.04.19 5h1m0kayu02 |
| HTTPメソッド | GET |
| URL | /api/libcard/bookStatusType?isbnCode=ISBN978-4-905318-63-7 |
| 処理 | 書籍の貸出し状態を取得する |
| 実行引数 | [isbnCode: ISBN978-4-905318-63-7] |

### 返却データ (JSON形式)

| JSON Key | 型 | 必須 | 値の説明 |
|:-----------|:----------:|:----------:|:-----------|
| status_type | 文字列 | ○ | 書籍の貸出し状態 |

> e.g. {"status_type":"貸出中"}

# Tips

(References)

* [［API］ API仕様書の書き方 - Qiita](https://qiita.com/sunstripe2011/items/9230396febfab2eae2c2)
* [基本情報技術者過去問題 令和元年秋期 午後問3(データベース)｜基本情報技術者試験.com](https://www.fe-siken.com/kakomon/01_aki/pm03.html)
* [図書カードって英語でなんて言うの？ - DMM英会話なんてuKnow?](https://eikaiwa.dmm.com/uknow/questions/79143/)
* [図書館の貸出日と返却日って英語でなんて言うの？ - DMM英会話なんてuKnow?](https://eikaiwa.dmm.com/uknow/questions/39622/)
* [Spring BootでH2 データベースを設定、利用する｜H2 DataBase - 技術ドットコム](https://www.lifestyle12345.com/2019/04/h2-database-spring-boot.html)
* [JavaEE使い方メモ（JPA その4 - クライテリアAPI） - Qiita](https://qiita.com/opengl-8080/items/6e3e03e4c90cd3d5e211)
