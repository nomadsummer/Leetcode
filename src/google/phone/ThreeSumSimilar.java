package phone;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
 * Give you an array of integers: A
Goal is to find three indexes (i,j,k) such that
   A + A[j] == A[k]

For Example:
   A = [ -5, 10, 1, 8, -2 ]

10 + -2 == 8
    Good answer: i=1, j=4, k=3. 
 */
public class ThreeSumSimilar {

	public static int[] threeSum(int[] nums){
		int[] res = new int[3];
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < nums.length; i++) {
			for(int j = i+1; j < nums.length-1; j++) {
				if(map.containsKey(nums[i] + nums[j])) {
					res[0] = i;
					res[1] = j;
					res[2] = map.get(nums[i] + nums[j]);
					return res;
				}else
				map.put(nums[j],j);
			}
		}
		return res;
	}
	class Index{
		int val;
		int idx;
		Index(int val, int idx){
			this.val = val;
			this.idx = idx;
		}
	}
	public int[] sum(int[] nums) {
		Index[] indexes = new Index[nums.length];
		for(int i = 0; i < nums.length;i++){
			Index index = new Index(nums[i],i);
			indexes[i]=index;
		}
		Arrays.sort(indexes, new Comparator<Index>(){
			public int compare(Index i1, Index i2){
			return i1.val-i2.val;
			}
		});
		int[] res = new int[3];
		for(int k = indexes.length-1;k > 1; k--){
			int i = 0, j = k-1;
			while(i < j) {
				if(indexes[i].val + indexes[j].val == indexes[k].val){
					res[0] = indexes[i].idx;
					res[1] = indexes[j].idx;
					res[2] = indexes[k].idx;
					break;
				}
				else if(indexes[i].val + indexes[j].val < indexes[k].val) i++;
				else j--;
			}
		}
		return res;
	}
	public static void main(String[] args) {
		int[] nums = {-5,10,1,8,-2};
		ThreeSumSimilar t = new ThreeSumSimilar();
		int[] res = t.sum(nums);
		for(int i : res) {
			System.out.println(i);
		}
	}
	
}
