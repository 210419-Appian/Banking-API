package com.bank.daos;

import java.util.List;

import com.bank.models.AccountStatus;

public interface AccountStatusDAO {
	public List<AccountStatus> getAll();
	public AccountStatus findById(int id);
	public int addItem(AccountStatus a);
	public boolean removeItemGivenId(int id);
}
