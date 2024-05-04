package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class User {
	static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	static ArrayList<Villa> villas = new ArrayList<Villa>();
	
	public static void userMenu() {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println(); System.out.println();
			System.out.println("1. Booking Villa");
			System.out.println("2. Exit");
			System.out.print(">> ");
			try {
				choice = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				System.out.println("Invalid input. Please enter a valid integer");
				System.out.println("Press enter to continue..."); scan.nextLine();
				e.printStackTrace();
			}
			if(choice == 1) bookingVilla();
			if(choice == 2) return;
			
			if(choice != 2 && choice != 1) {
				System.out.println("Enter a number between 1 or 2");
				System.out.println("Press enter to continue..."); scan.nextLine();
			}
		} while(choice != 2);
	}
	
	private static boolean isAvailable(ArrayList<Villa> villas) {
		if(villas.isEmpty()) return false;
		else {
			for (Villa villa : villas) {
	            if (villa.available) return true;
	        }
		}
		
		return false;
	}

	private static void bookingVilla() {
		Admin.viewVilla();
	    Random random = new Random();
	    Scanner scan = new Scanner(System.in);
	    int num = 0, totalDay = 0, price = 0, totalGuest = 0;
	    String name = "";

	    if(!isAvailable(villas)) {
			do {
				System.out.print("Input the villa ID [0 to exit]: ");
		        try {
		            num = scan.nextInt(); scan.nextLine();
		        } catch (Exception e) {
		            System.out.println("Invalid input. Please input a valid integer.");
		            System.out.println("Press enter to continue"); scan.nextLine();
		            e.printStackTrace();
		            continue;
		        }
		        
		        if (num != 0) {
		            System.out.println("There are no villas available. Please enter 0 to exit.");
		            System.out.println("Press enter to continue..."); 
		            scan.nextLine();
		        }
			} while(num != 0);
		}

	    boolean found = false;
	    do {
	        System.out.print("Input the villa ID [0 for exit]: ");
	        try {
	            num = scan.nextInt();
	            scan.nextLine();
	        } catch (Exception e) {
	            System.out.println("Invalid input. Please input a valid integer");
	            e.printStackTrace();
	        }

	        if (num == 0) userMenu();
	        for (Villa villa : villas) {
	            if (num == villa.id) {
	                found = true;
	                break;
	            } else if(!villa.available) {
	            	System.out.println("Villa not available");
	            	System.out.println("Press enter to continue..."); scan.nextLine();
	            	userMenu();
	            }
	        }
	        if (!found) {
	            System.out.println("ID not available");
	            System.out.println("Press enter to continue..."); scan.nextLine();
	        }
	    } while (!found);

	    do {
	        System.out.print("Input the name of the person who reserved the villa [3 - 10 characters]: ");
	        name = scan.nextLine();
	        if (name.length() > 10 || name.length() < 3) {
	            System.out.println("Name must be between 3 - 10 characters");
	            System.out.println("Press enter to continue..."); scan.nextLine();
	        }
	    } while (name.length() > 10 || name.length() < 3);

	    do {
	        System.out.print("Input the total guest [> 0]: ");
	        try {
	            totalGuest = scan.nextInt(); 
	            scan.nextLine();
	        } catch (Exception e) {
	            totalGuest = 0;
	            System.out.println("Invalid input. Input must be a valid integer.");
	            System.out.println("Press enter to continue..."); scan.nextLine();
	            e.printStackTrace();
	        }
	    } while (totalGuest <= 0);

	    do {
	        System.out.print("Input the total day [> 0]: ");
	        try {
	            totalDay = scan.nextInt(); 
	            scan.nextLine();
	        } catch (Exception e) {
	            totalDay = 0;
	            System.out.println("Invalid input. Input must be a valid integer.");
	            System.out.println("Press enter to continue..."); scan.nextLine();
	            e.printStackTrace();
	        }
	    } while (totalDay <= 0);

	    char choice = ' ';
	    do {
	        System.out.print("Are you sure to book the villa [Y | N]: ");
	        choice = scan.nextLine().charAt(0);
	    } while (choice != 'Y' && choice != 'N');

	    if (choice == 'Y') {
	    	int hahaGacha = random.nextInt(4) + 1;
	    	if(hahaGacha == 1) {
	    		for (Villa villa : villas) {
		            if (villa.id == num) {
		            	villa.price = (int)(villa.price * 0.8);
		                break;
		            }
		        }
	    	}
	        String billID = "BI" + random.nextInt(1000);
	        
	        Transaction transaction = new Transaction(billID, num, name, totalGuest, totalDay, price);
	        transactions.add(transaction);

	        for (Villa villa : villas) {
	            if (villa.id == num) {
	                villa.available = false;
	                break;
	            }
	        }
	        System.out.println("Villa booked successfully.");
	    } else System.out.println("Booking canceled");

	    System.out.println("Press enter to continue..."); 
	    scan.nextLine();
	}


}
