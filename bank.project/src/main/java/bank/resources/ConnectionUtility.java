package bank.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionUtility {
	//leverage a design pattern called the singleton pattern
	// singleton pattern involves making a private constructor
	// no other class can instance this object
	// store a static reference to the single copy of the object made
	// provide a public method to access that single instance
		
	// single instance of the connection utility
	private static ConnectionUtility singleton = new ConnectionUtility(); // call private constructor once
	
	private Connection conn;
	
	// private constructor so no one else can call
	private ConnectionUtility(){
		super();
		String username = System.getenv("DB_USERNAME");
		String password = System.getenv("DB_PASSWORD");
		String url = System.getenv("DB_URL");
		try {
			this.conn = DriverManager.getConnection(url, username, password);
			if (this.conn != null && !conn.isClosed()) {
				System.out.println("\nConnected to application");
				System.out.println("URL: " + url);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to Connect to DB");
			System.out.println("Username: " + username);
			System.out.println("Password: " + password);
			System.out.println("URL: " + url);
		}
	}
	// public getter for the static reference
	public static ConnectionUtility getConnectionUtility() {
		return singleton;	
	}
	
	public Connection getConnection() {
		return conn;
	}
}