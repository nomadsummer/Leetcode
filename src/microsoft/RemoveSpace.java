package microsoft;

public class RemoveSpace {

	public static String removeSpace(String s){
		
		s= s.trim();
		String res = "";
		int count = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) != ' ') {//if current character is not white space
				res += s.charAt(i);
				//remeber to set count to be 0
				count = 0;
			} else {
				if(count < 1) {
					res += s.charAt(i);
					count++;
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(removeSpace(" This   is a   really good    question  "));
	}
}
