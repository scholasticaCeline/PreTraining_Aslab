package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
	static char[][] map;
	
	public static char[][] readFromFile(int level) {
		File fp = new File(level + ".txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(fp);
            int numRows = 0;
            int numCols = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                numRows++;
                numCols = Math.max(numCols, line.length());
            }

            scanner.close();
            scanner = new Scanner(fp);

            char[][] map = new char[numRows][numCols];

            int row = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                char[] chars = line.toCharArray();
                System.arraycopy(chars, 0, map[row], 0, chars.length);
                row++;
            }

            return map;
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static void printMap(char[][] map, int currX, int currY, int xBefore, int yBefore) {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		for (int y = 0; y < map.length; y++) {
	        for (int x = 0; x < map[y].length; x++) {
	            if (y == currY && x == currX) {
	                System.out.print("P");
		            } else if (y == yBefore && x == xBefore && map[y][x] == 'C') {
		                System.out.print(" "); // Clear the chest from the previous position
		            }
	            else {
	                System.out.print(map[y][x]);
	            }
	        }
	        System.out.println();
	    }
		
		System.out.println("Current level: " + User.getLevel());
		System.out.println("Points gained: " + User.getPoint());
		System.out.println("Moves remaining: " + User.getMoveRemaining());
		System.out.println("Chest gotten: " + User.getChestFound());

	}
}
