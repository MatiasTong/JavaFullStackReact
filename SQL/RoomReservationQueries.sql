Use HotelReservations;
-- 1. Write a query that returns a list of reservations that end in July 2023, including the 
-- name of the guest, the room number(s), and the reservation dates.
SELECT
	Guest.FirstName,
    Guest.LastName,
    RoomReservation.RoomNumber,
    Reservation.CheckIn,
    Reservation.CheckOut
FROM Reservation
INNER JOIN RoomReservation ON Reservation.ReservationID = RoomReservation.ReservationID
INNER JOIN Guest ON Guest.GuestID = Reservation.GuestID
WHERE Reservation.CheckOut BETWEEN '2023-07-01' AND '2023-07-31';

/* # FirstName, LastName, RoomNumber, CheckIn, CheckOut
Matias, Tong, 205, 2023-06-28, 2023-07-02
Walter, Holaway, 204, 2023-07-13, 2023-07-14
Wilfred, Vise, 401, 2023-07-18, 2023-07-21
Bettyann, Seery, 303, 2023-07-28, 2023-07-29
 */

-- 2. Write a query that returns a list of all reservations for rooms with a jacuzzi, 
-- displaying the guest's name, the room number, and the dates of the reservation.
SELECT 
	Guest.GuestID,
	Guest.FirstName,
    Guest.LastName,
    RoomReservation.RoomNumber,
    Reservation.CheckIn,
    Reservation.CheckOut,
    Amenity.Amenity
FROM Reservation
INNER JOIN RoomReservation ON Reservation.ReservationID = RoomReservation.ReservationID
INNER JOIN Guest ON Guest.GuestID = Reservation.GuestID
INNER JOIN RoomAmenity ON RoomAmenity.RoomNumber = RoomReservation.RoomNumber
INNER JOIN Amenity ON Amenity.AmenityID = RoomAmenity.AmenityID
WHERE Amenity.Amenity= 'Jacuzzi' ;

/* # GuestID, FirstName, LastName, RoomNumber, CheckIn, CheckOut, Amenity
4, Karie, Yang, 201, 2023-03-06, 2023-03-07, Jacuzzi
2, Bettyann, Seery, 203, 2023-02-05, 2023-02-10, Jacuzzi
4, Karie, Yang, 203, 2023-09-13, 2023-09-15, Jacuzzi
1, Matias, Tong, 205, 2023-06-28, 2023-07-02, Jacuzzi
9, Wilfred, Vise, 207, 2023-04-23, 2023-04-24, Jacuzzi
8, Walter, Holaway, 301, 2023-04-09, 2023-04-13, Jacuzzi
12, Mack, Simmer, 301, 2023-11-22, 2023-11-25, Jacuzzi
2, Bettyann, Seery, 303, 2023-07-28, 2023-07-29, Jacuzzi
3, Duane, Cullison, 305, 2023-02-22, 2023-02-24, Jacuzzi
2, Bettyann, Seery, 305, 2023-08-30, 2023-09-01, Jacuzzi
1, Matias, Tong, 307, 2023-03-17, 2023-03-20, Jacuzzi
*/

---------------------------------------------------------------------------------------------
-- 3. Write a query that returns all the rooms reserved for a specific guest, including the 
-- guest's name, the room(s) reserved, the starting date of the reservation, and how many 
-- people were included in the reservation. (Choose a guest's name from the existing data.)
SELECT
    Guest.FirstName,
    Guest.LastName,
    RoomReservation.RoomNumber,
    Reservation.CheckIn,
    SUM(RoomReservation.Adults + RoomReservation.Children) NumberOfPeople
FROM Reservation
INNER JOIN RoomReservation ON Reservation.ReservationID = RoomReservation.ReservationID
INNER JOIN Guest ON Guest.GuestID = Reservation.GuestID
WHERE Guest.GuestID = '12'
GROUP BY RoomReservation.RoomNumber, Reservation.CheckIn;

/*# FirstName, LastName, RoomNumber, CheckIn, NumberOfPeople
Mack, Simmer, 308, 2023-02-02, 1
Mack, Simmer, 208, 2023-09-16, 2
Mack, Simmer, 206, 2023-11-22, 2
Mack, Simmer, 301, 2023-11-22, 4
*/


-- 4. Write a query that returns a list of rooms, reservation ID, and per-room cost for
-- each reservation. The results should include all rooms, whether or not there is a 
-- reservation associated with the room.
SELECT Room.RoomNumber, RoomReservation.ReservationID, RoomReservation.TotalRoomCost 
FROM RoomReservation
RIGHT OUTER JOIN Room ON RoomReservation.RoomNumber = Room.RoomNumber;

/*# RoomNumber, ReservationID, TotalRoomCost
205, 15, 699.96
206, 12, 599.96
206, 23, 449.97
207, 10, 174.99
208, 12, 599.96
208, 20, 149.99
305, 4, 349.98
305, 19, 349.98
306, , 
307, 1, 524.97
308, 2, 299.98
201, 5, 199.99
202, 7, 349.98
203, 3, 999.95
203, 21, 399.98
204, 16, 184.99
301, 9, 799.96
301, 23, 599.97
302, 6, 924.95
302, 25, 699.96
303, 18, 199.99
304, 14, 184.99
401, 11, 1199.97
401, 17, 1259.97
401, 22, 1199.97
402, , 
*/


-- 5. Write a query that returns all the rooms accommodating at least three guests and 
-- that are reserved on any date in April 2023.
SELECT 
Room.RoomNumber,
Reservation.CheckIn,
Reservation.Checkout,
Sum(RoomReservation.Adults + RoomReservation.Children) TotalGuests
FROM RoomReservation
INNER JOIN Room ON RoomReservation.RoomNumber = Room.RoomNumber
INNER JOIN Reservation ON Reservation.ReservationID = RoomReservation.ReservationID
WHERE (Reservation.CheckIn BETWEEN '2023-04-01' AND '2023-04-30')
OR (Reservation.CheckOut BETWEEN '2023-04-01' AND '2023-04-30')
GROUP BY Room.RoomNumber, Reservation.CheckIn, Reservation.Checkout
Having Sum(RoomReservation.Adults + RoomReservation.Children) >= 3;


-- 6. Write a query that returns a list of all guest names and the number of reservations 
-- per guest, sorted starting with the guest with the most reservations and then by the guest's
-- last name.

SELECT 
Guest.FirstName,
Guest.LastName,
Count(Reservation.ReservationID)
FROM Guest
INNER JOIN Reservation ON Guest.GuestID = Reservation.GuestID
GROUP BY Guest.GuestID
ORDER BY Count(Reservation.ReservationID) DESC, Guest.LastName;

/* # FirstName, LastName, Count(Reservation.ReservationID)
Bettyann, Seery, 3
Mack, Simmer, 3
Duane, Cullison, 2
Walter, Holaway, 2
Aurore, Lipton, 2
Maritza, Tilton, 2
Matias, Tong, 2
Wilfred, Vise, 2
Karie, Yang, 2
Zachery, Luechtefeld, 1
Joleen, Tison, 1
*/

-- 7. Write a query that displays the name, address, and phone number of a guest based 
-- on their phone number. (Choose a phone number from the existing data.)

SELECT Guest.FirstName, Guest.LastName, Guest.Street, 
Guest.City, Guest.State, Guest.Zip, Guest.Phone
FROM Guest
WHERE Guest.Phone = "929-391-6458";

/* # FirstName, LastName, Street, City, State, Zip, Phone
Matias, Tong, 14214 26 Ave., Flushing, NY, 11354, 929-391-6458 */
