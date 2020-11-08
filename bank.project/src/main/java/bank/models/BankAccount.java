package bank.models;

public class BankAccount {

	int KeyID;
	String AccountID;
	String RoutingID;
	String Balance;
	boolean Approval;
	String DateCreated;
	
	public BankAccount(int KeyID, String AccountID, String RoutingID, String Balance, boolean Approval, String DateCreated) {
		this.KeyID = KeyID;
		this.AccountID = AccountID;
		this.RoutingID = RoutingID;
		this.Balance = Balance;
		this.Approval = Approval;
		this.DateCreated = DateCreated;
	}
	
	public int getKeyID() {
		return KeyID;
	}
	public void setKeyID(int keyID) {
		KeyID = keyID;
	}
	public String getAccountID() {
		return AccountID;
	}
	public void setAccountID(String accountID) {
		AccountID = accountID;
	}
	public String getRoutingID() {
		return RoutingID;
	}
	public void setRoutingID(String routingID) {
		RoutingID = routingID;
	}
	public String getBalance() {
		return Balance;
	}
	public void setBalance(String balance) {
		Balance = balance;
	}
	public boolean isApproval() {
		return Approval;
	}
	public void setApproval(boolean approval) {
		Approval = approval;
	}
}
