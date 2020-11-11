package bank.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
	
	public Customer CustomerSetter(ResultSet results, Customer CurrentCustomer) {
		try {	
			CurrentCustomer.setKeyID(results.getInt("KeyID"));
			CurrentCustomer.setFirstName(results.getString("FirstName"));
			CurrentCustomer.setLastName(results.getString("LastName"));
			CurrentCustomer.setEmail(results.getString("Email"));
			CurrentCustomer.setAddress(results.getString("Address"));
			Timestamp obj = (results.getTimestamp("DateCreated"));
			String time = obj.toString();
			CurrentCustomer.setDateCreated(time);							
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return CurrentCustomer;		
	}
	
	public void showCustomerDetails(Customer CurrentCustomer, User CurrentUser) {
		System.out.println("****Customer Details****\n");
		System.out.println("Customer ID: " + CurrentUser.getKeyID());
		System.out.println("Username: "  + CurrentUser.getUsername());
		System.out.println("First name: " + CurrentCustomer.getFirstName());
		System.out.println("Last name: " + CurrentCustomer.getLastName());
		System.out.println("Email address: " + CurrentCustomer.getEmail());
		System.out.println("Home address: " + CurrentCustomer.getAddress());
		System.out.println("Date customer account created: " + CurrentCustomer.getDateCreated() + "\n");
	}

	
	// getter and setter methods below
	
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
