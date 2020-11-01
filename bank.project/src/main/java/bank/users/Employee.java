package bank.users;

public class Employee extends User{
	
	public Employee(int KeyID, String Username, String Password, boolean AccountType) {
		super(KeyID, Username, Password, AccountType);
		// TODO Auto-generated constructor stub
	}
	int KeyID;
	String Username;
	String Password;
	boolean AccountType;
	String FirstName;
	String LastName;
	String Email;
	String Address;
	String DOB;
	
	
}
