package bank.UserInterface;

import java.util.Scanner;
import bank.resources.EmployeeDAO;

public class EmployeeUserInterface {
	
	public void EmployeeMenu() {
		Scanner scanner = new Scanner(System.in);
		char option = '\0';
		do {
			this.EmployeeOptions();
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
				case 'Q':
					break;
				default:
					System.out.println("Invalid option; please try again");
					break;
			}
		}while (option != 'Q');
		System.out.println("***************");
		System.out.println("Log Out Successful. Returning to Login screen.");
		System.out.println("***************\n");
	}
	
	private void EmployeeOptions() {
		System.out.println("****Employee Screen****");
		System.out.println("Press 'A' to View Bank Accounts that are pending approval");
		System.out.println("Press 'B' to View Bank Accounts for a specific customer");
		System.out.println("Press 'C' to View log of all transcations");
		System.out.println("Press 'Q' to Logout\n");
	}
}

