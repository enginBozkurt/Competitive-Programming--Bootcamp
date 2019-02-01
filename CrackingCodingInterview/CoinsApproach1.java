package com.cracking;


public class CoinsApproach1 {
	
	/**
	Uses Dynamic programming to solve the Coin Change Problem.
	

	@param amount Total amount of money 
	@param coins Coins used to find different denominations
	*/
	
	public static void main(String[] args) {
		//System.out.println(change(13, new int[]{1,2,5}));
		System.out.println(change(5, new int[]{1,2}));
	}


	public static int change(int amount, int[] coins) {
		
		
		int[] combinations = new int[amount + 1];
		
		// How many ways are there to select coins that add up to zero?
        // Well, no matter what values of coins we have, there is exactly one way. You take no coins.
        // So we'll initialize our table with one.
		
		combinations[0] = 1;   //base case
		
		// Now, we build up the solutions, using previous solutions to find new ones.
        // In this way, we ensure that we don't duplicate work, which is the whole point of dynamic programming.
		 for (int i = 0; i < coins.length; i++) { 
			 for (int j = coins[i]; j <= amount; j++) { 
					 combinations[j] += combinations[j - coins[i]];
					 printAmount(combinations);	 
			 }
			 
			System.out.println(); 
		 }

		 return combinations[amount];

	}
	
	
	public static void printAmount(int[] arr) {
		for(int i = 0; i< arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
	}
	
}