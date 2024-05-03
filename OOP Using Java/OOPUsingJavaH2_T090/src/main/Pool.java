package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Pool {
	public static char[][] map;
	
	File fpPool = new File("pool.txt");
	
	public Pool() {
		readFromFile();
	}
	
	private static void readFromFile() {
		File file = new File("pool.txt");
        try {
            Scanner scanner = new Scanner(file);
            int rows = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().length() > 0) {
                    rows++;
                }
            }
            scanner.close();
            map = new char[rows][];
            scanner = new Scanner(file);
            int currentRow = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().length() > 0) {
                    map[currentRow] = line.toCharArray();
                    currentRow++;
                }
            }
            scanner.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
	}
	
	public void printPool() {
		if (map == null) {
	        System.out.println("Pool map is not initialized.");
	        return;
	    }
	    
	    for (char[] row : map) {
	        for (char cell : row) {
	            System.out.print(cell + " ");
	        }
	        System.out.println();
	    }
	    System.out.println();
    }
	
	public boolean isEmpty() {
		File fp = new File("pool.txt");
		try {
	        Scanner reader = new Scanner(fp);
	        while (reader.hasNextLine()) {
	            String temp = reader.nextLine();
	            if (!temp.isEmpty() && !temp.equals(".") && !temp.equals("#")) {
	                return false; 
	            }
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		
		return true;
	}
	
	public boolean isFull() {
	    File fp = new File("pool.txt");
	    try {
	        Scanner reader = new Scanner(fp);
	        while (reader.hasNextLine()) {
	            String temp = reader.nextLine();
	            for (char c : temp.toCharArray()) {
	                if (c == ' ') {
	                    return false;
	                }
	            }
	        }
	        reader.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    
	    return true;
	}

	
	public void floodFill(int i, int j) {
	    recursion(i, j);
	    
	    File fpWrite = new File("pool.txt");
	    try {
	        FileWriter fw = new FileWriter(fpWrite);
	        for (int row = 0; row < map.length; row++) {
	            for (int col = 0; col < map[row].length; col++) {
	                fw.write(map[row][col]);
	            }
	            fw.write("\n"); 
	        }
	        fw.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void recursion(int i, int j) {
		if (i < 0 || i >= map.length || j < 0 || j >= map[0].length || map[i][j] != ' ') {
	        return;
	    }
		
		map[i][j] = '.';
		if(map[i][j-1] == ' '){
			recursion(i,j-1);
		}
		if(map[i-1][j] == ' '){
			recursion(i-1,j);
		}
		if(map[i][j+1] == ' '){
			recursion(i,j+1);
		}
		if(map[i+1][j] == ' '){
			recursion(i+1,j);
		}
		
	}

	public void reverseFloodFill(int i, int j) {
		reverseRecursion(i, j);
	    
	    File fpWrite = new File("pool.txt");
	    try {
	        FileWriter fw = new FileWriter(fpWrite);
	        for (int row = 0; row < map.length; row++) {
	            for (int col = 0; col < map[row].length; col++) {
	                fw.write(map[row][col]);
	            }
	            fw.write("\n"); 
	        }
	        fw.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
	
	public void reverseRecursion(int i, int j) {
		if (i < 0 || i >= map.length || j < 0 || j >= map[0].length || map[i][j] != ' ') {
	        return;
	    }
		map[i][j] = ' ';
		if(map[i][j-1] == '.'){
			recursion(i,j-1);
		}
		if(map[i-1][j] == '.'){
			recursion(i-1,j);
		}
		if(map[i][j+1] == '.'){
			recursion(i,j+1);
		}
		if(map[i+1][j] == '.'){
			recursion(i+1,j);
		}
		
	}


}
