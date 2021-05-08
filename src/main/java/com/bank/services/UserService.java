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
	
	public boolean validateLogin(UserDTO udto) {
		boolean success = false;
		User myUser = getUserByUsername(udto.username);
		
		if(myUser != null) {
			if(udto.password.equals(myUser.getPassword())) {
				success = true;
			}
		}
		
		return success;
	}
	
//	public static void main(String args[]) {
//		UserService srv = new UserService();
//		Role r = new Role();
//
//		r.setRoleId(1);
//
//		User us = new User(1, "cooldude2021", "1234password", "Lee", "Brummitt", "lee@lee.lee", r,
//				new ArrayList<Account>());
//
//		System.out.println(srv.addUser(us));
//		System.out.println(srv.getAllUser());
//		System.out.println(srv.getUserById(1));
//		
//		us.setUsername("cooldude20");
//		System.out.println(srv.updateUser(us));
//		System.out.println(srv.getAllUser());
//		
//		System.out.println(srv.removeUserById(us.getUserId()));
//		System.out.println(srv.getAllUser());
//
//	}
	
}
