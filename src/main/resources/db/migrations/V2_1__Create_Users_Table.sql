create table users (
  id int not null auto_increment,
  name varchar(128) not null,
  email varchar(128) not null unique,
  password varchar(512) not null,
  created_at datetime default CURRENT_TIMESTAMP,
  updated_at datetime default CURRENT_TIMESTAMP,
  primary key(id)
)engine=InnoDB, charset=utf8;