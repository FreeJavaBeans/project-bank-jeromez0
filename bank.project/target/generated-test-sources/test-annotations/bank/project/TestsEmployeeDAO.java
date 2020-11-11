package bank.project;

import static org.junit.Assert.*;

import org.junit.Test;

import bank.resources.EmployeeDAO;

public class TestsEmployeeDAO {
	
	EmployeeDAO Employee = new EmployeeDAO();
	
	// testing the approve accounts functionality for employee users
	// function prototype: 
	// public boolean approvePendingAccounts(int AccountID);
	
	// Test case 1: Account does not exist so it should account should not be approved
	@Test
	public void test1() {
		assertEquals(Employee.approvePendingAccounts(0), false);
	}
	
	// Test case 2: account does exist so account should be approved
	@Test
	public void test2() {
		assertEquals(Employee.approvePendingAccounts(1000000002),true);
	}
	
	// Test case 3: account should not be approved as value is negative
	@Test
	public void test3()  {
		assertEquals(Employee.approvePendingAccounts(-400000000),false);
	}

	// testing the view customer bankaccounts functionality for employee users
	// function prototype: 
	// public boolean viewCustomerAccounts(int KeyID);
	
	// Test case 4: Employee should be able to view the bankaccounts for testsuer keyId 100
	@Test
	public void test4() {
		assertEquals(Employee.viewCustomerAccounts(100), true);	
	}
	// Test case 5: Employee should not be able to view the bankaccounts for account that does not exist	
	@Test 
	public void test5() {
		assertEquals(Employee.viewCustomerAccounts(10493), false);
	}
	// Test case 6: Employee should not be able to view the bankaccounts for keyID with a negative value
	@Test 
	public void test6() {
		assertEquals(Employee.viewCustomerAccounts(-1), false);
	}
	
}
