DROP DATABASE IF EXISTS HotelReservations;
CREATE DATABASE HotelReservations;
USE HotelReservations;

CREATE TABLE  Guest(
	GuestID int primary key auto_increment,
	FirstName varchar(30) not null,
	LastName varchar(30) not null,
    Street varchar(100) not null,
	City varchar(50) not null,
    State char(2) not null,
	Zip char(5) not null,
	Phone varchar(17) not null
);

CREATE TABLE RoomType(
	RoomTypeID int primary key auto_increment,
    RoomType varchar(30) not null,
    StanOcc int not null,
    MaxOcc int not null,
    ExtraPersonCost decimal(6,2)
);

CREATE TABLE  Room(
	RoomNumber int primary key auto_increment,
    AccessibleADA boolean not null,
	BasePrice decimal(8,2) not null,
    RoomTypeID int not null,
    FOREIGN KEY FK_Room_RoomType(RoomTypeID) REFERENCES RoomType(RoomTypeID)
);

CREATE TABLE  Amenity(
	AmenityID int primary key auto_increment,
	Amenity varchar(30),
    AmenityPrice decimal(8,2)
);


CREATE TABLE  RoomAmenity(
     RoomNumber int not null,
     AmenityID int not null,
     primary key (RoomNumber, AmenityID),
     foreign key FK_RoomAmenity_Room (RoomNumber) references Room(RoomNumber),
     foreign key FK_RoomAmenity_Amenity (AmenityID) references Amenity(AmenityID)
);

CREATE TABLE  Reservation(
	ReservationID int primary key auto_increment,
	GuestID int not null,
	CheckIn date not null,
	CheckOut date not null,
	TotalCost decimal(8,2) not null,
    foreign key FK_Reservation_Guest(GuestID) references Guest(GuestID)
);

CREATE TABLE  RoomReservation(
	RoomNumber int not null,
	ReservationID int,
    Adults int not null,
	Children int not null,
    TotalRoomCost decimal(8,2) not null,
	primary key (RoomNumber, ReservationID),
	foreign key FK_RoomReservation_Room (RoomNumber) references Room(RoomNumber),
	foreign key FK_RoomReservation_Reservation (ReservationID) references Reservation(ReservationID)
);