package com.bank.scanner;

import java.util.Scanner;

public class MainScreen {

	public static boolean appRunning = true;
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Screen currScreen = new MenuScreen();
		
		while(appRunning) {
			currScreen.render(scan);
		}
		
		scan.close();

	}
}