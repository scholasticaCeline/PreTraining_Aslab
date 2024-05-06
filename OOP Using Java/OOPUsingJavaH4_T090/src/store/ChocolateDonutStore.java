package store;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import main.Main;
import main.User;

public class ChocolateDonutStore extends Store{
	static String currID;
	static boolean isOpen;
	
	public String getCurrID() {
		return currID;
	}

	public static void setCurrID(String currID) {
		GlazeDonutStore.currID = currID;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public static void setOpen(boolean isOpen) {
		GlazeDonutStore.isOpen = isOpen;
	}
	
	public static void displayFromFile() {
		System.out.println(); System.out.println(); System.out.println( ); System.out.println();
		File fp = new File("Store3.txt");
		String id, name, fillType, temp;
		String [] splitting;
		Scanner scan = new Scanner(System.in);
		try {
			Scanner read = new Scanner(fp);
			if(!read.hasNextLine()) {
				System.out.println("===============================================");
				System.out.println("|                    Empty                    |");
				System.out.println("===============================================");
				System.out.println("Press Enter to Continue... "); scan.nextLine();
				return;
			}
			System.out.println("===========================================================\n"
							+ "| Donut ID | Donut Name | Donut Price | Donut Fill Type   |\n"
							+ "===========================================================");
			while(read.hasNextLine()) {
				temp = read.nextLine();
				splitting = temp.split(",");
				System.out.printf("| %-8s | %-10s | %-11s | %-17s |\n", splitting[0], splitting[1], splitting[2], splitting[3]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("===========================================================\n");
		System.out.println("Press enter to continue"); scan.nextLine();
	}
	
	public static void store(User user) {
		System.out.println(); System.out.println(); System.out.println( ); System.out.println();
		File fp = new File("Store3.txt");
		String id, name, fillType, temp;
		String [] splitting;
		int price;
		try {
			Scanner read = new Scanner(fp);
			while(read.hasNextLine()) {
				temp = read.nextLine();
				splitting = temp.split(",");
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("Welcome to Chocolate Donut Store");
			System.out.println("Store Revenue: " + user.money);
			if(isOpen) {
				System.out.println("1. Close Store");
			} else {
				System.out.println("1. Open Store");
			}
			System.out.println("2. Add Donut");
			System.out.println("3. Update Donut");
			System.out.println("4. Delete Donut");
			System.out.println("5. View Donut");
			System.out.println("6. Log Out");		
			System.out.print(">> ");
			
			try {
				choice = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				System.out.println("Invalid input");
				e.printStackTrace();
			}
			
			switch(choice) {
			case 1:
				if(isOpen) {
					isOpen = false;
					Store.writeToFile(currID, "Filled Donut", true);
				} else {
					isOpen = true;
					Store.writeToFile(currID, "Filled Donut", false);
				}
				break;
			case 2:
				displayFromFile();
				String nameAdd, level;
				int priceAdd;
				do {
					System.out.print("Input donut name [5-10]: ");
					nameAdd = scan.nextLine();
				} while(nameAdd.length() < 5 || nameAdd.length() > 10);
				
				do {
					System.out.print("Input donut price [>10000]: ");
					priceAdd = scan.nextInt(); scan.nextLine();
				} while(priceAdd <= 10000);
				
				do {
					System.out.print("Input donut fill type [Milk Chocolate | Dark Chocolate | White Chocolate]: ");
					level = scan.nextLine();
				} while(!level.equals("Milk Chocolate") && !level.equals("Dark Chocolate") && !level.equals("White Chocolate"));
				
				Random random = new Random();
				String idAdd = "DT" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10);
				
				try {
					FileWriter fw = new FileWriter(fp, true);
					fw.write(idAdd + "," + nameAdd + "," + priceAdd + "," + level + "\n");
		            fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				//update
				displayFromFile();
			    System.out.println();
			    try {
			        String idUpdate, nameUpdate, fillTypeUpdate;
			        int priceUpdate;
			        boolean found = false;
			        File tempFile = new File("temp.txt");
			        File inputFile = new File("Store3.txt");
			        Scanner read = new Scanner(inputFile);
			        if (!read.hasNextLine()) break;
			        System.out.print("Input Donut ID to update: ");
			        idUpdate = scan.nextLine();
			        
			        FileWriter fw = new FileWriter(tempFile);
			        while (read.hasNextLine()) {
			            temp = read.nextLine();
			            splitting = temp.split(",");
			            if (splitting.length > 0 && splitting[0].equals(idUpdate)) {
			                found = true;
			                do {
								System.out.print("Input donut name [5-10]: ");
								nameUpdate = scan.nextLine();
							} while(nameUpdate.length() < 5 || nameUpdate.length() > 10);
							
							do {
								System.out.print("Input donut price [>10000]: ");
								priceUpdate = scan.nextInt(); scan.nextLine();
							} while(priceUpdate <= 10000);
							
							do {
								System.out.print("Input donut fill type [Milk Chocolate | Dark Chocolate | White Chocolate]: ");
								level = scan.nextLine();
							} while(!level.equals("Milk Chocolate") && !level.equals("Dark Chocolate") && !level.equals("White Chocolate"));
			                fw.write(idUpdate + "," + nameUpdate + "," + priceUpdate + "," + level + "\n");
			            } else {
			                fw.write(temp + "\n");
			            }
			        }
			        fw.close();
			        read.close();
			        
			        if (!found) {
			            System.out.println("ID not found.");
			        } else {
			            if (!inputFile.delete()) {
			                System.out.println("Could not delete the original file.");
			                return;
			            }
			            if (!tempFile.renameTo(inputFile)) {
			                System.out.println("Could not rename the temp file.");
			            }
			            System.out.println("Donut with ID " + idUpdate + " has been updated successfully.");
			        }
			    } catch (FileNotFoundException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			    break;
			case 4:
				displayFromFile();
			    System.out.println();
			    try {
			        boolean found = false;
			        String idDelete;
			        File tempFile = new File("temp.txt");
			        File inputFile = new File("Store3.txt");
			        Scanner read = new Scanner(inputFile);
			        if (!read.hasNextLine()) break;
			        System.out.println("Insert Donut ID to delete: ");
			        idDelete = scan.nextLine();
			        
			        FileWriter fw = new FileWriter(tempFile);
			        while (read.hasNextLine()) {
			            temp = read.nextLine();
			            splitting = temp.split(",");
			            if (splitting.length > 0 && splitting[0].equals(idDelete)) {
			                found = true;
			                continue;
			            }
			            fw.write(temp + "\n");
			        }
			        fw.close();
			        read.close();
			        
			        if (!found) {
			            System.out.println("ID not found.");
			        } else {
			            if (!inputFile.delete()) {
			                System.out.println("Could not delete the original file.");
			                return;
			            }
			            if (!tempFile.renameTo(inputFile)) {
			                System.out.println("Could not rename the temp file.");
			            }
			            System.out.println("Donut with ID " + idDelete + " has been deleted successfully.");
			        }
			    } catch (FileNotFoundException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			    break;
			case 5:
 				displayFromFile();
 				break;
 			case 6:
 				Main.menu();
			}
		} while(choice != 6);
		
	}

	public static void insideStore(User user) {
		if(!isOpen) {
			System.out.println("Shop is closed");
			return;
		}
		Scanner scan = new Scanner(System.in);
		String pick;
		displayFromFile();
		System.out.println("Welcome to Chocolate Donut Store");
		System.out.println("1. Buy Donut");
		System.out.println("2. Exit Store");
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
			System.out.print("Input donut ID to buy [Case sensitive]: ");
			pick = scan.nextLine();
			boolean found = false;
			
			System.out.println(); System.out.println(); System.out.println( ); System.out.println();
			File fp = new File("Store3.txt");
			String temp;
			String [] splitting;
			try {
				Scanner read = new Scanner(fp);
				while(read.hasNextLine()) {
					temp = read.nextLine();
					splitting = temp.split(",");
					if(splitting[0].equals(pick)) {
						found = true;
						int quantity = 0;
						do {
							System.out.print("Input quantity [>0]: ");
							quantity = Integer.parseInt(scan.nextLine());
						} while(quantity <= 0);
						int price = Integer.parseInt(splitting[2]);
						int total = price*quantity;
						System.out.println("Total price: " + total);
						char confirm = 0;
						do {
							System.out.print("Confirm Order [Y|N]: ");
							confirm = scan.next().charAt(0);
						} while(confirm != 'Y' && confirm != 'N');
						if(confirm == 'Y') {
							try {
                                FileWriter fw = new FileWriter("UserTransaction.txt", true);
                                fw.write(user.id + "," + "Chocolate Donut" + "," + pick + "," + quantity + "\n");
                                fw.close();
                                System.out.println("Transaction successful.");
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.out.println("Failed to write transaction details.");
                            }
						} else {
							return;
						}
						
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			if(!found) {
				System.out.println("No id found");
				System.out.println("Press enter to continue"); scan.nextLine();
			}
			break;
		case 2:
			return;
		default:
			System.out.println("Invalid input");
		}		
		
	}

}
