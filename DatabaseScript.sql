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

drop table if exists "Employees";
drop table if exists "BankAccounts";
drop table if exists "Customers";
drop table if exists "UserAuth";


create table "UserAuth" 
(
	"KeyID" serial primary key,	
	"Username" varchar(25) not null,
	"Password" varchar(25) not null,
	"Account Type" boolean not null,  -- If true, account type is customer. If false employee type is employee
);

create table "Employees"
(
	"KeyID" serial primary key,	
	"FirstName" varchar(40) not null,
	"LastName" varchar(40) not null,
	"Email" varchar(60) not null,
	"Address" varchar(100),
	"DOB" timestamp,
	foreign key ("KeyID")
		references "UserAuth" ("KeyID")
);

create table "Customers"
(
	"KeyID" serial primary key,	
	"FirstName" varchar(40) not null,
	"LastName" varchar(40) not null,
	"Email" varchar(60) not null,
	"Address" varchar(100),
	"DOB" timestamp,
	foreign key ("KeyID")
		references "UserAuth" ("KeyID")
);

create table "BankAccounts"
(
	"KeyID" serial primary key,	
	"AccountID" varchar(16) not null,
	"RoutingID" varchar(16) not null,
	"Balance" numeric(10,2) not null,
	"Approval" boolean,
	foreign key ("KeyID")
		references "Customers" ("KeyID")
);



/**************
** Populate Tables
***************/




