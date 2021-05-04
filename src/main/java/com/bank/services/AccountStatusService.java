package com.bank.services;

import java.util.List;

import com.bank.daos.AccountStatusDAO;
import com.bank.daos.AccountStatusDAOImpl;
import com.bank.models.AccountStatus;

public class AccountStatusService {

	private AccountStatusDAO myAccountStatusDAO = new AccountStatusDAOImpl();

	public List<AccountStatus> getAllAccountStatus() {
		return myAccountStatusDAO.getAll();
	}

	public AccountStatus getAccountStatusById(int id) {
		return myAccountStatusDAO.findById(id);
	}

	public boolean addAccountStatus(AccountStatus myAccountStatus) {
		int newId = myAccountStatusDAO.addItem(myAccountStatus);
		if(newId == -1) {
			return false;
		}else {
			myAccountStatus.setStatusId(newId);
			return true;
		}
	}
	
	public boolean removeAccountStatusById(int id) {
		return myAccountStatusDAO.removeItemGivenId(id);
	}

	
//	 public static void main(String args[]) {
//		 AccountStatusService srv = new AccountStatusService();
//		 
//		 AccountStatus myAccountStatus = new AccountStatus("Test");
//		 
//		 System.out.println(srv.addAccountStatus(myAccountStatus)); 
//		 System.out.println(srv.getAllAccountStatus());
//		 System.out.println(srv.getAccountStatusById(1));
//		 srv.removeAccountStatusById(myAccountStatus.getStatusId());
//		 
//		 
//	 }
	 
}
