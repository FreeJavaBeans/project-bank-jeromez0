package bank.services;

import bank.resources.EmployeeDAO;
import bank.util.ScannerUtility;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeUserInterface {
	
	EmployeeDAO EmpServices = new EmployeeDAO();
	Scanner scanner = ScannerUtility.getScanner();
	
	public void EmployeeMenu() {

		char option = '\0';
		do {
			this.EmployeeOptions();
			System.out.print("Enter an option: ");
			option = scanner.next().charAt(0);				
			switch(option) {
				case 'A':
					this.EmployeeViewPendingAccounts(scanner);
					break;
				case 'B':
					this.EmployeeViewCustomerAccounts(scanner);
					break;
				case 'C':
					this.EmployeeViewTransactions(scanner);
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
		System.out.println("****Viewing Pending Accounts****");
		System.out.print("Please input AccountID of account you would like to approve: ");
		try {
			int acctID = scanner.nextInt();
			if (EmpServices.approvePendingAccounts(acctID) == true)
				System.out.println("\n****Account successfully approved****\n");
			else
				System.out.println("\n****Unable to approve account****\n");
		}catch (InputMismatchException I) {
			System.out.println("***Invalid Input***\n");
			scanner.next();
		}
	}
	// Case B functionality (view a specific customer's account)
	private void EmployeeViewCustomerAccounts(Scanner scanner) {
		System.out.println("****Viewing Specific Customer's Accounts****");
		System.out.print("Please input KeyID of customer bank accounts you would like to view: ");
		try {
			int key = scanner.nextInt();
			boolean bool= EmpServices.viewCustomerAccounts(key);
			if (bool == false) {
				System.out.println("\n***Account does not exist***\n");
			}
		}catch (InputMismatchException I) {
			System.out.println("***Invalid Input***\n");
			scanner.next();
		}
	}
	// Case C functionality (view all previous transactions)
	private void EmployeeViewTransactions(Scanner scanner) {
		System.out.println("****Viewing Transactions****\n");
		EmpServices.viewTransactions();
	}
	// Menu screen
	private void EmployeeOptions() {
		System.out.println("****Employee Screen****");
		System.out.println("Press 'A' to View Bank Accounts that are pending approval");
		System.out.println("Press 'B' to View Bank Accounts for a specific customer");
		System.out.println("Press 'C' to View log of all transactions");
		System.out.println("Press 'Q' to Logout\n");
	}
	// LogOut Screen
	private void LogOutScreen() {
		System.out.println("***************");
		System.out.println("Log Out Successful. Returning to Login screen.");
		System.out.println("***************\n");
	}
}

