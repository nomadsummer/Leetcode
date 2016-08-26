package google.phone;

public class ItemOccurrenceLargerThanN4 {
	
	public static int findFirstPopularItem(int[] nums) {
		int N = nums.length;
		
		for(int i = 0; i < N; i += N/4) {
			
			int firstIndex = findFirstPosition(nums, 0, i-1, nums[i]);
			int lastIndex = findLastPosition(nums, i+1, N-1, nums[i]);
			int diff = firstIndex-lastIndex;
			if(diff > N/4) {
				return nums[i];
			}
		}
		return -1;
		
	}
	
	private static int findFirstPosition(int[] nums, int start, int end, int target) {
		
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if(nums[mid] == target && nums[mid-1] != target)
				return mid;
			if(nums[mid] > target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			} 
		}
		if(nums[start] == target) return start;
		if(nums[end] == target) return end;
		return -1;
	}
	
	private static int findLastPosition(int[] nums, int start, int end, int target) {
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if(nums[mid] == target && nums[mid+1] != target) {
				return mid;
			} else if(nums[mid] < target) {
				start = mid + 1;
			} else end = mid - 1;
		}
		if(nums[end] == target) return end;
		if(nums[start] == target) return start;
		return -1;
	}
	
	public static void main(String[] args) {
		int[] nums = {1,2,3,3,3,4,5,9};
		int[] nums2 = {1,1,1,2,5,5,5,5,5,8};
		System.out.println(findFirstPopularItem(nums));
		System.out.println(findFirstPopularItem(nums2));
		
	}
}
