package Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Item {
	String name;
	int points;
	String type;
	
	public Item(String name, int points, String itemType) {
		super();
		this.name = name;
		this.points = points;
		this.type = itemType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
