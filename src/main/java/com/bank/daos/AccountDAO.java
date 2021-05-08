package com.bank.daos;

import java.util.List;

import com.bank.models.Account;
import com.bank.models.User;

public interface AccountDAO {
	public List<Account> getAll();
	public Account findById(int id);
	public int addItem(Account a, User u);
	public boolean removeItemGivenId(int id);
	public List<Account> getUserAccounts(int id);
	public boolean update(Account a, User u);
	public int getOwnerId(Account myAccount);
}
