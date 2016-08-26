package ixllearning;

import java.util.HashMap;
import java.util.Map;

public class ConvertLetter {

	public static String convert(String str) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('a', 1);
		map.put('b', 2);
		map.put('c', 3);
		
		String res = "";
		for(int i = 0 ; i < str.length(); i++) {
			if(Character.isDigit(str.charAt(i))) {
				res += str.charAt(i);
			} else if(Character.isAlphabetic(str.charAt(i))) {
				res += map.get(str.charAt(i));
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(convert("a1b2c3"));
		System.out.println(convert("1b2b3c4a"));
		
	}
	
	
}
