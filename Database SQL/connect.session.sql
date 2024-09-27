    --@block
CREATE TABLE Employee (
    employeeId INT,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    hourlyPay DECIMAL(5, 2),
    hireDate DATE
);

--@block
ALTER TABLE employee 
ADD phoneNumber VARCHAR(15);

--@block
ALTER TABLE employee
RENAME COLUMN phoneNumber to email;

--@block
ALTER TABLE employee
MODIFY COLUMN email VARCHAR(100);

--@block
ALTER TABLE employee
MODIFY email VARCHAR(100)
AFTER lastName;

SELECT * FROM employee;

--@block
ALTER TABLE employee
DROP COLUMN email;
SELECT * FROM employee;

--@block
INSERT INTO employee
VALUES
(2, "Test", "Name", 30.0, "2024-04-01");

SELECT * FROM employee;