package ixllearning;

public class WrapText {

	public static String wrapText(String text, int maxCharsPerLine) {
		
		if(text == null || text.length() == 0 || maxCharsPerLine <= 0)
			return "";
		
		StringBuilder word = new StringBuilder();
		StringBuilder res = new StringBuilder();
		
		//get rid of leading or trailing spaces
		text = text.trim();
		char[] textArray = text.toCharArray();
		//keep track of characters in current line
		int offset = 0;
		
		for(char curr : textArray) {
			
			//encounter an empty space or new line, may need to start a new word or a new line here.
			if(curr == ' ' || curr == '\n') {
				//the length of current word being processed is longer than maxCharsPerLine
				//cannot be wrapped up, simply return null
				if(word.length() > maxCharsPerLine) {
					return null;
				} 
				
				//the word being processed cannot be fit in current line
				//need to add a new line, and reset the offset point to the start position of the new line
				if(offset > 0 && offset + word.length() + 1 > maxCharsPerLine) {
					res.append("\n");
					offset = 0;
				}
				
				//otherwise, fit current word on the same line, update the offset to keep track of
				//characters on this line. We are done with processing current word, reset word.
				if(offset > 0) {
					res.append(" ");
					offset++;
				}
				res.append(word.toString());
				offset += word.length();
				word = new StringBuilder();
				
				//preserve new lines in the String argument
				if(curr == '\n') {
					res.append("\n");
					offset = 0;
				}
			} else {
				word.append(curr);
			}
		}
		
		//process the last word in the String argument
		if(word.length() > maxCharsPerLine) {
			return null;
		} else if(word.length() > 0) {
			//if last word is at the beginning of a new line, add it directly
			//else need to add an extra space before the last word.
			if(offset == 0) {
				res.append(word.toString());
			} else {
				res.append(" " + word.toString());
			}
			
		}
		return res.toString();
	}
	
	public static void main(String[] args) {
		
		String text1 = "IXL Learning is a small but very successful company "
				+ "with a vast array of new features and products on the horizo"
				+ "If you are eager to work on something you believe in and want to make an "
				+ "impact we want to meet you";
		
		System.out.println(wrapText(text1, 20));
		
		String text2 = "ab\n\n";
		System.out.println(wrapText(text2, 4));
		
		String text3 = "   abc   abcc";
		System.out.println(wrapText(text3, 4));
		
		String text4 = "Millions of users count on us to make learning as "
				+ "effective as can be \n so we are looking for exceptional "
				+ "people who are committed \n to solving the real world challenges "
				+ "faced by students and teachers around the world  ";
		
		System.out.println(wrapText(text4, 22));
		
		
		
		
	}
}
