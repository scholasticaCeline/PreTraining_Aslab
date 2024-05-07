package bank;

import java.util.Scanner;

import main.User;

public class Bank {

	public static void bankPage() {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		System.out.println(" ______     ______     __   __     __  __     __    __     ______     __   __     __  __\r\n"
						+ "/\\  == \\   /\\  __ \\   /\\ \"-.\\ \\   /\\ \\/ /    /\\ \"-./  \\   /\\  ___\\   /\\ \"-.\\ \\   /\\ \\/\\ \\\r\n"
						+ "\\ \\  __<   \\ \\  __ \\  \\ \\ \\-.  \\  \\ \\  _\"-.  \\ \\ \\-./\\ \\  \\ \\  __\\   \\ \\ \\-.  \\  \\ \\ \\_\\ \\\r\n"
						+ " \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_\\\\\"\\_\\  \\ \\_\\ \\_\\  \\ \\_\\ \\ \\_\\  \\ \\_____\\  \\ \\_\\\\\"\\_\\  \\ \\_____\\\r\n"
						+ "  \\/_____/   \\/_/\\/_/   \\/_/ \\/_/   \\/_/\\/_/   \\/_/  \\/_/   \\/_____/   \\/_/ \\/_/   \\/_____/\r\n"
						+ "\r\n"
						+ "=== * === # === & === @ === * === # === & === @ === * === # === & === @ === * === # === & === @ === * ===\r\n"
						+ "Welcome Mr/Mrs. username\r\n"
						+ "1. View Accounts\r\n"
						+ "2. Create New Account\r\n"
						+ "3. Pay Bills\r\n"
						+ "4. Sell Chicken\r\n"
						+ "5. Exit Bank");
		do {
			System.out.print(">> ");
			try {
				choice = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			switch(choice) {
			case 1:
				viewAccount();
				User.viewBank();
				break;
			case 2:
				createNewAccount();
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				return;
			}
		} while(choice != 5);
	}

	

	private static void viewAccount() {
		
		
	}



	private static void createNewAccount() {
		Scanner scan = new Scanner(System.in);
		int pick = 0;
		do {
			System.out.println(" ______     ______     ______     ______     ______   ______        __   __     ______     __     __        ______     ______     ______     ______     __  __     __   __     ______\r\n"
					+ "/\\  ___\\   /\\  == \\   /\\  ___\\   /\\  __ \\   /\\__  _\\ /\\  ___\\      /\\ \"-.\\ \\   /\\  ___\\   /\\ \\  _ \\ \\      /\\  __ \\   /\\  ___\\   /\\  ___\\   /\\  __ \\   /\\ \\/\\ \\   /\\ \"-.\\ \\   /\\__  _\\\r\n"
					+ "\\ \\ \\____  \\ \\  __<   \\ \\  __\\   \\ \\  __ \\  \\/_/\\ \\/ \\ \\  __\\      \\ \\ \\-.  \\  \\ \\  __\\   \\ \\ \\/ \".\\ \\     \\ \\  __ \\  \\ \\ \\____  \\ \\ \\____  \\ \\ \\/\\ \\  \\ \\ \\_\\ \\  \\ \\ \\-.  \\  \\/_/\\ \\/\r\n"
					+ " \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_\\ \\_\\    \\ \\_\\  \\ \\_____\\     \\ \\_\\\\\"\\_\\  \\ \\_____\\  \\ \\__/\".~\\_\\     \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\\\\"\\_\\    \\ \\_\\\r\n"
					+ "  \\/_____/   \\/_/ /_/   \\/_____/   \\/_/\\/_/     \\/_/   \\/_____/      \\/_/ \\/_/   \\/_____/   \\/_/   \\/_/      \\/_/\\/_/   \\/_____/   \\/_____/   \\/_____/   \\/_____/   \\/_/ \\/_/     \\/_/\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "=== * === # === & === @ === * === # === & === @ === * === # === & === @ === * === # === & === @ === * === # === & === @ === * === # === & === @ ===\r\n"
					+ "Choose the type of account you want to create :\r\n"
					+ "1. Savings Account\r\n"
					+ "2. Credit Account\r\n"
					+ "3. Back");
			try {
				pick = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(pick == 1) {
				User.addSavingAccount();
			}
			else if(pick == 2) {
				User.addCreditAccount();
			}
		
		} while(pick != 3);
		
	}
	
}
