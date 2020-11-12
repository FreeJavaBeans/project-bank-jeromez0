package bank.repositories;

public interface CustomerRepository {

	public boolean ApplyBankAccount(double startingBalance);
	
	public boolean ViewSpecificBalance(int AccountNum);
	
	public void ViewAllBalances(); 
	
	public void MakeDeposit(int AccountNum, double depositAmount);
	
	public void MakeWithdrawal(int AccountNum, double withdrawalAmount);
	
	public boolean PostMoneyTransfer(int KeyID, int SenderAccountID, int RecipientAccountID, double Amount);
	
	public void ViewMoneyTransfers();
	
	public boolean AcceptMoneyTransfer(int transactID);
	
}
