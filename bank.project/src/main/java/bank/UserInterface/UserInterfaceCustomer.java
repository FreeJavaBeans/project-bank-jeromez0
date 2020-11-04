package bank.UserInterface;

import java.util.Scanner;
import bank.resources.CustomerDAO;
import bank.users.User;

public class UserInterfaceCustomer {
	
	int KeyID;
	CustomerDAO customerDAO;
	
	UserInterfaceCustomer(int keyID, User current) {
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
				case 'G':
					System.out.println("Still under construction");
				case 'H': 
					customerDAO.ShowCustomerDetails();
				case 'Q':
					break;
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
		System.out.println("Press 'B' to View balances for all accounts");
		System.out.println("Press 'C' to View balances for a specific account");
		System.out.println("Press 'D' to Make a withdrawal for a specific account");
		System.out.println("Press 'E' to make a deposit for a specific account");
		System.out.println("Press 'F' to post a money transfer to a specific account");
		System.out.println("Press 'G' to view and/or accept pending money transfers");
		System.out.println("Press 'H' to view current user details");
		System.out.println("Press 'Q' to Logout\n");
	}
}
