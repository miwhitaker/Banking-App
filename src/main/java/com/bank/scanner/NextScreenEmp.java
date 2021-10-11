package com.bank.scanner;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.bank.dao.AllBankCommands;
import com.bank.dao.BankDbConnection;
import com.bank.model.BankAccount;

public class NextScreenEmp implements Screen{

	String action;
	String exit;
	
	BankDbConnection connect = new BankDbConnection();
	AllBankCommands command = new AllBankCommands(connect);

	@Override
	public void render(Scanner scan) {
		System.out.println("You are logged in successfully.");
		System.out.println("---------------");
		System.out.println("Type a to approve accounts,");
		System.out.println("Type v to view an accounts,");
		System.out.println("Type n for new account,");
		System.out.println("Type w to make a withdraw, ");
		System.out.println("Type d to make a deposit,");
		System.out.println("Type t to transfer funds,");
		
		System.out.println("---------------");
		System.out.println("What would you like to do?: ");
		action = scan.nextLine();
		int accountNumber;
		
		System.out.println("Enter account number: ");
		try {
			accountNumber = Integer.parseInt(scan.nextLine());
		}
		catch(InputMismatchException e) {
			System.out.println("That was not a valid account number, please try again: ");
			accountNumber = Integer.parseInt(scan.nextLine());
		}
		
		int idNumber = accountNumber - 1000;
		BankAccount currAcct = command.getOneAccount(idNumber);
		
		
		
		if(action.toLowerCase().equals("a")) {
			System.out.println("------------------------------");
			System.out.println("First Name: " + currAcct.getFirstName());
			System.out.println("Last Name: " + currAcct.getLastName());
			System.out.println("Account Type: " + currAcct.getAcctType());
			System.out.println("Account Status: " + currAcct.getAcctStatus());
			System.out.println("User Type: " + currAcct.getUserType());
			System.out.println("User Name: " + currAcct.getUserName());
			System.out.println("Balance: " + currAcct.getBalance());
			System.out.println("------------------------------");
			System.out.println("------------------------------");
			System.out.println("Type a to approve this account,");
			System.out.println("Type d to deny this account,");
			System.out.println("Type e to exit ");
	
			String apr_action = scan.nextLine();
			Boolean notValid = true;
			
			while(notValid) {
				if(apr_action.toLowerCase().equals("a")) {
					command.approveAccount(idNumber);
					System.out.println("Account was approved");
					notValid = false;
				}
				
				else if(apr_action.toLowerCase().equals("d")) {
					command.approveAccount(idNumber);
					System.out.println("Account was denied");
					notValid = false;
				}
				
				else if(apr_action.toLowerCase().equals("e")) {
					return;
				}
				
				else {
					System.out.println("I didn't understand, please try again");
					apr_action = scan.nextLine();
				}
			}
		}
		
		
		
		else if(action.toLowerCase().equals("v")) {
			System.out.println("First Name: " + currAcct.getFirstName());
			System.out.println("Last Name: " + currAcct.getLastName());
			System.out.println("Account Type: " + currAcct.getAcctType());
			System.out.println("Account Status: " + currAcct.getAcctStatus());
			System.out.println("User Type: " + currAcct.getUserType());
			System.out.println("User Name: " + currAcct.getUserName());
			System.out.println("Balance: " + currAcct.getBalance());
			System.out.println("------------------------------");
			System.out.println("------------------------------");
		}
		
		
		
		else if(action.toLowerCase().equals("n")) {
			String firstName;
			String lastName;
			Float deposit;
			String accountType = null;
			String uName;
			
			System.out.println("Enter customer's first name: ");
			firstName = scan.nextLine();
			System.out.println("Enter customer's last name: ");
			lastName = scan.nextLine();
			System.out.println("How much did you deposit today?: ");
			try {
				deposit = Float.valueOf(scan.nextLine());
			}
			catch(InputMismatchException e) {
				System.out.println("This should be a number in dollars and cents, please try again:");
				deposit = Float.valueOf(scan.nextLine());
			}
			
			System.out.println("What type of account is this - 'c' for checking, 's' for savings?: ");
			String acType = scan.nextLine();
			
			Boolean notValid = true;
			
			while(notValid) {
				if(acType.toLowerCase().equals("c")) {
					accountType = "checking";
					notValid = false;
				}
				
				else if(acType.toLowerCase().equals("s")) {
					accountType = "savings";
					notValid = false;
				}
				
				else {
					System.out.println("I didn't understand, please try again: ");
					accountType = scan.nextLine();
				}
			}
			
			System.out.println("Enter customer's username: ");
			uName = scan.nextLine();
			
			BankAccount newAct = new BankAccount(firstName, lastName, deposit, accountType, "pending", "customer", MainScreen.userName);
			command.addAccount(newAct);
			
			System.out.println("This account has been created");
			System.out.println("----------------------------------------------------------------");
		}
		
		
		else if(action.toLowerCase().equals("w")) {
			Float wdraw;
			int account;
			int id;
			
			System.out.println("From which account number: ");
			try {
				account = Integer.parseInt(scan.nextLine());
				id = account - 1000;
			}
			
			catch(InputMismatchException e) {
				System.out.println("That is not a valid account number, it should be 4 numbers");
				System.out.println("Please re-enter customer's account number: ");
				account = Integer.parseInt(scan.nextLine());
				id = account - 1000;
			}
			
			currAcct = command.getOneAccount(id);
			
			System.out.println("How much do you want to withdraw?: ");
			try {
				wdraw = Float.valueOf(scan.nextLine());
			}
			catch(InputMismatchException e) {
				System.out.println("This should be a number in dollars and cents, please try again:");
				wdraw = Float.valueOf(scan.nextLine());
			}
			
			
			id = account - 1000;
			float newBalance = currAcct.getBalance() - wdraw;
				
			if(newBalance < 0) {
					System.out.println("You do not have enough funds to complete this transaction");
			}
				
			else {
				command.withdraw(id, newBalance);
				System.out.println("--------------------");
				System.out.println("Transaction complete");
				System.out.println("--------------------");
			}
		}
		
		
		
		else if(action.toLowerCase().equals("d")) {
			Float dep;
			int account;
			int id;
		
			System.out.println("To which account?: ");
			try {
				account = Integer.parseInt(scan.nextLine());
				id = account - 1000;
			}
			catch(InputMismatchException e) {
				System.out.println("That is not a valid account number, it should be 4 numbers");
				System.out.println("Please re-enter your account number: ");
				account = Integer.parseInt(scan.nextLine());
				id = account - 1000;
			}
			
			System.out.println("How much do you want to deposit?: ");
			try {
				dep = Float.valueOf(scan.nextLine());
			}
			catch(InputMismatchException e) {
				System.out.println("This should be a number in dollars and cents, please try again:");
				dep = Float.valueOf(scan.nextLine());
			}
			
			BankAccount currAccount = command.getOneAccount(id);
			
			float newBalance = currAccount.getBalance() + dep;
			command.deposit(id, newBalance);
			System.out.println("--------------------");
			System.out.println("Transaction complete");
			System.out.println("--------------------");
		}
		
		
		
		else if(action.toLowerCase().equals("t")) {
			int account1;
			int id1;
			int account2;
			int id2;
			float amount;
			
			System.out.println("Transfer from which account: ");
			try {
				account1 = Integer.parseInt(scan.nextLine());
				id1 = account1 - 1000;
			}
			catch (InputMismatchException e){
				System.out.println("That is not a valid account number, it should be 4 numbers");
				System.out.println("Please re-enter your account number: ");
				account1 = Integer.parseInt(scan.nextLine());
				id1 = account1 - 1000;
			}
			
			System.out.println("Transfer to which account: ");
			try {
				account2 = Integer.parseInt(scan.nextLine());
				id2 = account2 - 1000;
			}
			catch (InputMismatchException e){
				System.out.println("That is not a valid account number, it should be 4 numbers");
				System.out.println("Please re-enter your account number: ");
				account2 = Integer.parseInt(scan.nextLine());
				id2 = account2 - 1000;
			}
			
			System.out.println("What amount?: ");
			try {
				amount = Float.valueOf(scan.nextLine());
			}
			catch (InputMismatchException e){
				System.out.println("That is not a valid account number, it should be 4 numbers");
				System.out.println("Please re-enter your account number: ");
				amount = Float.valueOf(scan.nextLine());
			}
			
			BankAccount currAcct1 = command.getOneAccount(id1);
			BankAccount currAcct2 = command.getOneAccount(id2);
			
			if(currAcct1.getUserName().equals(MainScreen.userName) && currAcct2.getUserName().equals(MainScreen.userName)) {
				float newBalance1 = currAcct1.getBalance() - amount;
				float newBalance2 = currAcct2.getBalance() + amount;
				
				if(newBalance1 < 0) {
					System.out.println("You do not have enough funds to complete this transaction");
				}
				
				else {
					command.transfer(id1, id2, newBalance1, newBalance2);
				}
			}
			
			
			else {
				System.out.println("Incorrect account number, please try again: ");
				action = scan.nextLine();
			}
		}
		
		
		
		else {
			System.out.println("I didn't understand, please try again: ");
			action = scan.nextLine();
		}
		
		System.out.println("Do you want to exit? y/n");
		String exit =  scan.nextLine();
		
		if(exit.toLowerCase().equals("y")) {
			ConsoleApp.isRunning = false;
			return;
		}
	}
		}
