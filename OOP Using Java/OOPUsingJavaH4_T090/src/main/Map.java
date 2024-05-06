package main;

public class Map {
	private User user;
	
	public Map(User user) {
		this.user = user;
	}
	
	public static void printMap(int y, int x) {
		System.out.println(); System.out.println(); System.out.println();
		int height = 10, width = 20;
		for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
            	if (i == 0 || i == height - 1 || j == 0 || j == width - 1) System.out.print("*"); 
            	else if (i == y && j == x) System.out.print("U"); 
            	else if (i == 8 && j == 9) System.out.print("H"); 
            	else if ((i == 3 && j == 4) || (i == 3 && j == 14) || (i == 1 && j == 9)) System.out.print("S");  
            	else System.out.print(" ");
            }
            System.out.println();
        }
	}

	

}
