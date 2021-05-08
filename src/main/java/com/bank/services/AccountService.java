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
	
	public boolean removeAccountById(int id) {
		return myAccountDAO.removeItemGivenId(id);
	}
	
	public boolean updateAccount(Account myAccount, User myUser) {
		return myAccountDAO.update(myAccount, myUser);
	}
	
	public boolean withdraw(Account myAccount, double value, User myUser) {
		boolean success = false;
		double balance = myAccount.getBalance();
		
		if(balance >= value) {
			success = true;
			myAccount.setBalance(balance-value);
			updateAccount(myAccount, myUser);
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
	
}
