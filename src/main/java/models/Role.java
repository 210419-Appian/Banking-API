package models;

public class Role {
	private int roleId; // primary key
	private String role; // not null, unique
	
	public Role(int roleId, String role) {
		this.roleId = roleId;
		this.role = role;
		//TODO: Check if this is already on the database
		//TODO: Check database to see if valid
	}
	
	public Role(int roleId) {
		//TODO: Create role using database information
	}
	
	public int getRoleId() {
		return roleId;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
		//TODO: Update the database and check for problems
	}
	
	public void setRole(String role) {
		this.role = role;
		//TODO: Update the database and check for problems
	}
	
	public void removeRole() {
		//TODO: This method should remove a role from the database
	}
}
