create schema if not exists test;

create table test.links(
  id int unsigned not null AUTO_INCREMENT,
  url varchar(5000) not null,
  PRIMARY KEY (id)
 );

INSERT INTO test.links (url)
VALUES ('https://ya.ru');
