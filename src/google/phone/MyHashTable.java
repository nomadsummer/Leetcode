package google.phone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
 * Design a data structure that supports following operations in Θ(1) time.

insert(x): Inserts an item x to the data structure if not already present.

remove(x): Removes an item x from the data structure if present.

search(x): Searches an item x in the data structure.

getRandom(): Returns a random element from current set of elements
 */
public class MyHashTable {
	
	Map<Integer, Integer> map;
	List<Integer> list;
	MyHashTable() {
		map = new HashMap<Integer, Integer>();
		list = new ArrayList<Integer>();
	}
	
	public void insert(int val) {
		if(map.containsKey(val)) return;
		
		list.add(val);
		map.put(val, list.size());
	}
	
	//return the index of element val
	public int search(int val) {
		if(map.containsKey(val))
			return map.get(val);
		return -1;
	}
	
	public void remove(int val) {
		 // If present, then remove element from hash
		if(map.containsKey(val)) {
			map.remove(val);
			
			int index = map.get(val);
			//Swap element with last element so that remove from
		    // arr[] can be done in O(1) time
			Collections.swap(list, index, list.size()-1);
			
			int last = list.get(list.size()-1);
			list.remove(list.size()-1);
			map.put(last, index);
		}
	}
	public int getRandom() {
		Random random = new Random();
		int next = random.nextInt(list.size());
		return list.get(next);
	}


}
