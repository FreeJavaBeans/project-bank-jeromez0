package bank.project;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;

import bank.resources.LoginDAO;
import bank.util.ConnectionUtility;

public class TestsLoginDAO {
	
	private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
	
	// Testing primary method of the banking application to log a user in
	// constructor prototype:
	// LoginDAO("username", "password")
	
	LoginDAO LoginTest1 = new LoginDAO("testuser", "password");
	LoginDAO LoginTest2 = new LoginDAO("testuser", "password1");
	
	// Test case 1: User has correct username and password
	@Test
	public void test1() {
		assertEquals(LoginTest1.Login(), true);
	}
	// Test case 2: User has incorrect username and password
	@Test
	public void test2() {
		assertEquals(LoginTest2.Login(), false);
	}
	
	// Testing method for creating a new "User" account
	// function prototype: 
	// public boolean NewUserAccount(String Username, String Password);
	
	LoginDAO LoginTest3 = new LoginDAO("testuser100", "23hliasdhfl4rh8;asd");
	
	// Test case 3: Username already exists so new account should not be creatable
	@Test
	public void test3() {
		assertEquals(LoginTest1.NewUserAccount(), false);
	}
	
	// Test case 4: Username does not exist so new account should be creatable
	@Test 
	public void test4() {
		Connection conn = cu.getConnection();
		String SQLDelete1 = "Delete from \"BankApplication\".Customers where \"Address\" = ?";
		String SQLDelete = "Delete from \"BankApplication\".UserAuth where \"Username\" = ?";
		try {
			PreparedStatement prepareStatement = conn.prepareStatement(SQLDelete1);
			prepareStatement.setString(1,  "200 test street");
			prepareStatement.execute();
			
			PreparedStatement prepareStatement1 = conn.prepareStatement(SQLDelete);
			prepareStatement1.setString(1, "testuser100");
			prepareStatement1.execute();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(LoginTest3.NewUserAccount(), true);
	}

	// Testing method for creating a new "User" account
	// function prototype: 
	// public boolean NewCustomerAccount(String firstname, String lastname, String email, String address, Timestamp ts)
	
	// Test case 5: Creating new customer account details for testuser100 should work as long as fields filled 
	@Test
	public void test5() {
		Date date = new Date();  
		Timestamp ts=new Timestamp(date.getTime());  
		assertEquals(LoginTest3.NewCustomerAccount("Test", "user", "test@user.com", "200 test street", ts), true);
	}	
}
