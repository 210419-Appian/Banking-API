package models;

public class Account {
	private int accountId; // primary key
	private double balance;  // not null
	private AccountStatus status;
	private AccountType type;
	
	Account(int accountID, double balance, AccountStatus status, AccountType type){
		this.accountId = accountID;
		this.balance = balance;
		this.status = status;
		this.type = type;
	}
}
