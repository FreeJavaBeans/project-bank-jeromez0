package bank.repositories;

import java.sql.Timestamp;

public interface LoginRepository {
	
	public boolean UserAuthentication();

	public boolean NewUserAccount(String Username, String Password);;
	
	public boolean NewCustomerAccount(String firstname, String lastname, String email, String address, Timestamp ts); 
	
}
