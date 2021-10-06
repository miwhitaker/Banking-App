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
		System.out.println("Type 'a' to approve accounts, 'v' for view accounts, 's' to view all accounts");
		System.out.println("w for withdraw, 'd' for deposit, 'c' for cancel accounts");
		System.out.println("---------------");
		System.out.println("What would you like to do?: ");
		action = scan.nextLine();
		int accountNumber;
		
		if(action.toLowerCase().equals('a')) {
			System.out.println("Enter the account number you wish to approve/deny: ");
			
			try {
				accountNumber = scan.nextInt();
			}
			
			catch(InputMismatchException e) {
				System.out.println("That was not a valid account number, plese try again: ");
				accountNumber = scan.nextInt();
			}
			
			int idNumber = 1000 - accountNumber;
			command.approveAccount(idNumber);
		}
		
		else if(action.toLowerCase().equals('v')) {
System.out.println("Enter the account number you wish to view: ");
			
			try {
				accountNumber = scan.nextInt();
			}
			
			catch(InputMismatchException e) {
				System.out.println("That was not a valid account number, please try again: ");
				accountNumber = scan.nextInt();
			}
			
			int id = 1000 - accountNumber;
			BankAccount currAcct = command.getOneAccount(id);
			System.out.println("First Name: " + currAcct.getFirstName());
			System.out.println("Last Name: " + currAcct.getLastName());
			System.out.println("Account Type: " + currAcct.getAcctType());
			System.out.println("Account Status: " + currAcct.getAcctStatus());
			System.out.println("User Type: " + currAcct.getUserType());
			System.out.println("User Name: " + currAcct.getUserName());
			System.out.println("Balance: " + currAcct.getBalance());
		}
		
		else if(action.toLowerCase().equals('s')) {
			List<BankAccount> allAccounts = command.getAll();
			System.out.println(allAccounts);
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
		
		else if(action.toLowerCase().equals('c')) {
			System.out.println("Cancel which account number: ");
			int actNum = scan.nextInt();
			int id = 1000 - actNum;
			BankAccount current = command.getOneAccount(id);
			command.deleteAccount(current);
		}
		
		else {
			System.out.println("I didn't understand, please try again: ");
			action = scan.nextLine();
		}
	}

}
