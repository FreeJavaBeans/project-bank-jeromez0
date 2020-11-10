package bank.project;

import static org.junit.Assert.*;

import org.junit.Test;

import bank.models.User;
import bank.resources.CustomerDAO;
import bank.resources.EmployeeDAO;

public class TestsCustomerDAO {
	
	User currentUser = new User(100,"testuser","password",true);
	
	CustomerDAO Customer = new CustomerDAO(100, currentUser);
	
	/*
	 * Testing apply for bank account functionality
	 */
	
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
	
	/*
	 * public boolean PostMoneyTransfer(int KeyID, int SenderAccountID, int RecipientAccountID, double Amount)
	 * testing the money transfer functionality
	 */
	
	// Test case 4: test user should be able to transfer $1000 
	@Test
	public void test4() {
		assertEquals(Customer.PostMoneyTransfer(100, 1000000000, 1000000001, 1000),true);
	}
	
	// Test case 5: test user should not be able to transfer $-1000 
	@Test
	public void test5() {
		assertEquals(Customer.PostMoneyTransfer(100, 1000000000, 1000000001, -1000),false);
	}
	
	// Test case 6: test user should not be able to transfer an amount over the value of the account
	@Test
	public void test6() {
		assertEquals(Customer.PostMoneyTransfer(100, 1000000000, 1000000001,100000000.25),false);
	}
	
}
