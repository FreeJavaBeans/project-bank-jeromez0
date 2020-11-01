package bank.app;

import java.sql.Connection;
import java.sql.SQLException;

import bank.resources.ConnectionUtility;
import bank.resources.UserAuthenticationDAO;

public class BankAppLauncher {
	
	public static void main(String[] args) {
//		ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
//		Connection conn = cu.getConnection();
		
		UserAuthenticationDAO us = new UserAuthenticationDAO();
		
	}
}
