USE DafflesHotel3

--No 1
SELECT 
	REPLACE(GuestID, 'CU', CONCAT(SUBSTRING(GuestName, 1, 1), SUBSTRING(GuestName, CHARINDEX(' ', GuestName) + 1, 1))) AS ID,
	GuestName AS 'Name',
	GuestAddress AS 'Address'
FROM MsGuest

--no 2
SELECT HotelID, HotelAddress, HotelEmail
FROM MsHotel
WHERE HotelAddress LIKE '%de%'

--no 3
UPDATE MsService
SET ServicePrice = ServicePrice + (ServicePrice * 0.15)
WHERE ServicePrice < 50000

--no 4
SELECT
	MsGuest.GuestID AS 'Customer ID',
	SUBSTRING(GuestName, 1, CHARINDEX(' ', GuestName) - 1) AS 'First Name',
	REVERSE(SUBSTRING(REVERSE(GuestName), 1, CHARINDEX(' ', REVERSE(GuestName)) - 1)) AS 'Last Name',
	FORMAT((RoomTypePrice * 
		(DAY(CheckOutDate) - DAY(CheckInDate))
	), 'Rp#,0.00') AS 'Total Price Room (Rupiahs)',
	FORMAT((RoomTypePrice * 
		(DAY(CheckOutDate) - DAY(CheckInDate))
	) / 16000, '$#,0.00') AS 'Total Price Room (Dollar)'
	--assuming nilai tukar rupiah ke dollar 16000 
FROM MsGuest
JOIN TransactionHeader ON MsGuest.GuestID = TransactionHeader.GuestID
JOIN MsRoom ON TransactionHeader.RoomNumber = MsRoom.RoomNumber
JOIN MsRoomType ON MsRoom.RoomTypeID = MsRoomType.RoomTypeID

--no 5
GO
CREATE VIEW [BestStaffin2023] AS
SELECT 
	MsStaff.StaffID, StaffName, 
	COUNT(MsGuest.GuestID) AS 'Total Served'
FROM MsStaff
JOIN TransactionHeader ON MsStaff.StaffID = TransactionHeader.StaffID 
JOIN MsGuest ON TransactionHeader.GuestID = MsGuest.GuestID
GROUP BY MsStaff.StaffID, StaffName, MsGuest.GuestID, CheckInDate
HAVING YEAR(CheckInDate) LIKE '2023'
GO

SELECT * FROM [BestStaffin2023]

--no 6
SELECT REPLACE(PaymentMethodID, 'M', UPPER(RIGHT(PaymentMethodName, 1))) AS 'Payment Method ID',
	PaymentMethodName AS 'Payment Method Name'
FROM MsPaymentMethod
ORDER BY PaymentMethodName ASC

--no 7
SELECT LEFT(GuestName, CHARINDEX(' ', GuestName) - 1) AS 'Name',
	FORMAT(CheckInDate, 'MM dd, yyyy') AS 'Check In Date',
	FORMAT(CheckOutDate, 'MM dd, yyyy') AS 'Check Out Date',
	CONCAT(LateCheckOutInHours, ' hours') AS 'Late Check Out Hours',
	FORMAT(LateCheckOutInHours * 150000, 'Rp#,0.00') AS 'Late Check Out Fee'
FROM MsGuest
JOIN TransactionHeader ON MsGuest.GuestID = TransactionHeader.GuestID

--no 8
SELECT REPLACE(MsService.ServiceID, 'SE', 'Service') AS 'Service ID',
	LOWER(ServiceName) AS 'Service Name',
	MIN(Quantity) AS 'Minimum Quantity Per Service',
	MAX(Quantity) AS 'Maximum Quantity Per Service'
FROM MsService
JOIN TransactionDetail ON MsService.ServiceID = TransactionDetail.ServiceID
GROUP BY MsService.ServiceID, ServiceName, Quantity

--no 9
SELECT DISTINCT
	CONCAT()
FROM MsHotel

--no 10
SELECT CONCAT('Mr/Mrs. ', UPPER(GuestName)) AS 'Customer Name',
	GuestEmail AS 'Customer Email Address',
	GuestPhone AS 'Customer Phone Number'
FROM MsGuest
WHERE RIGHT(GuestPhone, 1) % 2 = 1

--no 11


--no 12
ALTER TABLE MsGuest
ALTER COLUMN GuestPhone varchar(15);

--no 13
SELECT DISTINCT CONCAT(UPPER(LEFT(RoomTypeName, 2)), MsRoom.RoomNumber) AS 'ID',
	MsRoom.RoomNumber AS 'Room Number',
	RoomTypeName AS 'Room Type Name',
	COUNT(Quantity) AS 'Total Transaction'
FROM MsRoom
JOIN MsRoomType ON MsRoom.RoomTypeID = MsRoom.RoomTypeID
JOIN TransactionHeader ON MsRoom.RoomNumber = TransactionHeader.RoomNumber
JOIN TransactionDetail ON TransactionHeader.TransactionID = TransactionDetail.TransactionID
GROUP BY RoomTypeName, MsRoom.RoomNumber, RoomTypeName, Quantity
ORDER BY COUNT(Quantity) DESC

--no 14
BEGIN TRAN
ROLLBACK
DELETE FROM TransactionDetail
WHERE TransactionID IN (
    SELECT TransactionID
    FROM TransactionHeader
	JOIN MsPaymentMethod ON TransactionHeader.PaymentMethodID = MsPaymentMethod.PaymentMethodID
    WHERE PaymentMethodName LIKE 'Cash'
);

--no 15
CREATE VIEW [Top5BranchDafflEsHotel] AS
SELECT CONCAT(),
	HotelAddress,
	COUNT(GuestID) AS 'Total Transaction'
FROM MsHotel
JOIN TransactionHeader ON MsHotel.HotelID = TransactionHeader.HotelID
WHERE MsHotel.HotelID NOT LIKE '%0%'
ORDER BY COUNT(*) DESC

--no 16
SELECT CONCAT('SE', ASCII(LOWER(LEFT(ServiceName, 1))), ASCII(RIGHT(ServiceName, 1))) AS ServiceID,
    ServiceName,
    FORMAT(ServicePrice/16000, '$#,0.00') AS ServicePrice
FROM MsService
ORDER BY ServicePrice ASC

--no 17

--no 18
SELECT TransactionID,
	RoomNumber,
	CONCAT(LEFT(GuestName, CHARINDEX(' ', GuestName) - 1), ' ', LEFT(RIGHT(GuestName, LEN(GuestName) - CHARINDEX(' ', GuestName)), 1)) AS 'Customer Name',
	CheckInDate,
	CheckOutDate
FROM TransactionHeader
JOIN MsGuest ON TransactionHeader.GuestID = MsGuest.GuestID
WHERE CheckInDate >= DATEADD(MONTH, -9, GETDATE())
--no 19
SELECT REPLACE('T' + UPPER(SUBSTRING(StaffName, (LEN(StaffName) / 2) + 1, 1)), 'T', '') AS 'StaffID',
	StaffName,
	StaffGender
FROM MsStaff
WHERE StaffGender LIKE 'Female' AND StaffName LIKE '%a%'

--no 20
SELECT TransactionHeader.TransactionID,
	TransactionHeader.GuestID,
	CONCAT(DATEDIFF(DAY, CheckInDate, CheckOutDate), ' days') AS 'Length Of Stay',
	CONCAT(LateCheckOutInHours, ' hours') AS 'Late Check Out Hours',
	FORMAT(Quantity*ServicePrice ,'Rp#,0.00') AS 'Service Subtotal',
	FORMAT(DATEDIFF(DAY, CheckInDate, CheckOutDate)*RoomTypePrice, 'Rp#,0.00') AS 'Room Subtotal',
	CONCAT(Discount, '%') AS 'Discount',
	FORMAT((Quantity*ServicePrice) + (DATEDIFF(DAY, CheckInDate, CheckOutDate)*RoomTypePrice), 'Rp#,0.00') AS 'Total Price'
FROM TransactionHeader
JOIN TransactionDetail ON TransactionHeader.TransactionID = TransactionDetail.TransactionID
JOIN MsService ON TransactionDetail.ServiceID = MsService.ServiceID
JOIN MsRoom ON TransactionHeader.RoomNumber = MsRoom.RoomNumber
JOIN MsRoomType ON MsRoom.RoomTypeID = MsRoomType.RoomTypeID
