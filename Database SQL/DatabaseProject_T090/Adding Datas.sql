USE DafflesHotel

--Adding datas to fill in the tables
INSERT INTO MsHotel (HotelID, HotelAddress, HotelPhone, HotelEmail) VALUES
('HO001', 'Merdeka Street No. 67, Surabaya, Indonesia 60271', '(021)12345678', 'dafflesHotelIndonesia@gmail.com'),
('HO002',  'Pahlawan Street No. 55, Surabaya, Indonesia 60222', '(021)87654321', 'grandhotel@gmail.com');

INSERT INTO MsGuest (GuestID, GuestName, GuestAddress, GuestPhone, GuestEmail, GuestGender) VALUES
('CU001', 'Daniel Kurniawan', 'Anggrek Street No. 123, Jakarta, Indonesia 19283', '0891738495', 'daniel123@gmail.com', 'Male'),
('CU002', 'Alice Johnson', 'Orchid Street No. 50, Bali, Indonesia 80231', '0812345678', 'alicejohnson@gmail.com', 'Female');

INSERT INTO MsRoomType (RoomTypeID, RoomTypeName, RoomTypePrice) VALUES
('RT001', 'Deluxe', 1000000),
('RT002', 'Suite', 2000000),
('RT003', 'Presidential', 5000000);

INSERT INTO MsRoom (RoomNumber, RoomTypeID) VALUES
(724, 'RT001'),
(100, 'RT002'),
(103, 'RT003');

INSERT INTO MsService (ServiceID, ServiceName, ServicePrice) VALUES
('SE001', 'Breakfast Buffet', 500000),
('SE002', 'Spa', 750000),
('SE003', 'Airport Shuttle Service', 800000),
('SE004', 'Late Checkout Fee', 150000);

INSERT INTO MsPaymentMethod (PaymentMethodID, PaymentMethodName) VALUES
('PM001', 'Cash'),
('PM002', 'Debit Card'),
('PM003', 'Credit Card');

INSERT INTO MsStaff (StaffID, StaffName, StaffGender) VALUES
('ST001', 'Isabella Ramaputri', 'Female'),
('ST002', 'John Doe', 'Male');

INSERT INTO TransactionHeader (TransactionID, HotelID, GuestID, CheckInDate, CheckOutDate, LateCheckOutInHours, Discount, PaymentMethodID, StaffID, RoomNumber) VALUES
('TR001', 'HO001', 'CU001', '2024-05-05', '2024-05-10', 3, 45, 'PM001', 'ST001', 724),
('TR002', 'HO002', 'CU002', '2024-05-06', '2024-05-11', 2, 10, 'PM002', 'ST002', 100);

INSERT INTO TransactionDetail (TransactionID, ServiceID, Quantity) VALUES
('TR001', 'SE001', 2),
('TR001', 'SE002', 1),
('TR001', 'SE003', 1),
('TR001', 'SE004', 3),
('TR002', 'SE001', 1),
('TR002', 'SE002', 2),
('TR002', 'SE003', 1),
('TR002', 'SE004', 2);

--Simulate transaction process
