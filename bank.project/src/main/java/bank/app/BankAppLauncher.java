package bank.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import bank.resources.ConnectionUtility;
import bank.resources.LoginDAO;
import bank.services.*;

public class BankAppLauncher {
	
	public static void main(String[] args) {
//		ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
//		Connection conn = cu.getConnection();
		
		LoginUserInterface UI = new LoginUserInterface();
		System.out.println("============================================");
		System.out.println("Hello and welcome to the banking application");		
		System.out.println("============================================\n");
		
		UI.LoginMenu();
		
	}
}
