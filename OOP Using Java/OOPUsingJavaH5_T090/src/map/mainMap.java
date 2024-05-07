package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import interfaces.MovePlayer;
import main.User;

public class mainMap extends Map implements MovePlayer{
	static char[][] map;
	
	public static boolean movePlayer(int x, int y, char move, char[][]map) {		
		switch(move) {
        case 'w':
            if (y > 0 && map[y - 1][x] != '#') {
                y--;
                return true;
            }
            break;
        case 'a':
            if (x > 0 && map[y][x - 1] != '#') {
                x--;
                return true;
            }
            break;
        case 's':
            if (y < map.length - 1 && map[y + 1][x] != '#') {
                y++;
                return true;
            }
            break;
        case 'd':
            if (x < map[0].length - 1 && map[y][x + 1] != '#') {
                x++;
                return true;
            }
            break;
    }
    return false;
		
		
	}
	
	public static void moveInMap(int x, int y) {
		Scanner scan = new Scanner(System.in);
		char move;
		while(true) {		
			map = readFromFile();
			print(map, x, y);
			System.out.println("=====[COMMAND LIST]=====\r\n"
					+ " W to move player Up\r\n"
					+ " S to move player Down\r\n"
					+ " A to move player Left\r\n"
					+ " D to move player Right\r\n"
					+ " EXIT to exit the game");
			System.out.print(">> ");
			move = scan.next().charAt(0);
			boolean moved = mainMap.movePlayer(x, y, move, map);
            if (moved) {
                System.out.println("Player moved successfully!");
                switch (move) {
                    case 'w':
                        y--;
                        break;
                    case 'a':
                        x--;
                        break;
                    case 's':
                        y++;
                        break;
                    case 'd':
                        x++;
                        break;
                }
                User.setxPos(x); User.setyPos(y);
                
            } else  System.out.println("You hit the wall");
		}
	}
	
	public static void print(char[][] map, int currX, int currY) {
		for (int i = 0; i < map.length; i++) {
	        for (int j = 0; j < map[i].length; j++) {
	            if (i == currY && j == currX) {
	                System.out.print("P");
	            } else {
	                System.out.print(map[i][j]);
	            }
	        }
	        System.out.println();
	    }
	}
	
	public static char[][] readFromFile() {
		File fp = new File("main-map.txt");
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
	
	
}
