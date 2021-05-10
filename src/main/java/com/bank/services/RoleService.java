package com.bank.services;

import java.util.List;

import com.bank.daos.RoleDAO;
import com.bank.daos.RoleDAOImpl;
import com.bank.models.Role;

public class RoleService {
	
	private RoleDAO myRoleDAO = new RoleDAOImpl();

	public List<Role> getAllRole() {
		return myRoleDAO.getAll();
	}

	public Role getRoleById(int id) {
		return myRoleDAO.findById(id);
	}

	public boolean addRole(Role myRole) {
		int newId = myRoleDAO.addItem(myRole);
		if(newId == -1) {
			return false;
		}else {
			myRole.setRoleId(newId);
			return true;
		}
	}
	
	public boolean removeRoleById(int id) {
		return myRoleDAO.removeItemGivenId(id);
	}
	
	public boolean updateRole(Role myRole) {
		return myRoleDAO.update(myRole);
	}
	 
}
