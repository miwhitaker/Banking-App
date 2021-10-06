package com.bank.scanner;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.bank.dao.AllBankCommands;
import com.bank.dao.BankDbConnection;
import com.bank.model.BankAccount;

public class NextScreenCust implements Screen{

	String action;
	
	BankDbConnection connect = new BankDbConnection();
	AllBankCommands command = new AllBankCommands(connect);

	
	@Override
	public void render(Scanner scan) {
		System.out.println("You are logged in successfully.");
		System.out.println("---------------");
		System.out.println("Type 'n' for new account, 'v' for view accounts,");
		System.out.println("w for withdraw, 'd' for deposit");
		System.out.println("---------------");
		System.out.println("What would you like to do?: ");
		action = scan.nextLine();
		
		if(action.toLowerCase().equals('n')) {
			
			String firstName;
			String lastName;
			Float deposit;
			String accountType;
			
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
			command.addAccount(newAct);
		}
		
		else if(action.toLowerCase().equals('v')) {
			System.out.println("What account number: ");
			int acctNum = scan.nextInt();
			int id = 1000 - acctNum;
			BankAccount currAcct = command.getOneAccount(id);
			System.out.println("First Name: " + currAcct.getFirstName());
			System.out.println("Last Name: " + currAcct.getLastName());
			System.out.println("Account Type: " + currAcct.getAcctType());
			System.out.println("Account Status: " + currAcct.getAcctStatus());
			System.out.println("Balance: " + currAcct.getBalance());
		}
		
		else if(action.toLowerCase().equals('w')) {
			Float wdraw;
			int account;
			
			System.out.println("How much do you want to withdraw?: ");
			wdraw = scan.nextFloat();
			System.out.println("From which account?: ");
			account = scan.nextInt();
			int id = 1000 - account;
			BankAccount currAccount = command.getOneAccount(id);
			float newBalance = currAccount.getBalance() - wdraw;
			
			if(newBalance < 0) {
				System.out.println("You do not have enough funds to complete this transaction");
			}
			else {
				command.withdraw(id, newBalance);
			}
		}
		
		else if(action.toLowerCase().equals('d')) {
			Float dep;
			int account;
			
			System.out.println("How much do you want to deposit?: ");
			dep = scan.nextFloat();
			System.out.println("From which account?: ");
			account = scan.nextInt();
			int id = 1000 - account;
			BankAccount currAccount = command.getOneAccount(id);
			float newBalance = currAccount.getBalance() + dep;
			command.deposit(id, newBalance);
		}
		
		else {
			System.out.println("I didn't understand, please try again: ");
			action = scan.nextLine();
		}
		
		System.out.println("exit? y/n");
		String exit =  scan.nextLine();
		
		if(exit.toLowerCase().equals("y")) {
			ConsoleApp.isRunning = false;
			return;
		}
	}
	
}




