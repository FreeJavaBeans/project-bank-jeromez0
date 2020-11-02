package bank.UserInterface;

import java.util.Scanner;
import bank.resources.CustomerDAO;

public class UserInterfaceCustomer {
	
	public void CustomerMenu() {
		Scanner scanner = new Scanner(System.in);
		char option = '\0';
		do {
			this.CustomerOptions();
			System.out.println("Enter an option: ");
			option = scanner.next().charAt(0);		
			
			switch(option) {
				case 'A':
					System.out.println("Still under construction");
					break;
				case 'B':
					System.out.println("Still under construction");
					break;
				case 'C':
					System.out.println("Still under construction");
					break;
				case 'D':
					System.out.println("Still under construction");
					break;
				case 'E':
					System.out.println("Still under construction");
					break;
				case 'F':
					System.out.println("Still under construction");
					break;
				case 'Q':
					break;
				default:
					System.out.println("Invalid option; please try again");
					break;
			}
		}while (option != 'Q');
		System.out.println("Thank you for using our services");
	}
	
	private void CustomerOptions() {
		System.out.println("****Customer Screen****");
		System.out.println("Press 'A' to Apply for new bank account");
		System.out.println("Press 'B' to View balances for all accounts");
		System.out.println("Press 'C' to View balances for a specific account");
		System.out.println("Press 'D' to Make a withdrawal or a deposit for a specific account");
		System.out.println("Press 'E' to post a money transfer to a specific account");
		System.out.println("Press 'F' to view and/or accept pending money transfers");
		System.out.println("Press 'Q' to Logout");
	}
	
}
