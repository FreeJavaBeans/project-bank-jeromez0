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
drop table if exists MoneyTransfers;
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
	"DateCreated" timestamp,
	foreign key ("KeyID")
		references UserAuth ("KeyID")
);
 
create table Customers
(
	"KeyID" serial primary key,	
	"FirstName" varchar(40) not null,
	"LastName" varchar(40) not null,
	"Email" varchar(60) not null,
	"Address" varchar(100) not null,
	"DateCreated" timestamp not null,
	foreign key ("KeyID")
		references UserAuth ("KeyID")
);

create table BankAccounts
(	
	"KeyID" int not null,
	"AccountID" int not null primary key,
	"RoutingID" varchar(16) not null,
	"Balance" numeric(10,2) not null,
	"Approval" boolean,
	"DateCreated" timestamp not null,
	foreign key ("KeyID")
		references Customers ("KeyID")
);

create table MoneyTransfers
(
	"KeyID" int not null,
	"TransactionID" serial primary key,
	"AccountID" int not null,
	"RecipientAccountID" int not null,
	"Amount" numeric(10,2) not null,
	"Approval" boolean not null,
	"DateCreated" timestamp not null,
	"DateApproved" timestamp,
	foreign key ("KeyID")
		references Customers ("KeyID"),
	foreign key ("AccountID")
		references BankAccounts ("AccountID")
);

create unique index Unique_Emails on Customers("Email");
create unique index Unique_Account_ID on BankAccounts("AccountID");
create unique index Unique_Usernames on UserAuth("Username");

/**************
** Populate Tables
***************/

-- Employees
insert into UserAuth ("Username","Password", "AccountType") values ('Employee1', 'password', false);
insert into Employees ("KeyID", "FirstName", "LastName", "Email", "Address","DateCreated") values (1, 'John', 'Doe', 'John.Doe@BankApp.com','100 Cool Lane', '03/23/1968');
insert into UserAuth ("Username","Password", "AccountType") values ('Employee2', 'password', false);
insert into Employees ("KeyID", "FirstName", "LastName", "Email", "Address","DateCreated") values (2, 'Jon', 'Roe', 'Jon.Roe@BankApp.com','101 Cool Lane','04/23/1988');
insert into UserAuth ("Username","Password", "AccountType") values ('Employee3', 'password', false);
insert into Employees ("KeyID", "FirstName", "LastName", "Email", "Address","DateCreated") values (3, 'Jane', 'Doe', 'Jane.Doe@BankApp.com','102 Cool Lane', '06/14/1989');
insert into UserAuth ("Username","Password", "AccountType") values ('Employee4', 'password', false);
insert into Employees ("KeyID", "FirstName", "LastName", "Email", "Address","DateCreated") values (4, 'Janice', 'Rogriguez', 'Janice.Rodriguez@BankApp.com','104 Awesome Lane','02/14/1998');

-- Customers
insert into UserAuth ("Username","Password", "AccountType") values ('Customer1', 'password', true);
insert into Customers ("KeyID", "FirstName", "LastName", "Email", "Address","DateCreated") values (5, 'Maurice', 'Johnson', 'MauriceJohnson@google.com','100 Cool Street', '03/23/1968');
insert into UserAuth ("Username","Password", "AccountType") values ('Customer2', 'password', true);
insert into Customers ("KeyID", "FirstName", "LastName", "Email", "Address","DateCreated") values (6, 'Hope', 'Marshall', 'hope_Marshall@yahoo.com','101 Cool Avenue','08/23/1978');
insert into UserAuth ("Username","Password", "AccountType") values ('Customer3', 'password', true);
insert into Customers ("KeyID", "FirstName", "LastName", "Email", "Address","DateCreated") values (7, 'Marissa', 'Lawson', 'marissa_lawson@gmail.com','104 Pool Avenue', '05/14/1989');
insert into UserAuth ("Username","Password", "AccountType") values ('Customer4', 'password', true);
insert into Customers ("KeyID", "FirstName", "LastName", "Email", "Address","DateCreated") values (8, 'Janet', 'Mason', 'JanetMason@yahoo.com','104 Awesome Drive','02/17/1995');

--Bank Accounts
insert into BankAccounts ("KeyID", "AccountID", "RoutingID", "Balance", "Approval", "DateCreated") values (5, 555555555, 123456789, 10000.99, true, '09/15/2020');
insert into BankAccounts ("KeyID", "AccountID", "RoutingID", "Balance", "Approval", "DateCreated") values (6, 666666666, 123456789, 20000.99, true, '11/07/2020');
insert into BankAccounts ("KeyID", "AccountID", "RoutingID", "Balance", "Approval", "DateCreated") values (7, 777777777, 123456789, 30000.99, true, '11/07/2020');
insert into BankAccounts ("KeyID", "AccountID", "RoutingID", "Balance", "Approval", "DateCreated") values (8, 888888888, 123456789, 40000.99, true, '11/07/2020');

--Money Transfers
insert into MoneyTransfers("KeyID", "AccountID", "RecipientAccountID", "Amount", "Approval", "DateCreated") values (5, 555555555, 222222222, 1000.23, false, '11/08/2020');


-- testing
select * from UserAuth;
select * from Employees;
select * from Customers;
select * from BankAccounts;
select * from MoneyTransfers;



