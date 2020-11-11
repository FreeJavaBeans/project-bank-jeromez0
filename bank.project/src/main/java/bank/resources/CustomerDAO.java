package bank.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import bank.models.BankAccount;
import bank.models.Customer;
import bank.models.MoneyTransfers;
import bank.models.Transactions;
import bank.models.User;
import bank.util.ConnectionUtility;

public class CustomerDAO {
	
	private int KeyID;
	private Customer CurrentCustomer = new Customer();
	private User CurrentUser;
	private int routingID = 123456789;
	private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
	
	// Primary constructor method
	public CustomerDAO(int KeyID, User current) {
		this.KeyID = KeyID;
		this.CurrentUser = current;
		Connection conn = cu.getConnection();
		String SQLgetCustomerInformation = "select * from \"BankApplication\".customers where \"KeyID\" = ?";
		try {	
			PreparedStatement prepStatement = conn.prepareStatement(SQLgetCustomerInformation);
			prepStatement.setInt(1, this.KeyID);		
			ResultSet results = prepStatement.executeQuery();				
			while(results.next()) {		
				CurrentCustomer.CustomerSetter(results, CurrentCustomer);
			}	
		}catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	// Apply for Bank Account method
	public boolean ApplyBankAccount(double startingBalance) {
		if (startingBalance < 0) {
			System.out.println("Impossible to start with a balance less than 0.");
			return false;
		}
		// generate a random accounting number
		Random rand = new Random();
		int accountNumber = 100000000 + rand.nextInt(900000000);
		// generate a timestamp
		Date date = new Date();  
		Timestamp ts=new Timestamp(date.getTime());  
		String time = ts.toString();	
		Connection conn = cu.getConnection();
		String createNewBankAccount = "insert into \"BankApplication\".BankAccounts (\"KeyID\", \"AccountID\", \"RoutingID\", \"Balance\", \"Approval\", \"DateCreated\") " + 
				  "values (?,?,?,?,false,?)";
		try {		
			PreparedStatement prepStatement = conn.prepareStatement(createNewBankAccount);
			prepStatement.setInt(1, this.KeyID);
			prepStatement.setInt(2, accountNumber);
			prepStatement.setInt(3, this.routingID);
			prepStatement.setDouble(4, startingBalance);
			prepStatement.setTimestamp(5, ts);
			prepStatement.execute();			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Account with AccountID already exists.");
			return false;
		}
		System.out.println("\n**********");
		System.out.println("Account Number: " + accountNumber);
		System.out.println("Routing ID: " + this.routingID);
		System.out.println("Date Created: " + time);
		System.out.println("Current Balance: " + startingBalance);
		System.out.println("Approval Status: pending");
		System.out.println("****New Account Successfully Created****\n");
		return true;
	}
	// Viewing a specific account balance
	public void ViewSpecificBalance(int AccountNum) {
		Connection conn = cu.getConnection();
		String SQLviewAccountBalance = "select * from \"BankApplication\".BankAccounts where \"KeyID\" = ? and \"AccountID\" = ?";		
		try {	
			PreparedStatement prepStatement = conn.prepareStatement(SQLviewAccountBalance);
			prepStatement.setInt(1, this.KeyID);
			prepStatement.setInt(2, AccountNum);
			ResultSet results = prepStatement.executeQuery();				
			System.out.println("\n****Viewing Balance****\n");
			while(results.next()) {			 
				BankAccount Account = new BankAccount();
				Account.printBankAccount(Account.AccountSetter(results, Account));
			}		
		}catch (SQLException e) {
			e.printStackTrace();
			return;
		}	
	}
	// View all account balances
	public void ViewAllBalances() {
		Connection conn = cu.getConnection();
		String SQLviewBalances = "select * from \"BankApplication\".BankAccounts where \"KeyID\" = ?";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(SQLviewBalances);
			prepStatement.setInt(1,  this.KeyID);
			ResultSet results = prepStatement.executeQuery();
			System.out.println("\n****Viewing All Balances****\n");
			while(results.next()) {			 
				BankAccount Account = new BankAccount();
				Account.printBankAccount(Account.AccountSetter(results, Account));
			}		
		}catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}
	// Making a deposit
	public void MakeDeposit(int AccountNum, double depositAmount) {
		if (this.getApproval(AccountNum) == false) {
			System.out.println("\n****Invalid Deposit. Account not approved.****\n");
			return;
		}
		if (depositAmount < 0) {
			System.out.println("\n**Invalid Deposit. Can only make deposits of a positive amount.**\n");
			return;
		}
		double currentBalance = this.GetBal(AccountNum);
		double newBalance = currentBalance + depositAmount;
		Connection conn = cu.getConnection(); 	
		String SQLmakeDeposit = "Update \"BankApplication\".BankAccounts set \"Balance\" = ? where \"KeyID\" = ? and \"AccountID\" = ?";
		Transactions Transaction = new Transactions();
		Transaction.TransactionSetter(this.KeyID, "Deposit", AccountNum, depositAmount, Transaction);
		try {	
			PreparedStatement prepStatement = conn.prepareStatement(SQLmakeDeposit);
			prepStatement.setDouble(1, newBalance);
			prepStatement.setInt(2, this.KeyID);
			prepStatement.setInt(3, AccountNum);	
			prepStatement.execute();		
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Invalid Authorization or Account ID");
			return;
		}
		System.out.println("****Deposit Information****");
		System.out.println("Account ID: " + AccountNum);
		System.out.println("Previous Balance: " + currentBalance);
		System.out.println("New Balance: " + newBalance);
		System.out.println("**************************\n");
		return;
	}
	// Making a withdrawal
	public void MakeWithdrawal(int AccountNum, double withdrawalAmount) {
		double currentBalance = this.GetBal(AccountNum);
		double newBalance = currentBalance - withdrawalAmount;
		if (newBalance < 0) {
			System.out.println("\nInvalid Withdrawal. Completing transaction would leave your bank account with less than $0.00.\n");
			return;
		}
		if (this.getApproval(AccountNum) == false) {
			System.out.println("\n****Invalid Withdrawal. Account not approved.****\n");
			return;
		}
		if (withdrawalAmount < 0) {
			System.out.println("\n****Invalid Withdrawal. Can only withdraw a positive amount.****\n");
			return;
		}	
		String makeWithdrawal = "Update \"BankApplication\".BankAccounts set \"Balance\" = ? where \"KeyID\" = ? and \"AccountID\" = ?";
		Connection conn = cu.getConnection(); 	
		Transactions Transaction = new Transactions();
		Transaction.TransactionSetter(this.KeyID, "Withdrawal", AccountNum, withdrawalAmount, Transaction);
		try {	
			PreparedStatement prepStatement = conn.prepareStatement(makeWithdrawal);
			prepStatement.setDouble(1, newBalance);
			prepStatement.setInt(2, this.KeyID);
			prepStatement.setInt(3, AccountNum);	
			prepStatement.execute();		
			this.insertTransaction(Transaction);
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Invalid Authorization or Account ID");
			return;
		}
		System.out.println("\n****Withdrawal Information****");
		System.out.println("Account ID: " + AccountNum);
		System.out.println("Previous Balance: " + currentBalance);
		System.out.println("New Balance: " + newBalance);
		System.out.println("**************************\n");
		return;
	}
	// Show customer details
	public void ShowCustomerDetails() {
		CurrentCustomer.showCustomerDetails(CurrentCustomer, CurrentUser);
		return;
	}
	// Post Money Transfers
	public boolean PostMoneyTransfer(int KeyID, int SenderAccountID, int RecipientAccountID, double Amount) {
		if (Amount < 0) {
			System.out.println("\n***Cannot post a transfer of less than $0.00.***\n");
			return false;
		}	
		if (Amount > this.GetBal(SenderAccountID)) {
			System.out.println("\n***Cannot post money transfer; insufficient funds***\n");
			return false;
		}
		if(this.getApproval(SenderAccountID) == false) {
			System.out.println("\n***Cannot post a money transfer with an unapproved account***\n");
			return false;
		}
		if(this.getApproval(RecipientAccountID) == false) {
			System.out.println("\n***Cannot post a money transfer to an unapproved account***\n");
			return false;
		}
		Connection conn = cu.getConnection();
		String postMoneyTransfer = "insert into \"BankApplication\".MoneyTransfers (\"KeyID\", \"AccountID\", \"RecipientAccountID\", \"Amount\", \"Approval\", \"DateCreated\") " +
				   "values (?,?,?,?,false,?)";
		Date date = new Date();  
		Timestamp ts=new Timestamp(date.getTime());  
		String time = ts.toString();		
		Transactions Transaction = new Transactions();
		Transaction.TransactionSetterMoneyTransfer(KeyID, SenderAccountID, RecipientAccountID,Amount,Transaction);
		this.insertTransaction(Transaction);
		try {		
			PreparedStatement prepStatement = conn.prepareStatement(postMoneyTransfer);
			prepStatement.setInt(1, KeyID);
			prepStatement.setInt(2, SenderAccountID);
			prepStatement.setInt(3, RecipientAccountID);
			prepStatement.setDouble(4, Amount);
			prepStatement.setTimestamp(5, ts);
			prepStatement.execute();			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Money Transfer failed.");
			return false;
		}
		System.out.println("\n**********");
		System.out.println("Sender Account Number: " + SenderAccountID);
		System.out.println("Recipient Account ID: " + RecipientAccountID);
		System.out.println("Amount: " + Amount);
		System.out.println("Date Created: " + time);		
		System.out.println("Approval Status: pending");
		System.out.println("****Money Transfer Posted Successfully****\n");
		return true;
	}
	// View Money Transfers
	public void ViewMoneyTransfers() {
		Connection conn = cu.getConnection();
		String viewMoneyTransfersRecd = "select * from \"BankApplication\".MoneyTransfers where \"Approval\" = false and \"RecipientAccountID\" in (select \"AccountID\" from \"BankApplication\".bankaccounts where \"KeyID\" = ?)";
		String viewMoneyTransfersPosted = "select * from \"BankApplication\".MoneyTransfers where \"Approval\" = false and \"AccountID\" in (select \"AccountID\" from \"BankApplication\".bankaccounts where \"KeyID\" = ?)";
		
		try {	
			PreparedStatement prepStatement = conn.prepareStatement(viewMoneyTransfersRecd);
			prepStatement.setInt(1, this.KeyID);
			ResultSet results = prepStatement.executeQuery();				
			System.out.println("\n****Viewing Money Transfers Received****\n");
			while(results.next()) {			 
				MoneyTransfers Transfer = new MoneyTransfers();
				Transfer.printReceievedMoneyTransfers(Transfer.TransferSetter(results, Transfer));
			}		
		}catch (SQLException e) {
			e.printStackTrace();
			return;
		}		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(viewMoneyTransfersPosted);
			prepStatement.setInt(1, this.KeyID);
			ResultSet results = prepStatement.executeQuery();				
			System.out.println("\n****Viewing Money Transfers Posted****");
			while(results.next()) {			 
				MoneyTransfers Transfer = new MoneyTransfers();
				Transfer.printSentMoneyTransfers(Transfer.TransferSetter(results, Transfer));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return;
		}	
	}
	// Accept Money Transfers
	public boolean AcceptMoneyTransfer(int transactID) {	
		int Recipient, Sender;
		double Amount;
		Connection conn = cu.getConnection();
		String gatherInfo = "select \"AccountID\", \"RecipientAccountID\", \"Amount\" from \"BankApplication\".MoneyTransfers where \"TransactionID\" = ? and \"RecipientAccountID\" in " + 
				"(select \"AccountID\" from \"BankApplication\".bankaccounts where \"KeyID\" = ? and \"Approval\" = true)";
		
		try {	
			PreparedStatement prepStatement = conn.prepareStatement(gatherInfo);
			prepStatement.setInt(1, transactID);
			prepStatement.setInt(2, this.KeyID);
			ResultSet results = prepStatement.executeQuery();				
			while(results.next()) {			 
				Sender = results.getInt("AccountID");
				Recipient = results.getInt("RecipientAccountID");
				Amount = results.getDouble("Amount");
				double currBal = this.GetBal(Recipient);
				this.MakeTrans(Amount,Recipient);
				this.MakeTrans((Amount * -1), Sender);
				this.updateTransfer(transactID);
				System.out.println("\n****Transaction Completed****");
				System.out.println("Previous balance: " + currBal);
				System.out.println("New balance: " + this.GetBal(Recipient));
				System.out.println("****************************\n");
				return true;
			}	
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	// this method completes transactions by updating balances
	private boolean MakeTrans(double Amount, int AccountID) {
		double newTotal = this.GetBal(AccountID) + Amount;
		Connection conn = cu.getConnection();
		String makeTrans = "Update \"BankApplication\".bankaccounts set \"Balance\" = ? where \"AccountID\" = ?";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(makeTrans);
			prepStatement.setDouble(1,  newTotal);
			prepStatement.setInt(2, AccountID);
			prepStatement.execute();
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Invalid Transaction");
			return false;
		}
		return true;
	}
	// this method updates transactions by updating the money transfer in SQL
	private void updateTransfer(int TransID) {
		Date date = new Date();  
		Timestamp ts=new Timestamp(date.getTime());  
		Connection conn = cu.getConnection();
		String updateTransfer = "update \"BankApplication\".moneytransfers set \"Approval\" = true, \"DateApproved\" = ? where \"TransactionID\" = ? and \"RecipientAccountID\" in " +
				 "(select \"AccountID\" from \"BankApplication\".bankaccounts where \"KeyID\" = ?)";
		try {
			PreparedStatement prepStatement = conn.prepareStatement(updateTransfer);
			prepStatement.setTimestamp(1, ts);
			prepStatement.setInt(2, TransID);
			prepStatement.setInt(3, this.KeyID);
			prepStatement.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// this method finds the balance of an account
	private double GetBal(int AccountNum) {
		double bal;
		String findBalance = "select \"Balance\" from \"BankApplication\".BankAccounts where \"KeyID\" = ? and \"AccountID\" = ?";
		Connection conn = cu.getConnection();
		try {
			PreparedStatement prepStatement = conn.prepareStatement(findBalance);
			prepStatement.setInt(1,  this.KeyID);
			prepStatement.setInt(2,  AccountNum);
			ResultSet results = prepStatement.executeQuery();
			while(results.next()) {
				bal = results.getDouble("Balance");
				return bal;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	// this method finds the approval status of an account
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
	// this method inserts transactions into the database
	private void insertTransaction(Transactions Transaction) {
		Connection conn = cu.getConnection();
		String SQLinsertTransaction = "insert into \"BankApplication\".Transactions (\"KeyID\",\"Type\", \"AccountID\", \"RecipientAccountID\", \"Amount\",\"DateCreated\") " +
				   "values (?,?,?,?,?,?)";
		try {		
			PreparedStatement prepStatement = conn.prepareStatement(SQLinsertTransaction);
			prepStatement.setInt(1, Transaction.getKeyID());
			prepStatement.setString(2, Transaction.getType());
			prepStatement.setInt(3, Transaction.getAccountID());
			prepStatement.setDouble(4, Transaction.getRecipientAccountID());
			prepStatement.setDouble(5, Transaction.getAmount());
			Date date = new Date();  
			Timestamp ts=new Timestamp(date.getTime());  
			prepStatement.setTimestamp(6,  ts);
			prepStatement.execute();			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Failed to enter transaction.");
		}		
	}
}
