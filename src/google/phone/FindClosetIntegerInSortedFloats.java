package google.phone;

public class FindClosetIntegerInSortedFloats {
	static double minDiff = Double.MAX_VALUE;
	static int closet  = 0;
	public static int findCloset1(double[] nums, int target) {
		if(nums == null || nums.length == 0) return Integer.MAX_VALUE;
		
		double minDiff = Double.MAX_VALUE; 
		int closet = 0;
		for(int i = 0; i < nums.length; i++) {
			double diff = Math.abs(nums[i] - target);
			if(diff < minDiff) {
				minDiff = Math.abs(nums[i] - target);
				closet = i;
			}
		}
		return closet;
	}
	private static int binarySearch2(double[] nums, int start, int end, int target) {
		while(start < end) {
			int mid = start + (end - start)/2;
			if(Math.abs(nums[mid] - target) < minDiff) {
				minDiff = Math.abs(nums[mid] - target);
				closet = mid;
			} else if(nums[mid] < target) {
				start = mid+1;
			} else {
				end = mid - 1;
			}
		}
//		if(Math.abs(nums[start] - target) < minDiff) {
//			return start;
//		}
//		if(Math.abs(nums[end] - target) < minDiff) {
//			return end;
//		}
		return closet;
	}
	public static int findCloset2(double[] nums, int target) {
		closet = binarySearch2(nums, 0, nums.length-1, target);
		return Math.min((int) Math.ceil(nums[closet]), (int) Math.floor(nums[closet]));
	}
	public static void main(String[] args) {
		double[] d= {1.2,2.5,9.3,10.4,11.2};
		System.out.println(findCloset1(d, 5));
		System.out.println(findCloset2(d,10));
	}
}
