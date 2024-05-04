package main;

public class Transaction {
	String billID;
	int number;
	String reserveName;
	int totalGuest;
	int totalDay;
	int totalPrice;
	
	public Transaction(String billID, int number, String reserveName, int totalGuest, int totalDay, int totalPrice) {
		this.billID = billID;
		this.number = number;
		this.reserveName = reserveName;
		this.totalGuest = totalGuest;
		this.totalDay = totalDay;
		this.totalPrice = totalPrice;
	}
	

}
