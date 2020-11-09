package bank.services;

import java.util.Scanner;

import bank.models.User;
import bank.resources.CustomerDAO;
import bank.util.ScannerUtility;

public class CustomerUserInterface {
	
	CustomerDAO customerDAO;
	Scanner scanner = ScannerUtility.getScanner();
	private int KeyID;
	
	CustomerUserInterface(int keyID, User current) {
		this.KeyID = keyID;
		this.customerDAO = new CustomerDAO(this.KeyID, current);
	}
	
	public void CustomerMenu() {
		char option = '\0';
		do {
			this.CustomerOptions();
			System.out.println("Enter an option: ");
			option = scanner.next().charAt(0);		
			
			switch(option) {
				case 'A':
					this.CreateNewBankAccount(scanner);
					break;
				case 'B':
					this.ViewBalance(scanner);
					break;
				case 'C':
					this.MakeDeposit(scanner);
					break;
				case 'D':
					this.MakeWithdrawal(scanner);
					break;
				case 'E':
					this.PostMoneyTransfer(scanner);
					break;
				case 'F':
					this.ViewMoneyTransfers(scanner);
					break;
				case 'G': 
					customerDAO.ShowCustomerDetails();
					break;
				case 'H':
					customerDAO.ViewAllBalances();
					break;
				default:
					System.out.println("Invalid option; please try again");
					break;
			}
		}while (option != 'Q');
		this.LogOut();
	}	
	// Main Customer Menu
	private void CustomerOptions() {
		System.out.println("****Customer Screen****");
		System.out.println("Press 'A' to Apply for new bank account");
		System.out.println("Press 'B' to View balances for a specific account");
		System.out.println("Press 'C' to make a deposit for a specific account");
		System.out.println("Press 'D' to Make a withdrawal for a specific account");
		System.out.println("Press 'E' to post a money transfer to a specific account");
		System.out.println("Press 'F' to view and/or accept pending money transfers");
		System.out.println("Press 'G' to view current user details");
		System.out.println("Press 'H' to view all account balances");
		System.out.println("Press 'Q' to Logout\n");
	}
	// Logout Screen
	private void LogOut() {
		System.out.println("***************");
		System.out.println("Log Out Successful. Returning to Login screen.\n");
		System.out.println("***************\n");
	}
	// Case A functionality, create new bank account
	private void CreateNewBankAccount(Scanner scanner) {
		System.out.println("****Creating new bank account****");
		System.out.println("How much would you like to deposit as your starting balance?");
		float startingBal = scanner.nextFloat();
		this.customerDAO.ApplyBankAccount(startingBal);
	}
	
	// Case B functionality, view a specific balance
	private void ViewBalance(Scanner scanner) {
		System.out.println("Please enter the Account ID of the proper account to view the balance.");
		int AccountNum = scanner.nextInt();
		this.customerDAO.ViewSpecificBalance(AccountNum);
	}
	
	// Case C functionality, make a deposit
	private void MakeDeposit(Scanner scanner) {
		System.out.println("Please enter the Account ID of the account you wish to make a deposit: ");
		int AccountNum = scanner.nextInt();
		System.out.println("Please enter the amount you wish to deposit:");
		float depositAmount = scanner.nextFloat();
		this.customerDAO.MakeDeposit(AccountNum, depositAmount);
	}

	// Case D functionality, make a withdrawal
	private void MakeWithdrawal(Scanner scanner) {
		System.out.println("Please enter the Account ID of the account you wish to make a withdrawal: ");
		int AccountNum = scanner.nextInt();
		System.out.println("Please enter the amount you wish to withdraw:");
		float withdrawalAmount = scanner.nextFloat();
		this.customerDAO.MakeWithdrawal(AccountNum, withdrawalAmount);
	}
	
	// Case E functionality, post a money transfer
	private void PostMoneyTransfer(Scanner scanner) {
		System.out.println("Please enter the Account ID from which you would like to transfer money: ");
		int AccountNum = scanner.nextInt();
		System.out.println("Please enter the Account ID which you would like to send money: ");
		int AccountNum1 = scanner.nextInt();
		System.out.println("Please enter the amount which you would like to transfer: ");
		float transferAmount = scanner.nextFloat();
		this.customerDAO.PostMoneyTransfer(this.KeyID, AccountNum, AccountNum1, transferAmount);
	}
	
	// Case F Functionality, view pending money transfers
	private void ViewMoneyTransfers(Scanner scanner) {
		this.customerDAO.ViewMoneyTransfers();
		System.out.println("Please enter the Transaction ID of a money transfer received that you would like to approve: ");
		int transactID = scanner.nextInt();
		this.customerDAO.AcceptMoneyTransfer(transactID);
	}
}
