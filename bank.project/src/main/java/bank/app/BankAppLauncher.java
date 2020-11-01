package bank.app;

import java.sql.Connection;
import java.sql.SQLException;

import bank.resources.ConnectionUtility;

public class BankAppLauncher {
	
	public static void main(String[] args) throws SQLException {
	
		ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
		Connection conn = cu.getConnection();
		if (conn != null && !conn.isClosed()) {
			System.out.println("DB Connected");
		}
	}
}
