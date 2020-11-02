package bank.UserInterface;

import java.util.List;
import java.util.Scanner;

import bank.resources.UserAuthenticationDAO;

public class UserInterfaceLogin {
	// UserInterface for the customer as well as the employee are created
	UserInterfaceCustomer UICustomer = new UserInterfaceCustomer();
	UserInterfaceEmployee UIEmployee = new UserInterfaceEmployee();
	
	// main login menu
	public void LoginMenu() {
		Scanner scanner = new Scanner(System.in);
		char option = '\0';
		do {
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
					
					// Testing for User Authentication
					UserAuthenticationDAO us = new UserAuthenticationDAO(username,password);
					boolean UserAuth = us.UserAuthentication();
					
					// If Login Successful and account type is customer then return customer menu
					if ( (UserAuth == true) && (us.getCurrentUser().isAccountType() == true)) {
						UICustomer.CustomerMenu();
						break;
					}
					
					// If Login Successful and account type is employee then return employee menu
					else if ( (UserAuth == true) && (us.getCurrentUser().isAccountType() == false) ) {
						UIEmployee.EmployeeMenu();
						break;
					}
					//invalid option
					else break;
					
				case 'B':
					System.out.println("Still under construction");
					break;
				case 'Q':
					break;
				default:
					System.out.println("Invalid option; please try again");
			}
		}while (option != 'Q');
		System.out.println("Thank you for using our services");
	}
	// private method to display different Login Options
	private void LoginOptions() {
		System.out.println("****Login Screen****");
		System.out.println("Press 'A' to Log in");
		System.out.println("Press 'B' to create a new customer account");
		System.out.println("Press 'Q' to exit");
	}
}
