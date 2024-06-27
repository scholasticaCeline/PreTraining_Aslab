package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public Main() {
		firstMenu();
	}
	
	public static void firstMenu() {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		System.out.println("  _      __    __                     ______       ___  ____  _______      __\n"
						+ " | | /| / /__ / /______  __ _  ___   /_  __/__    / _ \\/ __ \\/_  __/ | /| / /\n"
						+ " | |/ |/ / -_) / __/ _ \\/  ' \\/ -_)   / / / _ \\  / // / /_/ / / /  | |/ |/ /\n"
						+ " |__/|__/\\__/_/\\__/\\___/_/_/_/\\__/   /_/  \\___/ /____/\\____/ /_/   |__/|__/");
		System.out.println("Enter to continue....");
		scan.nextLine();
		System.out.println("   __             _         __  ___\r\n"
						+ "  / /  ___  ___ _(_)__     /  |/  /__ ___  __ __\r\n"
						+ " / /__/ _ \\/ _ `/ / _ \\   / /|_/ / -_) _ \\/ // /\r\n"
						+ "/____/\\___/\\_, /_/_//_/  /_/  /_/\\__/_//_/\\_,_/\r\n"
						+ "          /___/"
						+ "\n"
						+ "1. Login\r\n"
						+ "2. Register\r\n"
						+ "3. Exit");
		do {
			System.out.print(">> ");
			try {
				choice = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				System.out.println("Invalid input. Please enter a valid integer");
				e.printStackTrace();
			}
			
			switch(choice) {
			case 1:
				System.out.println("");
				login();
				break;
			case 2:
				register();
				break;
			case 3:
				Logo.logo();
				System.exit(0);
			}
		}while(choice != 3);
		Logo.logo();
		scan.close();
	}
	
	private static void register() {
		Scanner scan = new Scanner(System.in);
		String email, password;
		boolean emailFlag = false;
		System.out.println("   ___           _     __             __  ___\r\n"
						+ "  / _ \\___ ___ _(_)__ / /____ ____   /  |/  /__ ___  __ __\r\n"
						+ " / , _/ -_) _ `/ (_-</ __/ -_) __/  / /|_/ / -_) _ \\/ // /\r\n"
						+ "/_/|_|\\__/\\_, /_/___/\\__/\\__/_/    /_/  /_/\\__/_//_/\\_,_/\r\n"
						+ "         /___/");
		System.out.println("");
		System.out.println("Input 'Exit' to quit from Login Menu");
		
		do {
            System.out.print("Input new email: ");
            email = scan.nextLine();
            if (email.equals("Exit")) break;
            if (isValidEmail(email)) {
                System.out.println("Email is valid.");
                emailFlag = true;
            } else {
                System.out.println("Email is invalid. Please try again.");
            }
        } while (!emailFlag);
		
		do {
			System.out.print("Input new password: ");
			password = scan.nextLine();
			if(password.length() <  8|| password.length() > 30) {
				System.out.println("Password must be 8-30 characters");
			}
			if(password == "Exit") break;
		} while(password.length() <  8|| password.length() > 30);
		
		if(email.equalsIgnoreCase("exit") && password.equalsIgnoreCase("exit")) firstMenu();
	}

	private static boolean isValidEmail(String email) {
		int atIndex = email.indexOf('@');
        int dotIndex = email.indexOf('.', atIndex);

        if (atIndex <= 0 || atIndex == email.length() - 1) return false;
        if (dotIndex <= atIndex + 1 || dotIndex == email.length() - 1) return false;
        
		return true;
	}

	public static void login() {
		Scanner scan = new Scanner(System.in);
		String email, pass;
		boolean incorrectCredentials = false;
		do {
			System.out.println("Input 'Exit' to quit from Login Menu");
			
			System.out.print("Input Email: ");
			email = scan.nextLine();
			System.out.println("Input Password: ");
			pass = scan.nextLine();
			
			if(email.equalsIgnoreCase("exit") && pass.equalsIgnoreCase("exit")) firstMenu();
			File fp = new File("credentials.txt");
			try {
				Scanner reader = new Scanner(fp);
				while(reader.hasNextLine()) {
					String temp = reader.nextLine();
					String[] splitting = temp.split("#");
					if(splitting[0] == email && splitting[1] == pass) {
						incorrectCredentials = false;
						break;
					} 
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(incorrectCredentials) {
				System.out.println("Incorrect credentials");
				System.out.println("\nEnter to continue..."); scan.nextLine();
			}
		} while(incorrectCredentials);
		
	}

}