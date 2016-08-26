package google.phone;

import java.util.HashMap;
import java.util.Map;

public class getLongestSubStringKDistinctCharacters {

	//There are now new requirements for getLongestSubstring! The string doesn't fit into memory. Instead you get an object of type BigString:

	interface BigString {
	        public boolean hasNextChar();
	        public char getNextChar();
	}
	private long startpos;
	private long endpos;
	
	getLongestSubStringKDistinctCharacters(long startpos, long endpos) {
         this.startpos = startpos; 
         this.endpos = endpos;
        }

	//Instead of returning a String, return the start index and end index of when the longest substring occurred.

	getLongestSubStringKDistinctCharacters getLongestSubstring(BigString s, int m){
	        int finalStart=-1, finalEnd=-1,maxLength=0;
	        int start=0,end=0;
	        Map<Character,Integer> map=new HashMap<Character,Integer>();
	        while(s.hasNextChar()){
	                char endC=s.getNextChar();
	                if(map.containsKey(endC)){
	                       // map.put(endC,end);
	                }else if(map.size()<m){
	                        map.put(endC,end);
	                }else if(map.size()==m){
	                        if(end-start>maxLength){
	                                maxLength=end-start;
	                                finalStart=start;
	                                finalEnd=end;
	                        }
	                        int startIndex=Integer.MAX_VALUE;
	                        Character removeC=null;
	                        for(char startC:map.keySet()){
	                                if(map.get(startC)<startIndex){
	                                        startIndex=map.get(startC);
	                                        removeC=startC;
	                                }
	                        }
	                        start=startIndex;
	                        map.remove(removeC);
	                }
	                end++;
	        }
	        if(finalStart==-1&&finalEnd==-1&&map.size()<m) return null;
	        return new getLongestSubStringKDistinctCharacters(finalStart,finalEnd);
			
	}

//	time O(N)
//	space O(M)
}
/*public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k < 1) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int l = 0;. 1point 3acres 璁哄潧
        int ret = 0;.1point3acres缃�
        BigStringIterator iter1 = new BigStringIterator(s), iter2 = new BigStringIterator(s);. visit 1point3acres.com for more.
        int i = 0;
        while (iter1.hasNextChar()) {
            char c = iter1.getNextChar();. visit 1point3acres.com for more.
            if (map.containsKey(c)) {
                int v = map.get(c);
                map.put(c, ++v);
            } else {. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
                map.put(c, 1);
            }. 1point 3acres 璁哄潧
            if (map.size() > k) {. 1point 3acres 璁哄潧
                while (iter2.hasNextChar()) {
                    c = iter2.getNextChar();. visit 1point3acres.com for more.
                    l++;
                    int v = map.get(c);
                    if (v > 1) {
                        map.put(c, --v);
                    } else {
                        map.remove(c);
                        break; 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
                    }
                }
            }
            ret = Math.max(ret, i-l+1);
            i++;
        }
        return ret;
    }
    class BigStringIterator implements BigString {
        String str;
        int i;
        BigStringIterator(String s) {
            str = s;
            i = -1;
        }
        public boolean hasNextChar() {
            return str.length() > 0 && i < str.length()-1;
        }
        public char getNextChar() {
            if (!hasNextChar()) return '\0';
            return str.charAt(++i);
        }
    }
    interface BigString {
        boolean hasNextChar();
        char getNextChar();
    }
}
 *
 */
