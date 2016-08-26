package microsoft;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountAndSay {

	public static List<Map<Integer, Integer>> countFrequency(List<Integer> groups) {
		List<Map<Integer, Integer>> res = new ArrayList<Map<Integer, Integer>>();
		Map<Integer, Integer> intMap = new HashMap<Integer, Integer>();		
		if(groups == null || groups.size() == 0) return res;		
		for(Integer i : groups) {
			intMap = new HashMap<Integer, Integer>();
			if(i == 0) {
				intMap.put(i, 1);
			} else {
				while(i > 0) {
					int remain = i%10;
					if(!intMap.containsKey(remain)) {
						intMap.put(remain, 1);
					} else {
						int count = intMap.get(remain);
						intMap.put(remain, count+1);
					}
					i /= 10;
				}
			}
			res.add(intMap);
		}
		return res;
	}
	public static int countGroups(int[] nums) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int count = 0;
		for(int num : nums) {
			
			int[] digits = processDigits(num);
			int hashCode = hash(digits);
			if(!map.containsKey(hashCode)) {
				map.put(hashCode, 1);
				count++;
			} else {
				map.put(hashCode, map.get(hashCode) +1);
			}
		}
		return count;
	} 
	private static int hash(int[] array) {
		int hashCode = 1;
		for(int a : array) {
			hashCode = hashCode * 31 + a;
		}
		return hashCode;
	}
	
	private static int[] processDigits(int n) {
		
		//store 0 - 9 digits
		int[] result = new int[10];
		//can n be negative number ?
		if(n == 0) {
			result[0] = 1;
			return result;
		}
		while(n > 0) {
			int digit = n%10;
			result[digit]++;
			n /= 10;
		}
		return result;
	}
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(15);
		list.add(51);
		list.add(151);
		
		int[] nums = new int[]{1,3,5,15,51,33,55,67,76};
		
		//List<Map<Integer, Integer>> res = countFrequency(list);
		int count = countGroups(nums);
		System.out.println(count);	
		//System.out.println(res.size());
		
	}
}
