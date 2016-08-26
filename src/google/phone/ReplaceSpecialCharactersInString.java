package google.phone;

import java.util.ArrayList;
import java.util.List;

public class ReplaceSpecialCharactersInString {

	public static List<String> replace(String s) {
		List<String> res = new ArrayList<String>();
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '*') {
				res.add(s.substring(0, i) + "0" + s.substring(i+1));
				res.add(s.substring(0, i) + "1" + s.substring(i+1));
			}
		}
		return res;
	}
	public static List<String> replace2(String s) {
		List<String> res = new ArrayList<String>();
		dfs(res, s, 0,"");
		
		return res;
	}
	private static void dfs(List<String> list, String s, int idx, String str) {
		if(str.length() == s.length()){
			list.add(str);
			return;
		}
				

		for(int i = idx; i < s.length(); i++) {
			if(s.charAt(i) == '*') {
				dfs(list, s, idx+1, str + '0');
				dfs(list, s, idx+1, str + '1');
			} else {
				str += s.charAt(i);
				if(str.length() == s.length()) {
					list.add(str);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		String s = "100*11";
		List<String> list = replace2(s);
		for(String str : list) {
			System.out.println(str);
		}
	}
}
