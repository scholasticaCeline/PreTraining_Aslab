--no 1
SELECT EmployeeName, EmployeeSalary, EmployeeDOB
FROM MsEmployee
WHERE EmployeeGender LIKE 'male'

--no 2
UPDATE MsProtein
SET ProteinBrand = CONCAT(ProteinBrand, ' Brand')
WHERE ProteinBrand LIKE '% %';

--no 3
SELECT 
UPPER(ProteinTypeName) AS ProteinTypeName, ProteinRating, ProteinManufactureDate
FROM MsProteinType, MsProtein
WHERE ProteinRating BETWEEN 4.4 AND 4.6

--no 4
SELECT 
	CONCAT('Mr/Mrs. ', CustomerName) AS CustomerSalutations, CustomerGender, EmployeeID, TransactionDate
FROM TransactionDetail, MsCustomer, TransactionHeader
WHERE MONTH(TransactionDate) = '1' AND LEN(CustomerName) >= 12

--no 5
SELECT *
FROM MsEmployee
WHERE LEFT(EmployeeAddress, 1) = '1' AND RIGHT(EmployeeName, 3) = 'son'--for testing aja

UPDATE MsEmployee
SET EmployeeAddress = '123 New Street'
WHERE RIGHT(EmployeeName, 3) = 'son' AND LEFT(EmployeeAddress, 1) = '1' AND LEN(EmployeePhone) > 10

--no 6
SELECT *
FROM TransactionHeader, MsCustomer
WHERE DATEPART(quarter, TransactionDate) IN (1,2) AND CustomerGender = 'Female'

BEGIN TRAN
ROLLBACK

UPDATE TransactionHeader
SET TransactionDate = DATEADD(day, 7, TransactionDate)
FROM TransactionHeader
JOIN MsCustomer ON TransactionHeader.CustomerID = MsCustomer.CustomerID
WHERE DATEPART(quarter, TransactionDate) IN (1,2) AND MsCustomer.CustomerGender LIKE 'Female'

COMMIT

--no 7
SELECT * FROM TransactionDetail, TransactionHeader
WHERE YEAR(TransactionDate) = 2022 
--AND TransactionDate > DATEADD(month, -2, GETDATE())

BEGIN TRAN
ROLLBACK

DELETE TransactionDetail FROM TransactionDetail 
JOIN TransactionHeader ON TransactionDetail.TransactionID = TransactionHeader.TransactionID
WHERE YEAR(TransactionHeader.TransactionDate) = 2022 
	AND TransactionHeader.TransactionDate > DATEADD(month, -2, GETDATE())

--no 8
SELECT * FROM MsProtein

SELECT LEFT(CustomerName, CHARINDEX(' ', CustomerName) - 1) AS FirstName,
    REVERSE(SUBSTRING(REVERSE(CustomerName), 1, CHARINDEX(' ', REVERSE(CustomerName)) - 1)) AS LastName,
    Quantity, ProteinName, ProteinPrice
FROM MsCustomer, MsProtein, TransactionDetail
WHERE ProteinPrice IN (45, 50)

--no 9
SELECT CONCAT('Mr/Mrs. ', CustomerName) AS CustomerSalutations, 
	FORMAT(TransactionDate, 'dd.MM.yyyy') AS NorwegianTransactionDate, Quantity, ProteinTypeName
FROM MsCustomer, TransactionDetail, TransactionHeader, MsProteinType
WHERE ProteinTypeName LIKE '%Egg Protein%'

--no 10
SELECT * FROM MsCustomer

SELECT STUFF(CustomerEmail, CHARINDEX('@', CustomerEmail), 0, 'man') AS ModifiedCustomerEmail,
	CustomerDOB,
    LOWER(ProteinName) AS LowercaseProteinName,
    ProteinPrice
FROM MsCustomer, MsProtein, MsProteinType
WHERE CustomerGender = 'Male' AND DATEDIFF(YEAR, CustomerDOB, GETDATE()) > 40;