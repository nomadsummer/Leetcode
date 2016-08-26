package microsoft;

import java.util.HashMap;
import java.util.Map;

public class PrintString {

	/**
	 * 
	 * @param characters char array
	 * @return
	 */
	public static String update(char[] characters) {
		
		int len = characters.length;
		int originLen = len;
		for(int i = 0; i < characters.length; ++i) {
			if(characters[i] == 'a') {
				len += 2;
			} else if(characters[i] == 'b') {
				len += 1;
			}
		}
		char[] newChar = new char[len];
		
		int i = originLen-1, j = len-1 ;
		while(i >= 0 && j >= 0) {
			if(characters[i] == 'a') {
				newChar[j] = 'a';
				newChar[j-1] = 'a';
				newChar[j-2] = 'a';
				j = j-2;
			} else if(characters[i] == 'b') {
				newChar[j] = 'b';
				newChar[j-1] = 'b';
				j = j - 1;
			} else {
				newChar[j] = characters[i];
			}
			i--;
			j--;
		}
		return new String(newChar);
	}
	
	public static String compress(String str) {
		//ask if the str is sorted
		String res = "";
		int count = 1;
		for(int i = 1; i < str.length(); i++) {
			if(str.charAt(i) == str.charAt(i-1)) {
				count++;
			} else {
				res += str.charAt(i-1);
				res += "{" + count + "}";
				count = 1;
			}
		}
		res += str.charAt(str.length()-1);
		res += "{" + count + "}";
		return res;
	}
	
	public static void main(String[] args) {
		char[] c1 = new char[]{'a','b','c','a','b'};
		System.out.println(update(c1));
		
		String str1 = "aaabbhf";
		String str2 = "aabbaahff";
		String str3 = "abahfff";
		System.out.println(compress(str1));
		System.out.println(compress(str2));
		System.out.println(compress(str3));
	}
}
