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
		System.out.println("Type 'a' to approve accounts, 'v' for view accounts,");
		System.out.println("---------------");
		System.out.println("What would you like to do?: ");
		action = scan.nextLine();
		int accountNumber;
		
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
