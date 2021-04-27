package models;

import exceptions.models.InvalidNonNullValue;
import exceptions.models.InvalidPrimaryKey;
import exceptions.models.InvalidUniqueValue;

public class Role {
	private int roleId; // primary key
	private String role; // not null, unique
	
	public Role(int roleId, String role) throws InvalidPrimaryKey, InvalidUniqueValue, InvalidNonNullValue{
		this.roleId = roleId;
		this.role = role;
		//TODO: Check database to see if roleID is unique!
		//TODO: Check database to see if Role is unique!
		//Note: RoleId is int and can NOT be null, so we don't need to check.
		if(role.equals(null)) {
			throw new InvalidNonNullValue("role was a null value!");
		}
	}
	
	public int getRoleId() {
		return roleId;
	}
	
	public String getRole() {
		return role;
	}
}
