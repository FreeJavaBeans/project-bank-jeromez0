package bank.repositories;

public interface EmployeeRepository {

	public void viewPendingAccounts();
	
	public void viewTransactions();
	
	public void viewCustomerAccounts(int KeyID);
	
}
