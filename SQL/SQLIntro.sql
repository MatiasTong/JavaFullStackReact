-- Flavors of primary keys (surrogate, natural, compound)
-- surrogate key example: ObjectId  
-- relationships:
-- one to one
-- one to many
-- many to many involves a bridge table
-- the main idea behind normalization is to reduce redundancy (1st, 2nd...5th Normal Forms)
-- back up database before doing drop database!!
-- SQL table and field names uses SnakeCase
show databases;
create database if not exists Test;
use Test;

-- drop database test
-- drop table ""

create table Person(
PersonId int(11) primary key auto_increment,
FirstName varchar(20),
LastName varchar(40),
DOB date
);


