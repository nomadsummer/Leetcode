package google.phone;

public class LongestConsequtiveIncreasingSubarray {
	//连续的
	public static int longestConsequtive(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		if(nums.length == 1) return 1;
		if(nums.length == 2) return nums[1]-nums[0] == 1 ? 2 : 1;
		
		int left = 0, idx = 0, right = 1;
		int maxLen = 0;
		while(right < nums.length) {
			while(nums[idx] + 1 == nums[right]) {
				idx++;
				right++;
			}
			maxLen = Math.max(maxLen, right - left);
			left = idx;
			idx++;
			right++;
		}
		return maxLen;
	}
	public static void main(String[] args) {
		int[] nums = {1,2,3,4,5,6,10,11,12,100,200};
		System.out.println(longestConsequtive(nums));
	}
}
