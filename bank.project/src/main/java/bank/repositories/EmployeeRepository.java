package bank.repositories;

public interface EmployeeRepository {
	
	public void viewPendingAccounts();
	
	public boolean approvePendingAccounts(int AccountID);
	
	public boolean viewCustomerAccounts(int KeyID);
	
	public void viewTransactions();
	
}
