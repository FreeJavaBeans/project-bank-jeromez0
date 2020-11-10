package bank.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class Transactions {
	
	int KeyID;
	String Type;
	int AccountID;
	int RecipientAccountID;
	double Amount;
	String DateCreated;
	
	public Transactions() {
		super();
	}
	
	public Transactions TransactionSetter(int KeyID, String Type, int AccountID, double Amount, Transactions Transaction) {
		Transaction.setKeyID(KeyID);
		Transaction.setType(Type);
		Transaction.setAccountID(AccountID);
		Transaction.setAmount(Amount);
		Date date = new Date();  
		Timestamp ts=new Timestamp(date.getTime());  
		String time = ts.toString();	
		Transaction.setDateCreated(time);
		return Transaction;
	}
		
	public Transactions TransactionSetterMoneyTransfer(int KeyID, int SenderAccountID, int RecipientAccountID, double Amount, Transactions Transaction) {
		Transaction.setKeyID(KeyID);
		Transaction.setType("Money Transfer");
		Transaction.setAccountID(SenderAccountID);
		Transaction.setRecipientAccountID(RecipientAccountID);
		Transaction.setAmount(Amount);
		Date date = new Date();  
		Timestamp ts=new Timestamp(date.getTime());  
		String time = ts.toString();	
		Transaction.setDateCreated(time);
		return Transaction;
	}

	public void printTransaction(ResultSet results) {
		try {
			System.out.println("**************************");
			System.out.println("Customer ID: " + results.getInt("KeyID"));
			System.out.println("Type: " + results.getString("Type"));
			System.out.println("Amount: " + results.getFloat("Amount"));
			System.out.println("Account ID: " + results.getInt("AccountID"));
			System.out.println("Recipient ID: " + results.getInt("RecipientAccountID"));
			Timestamp obj = (results.getTimestamp("DateCreated"));
			String time = obj.toString();
			System.out.println("Date created: " + time);
			System.out.println("**************************\n");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// public getters and setter methods below
	
	public int getKeyID() {
		return KeyID;
	}
	public void setKeyID(int keyID) {
		KeyID = keyID;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
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
	public void setAmount(double amount) {
		Amount = amount;
	}
	public String getDateCreated() {
		return DateCreated;
	}
	public void setDateCreated(String dateCreated) {
		DateCreated = dateCreated;
	}

}
