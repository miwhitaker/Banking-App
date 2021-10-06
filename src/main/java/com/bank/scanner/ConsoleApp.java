package com.bank.scanner;

import java.util.Scanner;


public class ConsoleApp {
	
	public static boolean isRunning = true;
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Screen currScreen = new MainScreen();
		
		while(isRunning) {
		
			currScreen.render(scan);
		
		}
		scan.close();
		
		System.out.println("done");
		
	}
}