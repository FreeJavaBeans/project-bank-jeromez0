package bank.services;

import java.util.Scanner;

import bank.models.User;
import bank.resources.CustomerDAO;

public class CustomerUserInterface {
	
	int KeyID;
	CustomerDAO customerDAO;
	
	CustomerUserInterface(int keyID, User current) {
		this.KeyID = keyID;
		this.customerDAO = new CustomerDAO(this.KeyID, current);
	}
	
	public void CustomerMenu() {
		Scanner scanner = new Scanner(System.in);
		char option = '\0';
		do {
			this.CustomerOptions();
			System.out.println("Enter an option: ");
			option = scanner.next().charAt(0);		
			
			switch(option) {
				case 'A':
					System.out.println("****Creating new bank account****");
					System.out.println("How much would you like to deposit as your starting balance?");
					float startingBal = scanner.nextFloat();
					this.customerDAO.ApplyBankAccount(startingBal);
					break;
				case 'B':
					System.out.println("Please enter the Account ID of the proper account to view the balance.");
					int AccountNum = scanner.nextInt();
					this.customerDAO.ViewSpecificBalance(AccountNum);
					break;
				case 'C':
					System.out.println("Please enter the Account ID of the account you wish to make a deposit: ");
					int AccountNum1 = scanner.nextInt();
					System.out.println("Please enter the amount you wish to deposit:");
					float depositAmount = scanner.nextFloat();
					this.customerDAO.MakeDeposit(AccountNum1, depositAmount);
					break;
				case 'D':
					System.out.println("Please enter the Account ID of the account you wish to make a withdrawal: ");
					int AccountNum2 = scanner.nextInt();
					System.out.println("Please enter the amount you wish to withdraw:");
					float withdrawalAmount = scanner.nextFloat();
					this.customerDAO.MakeWithdrawal(AccountNum2, withdrawalAmount);
					break;
				case 'E':
					System.out.println("Please enter the Account ID from which you would like to transfer money: ");
					int AccountNum3 = scanner.nextInt();
					System.out.println("Please enter the Account ID which you would like to send money: ");
					int AccountNum4 = scanner.nextInt();
					System.out.println("Please enter the amount which you would like to transfer: ");
					float transferAmount = scanner.nextFloat();
					this.customerDAO.PostMoneyTransfer(this.KeyID, AccountNum3, AccountNum4, transferAmount);
					break;
				case 'F':
					this.customerDAO.ViewMoneyTransfers();
					System.out.println("Please enter the Transaction ID of a money transfer received that you would like to approve: ");
					int transactID = scanner.nextInt();
					this.customerDAO.AcceptMoneyTransfer(transactID);
					break;
				case 'G': 
					customerDAO.ShowCustomerDetails();
				default:
					System.out.println("Invalid option; please try again");
					break;
			}
		}while (option != 'Q');
		System.out.println("***************");
		System.out.println("Log Out Successful. Returning to Login screen.\n");
		System.out.println("***************\n");
	}
	
	private void CustomerOptions() {
		System.out.println("****Customer Screen****");
		System.out.println("Press 'A' to Apply for new bank account");
		System.out.println("Press 'B' to View balances for a specific account");
		System.out.println("Press 'C' to make a deposit for a specific account");
		System.out.println("Press 'D' to Make a withdrawal for a specific account");
		System.out.println("Press 'E' to post a money transfer to a specific account");
		System.out.println("Press 'F' to view and/or accept pending money transfers");
		System.out.println("Press 'G' to view current user details");
		System.out.println("Press 'Q' to Logout\n");
	}
}
