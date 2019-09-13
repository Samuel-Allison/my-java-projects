DROP TABLE IF EXISTS Book CASCADE;
DROP TABLE IF EXISTS Author CASCADE;
DROP TABLE IF EXISTS BookAuthor CASCADE;

CREATE TABLE Book(
	Title text NOT NULL,
    Edition int(2) NOT NULL,
    Publisher text NOT NULL,
    ISBN Bigint primary Key
);
CREATE TABLE Author(
    ID Numeric primary key,
    Name text NOT NULL
    );
CREATE TABLE BookAuthor(
    AuthorID BigInt NOT NULL,
    BookISBN BigInt NOT NULL,
    CONSTRAINT BookAuthor_PK PRIMARY KEY (AuthorID,BookISBN),
    CONSTRAINT BookAuthor_Fk1 FOREIGN KEY (AuthorID) REFERENCES Author(ID),
    CONSTRAINT BookAuthor_Fk2 FOREIGN KEY (BookISBN) REFERENCES Book(ISBN)
    );
    
INSERT INTO Book(Title, Edition, Publisher, ISBN) VALUES
	('Object-Oriented Design and Patterns', 2, 'Wiley', 9780471744870),
    ('Intro to Java Programming, Comprehensive Version', 10, 'Pearson', 9780133761313),
    ('Advanced Engineering Mathematics', 10, 'Wiley', 9780470458365),
    ('Computer Architecture', 5, 'Elsevier Science', 978012383728)
    ;
INSERT INTO Author(ID , Name) 	VALUES
	(1, 'Cary S. Horstmann'),
    (2, 'Y. Daniel Liang'),
    (3, 'Erwin O. Kreyszig'),
    (4, 'John L. Hennessy'),
    (5, 'David A. Patterson')
    ;
INSERT INTO BookAuthor(AuthorID, BookISBN) VALUES
	(1, 9780471744870),
    (2, 9780133761313),
    (3, 9780470458365),
    (4, 9780123838728),
    (5, 9780123838728)
    ;
