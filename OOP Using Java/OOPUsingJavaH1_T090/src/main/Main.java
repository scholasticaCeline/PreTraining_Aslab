package main;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {

	public static void printArena(Player player1, Player player2, Arena arena) {
		Vector<Integer> cards1 = player1.getCards();
		int total = Player.calculateTotalPower(cards1);
		
		String playerName1 = player1.getName();
		System.out.println(playerName1 + ", Power {" + total + "}");
		
		int flag = 0;
		for (int card : cards1) {
            if (card == 0) {
                flag = 1;
            }
        }
		
		if(flag == 1) {
			System.out.println("[No cards placed] ");
		} else {
			for(int card: cards1) {
				System.out.print("[" + card + "] ");
			}
			System.out.println();
		}
		
		int num = arena.getNum();
		System.out.println("+----------+");
		System.out.println("|  Arena "+ num + " |");
		System.out.println("+----------+");
		
		Vector<Integer> cards2 = player2.getCards();
		int total2 = Player.calculateTotalPower(cards1);
		
		String playerName2 = player2.getName();
		System.out.println(playerName2 + ", Power {" + total2 + "}");
		
		flag = 0;
		for (int card : cards2) {
            if (card == 0) {
                flag = 1;
            }
        }
		
		if(flag == 1) {
			System.out.println("[No cards placed] ");
		} else {
			for(int card: cards2) {
				System.out.print("[" + card + "] ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static int randomCard() {
		Random random = new Random();
		int card = random.nextInt(10) + 1;
		return card;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		
		do {
			System.out.println("Welcome to Rigjaw");
			System.out.println("======================\n");
			System.out.println("1. Play Game");
			System.out.println("2. Exit Game");
			System.out.print(">> ");
			choice = scan.nextInt();
			scan.nextLine();
			System.out.println();
			
			if(choice == 1) {
				//Setup
				String playerName1, playerName2;
				playerName1 = Player.inputName(1);
//				System.out.println(playerName1);
				playerName2 = Player.inputName(2);
//				System.out.println(playerName2);
				
				//new Players
				Player player1 = new Player(playerName1);
				Player player2 = new Player(playerName2);
				
				//new Arena
				Arena arena1 = new Arena(1);
				Arena arena2 = new Arena(2);
				Arena arena3 = new Arena(3);
				
				for(int rounds = 1; rounds <= 10; rounds++) {
					//print beginning
					System.out.println("Round " + rounds);
					System.out.println();
					printArena(player1, player2, arena1);
				    printArena(player1, player2, arena2);
				    printArena(player1, player2, arena3);
				    
				    //player 1 turn
				    int currCard = randomCard();
				    System.out.println(playerName1 + "'s turn to place card [" + currCard + "]");
				    int chooseArena = 0;
				    do {
				    	System.out.print("Choose arena (1, 2, 3): ");
				    	chooseArena = scan.nextInt();
				    	scan.nextLine();
				    } while(chooseArena < 1 || chooseArena > 3);
				    
				    //choose for player2
				    Arena chosenArena;
				    do {
					    switch (chooseArena) {
					        case 1:
					            chosenArena = arena1;
					            break;
					        case 2:
					            chosenArena = arena2;
					            break;
					        case 3:
					            chosenArena = arena3;
					            break;
					        default:
					            chosenArena = null;
					    }
					    
					    if(!chosenArena.isFull()) {
					    	System.out.println("Arena already full");
					    }
				    } while(!chosenArena.isFull() || chosenArena.exists(currCard));
				    
				    //masukin ke arena cards
				    chosenArena.placeCard(player1, currCard);
				    
				    //player 2 turn
				    printArena(player1, player2, arena1);
				    printArena(player1, player2, arena2);
				    printArena(player1, player2, arena3);  
				  
				    currCard = randomCard();
				    System.out.println(playerName2 + "'s turn to place card [" + currCard + "]");
				    chooseArena = 0;
				    do {
				    	System.out.print("Choose arena (1, 2, 3): ");
				    	chooseArena = scan.nextInt();
				    	scan.nextLine();
				    } while(chooseArena < 1 || chooseArena > 3);
				    
				    //choose for player2
				    do {
					    switch (chooseArena) {
					        case 1:
					            chosenArena = arena1;
					            break;
					        case 2:
					            chosenArena = arena2;
					            break;
					        case 3:
					            chosenArena = arena3;
					            break;
					        default:
					            chosenArena = null;
					    }
					    
					    if(!chosenArena.isFull()) {
					    	System.out.println("Arena already full");
					    }
				    } while(!chosenArena.isFull() || chosenArena.exists(currCard));
				    
				    //masukin ke arena cards
				    chosenArena.placeCard(player2, currCard);
				}
				
				//check who winss
				int count = 0;
				String winner;
				
			} else {
				System.out.println("Invalid");
			}
			
		} while(choice != 2);
		Logo.logo();
		
		scan.close();

	}

}
