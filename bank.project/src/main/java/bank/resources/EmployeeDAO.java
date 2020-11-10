package bank.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bank.models.BankAccount;
import bank.repositories.EmployeeRepository;
import bank.util.ConnectionUtility;

public class EmployeeDAO implements EmployeeRepository{
	
	private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
	
	public EmployeeDAO() {
		super();
	}
	// view pending accounts
	public void viewPendingAccounts() {
		String sqlQuery = "select * from \"BankApplication\".bankaccounts where \"Approval\" = false";
		Connection conn = cu.getConnection();
		try {	
			PreparedStatement prepStatement = conn.prepareStatement(sqlQuery);
			ResultSet results = prepStatement.executeQuery();				
			System.out.println("\n****Viewing Unapproved Accounts****\n");
			while(results.next()) {			 
				BankAccount Account = new BankAccount();
				Account.printBankAccount(Account.AccountSetter(results, Account));
			}		
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Invalid Authorization or Account ID");
		}	
	}
	// approve a pending account
	public boolean approvePendingAccounts(int AccountID) {
		String sqlUpdate = "update \"BankApplication\".bankaccounts set \"Approval\" = true where \"AccountID\" = ?";
		Connection conn = cu.getConnection();
		try {	
			PreparedStatement prepStatement = conn.prepareStatement(sqlUpdate);
			prepStatement.setInt(1,  AccountID);
			prepStatement.execute();
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Invalid Authorization or Account ID");
			return false;
		}
		if (this.getApproval(AccountID) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	// view customer bank accounts
	public void viewCustomerAccounts(int KeyID) {
		String sqlQuery = "select * from \"BankApplication\".bankaccounts where \"KeyID\" = ?";
		Connection conn = cu.getConnection();
		try {
			PreparedStatement prepStatement = conn.prepareStatement(sqlQuery);
			prepStatement.setInt(1,  KeyID);
			ResultSet results = prepStatement.executeQuery();				
			System.out.println("\n****Viewing Customer's Accounts****\n");
			while(results.next()) {			 
				BankAccount Account = new BankAccount();
				Account.printBankAccount(Account.AccountSetter(results, Account));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewTransactions() {
		
	}
	
	private boolean getApproval(int AccountNum) {
		Connection conn = cu.getConnection();
		String SQLgetApproval = "select \"Approval\" from \"BankApplication\".BankAccounts where \"AccountID\" = ?";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(SQLgetApproval);
			prepStatement.setInt(1,  AccountNum);
			ResultSet results = prepStatement.executeQuery();
			while (results.next()) {
				boolean bool = results.getBoolean("Approval");
				if (bool == true)
						return true;
				if (bool == false)
						return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
