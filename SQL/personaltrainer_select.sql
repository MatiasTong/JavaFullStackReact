USE PersonalTrainer;

SELECT * 
FROM Exercise;

SELECT *
FROM Client;

SELECT * 
FROM CLIENT 
WHERE City = 'Metairie';

SELECT *
FROM CLIENT 
WHERE ClientId = '818u7faf-7b4b-48a2-bf12-7a26c92de20c';

SELECT *
FROM Goal;

SELECT Name, LevelId
FROM Workout;

SELECT Name, LevelId, Notes
FROM Workout
WHERE LevelId = 2;

SELECT FirstName, LastName, BirthDate
FROM Client
WHERE City IN ('Metairie', 'Kenner', 'Gretna'); -- city is metairie, gretna, or kenner

SELECT FirstName, LastName, BirthDate
FROM Client
WHERE BirthDate BETWEEN '1980-01-01' AND '1989-12-31';

SELECT FirstName, LastName, BirthDate
FROM Client
WHERE BirthDate >= '1980-01-01' 
AND BirthDate <= '1989-12-31';

SELECT *
FROM Login
WHERE EmailAddress LIKE '%.gov';

SELECT *
FROM Login
WHERE EmailAddress NOT LIKE '%.com';

SELECT FirstName, LastName
FROM Client
WHERE BirthDate IS NULL;

SELECT Name
FROM ExerciseCategory
WHERE ParentCategoryId IS NOT NULL;

SELECT *
FROM Workout;

SELECT Name, Notes
FROM  Workout
WHERE LevelId = 3 
AND Notes LIKE '%you%' ;

SELECT FirstName, LastName, City
FROM Client
WHERE City = 'LaPlace' 
AND (LastName LIKE 'L%'
OR LastName LIKE 'M%'
OR LastName LIKE 'N%');

SELECT InvoiceId, Description, Price, Quantity, ServiceDate,
Price * Quantity AS total
FROM InvoiceLineItem
WHERE Price * Quantity BETWEEN 15 AND 25;

SELECT ClientId
FROM Client
WHERE FirstName = 'Estrella' 
AND LastName = 'Bazely';


SELECT * 
FROM Login
WHERE ClientId = (SELECT ClientId
FROM Client
WHERE FirstName = 'Estrella' 
AND LastName = 'Bazely');


SELECT *
FROM Workout
WHERE Name = 'This is Parkour';

SELECT GoalId
FROM WorkoutGoal
WHERE WorkoutId = 12;

SELECT Name
FROM Goal
WHERE GoalId IN (3,8,15);