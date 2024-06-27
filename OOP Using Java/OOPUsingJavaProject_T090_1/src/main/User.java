package main;

import java.util.Scanner;

public class User {
	private static String email;
	private static String pass;
	private static int baseDmg;
	private static int baseMana;
	private static int baseHP ;
	private static int money;
	
	public void registerUser() {
		String email, password;
		Scanner scan = new Scanner(System.in);
		System.out.println("Input 'Exit' to quit from Login Menu"); System.out.println();
		boolean emailFlag = false;
		
		do {
			System.out.print("Input new email: ");
			email = scan.nextLine();
		} while(!emailFlag);
		
		do {
			System.out.print("Input new password: ");
			password = scan.nextLine();
			if(password.length() <  8|| password.length() > 30) {
				System.out.println("Password must be 8-30 characters");
			}
		} while(password.length() <  8|| password.length() > 30);
		
	}
}
