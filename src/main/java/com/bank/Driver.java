package com.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bank.menus.MainMenu;

public class Driver {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Welcome to the bank console. Please enter a command when ready.");
		
		MainMenu.printMenu(s);

	}

}
