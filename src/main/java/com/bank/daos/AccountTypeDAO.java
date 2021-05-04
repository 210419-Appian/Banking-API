package com.bank.daos;

import java.util.List;

import com.bank.models.AccountType;

public interface AccountTypeDAO {
	public List<AccountType> getAll();
	public AccountType findById(int id);
	public int addItem(AccountType a);
	public boolean removeItemGivenId(int id);
}
