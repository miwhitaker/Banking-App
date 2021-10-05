package com.bank.scanner;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NextScreen implements Screen{

	String userName;
	
	@Override
	public void render(Scanner scan) {
		System.out.println("Welcome to Capital Two Bank");
		System.out.println("Please enter your name: ");
		userName = scan.nextLine();
		enterAge(scan);
		
		new NextScreen().render(scan);
	}
	
	public void enterAge(Scanner scan) {
		
		System.out.println("enter your age:");
		try {
		int age = scan.nextInt();
		scan.nextLine();
		}
		catch(InputMismatchException e) {
			System.out.println("That wasn't a number, try again");
			scan.nextLine(); // sometimes you will have to put an empty nextLine() to prevent it skipping the next console input
			enterAge(scan);
		}
	}

}


