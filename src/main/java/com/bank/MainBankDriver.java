package com.bank;

import com.bank.dao.AllBankCommands;
import com.bank.dao.BankDbConnection;
import com.bank.scanner.ConsoleApp;


public class MainBankDriver {

	public static void main(String[] args) {
		
		BankDbConnection connect = new BankDbConnection();
		AllBankCommands com = new AllBankCommands(connect);
		ConsoleApp myApp = new ConsoleApp();
		myApp.main(args);
	}
	
}
