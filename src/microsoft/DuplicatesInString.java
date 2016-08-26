package microsoft;

public class DuplicatesInString {

	public static char containsMostDuplicates(String str) {
		//if only contains A-Z characters, if we can ignore uppercase and lowercase
		//do we need to count space;
		str = str.toLowerCase();
		char[] chars = str.toCharArray();
		//use an array to store number of occurrence of 26 characters
		int[] count = new int[26];
		//System.out.println('a' - '0');
		for(int i = 0; i < chars.length; i++) {
			//System.out.println(chars[i]-'0' - 49);
			count[chars[i] - '0' - 49]++;
		}
		int max = count[0];
		int maxPosition = 0;
		
		for(int i = 1; i < 26; i++) {
			if(count[i] > max) {
				maxPosition = i;
				max = count[i];
			}
		}
		System.out.println("the character with most frequency is:" + maxPosition);
	
		return '0';
	
	} 
	public static void main(String[] args) {
		String s = "cccompccany";
		
		System.out.println(containsMostDuplicates(s));
	}
}
