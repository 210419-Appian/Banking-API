package com.bank.daos;

import java.util.List;

import com.bank.models.Role;

public interface RoleDAO {
	public List<Role> getAll();
	public Role findById(int id);
	public int addItem(Role a);
	public boolean removeItemGivenId(int id);
}
