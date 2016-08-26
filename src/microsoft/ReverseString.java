package microsoft;

public class ReverseString {

	public static String reverseWords(String s) {

		// reverse twice,
		// 1.reverse all characters by character 2. reverse each word
		s = s.trim();
		reverse(s);
		String[] strs = s.split("\\s+");
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (String str : strs) {
			reverse(str);
			if (i != strs.length - 1) {
				sb.append(str).append(" ");
			} else {
				sb.append(str);
			}
			i++;
		}
		return sb.length() == 0 ? "" : sb.toString();
	}

	private static void reverse(String str) {
		int i = 0, j = str.length() - 1;
		while (i < j) {
			swap(str, i, j);
			i++;
			j--;
		}
	}

	private static void swap(String s, int i, int j) {
		char c1 = s.charAt(i);
		char c2 = s.charAt(j);
		char t = c1;
		c1 = c2;
		c2 = t;
	}

	public static void main(String[] args) {
		System.out.println(reverseWords("The sky is blue"));
	}
}
