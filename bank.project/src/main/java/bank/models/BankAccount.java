package bank.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BankAccount {

	int KeyID;
	int AccountID;
	String RoutingID;
	double Balance;
	boolean Approval;
	String DateCreated;
	
	public BankAccount(int KeyID, int AccountID, String RoutingID, double Balance, boolean Approval, String DateCreated) {
		this.KeyID = KeyID;
		this.AccountID = AccountID;
		this.RoutingID = RoutingID;
		this.Balance = Balance;
		this.Approval = Approval;
		this.DateCreated = DateCreated;
	}
	
	public BankAccount() {
		super();
	}
	
	public BankAccount AccountSetter(ResultSet results, BankAccount Account) {
	
		try {	
			Account.setKeyID(results.getInt("KeyID"));
			Account.setAccountID(results.getInt("AccountID"));
			Account.setRoutingID(results.getString("RoutingID"));
			Timestamp obj = (results.getTimestamp("DateCreated"));
			String time = obj.toString();
			Account.setDateCreated(time);
			Account.setBalance(results.getDouble("Balance"));
			Account.setApproval(results.getBoolean("Approval"));							
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return Account;		
	}
	
	public void printBankAccount(BankAccount Account) {
		System.out.println("**************************");
		System.out.println("Customer ID: " + Account.getKeyID());
		System.out.println("Account ID: " + Account.getAccountID());
		System.out.println("Routing ID: " + Account.getRoutingID());
		System.out.println("Date created: " + Account.getDateCreated());
		System.out.println("Current Balance: " + Account.getBalance());
		if (Account.isApproval() == false)
			System.out.println("Approval Status: Pending");
		if (Account.isApproval() == true)
			System.out.println("Approval Status: Confirmed");
		System.out.println("**************************\n");
	}
	
	// getter and setter methods below
	
	public int getKeyID() {
		return KeyID;
	}
	public void setKeyID(int keyID) {
		KeyID = keyID;
	}
	public int getAccountID() {
		return AccountID;
	}
	public void setAccountID(int accountID) {
		AccountID = accountID;
	}
	public String getRoutingID() {
		return RoutingID;
	}
	public void setRoutingID(String routingID) {
		RoutingID = routingID;
	}
	public double getBalance() {
		return Balance;
	}
	public void setBalance(double balance) {
		Balance = balance;
	}
	public boolean isApproval() {
		return Approval;
	}
	public void setApproval(boolean approval) {
		Approval = approval;
	}
	public void setDateCreated(String time) {
		DateCreated = time;	
	}
	public String getDateCreated() {
		return DateCreated;
	}
}