package google.phone;

import java.util.HashMap;
import java.util.Map;

/*
 *  Suppose we have a method "getLongestSubstring(String s, int m)" which finds the longest substring with exactly M distinct characters.
Examples:

"ABACAAAB" M=2 -> "ACAAA"

There are now new requirements for getLongestSubstring! The string doesn't fit into memory. Instead you get an object of type BigString:
interface
interface BigString {
        public boolean hasNextChar();
        public char getNextChar();
}
 */
public class LongestSubstringAtMostKDistinctCharacters {
	interface BigString {
		public boolean hasNextChar();
		public char getNextChar();
	}
	
	class SubString{
		long start;
		long end;
		SubString(long start, long end){
			this.start = start;
			this.end = end;
		}
	}
	public SubString getLongest(BigString s, int m) {
		int left = -1, right = -1, max = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		int curLeft = 0, curRight = 0;
		while(s.hasNextChar()) {
			char endC = s.getNextChar();
			if(map.containsKey(endC)|| map.size() < m){
				map.put(endC,curRight);
			}else if(map.size() == m) {
				if(curRight - curLeft > max) {
					max = curRight-curLeft;
					left = curLeft;
					right = curRight;
				}
				int startIndex = Integer.MAX_VALUE;
				Character toRemove = null;
				for(char startC : map.keySet()) {
					if(map.get(startC) < startIndex){
					startIndex = map.get(startC);
					toRemove = startC;
					}
				}
				curLeft = startIndex;
				map.remove(toRemove);
			}
			curRight++;
		}
		if(left == -1 && right== -1 && map.size() < m) return null;
		return new SubString(left, right);
	}
}
