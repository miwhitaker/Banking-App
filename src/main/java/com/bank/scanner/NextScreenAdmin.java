package com.bank.scanner;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.bank.dao.AllBankCommands;
import com.bank.dao.BankDbConnection;
import com.bank.model.BankAccount;

public class NextScreenAdmin implements Screen{
	
	String action;
	BankDbConnection connect = new BankDbConnection();
	AllBankCommands command = new AllBankCommands(connect);
	
	@Override
	public void render(Scanner scan) {
		System.out.println("You are logged in successfully.");
		System.out.println("---------------");
		System.out.println("Type a to approve/deny an account,");
		System.out.println("Type v to view an account,");
		System.out.println("Type s to view all accounts");
		System.out.println("Type w for withdraw,");
		System.out.println("Type d for deposit,");
		System.out.println("Type t for transfer funds,");
		System.out.println("Type c to cancel accounts");
		System.out.println("---------------");
		System.out.println("What would you like to do?: ");
		action = scan.nextLine();
		int accountNumber;
		
		
		/////For approving/denying accounts
		if(action.toLowerCase().equals("a")) {
			System.out.println("Enter account number: ");
			try {
				accountNumber = scan.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println("That was not a valid account number, please try again: ");
				accountNumber = scan.nextInt();
			}
			
			int idNumber = accountNumber - 1000;
			BankAccount currAcct = command.getOneAccount(idNumber);
			
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
		
		
		/////For viewing a single account
		else if(action.toLowerCase().equals("v")) {
			System.out.println("Enter account number: ");
			try {
				accountNumber = scan.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println("That was not a valid account number, please try again: ");
				accountNumber = scan.nextInt();
			}
			
			int idNumber = accountNumber - 1000;
			BankAccount currAcct = command.getOneAccount(idNumber);
			
			System.out.println("First Name: " + currAcct.getFirstName());
			System.out.println("Last Name: " + currAcct.getLastName());
			System.out.println("Account Type: " + currAcct.getAcctType());
			System.out.println("Account Status: " + currAcct.getAcctStatus());
			System.out.println("Account Number: " + (currAcct.getId() + 1000));
			System.out.println("User Type: " + currAcct.getUserType());
			System.out.println("User Name: " + currAcct.getUserName());
			System.out.println("Balance: " + currAcct.getBalance());
			System.out.println("------------------------------");
			System.out.println("------------------------------");
		}
		
		
		/////To view all accounts
		else if(action.toLowerCase().equals("s")) {
			List<BankAccount> allAccounts = command.getAll();
			for(BankAccount i : allAccounts) {
				System.out.println("-------------------------------");
				System.out.println("First Name: " + i.getFirstName());
				System.out.println("Last Name: " + i.getLastName());
				System.out.println("Account Type: " + i.getAcctType());
				System.out.println("Account Status: " + i.getAcctStatus());
				System.out.println("Account Number: " + (i.getId() + 1000));
				System.out.println("User Type: " + i.getUserType());
				System.out.println("User Name: " + i.getUserName());
				System.out.println("Balance: " + i.getBalance());
				System.out.println("-------------------------------");
			}
		}
		
		
		/////For making a withdraw
		else if(action.toLowerCase().equals("w")) {
			Float wdraw;
			int account;
			int id;
			
			System.out.println("From which account number: ");
			try {
				account = scan.nextInt();
				id = account - 1000;
			}
			
			catch(InputMismatchException e) {
				System.out.println("That is not a valid account number, it should be 4 numbers");
				System.out.println("Please re-enter your account number: ");
				account = scan.nextInt();
				id = account - 1000;
			}
			
			BankAccount currAcct = command.getOneAccount(id);
			
			System.out.println("How much do you want to withdraw?: ");
			try {
				wdraw = scan.nextFloat();
			}
			catch(InputMismatchException e) {
				System.out.println("This should be a number in dollars and cents, please try again:");
				wdraw = scan.nextFloat();
			}
			
			if(currAcct.getUserName().equals(MainScreen.userName)) {
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
		}
		
		
		/////For making a deposit
		else if(action.toLowerCase().equals('d')) {
			Float dep;
			int account;
			int id;
		
			System.out.println("To which account?: ");
			try {
				account = scan.nextInt();
				id = account - 1000;
			}
			catch(InputMismatchException e) {
				System.out.println("That is not a valid account number, it should be 4 numbers");
				System.out.println("Please re-enter your account number: ");
				account = scan.nextInt();
				id = account - 1000;
			}
			
			System.out.println("How much do you want to deposit?: ");
			try {
				dep = scan.nextFloat();
			}
			catch(InputMismatchException e) {
				System.out.println("This should be a number in dollars and cents, please try again:");
				dep = scan.nextFloat();
			}
			
			BankAccount currAccount = command.getOneAccount(id);
			
			float newBalance = currAccount.getBalance() + dep;
			command.deposit(id, newBalance);
			System.out.println("--------------------");
			System.out.println("Transaction complete");
			System.out.println("--------------------");
		}
		
		
		/////For account transfers
		else if(action.toLowerCase().equals("t")) {
			int account1;
			int id1;
			int account2;
			int id2;
			float amount;
			
			System.out.println("Transfer from which account: ");
			try {
				account1 = scan.nextInt();
				id1 = account1 - 1000;
			}
			catch (InputMismatchException e){
				System.out.println("That is not a valid account number, it should be 4 numbers");
				System.out.println("Please re-enter your account number: ");
				account1 = scan.nextInt();
				id1 = account1 - 1000;
			}
			
			System.out.println("Transfer to which account: ");
			try {
				account2 = scan.nextInt();
				id2 = account2 - 1000;
			}
			catch (InputMismatchException e){
				System.out.println("That is not a valid account number, it should be 4 numbers");
				System.out.println("Please re-enter your account number: ");
				account2 = scan.nextInt();
				id2 = account2 - 1000;
			}
			
			System.out.println("What amount?: ");
			try {
				amount = scan.nextFloat();
			}
			catch (InputMismatchException e){
				System.out.println("That is not a valid account number, it should be 4 numbers");
				System.out.println("Please re-enter your account number: ");
				amount = scan.nextFloat();
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
		
		
		/////To cancel/delete account
		else if(action.toLowerCase().equals("c")) {
			System.out.println("Cancel which account number: ");
			int actNum = scan.nextInt();
			int id = actNum - 1000;
			BankAccount current = command.getOneAccount(id);
			command.deleteAccount(current);
			System.out.println("Account has been deleted");
			System.out.println("------------------------");
		}
		
		
		/////If the user makes a mistake
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
