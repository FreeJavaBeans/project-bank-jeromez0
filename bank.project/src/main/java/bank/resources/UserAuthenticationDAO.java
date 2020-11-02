package bank.resources;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bank.resources.ConnectionUtility;
import bank.users.User;

public class UserAuthenticationDAO {
	
	private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();

	int KeyID;
	String Username;
	String Password;
	boolean AccountType;
	
	// method for logging in to the system
	public UserAuthenticationDAO(String username, String password) {
		// get a connection
		Connection conn = cu.getConnection();
		// authentication process starts
		System.out.println("\n*****Authentication*****");		
		System.out.println("Username: " + username + "\nPassword: "+ password);
		// tries the SQL query
		try {
			Statement statementObject = conn.createStatement();

			String authString = "select * " +
								"from \"BankApplication\".UserAuth " +
								"where \"Username\" = \'" + username + "\'";
			
			
			String queryString = "select * "+ 
								 "from \"BankApplication\".UserAuth " + 
								 "where \"Username\" = \'Customer1\'";
			
			
			System.out.println("\n User Input Query: " + authString);
			System.out.println("Correct SQL Query: " + queryString + "\n");
			
			ResultSet results = statementObject.executeQuery(authString);		
			//ResultSet results = statementObject.executeQuery(queryString);
			
			// calling first() or next() will take us to the first row
			User TestUser = new User();
			while(results.next()) {			 
				TestUser.setKeyID(results.getInt("KeyID"));
				TestUser.setUsername(results.getString("Username"));
				TestUser.setPassword(results.getString("Password"));
				TestUser.setAccountType(results.getBoolean("AccountType"));
			}
			System.out.println("****Login successful**** " );
			System.out.println(TestUser.showUser());
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception e");
		}
		
	}
	
	// method for creating a new account
	public UserAuthenticationDAO(String Username, String Password, boolean AccountType) {
		
	}
	
}
