create table books (
	id int not null auto_increment,
	title varchar(256) not null,
	author varchar(256) not null,
	published_at datetime,
	isbn varchar(64),
	primary key(id)
)Engine=Innodb, charset=utf8;