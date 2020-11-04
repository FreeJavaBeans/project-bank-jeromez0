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
	private int routingID = 123456789;
	private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
	
	/*
	 * SQL Queries
	 */
	String getCustomerInformation = "select * from \"BankApplication\".customers where \"KeyID\" = ?";
	String createNewBankAccount = "insert into \"BankApplication\".BankAccounts (\"KeyID\", \"AccountID\", \"RoutingID\", \"Balance\", \"Approval\", \"DateCreated\") " + 
								  "values (?,?,?,?,false,?)";
	
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
	
	public boolean ApplyBankAccount(float startingBalance) {
		// get a connection
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
		System.out.println("Account number: " + accountNumber);
		System.out.println("Routing ID: " + this.routingID);
		System.out.println("Date created: " + time);
		System.out.println("Current Balance: " + startingBalance);
		System.out.println("Approval: pending");
		System.out.println("****New Account Successfully Created****\n");
		return true;
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
