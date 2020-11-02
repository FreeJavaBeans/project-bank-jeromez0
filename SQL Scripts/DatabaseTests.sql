-- testing

set schema 'BankApplication';

select * from UserAuth;
select * from Customers;

select * from "BankApplication".userauth where "Username" = 'Customer1';

select * from "BankApplication".userauth u  where "KeyID" = 1;


insert into UserAuth ("Username","Password", "AccountType") 
values ('Customer1', 'password', true);
