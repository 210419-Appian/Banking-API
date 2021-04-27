import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import models.*;

public class modelsTests {
	
	/*******************************************
	 * Role tests
	 *//////////////////////////////////////////
	@Test
	public void roleCreateTest() {
		Role myRole = new Role(-9, "AssociateTest");
		assertTrue((myRole.getRoleId() == -9) && (myRole.getRole() == "AssociateTest"));
		//TODO: Check if the database if working
		myRole.removeRole();
		//TODO: Check if creating an role from the database works
	}
	
	@Test
	public void roleUpdateTest() {
		Role myRole = new Role(-9, "AssociateTest1");
		myRole.setRole("AssociateTest2");
		myRole.setRoleId(-8);
		assertTrue((myRole.getRoleId() == -8) && (myRole.getRole() == "AssociateTest2"));
		//TODO: Check if the database if working
		myRole.removeRole();
	}
	
	@Test
	public void roleThrowTest() {
		//TODO: Do this when you have throwables!!!
	}
	
	/*******************************************
	 * User tests
	 *//////////////////////////////////////////
	
	@Test
	public void userCreateTest() {
		
	}
	
	@Test
	public void userUpdateTest() {
		
	}
	
	public void userThrowTest() {
		//TODO: Do this when you have throwables!!!
	}
}
