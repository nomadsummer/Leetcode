package google.phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TwoDAndKDAdjacentNode {

	public static List<List<Integer>> twoDNeighbors(int[] nums) {//nums contains two elements
		List<List<Integer>> neighbors = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		dfs(neighbors, list, nums, 0);
		return neighbors;
		
	}
	private static void dfs(List<List<Integer>> rst, List<Integer> list, int[] nums, int index) {
		if(nums.length == index) {
			for(int i = 0; i < nums.length; i++) {
				if(list.get(i) != nums[i]) {
					rst.add(new ArrayList<Integer>(list));
					return;
				}
			}
			return;	
		}
		list.add(nums[index]);
		dfs(rst,list,nums,index+1);
		list.remove(list.size()-1);
		
		list.add(nums[index] +1);
		dfs(rst,list,nums,index+1);
		list.remove(list.size()-1);
		
		list.add(nums[index]-1);
		dfs(rst,list,nums,index+1);
		list.remove(list.size()-1);
	}
	
	public static List<int[]> NDNeighbors(int[] nums) {//nums contains two elements
	
		int k = nums.length;
		int[] directions = {-1, 1, 0};
		LinkedList<int[]> ans = new LinkedList<int[]>();
		
		ans.offer(Arrays.copyOf(nums, k));
		for(int i = 0; i < k; i++) {
			int n = ans.size();
			for(int j = 0; j < n; j++) {
				for(int d : directions) {
					int[] copy = Arrays.copyOf(ans.get(j), k);
					copy[i] += d;
					ans.offer(copy);
				}
			}
		}
		ans.pollFirst();
		return ans;
		
	}
}
