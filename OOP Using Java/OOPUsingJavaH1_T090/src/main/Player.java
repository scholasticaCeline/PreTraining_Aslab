package main;
import java.util.Scanner;
import java.util.Vector;

public class Player {
	private String name;
	private Vector<Integer> cards;
	
	public Player(String name) {
		this.name = name;
		this.cards = new Vector<>();
		for(int i = 0; i < 10; i++) {
			cards.add(0);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public Vector<Integer> getCards() {
	    return cards;
	}
	
	public static String inputName(int i) {
		String name = "0";
		Scanner scan = new Scanner(System.in);
		do {
			System.out.print("Input player " + i + " name [5-10 character]: ");
			name = scan.nextLine();
			
			if(name.length() > 10 || name.length() < 5)
				System.out.println("Name length must be between 5 - 10 characters");
			
		} while(name.length() > 10 || name.length() < 5);
//		scan.close();
		return name;
	}
	
	public void addCard(int card) {
		cards.add(card);
	}
	
	public static int calculateTotalPower(Vector<Integer> cards) {
		int total = 0;
		for(int card: cards) {
			total += card;
		}
		
		return total;
	}
}
