package google.phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumSmaller {

	public static List<List<Integer>> threeSumSmaller(int[] nums, int target) {
		List<List<Integer>> rst = new ArrayList<List<Integer>>();
		
		Arrays.sort(nums);
		for(int i = 0; i < nums.length-2; i++) {
			int l = i+1;
			int r = nums.length-1;
			while(l < r) {
				int sum = nums[i] + nums[l] + nums[r];
				if(sum < target) {
					for(int k = r; k >l; k--){
						List<Integer> list = new ArrayList<Integer>();
						list.add(nums[i]);
						list.add(nums[l]);
						list.add(nums[k]);
						rst.add(list);
					}
					l++;
				}else r--;
			}
		}
		return rst;
	}
	public static void main(String[] args) {
		int[] nums = {-2,0,1,3};
		List<List<Integer>> rst = threeSumSmaller(nums,2);
		for(List<Integer> list : rst) {
			for(Integer i : list) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
