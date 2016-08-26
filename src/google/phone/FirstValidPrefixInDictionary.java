package google.phone;

/*
 *  Give you a sorted dictionary and a prefix. 
 *  Find the first valid prefix string for this dictionary.
 */
public class FirstValidPrefixInDictionary {

	public static String findValid(String[] dictionary, String prefix) {
		
		return find(dictionary, prefix, 0, dictionary.length-1);
	}
	
	private static String find(String[] dictionary, String prefix, int left, int right) {
		if(left > right) return "";
		
		while(left + 1 < right) {
			int mid = left + (right - left)/2;
			if(dictionary[mid].startsWith(prefix) && !dictionary[mid-1].startsWith(prefix)) return dictionary[mid];
			else if(dictionary[mid].compareTo(prefix) < 0) {
				return find(dictionary, prefix, mid+1, right);
			} else return find(dictionary, prefix, left, mid-1);
//			while(i < prefix.length()) {
//				if(prefix.charAt(i) > dictionary[mid].charAt(i)) {
//					return find(dictionary, prefix, mid+1, right);
//				} else if(prefix.charAt(i) == dictionary[mid].charAt(i)) {
//					i++;
//				} else {
//					return find(dictionary, prefix, left, mid-1);
//				}
//			}
		}
		if(dictionary[left].startsWith(prefix)) return dictionary[left];
		if(dictionary[right].startsWith(prefix)) return dictionary[right];
		return "";
	}
	public static void main(String[] args) {
		String[] dict = {"abc","cab","cat", "cataclysm", "catch"};
		String prefix = "catc";
		System.out.println(findValid(dict, prefix));
	}
}
