package models;

public class AccountStatus {
	private int statusId; // primary key
	private String status; // not null, unique
	
	public AccountStatus(int statusID, String status){
		this.statusId = statusID;
		this.status = status;
	}
}
