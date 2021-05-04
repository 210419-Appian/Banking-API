package com.bank.services;

import java.util.ArrayList;
import java.util.List;

import com.bank.daos.UserDAO;
import com.bank.daos.UserDAOImpl;
import com.bank.models.Account;
import com.bank.models.Role;
import com.bank.models.User;

public class UserService {


	private UserDAO myUserDAO = new UserDAOImpl();

	public List<User> getAllUser() {
		return myUserDAO.getAll();
	}

	public User getUserById(int id) {
		return myUserDAO.findById(id);
	}

	public boolean addUser(User myUser) {
		int newId = myUserDAO.addItem(myUser);
		if(newId == -1) {
			return false;
		}else {
			myUser.setUserId(newId);
			return true;
		}
	}
	
	public boolean removeUserById(int id) {
		return myUserDAO.removeItemGivenId(id);
	}

	
//	 public static void main(String args[]) {
//		 UserService srv = new UserService();
//		 Role r = new Role();
//		 
//		 
//		 r.setRoleId(1);
//		 
//		 User us = new User(1, "cooldude2021", "1234password","Lee", "Brummitt", "lee@lee.lee", r, new ArrayList<Account>());
//		 
//		 System.out.println(srv.addUser(us)); 
//		 System.out.println(srv.getAllUser());
//		 System.out.println(srv.getUserById(1));
//		 srv.removeUserById(us.getUserId());
//		 System.out.println(srv.getAllUser());
//		 
//	 }
	
	
}
