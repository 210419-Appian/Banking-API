package com.bank.daos;

import java.util.List;

import com.bank.models.User;

public interface UserDAO {
	public List<User> getAll();
	public User findById(int id);
	public User addItem(User a);
	public boolean removeItemGivenId(int id);
	public boolean update(User a);
	public User findByUsername(String username);
}
