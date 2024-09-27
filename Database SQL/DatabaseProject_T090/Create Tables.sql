CREATE DATABASE DafflesHotel3

USE DafflesHotel3

-- create Tables
CREATE TABLE MsStaff (
    StaffID CHAR(5) PRIMARY KEY,
    StaffName VARCHAR(50),
    StaffGender VARCHAR(10) CHECK (StaffGender IN ('Male', 'Female'))
);

CREATE TABLE MsGuest (
    GuestID CHAR(5) PRIMARY KEY,
    GuestName VARCHAR(50) CHECK (GuestName LIKE '% %'),
    GuestAddress VARCHAR(100),
    GuestPhone VARCHAR(15),
    GuestEmail VARCHAR(50),
    GuestGender VARCHAR(10) CHECK (GuestGender IN ('Male', 'Female'))
);

CREATE TABLE MsPaymentMethod (
    PaymentMethodID CHAR(5) PRIMARY KEY,
    PaymentMethodName VARCHAR(255) CHECK (PaymentMethodName IN ('Cash', 'Debit Card', 'Credit Card'))
);

CREATE TABLE MsRoomType (
    RoomTypeID CHAR(5) PRIMARY KEY,
    RoomTypeName VARCHAR(25),
    RoomTypePrice INTEGER CHECK (RoomTypePrice >= 1000000 AND RoomTypePrice <= 5000000)
);

CREATE TABLE MsRoom (
    RoomNumber CHAR(5) PRIMARY KEY,
    RoomTypeID CHAR(5),
    FOREIGN KEY (RoomTypeID) REFERENCES MsRoomType(RoomTypeID)
);

CREATE TABLE MsService (
    ServiceID CHAR(5) PRIMARY KEY,
    ServiceName VARCHAR(50),
    ServicePrice INTEGER CHECK (ServicePrice >= 35000 AND ServicePrice <= 1000000),
    ServiceDate DATE
);

CREATE TABLE MsHotel (
    HotelID CHAR(5) PRIMARY KEY,
    HotelAddress VARCHAR(100),
    HotelPhone VARCHAR(20) CHECK (HotelPhone LIKE '(021)%'),
    HotelEmail VARCHAR(50)
);

CREATE TABLE TransactionHeader (
    TransactionID CHAR(5) PRIMARY KEY,
    PaymentMethodID CHAR(5),
    RoomNumber CHAR(5),
    HotelID CHAR(5),
    GuestID CHAR(5),
	StaffID CHAR(5),
    CheckInDate DATE,
    CheckOutDate DATE,
    LateCheckOutInHours INTEGER,
    Discount INTEGER,
	FOREIGN KEY (StaffID) REFERENCES MsStaff(StaffID),
    FOREIGN KEY (PaymentMethodID) REFERENCES MsPaymentMethod(PaymentMethodID),
    FOREIGN KEY (RoomNumber) REFERENCES MsRoom(RoomNumber),
    FOREIGN KEY (HotelID) REFERENCES MsHotel(HotelID),
    FOREIGN KEY (GuestID) REFERENCES MsGuest(GuestID)
);

CREATE TABLE TransactionDetail (
    ServiceID CHAR(5),
    TransactionID CHAR(5),
    Quantity INTEGER,
    PRIMARY KEY (ServiceID, TransactionID),
    FOREIGN KEY (ServiceID) REFERENCES MsService(ServiceID),
    FOREIGN KEY (TransactionID) REFERENCES TransactionHeader(TransactionID)
);