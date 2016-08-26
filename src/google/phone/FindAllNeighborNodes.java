package google.phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FindAllNeighborNodes {

	/*
	 * 2维坐标找与给定点相邻的点，fellow up k 维坐标找与给定点相邻的点。
比如二维下给定（0，0）这个点，那么相邻的点有（-1，-1），（-1， 0）， （-1， 1），（0， -1），（1， -1），（1，0），（1， 1），（0，1）这8个点
	 */
	
	List<List<Integer>> neiborhoods(int nums[]) {
	    List<List<Integer>> ret = new ArrayList<List<Integer>>();
	    dfs(ret, nums, new ArrayList<Integer>(), 0);
	    return ret;
	}
	int[] dx = {-1,0,1};
	void dfs(List<List<Integer>> ret, int nums[], List<Integer> item, int index) {
	    if (index == nums.length) {
	      //这里for循环，判断一下如果此时的item和所给数组nums完全一样，就不要把这个加进去
	      for(int i = 0; i < nums.length; i++) {
	        if (item.get(i) != nums[i]) {
	          ret.add(new ArrayList<Integer>(item));
	          return;
	        }
	      }
	      return;
	    }	      
	    
	   for(int i = 0; i < dx.length;i++) {
		   item.add(nums[index]+dx[i]);
		   dfs(ret, nums, item, index + 1);
		   item.remove(item.size() - 1);
	   }
//	   item.add(nums[index]);
//	   dfs(ret, nums, item, index + 1);
//	   item.remove(item.size() - 1);
//
//	   item.add(nums[index] + 1);
//	   dfs(ret, nums, item, index + 1);
//	   item.remove(item.size() - 1);
//
//	   item.add(nums[index] - 1);
//	   dfs(ret, nums, item, index + 1);
//	   item.remove(item.size() - 1);
	}
	
	public class Solution {
	    public List<int[]> solve(int[] p) {
	        int k = p.length;
	        int[] directions = {-1, 1};
	        LinkedList<int[]> ans = new LinkedList<>();
	        ans.offer(Arrays.copyOf(p, k));
	        for (int i = 0; i < k; ++i) {
	            int n = ans.size();
	            for (int j = 0; j < n; ++j) {
	                for (int d: directions) {
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
	public void main(String[] args) {
		
	}

}
