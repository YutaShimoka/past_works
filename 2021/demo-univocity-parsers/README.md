# demo-univocity-parsers

This is an example of a from tsv to db implementation with using the [univocity_parsers](https://www.univocity.com/pages/univocity_parsers_tutorial.html).

# prerequisites

* Java11 or later.
* Gradle
* PostgreSQL

# DDL

```sql
create table public.on_site_info (
  employee_id character(6)
  , last_name character varying(30)
  , first_name character varying(30)
  , start_date character(8)
  , team_name character varying(255)
  , rookie_flag integer
);
```

# Tips

(References)

* https://techacademy.jp/magazine/19443
* https://qiita.com/n_slender/items/ef777ba3fe636c1ea2d6#%E8%AA%AD%E3%81%BF%E8%BE%BC%E3%81%BF
