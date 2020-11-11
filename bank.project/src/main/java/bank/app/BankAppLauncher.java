package bank.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bank.services.*;


public class BankAppLauncher {
	
	private static final Logger LOG = LogManager.getLogger(BankAppLauncher.class);
	
	public static void main(String[] args) {
	
		LOG.info("User has accessed banking application");
		LoginUserInterface UI = new LoginUserInterface();
		System.out.println("============================================");
		System.out.println("Hello and welcome to the banking application");		
		System.out.println("============================================\n");
		
		UI.LoginMenu();
		
	}
}
