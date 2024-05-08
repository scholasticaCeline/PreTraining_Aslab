	package Main;
import java.util.Random;
import java.util.Scanner;

import Item.Item;
import chests.enemyReward;
import chests.itemReward;
	
public class User {
	private static int posX = 5, posY = 5;
	private static int moveRemaining = 25;
	private static int point = 0;
	private static int level = 1;
	private static int chestFound = 0;
	private static boolean hasProtection = false;
	private static boolean hasMultiplier = false;
	private static final itemReward reward = new itemReward();
	public static boolean isHasProtection() {
		return hasProtection;
	}
	public static void setHasProtection(boolean hasProtection) {
		User.hasProtection = hasProtection;
	}
	public static boolean isHasMultiplier() {
		return hasMultiplier;
	}
	public static void setHasMultiplier(boolean hasMultiplier) {
		User.hasMultiplier = hasMultiplier;
	}

	private static final enemyReward enemy = new enemyReward();
	private static final itemReward itemRewardInstance = new itemReward();
	
	public static int getPosX() {
		return posX;
	}
	public static void setPosX(int posX) {
		User.posX = posX;
	}
	public static int getPosY() {
		return posY;
	}
	public static void setPosY(int posY) {
		User.posY = posY;
	}
	public static int getMoveRemaining() {
		return moveRemaining;
	}
	public static void setMoveRemaining(int moveRemaining) {
		User.moveRemaining = moveRemaining;
	}
	public static int getPoint() {
		return point;
	}
	public static void setPoint(int point) {
		User.point = point;
	}
	public static int getLevel() {
		return level;
	}
	public static void setLevel(int level) {
		User.level = level;
	}
	public static int getChestFound() {
		return chestFound;
	}
	public static void setChestFound(int chestFound) {
		User.chestFound = chestFound;
	}
	
	public static void gamePage() {
		Scanner scan = new Scanner(System.in);
		try {
			loading();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		char input;
		
		int xBefore = posX;
        int yBefore = posY;
		
		char[][] map = Map.readFromFile(level);
		while(moveRemaining > 0) {
			Map.printMap(map, posX, posY, xBefore, yBefore);
			if(!hasProtection) System.out.println("Protection Not Available");
			else System.out.println("Protection Available");
			
			if(!hasMultiplier) System.out.println("Multiplier Not Available");
			else System.out.println("Multiplier Available");
			
			System.out.print("\nInput a key [w | a | s | d] (Then press enter) >> ");
			input = scan.next().charAt(0);
			
			switch(input) {
            case 'w':
                if(posY > 0 && map[posY - 1][posX] != '#') posY--;
                moveRemaining--;
                break;
            case 'a':
                if(posX > 0 && map[posY][posX - 1] != '#') posX--;
                moveRemaining--;
                break;
            case 's':
                if(posY < map.length - 1 && map[posY + 1][posX] != '#') posY++;
                moveRemaining--;
                break;
            case 'd':
                if(posX < map[0].length - 1 && map[posY][posX + 1] != '#') posX++;
                moveRemaining--;
                break;
            default:
                System.out.println("Invalid input!! Please input [w | a | s | d]");
                continue;
			}
			
			for(int y = 0; y < map.length; y++) {
				for(int x = 0; x < map[y].length; x++) {
					if(posX == x && posY == y) {
			            if(map[y][x] == '#') {
			                posX = xBefore;
			                posY = yBefore;
			                break;
			            } else if(map[y][x] == 'C') {
			            	gachaChestDuluLol(level, point);
			                map[y][x] = ' ';
			                chestFound++;
			                break;
			            }
			        }
				}
			}
			if(moveRemaining <= 0) losePage(point); 
			map = checkWin(map, point, level, chestFound);
		}
	}
	
	private static int gachaChestDuluLol(int level, int points) {
		return itemRewardInstance.gachaChestDuluLol(level, points);
		
		
	}
	
	private static char[][] checkWin(char[][] map, int points, int level, int chestFound) {
		Scanner scan = new Scanner(System.in);
		boolean chestTarget = false;
		if(chestFound == 10+(5*level)) {
			chestTarget = true;
		} 
		
		if(chestTarget) {
	        System.out.println("All chests have been cleared.");
	        System.out.println("You have gained " + points + " points.");
	        System.out.println("Press enter to continue..."); scan.nextLine();
	        level++;
	        posX = 5;
	        posY = 5;
	        chestFound = 0;
	        return Map.readFromFile(level);
	    }
		return map;
	}
	
	private static void losePage(int points) {
		Scanner scan = new Scanner(System.in);
		System.out.println("You Lose!!");
		System.out.println("You have gained " + points + "points.");
		System.out.println("Press enter to continue");
		scan.nextLine();
	}		
	
	private static void loading() throws InterruptedException {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("[               ]\r\n"
							+ "  Loading"
							+ "... 0%");
		Thread.sleep(1000);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("[===            ]\r\n"
							+ "  Loading... 20%");
		Thread.sleep(1000);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("[======         ]\r\n"
				+ "  Loading... 40%"	);
		Thread.sleep(1000);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("[=========      ]\r\n"
				+ "  Loading... 60%");
		Thread.sleep(1000);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("[============   ]\r\n"
				+ "  Loading... 80%");
		Thread.sleep(1000);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("[===============]\r\n"
				+ "  Loading... 100%");
		
		
	}
	
}
