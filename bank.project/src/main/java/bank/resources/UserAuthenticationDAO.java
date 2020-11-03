package bank.resources;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bank.users.User;

public class UserAuthenticationDAO {
	
	private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
	String Username;
	String Password;
	User CurrentUser;
	
	/* SQL Queries 
	*/ 
	String QueryLogin = "select * from \"BankApplication\".UserAuth where \"Username\" = ?;";
	String CreateUser = "insert into \"BankApplication\".UserAuth (\"Username\", \"Password\", \"AccountType\") values (?,?,true);";
	String getKeyID = "select \"KeyID\" from \"BankApplication\".UserAuth where \"Username\" = ?;";
	String EnterCustomerDetails = "insert into \"BankApplication\".Customers (\"KeyID\", \"FirstName\", \"LastName\", \"Email\", \"Address\",\"DateCreated\") "+
								  "values (?,?,?,?,?,?)";
	/*
	*/
	
	// public constructor method
	public UserAuthenticationDAO(String username, String password) {
		this.Username = username;
		this.Password = password;
	}
	// method for logging in to the system
	public boolean UserAuthentication() {
		// get a connection
		Connection conn = cu.getConnection();
		// authentication process starts
		System.out.println("\n*****Authenticating*****");
		System.out.println("........................");
		System.out.println("........................");
		System.out.println("........................");
		// tries the SQL query
		try {	
			PreparedStatement prepStatement = conn.prepareStatement(this.QueryLogin);
			prepStatement.setString(1, this.Username);
			// System.out.println("Prepared String: " + prepStatement);	printing out the SQL QUERY			
			// Results are gathered after the Query is executed
			ResultSet results = prepStatement.executeQuery();				
			User TestUser = new User(); // The TestUser is a temporary object that stores the data for the queried username
			// calling first() or next() will take us to the first row
			while(results.next()) {			 
				TestUser.setKeyID(results.getInt("KeyID"));
				TestUser.setUsername(results.getString("Username"));
				TestUser.setPassword(results.getString("Password"));
				TestUser.setAccountType(results.getBoolean("AccountType"));
			}
			// tests user-input username and password against database username and password
			if (this.UserAuthentication(TestUser, this.Username, this.Password) == true) {
				this.CurrentUser = TestUser;
				System.out.println("****Login successful****\n" );
				// System.out.println(CurrentUser.showUser());
				return true;
			}
			else {
				System.out.println("****Incorrect Username or Password, Login Failed****\n");
				return false;
			}
		// if the SQL query doesn't work then show the exception		
		}catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("SQL Exception e");
			return false;
		}	
	}	
	// method for creating a new user account
	public boolean UserAuthNewAccount(String Username, String Password) {
		// get a connection
		Connection conn = cu.getConnection();
		// Creating User Account Starts
		System.out.println("\n*****Creating User Account*****\n");
		// tries the SQL query
		try {		
			PreparedStatement prepStatement = conn.prepareStatement(this.CreateUser);
			prepStatement.setString(1, this.Username);
			prepStatement.setString(2,  this.Password);
			prepStatement.execute();
			return true;
		}catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("SQL Exception");
			return false;
		}
	}
	// method for logging new account details
	public boolean NewCustomerAccount(String firstname, String lastname, String email, String address, Timestamp ts) {
		// get a connection
		Connection conn = cu.getConnection();
		try {		
			PreparedStatement prepStatement = conn.prepareStatement(this.EnterCustomerDetails);
			prepStatement.setInt(1, this.getKey());
			prepStatement.setString(2, firstname);
			prepStatement.setString(3, lastname);
			prepStatement.setString(4,  email);
			prepStatement.setString(5,  address);
			prepStatement.setTimestamp(6, ts);
			prepStatement.execute();
			System.out.println("****Account created successfully****\n");
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Email address with account already exists.");
			return false;
		}
	}
	// get the correct KeyID for the new account
	private int getKey() {
		// get a connection
		Connection conn = cu.getConnection();
		try {			
			PreparedStatement prepStatement = conn.prepareStatement(this.getKeyID);
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
