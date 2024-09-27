USE JnZ_Express
SELECT * FROM MsCustomer

--no 1
SELECT sender.customerName AS 'Sender Name',
	receiver.customerName AS 'Receiver Name',
	transactionDate AS 'Transaction Date'
FROM TransactionHeader
JOIN MsCustomer sender ON TransactionHeader.senderID = sender.customerID
JOIN MsCustomer receiver ON TransactionHeader.receiverID = receiver.customerID
WHERE RIGHT(transactionID, 3) % 4 = 0 AND MONTH(transactionDate) = '2'

--no 2
SELECT UPPER(staffName) AS 'Staff Name', 
	staffAddress AS 'Staff Address',
	cityName AS 'Staff City'
FROM MsStaff
JOIN MsCity ON MsStaff.cityID = MsCity.cityID
WHERE staffAddress LIKE '[0-9]0 %'; --done 

--no 3
SELECT 
    transactionID AS 'Transaction ID',
    MONTH(transactionDate) AS 'Transaction Month',
    deliveryServiceName AS 'Delivery Service Name',
    (deliveryServicePrice * weight) AS 'Total Revenue Per Transaction'
FROM TransactionHeader
JOIN 
    MsDeliveryService ON TransactionHeader.deliveryServiceID = MsDeliveryService.deliveryServiceID
WHERE 
    deliveryServiceName LIKE '% % %';

--no 4
BEGIN TRAN
UPDATE MsDeliveryService
SET deliveryServicePrice += 5000
FROM TransactionHeader
JOIN MsProductType ON TransactionHeader.productTypeID = MsProductType.productTypeID
JOIN MsDeliveryService ON TransactionHeader.deliveryServiceID = MsDeliveryService.deliveryServiceID
WHERE deliveryServiceName LIKE '___'
    AND RIGHT(transactionID, 3) % 2 = 0
    AND MONTH(transactionDate) = 4
    AND LEN(description) > 20;
ROLLBACK

--no 5
SELECT sender.customerName AS 'Customer Name',
	REVERSE(TransactionHeader.transactionID) AS 'Coupon Code',
	FORMAT(TransactionHeader.transactionDate, 'dd-MM-yyyy') AS 'Coupon Start Date',
	FORMAT(DATEADD (MONTH, 3, TransactionHeader.transactionDate), 'dd-MM-yyyy') AS 'Coupon Expiry Date',
	'Rp 10,000' AS 'Coupon Price'
FROM TransactionHeader
JOIN MsCustomer sender ON TransactionHeader.senderID = sender.customerID
JOIN MsCustomer receiver ON TransactionHeader.receiverID = receiver.customerID
JOIN MsDeliveryService ON TransactionHeader.deliveryServiceID = MsDeliveryService.deliveryServiceID
WHERE (MsDeliveryService.deliveryServicePrice * weight) > 20000

--no 6
SELECT * 
FROM MsProductType
JOIN TransactionHeader ON MsProductType.productTypeID = TransactionHeader.productTypeID
WHERE LEN(MsProductType.description) > 20 
	AND DATEPART(quarter, TransactionHeader.transactionDate) IN (1)

BEGIN TRAN
DELETE MsProductType 
FROM MsProductType
JOIN TransactionHeader ON MsProductType.productTypeID = TransactionHeader.productTypeID
WHERE LEN(MsProductType.description) > 20 
	AND DATEPART(quarter, TransactionHeader.transactionDate) IN (1,4)
ROLLBACK

--no 7
SELECT *
FROM MsCustomer
JOIN TransactionHeader ON MsCustomer.customerID = TransactionHeader.senderID
JOIN MsDeliveryService ON TransactionHeader.deliveryServiceID = MsDeliveryService.deliveryServiceID
WHERE MONTH(TransactionHeader.transactionDate) LIKE '2'
	AND MsDeliveryService.deliveryServiceName LIKE 'G%' --tes aja

BEGIN TRAN
UPDATE MsCustomer
SET customerName = CONCAT('Mr./Mrs ', customerName)
FROM MsCustomer
JOIN TransactionHeader ON MsCustomer.customerID = TransactionHeader.senderID
JOIN MsDeliveryService ON TransactionHeader.deliveryServiceID = MsDeliveryService.deliveryServiceID
WHERE MONTH(TransactionHeader.transactionDate) LIKE '2'
	AND MsDeliveryService.deliveryServiceName LIKE 'G%'
ROLLBACK

--no 8
CREATE VIEW [Customer_Total_Transaction_More_Than_25000] AS
SELECT
	SUBSTRING(customerName, 1, CHARINDEX(' ', customerName) - 1) AS 'Sender First Name',
	SUBSTRING(customerName, CHARINDEX(' ', customerName) + 1, LEN(customerName)) AS 'Sender Last Name',
	MsProductType.productTypeName AS 'Product Type Name',
	MsProductType.description AS 'Product Description',
	TransactionHeader.transactionDate AS 'Transaction Date',
	MsDeliveryServiceType.deliveryServiceName AS 'Delivery Service Name',
	FORMAT(MsDeliveryService.deliveryServicePrice * TransactionHeader.weight, 'Rp#,0.00') AS 'Total Transaction'
FROM MsCustomer
JOIN TransactionHeader ON MsCustomer.customerID = TransactionHeader.senderID
JOIN MsProductType ON TransactionHeader.productTypeID = MsProductType.productTypeID
JOIN MsDeliveryService ON TransactionHeader.deliveryServiceID = MsDeliveryService.deliveryServiceID
JOIN MsDeliveryServiceType ON MsDeliveryService.deliveryServiceTypeID = MsDeliveryServiceType.deliveryServiceTypeID
WHERE (MsDeliveryService.deliveryServicePrice * weight) > 25000

SELECT * FROM [Customer_Total_Transaction_More_Than_25000]
DROP VIEW [Customer_Total_Transaction_More_Than_25000]

--no 9
CREATE VIEW [Total_Transaction_Using_Sea_Delivery_In_American_Dollar] AS
SELECT
	CONCAT('Mr./Mrs. ', SUBSTRING(MsStaff.staffName, CHARINDEX(' ', MsStaff.staffName) + 1, LEN(MsStaff.staffName))) AS 'Staff Name',
	FORMAT(transactionHeader.transactionDate, 'dd-MM-yyyy') AS 'Transaction Date',
	FORMAT((TransactionHeader.weight * MsDeliveryService.deliveryServicePrice / 14000), '###,###,###,##0.00') AS 'Total Revenue',
	MsDeliveryService.deliveryServiceName AS 'Delivery Service Name'
FROM MsStaff
JOIN TransactionHeader ON MsStaff.staffID = TransactionHeader.staffID
JOIN MsDeliveryService ON TransactionHeader.deliveryServiceID = MsDeliveryService.deliveryServiceID
JOIN MsDeliveryServiceType ON MsDeliveryService.deliveryServiceTypeID = MsDeliveryServiceType.deliveryServiceTypeID
WHERE MsDeliveryServiceType.deliveryServiceName LIKE 'Sea Delivery'

SELECT * FROM [Total_Transaction_Using_Sea_Delivery_In_American_Dollar]
DROP VIEW [Total_Transaction_Using_Sea_Delivery_In_American_Dollar]

--no 10
SELECT MsStaff.staffName, MsProductType.productTypeName, MsDeliveryServiceType.deliveryServiceName
FROM MsStaff
JOIN TransactionHeader ON MsStaff.staffID = TransactionHeader.staffID
JOIN MsProductType ON TransactionHeader.productTypeID = MsProductType.productTypeID
JOIN MsDeliveryService ON TransactionHeader.deliveryServiceID = MsDeliveryService.deliveryServiceID
JOIN MsDeliveryServiceType ON MsDeliveryService.deliveryServiceTypeID = MsDeliveryServiceType.deliveryServiceTypeID
WHERE MsProductType.productTypeName LIKE 'Fragile'
	AND MsDeliveryServiceType.deliveryServiceName LIKE 'Land Delivery' --keperluan debug :)

CREATE VIEW [View_staff_commission] AS
SELECT 
	LOWER(CONCAT(SUBSTRING(MsStaff.staffName, CHARINDEX(' ', MsStaff.staffName) + 1, LEN(MsStaff.staffName)), '@gmail.com')) AS 'Staff Email',
	FORMAT((TransactionHeader.weight * MsDeliveryService.deliveryServicePrice), 'Rp#,0.00') AS 'Total Revenue',
	FORMAT((TransactionHeader.weight * MsDeliveryService.deliveryServicePrice * 0.2), 'Rp#,0.00') AS 'Commission',
	TransactionHeader.transactionDate AS 'Transaction Date'
FROM MsStaff
JOIN TransactionHeader ON MsStaff.staffID = TransactionHeader.staffID
JOIN MsProductType ON TransactionHeader.productTypeID = MsProductType.productTypeID
JOIN MsDeliveryService ON TransactionHeader.deliveryServiceID = MsDeliveryService.deliveryServiceID
JOIN MsDeliveryServiceType ON MsDeliveryService.deliveryServiceTypeID = MsDeliveryServiceType.deliveryServiceTypeID
WHERE MsProductType.productTypeName LIKE 'Fragile'
	AND MsDeliveryServiceType.deliveryServiceName LIKE 'Land Delivery'

UNION ALL

SELECT 
	LOWER(CONCAT(SUBSTRING(MsStaff.staffName, CHARINDEX(' ', MsStaff.staffName) + 1, LEN(MsStaff.staffName)), '@gmail.com')) AS 'Staff Email',
	FORMAT((TransactionHeader.weight * MsDeliveryService.deliveryServicePrice), 'Rp#,0.00') AS 'Total Revenue',
	FORMAT((TransactionHeader.weight * MsDeliveryService.deliveryServicePrice * 0.2), 'Rp#,0.00') AS 'Commission',
	TransactionHeader.transactionDate AS 'Transaction Date'
FROM MsStaff
JOIN TransactionHeader ON MsStaff.staffID = TransactionHeader.staffID
JOIN MsProductType ON TransactionHeader.productTypeID = MsProductType.productTypeID
JOIN MsDeliveryService ON TransactionHeader.deliveryServiceID = MsDeliveryService.deliveryServiceID
JOIN MsDeliveryServiceType ON MsDeliveryService.deliveryServiceTypeID = MsDeliveryServiceType.deliveryServiceTypeID
WHERE MsProductType.productTypeName LIKE 'Caution'
	AND MsDeliveryServiceType.deliveryServiceName LIKE 'Air Delivery'


SELECT * FROM View_staff_commission
DROP VIEW View_staff_commission