package bank.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bank.services.*;


public class BankAppLauncher {
	
	public static void main(String[] args) {
//		ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
//		Connection conn = cu.getConnection();
		
		Logger logger = LogManager.getLogger("bank.project");
		logger.debug("This is a test");
		
		LoginUserInterface UI = new LoginUserInterface();
		System.out.println("============================================");
		System.out.println("Hello and welcome to the banking application");		
		System.out.println("============================================\n");
		
		UI.LoginMenu();
		
	}
}
