package com.bank;

import com.bank.dao.AllBankCommands;
import com.bank.dao.BankDbConnection;
import com.bank.model.EditAccount;


public class MainBankDriver {

	public static void main(String[] args) {
		
		BankDbConnection connect = new BankDbConnection();
		AllBankCommands com = new AllBankCommands(connect);
		
		//Command to add a new account
		//EditAccount newacct = new EditAccount("Michael", "Jordan", (float) 50000.00, "checking", "active", "customer");
		//com.addAccount(newacct);
		
		com.getOneAccount(1);
		System.out.println(com);
		
	}
	
}
