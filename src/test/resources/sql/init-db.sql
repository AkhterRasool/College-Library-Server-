DROP TABLE IF EXISTS author;
CREATE TABLE  author (
id int(10) NOT NULL AUTO_INCREMENT,
name varchar(20)DEFAULT NULL,
PRIMARY KEY(id),
UNIQUE KEY name(name)
);

DROP TABLE IF EXISTS book;
CREATE TABLE  book (
id int(11) NOT NULL AUTO_INCREMENT,
title varchar(45)NOT NULL,
PRIMARY KEY(id),
UNIQUE KEY title(title),
);

DROP TABLE IF EXISTS book_author_mapping;
CREATE TABLE  book_author_mapping (
book_id int(10) NOT NULL,
author_id int(10)NOT NULL,
UNIQUE KEY book_author_key(book_id, author_id),
CONSTRAINT book_author_mapping_ibfk_1 FOREIGN KEY(book_id)REFERENCES book(id) ON DELETE CASCADE,
CONSTRAINT book_author_mapping_ibfk_2 FOREIGN KEY(author_id)REFERENCES author(id) ON DELETE CASCADE
);

drop table if exists book_location;
CREATE TABLE  book_location (
id int(11) NOT NULL AUTO_INCREMENT,
rack int(11)NOT NULL,
row_num int(11)NOT NULL,
col int(11)NOT NULL,
PRIMARY KEY(id),
UNIQUE KEY id_UNIQUE(id),
UNIQUE KEY location_key(rack, row_num, col)
);

drop table if exists book_book_location;
CREATE TABLE  book_book_location (
book_id int(11) NOT NULL,
location_id int(11)NOT NULL,
UNIQUE KEY book_location_key(book_id, location_id),
CONSTRAINT book_id FOREIGN KEY(book_id)REFERENCES book(id) ON DELETE CASCADE,
CONSTRAINT location_id FOREIGN KEY(location_id)REFERENCES book_location(id) ON DELETE CASCADE
);

drop table if exists student;
CREATE TABLE  student (
id int(11) NOT NULL,
rollnumber varchar(15)DEFAULT NULL,
PRIMARY KEY(id)
);

drop table if exists fine;
CREATE TABLE  fine (
student_id int(11) NOT NULL,
amount double DEFAULT NULL,
PRIMARY KEY(student_id),
CONSTRAINT student_id FOREIGN KEY(student_id)REFERENCES student(id) ON DELETE CASCADE
);

drop table if exists  student_books_issued;
CREATE TABLE  student_books_issued (
student_id int(11) NOT NULL,
book_id int(11)NOT NULL,
PRIMARY KEY(student_id),
UNIQUE KEY student_book_key(student_id, book_id),
CONSTRAINT student_books_issued_ibfk_1 FOREIGN KEY(student_id)REFERENCES student(id) ON DELETE CASCADE,
CONSTRAINT student_books_issued_ibfk_2 FOREIGN KEY(book_id)REFERENCES book(id) ON DELETE CASCADE
);

