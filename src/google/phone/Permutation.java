package google.phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {

	/*大概就是取一个array里的任意n个不同的值,得到一个随机的组合，不能取同一个index的数，但是可以取数值相同的不同index的数
	给一个array：[5,1,3,3],
	再给一个数字n：2，
	求这个array里的任意num个数：比如可以得到[5,1] or [5,3] or [1,3] or [3,3] ，但是不能得到[5,5]
	再比如[5,1,3,3], 1 ===> [5] or [1] or [3]
*/
	public static ArrayList<List<Integer>> permute(int[] nums, int n) {
		ArrayList<List<Integer>> rst = new ArrayList<List<Integer>>();
		if(nums == null || nums.length == 0) return rst;
		Arrays.sort(nums);
		List<Integer> list = new ArrayList<Integer>();
		boolean[] visited = new boolean[nums.length];
		helper(rst, list, nums, n, visited);
		return rst;	
	}
	
	public static void helper(ArrayList<List<Integer>> rst, List<Integer> list, int[] nums, int n, boolean[] visited) {
		if(list.size() == n) {
			rst.add(new ArrayList<Integer>(list));
			return;
		}
		
		for(int i = 0; i < nums.length; i++) {
			if(visited[i] || (i > 0 && !visited[i-1] && nums[i] == nums[i-1])) continue;
			
			visited[i] = true;
			list.add(nums[i]);
			helper(rst, list, nums, n, visited);
			visited[i] = false;
			list.remove(list.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		int[] nums = {5,1,3,3};
		ArrayList<List<Integer>> rst1 = permute(nums, 2);
		for(List<Integer> list : rst1) {
			for(Integer i : list) {
				System.out.print(i + " ");
			}
			System.out.println(" ");
		}
	}
}
