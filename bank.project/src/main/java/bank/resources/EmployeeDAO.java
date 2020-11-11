package bank.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bank.models.BankAccount;
import bank.models.Transactions;
import bank.repositories.EmployeeRepository;
import bank.util.ConnectionUtility;

public class EmployeeDAO implements EmployeeRepository{
	
	private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
	
	public EmployeeDAO() {
		super();
	}
	// this method allows the employee to view pending accounts
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
	// this method allows employee to approve a pending account
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
	// this method lets employee view customer bank accounts
	public boolean viewCustomerAccounts(int KeyID) {
		String sqlQuery = "select * from \"BankApplication\".bankaccounts where \"KeyID\" = ?";
		Connection conn = cu.getConnection();
		if (this.getAccountType(KeyID) == false) {
			return false;
		}
		try {
			PreparedStatement prepStatement = conn.prepareStatement(sqlQuery);
			prepStatement.setInt(1,  KeyID);
			ResultSet results = prepStatement.executeQuery();				
			System.out.println("\n****Viewing Customer's Accounts****\n");
			while(results.next()) {			 
				BankAccount Account = new BankAccount();
				Account.printBankAccount(Account.AccountSetter(results, Account));
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();			
		}
		return false;
	}
	// this method lets employee view all past transactions
	public void viewTransactions() {
		Connection conn = cu.getConnection();
		String getAllTransactions = "select * from \"BankApplication\".Transactions";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(getAllTransactions);
			ResultSet results = prepStatement.executeQuery();
			Transactions Transaction = new Transactions();
			while (results.next()) {
				Transaction.printTransaction(results);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	// this method checks if an account has approval
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
	// this method checks if a user or customer account exists
	private boolean getAccountType(int keyID ) {
		Connection conn = cu.getConnection();
		String SQLgetApproval = "select \"AccountType\" from \"BankApplication\".UserAuth where \"KeyID\" = ?";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(SQLgetApproval);
			prepStatement.setInt(1,  keyID);
			ResultSet results = prepStatement.executeQuery();
			while (results.next()) {
				boolean bool = results.getBoolean("AccountType");
				if (bool == true)
						return true;
				else
						return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
