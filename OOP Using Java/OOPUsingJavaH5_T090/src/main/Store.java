package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Store {
	public static void viewStore(){
		Scanner scan = new Scanner(System.in);
		System.out.println(" (`-').-> (`-').->            _  (`-')\r\n"
						+ " ( OO)_   (OO )__      .->    \\-.(OO )\r\n"
						+ "(_)--\\_) ,--. ,'-'(`-')----.  _.'    \\\r\n"
						+ "/    _ / |  | |  |( OO).-.  '(_...--''\r\n"
						+ "\\_..`--. |  `-'  |( _) | |  ||  |_.' |\r\n"
						+ ".-._)   \\|  .-.  | \\|  |)|  ||  .___.'\r\n"
						+ "\\       /|  | |  |  '  '-'  '|  |\r\n"
						+ " `-----' `--' `--'   `-----' `--'\r\n"
						+ "=== o === O === o === O === o === O === o ===\r\n"
						+ "1. View Item List\r\n"
						+ "2. Buy Item\r\n"
						+ "3. Exit");
		int choice = 0;
		do {
			System.out.println(">> ");
			try {
				choice = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			switch(choice) {
			case 1:
				System.out.println("   ______                    _____ __\r\n"
								+ "   /  _/ /____  ____ ___     / ___// /_  ____  ____\r\n"
								+ "   / // __/ _ \\/ __ `__ \\    \\__ \\/ __ \\/ __ \\/ __ \\\r\n"
								+ " _/ // /_/  __/ / / / / /   ___/ / / / / /_/ / /_/ /\r\n"
								+ "/___/\\__/\\___/_/ /_/ /_/   /____/_/ /_/\\____/ .___/\r\n"
								+ "                                           /_/");
				System.out.println();
				viewItem();
				System.out.println("Press enter to continue"); scan.nextLine();
				break;
			case 2:
				buyItem();
				break;
			case 3:
				return;
			}
		} while(choice != 3);
		
	}

	private static void buyItem() {
		Scanner scan = new Scanner(System.in);
		String temp;
		String[] splitting;
		int choice = 0;
		viewItem();
		System.out.println("Choose which item that you want to buy [ 1 - 4 ]:\n"
				+ "[Input 0 to go back]");
		do {
			System.out.print(">> ");
			try {
				choice = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(choice == 0) return;
		} while(choice != 0);
		
		File fp = new File("shop_items.txt");
		int parse = 1;
		try {
			Scanner read = new Scanner(fp);
			while(read.hasNextLine()) {
				temp = read.nextLine();
				splitting = temp.split(",");
				if(parse == choice) {
					if(User.hasBoughtItem(splitting[0])) {
						System.out.println("You have bought this item!");
						System.out.println("Press enter to continue"); scan.nextLine();
						return;
					}
					
					User.buyItem(splitting[0]);
				}
				parse++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return;
	}

	private static void viewItem() {
		File fp = new File("shop_items.txt");
		String temp, boughtStatus;
		String[] splitting;
		System.out.println("-------------------------------------------------------------------------\n"
						+ "| Item Name                 | Price      | Effect Type     | Bought     |\n"
						+ "-------------------------------------------------------------------------");
		try {
			Scanner read = new Scanner(fp);
			while(read.hasNextLine()) {
				temp = read.nextLine();
				splitting = temp.split(",");
				boughtStatus = User.hasBoughtItem(splitting[0]) ? "True" : "False";

				System.out.printf("| %-25s | %-10s | %-15s | %-10s |", splitting[0], splitting[1], splitting[2], boughtStatus);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------------------------------------------------------\n");
		
	}
}
