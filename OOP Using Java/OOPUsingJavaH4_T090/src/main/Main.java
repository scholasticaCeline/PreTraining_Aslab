package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import store.ChocolateDonutStore;
import store.FilledDonutStore;
import store.GlazeDonutStore;
import store.Store;

public class Main {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		menu();
	}
	
	public static void menu() {
		int choice = 0;
		do{
			System.out.println("           (`-')                  _(`-')              <-. (`-')_            (`-')\r\n"
					+ "           ( OO).->    .->       ( (OO ).->     .->      \\( OO) )     .->   ( OO).->\r\n"
					+ "   <-.--.,(_/----.(`-')----.      \\    .'_ (`-')----. ,--./ ,--/ ,--.(,--.  /    '._\r\n"
					+ " (`-'| ,||__,    |( OO).-.  '     '`'-..__)( OO).-.  '|   \\ |  | |  | |(`-')|'--...__)\r\n"
					+ " (OO |(_| (_/   / ( _) | |  |     |  |  ' |( _) | |  ||  . '|  |)|  | |(OO )`--.  .--'\r\n"
					+ ",--. |  | .'  .'_  \\|  |)|  |     |  |  / : \\|  |)|  ||  |\\    | |  | | |  \\   |  |\r\n"
					+ "|  '-'  /|       |  '  '-'  '     |  '-'  /  '  '-'  '|  | \\   | \\  '-'(_ .'   |  |\r\n"
					+ " `-----' `-------'   `-----'      `------'    `-----' `--'  `--'  `-----'      `--'");
	
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			System.out.print(">> ");
			try {
				choice = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				System.out.println("Invalid input");
				e.printStackTrace();
			}
			
			switch(choice) {
			case 1:
				login();
				break;
			case 2:
				register();
				break;
			case 3:
				Logo.logo();
				System.exit(0);
			default:
				System.out.println("");
				break;
			}
			
		} while(choice != 3);
		
	}

	private static void register() {
		String name, pass;
		File fp = new File("Credentials.txt");
		boolean correctUser = true, correctPass = true;
		System.out.println("   (`-')  (`-')  _            _      (`-').->(`-')      (`-')  _   (`-')\r\n"
						+ "<-.(OO )  ( OO).-/    .->    (_)     ( OO)_  ( OO).->   ( OO).-/<-.(OO )\r\n"
						+ ",------,)(,------. ,---(`-') ,-(`-')(_)--\\_) /    '._  (,------.,------,)\r\n"
						+ "|   /`. ' |  .---''  .-(OO ) | ( OO)/    _ / |'--...__) |  .---'|   /`. '\r\n"
						+ "|  |_.' |(|  '--. |  | .-, \\ |  |  )\\_..`--. `--.  .--'(|  '--. |  |_.' |\r\n"
						+ "|  .   .' |  .--' |  | '.(_/(|  |_/ .-._)   \\   |  |    |  .--' |  .   .'\r\n"
						+ "|  |\\  \\  |  `---.|  '-'  |  |  |'->\\       /   |  |    |  `---.|  |\\  \\\r\n"
						+ "`--' '--' `------' `-----'   `--'    `-----'    `--'    `------'`--' '--'");
		String temp, nameFromFile;
		String[] splitting;
		do {
	        System.out.print("Input Username [5 - 10 length | Unique | Can't Contain Penjual]: ");
	        name = scan.nextLine();
	        if (name.length() < 5 || name.length() > 10) { //length
	            System.out.println("Username must be between 5 and 10 characters long.");
	            correctUser = false; continue; 
	        }
	        if (name.equalsIgnoreCase("Penjual")) { //ada kata penjual gak
	            System.out.println("Username cannot contain 'Penjual'.");
	            correctUser = false; continue; 
	        }
	        try {
				Scanner read = new Scanner(fp);
				while(read.hasNextLine()) {
					temp = read.nextLine();
					splitting = temp.split(",");
					nameFromFile = splitting[1];
					if(nameFromFile.equals(name)) {
		                correctUser = false; break; 
		            }
				}
			} catch (FileNotFoundException e) {
				System.out.println("Couldn't be read");
				e.printStackTrace();
			}
	    } while (!correctUser);
		
		do {
	        System.out.print("Input Password [5 - 10 length | Alphanumeric]: ");
	        pass = scan.nextLine();

	        if (pass.length() < 5 || pass.length() > 10) {
	            System.out.println("Password must be between 5 and 10 characters long.");
	            correctPass = false; continue; 
	        }
	        boolean hasLetter = false, hasNumber = false, isAlnum = false;
	        for (int i = 0; i < pass.length(); i++) {
	            char c = pass.charAt(i);
	            if (!(Character.isLetterOrDigit(c))) isAlnum = true;
	            else if (Character.isLetter(c)) hasLetter = true;
	            else if (Character.isDigit(c)) hasNumber = true;
	        }
	        
	        if(isAlnum && hasLetter && hasNumber) {
	        	correctPass = false;
	        }
	    } while (!correctPass);
		
		return;
	}

	public static void login() {
	    String user, pass;
	    System.out.println("                               _     <-. (`-')_\r\n"
	            + "   <-.        .->       .->    (_)       \\( OO) )\r\n"
	            + " ,--. )  (`-')----.  ,---(`-') ,-(`-'),--./ ,--/\r\n"
	            + " |  (`-')( OO).-.  ''  .-(OO ) | ( OO)|   \\ |  |\r\n"
	            + " |  |OO )( _) | |  ||  | .-, \\ |  |  )|  . '|  |)\r\n"
	            + "(|  '__ | \\|  |)|  ||  | '.(_/(|  |_/ |  |\\    |\r\n"
	            + " |     |'  '  '-'  '|  '-'  |  |  |'->|  | \\   |\r\n"
	            + " `-----'    `-----'  `-----'   `--'   `--'  `--'");
	    do {
	        System.out.print("Input Username: ");
	        user = scan.nextLine();

	        System.out.print("Input Password: ");
	        pass = scan.nextLine();
	    } while(correctLoginCredentials(user, pass) == null);
	    User loggedIn = correctLoginCredentials(user, pass);
	    Buyer buyer = new Buyer(loggedIn);
	    if(loggedIn.isBuyer) Buyer.buyerMenu(loggedIn);
	    
	    if(user.equals("Seller1")) GlazeDonutStore.store(loggedIn);
	    if(user.equals("Seller2")) FilledDonutStore.store(loggedIn);
	    if(user.equals("Seller3")) ChocolateDonutStore.store(loggedIn);
	}

	public static User correctLoginCredentials(String inputName, String inputPass) {
	    File fp = new File("Credentials.txt");
	    String temp;
	    String[] splitting;
	    String id, name, pass, type;
	    int money;
	    try {
	        Scanner read = new Scanner(fp);
	        while(read.hasNextLine()) {
	            temp = read.nextLine();
	            splitting = temp.split(",");
	            id = splitting[0];
	            name = splitting[1];
	            pass = splitting[2];
	            type = splitting[3];
	            money = Integer.parseInt(splitting[4]);

	            if(inputName.equals(name) && inputPass.equals(pass)) {
	            	return new User(id, name, pass, (type.equals("Buyer")? true : false), money);
	            }
	        }
	    } catch (FileNotFoundException e) {
	        System.out.println("Read file failed");
	        e.printStackTrace();
	    }

	    return null;
	}

}
