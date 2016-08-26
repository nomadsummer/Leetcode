package microsoft;

public class MyString {
	
	private char[] characters;
	
	public MyString(char[] characters) {
		this.characters = characters;
	}
	
	public MyString(String s) {
		this.characters = s.toCharArray();
	}
	
	public MyString insert(char c, int index) {
		int len = characters.length;
		char[] newChar = new char[len+1];
		int i = 0, j = 0;
		while(index > len) {
			index--;
		}
		while(i < len && j < len + 1) {
			if(i < index) {
				newChar[j++] = characters[i++];
			} else if(j == index) {
				newChar[j++] = c;
			} else {
				newChar[j++] = characters[i++];
			}
		}
		System.out.println(i);
		System.out.println(j);
		if(j < len + 1) {
			newChar[j] = c;
		}
		return new MyString(newChar);
	}
	
	public MyString copy() {
		return new MyString(characters);
	}
	
	public int length() {
		return characters.length;
	}
	
	public char charAt(int index) {
		return characters[index];
	}
	
	public MyString concat(MyString myString) {
		int len = characters.length;
		char[] newChar = new char[len+myString.length()];
		for(int i = 0; i < len; i++) {
			newChar[i] = characters[i];
		}
		for(int i = 0; i < myString.length(); i++) {
			newChar[i+len] = myString.charAt(i);
		}
		return new MyString(newChar);
	}
	
	public String toString() {
		return new String(characters);
	}
	
//	private char[] shifBy1(char[] chars, int index, int direction) {
//		//direction = 1 -> shift to right -> when insert
//		//direction = -1 -> shift to left -> when delete
//		char[] newChar = new char[chars.length];
//		if(direction == 1) {
//			
//		}
//	}
	
	public static void main(String[] args) {
		char[] chars1 = new char[]{'t','h','i','s'};
		char[] chars2 = new char[]{'e','a','s','y',',','h','e','l','l','o'};
		MyString s1 = new MyString(chars1);
		MyString s2 = new MyString(chars2);
		
		System.out.println("length of s1: "+ s1.length());
		System.out.println("s1 is copied: " +s1.copy());
		System.out.println("s1 after inserted a character at index: " + s1.insert('a', 0).toString());
		System.out.println("s1 after inserted a character at index: " + s1.insert('b', 6).toString());
		System.out.println("s1 after inserted a character at index: " + s1.insert('m', 3).toString());
		System.out.println("concatenate s1 and s2: " + s1.concat(s2).toString());	
	}
}
