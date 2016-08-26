package google.phone;

public class CompressString {
	
	public static String compress(String str) {
		if(str == null || str.length() < 2) return str;
		
		StringBuilder sb = new StringBuilder();
		
		char pre = str.charAt(0);
		int count = 1;
		for(int i = 1; i < str.length(); i++) {
			char curr = str.charAt(i);
			if(pre == curr) count++;
			else {
				if(count == 1) sb.append(pre);
				else sb.append(count).append(pre);
				count = 1;
			}
			pre = curr;
		}
		if(count == 1) sb.append(pre);
		else sb.append(count).append(pre);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String s = "aaabbbcccddd";
		System.out.println(compress(s));
		System.out.println(compress("abbbcddda"));
	}
}
