package main;

import java.util.ArrayList;
import java.util.Scanner;

import bank.creditAccount;
import bank.savingAccount;
import map.mainMap;

public class User {
	private static String username;
	private static int chickenAmount = 0;
	private static int xPos, yPos;
	private static ArrayList <savingAccount> save = new ArrayList<>();
	private static ArrayList <creditAccount> credit = new ArrayList<>();
	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		User.username = username;
	}

	public static int getChickenAmount() {
		return chickenAmount;
	}

	public static void setChickenAmount(int chickenAmount) {
		User.chickenAmount = chickenAmount;
	}

	public static int getxPos() {
		return xPos;
	}

	public static void setxPos(int xPos) {
		User.xPos = xPos;
	}

	public static int getyPos() {
		return yPos;
	}

	public static void setyPos(int yPos) {
		User.yPos = yPos;
	}

	public static ArrayList<savingAccount> getSave() {
		return save;
	}

	public static void setSave(ArrayList<savingAccount> save) {
		User.save = save;
	}

	public static ArrayList<creditAccount> getCredit() {
		return credit;
	}

	public static void setCredit(ArrayList<creditAccount> credit) {
		User.credit = credit;
	}

	public static ArrayList<String> getBoughtItems() {
		return boughtItems;
	}

	public static void setBoughtItems(ArrayList<String> boughtItems) {
		User.boughtItems = boughtItems;
	}

	private static ArrayList<String> boughtItems = new ArrayList<>();
	
	public static void buyItem(String itemName) {
        boughtItems.add(itemName);
    }

    public static boolean hasBoughtItem(String itemName) {
        return boughtItems.contains(itemName);
    }

	public static void gamePage() {
		Scanner scan = new Scanner(System.in);
		String user;
		do {
			System.out.print("Input your username: ");
			user = scan.nextLine();
			if(user.length() < 5) {
				System.out.println("Username cannot be less than 5 characters");
				System.out.println(); 
			}
		} while(user.length() < 5);
		username = user;
		
		savingAccount saveAcc = new savingAccount(username, 500);
		save.add(saveAcc);
		System.out.println("Welcome, Mr/Mrs. " + username);
		System.out.println("A new savings account has also been created");
		System.out.println("Press enter to start playing the game"); scan.nextLine();
		
		//main map
		mainMap.moveInMap(xPos, yPos);
	} 
	
	public static void viewBank() {
		Scanner scan = new Scanner(System.in);
		System.out.println(" ______     ______     __   __   __     __   __     ______     ______        ______     ______     ______     ______     __  __     __   __     ______\r\n"
				+ "/\\  ___\\   /\\  __ \\   /\\ \\ / /  /\\ \\   /\\ \"-.\\ \\   /\\  ___\\   /\\  ___\\      /\\  __ \\   /\\  ___\\   /\\  ___\\   /\\  __ \\   /\\ \\/\\ \\   /\\ \"-.\\ \\   /\\__  _\\\r\n"
				+ "\\ \\___  \\  \\ \\  __ \\  \\ \\ \\'/   \\ \\ \\  \\ \\ \\-.  \\  \\ \\ \\__ \\  \\ \\___  \\     \\ \\  __ \\  \\ \\ \\____  \\ \\ \\____  \\ \\ \\/\\ \\  \\ \\ \\_\\ \\  \\ \\ \\-.  \\  \\/_/\\ \\/\r\n"
				+ " \\/\\_____\\  \\ \\_\\ \\_\\  \\ \\__|    \\ \\_\\  \\ \\_\\\\\"\\_\\  \\ \\_____\\  \\/\\_____\\     \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\\\\"\\_\\    \\ \\_\\\r\n"
				+ "  \\/_____/   \\/_/\\/_/   \\/_/      \\/_/   \\/_/ \\/_/   \\/_____/   \\/_____/      \\/_/\\/_/   \\/_____/   \\/_____/   \\/_____/   \\/_____/   \\/_/ \\/_/     \\/_/");
		viewDebit();
		
		System.out.println(" ______     ______     ______     _____     __     ______      ______     ______     ______     ______     __  __     __   __     ______\r\n"
				+ "/\\  ___\\   /\\  == \\   /\\  ___\\   /\\  __-.  /\\ \\   /\\__  _\\    /\\  __ \\   /\\  ___\\   /\\  ___\\   /\\  __ \\   /\\ \\/\\ \\   /\\ \"-.\\ \\   /\\__  _\\\r\n"
				+ "\\ \\ \\____  \\ \\  __<   \\ \\  __\\   \\ \\ \\/\\ \\ \\ \\ \\  \\/_/\\ \\/    \\ \\  __ \\  \\ \\ \\____  \\ \\ \\____  \\ \\ \\/\\ \\  \\ \\ \\_\\ \\  \\ \\ \\-.  \\  \\/_/\\ \\/\r\n"
				+ " \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\____-  \\ \\_\\    \\ \\_\\     \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\\\\"\\_\\    \\ \\_\\\r\n"
				+ "  \\/_____/   \\/_/ /_/   \\/_____/   \\/____/   \\/_/     \\/_/      \\/_/\\/_/   \\/_____/   \\/_____/   \\/_____/   \\/_____/   \\/_/ \\/_/     \\/_/");
		viewCredit();
		
		System.out.println("Press enter to continue"); scan.nextLine();
	}

	private static void viewCredit() {
		int index = 1;
		Scanner scan = new Scanner(System.in);
		System.out.println("================================================================================================\r\n"
				+ "| No. | Card Number          | Card Holder Name          | CVV   | Limit        | Bills        |\r\n"
				+ "================================================================================================");
		if(credit.isEmpty()) {
			System.out.println("                       You don't have any Account yet !!");
			System.out.println("Press enter to continue"); scan.nextLine();
			return;
		}		
		for(creditAccount cred: credit) {
			System.out.printf("| %-3d | %-20s | %-25s | %-5s | %-12d | %-12d |\n", index++, cred.getCardNumber(), cred.getHolderName(), cred.getCvvNumber(), cred.getLimit(), cred.getBill());
		}
		
	}

	private static void viewDebit() {
		int index = 1;
		System.out.println("=================================================================================\r\n"
				+ "| No. | Card Number          | Card Holder Name          | CVV   | Balance      |\r\n"
				+ "=================================================================================");
		for(savingAccount savings: save) {
			System.out.printf("| %-3d | %-20s | %-25s | %-5s | %-12d |\n", index++, savings.getCardNumber(), savings.getHolderName(), savings.getCvvNumber(), savings.getBalance());
		}
		System.out.println("=================================================================================\r\n");

	}

	public static void addSavingAccount() {
		Scanner scan = new Scanner(System.in);
		String newName = null;
		boolean userAgree = true;
		char agree;
		do{
			System.out.println("=== $ === Create New Savings Account === $ ===");
			System.out.print("Input Card Holder Name [enter '1' to use logged in username]: ");
			newName = scan.nextLine();
			if(newName.length() < 5) {
				System.out.println("Name cannot be less than 5 Character!!");
				continue;
			}
			
			if(newName.equals("1")) {
				newName = username;
			}
			savingAccount saveAcc = new savingAccount(username, 0);
			save.add(saveAcc);
			System.out.printf("=== * === New Savings Card Detail === * ===\r\n"
					+ " [+] Card        -> %s\n"
					+ " [+] Card Holder Name -> %s\n"
					+ " [+] CVV Number       -> %s\n"
					+ " [+] Balance          -> %d\n", saveAcc.getCardNumber(), newName, saveAcc.getCvvNumber(), 0);

			System.out.print("Are you sure you want to Create this new Account? [y/n] ");
			agree = scan.next().charAt(0);
			if(agree == 'y' || agree == 'Y') userAgree = true;
			else if(agree == 'n' || agree == 'N') {
				save.remove(saveAcc);
				userAgree = false;
				continue;
			}
		} while(!userAgree);
		
		savingAccount saveAcc = new savingAccount(username, 0);
		save.add(saveAcc);
		
	}

	public static void addCreditAccount() {
		Scanner scan = new Scanner(System.in);
		String newName = null;
		boolean userAgree = true;
		char agree;
		int limit = 0;
		do{
			System.out.println("=== $ === Create New Credit Account === $ ===");
			System.out.print("Input Card Holder Name [enter '1' to use logged in username]: ");
			newName = scan.nextLine();
			if(newName.length() < 5) {
				System.out.println("Name cannot be less than 5 Character!!");
				continue;
			}
			if(newName.equals("1")) {
				newName = username;
			}
			
			int pickDebit = 0, maxChoice = 1;

			for(savingAccount savings: save) {
				maxChoice++;
			}
			viewDebit();
			do {
				System.out.printf("Choose which account do you want to use [1 - %d]\n", maxChoice);
				System.out.println("[Input 0 to go back]");
				System.out.printf(">> ");
				try {
					pickDebit = scan.nextInt(); scan.nextLine();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				maxChoice = 1;
				for(savingAccount savings: save) {
					if(maxChoice == pickDebit) {
						limit = savings.getBalance();
					}
					maxChoice++;
				}
			} while(pickDebit <= maxChoice);
			
			creditAccount saveAcc = new creditAccount(username, limit, 0);
			credit.add(saveAcc);
			System.out.printf("=== * === New Credit Card Detail === * ===\r\n"
					+ " [+] Card Number      -> %s\n"
					+ " [+] Card Holder Name -> %s\n"
					+ " [+] Limit            -> %d\n"
					+ " [+] Bills            -> %d", saveAcc.getCardNumber(), newName, saveAcc.getCvvNumber(), saveAcc.getLimit(), saveAcc.getBill());

			System.out.print("Are you sure you want to Create this new Account? [y/n] ");
			agree = scan.next().charAt(0);
			if(agree == 'y' || agree == 'Y') userAgree = true;
			else if(agree == 'n' || agree == 'N') {
				credit.remove(saveAcc);
				userAgree = false;
				continue;
			}
		} while(!userAgree);
		
		savingAccount saveAcc = new savingAccount(username, 0);
		save.add(saveAcc);
		
	}

}
