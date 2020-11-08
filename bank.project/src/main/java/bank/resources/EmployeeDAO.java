package bank.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import bank.repositories.EmployeeRepository;

public class EmployeeDAO implements EmployeeRepository{
	
	private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
	
	public EmployeeDAO() {
		super();
	}
	
	public void viewPendingAccounts() {
		String sqlQuery = "select * from \"BankApplication\".bankaccounts where \"Approval\" = false";
		Connection conn = cu.getConnection();
		try {	
			PreparedStatement prepStatement = conn.prepareStatement(sqlQuery);
			ResultSet results = prepStatement.executeQuery();				
			while(results.next()) {			 
				System.out.println("\n****Viewing Unapproved Accounts****");
				System.out.println("Account ID: " + results.getInt("AccountID"));
				System.out.println("Routing ID: " + results.getString("RoutingID"));
				Timestamp obj = (results.getTimestamp("DateCreated"));
				String time = obj.toString();
				System.out.println("Date created: " + time);
				System.out.println("Current Balance: " + results.getDouble("Balance"));
				System.out.println("Approval Status: " + results.getBoolean("Approval"));
				System.out.println("**************************\n");
			}
		// if the SQL query doesn't work then show the exception		
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Invalid Authorization or Account ID");
		}	
	}

	public boolean approvePendingAccounts(int AccountID) {
		String sqlQuery = "update \"BankApplication\".bankaccounts set \"Approval\" = true where \"AccountID\" = ?";
		Connection conn = cu.getConnection();
		try {	
			PreparedStatement prepStatement = conn.prepareStatement(sqlQuery);
			prepStatement.setInt(1,  AccountID);
			prepStatement.execute();
			System.out.println("****Account updated successfully****\n");
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Invalid Authorization or Account ID");
			return false;
		}			
		return true;
	}
		
	public void viewCustomerAccounts(int KeyID) {
		String sqlQuery = "select * from \"BankApplication\".bankaccounts where \"KeyID\" = ?";
		Connection conn = cu.getConnection();
		try {
			PreparedStatement prepStatement = conn.prepareStatement(sqlQuery);
			prepStatement.setInt(1,  KeyID);
			ResultSet results = prepStatement.executeQuery();				
			System.out.println("\n****Viewing Customer's Accounts****");
			while(results.next()) {			 				
				System.out.println("Account ID: " + results.getInt("AccountID"));
				System.out.println("Routing ID: " + results.getString("RoutingID"));
				Timestamp obj = (results.getTimestamp("DateCreated"));
				String time = obj.toString();
				System.out.println("Date created: " + time);
				System.out.println("Current Balance: " + results.getDouble("Balance"));
				System.out.println("Approval Status: " + results.getBoolean("Approval"));
				System.out.println("**************************\n");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewTransactions() {
		
	}

}
