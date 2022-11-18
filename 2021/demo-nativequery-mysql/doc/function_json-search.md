# demo-nativequery-mysql

function_json-search
---

## 書式

**SQL**

```sql
mysql> set source_id='xxx';
mysql> select * from hoge where json_search(items, 'one', @source_id) is not null\G
```

**Spring Data JPA**

```java
@Query(value = "select * from hoge where json_search(items, 'one', ?1) is not null;", nativeQuery = true)
List<Hoge> findBySourceId(String sourceId);
```

## 準備

**DDL**

<details><summary>Click to expand</summary><br>

```sql
create table hoge (
    id int,
    items json,
    primary key(id)
);
```

</details>

**テストデータ**

<details><summary>Click to expand</summary><br>

```sql
insert into hoge values (1, '[{"str": "asdf", "source_id": "xxx"}]');
insert into hoge values (2, '[{"str": "asdf", "source_id": "xxx"}, {"str": "ghjk", "source_id": "yyy"}]');
```

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

### API 呼び出し

**事前確認**

```bash
$ curl -Ss http://localhost:8080/management/mappings | ./jq '.contexts.application.mappings.dispatcherServlets.dispatcherServlet[] | select(.details.requestMappingConditions.patterns[]? == "/api/hoge")'
## {
##   "handler": "demo.controller.HogeController#getHogesBySourceId(String)",
##   "predicate": "{GET [/api/hoge]}",
##   "details": {
##     "handlerMethod": {
##       "className": "demo.controller.HogeController",
##       "name": "getHogesBySourceId",
##       "descriptor": "(Ljava/lang/String;)Ljava/util/List;"
##     },
##     "requestMappingConditions": {
##       "consumes": [],
##       "headers": [],
##       "methods": [
##         "GET"
##       ],
##       "params": [],
##       "patterns": [
##         "/api/hoge"
##       ],
##       "produces": []
##     }
##   }
## }
```

**APIを叩く**

```bash
$ curl -Ss http://localhost:8080/api/hoge?source_id=xxx | ./jq
## [
##   {
##     "id": 1,
##     "items": [
##       {
##         "str": "asdf",
##         "source_id": "xxx"
##       }
##     ]
##   },
##   {
##     "id": 2,
##     "items": [
##       {
##         "str": "asdf",
##         "source_id": "xxx"
##       },
##       {
##         "str": "ghjk",
##         "source_id": "yyy"
##       }
##     ]
##   }
## ]

$ curl -Ss http://localhost:8080/api/hoge?source_id=yyy | ./jq
## [
##   {
##     "id": 2,
##     "items": [
##       {
##         "str": "asdf",
##         "source_id": "xxx"
##       },
##       {
##         "str": "ghjk",
##         "source_id": "yyy"
##       }
##     ]
##   }
## ]

$ curl -Ss http://localhost:8080/api/hoge?source_id=zzz | ./jq
## []
```

## Bookmarks

* [MySQL :: MySQL 8.0 Reference Manual :: 12.18.3 Functions That Search JSON Values](https://dev.mysql.com/doc/refman/8.0/en/json-search-functions.html)
