package bank.resources;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

	public void LoginMenu() {
		Scanner scanner = new Scanner(System.in);
		char option = '\0';
		System.out.println("****Login Screen****");
		System.out.println("Press 'A' to Log in");
		System.out.println("Press 'B' to create a new customer account");
		System.out.println("Press 'C' to create a new employee/admin account");
		System.out.println("Press 'Q' to exit");
		do {
			System.out.println("Enter an option: ");
			option = scanner.next().charAt(0);		
			
			switch(option) {
				case 'A':
					System.out.println("Enter a username:");		
					String username = scanner.next();
					System.out.println("Enter a password:");
					String password = scanner.next();			
					UserAuthenticationDAO us = new UserAuthenticationDAO(username,password);
					break;
				case 'B':
					System.out.println("Still under construction");
					break;
				case 'C':
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
	
}
