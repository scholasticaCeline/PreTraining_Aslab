CREATE TABLE Lecturer(
	lecturerID CHAR(7) PRIMARY KEY,
	lecturerName VARCHAR(255) NOT NULL,
	lecturerEmail VARCHAR(255) NOT NULL UNIQUE,
	lecturerPhone VARCHAR(255) NOT NULL UNIQUE,
	lecturerDOB DATE NOT NULL
);

CREATE TABLE Course(
	courseID CHAR(7) PRIMARY KEY,
	courseName VARCHAR(50) NOT NULL
);

CREATE TABLE Class(
	classID CHAR(5) PRIMARY KEY,
	courseID CHAR(7) NOT NULL,
	FOREIGN KEY(courseID) REFERENCES Course(courseID),
	lecturerID CHAR(7) NOT NULL,
	FOREIGN KEY(lecturerID) REFERENCES Lecturer(lecturerID)
);

CREATE TABLE Students(
	StudentID CHAR(9) PRIMARY KEY,
	studentName VARCHAR(255) NOT NULL,
	studentEmail VARCHAR(255),
	studentPhone VARCHAR(15),
	studentDOB DATE NOT NULL,
	classID CHAR(5),
	FOREIGN KEY(classID) REFERENCES Class(classID)
);

ALTER TABLE Lecturer
ADD CHECK(lecturerID LIKE 'LEC[0-9][0-9][0-9][0-9]'); --LEC 4 angka

ALTER TABLE Lecturer
ADD CHECK(lecturerPhone LIKE '[0-9]%'); --checks if it starts with a number

INSERT INTO Lecturer(lecturerID, lecturerName, lecturerEmail, lecturerPhone, lecturerDOB)
VALUES
	('LEC0001', 'Vernandio', 'Vernandio@gmail.com', '0812345678', '2000-01-01');

SELECT * FROM Lecturer;

UPDATE Lecturer
SET lecturerName = 'Natha' WHERE lecturerID LIKE 'LEC0001';

UPDATE Lecturer
SET lecturerEmail = 'natha@gmail.com' WHERE lecturerID LIKE 'LEC0001';

DELETE FROM Lecturer 
WHERE lecturerID LIKE 'LEC0001';

SELECT * FROM Lecturer;

--transaction
BEGIN TRAN
DELETE FROM Lecturer
WHERE lecturerID LIKE 'LEC0001'

ROLLBACK
COMMIT

--Select :)
SELECT lecturerID, lecturerEmail
FROM Lecturer

--script
