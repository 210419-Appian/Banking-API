import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.*;

public class Driver {

	public static void main(String[] args) {
		List<Account> myAccounts = new ArrayList<>();
		boolean exit = false;
		Scanner s = new Scanner(System.in);
		
		System.out.println("Welcome to the bank console. Please enter a command when ready.");
		
		//loop until we decide to exit
		while(exit == false) {
			String input = s.nextLine();
			System.out.println(input);
			
			switch(input) {
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
