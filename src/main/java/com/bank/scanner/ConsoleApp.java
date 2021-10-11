package com.bank.scanner;

import java.util.Scanner;


public class ConsoleApp {
	
	public static boolean isRunning = true;
	public void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		//Screen currScreen = new MainScreen();
		
		MainScreen mainScreen = new MainScreen();
		String userType;
		try {
			userType = mainScreen.login(scan);
		}
		catch(NullPointerException e) {
			///Assuming all new users are customers
			
			userType = "customer";
			new NextScreenNew().render(scan);
		}
		
		Screen userScreen;
		
		if(userType.equals("customer")) {
			userScreen = new NextScreenCust();
		}	
		else if(userType.equals("employee")) {
			userScreen = new NextScreenEmp();

		}			
		else {
			userScreen = new NextScreenAdmin();
		}
		
		
		while(isRunning) {
		
			userScreen.render(scan);
		
		}
		scan.close();
		System.out.println("=======================");
		System.out.println("You are logged out");
		System.out.println("=======================");
		
	}
}