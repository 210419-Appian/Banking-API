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
	
	public int getUserId() {
		return userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
		//TODO: Update database, check for problems
	}
	
	public void setUsername(String username) {
		this.username = username;
		//TODO: Update database, check for problems
	}
	
	public void setPassword(String password) {
		this.password = password;
		//TODO: Update database, check for problems
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		//TODO: Update database, check for problems
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
		//TODO: Update database, check for problems
	}
	
	public void setEmail(String email) {
		this.email = email;
		//TODO: Update database, check for problems
	}
	
	public void setRole(Role role) {
		this.role = role;
		//TODO: Update database, check for problems
	}
	
	public void removeUser() {
		//TODO: This method should remove a user from the database
	}
	
}
