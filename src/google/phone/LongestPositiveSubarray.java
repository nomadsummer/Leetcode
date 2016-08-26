package google.phone;

import java.util.ArrayList;
import java.util.List;

public class LongestPositiveSubarray {

	public static List<Integer> longestPositiveSequence(int[] nums) {
		List<Integer> rst = new ArrayList<Integer>();
		if(nums == null || nums.length == 0) return rst;
		
		int maxSeqStart = 0, maxSeqLen = 0;
		int currSeqStart = 0, currSeqLen = 0;
		
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] > 0) {
				//if the current element is positive, increate curr sequence length
				currSeqLen++;
			} else {//encounter the negative
				if(currSeqLen > maxSeqLen) {
					maxSeqLen = currSeqLen;
					maxSeqStart = currSeqStart;
				}
				currSeqStart = i+1;
				currSeqLen = 0;
			}
		}
		if(currSeqLen > maxSeqLen) {
			maxSeqLen = currSeqLen;
			maxSeqStart = currSeqStart;
		}
		for(int i = maxSeqStart; i < maxSeqStart+maxSeqLen; i++) {
			rst.add(nums[i]);
		}
		return rst;
	}
	public static void main(String[] args) {
		int[] nums = {-1,2,3,-4,6,12,8,9,-3,-5};
		List<Integer> rst = longestPositiveSequence(nums);
		for(Integer i : rst)
			System.out.println(i);
	}
}
