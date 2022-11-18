demo-nativequery-mysql
====

## MySQLのバージョン

* MySQL 8.0.27

## データベース（スキーマ）[^1] 名

* db_example

## 準備

**データベース（スキーマ）を作成する**

<details><summary>Click to expand</summary><br>

```sql
mysql> create database db_example;
```

</details>

**application-dev.propertiesの設定**

<details><summary>Click to expand</summary><br>

`application.properties` のパスワード設定は `******` としているので `application-dev.properties` を作成し、各環境に合わせて、以下のように設定し、上書きする。（パスワード以外の設定も上書き可）

なお `application-dev.properties` は `application.properties` と同じ `src/main/resources` 配下に作成する。

_application.properties_

[code](src/main/resources/application.properties)

_application-dev.properties_

```
spring.datasource.password=drowssap
```

※上記は、パスワードが `drowssap` の場合の例。

</details>

## 動作確認

### サーバ起動 （ Git Bash ）

```bash
$ cd demo-nativequery-mysql

$ ./gradlew bootRun
```

### ヘルスチェック

```bash
$ curl http://localhost:8080/management/health
## {"status":"UP"}
```

## Contents

* [function_json-search](doc/function_json-search.md)

---

[^1]: [MySQLの基本はデータベースオブジェクトの生成と破棄、『MySQL徹底入門 第4版』から紹介：CodeZine（コードジン）](https://codezine.jp/article/detail/12519)
