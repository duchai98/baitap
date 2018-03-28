create database book

use book

create table book (
	book_id int IDENTITY(1,1) not null primary key,
	title varchar(128) unique,
	author varchar(45),
	price float
)