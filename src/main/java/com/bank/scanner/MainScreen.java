package com.bank.scanner;

import java.util.Scanner;
import com.bank.dao.AllBankCommands;
import com.bank.dao.BankDbConnection;
import com.bank.model.UserAccount;


public class MainScreen implements Screen {

	static String userName;
	static String pass;
	
	BankDbConnection connect = new BankDbConnection();
	AllBankCommands command = new AllBankCommands(connect);
		
		@Override
		public void render(Scanner scan) {

}
			
		

		public String login(Scanner scan) {
			System.out.println("Welcome to Capital 0.5 Bank");
			System.out.println("Double the fees, half the service!!!");
			System.out.println("Please enter your username, or create a new one: ");
			userName = scan.nextLine();
			
			////TO DO: Null passwords and duplicate users are allowed in database -- this needs to be fixed with database overhaul
			////before password validation will work properly
			
			//for(int i = 0; i < 3; i++) {
				System.out.println("Please enter your password: ");
				pass = scan.nextLine();
			
				UserAccount userExists = command.getUser(userName);
				String userType = userExists.getUserType();
				return userType;
				//String userPass = userExists.getPass();

				
				/*if(userPass.equals(MainScreen.pass)) {
					
				}
				else {
					System.out.println("Your password was incorrect, please try again");
				}
			}
			
			System.out.println("Your password was incorrect 3 times, you have been logged out");
			System.exit(0);
			return "";
		}*/
			}
}


