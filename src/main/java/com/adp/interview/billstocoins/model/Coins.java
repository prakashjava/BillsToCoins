package com.adp.interview.billstocoins.model;

public class Coins {
	
	private String coinsType;
	private int number;
	
	public Coins(String coinsType, int number) {
		
		this.coinsType = coinsType;
		this.number = number;
	}
	
	public String getCoinsType() {
		return coinsType;
	}
	public void setCoinsType(String coinsType) {
		this.coinsType = coinsType;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	

}
