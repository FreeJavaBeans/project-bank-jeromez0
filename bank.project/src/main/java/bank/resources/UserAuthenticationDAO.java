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
	public UserAuthenticationDAO() {
		// get a connection
		Connection conn = cu.getConnection();
		try {
			Statement statementObject = conn.createStatement();
			
			String queryString = "select * from \"BankApplication\".UserAuth where \"KeyID\" = 1";
			
			ResultSet results = statementObject.executeQuery(queryString);
			
			// calling first() or next() will take us to the first row
			
			User test = new User();
			while(results.next()) {			 
				test.setKeyID(results.getInt("KeyID"));
				test.setUsername(results.getString("Username"));
				test.setPassword(results.getString("Password"));
				test.setAccountType(results.getBoolean("AccountType"));
			}
			System.out.println(test.showUser());
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Get all users failed");
		}
		
	}
	
	// method for creating a new account
	public UserAuthenticationDAO(String Username, String Password, boolean AccountType) {
		
	}
	
}
