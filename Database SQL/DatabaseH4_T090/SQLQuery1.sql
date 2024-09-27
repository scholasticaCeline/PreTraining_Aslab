USE TheHarveISt

SELECT * FROM MsProduct

-- no 1
SELECT mcu.CustomerID AS 'Customer ID',
       mcu.CustomerName AS 'Customer Name'
FROM msCustomer mcu
JOIN TransactionHeader th ON mcu.CustomerID = th.CustomerID
JOIN TransactionDetail td ON th.TransactionID = td.TransactionID
GROUP BY mcu.CustomerID, mcu.CustomerName
HAVING COUNT(td.ProductID) >= 2

EXCEPT

SELECT mcu.CustomerID AS 'Customer ID',
       mcu.CustomerName AS 'Customer Name'
FROM msCustomer mcu
JOIN TransactionHeader th ON mcu.CustomerID = th.CustomerID
JOIN TransactionDetail td ON th.TransactionID = td.TransactionID
GROUP BY mcu.CustomerID, mcu.CustomerName
HAVING COUNT(td.ProductID) < 2; --done

--no 2
SELECT ProductID AS 'Product ID', ProductName AS 'Product Name'
FROM MsProduct
WHERE ProductPrice > (SELECT AVG(ProductPrice) FROM MsProduct); --done

--no 3
SELECT REPLACE(cu.CustomerID, 'CU', 'Customer') AS 'Customer ID',
	CONCAT('Mr. ', CustomerName) AS 'Customer Name',
	mt.MembershipTypeName AS 'Membership Type',
	FORMAT(cu.CustomerDOB, 'dd MMM yyyy') AS 'Date of Birth',
	DATEDIFF(YEAR, cu.CustomerDOB, GETDATE()) AS 'Age'
FROM MsCustomer cu
JOIN MsMembershipType mt ON cu.MembershipTypeID = mt.MembershipTypeID
WHERE cu.CustomerGender LIKE 'Male'

UNION

SELECT REPLACE(cu.CustomerID, 'CU', 'Customer') AS 'Customer ID',
	CONCAT('Mrs. ', CustomerName) AS 'Customer Name',
	mt.MembershipTypeName AS 'Membership Type',
	FORMAT(cu.CustomerDOB, 'dd MMM yyyy') AS 'Date of Birth',
	DATEDIFF(YEAR, cu.CustomerDOB, GETDATE()) AS 'Age'
FROM MsCustomer cu
JOIN MsMembershipType mt ON cu.MembershipTypeID = mt.MembershipTypeID
WHERE cu.CustomerGender LIKE 'Female'

--no 4
SELECT TOP 1 pr.ProductName AS 'Best Seller Product',
    COUNT(*) AS 'Total Product Purchased'
FROM MsCustomer cu
JOIN TransactionHeader th ON th.CustomerID = cu.CustomerID
JOIN TransactionDetail td ON th.TransactionID = td.TransactionID
JOIN MsProduct pr ON td.ProductID = pr.ProductID
WHERE YEAR(TransactionDate) LIKE '2023'
GROUP BY pr.ProductName
ORDER BY COUNT(*) DESC --done

--no 5
SELECT SUBSTRING(StaffName, 1, CHARINDEX(' ', StaffName) - 1) AS 'First Name',
	SUBSTRING(StaffName, CHARINDEX(' ', StaffName) + 1, LEN(StaffName)) AS 'Last Name',
	COUNT(*) AS 'Total Transactions'
FROM MsStaff st
JOIN TransactionHeader th ON st.StaffID = th.StaffID
GROUP BY StaffName --done

--no 6
SELECT cu.CustomerName AS 'Customer Name',
    COUNT(*) AS 'Total Coffee Purchased'
FROM MsCustomer cu
JOIN TransactionHeader th ON cu.CustomerID = th.CustomerID
JOIN TransactionDetail td ON th.TransactionID = td.TransactionID
JOIN MsProduct pr ON td.ProductID = pr.ProductID
WHERE cu.CustomerGender LIKE 'Male' AND pr.ProductName LIKE 'Coffee'
GROUP BY cu.CustomerName, pr.ProductName;

--no 7
SELECT UPPER(MembershipTypeName) AS 'Membership Type', 
	COUNT(cu.MembershipTypeID) AS 'Customers'
FROM MsMembershipType mt
JOIN MsCustomer cu ON mt.MembershipTypeID = cu.MembershipTypeID
GROUP BY mt.MembershipTypeName --done

--no 8
SELECT LOWER(cu.CustomerName) AS 'Customer Name',
	CONCAT('Rp. ', SUM(pr.ProductPrice), ',-') AS 'Total Spending'
FROM MsCustomer cu
JOIN TransactionHeader th ON cu.CustomerID = th.CustomerID
JOIN TransactionDetail td ON th.TransactionID = td.TransactionID
JOIN MsProduct pr ON td.ProductID = pr.ProductID
GROUP BY cu.CustomerName
ORDER BY SUM(pr.ProductPrice) DESC --done

--no 9


--no 10
SELECT CustomerName AS 'Customer Name',
	COUNT(th.TransactionID) AS 'Total Transactions'
FROM MsCustomer cu
JOIN TransactionHeader th ON cu.CustomerID = th.CustomerID
--JOIN TransactionDetail td ON th.TransactionID = td.TransactionID
WHERE CustomerName LIKE 'Isabella%'
GROUP BY CustomerName --done

