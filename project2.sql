DROP SCHEMA IF EXISTS private CASCADE;
DROP TABLE IF EXISTS SeatRow CASCADE;
DROP TABLE IF EXISTS SeatNum CASCADE;
DROP TABLE IF EXISTS Seat CASCADE;
DROP TABLE IF EXISTS Ticket CASCADE;
DROP TABLE IF EXISTS Customer CASCADE;

CREATE SCHEMA private;

CREATE TABLE SeatRow(
    row char(2) primary key,
    check (row in ('A','B','C','D','E','F','G','H','J','K','L','M','N','O','P','Q','R','AA','BB'
                   ,'CC','DD','EE','FF','GG','HH'))
    );
Create Table SeatNum(
    num int primary key,
    constraint check_num
    check (num between 1 and 15 or num between 101 and 126)
    );
    
CREATE TABLE Seat(
    SeatRow char(2),
    SeatNum int,
    Section text constraint check_section 
    check  (Section in ('Balcony' , 'Main Floor')) not null,
    Side text constraint check_side
    check (Side in ('Right','Middle','Left')) not null,
    PricingTier text constraint check_PricingTier
    check (PricingTier in('Upper Balcony','Side','Orchestra')),
    Wheelchair boolean not null,
    constraint seat_row foreign key (SeatRow) references SeatRow(row),
    constraint seat_num foreign key (SeatNum) references SeatNum(num),
    constraint seat_pk primary key (SeatRow, SeatNum)
    );
    
CREATE TABLE Customer(
    CustomerID int primary key,
    FirstName text,
    LastName text
    );
    
CREATE TABLE private.Customer(
    CustomerID int primary key,
    CreditCard bigint,
    constraint custmer_ID foreign key (CustomerID) references Customer(CustomerID)
    );
CREATE TABLE Ticket(
    TicketNumber serial primary key,
    CustomerID int,
    SeatRow char(2),
    SeatNumber int,
    ShowTime timestamp,
    foreign key (SeatRow, SeatNumber) references Seat(Seatrow,SeatNum),
    constraint seat_unique unique (SeatRow, SeatNumber,ShowTime),
    foreign key (CustomerID) references Customer(CustomerID)
    );
       
INSERT INTO SeatRow (row) values 
    ('A'),('B'),('C'),('D'),('E'),('F'),('G'),('H'),('J'),('K'),
    ('L'),('M'),('N'),('O'),('P'),('Q'),('R'),
    ('AA'),('BB'),('CC'),('DD'),('EE'),('FF'),('GG'),('HH')
	;
    
INSERT INTO SeatNum(num) select generate_series(1,15);
INSERT INTO SeatNum(num) select generate_series(101,126);
-- main floor middle
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Main Floor', 'Middle','Orchestra',False
from SeatRow, SeatNum
where SeatNum.num >=1
and SeatNum.num <=10
and SeatRow.row in ('C','B','A')
;

INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Main Floor', 'Middle','Orchestra',False
from SeatRow, SeatNum
where SeatNum.num >=1
and SeatNum.num <=11
and SeatRow.row in ('D','E','F')
;
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Main Floor', 'Middle','Orchestra',False
from SeatRow, SeatNum
where SeatNum.num >=1
and SeatNum.num <=12
and SeatRow.row in ('G','H','J')
;
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Main Floor', 'Middle','Orchestra',False
from SeatRow, SeatNum
where SeatNum.num >=1
and SeatNum.num <=13
and SeatRow.row in ('K','L','M')
;
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Main Floor', 'Middle','Orchestra',False
from SeatRow, SeatNum
where SeatNum.num >=1
and SeatNum.num <=14
and SeatRow.row in ('N','O','P')
;
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Main Floor', 'Middle','Orchestra',False
from SeatRow, SeatNum
where SeatNum.num >=1
and SeatNum.num <=15
and SeatRow.row in ('Q','R')
;

-- balcony middle
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Balcony','Middle', 'Orchestra',False
from SeatRow, SeatNum
where SeatNum.num >=1
and SeatNum.num <=13
and SeatRow.row in ('AA')
;
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Balcony','Middle', 'Orchestra',False
from SeatRow, SeatNum
where SeatNum.num >=1
and SeatNum.num <=14
and SeatRow.row in ('BB','CC','DD')
;
-- balcony left side
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Balcony','Left', 'Side',False
from SeatRow, SeatNum
where SeatNum.num >=101
and SeatNum.num <=123
and (SeatNum.num%2 != 0)
and SeatRow.row in ('AA','BB','CC')
;
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Balcony','Left', 'Side',False
from SeatRow, SeatNum
where SeatNum.num >=101
and SeatNum.num <=125
and (SeatNum.num%2 != 0)
and SeatRow.row in ('DD')
;
-- left side upper balcony
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Balcony','Left', 'Upper Balcony',False
from SeatRow, SeatNum
where SeatNum.num >=101
and SeatNum.num <=121
and (SeatNum.num%2 != 0)
and SeatRow.row in ('EE','FF')
;
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Balcony','Left', 'Upper Balcony',False
from SeatRow, SeatNum
where SeatNum.num >=101
and SeatNum.num <=119
and (SeatNum.num%2 != 0)
and SeatRow.row in ('GG')
;
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Balcony','Left', 'Upper Balcony',False
from SeatRow, SeatNum
where SeatNum.num >=101
and SeatNum.num <=117
and (SeatNum.num%2 != 0)
and SeatRow.row in ('HH')
;
-- balcony upper middle

INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Balcony', 'Middle', 'Upper Balcony',False
from SeatRow, SeatNum
where SeatNum.num >=1
and SeatNum.num <=10
and SeatRow.row in ('EE','FF')
;
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Balcony', 'Middle', 'Upper Balcony',False
from SeatRow, SeatNum
where SeatNum.num >=1
and SeatNum.num <=11
and SeatRow.row in ('GG','HH')
;
-- balcony upper right
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Balcony','Right', 'Upper Balcony',False
from SeatRow, SeatNum
where SeatNum.num >=102
and SeatNum.num <=122
and (SeatNum.num%2 = 0)
and SeatRow.row in ('EE','FF')
;
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Balcony','Right', 'Upper Balcony',False
from SeatRow, SeatNum
where SeatNum.num >=102
and SeatNum.num <=120
and (SeatNum.num%2 = 0)
and SeatRow.row in ('GG')
;
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Balcony','Right', 'Upper Balcony',False
from SeatRow, SeatNum
where SeatNum.num >=102
and SeatNum.num <=118
and (SeatNum.num%2 = 0)
and SeatRow.row in ('HH')
;
-- right side balcony
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Balcony','Right', 'Side',False
from SeatRow, SeatNum
where SeatNum.num >=102
and SeatNum.num <=124
and (SeatNum.num%2 = 0)
and SeatRow.row in ('AA','BB','CC')
;
INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)
select SeatRow.row, SeatNum.num, 'Balcony','Right', 'Side',False
from SeatRow, SeatNum
where SeatNum.num >=102
and SeatNum.num <=126
and (SeatNum.num%2 = 0)
and SeatRow.row in ('DD')
;

INSERT INTO Seat (SeatRow,SeatNum, Section, Side, PricingTier,Wheelchair)values 
('A',101,'Main Floor','Left','Orchestra', FALSE),
('A',103,'Main Floor','Left','Orchestra', FALSE),
('A',105,'Main Floor','Left','Orchestra', FALSE),
('A',107,'Main Floor','Left','Side', FALSE),
('A',109,'Main Floor','Left','Side', FALSE),
('A',111,'Main Floor','Left','Side', FALSE),
('A',113,'Main Floor','Left','Side', FALSE),

('B',101,'Main Floor','Left','Orchestra', FALSE),
('B',103,'Main Floor','Left','Orchestra', FALSE),
('B',105,'Main Floor','Left','Orchestra', FALSE),
('B',107,'Main Floor','Left','Side', FALSE),
('B',109,'Main Floor','Left','Side', FALSE),
('B',111,'Main Floor','Left','Side', FALSE),
('B',113,'Main Floor','Left','Side', FALSE),
('B',115,'Main Floor','Left','Side', FALSE),

('C',101,'Main Floor','Left','Orchestra', FALSE),
('C',103,'Main Floor','Left','Orchestra', FALSE),
('C',105,'Main Floor','Left','Orchestra', FALSE),
('C',107,'Main Floor','Left','Side', FALSE),
('C',109,'Main Floor','Left','Side', FALSE),
('C',111,'Main Floor','Left','Side', FALSE),
('C',113,'Main Floor','Left','Side', FALSE),
('C',115,'Main Floor','Left','Side', FALSE),

('D',101,'Main Floor','Left','Orchestra', FALSE),
('D',103,'Main Floor','Left','Orchestra', FALSE),
('D',105,'Main Floor','Left','Orchestra', FALSE),
('D',107,'Main Floor','Left','Side', FALSE),
('D',109,'Main Floor','Left','Side', FALSE),
('D',111,'Main Floor','Left','Side', FALSE),
('D',113,'Main Floor','Left','Side', FALSE),
('D',115,'Main Floor','Left','Side', FALSE),

('E',101,'Main Floor','Left','Orchestra', FALSE),
('E',103,'Main Floor','Left','Orchestra', FALSE),
('E',105,'Main Floor','Left','Orchestra', FALSE),
('E',107,'Main Floor','Left','Side', FALSE),
('E',109,'Main Floor','Left','Side', FALSE),
('E',111,'Main Floor','Left','Side', FALSE),
('E',113,'Main Floor','Left','Side', FALSE),
('E',115,'Main Floor','Left','Side', FALSE),    
    
('F',101,'Main Floor','Left','Orchestra', FALSE),
('F',103,'Main Floor','Left','Orchestra', FALSE),
('F',105,'Main Floor','Left','Orchestra', FALSE),
('F',107,'Main Floor','Left','Side', FALSE),
('F',109,'Main Floor','Left','Side', FALSE),
('F',111,'Main Floor','Left','Side', FALSE),
('F',113,'Main Floor','Left','Side', FALSE),
('F',115,'Main Floor','Left','Side', FALSE),
('F',117,'Main Floor','Left','Side', FALSE), 

('G',101,'Main Floor','Left','Orchestra', FALSE),
('G',103,'Main Floor','Left','Orchestra', FALSE),
('G',105,'Main Floor','Left','Orchestra', FALSE),
('G',107,'Main Floor','Left','Side', FALSE),
('G',109,'Main Floor','Left','Side', FALSE),
('G',111,'Main Floor','Left','Side', FALSE),
('G',113,'Main Floor','Left','Side', FALSE),
('G',115,'Main Floor','Left','Side', FALSE),
('G',117,'Main Floor','Left','Side', FALSE),

('H',101,'Main Floor','Left','Orchestra', FALSE),
('H',103,'Main Floor','Left','Orchestra', FALSE),
('H',105,'Main Floor','Left','Orchestra', FALSE),
('H',107,'Main Floor','Left','Side', FALSE),
('H',109,'Main Floor','Left','Side', FALSE),
('H',111,'Main Floor','Left','Side', FALSE),
('H',113,'Main Floor','Left','Side', FALSE),
('H',115,'Main Floor','Left','Side', FALSE),
('H',117,'Main Floor','Left','Side', FALSE),

('J',101,'Main Floor','Left','Orchestra', FALSE),
('J',103,'Main Floor','Left','Orchestra', FALSE),
('J',105,'Main Floor','Left','Orchestra', FALSE),
('J',107,'Main Floor','Left','Side', FALSE),
('J',109,'Main Floor','Left','Side', FALSE),
('J',111,'Main Floor','Left','Side', FALSE),
('J',113,'Main Floor','Left','Side', FALSE),
('J',115,'Main Floor','Left','Side', FALSE),
('J',117,'Main Floor','Left','Side', FALSE),

('K',101,'Main Floor','Left','Orchestra', FALSE),
('K',103,'Main Floor','Left','Orchestra', FALSE),
('K',105,'Main Floor','Left','Orchestra', FALSE),
('K',107,'Main Floor','Left','Side', FALSE),
('K',109,'Main Floor','Left','Side', FALSE),
('K',111,'Main Floor','Left','Side', FALSE),
('K',113,'Main Floor','Left','Side', FALSE),
('K',115,'Main Floor','Left','Side', FALSE),
('K',117,'Main Floor','Left','Side', FALSE),
('K',119,'Main Floor','Left','Side', FALSE),

('L',101,'Main Floor','Left','Orchestra', FALSE),
('L',103,'Main Floor','Left','Orchestra', FALSE),
('L',105,'Main Floor','Left','Orchestra', FALSE),
('L',107,'Main Floor','Left','Side', FALSE),
('L',109,'Main Floor','Left','Side', FALSE),
('L',111,'Main Floor','Left','Side', FALSE),
('L',113,'Main Floor','Left','Side', FALSE),
('L',115,'Main Floor','Left','Side', FALSE),
('L',117,'Main Floor','Left','Side', FALSE),
('L',119,'Main Floor','Left','Side', FALSE),

('M',101,'Main Floor','Left','Orchestra', FALSE),
('M',103,'Main Floor','Left','Orchestra', FALSE),
('M',105,'Main Floor','Left','Orchestra', FALSE),
('M',107,'Main Floor','Left','Side', FALSE),
('M',109,'Main Floor','Left','Side', FALSE),
('M',111,'Main Floor','Left','Side', FALSE),
('M',113,'Main Floor','Left','Side', FALSE),
('M',115,'Main Floor','Left','Side', FALSE),
('M',117,'Main Floor','Left','Side', FALSE),
('M',119,'Main Floor','Left','Side', FALSE),

('N',101,'Main Floor','Left','Orchestra', FALSE),
('N',103,'Main Floor','Left','Orchestra', FALSE),
('N',105,'Main Floor','Left','Orchestra', FALSE),
('N',107,'Main Floor','Left','Side', FALSE),
('N',109,'Main Floor','Left','Side', FALSE),
('N',111,'Main Floor','Left','Side', FALSE),
('N',113,'Main Floor','Left','Side', FALSE),
('N',115,'Main Floor','Left','Side', FALSE),
('N',117,'Main Floor','Left','Side', FALSE),
('N',119,'Main Floor','Left','Side', FALSE),

('O',101,'Main Floor','Left','Orchestra', FALSE),
('O',103,'Main Floor','Left','Orchestra', FALSE),
('O',105,'Main Floor','Left','Orchestra', FALSE),
('O',107,'Main Floor','Left','Side', FALSE),
('O',109,'Main Floor','Left','Side', FALSE),
('O',111,'Main Floor','Left','Side', FALSE),
('O',113,'Main Floor','Left','Side', FALSE),
('O',115,'Main Floor','Left','Side', FALSE),
('O',117,'Main Floor','Left','Side', FALSE),
('O',119,'Main Floor','Left','Side', FALSE),
('O',121,'Main Floor','Left','Side', FALSE),

('P',101,'Main Floor','Left','Orchestra', FALSE),
('P',103,'Main Floor','Left','Orchestra', FALSE),
('P',105,'Main Floor','Left','Orchestra', FALSE),
('P',107,'Main Floor','Left','Side', FALSE),
('P',109,'Main Floor','Left','Side', TRUE),
('P',111,'Main Floor','Left','Side', TRUE),
('P',113,'Main Floor','Left','Side', TRUE),
('P',115,'Main Floor','Left','Side', TRUE),
('P',117,'Main Floor','Left','Side', TRUE),
('P',119,'Main Floor','Left','Side', TRUE),
('P',121,'Main Floor','Left','Side', TRUE),

('Q',101,'Main Floor','Left','Orchestra', FALSE),
('Q',103,'Main Floor','Left','Orchestra', FALSE),
('Q',105,'Main Floor','Left','Orchestra', FALSE),
('Q',107,'Main Floor','Left','Side', FALSE),
('Q',109,'Main Floor','Left','Side', TRUE),
('Q',111,'Main Floor','Left','Side', TRUE),
('Q',113,'Main Floor','Left','Side', TRUE),
('Q',115,'Main Floor','Left','Side', TRUE),
('Q',117,'Main Floor','Left','Side', TRUE),
('Q',119,'Main Floor','Left','Side', TRUE),
('Q',121,'Main Floor','Left','Side', TRUE),

('R',101,'Main Floor','Left','Orchestra', FALSE),
('R',103,'Main Floor','Left','Orchestra', FALSE),
('R',105,'Main Floor','Left','Orchestra', FALSE),
('R',107,'Main Floor','Left','Side', FALSE),
('R',109,'Main Floor','Left','Side', TRUE),
('R',111,'Main Floor','Left','Side', TRUE),
('R',113,'Main Floor','Left','Side', TRUE),
('R',115,'Main Floor','Left','Side', TRUE),
('R',117,'Main Floor','Left','Side', TRUE),
('R',119,'Main Floor','Left','Side', TRUE),
('R',121,'Main Floor','Left','Side', TRUE),
    
    
('A',114,'Main Floor','Right','Side',FALSE),
('A',112,'Main Floor','Right','Side',FALSE),
('A',110,'Main Floor','Right','Side',FALSE),
('A',108,'Main Floor','Right','Side',FALSE),
('A',106,'Main Floor','Right','Orchestra',FALSE),
('A',104,'Main Floor','Right','Orchestra',FALSE),
('A',102,'Main Floor','Right','Orchestra',FALSE),
      
('B',116,'Main Floor','Right','Side',FALSE),
('B',114,'Main Floor','Right','Side',FALSE),
('B',112,'Main Floor','Right','Side',FALSE),
('B',110,'Main Floor','Right','Side',FALSE),
('B',108,'Main Floor','Right','Side',FALSE),
('B',106,'Main Floor','Right','Orchestra',FALSE),
('B',104,'Main Floor','Right','Orchestra',FALSE),
('B',102,'Main Floor','Right','Orchestra',FALSE),

('C',116,'Main Floor','Right','Side',FALSE),
('C',114,'Main Floor','Right','Side',FALSE),
('C',112,'Main Floor','Right','Side',FALSE),
('C',110,'Main Floor','Right','Side',FALSE),
('C',108,'Main Floor','Right','Side',FALSE),
('C',106,'Main Floor','Right','Orchestra',FALSE),
('C',104,'Main Floor','Right','Orchestra',FALSE),
('C',102,'Main Floor','Right','Orchestra',FALSE),

('D',116,'Main Floor','Right','Side',FALSE),
('D',114,'Main Floor','Right','Side',FALSE),
('D',112,'Main Floor','Right','Side',FALSE),
('D',110,'Main Floor','Right','Side',FALSE),
('D',108,'Main Floor','Right','Side',FALSE),
('D',106,'Main Floor','Right','Orchestra',FALSE),
('D',104,'Main Floor','Right','Orchestra',FALSE),
('D',102,'Main Floor','Right','Orchestra',FALSE),

('E',116,'Main Floor','Right','Side',FALSE),
('E',114,'Main Floor','Right','Side',FALSE),
('E',112,'Main Floor','Right','Side',FALSE),
('E',110,'Main Floor','Right','Side',FALSE),
('E',108,'Main Floor','Right','Side',FALSE),
('E',106,'Main Floor','Right','Orchestra',FALSE),
('E',104,'Main Floor','Right','Orchestra',FALSE),
('E',102,'Main Floor','Right','Orchestra',FALSE),

('F',118,'Main Floor','Right','Side',FALSE),
('F',116,'Main Floor','Right','Side',FALSE),
('F',114,'Main Floor','Right','Side',FALSE),
('F',112,'Main Floor','Right','Side',FALSE),
('F',110,'Main Floor','Right','Side',FALSE),
('F',108,'Main Floor','Right','Side',FALSE),
('F',106,'Main Floor','Right','Orchestra',FALSE),
('F',104,'Main Floor','Right','Orchestra',FALSE),
('F',102,'Main Floor','Right','Orchestra',FALSE),

('G',118,'Main Floor','Right','Side',FALSE),
('G',116,'Main Floor','Right','Side',FALSE),
('G',114,'Main Floor','Right','Side',FALSE),
('G',112,'Main Floor','Right','Side',FALSE),
('G',110,'Main Floor','Right','Side',FALSE),
('G',108,'Main Floor','Right','Side',FALSE),
('G',106,'Main Floor','Right','Orchestra',FALSE),
('G',104,'Main Floor','Right','Orchestra',FALSE),
('G',102,'Main Floor','Right','Orchestra',FALSE),

('H',118,'Main Floor','Right','Side',FALSE),
('H',116,'Main Floor','Right','Side',FALSE),
('H',114,'Main Floor','Right','Side',FALSE),
('H',112,'Main Floor','Right','Side',FALSE),
('H',110,'Main Floor','Right','Side',FALSE),
('H',108,'Main Floor','Right','Side',FALSE),
('H',106,'Main Floor','Right','Orchestra',FALSE),
('H',104,'Main Floor','Right','Orchestra',FALSE),
('H',102,'Main Floor','Right','Orchestra',FALSE),

('J',118,'Main Floor','Right','Side',FALSE),
('J',116,'Main Floor','Right','Side',FALSE),
('J',114,'Main Floor','Right','Side',FALSE),
('J',112,'Main Floor','Right','Side',FALSE),
('J',110,'Main Floor','Right','Side',FALSE),
('J',108,'Main Floor','Right','Side',FALSE),
('J',106,'Main Floor','Right','Orchestra',FALSE),
('J',104,'Main Floor','Right','Orchestra',FALSE),
('J',102,'Main Floor','Right','Orchestra',FALSE),

('K',120,'Main Floor','Right','Side',FALSE),
('K',118,'Main Floor','Right','Side',FALSE),
('K',116,'Main Floor','Right','Side',FALSE),
('K',114,'Main Floor','Right','Side',FALSE),
('K',112,'Main Floor','Right','Side',FALSE),
('K',110,'Main Floor','Right','Side',FALSE),
('K',108,'Main Floor','Right','Side',FALSE),
('K',106,'Main Floor','Right','Orchestra',FALSE),
('K',104,'Main Floor','Right','Orchestra',FALSE),
('K',102,'Main Floor','Right','Orchestra',FALSE),

('L',120,'Main Floor','Right','Side',FALSE),
('L',118,'Main Floor','Right','Side',FALSE),
('L',116,'Main Floor','Right','Side',FALSE),
('L',114,'Main Floor','Right','Side',FALSE),
('L',112,'Main Floor','Right','Side',FALSE),
('L',110,'Main Floor','Right','Side',FALSE),
('L',108,'Main Floor','Right','Side',FALSE),
('L',106,'Main Floor','Right','Orchestra',FALSE),
('L',104,'Main Floor','Right','Orchestra',FALSE),
('L',102,'Main Floor','Right','Orchestra',FALSE),

('M',120,'Main Floor','Right','Side',FALSE),
('M',118,'Main Floor','Right','Side',FALSE),
('M',116,'Main Floor','Right','Side',FALSE),
('M',114,'Main Floor','Right','Side',FALSE),
('M',112,'Main Floor','Right','Side',FALSE),
('M',110,'Main Floor','Right','Side',FALSE),
('M',108,'Main Floor','Right','Side',FALSE),
('M',106,'Main Floor','Right','Orchestra',FALSE),
('M',104,'Main Floor','Right','Orchestra',FALSE),
('M',102,'Main Floor','Right','Orchestra',FALSE),

('N',120,'Main Floor','Right','Side',FALSE),
('N',118,'Main Floor','Right','Side',FALSE),
('N',116,'Main Floor','Right','Side',FALSE),
('N',114,'Main Floor','Right','Side',FALSE),
('N',112,'Main Floor','Right','Side',FALSE),
('N',110,'Main Floor','Right','Side',FALSE),
('N',108,'Main Floor','Right','Side',FALSE),
('N',106,'Main Floor','Right','Orchestra',FALSE),
('N',104,'Main Floor','Right','Orchestra',FALSE),
('N',102,'Main Floor','Right','Orchestra',FALSE),

('O',122,'Main Floor','Right','Side',False),
('O',120,'Main Floor','Right','Side',FALSE),
('O',118,'Main Floor','Right','Side',FALSE),
('O',116,'Main Floor','Right','Side',FALSE),
('O',114,'Main Floor','Right','Side',FALSE),
('O',112,'Main Floor','Right','Side',FALSE),
('O',110,'Main Floor','Right','Side',FALSE),
('O',108,'Main Floor','Right','Side',FALSE),
('O',106,'Main Floor','Right','Orchestra',FALSE),
('O',104,'Main Floor','Right','Orchestra',FALSE),
('O',102,'Main Floor','Right','Orchestra',FALSE),

('P',122,'Main Floor','Right','Side',TRUE),
('P',120,'Main Floor','Right','Side',TRUE),
('P',118,'Main Floor','Right','Side',TRUE),
('P',116,'Main Floor','Right','Side',TRUE),
('P',114,'Main Floor','Right','Side',TRUE),
('P',112,'Main Floor','Right','Side',TRUE),
('P',110,'Main Floor','Right','Side',TRUE),
('P',108,'Main Floor','Right','Side',FALSE),
('P',106,'Main Floor','Right','Orchestra',FALSE),
('P',104,'Main Floor','Right','Orchestra',FALSE),
('P',102,'Main Floor','Right','Orchestra',FALSE),

('Q',122,'Main Floor','Right','Side',TRUE),
('Q',120,'Main Floor','Right','Side',TRUE),
('Q',118,'Main Floor','Right','Side',TRUE),
('Q',116,'Main Floor','Right','Side',TRUE),
('Q',114,'Main Floor','Right','Side',TRUE),
('Q',112,'Main Floor','Right','Side',TRUE),
('Q',110,'Main Floor','Right','Side',TRUE),
('Q',108,'Main Floor','Right','Side',FALSE),
('Q',106,'Main Floor','Right','Orchestra',FALSE),
('Q',104,'Main Floor','Right','Orchestra',FALSE),
('Q',102,'Main Floor','Right','Orchestra',FALSE),

('R',122,'Main Floor','Right','Side',TRUE),
('R',120,'Main Floor','Right','Side',TRUE),
('R',118,'Main Floor','Right','Side',TRUE),
('R',116,'Main Floor','Right','Side',TRUE),
('R',114,'Main Floor','Right','Side',TRUE),
('R',112,'Main Floor','Right','Side',TRUE),
('R',110,'Main Floor','Right','Side',TRUE),
('R',108,'Main Floor','Right','Side',FALSE),
('R',106,'Main Floor','Right','Orchestra',FALSE),
('R',104,'Main Floor','Right','Orchestra',FALSE),
('R',102,'Main Floor','Right','Orchestra',FALSE);

SELECT count (*) from Seat;

insert into Customer (CustomerID, FirstName, LastName)values
(1234,'Mike','Johnson')
;
insert into private.Customer(CustomerID, CreditCard) values
(1234, 1234567887654321)
;
insert into Ticket (TicketNumber,CustomerID,SeatRow,SeatNumber,ShowTime)values
(1,1234,'A',6,'2017-12-15 02:00:00'),
(2,1234,'A',8,'2017-12-15 02:00:00'),
(3,1234,'A',10,'2017-12-15 02:00:00'),
(4,1234,'A',9,'2017-12-15 02:00:00')
;