package models;

public class AccountType {
	private int typeId; // primary key
	private String type; // not null, unique
	
	public AccountType(int typeId, String type){
		this.typeId = typeId;
		this.type = type;
	}
}
