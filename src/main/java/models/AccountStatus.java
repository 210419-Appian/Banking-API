package models;

public class AccountStatus {
	private int statusId; // primary key
	private String status; // not null, unique
	
	AccountStatus(int statusID, String status){
		this.statusId = statusID;
		this.status = status;
	}
}
