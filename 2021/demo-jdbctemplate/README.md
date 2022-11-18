demo-jdbctemplate
====

## 動作確認

### サーバ起動 （ Git Bash ）

```bash
$ cd demo-jdbctemplate

$ ./gradlew bootRun
```

### API 呼び出し

```bash
$ curl -Ss http://localhost:8080/api/hoge | ./jq
## [
##   {
##     "id": "xxx",
##     "str": "asdf"
##   },
##   {
##     "id": "yyy",
##     "str": "ghjk"
##   }
## ]

$ curl -Ss http://localhost:8080/api/hoge/xxx | ./jq
## {
##   "id": "xxx",
##   "str": "asdf"
## }

$ curl -Ss http://localhost:8080/api/hoge/yyy | ./jq
## {
##   "id": "yyy",
##   "str": "ghjk"
## }

$ curl -Ss http://localhost:8080/api/hoge/zzz | ./jq
## {
##   "error_code": 404,
##   "msg": "情報が見つかりませんでした。"
## }

$ curl -Ss http://localhost:8080/api/fuga | ./jq
## {
##   "error_code": 404,
##   "msg": "このAPIは存在しません。"
## }
```
