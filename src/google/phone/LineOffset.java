package google.phone;


/*
 * 给你一个以String形式存储的文件，每行有一些字符串，结尾是\n，然后给你一个offset，问是在第几行。
 * 比如：
 * abc\n
 * de\n
 * efg\n
 * h\n
 * offset = 0 的话是a，在第1行。offset = 3 为\n，还在第一行。offset = 8 是f，在第三行。非常简单的题目，用循环做就行，每次遇到一个\n就加1，注意不要count最后一个字母，因为可能是结尾的\n。
 */
public class LineOffset {

	public static int getLine(String s, int offset) {
		if (s == null || s.length() == 0) return 0;
		
		int idx = 0, line = 1;
		while(idx <= offset && idx < s.length()) {
			if(idx != offset && s.charAt(idx) == '\n') {
				line++;
			}
			idx++;
		}
		return line;
	}
	public static void main(String[] args) {
		String s = " abc\n"
				+ "de\n"
				+ "efg\n"
				+ "h\n";
		System.out.println(getLine(s, 0));
		System.out.println(getLine(s, 3));
		System.out.println(getLine(s, 8));
		System.out.println(getLine(s, 10));
		
	}
	
	/*
	 * follow up：接上题，让你设计一种数据结构，用比O(n)快的方法找到line number。
	 * 方法：建一个int[]，存每一行字符数的加和，然后binary search，跟之前leetcode insert position的题一模一样。
	 * 也跟地里之前出现过的“每次调用一个函数，按照数组里面的数字的大小，返回相应的Index”一样。
	 * 比如：
	 * abc\n
	 * de\n
	 * efg\n
	 * h\n
	 * 就建立一个array：[4， 7，11， 13]，然后按照给的offset搜索。. 
	 */
}
