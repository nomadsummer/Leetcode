package google.phone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 一个字典，里面很多单词，例如
google, leg, about, lemma, apple, time
找这样的pair <A, B>，有两个条件, (1) A单词的后两个字母和B单词的前两个字母一样 （2）A单词的第一个字母和B单词的最后一个字母一样，例如<google, leg>就是一个合格的pair，<apple, lemma>也是一个合格的pair， <about, time>不可以.
 */
public class LongestMatchPairString {
	
	//since the input is already sorted, use binary search
	static int max = 0;
	public static int longestPair1(String[] words) {
		int max = 0;
		for(int i = 0; i < words.length; i++) {
			if(words[i].length() < 2) continue;
			
			binarySearch(words, i, 0, words.length-1, words[i].substring(words[i].length()-2),words[i].charAt(0));
			//System.out.println(max + ": ");
		}
		return max;
	}
	private static void binarySearch(String[] words, int index, int lo, int hi, String prefix, char c) {
		if(lo > hi) return;
		//char c = words[index].charAt(0);
		while(lo <= hi) {
			int mid = lo + (hi-lo)/2;
			if(words[mid].length() < 2) {
				binarySearch(words, index, lo, mid-1, prefix,c);
				binarySearch(words, index, mid+1, hi, prefix,c);	
			} else if(words[mid].startsWith(prefix)) {
				if(words[mid].charAt(words[mid].length()-1) == c){
					max = Math.max(max, words[mid].length()+words[index].length());
					//System.out.println(max);
			}
				binarySearch(words, index, lo, mid-1, prefix,c);
				binarySearch(words, index, mid+1, hi, prefix,c);			
				return;
			} else if(words[mid].substring(0, 2).compareTo(prefix) < 0) {
				lo = mid+1;
			} else hi = mid-1;
		}
	}
	//use hashmap, one pass
	public static int longestPair2(String[] words) {
		Map<String, String> map = new HashMap<String, String>();
		int longest = 0;
		for(String word : words) {
			String _key = word.substring(word.length()-1) + word.substring(0, 2);
			if(map.containsKey(_key)) {
				longest = Math.max(longest, map.get(_key).length() + word.length());
			}
			String key = word.substring(0,1) + word.substring(word.length()-2);
			String value = map.get(key);
			if(value == null || word.length() > value.length()) {
				map.put(key, word);
			}
		}
		return longest;
		
	}
	public static int longestPair3(String[] words) {
		if(words == null || words.length == 0) return 0;
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for(int i = 0; i < words.length; i++) {
			map.put(words[i], new ArrayList<String>());
		}
		
		for(int i = 0; i < words.length-1; i++) {
			String word1 = words[i];
			int len1 = word1.length();
			List<String> list = map.get(word1);
			for(int j = 0; j < words.length; j++) {
				String word2 = words[j];
				int len2 = word2.length();
				if(word1.charAt(0) == word2.charAt(len2-1)
						&& word1.substring(len1-2).equals(word2.substring(0,2))) {
					list.add(word2);	
				}
			}
			map.put(word1, list);
		}
		int longest = 0;
		for(Map.Entry<String, List<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			for(String str : entry.getValue()) {
				longest = longest > key.length() + str.length() ? longest : key.length() + str.length();
			}
		}
		return longest;	
	}
	public static void main(String[] args) {
		String[] words = {"about", "apple", "google", "leg","lemma","time"};
		System.out.println(longestPair3(words));
		System.out.println(longestPair2(words));
		System.out.println(longestPair1(words));
	}
}
