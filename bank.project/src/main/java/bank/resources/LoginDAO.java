package bank.resources;

import java.sql.Connection;
import java.sql.Timestamp;

import bank.models.User;
import bank.util.ConnectionUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
	
	private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
	String Username;
	String Password;
	User CurrentUser;
	
	// public constructor method
	public LoginDAO(String username, String password) {
		this.Username = username;
		this.Password = password;
	}
	// method for logging in to the system
	public boolean Login() {
		Connection conn = cu.getConnection();
		String SQLlogin = "select * from \"BankApplication\".UserAuth where \"Username\" = ?";
		try {	
			PreparedStatement prepStatement = conn.prepareStatement(SQLlogin);
			prepStatement.setString(1, this.Username);		
			ResultSet results = prepStatement.executeQuery();				
			User TestUser = new User(); // The TestUser is a temporary object that stores the data for the queried username
			while(results.next()) {			 
				TestUser.SetUser(results, TestUser);
			}
			// tests user-input username and password against database username and password
			if (this.UserAuthentication(TestUser, this.Username, this.Password) == true) {
				this.CurrentUser = TestUser;
				return true;
			}
			else {
				return false;
			}		
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}	
	// method for creating a new user account
	public boolean NewUserAccount() {
		Connection conn = cu.getConnection();
		String SQLCreateUser = "INSERT INTO \"BankApplication\".UserAuth (\"Username\", \"Password\", \"AccountType\") VALUES (?,?,true);";
		try {		
			PreparedStatement prepStatement = conn.prepareStatement(SQLCreateUser);
			prepStatement.setString(1, this.Username);
			prepStatement.setString(2, this.Password);
			prepStatement.execute();
		}catch (SQLException e) {
			return false;
		}
		return true;
	}
	// method for creating a new customer account that is linked by KeyID to the user account
	public boolean NewCustomerAccount(String firstname, String lastname, String email, String address, Timestamp ts) {
		Connection conn = cu.getConnection();
		String SQLCreateAccount = "INSERT INTO \"BankApplication\".Customers (\"KeyID\",\"FirstName\", \"LastName\", \"Email\", \"Address\",\"DateCreated\") "+
				  "values (?,?,?,?,?,?)";
		try {		
			PreparedStatement prepStatement = conn.prepareStatement(SQLCreateAccount);
			prepStatement.setInt(1, this.getKey());
			prepStatement.setString(2, firstname);
			prepStatement.setString(3, lastname);
			prepStatement.setString(4,  email);
			prepStatement.setString(5,  address);
			prepStatement.setTimestamp(6, ts);
			prepStatement.execute();
		}catch (SQLException e) {
			return false;
		}
		return true;
	}
	// method to get the correct KeyID for the new customer account
	private int getKey() {
		Connection conn = cu.getConnection();
		String SQLgetKeyID = "select \"KeyID\" from \"BankApplication\".UserAuth where \"Username\" = ?;";
		try {			
			PreparedStatement prepStatement = conn.prepareStatement(SQLgetKeyID);
			prepStatement.setString(1, this.Username);
			ResultSet results = prepStatement.executeQuery();
			while(results.next()) {			 
				return (results.getInt("KeyID"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception");
			return 0;
		}
		return 0;
	}
	// this method tests if the user inputted username and password match identically with the queried username and password 
	private boolean UserAuthentication(User TestUser, String username, String password) {
		try {
			if (TestUser.getUsername().equals(username) && TestUser.getPassword().equals(password)) {
				return true;
			}
			else {
				return false;
			}
		}catch (NullPointerException e) {
			return false;
		}
	}
	
	// getters and setter methods 
	
	public ConnectionUtility getCu() {
		return cu;
	}
	public void setCu(ConnectionUtility cu) {
		this.cu = cu;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public User getCurrentUser() {
		return CurrentUser;
	}
	public void setCurrentUser(User currentUser) {
		CurrentUser = currentUser;
	}
}
