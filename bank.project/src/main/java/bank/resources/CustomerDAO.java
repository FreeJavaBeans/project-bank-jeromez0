package bank.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import bank.users.Customer;
import bank.users.User;

public class CustomerDAO {
//	"Press 'A' to Apply for new bank account"
//	"Press 'B' to View balances for all accounts"
//	"Press 'C' to View balances for a specific account"
//	"Press 'D' to Make a withdrawal or a deposit for a specific account"
//	"Press 'E' to post a money transfer to a specific account"
//	"Press 'F' to view and/or accept pending money transfers"
//	"Press 'Q' to Logout"
	private int KeyID;
	private Customer CurrentCustomer = new Customer();
	private User CurrentUser;
	private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
	/*
	 * SQL Queries
	 */
	String getCustomerInformation = "select * from \"BankApplication\".customers where \"KeyID\" = ?";
	
	
	public CustomerDAO(int KeyID, User current) {
		// get a connection
		Connection conn = cu.getConnection();
		this.KeyID = KeyID;
		this.CurrentUser = current;
		try {	
			PreparedStatement prepStatement = conn.prepareStatement(this.getCustomerInformation);
			prepStatement.setInt(1, this.KeyID);		
			// Results are gathered after the Query is executed
			ResultSet results = prepStatement.executeQuery();				
			// calling first() or next() will take us to the first row
			while(results.next()) {			 
				CurrentCustomer.setKeyID(results.getInt("KeyID"));
				CurrentCustomer.setFirstName(results.getString("FirstName"));
				CurrentCustomer.setLastName(results.getString("LastName"));
				CurrentCustomer.setEmail(results.getString("Email"));
				CurrentCustomer.setAddress(results.getString("Address"));
				Timestamp obj = (results.getTimestamp("DateCreated"));
				String time = obj.toString();
				CurrentCustomer.setDateCreated(time);
			}
		// if the SQL query doesn't work then show the exception		
		}catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("SQL Exception e");
		}	
	}
	
	public void ShowCustomerDetails() {
		System.out.println("****Customer Details****\n");
		System.out.println("Username: "  + CurrentUser.getUsername());
		System.out.println("First name: " + CurrentCustomer.getFirstName());
		System.out.println("Last name: " + CurrentCustomer.getLastName());
		System.out.println("Email address: " + CurrentCustomer.getEmail());
		System.out.println("Home address: " + CurrentCustomer.getAddress());
		System.out.println("Date customer account created: " + CurrentCustomer.getDateCreated() + "\n");
	}
	
	public void ApplyBankAccount() {
		
	}
	public void ViewBalances() {
		
	}
	public void ViewSpecificBalance() {
		
	}
	public void MakeWithdrawal() {
		
	}
	public void MakeDeposit() {
		
	}
	public void PostMoneyTransfer() {
		
	}
	public void ViewMoneyTransfers() {
		
	}
}
