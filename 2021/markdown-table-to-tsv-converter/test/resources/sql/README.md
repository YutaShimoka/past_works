# PostgreSQL linkage

## login to postgresql

```bash
$ cd markdown-table-to-tsv-converter/test/resources/sql

$ psql -U postgres
```

## Create new Table

```postgresql
postgres=# \i ddl.sql
```

## Import TSV file data into a PostgreSQL table

```postgresql
postgres=# \i data.sql
```

## Confirmation

```postgresql
postgres=# select count(*) from product_table;
 count
-------
     3
(1 行)

postgres=# select * from product_table;
 id |  name
----+--------
  1 | test 1
  2 | test 2
  3 | test 3
(3 行)
```
