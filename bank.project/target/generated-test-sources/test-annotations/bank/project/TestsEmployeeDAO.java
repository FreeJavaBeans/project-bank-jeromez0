package bank.project;

import static org.junit.Assert.*;

import org.junit.Test;

import bank.resources.EmployeeDAO;

public class TestsEmployeeDAO {
	
	EmployeeDAO Employee = new EmployeeDAO();
	
	// testing the approve accounts functionality for employee users
	@Test
	public void test1() {
		assertEquals(Employee.approvePendingAccounts(0), false);
	}
	@Test
	public void test2() {
		assertEquals(Employee.approvePendingAccounts(555555555),true);
	}
}
