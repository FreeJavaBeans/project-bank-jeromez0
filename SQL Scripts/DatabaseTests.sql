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

select * from MoneyTransfers where "RecipientAccountID" = 222222222;



-- money transfers that have been received
select * from MoneyTransfers where "RecipientAccountID" in
	(select "AccountID" from bankaccounts where "KeyID" = 5);	
	
-- money transfers that have been posted
select * from MoneyTransfers where "Approval" = false and "AccountID" in 
	(select "AccountID" from bankaccounts where "KeyID" = 5);

-----
-- accepting a money transfer
-----

--1. gathering information
select "AccountID", "RecipientAccountID", "Amount" from MoneyTransfers where "TransactionID" = 2 and "RecipientAccountID" in
	(select "AccountID" from bankaccounts where "KeyID" = 5 and "Approval" = true);	

--2. making the deposit
Update BankAccounts set "Balance" = 11000.22 where "AccountID" = 555555555;

--3. making the withdrawal
Update BankAccounts set "Balance" = 20000.66 where "AccountID" = 777777777;


--4. updating status of the money transfer                              --?                    --?                            --?
update moneytransfers set "Approval" = true,"DateApproved" = '11/08/2020' where "TransactionID" = 2 and "RecipientAccountID" in
	(select "AccountID" from bankaccounts where "KeyID" = 5);
                                                 --?

select * from moneytransfers;
select * from bankaccounts;
