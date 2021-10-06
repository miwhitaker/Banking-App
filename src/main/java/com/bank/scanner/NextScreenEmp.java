package com.bank.scanner;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.bank.dao.AllBankCommands;
import com.bank.dao.BankDbConnection;
import com.bank.model.BankAccount;

public class NextScreenEmp implements Screen{

	String action;
	
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
		
		else {
			System.out.println("I didn't understand, please try again: ");
			action = scan.nextLine();
		}
	}
	
}
