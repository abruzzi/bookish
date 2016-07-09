CREATE TABLE `books` (
	`book_id` int NOT NULL,
	`user_id` int NOT NULL,
	PRIMARY KEY (`book_id`, `user_id`),
	INDEX `FK_USER` (`user_id`),
	CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
	CONSTRAINT `FK_BOOK` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`)
)engine=Innodb, charset=utf8;