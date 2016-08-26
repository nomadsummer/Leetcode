package microsoft;

import java.util.ArrayList;
import java.util.List;

public class CountWinner {

	public static List<Integer> winner(int n, int k) {
		List<Integer> res = new ArrayList<Integer>();
		int[] children = new int[n];
		int count = 0, pos = 0, curr = 0;
		for(int i = 0; i < n; i++) {//place the children
			children[i] = i+1;
		}
		
		while(count < n - k + 1) {
			while(children[pos] == 0) {
				//children in postion pos has been removed, update the position
				pos = (pos + 1) % n;
			}
			curr++;
			if(curr == k) {
				//remove current child
				children[pos] = 0;
				curr = 0;
				count++;
			}
			pos = (pos + 1) % n;	
		}
		
		for(int i = 0; i < n; i++) {
			if(children[i] != 0){
				res.add(children[i]);
			} 
		}
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(winner(8,4));
	}
}
