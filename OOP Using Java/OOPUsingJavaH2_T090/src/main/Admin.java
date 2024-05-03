package main;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Admin {
	public static void adminMenu() { 
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println(" Welcome, Admin\n"
					+ " -------------------\n"
					+ " 1. Fill pool\n"
					+ " 2. Empty pool\n"
					+ " 3. Register user\n"
					+ " 4. Logout\n");
			System.out.print(">> ");
			try {
				choice = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				System.out.println("Invalid Input");
				e.printStackTrace();
			}
			
			Pool pool = new Pool();
			
			switch(choice) {
					case 1:
						//Fill pool
//						pool.printPool();
						if(pool.isFull()) {
							System.out.println("Pool already filled");
							System.out.println("Press enter to continue...");
							scan.nextLine();
						} else {
							System.out.println("Pool before filling: ");
							pool.printPool();		
							pool.floodFill(19, 32);
							System.out.println("Pool after filling: ");
							pool.printPool();
						}
						break;
					case 2:
						//Empty pool
						System.out.println("Empty pool");
						if(pool.isEmpty()) {
							System.out.println("Pool already emptied");
							System.out.println("Press enter to continue...");
							scan.nextLine();
						} else {
							System.out.println("Pool before empty: ");
							pool.printPool();
							pool.reverseFloodFill(20, 32);
							System.out.println("Pool after emptying: ");
							pool.printPool();
						}
						break;
					case 3:
						registerUser();
						break;
			}
			if(choice < 1 || choice > 4) {
				System.out.println("Invalid choice");
				System.out.println("Press enter to continue...");
				scan.nextLine();
			}
			
		} while(choice != 4);
		Main.menuAwal();
	}
	
	public static void registerUser() {
	    String username;
	    String pass;
	    Scanner scan = new Scanner(System.in);
	    Random random = new Random();

	    System.out.print("\033[H\033[2J");
	    System.out.println("Add new user");
	    System.out.println("-------------------");
	    int usernameFlag = 0;
	    int passFlag = 0;

	    //input nama
	    do {
	        System.out.print("\nUsername: ");
	        username = scan.nextLine();
	        if (username.length() < 4) {
	            System.out.println("Username must be 4 or more");
	            usernameFlag = 0;
	        } else {
	        	File fpData = new File("credentials.txt");
	        	int exists = 0;
                try {
                    Scanner reader = new Scanner(fpData);
                    while(reader.hasNextLine()) {
                        String temp = reader.nextLine();
                        String[] splitting = temp.split(",");
                        if (username.equals(splitting[1])) {
                            System.out.println("Username must be unique");
                            exists = 1;
                            break;
                        }
                    }
                    if(exists == 0) {
                    	usernameFlag = 1;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
	        }
	    } while (usernameFlag == 0);

	    //input pass
	    do {
	        System.out.print("\nPassword: ");
	        pass = scan.nextLine();
	        if (pass.length() < 6) {
	            System.out.println("\nPassword must be 6 or more");
	            passFlag = 0;
	        } else {
	            //cek ada uppercase gk
	            int upperExist = 0;
	            for (int i = 0; i < pass.length(); i++) {
	                if (Character.isUpperCase(pass.charAt(i))) {
	                    upperExist = 1;
	                    break;
	                }
	            }

	            if (upperExist == 0) {
	                System.out.println("\nPassword must have at least 1 uppercase letter");
	                passFlag = 0; 
	            } else {
	                passFlag = 1;
	            }
	        }
	    } while (passFlag == 0);

	    //generate idnya
	    String id = "US" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10);
	    
	    //Append to file
	    String textAdd = id + "," + username + "," + pass;
	    File fpWrite = new File("credentials.txt");
	    try {
			FileWriter fw = new FileWriter(fpWrite, true);
			fw.write(textAdd);
            fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    System.out.println("User " + id + " successfully registered");
	    System.out.println("Press enter to continue...");
	    scan.nextLine();

	}

}
