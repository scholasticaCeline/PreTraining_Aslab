package chests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Item.Item;
import Main.User;

public class itemReward implements Chests {
	static List<Item> items = new ArrayList<Item>();
	private final enemyReward enemy = new enemyReward();

	public static void readFromFile() {
		File fp = new File("item.txt");
		String itemType = null;
		String temp;
		String[] splitting;
		try {
			Scanner read = new Scanner(fp);
			while(read.hasNextLine()) {
				temp = read.nextLine();
				splitting = temp.split("#");
				
				String name = splitting[0];
	            int value = Integer.parseInt(splitting[1]);
	            
	            if (name.startsWith("Shield")) itemType = "Shield";
	            else if (name.toLowerCase().endsWith("potion")) itemType = "Potion";
	            else itemType = "Normal";
	            
	            Item item = new Item(name, value, itemType);
	            items.add(item);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String youHaveFound() {
		String itemGot = null;
		Random rand = new Random();
		int gachaItem = rand.nextInt(items.size());
		
		for (Item i : items) {
            if (i.equals(items.get(gachaItem))) {
                itemGot = i.getName();
                break;
            }
        }
		
		return itemGot;
	}
	
	@Override
	public int changePoints(int points) {
		Random rand = new Random();
	    int movesAdded = rand.nextInt(3) + 2;
	    User.setMoveRemaining(movesAdded + User.getMoveRemaining());
	    int pointsAdded = 0;
	    
	    int gachaItemIndex = rand.nextInt(items.size());
	    Item foundItem = items.get(gachaItemIndex);
	    
	    int itemPoints = foundItem.getPoints();
	    
	    int totalPoints = points + itemPoints;
	    User.setPoint(totalPoints);
	    
	    String itemType = foundItem.getType();
	    if (itemType.equals("Shield")) {
	        User.setHasProtection(true);
	        System.out.println("You obtained a shield! Protection is now available.");
	    } else if (itemType.equals("Potion")) {
	        User.setHasMultiplier(false);
	        System.out.println("You obtained a potion! Multiplier is now available.");
	    }
	    
	    return itemPoints;
	}

	public int gachaChestDuluLol(int level, int points) {
		readFromFile();
		Scanner scan = new Scanner(System.in);
		int type = 0;
		Random random = new Random();
		int itemChance = 55 - level * 5;
		int monsterChance = 35 + level * 5;
		int zonkChance = 10;
		
		type = random.nextInt(100) + 1;
		if(type <= itemChance) {
			int randomIndex = random.nextInt(items.size());
	        Item foundItem = items.get(randomIndex);
			System.out.println("");
			System.out.println("You have found " + youHaveFound());
			if(User.isHasMultiplier()) {
				int point = changePoints(points)*2;
				System.out.println("Your points increased by " + point);
				User.setHasMultiplier(false);
			} else {
				System.out.println("Your points increased by " + changePoints(points));
			}
			System.out.println("Press enter to continue.."); scan.nextLine();
		} else if(type <= itemChance + monsterChance) {
			System.out.println("You have encountered an enemy!");
			if(User.isHasProtection()) {
				System.out.println("You have protection! Your points are still the same");
				User.setHasProtection(false);
			} else {
				System.out.println("Your points decreased by " + enemy.changePoints(points));
			}
			System.out.println("Press enter to continue.."); scan.nextLine();
		} else if(type <= itemChance + monsterChance + zonkChance) {
			System.out.println("This chest is empty! Better luck next time");
			System.out.println("Press enter to continue... "); scan.nextLine();
		} 
		
		else gachaChestDuluLol(level, points); //kalo dianya gak kena chance
		
		return points;
	}

}
