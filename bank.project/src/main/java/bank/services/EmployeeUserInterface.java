package bank.services;

import bank.resources.EmployeeDAO;
import bank.util.ScannerUtility;
import java.util.Scanner;

public class EmployeeUserInterface {
	
	EmployeeDAO EmpServices = new EmployeeDAO();
	Scanner scanner = ScannerUtility.getScanner();
	
	public void EmployeeMenu() {

		char option = '\0';
		do {
			this.EmployeeOptions();
			System.out.println("Enter an option: ");
			option = scanner.next().charAt(0);				
			switch(option) {
				case 'A':
					this.EmployeeViewPendingAccounts(scanner);
					break;
				case 'B':
					this.EmployeeViewCustomerAccounts(scanner);
					break;
				case 'C':
					EmpServices.viewTransactions();
					System.out.println("Under construction");
					break;
				case 'Q':
					break;
				default:
					System.out.println("Invalid option; please try again");
					break;
			}
		}while (option != 'Q');
		this.LogOutScreen();
	}
	
	// Case A functionality (view pending accounts that require approval)
	private void EmployeeViewPendingAccounts(Scanner scanner) {
		EmpServices.viewPendingAccounts();
		System.out.println("Please input AccountID of account you would like to approve: ");
		int acctID = scanner.nextInt();
		if (EmpServices.approvePendingAccounts(acctID) == true)
			System.out.println("\n****Account successfully approved****\n");
		else
			System.out.println("\n****Unable to approve account****\n");
	}
	// Case B functionality (view a specific customer's account)
	private void EmployeeViewCustomerAccounts(Scanner scanner) {
		System.out.println("Please input KeyID of customer bank accounts you would like to view: ");
		int key = scanner.nextInt();
		EmpServices.viewCustomerAccounts(key);
	}
	// Menu screen
	private void EmployeeOptions() {
		System.out.println("****Employee Screen****");
		System.out.println("Press 'A' to View Bank Accounts that are pending approval");
		System.out.println("Press 'B' to View Bank Accounts for a specific customer");
		System.out.println("Press 'C' to View log of all transcations");
		System.out.println("Press 'Q' to Logout\n");
	}
	// LogOut Screen
	private void LogOutScreen() {
		System.out.println("***************");
		System.out.println("Log Out Successful. Returning to Login screen.");
		System.out.println("***************\n");
	}
}

