/*******************************************************************************
   BankApplication Database
   Script: DatabaseScript.sql
   Description: Creates and populates the Bank App database.
   DB Server: PostgreSql
   Author: Jerome Zhang
********************************************************************************/

/**************
** Create Tables
***************/

set schema 'BankApplication';

drop table if exists Employees;
drop table if exists BankAccounts;
drop table if exists Customers;
drop table if exists UserAuth;


create table UserAuth
(
	"KeyID" serial primary key,	
	"Username" varchar(25) not null,
	"Password" varchar(25) not null,
	"AccountType" boolean not null  -- If true, account type is customer. If false employee type is employee
);

create table Employees
(
	"KeyID" serial primary key,	
	"FirstName" varchar(40) not null,
	"LastName" varchar(40) not null,
	"Email" varchar(60) not null,
	"Address" varchar(100),
	"DOB" timestamp,
	foreign key ("KeyID")
		references UserAuth ("KeyID")
);

create table Customers
(
	"KeyID" serial primary key,	
	"FirstName" varchar(40) not null,
	"LastName" varchar(40) not null,
	"Email" varchar(60) not null,
	"Address" varchar(100),
	"DOB" timestamp,
	foreign key ("KeyID")
		references UserAuth ("KeyID")
);

create table BankAccounts
(
	"KeyID" serial primary key,	
	"AccountID" varchar(16) not null,
	"RoutingID" varchar(16) not null,
	"Balance" numeric(10,2) not null,
	"Approval" boolean,
	foreign key ("KeyID")
		references Customers ("KeyID")
);



/**************
** Populate Tables
***************/

-- Employees
insert into UserAuth ("Username","Password", "AccountType") values ('Employee1', 'password', false);
insert into Employees ("KeyID", "FirstName", "LastName", "Email", "Address","DOB") values (1, 'John', 'Doe', 'John.Doe@BankApp.com','100 Cool Lane', '03/23/1968');
insert into UserAuth ("Username","Password", "AccountType") values ('Employee2', 'password', false);
insert into Employees ("KeyID", "FirstName", "LastName", "Email", "Address","DOB") values (2, 'Jon', 'Roe', 'Jon.Roe@BankApp.com','101 Cool Lane','04/23/1988');
insert into UserAuth ("Username","Password", "AccountType") values ('Employee3', 'password', false);
insert into Employees ("KeyID", "FirstName", "LastName", "Email", "Address","DOB") values (3, 'Jane', 'Doe', 'Jane.Doe@BankApp.com','102 Cool Lane', '06/14/1989');
insert into UserAuth ("Username","Password", "AccountType") values ('Employee4', 'password', false);
insert into Employees ("KeyID", "FirstName", "LastName", "Email", "Address","DOB") values (4, 'Janice', 'Rogriguez', 'Janice.Rodriguez@BankApp.com','104 Awesome Lane','02/14/1998');

-- testing
select * from UserAuth;
select * from Employees;


-- Customers
insert into UserAuth ("Username","Password", "AccountType") values ('Customer1', 'password', true);
insert into Customers ("KeyID", "FirstName", "LastName", "Email", "Address","DOB") values (5, 'Maurice', 'Johnson', 'MauriceJohnson@google.com','100 Cool Street', '03/23/1968');
insert into UserAuth ("Username","Password", "AccountType") values ('Customer2', 'password', true);
insert into Customers ("KeyID", "FirstName", "LastName", "Email", "Address","DOB") values (6, 'Hope', 'Marshall', 'hope_Marshall@yahoo.com','101 Cool Avenue','08/23/1978');
insert into UserAuth ("Username","Password", "AccountType") values ('Customer3', 'password', true);
insert into Customers ("KeyID", "FirstName", "LastName", "Email", "Address","DOB") values (7, 'Marissa', 'Lawson', 'marissa_lawson@gmail.com','104 Pool Avenue', '05/14/1989');
insert into UserAuth ("Username","Password", "AccountType") values ('Customer4', 'password', true);
insert into Customers ("KeyID", "FirstName", "LastName", "Email", "Address","DOB") values (8, 'Janet', 'Mason', 'JanetMason@yahoo.com','104 Awesome Drive','02/17/1995');

-- testing
select * from UserAuth;
select * from Customers;

select * from "BankApplication".userauth where "Username" = 'Customer1';

select * from "BankApplication".userauth u  where "KeyID" = 1;
