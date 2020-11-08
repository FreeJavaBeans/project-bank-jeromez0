-- testing

set schema 'BankApplication';

select * from UserAuth;
select * from Customers;

select * from "BankApplication".userauth where "Username" = 'Customer1';

select * from "BankApplication".userauth u  where "KeyID" = 1;


insert into UserAuth ("Username","Password", "AccountType") 
values ('Customer1', 'password', true);


select * from "BankApplication".customers where "KeyID" = 9;

select * from "BankApplication".BankAccounts where "KeyID" = 5 and "AccountID" = 466033548;

select * from MoneyTransfers where "RecipientAccountID" = 11111111;