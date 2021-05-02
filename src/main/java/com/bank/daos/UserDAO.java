package com.bank.daos;

import java.util.List;

import com.bank.models.User;

public interface UserDAO {
	public List<User> getAll();
	public User findById(int id);
	public boolean addItem(User a);
}
