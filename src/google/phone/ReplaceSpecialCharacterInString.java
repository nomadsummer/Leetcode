package google.phone;

import java.util.ArrayList;
import java.util.List;

public class ReplaceSpecialCharacterInString {

	public List<String> replace(String s) {
		List<String> rst = new ArrayList<String>();
		helper(rst, s.toCharArray(), 0);
		return rst;
	}
	
	public void helper(List<String> rst, char[] chars, int index) {
		if(index == chars.length) {
			rst.add(new String(chars));
			return;
		}
		if(chars[index] == '*') {
			chars[index] = '1';
			helper(rst, chars, index+1);
			
			chars[index] = '0';
			helper(rst, chars, index+1);
			
			chars[index] = '?';
		} else {
			helper(rst, chars, index+1);
		}
	}
}
/*
 * public ArrayList<String> findCombination(String input) {
                ArrayList<String> res = new ArrayList<String>();
                helper(res, "", input);
                return res;
        }
        
        private void helper(ArrayList<String> res, String cur, String input) {
                if(cur.length() == input.length()) {
                        res.add(new String(cur));
                        return;
                }
                
                for(int i = cur.length(); i < input.length(); i++) {. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
                        if(input.charAt(i) != '?') {-google 1point3acres
                                cur += input.charAt(i);
                                if(cur.length() == input.length()) {. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
                                        res.add(new String(cur));
                                }
                        } else {
                                helper(res, cur + "0", input);
                                helper(res, cur + "1", input);
                        }
                }
        }
*/
