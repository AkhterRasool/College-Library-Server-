INSERT INTO author VALUES (1, 'Andrew Tanenbaum'), (2,'Henry Williams'), (3,'Pearson');
INSERT INTO book VALUES (1, 'Computer Architecture'), (2, 'Embedded Systems'), (3, 'Operating Systems'), (4, 'Database Design');
INSERT INTO book_author_mapping VALUES (2, 1), (2, 3), (3, 1), (4, 1);
INSERT INTO book_book_location VALUES (1, 1), (2, 2), (3, 3), (4, 4), (1, 1);
INSERT INTO book_location VALUES (1, 7, 5, 1), (2, 8, 2, 1), (3, 27, 4, 3), (4, 18, 12, 19);

INSERT INTO student VALUES (1,'160413733001'),(2,'160413733002');
INSERT INTO student_books_issued VALUES (1, 1),(1, 2),(2, 3);
INSERT INTO fine VALUES (1, 23.45),(2, 123.45);