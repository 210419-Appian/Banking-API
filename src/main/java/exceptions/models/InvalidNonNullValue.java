package exceptions.models;

public class InvalidNonNullValue extends Exception {
	public InvalidNonNullValue(){
		super();
	}
	
	public InvalidNonNullValue(String message){
		super(message);
	}
}
