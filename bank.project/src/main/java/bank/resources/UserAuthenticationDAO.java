package bank.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bank.users.User;

public class UserAuthenticationDAO {
	
	private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
	String Username;
	String Password;
	User CurrentUser;
	String QueryLogin = "select * from \"BankApplication\".UserAuth where \"Username\" = ?;";
	
	String CreateUser = "insert into UserAuth (\"Username\", \"Password\", \"AccountType\") values (?,?,true);";
	
	String getKeyID = "select KeyID from \"BankApplication\".UserAuth where \"Username\" = ?;";
	
	String EnterCustomerDetails = "insert into Customers (\"KeyID\", \"FirstName\", \"LastName\", \"Email\", \"Address\",\"DOB\") "+
								  "values (?,?,?,?,?,?)";
	
	
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
			Statement statementObject = conn.createStatement();					
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
				
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception e");
			return false;
		}	
	}
	
	// method for creating a new account
	public void UserAuthNewAccount(String Username, String Password, boolean AccountType) {
		
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
	
}
