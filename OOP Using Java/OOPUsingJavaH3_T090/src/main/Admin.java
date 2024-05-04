package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
	
	private static ArrayList<Villa> villas = new ArrayList<Villa>();
	private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	public static void adminMenu() {
		int choice = 0;
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println(); System.out.println();
			System.out.println("1. View Transaction");
			System.out.println("2. View Villa");
			System.out.println("3. Add New Villa");
			System.out.println("4. Edit Villa");
			System.out.println("5. Delete Villa");
			System.out.println("6. Exit");
			System.out.print(">> ");
			try {
				choice = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				System.out.println("Invalid input. Please enter a valid integer");
				System.out.println("Press enter to continue..."); scan.nextLine();
				e.printStackTrace();
			}
			
			switch(choice) {
			case 1:
				viewTransaction();
				break;
			case 2:
				viewVilla();
				System.out.println("Press enter to continue..."); scan.nextLine();
				break;
			case 3:
				addNew();
				break;
			case 4:
				editVilla();
				break;
			case 5:
				deleteVilla();
				break;
			}
			
			if(choice < 1 || choice > 6) {
				System.out.println("Enter a number between 1 and 6");
				System.out.println("Press enter to continue..."); scan.nextLine();
			}
		} while(choice != 6);
	}

	private static void deleteVilla() {
		Scanner scan = new Scanner(System.in);
		int id = 0;
		viewVilla();
		
		boolean idFlag = false;
		do {
			System.out.print("Input the villa ID [0 to exit]: ");
			try {
				id = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				idFlag = true;
				System.out.println("Invalid input. Please enter a valid integer");
				System.out.println("Press enter to continue..."); scan.nextLine();
				e.printStackTrace();
			}
		} while(idFlag);
		
		for (Villa villa : villas) {
            if (villa.id == id) {
            	if(!villa.available) {
            		System.out.println("The ID doesn't exists");
            		System.out.println("Press enter to continue..."); scan.nextLine();
            		return;
            	}
            }
        }
		villas.remove(id-1);
		
	}

	private static void editVilla() {
	    Scanner scan = new Scanner(System.in);
	    int id = 0, total = 0, price = 0;
	    String type;
	    boolean isGardenRoom = false, totalFlag, typeFlag, priceFlag;

	    viewVilla();

	    boolean idFlag = false;
	    do {
	        System.out.print("Input the villa ID [0 to exit]: ");
	        try {
	            id = scan.nextInt(); scan.nextLine();
	        } catch (Exception e) {
	            idFlag = true;
	            System.out.println("Invalid input. Please enter a valid integer");
	            System.out.println("Press enter to continue..."); scan.nextLine();
	            e.printStackTrace();
	        }
	    } while (idFlag);

	    for (Villa villa : villas) {
	        if (villa.id == id) {
	            if (!villa.available) {
	                System.out.println("The ID doesn't exist");
	                System.out.println("Press enter to continue..."); scan.nextLine();
	                return;
	            } else {
	                totalFlag = false;
	                do {
	                    System.out.print("Input the total number of bedrooms [1 - 3]: ");
	                    try {
	                        total = scan.nextInt(); scan.nextLine();
	                    } catch (Exception e) {
	                        totalFlag = true;
	                        System.out.println("Invalid input. Please enter a valid integer.");
	                        System.out.println("Press enter to continue..."); scan.nextLine();
	                        continue;
	                    }

	                    if (total < 1 || total > 3) {
	                        totalFlag = true;
	                        System.out.println("Number of bedrooms must be between 1 and 3.");
	                        System.out.println("Press enter to continue..."); scan.nextLine();
	                    }
	                } while (totalFlag);
	                villa.totalBedroom = total;

	                typeFlag = false;
	                do {
	                    System.out.print("Input the type of the villa [River Room | Garden Room]: ");
	                    type = scan.nextLine();
	                    if (!type.equals("River Room") && !type.equals("Garden Room")) {
	                        typeFlag = true;
	                        System.out.println("Invalid input. Please enter either 'River Room' or 'Garden Room'.");
	                        System.out.println("Press enter to continue..."); scan.nextLine();
	                        continue;
	                    }
	                    isGardenRoom = type.equals("Garden Room");
	                } while (typeFlag);
	                villa.type = isGardenRoom;

	                priceFlag = false;
	                do {
	                    System.out.print("Input the price [> 0]: ");
	                    try {
	                        price = scan.nextInt(); scan.nextLine();
	                    } catch (Exception e) {
	                        priceFlag = true;
	                        System.out.println("Invalid input. Please enter a valid integer.");
	                        System.out.println("Press enter to continue..."); scan.nextLine();
	                        continue;
	                    }
	                    if (price <= 0) {
	                        priceFlag = true;
	                        System.out.println("Price must be a positive number");
	                        System.out.println("Press enter to continue..."); scan.nextLine();
	                    }
	                } while (priceFlag);
	                villa.price = price;
	                System.out.println("Villa details updated successfully.");
	            }
	        }
	    }
	}


	private static void addNew() {
	    Scanner scan = new Scanner(System.in);
	    boolean totalFlag = false, typeFlag = false, priceFlag = false;
	    int total = 0, price = 0;
	    String type;
	    boolean isGardenRoom = false;

	    do {
	        System.out.print("Input the total number of bedrooms [1 - 3]: ");
	        try {
	            total = scan.nextInt(); scan.nextLine();
	        } catch (Exception e) {
	            totalFlag = true;
	            System.out.println("Invalid input. Please enter a valid integer.");
	            System.out.println("Press enter to continue..."); scan.nextLine();
	            continue;
	        }

	        if (total < 1 || total > 3) {
	            totalFlag = true;
	            System.out.println("Number of bedrooms must be between 1 and 3.");
	            System.out.println("Press enter to continue..."); scan.nextLine();
	        }
	    } while (totalFlag);

	    do {
	        System.out.print("Input the type of the villa [River Room | Garden Room]: ");
	        type = scan.nextLine();
	        if (!type.equals("River Room") && !type.equals("Garden Room")) {
	            typeFlag = true;
	            System.out.println("Invalid input. Please enter either 'River Room' or 'Garden Room'.");
	            System.out.println("Press enter to continue..."); scan.nextLine();
	            continue;
	        }
	    } while (typeFlag);
	    isGardenRoom = type.equals("Garden Room");

	    do {
	        System.out.print("Input the price [> 0]: ");
	        try {
	            price = scan.nextInt();
	            scan.nextLine();
	        } catch (Exception e) {
	            priceFlag = true;
	            System.out.println("Invalid input. Please enter a valid integer.");
	            System.out.println("Press enter to continue..."); 
	            scan.nextLine();
	            continue;
	        }
	        if (price <= 0) {
	            priceFlag = true;
	            System.out.println("Price must be a positive number");
	            System.out.println("Press enter to continue..."); 
	            scan.nextLine();
	        }
	    } while (priceFlag);

	    Villa villa = new Villa(isGardenRoom, total, true, price);
	    villas.add(villa);

	    System.out.println("Villa added successfully.");
	    System.out.println("Press enter to continue..."); 
	    scan.nextLine();
	}

	
	static void viewVilla() {
		Scanner scan = new Scanner(System.in);
		System.out.println("=====================================================================");
	    System.out.println("| ID |     Type        | Total Bedroom |    Status     |    Price   |");
	    System.out.println("=====================================================================");

	    if (villas.isEmpty()) System.out.println("|                      The data is empty                         |");
	    else {
	    	for (Villa villa : villas) {
	            if(!villa.available) {
	            	continue;
	            } else {
	            	String villaType = villa.type ? "Garden Room" : "River Room";
		            String villaStatus = villa.available ? "Available" : "Not Available";

		            System.out.printf("| %2d | %-15s | %13d | %-13s | %10d |\n",
		                    villa.id, villaType, villa.totalBedroom, villaStatus, villa.price);
	            }
	        }
	    }

	    System.out.println("======================================================================");
	}


	private static void viewTransaction() {
	    Scanner scan = new Scanner(System.in);
	    System.out.println("===================================================================================");
	    System.out.println("| Bill ID | Villa Number | Reserved By | Total Guest | Total Day |   Total Price  |");
	    System.out.println("===================================================================================");

	    if (transactions.isEmpty()) {
	        System.out.println("|                           There's no Transaction                                |");
	    } else {
	        for (Transaction transaction : transactions) {
	            System.out.printf("| %7s | %12d | %11s | %11d | %9d | %14d |\n",
	                    transaction.billID, transaction.number, transaction.reserveName,
	                    transaction.totalGuest, transaction.totalDay, transaction.totalPrice);
	        }
	    }

	    System.out.println("===================================================================================");
	    System.out.println("Press enter to continue..."); scan.nextLine();
	}
	
	static boolean isAvailable(ArrayList<Villa> villas) {
		if(villas.isEmpty()) return false;
		else {
			for (Villa villa : villas) {
	            if (villa.available) return true;
	        }
		}
		
		return false;
	}

}
