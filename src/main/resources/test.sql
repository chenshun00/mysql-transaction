create database test;
-- auto-generated definition
create table test_transaction
(
  id    int auto_increment
    primary key,
  name  varchar(16) default ''              not null,
  email varchar(24) default ''              not null,
  birth timestamp default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP
)
  engine = InnoDB;
