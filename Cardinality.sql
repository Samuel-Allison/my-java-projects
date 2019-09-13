DROP TABLE IF EXISTS student CASCADE;
DROP TABLE IF EXISTS dorm_room CASCADE;
DROP TABLE IF EXISTS developer CASCADE;
DROP TABLE IF EXISTS workstation CASCADE;
DROP TABLE IF EXISTS pupll CASCADE;
DROP TABLE IF EXISTS assigned_seat CASCADE;
DROP TABLE IF EXISTS artist CASCADE;
DROP TABLE IF EXISTS painting CASCADE;
DROP TABLE IF EXISTS dorm_resident CASCADE;
DROP TABLE IF EXISTS patient CASCADE;
DROP TABLE IF EXISTS prescription CASCADE;
DROP TABLE IF EXISTS doctor CASCADE;
DROP TABLE IF EXISTS medication CASCADE;

CREATE TABLE dorm_room(
    building TEXT unique,
    number INT unique,
    primary key( building, number)
    );

CREATE TABLE student(
    id INT primary key,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    dorm_room_building text,
    dorm_room_number INT,
    constraint student_FK FOREIGN KEY(dorm_room_building,dorm_room_number) REFERENCES dorm_room(building,number)
    );
CREATE TABLE workstation(
    hostname TEXT primary key
    );
CREATE TABLE developer(
    first_name TEXT,
    last_name TEXT,
    workstation_hostname TEXT NOT NULL Unique,
    primary key (first_name,last_name),
    foreign key (workstation_hostname) REFERENCES workstation(hostname)
    );
CREATE TABLE assigned_seat(
    number INT primary key
    );
CREATE TABLE pupll(
    id INT primary key,
    name TEXT,
    assigned_seat_number INT unique,
    foreign key (assigned_seat_number) REFERENCES assigned_seat(number)
    );

CREATE TABLE artist(
    name TEXT primary key,
    year_born INT NOT NULL,
    year_died INT
    );
CREATE TABLE painting(
    id INT primary key,
    name TEXT,
    artist_name text NOT NULL,
    foreign key (artist_name) REFERENCES artist(name)
    );
CREATE TABLE dorm_resident( 
    id INT primary key,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    room_number INT,
    resident_assistant INT,
    foreign key (resident_assistant) REFERENCES dorm_resident(id)
    );

CREATE TABLE patient(
    id INT primary key,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL
    );
CREATE TABLE doctor(
    id INT primary key,
    last_name TEXT NOT NULL
    );
CREATE TABLE medication(
    name TEXT primary key
    );

CREATE TABLE prescription(
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    medication_name TEXT NOT NULL,
    foreign key (patient_id) REFERENCES patient(id),
    foreign key (doctor_id) REFERENCES doctor(id),
    foreign key (medication_name) REFERENCES medication(name)
   -- primary key(patient_id,doctor_id,medication_name)
    );
    