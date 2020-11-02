package bank.resources;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bank.resources.ConnectionUtility;
import bank.users.User;
import bank.UserInterface.*;

public class UserAuthenticationDAO {
	
	private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
	String Username;
	String Password;
	User CurrentUser;
	
	public UserAuthenticationDAO(String username, String password) {
		this.Username = username;
		this.Password = password;
	}
	// method for logging in to the system
	public boolean UserAuthentication() {
		// get a connection
		Connection conn = cu.getConnection();
		// authentication process starts
		System.out.println("\n*****Authentication*****");		
		System.out.println("Username: " + this.Username + "\nPassword: "+ this.Password);
		// tries the SQL query
		try {
			Statement statementObject = conn.createStatement();
			
			String authString = "select * " +
								"from \"BankApplication\".UserAuth " +
								"where \"Username\" = \'" + this.Username + "\'";
			/* Blocked out code is for testing SQL
			 *  Queries
			 */
			
//			String queryString = "select * "+ 
//								 "from \"BankApplication\".UserAuth " + 
//								 "where \"Username\" = \'Customer1\'";
//			System.out.println("\n****SQL Queries****");
//			System.out.println(" User Input Query: " + authString);
//			System.out.println("Working SQL Query: " + queryString + "\n");
				
			//ResultSet results = statementObject.executeQuery(queryString);
			
			// calling first() or next() will take us to the first row
			
			ResultSet results = statementObject.executeQuery(authString);	
			User TestUser = new User();
			while(results.next()) {			 
				TestUser.setKeyID(results.getInt("KeyID"));
				TestUser.setUsername(results.getString("Username"));
				TestUser.setPassword(results.getString("Password"));
				TestUser.setAccountType(results.getBoolean("AccountType"));
			}
			// tests user-input username and password against database version 
			if (this.UserAuthentication(TestUser, this.Username, this.Password) == true) {
				this.CurrentUser = TestUser;
				System.out.println("****Login successful****\n" );
				System.out.println(CurrentUser.showUser());
				return true;
			}
			else {
				System.out.println("****Incorrect Username or Password, Login Failed****\n");
				return false;
			}
				
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception e");
			return false;
		}	
	}
	
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
	// method for creating a new account
	public UserAuthenticationDAO(String Username, String Password, boolean AccountType) {
		
	}
	
}
