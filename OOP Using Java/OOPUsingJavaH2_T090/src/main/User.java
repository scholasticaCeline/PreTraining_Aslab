package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class User {
	private static int userSpeed;
	private static String name;
	
	public void setName(String name) {
		User.name = name;
	}
	
	public static void menuUser() {
		int choice = 0;
		do {
			Scanner scan = new Scanner(System.in);
			System.out.println(" Welcome, Johnson!\n"
					+ " -------------------\n"
					+ " 1. Swim\n"
					+ " 2. Tutorial\n"
					+ " 3. Store\n"
					+ " 4. Logout\n");
			System.out.print(">> ");
			try {
				choice = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				System.out.println("Invalid Input");
				e.printStackTrace();
			}
			Pool pool = new Pool();
			
			switch(choice){
				case 1:
					if(pool.isEmpty()) {
						System.out.println("Pool hasn't been filled");
						System.out.print("Press enter to continue...");
						scan.nextLine();
					} else {
						swim();
					}
					break;
				case 2:
					tutorial();
					break;
				case 3:
					buyEquip(name);
					break;
			}
			if(choice < 1 || choice > 4) {
				System.out.println("Invalid choice");
				System.out.println("Press enter to continue...");
				scan.nextLine();
			}
		} while(choice != 4);
	}
	
	public static void swim() {
		
	}
	
	public static void tutorial() {
		Scanner scan = new Scanner(System.in);
		System.out.println(" Movement Tutorial\r\n"
				+ " -----------------\r\n"
				+ "\r\n"
				+ " Swim Controls\r\n"
				+ " W -> Up\r\n"
				+ " S -> Down\r\n"
				+ " A -> Left\r\n"
				+ " D -> Right\r\n"
				+ "\r\n"
				+ " Objects\r\n"
				+ " # -> Wall\r\n"
				+ " . -> Water\r\n"
				+ " E -> Exit Pool\r\n"
				+ "\r\n"
				+ " Press enter to continue...");
		scan.nextLine();
	}
	
	public static void buyEquip(String name) {
		Scanner scan = new Scanner(System.in);
		Equipment equipment = new Equipment();
		String textAdd = "";
		int pick = 0;
		do {
			System.out.println(" Equipment Store\r\n"
					+ " -------------------\r\n"
					+ " 1. Swimcap\r\n"
					+ " 2. Goggle\r\n"
					+ " 3. Swimsuit\r\n"
					+ " 4. Exit");
			System.out.print(">> ");
			pick = scan.nextInt();
			scan.nextLine();
			
			if(pick == 1) textAdd = name + "," + "Swimcap";
	        else if(pick == 2) textAdd = name + "," + "Goggle";
	        else if(pick == 3) textAdd = name + "," + "Swimsuit";
	        else if(pick == 4) menuUser();
	        userSpeed = equipment.getSpeed(pick);
			
	        if(hasEquipment(name)) {
	        	System.out.println("You have already bought an equipment");
	        	System.out.println("Press enter to continue...");
	        	scan.nextLine();
	        }
	        
		    File fpWrite = new File("credentials.txt");
		    try {
				FileWriter fw = new FileWriter(fpWrite, true);
				fw.write(textAdd);
				fw.write("\n");
	            fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} while(pick > 4 || pick < 1);
	}
	
	public static boolean hasEquipment(String name) {
        try {
            File file = new File("credentials.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith(name)) {
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
