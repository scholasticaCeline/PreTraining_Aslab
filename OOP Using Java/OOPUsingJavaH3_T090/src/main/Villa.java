package main;

public class Villa {
	static int lastId = 0;
    int id;
    boolean type; 
    int totalBedroom;
    boolean available; // true = avail, false = not avail
    int price;

    public Villa(boolean type, int totalBedroom, boolean available, int price) {
        this.id = ++lastId;
        this.type = type;
        this.totalBedroom = totalBedroom;
        this.available = available;
        this.price = price;
    }
}
