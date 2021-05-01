package com.bank.menus;

import java.util.Scanner;

public class MainMenu {
	public static void printMenu(Scanner s) {
		boolean exit = false;
		
		while(exit == false) {
			String input = s.nextLine();
			System.out.println(input);
			System.out.println("Valid commands:");
			System.out.println("exit");
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
