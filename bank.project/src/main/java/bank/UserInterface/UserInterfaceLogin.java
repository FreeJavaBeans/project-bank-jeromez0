package bank.UserInterface;

import java.util.Scanner;
import java.util.Date;
import java.sql.Timestamp;

import bank.resources.UserAuthenticationDAO;

public class UserInterfaceLogin {
	// UserInterface for the customer as well as the employee are created
	UserInterfaceEmployee UIEmployee = new UserInterfaceEmployee();
	
	// main login menu
	public void LoginMenu() {
		
		char option = '\0';
		do {
			Scanner scanner = new Scanner(System.in);
			this.LoginOptions();
			System.out.println("Enter an option: ");
			option = scanner.next().charAt(0);		
			
			switch(option) {
				case 'A':
					// Accepting User Inputs to Login
					System.out.println("Enter a username:");		
					String username = scanner.next();
					System.out.println("Enter a password:");
					String password = scanner.next();								
					// Creating new user session
					UserAuthenticationDAO Login = new UserAuthenticationDAO(username,password);
					// Testing for User Authentication
					boolean UserAuth = Login.UserAuthentication();					
					// If Login Successful and account type is customer then return customer menu
					if ( (UserAuth == true) && (Login.getCurrentUser().isAccountType() == true)) {
						UserInterfaceCustomer UICustomer = new UserInterfaceCustomer(Login.getCurrentUser().getKeyID(), Login.getCurrentUser());
						UICustomer.CustomerMenu();
						break;
					}					
					// If Login Successful and account type is employee then return employee menu
					else if ( (UserAuth == true) && (Login.getCurrentUser().isAccountType() == false) ) {
						UIEmployee.EmployeeMenu();
						break;
					}
					//invalid option
					else break;
					
				case 'B':
					// accepting user inputs to create a new user account
					System.out.println("Enter a username:");
					String username1 = scanner.next();
					System.out.println("Enter a password:");
					String password1 = scanner.next();
					// creating new user authentication session
					UserAuthenticationDAO CreateAccount = new UserAuthenticationDAO(username1,password1);
					// if the user account is created successfully then add customer details
					if (CreateAccount.UserAuthNewAccount(username1, password1) == true) { 
						NewCustomerAccount(scanner, CreateAccount);
						break;
					}
					else
						System.out.println("Creating a new user account failed.\n");
						break;						
						
				case 'Q':
					break;
				default:
					System.out.println("Invalid option; please try again");
			}
		}while (option != 'Q');
		System.out.println("***************");
		System.out.println("Thank you for using our services. Exiting Application");
		System.out.println("***************\n");
	}
	// private method to display different Login Options
	private void LoginOptions() {
		System.out.println("****Login Screen****");
		System.out.println("Press 'A' to Log in");
		System.out.println("Press 'B' to create a new customer account");
		System.out.println("Press 'Q' to exit\n");
	}
	// private method to accept user input for the Create New Customer Account form
	private void NewCustomerAccount(Scanner scanner, UserAuthenticationDAO CreateAccount) {
		System.out.println("All fields required");
		System.out.println("Enter your first name:");
		String firstname= scanner.next();
		System.out.println("Enter your last name:");
		String lastname= scanner.next();
		System.out.println("Enter your email address:");
		String email= scanner.next();
		System.out.println("Enter your home address:");
		scanner.nextLine();
		String address = scanner.nextLine();
		Date date = new Date();  
		Timestamp ts=new Timestamp(date.getTime());  
//		System.out.println(ts);       
		CreateAccount.NewCustomerAccount(firstname, lastname, email, address, ts);
	}
}
