package com.bank.services;

import java.util.List;

import com.bank.daos.AccountTypeDAO;
import com.bank.daos.AccountTypeDAOImpl;
import com.bank.models.AccountType;

public class AccountTypeService {

	private AccountTypeDAO myAccountTypeDAO = new AccountTypeDAOImpl();

	public List<AccountType> getAllAccountType() {
		return myAccountTypeDAO.getAll();
	}

	public AccountType getAccountTypeById(int id) {
		return myAccountTypeDAO.findById(id);
	}

	public boolean addAccountType(AccountType myAccountType) {
		int newId = myAccountTypeDAO.addItem(myAccountType);
		if(newId == -1) {
			return false;
		}else {
			myAccountType.setTypeId(newId);
			return true;
		}
	}
	
	public boolean removeAccountTypeById(int id) {
		return myAccountTypeDAO.removeItemGivenId(id);
	}

	
	 public static void main(String args[]) {
		 AccountTypeService srv = new AccountTypeService();
		 
		 AccountType myAccountType = new AccountType("Test");
		 
		 System.out.println(srv.addAccountType(myAccountType)); 
		 System.out.println(srv.getAllAccountType());
		 System.out.println(srv.getAccountTypeById(1));
		 srv.removeAccountTypeById(myAccountType.getTypeId());
		 
		 
	 }
	
}
