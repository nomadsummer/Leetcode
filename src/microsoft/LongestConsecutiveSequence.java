package microsoft;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
	
	public static int consecutiveSeq(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		
		int max = 0;
		Set<Integer> set = new HashSet<Integer>();
		for(int num : nums) {
			set.add(num);
		}
		//remember to remove the element from the hash set you have founded
		for(int i = 0; i < nums.length; i++) {
			int curr = nums[i];
			//if the set is already empty, jump out of the loop
			if(set.size() == 0) break;
			int count = 0;
			//starting from current element, find all integers that are larger than current consecutive
			while(set.contains(curr)) {
				set.remove(curr);
				curr++;
				count++;
			}
			//starting from i-1, find all integers that are smaller than current element
			curr = nums[i]-1;
			while(set.contains(curr)) {
				set.remove(curr);
				curr--;
				count++;
			}
			max = Math.max(max, count);
		}
		return max;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[]{100,8,9,1,4,10,2,5,7,11};
		System.out.println(consecutiveSeq(nums));
	}
}
