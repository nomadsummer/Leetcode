package google.phone;

public class Turtle {

	static int x;
	static int y;
	int direction;
	static int n;
	Turtle(int n) {
		Turtle.n = n;
		Turtle.x = 0;
		Turtle.y = 0;
		this.direction = 0;
	}
	public void forward(){
		if(direction == 0 && y < n-1) y++;
		else if(direction == 1 && x < n-1) x++;
		else if(direction == 2 && y > 0) y--;
		else if(direction == 3 && x > 0) x--;
	}
	public void turnRight(){
		direction = (direction + 1) % 4;
	}
	public int[] getCordinnates(){
		int[] cor = new int[2];
		cor[0] = x;
		cor[1] = y;
		return cor;
	}
	public void execute(String query) {
		
		for(int i = 0; i < query.length(); i++) {
			if(query.charAt(i) == 'F') forward();
			else if(query.charAt(i) == 'R') turnRight();
		}
	}
	//follow1：输入指令，如 FFRRF3R，F就是前进，R就是向右转，2R就是RRR。输出这个指令之后所处的位置。
	public void execute1(String query) {
		
		String decompress = decompress1(query);
		System.out.println(decompress);
		
		execute(decompress);	
	}
	private String decompress1(String s) {
		
		int i = 0, j;
		String str = "";
		while(i < s.length()) {
			char c= s.charAt(i);
			int count = 0;
			if(c <= '9' && c > '0') {
				count = c - '0';
				j = i + 1;
				str += s.charAt(j);
				while(count > 0) {
					str += s.charAt(j);
					count--;
				}
				i = j+1;
			} else {
				str += c;
				i++;
			}
		}
		return str;
	}
	//follow2：在指令里面会出现2（FFR）这种情况，就是FFRFFR。
	public void execute2(String query) {
		
		String s = decompression2(query);
		System.out.println(s);
		
		execute(s);
	}
	private String decompression2(String query) {
		String res = "";
		int i = 0;
		while(i < query.length()) {
			char c = query.charAt(i);
			if(c <= 'Z' && c >= 'A') {
				res += c;
				i++;
			} else if(c <= '9' && c > '0') {
				int count = c - '0';
				int j = i + 2;//skip the left brackets
				String tmp = "";
				while(j < query.length() && query.charAt(j) <= 'Z' && query.charAt(j) >= 'A') {
					tmp += query.charAt(j);
					j++;
					System.out.println(tmp);
				}
				System.out.println(tmp);
				while(count > 0) {
					res += tmp;
					count--;
				}
				i = j+1;
			}
		}
		return res;
	}
	public static void main(String[] args) {
		Turtle t = new Turtle(6);
		//t.execute("FFFRFF");
		//t.execute1("FFR3F");
		t.execute2("FF2(FFR)");
		int[] cors = t.getCordinnates();
		System.out.println(cors[0] + " "+ cors[1]);
	}
}
