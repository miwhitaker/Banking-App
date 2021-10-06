package com.bank.scanner;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.bank.dao.AllBankCommands;
import com.bank.dao.BankDbConnection;
import com.bank.model.BankAccount;


public class NextScreenNew implements Screen{

	String firstName;
	String lastName;
	Float deposit;
	String accountType;
	
	@Override
	public void render(Scanner scan) {
		
		BankDbConnection connect = new BankDbConnection();
		AllBankCommands newUser = new AllBankCommands(connect);
		newUser.addUser(MainScreen.userName, MainScreen.pass);
		
		System.out.println("You're account was created successfully.");
		System.out.println("---------------");
		System.out.println("Enter your first name: ");
		firstName = scan.nextLine();
		System.out.println("Enter your last name: ");
		lastName = scan.nextLine();
		System.out.println("How much did you deposit today?: ");
		try {
			deposit = scan.nextFloat();
		}
		catch(InputMismatchException e) {
			System.out.println("This should be in dollars and cents, please try again:");
			deposit = scan.nextFloat();
		}
		
		System.out.println("What type of account is this - 'c' for checking, 's' for savings?: ");
		accountType = scan.nextLine();
		
		if(accountType.toLowerCase().equals('c')) {
			accountType = "checking";
		}
		else if(accountType.toLowerCase().equals('s')) {
			accountType = "savings";
		}
		else {
			System.out.println("I didn't understand, please try again: ");
			accountType = scan.nextLine();
		}
		
		BankAccount newAct = new BankAccount(firstName, lastName, deposit, accountType, "pending", "customer", MainScreen.userName);
		newUser.addAccount(newAct);
		
		System.out.println("exit? y/n");
		String exit =  scan.nextLine();
		
		if(exit.toLowerCase().equals("y")) {
			ConsoleApp.isRunning = false;
			return;
		}
	}
	
}
