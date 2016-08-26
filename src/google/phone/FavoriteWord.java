package google.phone;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FavoriteWord {
	/*
	 * 给一个 array of words，和favorite letters， 让重新排序array，使得按照favorite letters的priority 排列。 
	 * 没有包含 favorite letters 的 words 则继续按照原本字母表排序

举个栗子：array：['animal','duck','snake','zebra','horse','mouse'], favorite letter:'zh',  output--->['zebra','horse','animal','duck','mouse','snake']
array：['aab','baa','caa','aaa','aaaa'], favorite letter:'ab',  output--->['aaa','aaaa','aab','baa','caa'].
	 */
	public String[] rearrange(String[] words, String letter) {
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		for(int i =0; i < letter.length(); i++) {
			map.put(letter.charAt(i), i);
		}
		
		Arrays.sort(words, new Comparator<String>(){

			@Override
			public int compare(String s1, String s2) {
				//1.both contain favorite word
				int p = 0;
				while(p < s1.length() && p < s2.length()) {
					char c1 = s1.charAt(p);
					char c2 = s2.charAt(p);
					if(map.containsKey(c1) && !map.containsKey(c2)) {
						return 1;
					} else if(!map.containsKey(c1) && map.containsKey(c2)) {
						return -1;
					} else if(map.containsKey(c1) && map.containsKey(c2)){
						if(map.get(c1) < map.get(c2)) {
							return -1;
						} else if(map.get(c1) > map.get(c2)) {
							return 1;
						}
					}
					p++;
				}
				return s1.compareTo(s2);
			}
		});
		return words;
	}
}
