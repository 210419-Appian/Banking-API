package models;

public class Role {
	private int roleId; // primary key
	private String role; // not null, unique
	
	Role(int roleId, String role){
		this.roleId = roleId;
		this.role = role;
	}
}
