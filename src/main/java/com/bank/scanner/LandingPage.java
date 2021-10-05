package com.bank.scanner;

import java.util.Scanner;

public class LandingPage implements Screen {

static String newOld;
static String name;
static String pass;
	
	@Override
	public void render(Scanner scan) {
		System.out.println("Welcome to Capital 0.5 Bank");
		System.out.println("Double the fees, half the service!!!");
		System.out.println("Have you logged in before (Y/N)? : ");
		newOld = scan.nextLine();
		char firstChar = newOld.toLowerCase().charAt(0);
		
		if(firstChar == 'n') {
			//GOTO new customer screen
		}
		
		else if (firstChar == 'y') {
			//GOTO login screen ---> username/pass tells you type of user that is logged in (after DB call)
		}
		
		else {
			System.out.println("I didn't understand, please try again");
			scan.nextLine();
		}
		
		
		
		enterAge(scan);
		/* return */ new NextScreen().render(scan);
	}

	/*
	public void enterAge(Scanner scan) {
		
		System.out.println("enter your age:");
		try {
		int age = scan.nextInt();
		scan.nextLine();
		}catch(InputMismatchException e) {
			System.out.println("That wasn't a number, try again");
			scan.nextLine(); // sometimes you will have to put an empty nextLine() to prevent it skipping the next console input
			enterAge(scan);
		}*/
	}


}
	

