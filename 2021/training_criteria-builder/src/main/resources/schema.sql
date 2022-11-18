drop table if exists 貸出表;
create table 貸出表 (
  貸出番号 bigint(20) not null auto_increment
  , ISBNコード character varying
  , 従業員番号 character varying
  , 貸出日 datetime(6)
  , 返却予定日 datetime(6)
  , 返却日 datetime(6)
  , primary key (貸出番号)
);
