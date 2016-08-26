package google.phone;

public class AndroidUnlockPattern {
	public static boolean isValid(String s){
		if(s.length() < 4) return false;
		
		int[][] skip = new int[10][10];
        skip[1][3]=skip[3][1]=2;
        skip[7][9]=skip[9][7]=8;
        skip[1][7]=skip[7][1]=4;
        skip[3][9]=skip[9][3]=6;
        skip[2][8]=skip[8][2]=skip[1][9]=skip[9][1]=skip[3][7]=skip[7][3]=skip[4][6]=skip[6][4]=5;
 
        boolean[] visited = new boolean[10];
        int pre = s.charAt(0) - '0';
        visited[pre] = true;
        for(int i = 1; i < s.length(); i++) {
        	if((skip[pre][i] != 0 && !visited[skip[pre][i]]) || visited[i])
        		return false;
        	pre = i;
        }
        return true;
	}
	public static void main(String[] args) {
		System.out.println(isValid("654192"));
	}
}
