package bank;

import java.util.Random;

public class creditAccount extends Bank {
	private String cardNumber;
	private String holderName;
	private String cvvNumber;
	private int limit;
	private int bill;
	
	public creditAccount(String holderName, int limit, int bill) {
		super();
		Random random = new Random();
		for(int i = 0; i < 16; i++) {
			this.cardNumber += random.nextInt(10);
		}
		this.holderName = holderName;
		for(int i = 0; i < 3; i++) {
			this.cvvNumber += random.nextInt(10);
		}
		this.limit = limit;
		this.bill = bill;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getCvvNumber() {
		return cvvNumber;
	}

	public void setCvvNumber(String cvvNumber) {
		this.cvvNumber = cvvNumber;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getBill() {
		return bill;
	}

	public void setBill(int bill) {
		this.bill = bill;
	}
	
	
}