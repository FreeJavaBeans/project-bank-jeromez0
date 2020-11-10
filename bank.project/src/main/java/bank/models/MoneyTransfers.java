package bank.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MoneyTransfers {
	int KeyID;
	int TransactionID;
	int AccountID;
	int RecipientAccountID;
	double Amount;
	boolean Approval;
	String DateCreated;
	String DateApproved;
	
	public MoneyTransfers() {
		super();
	}
	
	public MoneyTransfers TransferSetter(ResultSet results, MoneyTransfers Transfer) {
		try {	
			Transfer.setKeyID(results.getInt("KeyID"));
			Transfer.setTransactionID(results.getInt("TransactionID"));
			Transfer.setAccountID(results.getInt("AccountID"));
			Transfer.setRecipientAccountID(results.getInt("RecipientAccountID"));
			Transfer.setAmount(results.getDouble("Amount"));
			Transfer.setApproval(results.getBoolean("Approval"));	
			Timestamp obj = (results.getTimestamp("DateCreated"));
			String time = obj.toString();
			Transfer.setDateCreated(time);					
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return Transfer;		
	}
	
	public void printReceievedMoneyTransfers(MoneyTransfers Transfer) {
		System.out.println("***************************");
		System.out.println("Transaction ID: " + Transfer.getTransactionID());
		System.out.println("Sender Account ID: " + Transfer.getAccountID());
		System.out.println("Receiver Account ID: " + Transfer.getRecipientAccountID());
		System.out.println("Amount: " + Transfer.getAmount());
		System.out.println("Date created: " + Transfer.getDateCreated());
		System.out.println("Approval Status: pending");
		System.out.println("**************************\n");
	}
		
	public void printSentMoneyTransfers(MoneyTransfers Transfer) {
		System.out.println("***************************");
		System.out.println("Sender Account ID: " + Transfer.getAccountID());
		System.out.println("Receiver Account ID: " + Transfer.getRecipientAccountID());
		System.out.println("Amount: " + Transfer.getAmount());
		System.out.println("Date created: " + Transfer.getDateCreated());
		System.out.println("Approval Status: pending");
		System.out.println("**************************\n");
	}
	
	// getter and setter methods below ---
	
	public int getKeyID() {
		return KeyID;
	}
	public void setKeyID(int keyID) {
		KeyID = keyID;
	}
	public int getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(int transactionID) {
		TransactionID = transactionID;
	}
	public int getAccountID() {
		return AccountID;
	}
	public void setAccountID(int accountID) {
		AccountID = accountID;
	}
	public int getRecipientAccountID() {
		return RecipientAccountID;
	}
	public void setRecipientAccountID(int recipientAccountID) {
		RecipientAccountID = recipientAccountID;
	}
	public double getAmount() {
		return Amount;
	}
	public void setAmount(double d) {
		Amount = d;
	}
	public boolean isApproval() {
		return Approval;
	}
	public void setApproval(boolean approval) {
		Approval = approval;
	}
	public String getDateCreated() {
		return DateCreated;
	}
	public void setDateCreated(String dateCreated) {
		DateCreated = dateCreated;
	}
	public String getDateApproved() {
		return DateApproved;
	}
	public void setDateApproved(String dateApproved) {
		DateApproved = dateApproved;
	}
		
}
