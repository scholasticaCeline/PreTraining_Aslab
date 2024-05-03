package main;

public class Equipment {
	private int speed;
	
	public int getSpeed(int pick) {
		if(pick == 1) speed = 1;
		else if(pick == 2) speed = 2;
		else if(pick == 3) speed = 3;
		else speed = 0;
		
		return speed;
	}
}
