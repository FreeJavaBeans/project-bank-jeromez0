package bank.models;

import java.sql.Timestamp;

public class MoneyTransfers {
	int KeyID;
	int AccountID;
	int RecipientAccountID;
	float Amount;
	boolean Approval;
	Timestamp DateCreated;
	Timestamp DateApproved;
	
	
	
	
	// getter and setter methods below ---
	
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
	public int getRecipientAccountID() {
		return RecipientAccountID;
	}
	public void setRecipientAccountID(int recipientAccountID) {
		RecipientAccountID = recipientAccountID;
	}
	public float getAmount() {
		return Amount;
	}
	public void setAmount(float amount) {
		Amount = amount;
	}
	public boolean isApproval() {
		return Approval;
	}
	public void setApproval(boolean approval) {
		Approval = approval;
	}
	public Timestamp getDateCreated() {
		return DateCreated;
	}
	public void setDateCreated(Timestamp dateCreated) {
		DateCreated = dateCreated;
	}
	public Timestamp getDateApproved() {
		return DateApproved;
	}
	public void setDateApproved(Timestamp dateApproved) {
		DateApproved = dateApproved;
	}
	
	
	
}
