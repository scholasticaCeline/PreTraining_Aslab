package main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		mainMenu();
	}

	private static void mainMenu() {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("      ___           ___                        ___                                                                                       ___     \r\n"
							+ "     /__/\\         /  /\\          ___         /  /\\                                 ___        ___                                      /  /\\    \r\n"
							+ "     \\  \\:\\       /  /:/_        /__/\\       /  /:/_                               /__/\\      /  /\\                                    /  /::\\   \r\n"
							+ "      \\__\\:\\     /  /:/ /\\       \\  \\:\\     /  /:/ /\\    ___     ___               \\  \\:\\    /  /:/      ___     ___   ___     ___    /  /:/\\:\\  \r\n"
							+ "  ___ /  /::\\   /  /:/ /:/_       \\  \\:\\   /  /:/ /:/_  /__/\\   /  /\\               \\  \\:\\  /__/::\\     /__/\\   /  /\\ /__/\\   /  /\\  /  /:/~/::\\ \r\n"
							+ " /__/\\  /:/\\:\\ /__/:/ /:/ /\\  ___  \\__\\:\\ /__/:/ /:/ /\\ \\  \\:\\ /  /:/           ___  \\__\\:\\ \\__\\/\\:\\__  \\  \\:\\ /  /:/ \\  \\:\\ /  /:/ /__/:/ /:/\\:\\\r\n"
							+ " \\  \\:\\/:/__\\/ \\  \\:\\/:/ /:/ /__/\\ |  |:| \\  \\:\\/:/ /:/  \\  \\:\\  /:/           /__/\\ |  |:|    \\  \\:\\/\\  \\  \\:\\  /:/   \\  \\:\\  /:/  \\  \\:\\/:/__\\/\r\n"
							+ "  \\  \\::/       \\  \\::/ /:/  \\  \\:\\|  |:|  \\  \\::/ /:/    \\  \\:\\/:/            \\  \\:\\|  |:|     \\__\\::/   \\  \\:\\/:/     \\  \\:\\/:/    \\  \\::/     \r\n"
							+ "   \\  \\:\\        \\  \\:\\/:/    \\  \\:\\__|:|   \\  \\:\\/:/      \\  \\::/              \\  \\:\\__|:|     /__/:/     \\  \\::/       \\  \\::/      \\  \\:\\     \r\n"
							+ "    \\  \\:\\        \\  \\::/      \\__\\::::/     \\  \\::/        \\__\\/                \\__\\::::/      \\__\\/       \\__\\/         \\__\\/        \\  \\:\\    \r\n"
							+ "     \\__\\/         \\__\\/           ~~~~       \\__\\/                                  ~~~~                                               \\__\\/    \r\n");
			System.out.println("1. Login");
			System.out.println("2. Exit");
			System.out.print(">> ");
			try {
				choice = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				System.out.println("Invalid input. Please enter a valid integer");
				System.out.println("Press enter to continue..."); scan.nextLine();
				e.printStackTrace();
			}
			if(choice == 1) login();
			else if(choice != 2 && choice != 1) {
				System.out.println("Enter a number between 1 or 2");
				System.out.println("Press enter to continue..."); scan.nextLine();
			}
		} while(choice != 2);
	}

	private static void login() {
	    Scanner scan = new Scanner(System.in);
	    String user, pass;
	    boolean invalidCredentials = false;
	    
	    do {
	        System.out.print("Input your username: ");
	        user = scan.nextLine();
	        System.out.print("Input your password: ");
	        pass = scan.nextLine();
	        
	        if(user.equals("User") && pass.equals("User")) {
	            User.userMenu();
	        } else if(user.equals("Admin") && pass.equals("Admin")) {
	            Admin.adminMenu();
	        } else {
	            invalidCredentials = true;
	            System.out.println("Your credential is wrong");
	            System.out.println("Press enter to continue..."); scan.nextLine();
	        }
	    } while(invalidCredentials);
	}


}
