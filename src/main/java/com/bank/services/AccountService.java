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
	
//	 public static void main(String args[]) {
//		 AccountService srv = new AccountService();
//		 AccountStatus as = new AccountStatus();
//		 AccountType at = new AccountType();
//		 User us = new User();
//		 as.setStatusId(1);
//		 at.setTypeId(1);
//		 us.setUserId(1);
//		 
//		 Account myAccount = new Account(100, as, at);
//		 
//		 System.out.println(srv.addAccount(myAccount, us)); 
//		 System.out.println(srv.getAllAccount());
//		 System.out.println(srv.getAccountById(1));
//		 
//		 //update
//		 myAccount.setBalance(500);
//		 System.out.println(srv.updateAccount(myAccount, us));
//		 
//		 System.out.println(srv.getAllAccount());
//		 
//		 srv.removeAccountById(myAccount.getAccountId());
//		 
//		 System.out.println(srv.getAllAccount());
//		 
//		 
//	 }
	
}
