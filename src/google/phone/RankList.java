package google.phone;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Given a list of numbers\n output a list of their relative ranks. For example: [10 3 8 9 4] => [1 5 3 2 4]
 */
public class RankList {
	public static int[] computeRank(int[] nums) {
		int[] ranks = new int[nums.length];
		
		Map<Integer, Integer> sortedMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> indicesMap = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < nums.length; i++) {
			indicesMap.put(nums[i], i);
		}
		//sort the array
		Arrays.sort(nums);
		for(int i = 0; i < nums.length; i++) {
			int rank = nums.length - i;
			sortedMap.put(nums[i], rank);
			if(i < nums.length - 1 && nums[i] == nums[i+1]) continue;
		}
		
		for(int i = 0; i < nums.length; i++) {
			ranks[indicesMap.get(nums[i])] = sortedMap.get(nums[i]);
		}
		return ranks;
	}
	
	public static void main(String[] args) {
		int[] nums = {10,3,8,9,4};
		int[] ranks = computeRank(nums);
		
		for(int rank : ranks) {
			System.out.print(rank + " ");
		}
	}
}
