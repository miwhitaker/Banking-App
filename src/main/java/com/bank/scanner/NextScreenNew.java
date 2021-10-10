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
	Boolean notValid = true;
	
	@Override
	public void render(Scanner scan) {
		
		BankDbConnection connect = new BankDbConnection();
		AllBankCommands command = new AllBankCommands(connect);
		command.addUser(MainScreen.userName, MainScreen.pass);
		
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
		String actType = scan.nextLine();
		
		while(notValid) {
			if(actType.toLowerCase().equals("c")) {
				accountType = "checking";
				notValid = false;
			}
			else if(actType.toLowerCase().equals("s")) {
				accountType = "savings";
				notValid = false;
			}
			else {
				System.out.println("I didn't understand, please try again: ");
				actType = scan.nextLine();
			}
		}
		BankAccount newAct = new BankAccount(firstName, lastName, deposit, accountType, "pending", "customer", MainScreen.userName);
		command.addAccount(newAct);
		
		System.out.println("Your account has been created. It will take 1-3 days for an administrator");
		System.out.println("to review your account. You can log in after it has been approved.");
		System.out.println("----------------------------------------------------------------");
		System.out.println("Do you want to exit? y/n");
		String exit =  scan.nextLine();
		
		if(exit.toLowerCase().equals("y")) {
			ConsoleApp.isRunning = false;
			return;
		}
	}
	
}
