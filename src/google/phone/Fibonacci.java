package google.phone;

import java.util.Arrays;

public class Fibonacci {

	public static int[] getFibonacci(int N) {
		int[] fib = new int[N];
		fib[0] = 0;
		fib[1] = 1;
	
		for(int i = 2; i < N; i++) {
			fib[i] = fib[i-1] + fib[i-2];
		}
		return fib;
	}
	public static int getMin(int target) {
		int[] fibs = getFibonacci(20);
		//min[i] : minimum number of fibonacci numbers sum to i
		int[] min = new int[target+1];
		Arrays.fill(min,target);
		min[0] = 0;
		
		for(int i = 1; i <= target; i++) {
			//min[i] = target;
			for(int fib : fibs) {
				int count = 0;
				if(fib <= i) {
					count = min[i-fib]+1;
					min[i] = Math.min(min[i], count);
				} else break;
			}
		}
		return min[target];
	}
	
	public static void main(String[] args) {
		int[] fibs = getFibonacci(20);
		for(int fib : fibs) {
			System.out.print(fib + " ");
		}
		System.out.println();
		System.out.println(getMin(20));
	}
}
