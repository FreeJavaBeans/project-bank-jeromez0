package bank.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import bank.resources.ConnectionUtility;
import bank.resources.UserAuthenticationDAO;



public class BankAppLauncher {
	
	public static void main(String[] args) {
//		ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
//		Connection conn = cu.getConnection();
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Hello and welcome to the banking application");		
		
		System.out.println("Enter a username:");		
		String username = scanner.next();
		
		System.out.println("Enter a password:");
		String password = scanner.next();
		
		UserAuthenticationDAO us = new UserAuthenticationDAO(username,password);
		
		
		
		
		
	}
}
