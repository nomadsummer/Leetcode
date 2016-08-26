package ixllearning;

import java.util.Random;

public class RandomPrime {

	public static void printRandomPrime() {
		
		Random random = new Random();
		int nextInt = random.nextInt(100) + 1; // shift the range from 0-99 to 1-100
		if(isPrime(nextInt)) {
			System.out.println(nextInt);
			return;
		}
		printRandomPrime();
	}

	private static boolean isPrime(int nextInt) {
		if(nextInt <= 1) return false;
		for(int i = 2; i*i <= nextInt; i++) {
			if(nextInt % i == 0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		printRandomPrime();
	}
}
