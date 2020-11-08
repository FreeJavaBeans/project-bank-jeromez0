package bank.models;

public class Customer extends User{
	
	int KeyID;
	String Username;
	String Password;
	boolean AccountType;
	String FirstName;
	String LastName;
	String Email;
	String Address;
	String DateCreated;
	
	public Customer() {
		super();
	}
	
	public int getKeyID() {
		return KeyID;
	}
	public void setKeyID(int keyID) {
		KeyID = keyID;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public boolean isAccountType() {
		return AccountType;
	}
	public void setAccountType(boolean accountType) {
		AccountType = accountType;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getDateCreated() {
		return DateCreated;
	}
	public void setDateCreated(String TS) {
		DateCreated = TS;
	}
}
