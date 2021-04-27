package models;

public class User {
	private int userId; // primary key
	private String username; // not null, unique
	private String password; // not null
	private String firstName; // not null
	private String lastName; // not null
	private String email; // not null
	private Role role;
	
	public User(int userId, String username, String password, String firstName, String lastName, String email, Role role){
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		
		//TODO: Check if this is already on the database
		//TODO: Check database to see if valid
	}
	
	public User(int userId) {
		//TODO: Create user using database information
	}
	
	
}
