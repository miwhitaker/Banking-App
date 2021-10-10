package com.bank.scanner;

import java.util.Scanner;
import com.bank.dao.AllBankCommands;
import com.bank.dao.BankDbConnection;
import com.bank.model.UserAccount;


public class MainScreen implements Screen {

	static String userName;
	static String pass;
		
		@Override
		public void render(Scanner scan) {
			System.out.println("Welcome to Capital 0.5 Bank");
			System.out.println("Double the fees, half the service!!!");
			System.out.println("Please enter your username, or create a new one: ");
			userName = scan.nextLine();
			
			System.out.println("Please enter your password: ");
			pass = scan.nextLine();
			
			
			BankDbConnection connect = new BankDbConnection();
			AllBankCommands command = new AllBankCommands(connect);
			
			try {
				UserAccount userExists = command.getUser(userName);
				String userType = userExists.getUserType();
				
				if(userType.equals("customer")) {
					new NextScreenCust().render(scan);
				}
				
				else if(userType.equals("employee")) {
					new NextScreenEmp().render(scan);
				}
				
				else if(userType.equals("admin")) {
					new NextScreenAdmin().render(scan);
				}
				
				else {
					new NextScreenNew().render(scan);
					}
			}
			
			catch(NullPointerException e) {
				new NextScreenNew().render(scan);
			}
		}
}
