package bank.users;

public class User {

	int KeyID;
	String Username;
	String Password;
	boolean AccountType;
	
	public User(int KeyID, String Username, String Password, boolean AccountType) {
		this.KeyID = KeyID;
		this.Username = Username;
		this.Password = Password;
		this.AccountType = AccountType;		
	}
	
	public User() {
		super();
	}
	
	public String showUser() {
		return "KeyID: " + this.KeyID + "\n" + 
			   "Username: " + this.Username + "\n" +
			   "Password: " + this.Password + "\n" + 
			   "Account Type: " + this.AccountType + "\n";
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
}
