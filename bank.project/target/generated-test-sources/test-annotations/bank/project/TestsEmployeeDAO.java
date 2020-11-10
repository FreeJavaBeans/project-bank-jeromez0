package bank.project;

import static org.junit.Assert.*;

import org.junit.Test;

import bank.resources.EmployeeDAO;

public class TestsEmployeeDAO {
	
	EmployeeDAO Employee = new EmployeeDAO();
	
	// testing the approve accounts functionality for employee users
	
	// Account does not exist so it should account should not be approved
	@Test
	public void test1() {
		assertEquals(Employee.approvePendingAccounts(0), false);
	}
	
	// account does exist so account should be approved
	@Test
	public void test2() {
		assertEquals(Employee.approvePendingAccounts(1000000002),true);
	}
}
