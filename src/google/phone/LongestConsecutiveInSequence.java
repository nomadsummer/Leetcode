package google.phone;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LongestConsecutiveInSequence {
	//不一定是连续的
	public static List<Integer> longestConsecutive(int[] nums) {
		List<Integer> rst = new ArrayList<Integer>();
		if(nums == null || nums.length == 0) return rst;
		
		Set<Integer> set = new HashSet<Integer>();
		for(int num : nums) {
			if(num > 0) {
				set.add(num);
			}
		}
		int count = 1;
		while(!set.isEmpty()) {
			Iterator<Integer> iter = set.iterator();
			List<Integer> list = new ArrayList<Integer>();
			int curr = iter.next();
			list.add(curr);
			int i = curr - 1;
			while(set.contains(i)) {
				count++;
				list.add(i);
				set.remove(i);
				i--;
			}
			i = curr + 1;
			while(set.contains(i)) {
				count++;
				list.add(i);
				set.remove(i);
				i++;
			}
			if(list.size() > count) {
				rst = new ArrayList<Integer>(list);
				count = list.size();
			}
		}
		return rst;
	}
	public static void main(String[] args) {
		int[] a1 = {-1,2,3,-4,6,12,8,9,-3,-5};
		List<Integer> rst = longestConsecutive(a1);
		System.out.println("112");
		for(Integer i : rst) {
			System.out.println(i);
		}
	}
}
