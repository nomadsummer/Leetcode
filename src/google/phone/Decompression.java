package google.phone;

import java.util.Stack;

public class Decompression {
/*
 * input ： abbaba4x[a]bb3x[abaa2x[bab]]
output : abbabaaaaabbabaababbababaababbababaababbab
3[ab]2[abc]e 变成 ababababcabce
3[2[de]f] 变成  dedefdedefdedef
就用了stack就可以了把不是‘]’的push,遇见']'就开始pop, 并且保存直到'['然后再看要重复几次再扔进栈里这么多次
 */
	public static String decompress(String s) {
		if(s == null || s.length() == 0) return s;
		
		StringBuilder res = new StringBuilder();
		Stack<Integer> values = new Stack<Integer>();
		Stack<StringBuilder> strs = new Stack<StringBuilder>();
		
		int idx = 0;
		StringBuilder sb = new StringBuilder();
		while(idx < s.length()) {//will have four conditions
			char c = s.charAt(idx);
			if(c >= 'a' && c <= 'z' || c >= 'A' && c <='Z') {
				sb.append(c);
				idx++;
			} else if(c >= '0' && c <= '9') {//might have multiple values
				int val = 0;
				while(idx < s.length() && s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
					val = val * 10 + s.charAt(idx) - '0';
					idx++;
				}
				idx++;//skip multiply '*';
				values.push(val);
				continue;
			} else if(c == '[') {//push previous string
				strs.push(sb);
				sb = new StringBuilder();
				idx++;
			} else if(c == ']') {
				int times = 0;
				if(!values.empty())
					times = values.pop();
				String tmp = sb.toString();
				for(int i = 0; i < times-1; i++) {
					sb.append(tmp);
				}
				//only contains one element in strs
				if(!strs.empty()) {
					strs.peek().append(sb);
				} else {
					strs.push(sb);
				}
				idx++;
			}
		}
		while(!strs.isEmpty()) {
			res.append(strs.pop());
		}
		return res.toString();
	}
	public static void main(String[] args) {
		String str = "abbaba4x[a]bb3x[abaa2x[bab]]";
		System.out.println(decompress(str));
		System.out.println(decompress("3*[2*[de]f]"));
	}
}
