package google.phone;

/*
 * filter out the given char. 如：给“abcdefg”，需要filter掉的是“a“，则给出的char *应该是“bcdefg”，
 * 要做到in place
 */
public class StringFilter {
	public static String filter(String word, Character c) {
		char[] chars = word.toCharArray();
		int i = 0, j = 0;
		while(i < chars.length) {
			if(chars[i] != c) {
				chars[j++] = chars[i++];
			} else if(chars[i] == c || (i > 0 && chars[i] == chars[i-1])) {
				i++;
			}
		}
		return new String(chars);
	}
	public static void main(String[] args) {
		String word = "abcabd";
		System.out.println(filter(word, 'a'));
	}
}
