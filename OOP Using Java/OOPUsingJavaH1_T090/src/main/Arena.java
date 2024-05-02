package main;
import java.util.Vector;

public class Arena {
	private int num;
	private int capacity;
	private Vector<Player> cards;
	
	public Arena(int num) {
		this.num = num;
		if(num == 1) {
			this.capacity = 4;
		} else if(num == 2 || num == 3) {
			this.capacity = 3;
		}
		this.cards = new Vector<>();
	}
	
	public int getNum() {
		return num;
	}
	public int getCapacity() {
		return capacity;
	}
	
	public void placeCard(Player player, int card) {
		player.addCard(card);
	}
	
	public boolean isFull() {
		if(cards.size() >= capacity) {
			return true;
		}
		return false;
	}
	
	public boolean exists(int checkCard) {
		for(Player player: cards) {
			for(int card: player.getCards()) {
				if(card == checkCard) {
					return true;
				}
			}
		}
		return false;
	}
	
	public String mostPointInArena(Player player1, Player player2, Player player3) {
		String winner = null;
		int maxForNow = 0;
		
		int totalPoints = player1.calculateTotalPower(player1.getCards());
        if (totalPoints > maxForNow) {
            maxForNow = totalPoints;
            winner = player1.getName();
        }
		
        totalPoints = player2.calculateTotalPower(player2.getCards());
        if (totalPoints > maxForNow) {
            maxForNow = totalPoints;
            winner = player2.getName();
        }
        
        totalPoints = player3.calculateTotalPower(player3.getCards());
        if (totalPoints > maxForNow) {
            maxForNow = totalPoints;
            winner = player3.getName();
        }
        
		return winner;
	}
}
