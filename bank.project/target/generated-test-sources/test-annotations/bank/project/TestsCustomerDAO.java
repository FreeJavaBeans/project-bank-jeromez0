package bank.project;

import static org.junit.Assert.*;

import org.junit.Test;

import bank.models.User;
import bank.resources.CustomerDAO;
import bank.resources.EmployeeDAO;

public class TestsCustomerDAO {
	
	User currentUser = new User(100,"testuser","password",true);
	CustomerDAO Customer = new CustomerDAO(100, currentUser);
		
	// Testing apply for bank account functionality
	// function prototype: 
	// public boolean ApplyBankAccount(double startingBalance);
	
	// Test case 1: test user should be able to apply for a bank account with a positive initial deposit
	@Test
	public void test1() {
		assertEquals(Customer.ApplyBankAccount(50000),true);
	}	
	
	// Test case 2: test user should not be able to apply for a bank account with a negative initial deposit
	@Test
	public void test2() {
		assertEquals(Customer.ApplyBankAccount(-50000),false);
	}
	
	// Test case 3: test user should be able to apply for a bank account with an initial value of 0
	@Test
	public void test3() {
		assertEquals(Customer.ApplyBankAccount(0000.000),true);
	}
	
	// Testing view specific bank account functionality
	// function prototype: 
	// public boolean ViewSpecificBalance(int AccountNum)
	
	// Test case 4: test user should be able to view the account balance that is approved to her 
	@Test
	public void test4() {
		assertEquals(Customer.ViewSpecificBalance(1000000000), true);
	}
	
	// Test case 5: test user should not be able to view account not approved to her
	@Test
	public void test5() {
		assertEquals(Customer.ViewSpecificBalance(555555555), false);
	}
	
	// Test case 6: test user should not be able to view account that does not exist
	@Test
	public void test6() {
		assertEquals(Customer.ViewSpecificBalance(-50), false);
	}
	
	// testing post the money transfer functionality
	// function prototype:
	// public boolean PostMoneyTransfer(int KeyID, int SenderAccountID, int RecipientAccountID, double Amount)
	
	// Test case 7: test user should be able to transfer $1000 
	@Test
	public void test7() {
		assertEquals(Customer.PostMoneyTransfer(100, 1000000000, 1000000001, 1000),true);
	}
	
	// Test case 8: test user should not be able to transfer $-1000 
	@Test
	public void test8() {
		assertEquals(Customer.PostMoneyTransfer(100, 1000000000, 1000000001, -1000),false);
	}
	
	// Test case 9: test user should not be able to transfer an amount over the value of the account
	@Test
	public void test9() {
		assertEquals(Customer.PostMoneyTransfer(100, 1000000000, 1000000001,100000000.25),false);
	}
	
	// Test case 10: test user should not be able to transfer an amount to itself
	@Test
	public void test10() {
		assertEquals(Customer.PostMoneyTransfer(100,1000000000,1000000000,1), false);
	}
	
	// testing the accept money transfer functionality
	// function prototype:
	// public boolean AcceptMoneyTransfer(int transactID);
	
	// Test case 11: test user should be able to accept the money transfer between accounts
	@Test
	public void test11() {
		assertEquals(Customer.AcceptMoneyTransfer(1),true);
	}
	
	// Test case 12: test user should not be able to accept money transfer from a nonexistent transaction ID
	@Test
	public void test12() {
		assertEquals(Customer.AcceptMoneyTransfer(10000),false);
	}
	
	// Test case 13: test user should not be able to accept money transfer from a negative transaction ID
	@Test
	public void test13() {
		assertEquals(Customer.AcceptMoneyTransfer(-30),false);
	}	
}
