package com.bank.services;

import java.util.ArrayList;
import java.util.List;

import com.bank.daos.UserDAO;
import com.bank.daos.UserDAOImpl;
import com.bank.models.Account;
import com.bank.models.Role;
import com.bank.models.User;
import com.bank.models.UserDTO;

public class UserService {


	private UserDAO myUserDAO = new UserDAOImpl();

	public List<User> getAllUser() {
		return myUserDAO.getAll();
	}

	public User getUserById(int id) {
		return myUserDAO.findById(id);
	}
	
	public User getUserByUsername(String username) {
		return myUserDAO.findByUsername(username);
	}

	public User addUser(User myUser) {
		return myUserDAO.addItem(myUser);
	}
	
	public boolean removeUserById(int id) {
		return myUserDAO.removeItemGivenId(id);
	}

	public boolean updateUser(User u) {
		return myUserDAO.update(u);
	}
	
}
