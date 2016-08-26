package ixllearning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dearrangement {

	public static ArrayList<ArrayList<Integer>> dearrangement(int[] nums) {

		ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();

		boolean[] visited = new boolean[nums.length];
		Arrays.sort(nums);// so we can skip duplicates
		helper(rst, list, visited, 0, nums);
		return rst;

	}

	private static void helper(ArrayList<ArrayList<Integer>> rst, ArrayList<Integer> list, boolean[] visited, int idx,
			int[] nums) {
		if (list.size() == nums.length) {
			rst.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (visited[i] || i == idx || i > 0 && !visited[i - 1] && nums[i] == nums[i - 1])
				continue;

			list.add(nums[i]);
			visited[i] = true;
			helper(rst, list, visited, idx + 1, nums);
			visited[i] = false;
			list.remove(list.size() - 1);
		}
	}

	public static List<String> dearrangement(String str) {
		List<String> rst = new ArrayList<String>();
		char[] chars = str.toCharArray();
		Arrays.sort(chars);
		StringBuilder sb = new StringBuilder();
		boolean[] visited = new boolean[chars.length];
		dfs(chars, rst, sb, 0, visited);
		return rst;
	}

	private static void dfs(char[] chars, List<String> rst, StringBuilder sb, int idx, boolean[] visited) {
		if (sb.length() == chars.length) {
			rst.add(sb.toString());
			return;
		}
		for (int i = 0; i < chars.length; i++) {
			if (visited[i] || i == idx || i > 0 && !visited[i-1] && chars[i] == chars[i - 1])
				continue;

			sb.append(chars[i]);
			visited[i] = true;
			dfs(chars, rst, sb, idx + 1, visited);
			visited[i] = false;
			sb.deleteCharAt(sb.length()-1);
		}
	}

	public static void main(String[] args) {
		int[] nums = {1, 2, 3};
		ArrayList<ArrayList<Integer>> rst = dearrangement(nums);

		for (ArrayList<Integer> list : rst) {
			for (Integer i : list) {
				System.out.print(i);
			}
			System.out.println("");
		}
		
		String s1 = "abc";
		List<String> rst1 = dearrangement(s1);
		for(String s : rst1) {
			System.out.println(s);
		}
	}
	
	
}
