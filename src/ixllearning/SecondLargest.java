package ixllearning;

public class SecondLargest {

	public static int secondLargest(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		
		int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
		
		for(int num : nums) {
			if(num > first) {
				second = first;
				first = num;
			} else if(num > second) {
				second = num;
			}
		}
		return second == Integer.MIN_VALUE ? Integer.MIN_VALUE : second;
	}
	
	public static void main(String[] args) {
		int[] nums = {1,3,4,6,2,5,7,5,10};
		System.out.println(secondLargest(nums));
	}
}
