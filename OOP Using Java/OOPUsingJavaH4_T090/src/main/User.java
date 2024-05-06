package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
	public String id;
	String name;
	String pass;
	boolean isBuyer;
	public int money;
	static ArrayList<String> donuts = new ArrayList<String>();
	static ArrayList<Integer> quantity = new ArrayList<Integer>();	
	
	public User(String id, String name, String pass, boolean isBuyer, int money) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.isBuyer = isBuyer;
		this.money = money;
	}
	
	public static void homePage(User user) {
		System.out.println(); System.out.println(); System.out.println();
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		
		System.out.println("=========================");
		System.out.printf("| %-23s |\n", user.name);
		System.out.printf("| %-23d |\n", user.money);
		System.out.println("=========================");
		System.out.println("1. Consume Donut");
		System.out.println("2. View Donut");
		System.out.println("3. Exit Home");
		System.out.println("4. Log Out");
		System.out.print(">> ");
		do {
			choice = scan.nextInt(); scan.nextLine();
			
			switch(choice) {
			case 1:
				consume(user);
				break;
			case 2:
				view(user);
				break;
			case 3:
				return;
			case 4: 
				Main.menu();
				break;
			default:
				System.out.println("Invalid input");
			}
		} while(choice != 4);
		
	}

	private static void view(User user) {
		File fp = new File("UserTransaction.txt");
		Scanner scan = new Scanner(System.in);
		String temp;
		String id, store, donut, quantity;
		String[] splitting;
		try {
			Scanner read = new Scanner(fp);
			if(!read.hasNextLine()) {
				System.out.println("===============================================");
				System.out.println("|                    Empty                    |");
				System.out.println("===============================================");
				System.out.println("Press Enter to Continue... "); scan.nextLine();
				return;
			}
			while(read.hasNextLine()) {
	            temp = read.nextLine();
	            splitting = temp.split(",");
	            if(splitting[0].equals(user.id)) {
	            	System.out.println("");
	            }
	            //id,store,donut,quantity
	            
	        }
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	private static void consume(User user) {
		// TODO Auto-generated method stub
		
	}
	

}
