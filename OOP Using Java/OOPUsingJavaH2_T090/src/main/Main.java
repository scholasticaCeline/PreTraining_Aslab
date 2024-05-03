package main;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Main {	
	
	public static void main(String[] args) {		
		menuAwal();
		
		Logo.logo();
	}
	
	public static void menuAwal() {
		System.out.print("\033[H\033[2J");  
		Scanner scan = new Scanner(System.in);
		int choice = 0; 
		//login page
		do {
			System.out.println("         __  ___       ___                              ___\n"
					+ " |\\/| | |__)  |  |__| |__  |  | |       |__|  /\\  \\  / |__  |\\ |\n"
					+ " |  | | |  \\  |  |  | |    \\__/ |___    |  | /~~\\  \\/  |___ | \\|\n"
					+ " ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
			System.out.println("\n1. Login");
			System.out.println("2. About Us");
			System.out.println("3. Exit");
			System.out.print(">> ");
			try {
				choice = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				System.out.println("Invalid Input");
				e.printStackTrace();
			}
			
			switch(choice) {
				case 1:
					login();
					break;
				case 2:
					aboutUs();
					break;
			}
			
			if(choice < 1 || choice > 3) {
				System.out.println("Invalid input");
				System.out.println("Press enter to continue...");
				scan.nextLine();
			}
			
		} while(choice != 3);
	}
	
	public static void login() {  
		Scanner scan = new Scanner(System.in);
		String username;
		String pass;
		
		System.out.println("Login");
		System.out.println("-----------");
		System.out.print("Username: ");
		username = scan.nextLine();
		System.out.print("Password: ");
		pass = scan.nextLine();
		
		File fpData = new File("credentials.txt");
		try {
			User user = new User();
			Scanner reader = new Scanner(fpData);
			while(reader.hasNextLine()) {
				String temp = reader.nextLine();
				String[] splitting = temp.split(",");
				
				Credentials data = new Credentials();
				data.username = splitting[1];
				data.pass = splitting[2];
				
				if(username.equals(data.username) && pass.equals(data.pass)) {
					user.setName(username);
	                user.menuUser();
	                return;
	            }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if(username.equals("Admin") && pass.equals("Admin")) {
			Admin.adminMenu();
		} else {
			System.out.println("\nInvalid credentials");
			System.out.println("Press enter to continue...");
			scan.nextLine();
			menuAwal();
		}
	}
	
	public static void aboutUs() {
		Scanner scan = new Scanner(System.in);
		System.out.println(" Welcome to Mirthful Harbor, where every splash is a moment of joy! Our swimming pool offers a haven of laughter\r\n"
				+ " and relaxation for all ages. Dive into our crystal-clear waters and immerse yourself in a world of aquatic merriment.\r\n"
				+ "\n"
				+ " _\\/_                 |                _\\/\n"
				+ " /o\\\\             \\       /            //o\\\n"
				+ "  |                 .---.                |\n"
				+ " _|_______     --  /     \\  --     ______|__\n"
				+ "          `~^~^~^~^~^~^~^~^~^~^~^~`");
		System.out.println("Press enter to continue...");
		scan.nextLine();
	}
	
}
