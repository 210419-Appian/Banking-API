package com.bank.daos;

import java.util.List;

import com.bank.models.Account;

public interface AccountDAO {
	public List<Account> getAll();
	public Account findById(int id);
	public boolean addItem(Account a);
	
}
