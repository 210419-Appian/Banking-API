package menus;

import java.util.Scanner;

import models.Role;
import models.User;

public class NewUserMenu {
	public static void printMenu(Scanner s) {
		System.out.println("Please input additional information");
		User myUser;
		
		try {
			System.out.println("Please inpit the userID");
			String userID = s.nextLine();
			System.out.println("Please input the username");
			String username = s.nextLine();
			System.out.println("Please enter the password");
			String password = s.nextLine();
			System.out.println();
			String firstName = s.nextLine();
			String lastName = s.nextLine();
			String email = s.nextLine();
			String roleString = s.nextLine();
			//TODO: Add roll and validate
			//TODO: Create class and add to arrayList or something
			//TODO: Finish this, in general
		}catch(Exception e){  //TODO: This is a placeholder, change this when creating custom exceptions!
			e.printStackTrace();
		}
	}
}
