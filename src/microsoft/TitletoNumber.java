package microsoft;

public class TitletoNumber {
	public static int titleToNumber(String s) {
		if (s == null || s.length() == 0)
			return 0;
		
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			int curr = s.charAt(i) - 'A' + 1;
			System.out.println("current character is: " + curr);
			//convert the current character to it's ascii representation
			res = 26 * res + (s.charAt(i) - 'A' + 1);
		}
		return res;
	}
	
	public static void main(String[] args) {
		int n1 = titleToNumber("A");
		System.out.println(n1);
		int n2 = titleToNumber("AA");
		System.out.println(n2);
		int n3 = titleToNumber("AZ");
		System.out.println(n3);
	}
}
