package google.phone;

import java.util.Random;

/*
 * It can be solved in O(n) time. The solution also suits well for input in the form of stream. The idea is similar to this post. Following are the steps.

1) Create an array reservoir[0..k-1] and copy first k items of stream[] to it.
2) Now one by one consider all items from (k+1)th item to nth item.
a) Generate a random number from 0 to i where i is index of current item in stream[]. Let the generated random number is j.
b) If j is in range 0 to k-1, replace reservoir[j] with arr[i]
 */
public class ReservoirSampling {
	public static int[] selectKItem(int[] stream, int n, int k) {
		int[] reservoir = new int[k];
		int i;
		for(i = 0; i < k; i++) {
			reservoir[i] = stream[i];
		}
		Random random = new Random();
		
		//Iterate from the (k+1)th element to nth element
		for(; i < n; i++) {
			int nextInt = random.nextInt(i);
			//If the randomly  picked index is smaller than k, then replace
	        // the element present at the index with new element from stream
			if(nextInt < k) {
				reservoir[nextInt]  = stream[i];
			}
		}
		return reservoir;
	}
	public static void main(String[] args) {
		int[] stream = {1,3,5,7,8};
		int[] reservoir = selectKItem(stream, 5,2);
		for(int i : reservoir){
			System.out.print(i + " ");
		}
	}
	
}
