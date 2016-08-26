package ixllearning;

import java.util.HashMap;
import java.util.Map;

public class PhoneNumber {

	public static HashMap<String, HashMap<String, Integer>> getPhone(HashMap<String, HashMap<String, Integer>> map) {
		
		//result
		HashMap<String, HashMap<String, Integer>> res = new HashMap<String, HashMap<String, Integer>>();
		
		for(Map.Entry<String, HashMap<String, Integer>> entry : map.entrySet()) {
			String firstName = entry.getKey();
			HashMap<String, Integer> temp = entry.getValue();
			for(Map.Entry<String, Integer> numEntry : temp.entrySet()) {
				String lastName = numEntry.getKey();
				Integer phone = numEntry.getValue();
				HashMap<String, Integer> hm = new HashMap<String, Integer>();
				hm.put(firstName, phone);
				res.put(lastName, hm);
			}
		}
		return res;
		
	}
}
