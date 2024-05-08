package chests;

import java.util.Random;

import Main.User;

public class enemyReward implements Chests {

	@Override
	public int changePoints(int points) {
		int currPoint = User.getPoint();
		int lostPoints = 0;
		Random rand = new Random();
		lostPoints = rand.nextInt(5) + 4;
		User.setPoint(currPoint - lostPoints);
		if(lostPoints > currPoint) lostPoints = currPoint;
		
		return lostPoints;
	}

}
