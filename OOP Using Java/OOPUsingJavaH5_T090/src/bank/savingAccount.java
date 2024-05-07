package bank;

import java.util.Random;

public class savingAccount extends Bank {
	private String cardNumber;
	private String holderName;
	private String cvvNumber;
	private int balance;
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
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public savingAccount(String holderName, int balance) {
		super();
		Random random = new Random();
		for(int i = 0; i < 16; i++) {
			this.cardNumber += random.nextInt(10);
		}
		this.holderName = holderName;
		for(int i = 0; i < 3; i++) {
			this.cvvNumber += random.nextInt(10);
		}
		this.balance = balance;
	}
	
	
	
}
