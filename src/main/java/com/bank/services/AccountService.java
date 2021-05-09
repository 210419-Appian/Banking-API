package com.bank.services;

import java.util.List;

import com.bank.daos.AccountDAO;
import com.bank.daos.AccountDAOImpl;
import com.bank.models.Account;
import com.bank.models.AccountStatus;
import com.bank.models.AccountType;
import com.bank.models.User;

public class AccountService {

	private AccountDAO myAccountDAO = new AccountDAOImpl();

	public List<Account> getAllAccount() {
		return myAccountDAO.getAll();
	}

	public Account getAccountById(int id) {
		return myAccountDAO.findById(id);
	}

	public boolean addAccount(Account myAccount, User myUser) {
		int newId = myAccountDAO.addItem(myAccount, myUser);
		if(newId == -1) {
			return false;
		}else {
			myAccount.setAccountId(newId);
			return true;
		}
	}
	
	public boolean addAccount(Account myAccount) {
		int newId = myAccountDAO.addItem(myAccount);
		if(newId == -1) {
			return false;
		}else {
			myAccount.setAccountId(newId);
			return true;
		}
	}
	
	public boolean removeAccountById(int id) {
		return myAccountDAO.removeItemGivenId(id);
	}
	
	public boolean updateAccount(Account myAccount, User myUser) {
		return myAccountDAO.update(myAccount, myUser);
	}
	
	public boolean updateAccount(Account myAccount) {
		return myAccountDAO.update(myAccount);
	}
	
	public boolean withdraw(Account myAccount, double value) {
		boolean success = false;
		double balance = myAccount.getBalance();
		
		if(balance >= value) {
			success = true;
			myAccount.setBalance(balance-value);
			updateAccount(myAccount);
		}
		
		return success;
	}
	
	public boolean checkOwner(Account myAccount, int userId) {
		boolean success = false;
		
		if(myAccountDAO.getOwnerId(myAccount) == userId) {
			success = true;
		}
		
		return success;
	}

	public void deposit(Account myAccount, double value) {
		double balance = myAccount.getBalance();
		
		myAccount.setBalance(balance+value);
		updateAccount(myAccount);
	}

	public List<Account> getAccountsByStatus(int statusId) {
		return myAccountDAO.findByStatus(statusId);
	}

	public List<Account> getAccountsByUser(int ownerId) {
		return myAccountDAO.findByOwner(ownerId);
	}
	
}
