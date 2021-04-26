package menus;

import java.util.Scanner;

public class MainMenu {
	public static void printMenu(Scanner s) {
		boolean exit = false;
		
		while(exit == false) {
			String input = s.nextLine();
			System.out.println(input);
			System.out.println("Vallid commands:");
			System.out.println("new user");
			System.out.println("exit");
			switch(input) {
			case "new user":
				NewUserMenu.printMenu(s);
				break;
			case "exit":
				System.out.println("Goodbye!");
				exit = true;
				break;
			default:
				System.out.println("Invalid command");
				break;
			}
		}
	}
}
