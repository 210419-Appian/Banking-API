import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import menus.MainMenu;
import models.*;

public class Driver {

	public static void main(String[] args) {
		List<Account> myAccounts = new ArrayList<>();

		Scanner s = new Scanner(System.in);
		
		System.out.println("Welcome to the bank console. Please enter a command when ready.");
		
		MainMenu.printMenu(s);

	}

}
