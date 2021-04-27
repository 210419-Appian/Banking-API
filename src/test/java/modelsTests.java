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
		Role myRole = new Role(-9, "AssociateTest");
		User myUser = new User(-9, "UsernameTest", "1234", "LeeTest", "BrumTest", "aaa@aaa.b", myRole);
		
		assertTrue(myUser.getUserId() == -9);
		assertTrue(myUser.getUsername() == "UsernameTest");
		assertTrue(myUser.getPassword() == "1234");
		assertTrue(myUser.getFirstName() == "LeeTest");
		assertTrue(myUser.getLastName() == "BrumTest");
		assertTrue(myUser.getEmail() == "aaa@aaa.b");
		assertTrue(myUser.getRole() == myRole);
		
		myRole.removeRole();
		myUser.removeUser();
	}
	
	@Test
	public void userUpdateTest() {
		Role myRole = new Role(-9, "AssociateTest");
		Role myRole2 = new Role(-9, "AssociateTest2");
		User myUser = new User(-9, "UsernameTest", "1234", "LeeTest", "BrumTest", "aaa@aaa.b", myRole);
		
		myUser.setUserId(-8);
		myUser.setUsername("UsernameTest2");
		myUser.setPassword("4321");
		myUser.setFirstName("TestLee");
		myUser.setLastName("TestBrum");
		myUser.setEmail("bbb@bbb.b");
		myUser.setRole(myRole2);
		
		assertTrue(myUser.getUserId() == -8);
		assertTrue(myUser.getUsername() == "UsernameTest2");
		assertTrue(myUser.getPassword() == "4321");
		assertTrue(myUser.getFirstName() == "TestLee");
		assertTrue(myUser.getLastName() == "TestBrum");
		assertTrue(myUser.getEmail() == "bbb@bbb.b");
		assertTrue(myUser.getRole() == myRole2);
		
		myRole.removeRole();
		myUser.removeUser();
	}
	
	public void userThrowTest() {
		//TODO: Do this when you have throwables!!!
	}
	
	/*******************************************
	 * Acccount tests
	 *//////////////////////////////////////////
	
	//TODO: Do tests!
	
	/*******************************************
	 * AccountStatus tests
	 *//////////////////////////////////////////
	
	//TODO: Do tests!
	
	/*******************************************
	 * AccountType tests
	 *//////////////////////////////////////////
	
	//TODO: Do tests!
}
