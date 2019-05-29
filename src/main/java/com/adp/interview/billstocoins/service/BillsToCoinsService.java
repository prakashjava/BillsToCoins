package com.adp.interview.billstocoins.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.adp.interview.billstocoins.model.BillsToCoinsException;
import com.adp.interview.billstocoins.model.Coins;

@Component
public class BillsToCoinsService {
	
	private static int[] validAmt = new int[] { 1, 2, 5, 10, 20, 50, 100 };

	private static int noOfoneCent;
	private static int noOffiveCent;
	private static int noOftenCent;
	private static int noOftwentyFiveCent;
	private static int noOfseventyFiveCent;
	
    @Value("${noOfoneCent}")
    public void setNoOfoneCent(int noOfOne) {
    	noOfoneCent = noOfOne;
    }
    
    @Value("${noOffiveCent}")
    public void setNoOffiveCent(int noOffive) {
    	noOffiveCent = noOffive;
    }
    
    @Value("${noOftenCent}")
    public void setnoOftenCent(int noOften) {
    	noOftenCent = noOften;
    }
    
    @Value("${noOftwentyFiveCent}")
    public void noOftwentyFiveCent(int noOftwentyFive) {
    	noOftwentyFiveCent = noOftwentyFive;
    }
    
    @Value("${noOfseventyFiveCent}")
    public void noOfseventyFiveCent(int noOfseventyFive) {
    	noOfseventyFiveCent = noOfseventyFive;
    }

	public List<Coins> getCoinsList(int dollarAmount) throws BillsToCoinsException {
		
		System.out.println("noOfseventyFiveCent" + noOfseventyFiveCent + "noOftwentyFiveCent" + noOftwentyFiveCent + " noOftenCent: " + noOftenCent
				+ " noOfFiveCent: " + noOffiveCent + " noOfoneCent" + noOfoneCent);
		
		List<Coins> coinsList = new ArrayList<Coins>();

		if (coinsRemainingNotZero()) {

			//if (validInput(dollarAmount)) {

				dollarAmount = dollarAmount * 100;

				int num75 = 0;
				int num25 = 0;
				int num10 = 0;
				int num5 = 0;
				int num1 = 0;

				int difference = dollarAmount;
				
				num75 = (int) (difference / 75);

					if ( (difference!=0 &&difference%75!=0) || num75 > noOfseventyFiveCent) {
						
						if(noOfseventyFiveCent==0) num75 = 0;
						
						if(num75 > noOfseventyFiveCent) num75 = noOfseventyFiveCent;
						
						difference = difference - num75 * 75;
						noOfseventyFiveCent = 0;
						
						num25 = (int) (difference / 25);
						
						if ( (difference!=0 && difference%25!=0) || num25 > noOftwentyFiveCent) {
							
							if(noOftwentyFiveCent==0) num25 = 0;
							
							if(num25 > noOftwentyFiveCent) num25 = noOftwentyFiveCent;
		
							difference = difference - num25 * 25;
							noOftwentyFiveCent = 0;
		
							num10 = (int) (difference / 10);
		
							if ( (difference!=0 && difference%10!=0) || num10 > noOftenCent) {
								
								if(noOftenCent==0) num10 = 0;
		
								if(num10 > noOftenCent) num10 = noOftenCent;
								
								difference = difference - noOftenCent * 10;
								noOftenCent = 0;
		
								num5 = (int) (difference / 5);
		
								if ( (difference!=0 && difference%5!=0) || num5 > noOffiveCent) {
		
									if(noOffiveCent==0) num5 = 0;
										
									if(num5 > noOffiveCent)	num5 = noOffiveCent;
									
									difference = difference - noOffiveCent * 5;
									noOffiveCent = 0;
		
									num1 = (int) (difference / 1);
		
									if ((difference!=0 && difference%1!=0) || num1 > noOfoneCent) {
		
										if(noOfoneCent==0) num1 = 0; 
										
										if(num1 > noOfoneCent) num1 = noOfoneCent;
										
										difference = difference - noOfoneCent * 1;
										noOfoneCent = 0;
		
										if (difference > 0) {
											noOfseventyFiveCent = num75;
											noOftwentyFiveCent = num25;
											noOftenCent = num10;
											noOffiveCent = num5;
											noOfoneCent = num1;
											
											throw new BillsToCoinsException ("InSufficient Number of coins for:" + dollarAmount/100 + 
													" We can dispatch only " + 
													"num75: " + num75 + "num25: " + num25+ " num10: " + num10 + " num5: " + num5 + " num1: " + num1);
										}
		
									} else {
										noOfoneCent = noOfoneCent - num1;
									}
		
								} else {
									noOffiveCent = noOffiveCent - num5;
								}
		
							} else {
								noOftenCent = noOftenCent - num10;
							}
		
						} else {
							noOftwentyFiveCent = noOftwentyFiveCent - num25;
						} 
				} else {
					noOfseventyFiveCent = noOfseventyFiveCent - num75;
				}

				System.out.println("noOfseventyFiveCent" + noOfseventyFiveCent  + "noOftwentyFiveCent" + noOftwentyFiveCent + " noOftenCent: " + noOftenCent
						+ " noOffiveCent: " + noOffiveCent + " noOfoneCent" + noOfoneCent);
				
				coinsList.add(new Coins("75", num75));
				coinsList.add(new Coins("25", num25));
				coinsList.add(new Coins("10", num10));
				coinsList.add(new Coins("5", num5));
				coinsList.add(new Coins("1", num1));

				return coinsList;
			} /*else {
				throw new BillsToCoinsException("Invalid input amount received");
			}

		} */else {
			throw new BillsToCoinsException("Empty coins");
		}

	}

	public static boolean validInput(int dollarAmount) {

		return Arrays.stream(validAmt).anyMatch(i -> i == dollarAmount);
	}

	public static boolean coinsRemainingNotZero() {

		return noOfseventyFiveCent>0 || noOftwentyFiveCent > 0 || noOftenCent > 0 || noOffiveCent > 0 || noOfoneCent > 0;
	}

	/*
	 * int num10 = (int)(difference / 10); difference = difference % 10;
	 * System.out.println("num10 = " + num10 + " diff: "+difference);
	 * 
	 * int num5 = (int)(difference / 5); difference = difference % 5;
	 * System.out.println("num5 = " + num5 + " diff: "+difference);
	 * 
	 * int num1 = (int)(difference / 1); difference = difference % 1;
	 * System.out.println("num1 = " + num1 + " diff: "+difference); }
	 * 
	 * /*public static void intializeCents() {
	 * 
	 * for (int i=0; i<=99; i++) { oneCentArray[i] = i; fiveCentArray[i] = i;
	 * tenCentArray[i] = i; twenFiveCentArray[i] = i; } }
	 */

}
