package bank.project;

import static org.junit.Assert.*;

import org.junit.Test;

import bank.resources.LoginDAO;

public class TestsLoginDAO {
	
	// primary method of the banking application to log a user in
	LoginDAO LoginTest1 = new LoginDAO("testuser", "password");
	
	@Test
	public void test1() {
		assertEquals(LoginTest1.Login(), false);
	}

	LoginDAO LoginTest2 = new LoginDAO("customer1", "password");
	
	@Test
	public void test2() {
		assertEquals(LoginTest2.Login(), true);
	}
	
	LoginDAO LoginTest3 = new LoginDAO("customer1", "passw0rd");
	
	@Test
	public void test3() {
		assertEquals(LoginTest3.Login(), false);
	}
	
	
}
