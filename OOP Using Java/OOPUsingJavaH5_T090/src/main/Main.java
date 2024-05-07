package main;

import java.util.Scanner;

import map.mainMap;

public class Main {
	
	public static void main(String[] args) {
		menu();
	}
	
	public static void menu() {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		System.out.println("           (`-').->  _               <-.(`-')  (`-')  _<-. (`-')_           (`-')  _    (`-') <-. (`-')\r\n"
						+ " _         (OO )__  (_)     _         __( OO)  ( OO).-/   \\( OO) )  <-.     (OO ).-/ <-.(OO )    \\(OO )_\r\n"
						+ " \\-,-----.,--. ,'-' ,-(`-') \\-,-----.'-'. ,--.(,------.,--./ ,--/(`-')-----./ ,---.  ,------,),--./  ,-.)\r\n"
						+ "  |  .--./|  | |  | | ( OO)  |  .--./|  .'   / |  .---'|   \\ |  |(OO|(_\\---'| \\ /`.\\ |   /`. '|   `.'   |\r\n"
						+ " /_) (`-')|  `-'  | |  |  ) /_) (`-')|      /)(|  '--. |  . '|  |)/ |  '--. '-'|_.' ||  |_.' ||  |'.'|  |\r\n"
						+ " ||  |OO )|  .-.  |(|  |_/  ||  |OO )|  .   '  |  .--' |  |\\    | \\_)  .--'(|  .-.  ||  .   .'|  |   |  |\r\n"
						+ "(_'  '--'\\|  | |  | |  |'->(_'  '--'\\|  |\\   \\ |  `---.|  | \\   |  `|  |_)  |  | |  ||  |\\  \\ |  |   |  |\r\n"
						+ "   `-----'`--' `--' `--'      `-----'`--' '--' `------'`--'  `--'   `--'    `--' `--'`--' '--'`--'   `--'\r\n"
						+ "=== * === # === & === @ === * === # === & === @ === * === # === & === @ === * === # === & === @ === * ===");
		do {
			System.out.println("1. Start Game\r\n"
					+ "2. How To Play\r\n"
					+ "3. Exit");
			System.out.print(">> ");
			try {
				choice = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				System.out.println("Invalid input");
				e.printStackTrace();
			}
			switch(choice) {
			case 1:
			    mainMap.readFromFile();
			    User.gamePage();
			    break;
			case 2:
				howToPlay();
				break;
			case 3:
				Logo.logo();
				System.exit(0);
			}
		} while(choice != 3);
	}

	private static void howToPlay() {
		System.out.println(); System.out.println(); System.out.println(); System.out.println();
		Scanner scan = new Scanner(System.in);
		System.out.println(" ('-. .-.               (`\\ .-') /`       .-') _                         _ (`-.              ('-.\r\n"
						+ "( OO )  /                `.( OO ),'      (  OO) )                       ( (OO  )            ( OO ).-.\r\n"
						+ ",--. ,--. .-'),-----. ,--./  .--.        /     '._  .-'),-----.        _.`     \\ ,--.       / . --. /  ,--.   ,--.\r\n"
						+ "|  | |  |( OO'  .-.  '|      |  |        |'--...__)( OO'  .-.  '      (__...--'' |  |.-')   | \\-.  \\    \\  `.'  /\r\n"
						+ "|   .|  |/   |  | |  ||  |   |  |,       '--.  .--'/   |  | |  |       |  /  | | |  | OO ).-'-'  |  | .-')     /\r\n"
						+ "|       |\\_) |  |\\|  ||  |.'.|  |_)         |  |   \\_) |  |\\|  |       |  |_.' | |  |`-' | \\| |_.'  |(OO  \\   /\r\n"
						+ "|  .-.  |  \\ |  | |  ||         |           |  |     \\ |  | |  |       |  .___.'(|  '---.'  |  .-.  | |   /  /\\_\r\n"
						+ "|  | |  |   `'  '-'  '|   ,'.   |           |  |      `'  '-'  '       |  |      |      |   |  | |  | `-./  /.__)\r\n"
						+ "`--' `--'     `-----' '--'   '--'           `--'        `-----'        `--'      `------'   `--' `--'   `--'\r\n"
						+ "==================================================================================================================\r\n");
		System.out.println("1. How To Play\n"
						+ "2. Item Effects\n"
						+ "3. Back to Main Menu");
		System.out.print(">> ");
		int choice = 0;
		try {
			choice = scan.nextInt(); scan.nextLine();
		} catch (Exception e) {
			System.out.println("Invalid input");
			e.printStackTrace();
		}
		switch(choice) {
		case 1:
			System.out.println(); System.out.println(); System.out.println(); System.out.println();
			System.out.println("ChIckenFArm - How To Play\r\n"
					+ "=========================\r\n"
					+ "\r\n"
					+ "[+] Objective: Make as much money as possible by catching and selling chicken.\r\n"
					+ "\r\n"
					+ "[1] You will spawn in a small town as a player (\"P\") where you can visit\r\n"
					+ "    the Farm (\"F\"), the Bank (\"B\"), and the Shop (\"S\").\r\n"
					+ "\r\n"
					+ "                ##############################\r\n"
					+ "                #####   #   ####      #  #####\r\n"
					+ "                ###          ##   S        ###\r\n"
					+ "                ##                          ##\r\n"
					+ "                #                            #\r\n"
					+ "                #     B                     ##\r\n"
					+ "                #               ###          #\r\n"
					+ "                ##             ###          ##\r\n"
					+ "                ###      ##    ##    F     ###\r\n"
					+ "                #####   ###              #####\r\n"
					+ "                ##############################\r\n"
					+ "\r\n"
					+ "[2] Farm - This is where you can catch the Farm Grown Chickens.\r\n"
					+ "[3] Bank - This is where you can sell you chicken, create new Accounts\r\n"
					+ "    (Saving and Credit Account) and view your balance.\r\n"
					+ "[4] Shop - This is where you can buy various items to help with catching\r\n"
					+ "    the chicken and make more money.\r\n"
					+ "[5] You can use the simple \"WASD\" command to navigate the player around\r\n"
					+ "    the map.\r\n"
					+ "\r\n"
					+ "Press Enter to Continue!!");
			scan.nextLine();
			break;
		case 2:
			System.out.println(); System.out.println(); System.out.println(); System.out.println();
			System.out.println("Shop - Item Effects\r\n"
					+ "===================\r\n"
					+ "The items sold at the shop will have some effects, depending on the Effect\r\n"
					+ "Type of the item. The effect will be active once you have bought the item\r\n"
					+ "using the money you have. Here's the list of the each item effects.\r\n"
					+ "\r\n"
					+ "[1] Make the Chickens move slower\r\n"
					+ "[2] Increases the price of chicken's selling price\r\n"
					+ "\r\n"
					+ "HINT : The more expensive the items, the better the effects are.\r\n"
					+ "\n"
					+ "Press Enter to Continue!!");
			scan.nextLine();
			break;
		case 3:
			return;
		}
		
		
	}
	
}
