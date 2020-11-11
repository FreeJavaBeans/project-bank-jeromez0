package bank.repositories;

public interface EmployeeRepository {
	
	public void viewPendingAccounts();
	
	public boolean approvePendingAccounts(int AccountID);
	
	public void viewCustomerAccounts(int KeyID);
	
	public void viewTransactions();
	
}
