package ixllearning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindDups {
	public static String[] findDups(String[] strs) {
		
		if(strs == null || strs.length == 0) return new String[]{""};
		
		List<String> list = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		for(String str : strs) {
			if(set.contains(str)) {
				list.add(str);
			}
			set.add(str);
		}
		return list.toArray(new String[list.size()]);
	}
	
	public static int removeDups(int[] nums) {
		//does not change the origin array, but simply return the size of removed duplicates array
		if(nums.length < 2) return nums.length;
		Arrays.sort(nums);
		int i = 1, idx = 0;
		while(i < nums.length) {
			if(nums[i] == nums[idx]) i++;
			else nums[++idx] = nums[i++];
		}
		return idx + 1;
	} 
	
	public static int[] removeDups2(int[] nums) {
		//return the new array without duplicates
		if(nums.length < 2) return nums;
		Arrays.sort(nums);
		int i = 1, idx = 0;
		while(i < nums.length) {
			if(nums[i] == nums[idx]) i++;
			else nums[++idx] = nums[i++];
		}
		return Arrays.copyOf(nums, idx+1);
	} 
	
	public boolean compareTwoLists(List<String> list1, List<String> list2) {
		if(list1 == null || list2 == null) return false;
		Set<String> set = new HashSet<String>();
		for(String s : list1) {
			s  = s.toLowerCase();
			set.add(s);
		}
		for(String s : list2) {
			s = s.toLowerCase();
			if(!set.contains(s)) return false;
			set.remove(s);
		}
		return set.size()  == 0;
		
	}
	
	public boolean compareTwoLists2(List<String> list1, List<String> list2) {
		if(list1 == null || list2 == null || list1.size() != list2.size()) return false;
		Map<String, Integer> map = (Map<String, Integer>) new HashMap<String, Integer>();
		
		for(String s1 : list1) {
			if(!map.containsKey(s1)) map.put(s1, 1);
			else map.put(s1, map.get(s1) + 1);
		}
		for(String s2 : list2) {
			if(!map.containsKey(s2)) return false;
			else map.put(s2, map.get(s2)-1);
		}
//		for(Integer v : map.values()) {
//			if(v != 0) return false;
//		}
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			if(entry.getValue() != 0) return false;
		}
		return true;
		
		
	}
	
	
	public static void main(String[] args) {
//		String[] strs = {"abc", "abc", "adv", "adv", "add"};
//		String[] dups = findDups(strs);
//		for(String dup : dups) {
//			System.out.println(dup);
//		}
		int[] nums = {1,4,6,8,4,5,1};
		System.out.println(removeDups(nums));
		
		
	}
}
