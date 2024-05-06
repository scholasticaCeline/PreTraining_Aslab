package main;

import java.util.Scanner;

import store.ChocolateDonutStore;
import store.FilledDonutStore;
import store.GlazeDonutStore;

public class Buyer {
	private User user;
	
	public Buyer(User user) {
		this.user = user;
	}

	public static void buyerMenu(User user) {
		int x = 9, y = 6;
		Scanner scan = new Scanner(System.in);
		char input = 0;
		
		while(true) {
			Map.printMap(y, x);
			System.out.print("Press W | A | S | D To Move: ");
			input = scan.next().charAt(0); scan.nextLine();
			
			switch(input) {
            case 'w':
                if (y > 0) y--;
                break;
            case 'a':
                if (x > 0) x--;
                break;
            case 's':
                if (y < 9) y++;
                break;
            case 'd':
                if (x < 19) x++;
                break;
            default:
                continue;
        }
			
			if(y == 3 && x == 4) GlazeDonutStore.insideStore(user);
			else if(y == 3 && x == 14) FilledDonutStore.insideStore(user);
			else if(y == 1 && x == 9) ChocolateDonutStore.insideStore(user);
			else if(y == 8 && x == 9) User.homePage(user); 
		}		
	}

}
