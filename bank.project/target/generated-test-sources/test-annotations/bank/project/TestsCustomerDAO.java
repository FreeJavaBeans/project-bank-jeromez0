package bank.project;

import static org.junit.Assert.*;

import org.junit.Test;

import bank.models.User;
import bank.resources.CustomerDAO;
import bank.resources.EmployeeDAO;

public class TestsCustomerDAO {
	
	User currentUser = new User(8,"customer4","password",true);
	
	CustomerDAO Customer = new CustomerDAO(8, currentUser);
	
	// testing the approve accounts functionality for employee users
	@Test
	public void test1() {
		assertEquals(Customer.ApplyBankAccount(50000),true);
	}
	@Test
	public void test2() {
		assertEquals(Customer.ApplyBankAccount(-50000),false);
	}
	@Test
	public void test3() {
		assertEquals(Customer.ApplyBankAccount(0000.000),true);
	}
	
}
