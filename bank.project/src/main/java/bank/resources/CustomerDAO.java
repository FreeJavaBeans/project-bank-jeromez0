package bank.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import bank.users.Customer;
import bank.users.User;

public class CustomerDAO {
	
	private int KeyID;
	private Customer CurrentCustomer = new Customer();
	private User CurrentUser;
	private int routingID = 123456789;
	private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
	
	// SQL Queries
	String getCustomerInformation = "select * from \"BankApplication\".customers where \"KeyID\" = ?";
	String createNewBankAccount = "insert into \"BankApplication\".BankAccounts (\"KeyID\", \"AccountID\", \"RoutingID\", \"Balance\", \"Approval\", \"DateCreated\") " + 
								  "values (?,?,?,?,false,?)";
	String viewAccountBalance = "select * from \"BankApplication\".BankAccounts where \"KeyID\" = ? and \"AccountID\" = ?";
	//
	// Primary constructor method
	public CustomerDAO(int KeyID, User current) {
		Connection conn = cu.getConnection();
		this.KeyID = KeyID;
		this.CurrentUser = current;
		try {	
			PreparedStatement prepStatement = conn.prepareStatement(this.getCustomerInformation);
			prepStatement.setInt(1, this.KeyID);		
			ResultSet results = prepStatement.executeQuery();				
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
		}catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("SQL Exception e");
		}	
	}
	// Apply for Bank Account method
	public boolean ApplyBankAccount(float startingBalance) {
		Connection conn = cu.getConnection();
		// generate a random accounting number
		Random rand = new Random();
		int accountNumber = 100000000 + rand.nextInt(900000000);
		// generate a timestamp
		Date date = new Date();  
		Timestamp ts=new Timestamp(date.getTime());  
		String time = ts.toString();
		if (startingBalance < 0) {
			System.out.println("Impossible to start with a balance less than 0.");
			return false;
		}	
		try {		
			PreparedStatement prepStatement = conn.prepareStatement(this.createNewBankAccount);
			prepStatement.setInt(1, this.KeyID);
			prepStatement.setInt(2, accountNumber);
			prepStatement.setInt(3, this.routingID);
			prepStatement.setFloat(4, startingBalance);
			prepStatement.setTimestamp(5, ts);
			prepStatement.execute();			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Account with RoutingID already exists.");
			return false;
		}
		System.out.println("\n**********");
		System.out.println("Account Number: " + accountNumber);
		System.out.println("Routing ID: " + this.routingID);
		System.out.println("Date Created: " + time);
		System.out.println("Current Balance: " + startingBalance);
		System.out.println("Approval Status: pending");
		System.out.println("****New Account Successfully Created****\n");
		return true;
	}
	// Viewing a specific account balance
	public void ViewSpecificBalance(int AccountNum) {
		// get a connection
		Connection conn = cu.getConnection();
		try {	
			PreparedStatement prepStatement = conn.prepareStatement(this.viewAccountBalance);
			prepStatement.setInt(1, this.KeyID);
			prepStatement.setInt(2, AccountNum);
			ResultSet results = prepStatement.executeQuery();				
			while(results.next()) {			 
				System.out.println("\n****Viewing Balances****");
				System.out.println("Account ID: " + AccountNum);
				System.out.println("Routing ID: " + results.getString("RoutingID"));
				Timestamp obj = (results.getTimestamp("DateCreated"));
				String time = obj.toString();
				System.out.println("Date created: " + time);
				System.out.println("Current Balance: " + results.getFloat("Balance"));
				System.out.println("Approval Status: " + results.getBoolean("Approval"));
				System.out.println("**************************\n");
			}
		// if the SQL query doesn't work then show the exception		
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Invalid Authorization or Account ID");
		}	
	}
	// Making a withdrawal
	public void MakeWithdrawal() {
		
	}
	// Making a deposit
	public void MakeDeposit() {
		
	}
	// Posting a money transfer
	public void PostMoneyTransfer() {
		
	}
	// Viewing pending money transfers
	public void ViewMoneyTransfers() {
		
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
}
