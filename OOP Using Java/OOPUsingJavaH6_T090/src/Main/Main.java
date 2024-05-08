package Main;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		do {		
			System.out.println("\n\n\n\n\n\n\n\n\n");
			System.out.println(" ____  ___.__             .___                   ____  __.\r\n"
							+ "  \\   \\/  /|__|  ____    __| _/ ____ _______     |    |/ _| ____   ____  ______    ____ _______\r\n"
							+ "   \\     / |  | /    \\  / __ |_/ __ \\\\_  __ \\    |      < _/ __ \\_/ __ \\ \\____ \\ _/ __ \\\\_  __ \\\r\n"
							+ "   /     \\ |  ||   |  \\/ /_/ |\\  ___/ |  | \\/    |    |  \\\\  ___/\\  ___/ |  |_> >\\  ___/ |  | \\/\r\n"
							+ "  /___/\\  \\|__||___|  /\\____ | \\___  >|__|       |____|__ \\\\___  >\\___  >|   __/  \\___  >|__|\r\n"
							+ "        \\_/         \\/      \\/     \\/                    \\/    \\/     \\/ |__|         \\/\r\n"
							+ "\r\n"
							+ "  1. Play\r\n"
							+ "  2. How To Play\r\n"
							+ "  3. Exit");
			System.out.print(">> ");
			try {
				choice = scan.nextInt(); scan.nextLine();
			} catch (Exception e) {
				System.out.println("Invalid input");
				System.out.println("Press enter to continue... "); scan.nextLine();
				e.printStackTrace();
			}
			
			switch(choice) {
			case 1:
				User.gamePage();
				break;
			case 2:
				howToPlay();
				break;
			case 3:
				Logo.logo();
				System.exit(0);
			default:
				System.out.println("Invalid input");
			}
				
		} while(choice != 3);

	}

	public static void howToPlay() {
		System.out.println("\n\n\n\n\n\n\n\n\n");
		Scanner scan = new Scanner(System.in);
		System.out.println("               _________________________________________________________________________\r\n"
				+ "                    _     _                 ______             ____\r\n"
				+ "                   /    /                    /                /    )   /\r\n"
				+ "               ---/___ /-----__-------------/-------__-------/____/---/----__-----------\r\n"
				+ "                 /    /    /   )| /| /     /      /   )     /        /   /   ) /   /\r\n"
				+ "               _/____/____(___/_|/_|/_____/______(___/_____/________/___(___(_(___/_____\r\n"
				+ "                                                                                 /\r\n"
				+ "                                                                             (_ /\r\n"
				+ "\r\n"
				+ " The main objective of the game is basically to open all the chests in the game map. Every chest give\r\n"
				+ " various rewards such as item, shield, and potion. The player can get some points from that. But, the chest\r\n"
				+ " may contains enemy that can decrease points and the chest can be empty which not give any point when opened.\r\n"
				+ "\r\n"
				+ " Control :                           Symbols :\r\n"
				+ " W -> Move Up                        # -> Wall\r\n"
				+ " A -> Move Left                      C -> Chest\r\n"
				+ " S -> Move Down                      P -> Player\r\n"
				+ " D -> Move Right\r\n"
				+ "\r\n"
				+ " When the player starts the game, player get specific amount of moves and when the moves runs out, game\r\n"
				+ " ends. Player's moves can be increased if player get an item/shield/potion from chest.\r\n"
				+ "\r\n"
				+ " If all chests are opened, player will level up and another map generated along with the chests.\r\n"
				+ "\r\n"
				+ " Press enter to go back");
		scan.nextLine();
	}

}
