# Hello World

## SQL

```sql
select 'こんにちは世界';
```

## How to use

```bash
$ cd hello-world/sql

$ sh tools/checker/is_sjis.sh hello.sql
## [debug] isSjis=true

$ psql -U postgres -t -f hello.sql
## ユーザ postgres のパスワード:
##  こんにちは世界


## RESET
```

## References

* [Linux/UNIXでファイルの文字コード(UTF-8 or Shift_JIS or EUC-JP…)を確認する | 俺的備忘録 〜なんかいろいろ〜](https://orebibou.com/ja/home/201606/20160615_001/)
