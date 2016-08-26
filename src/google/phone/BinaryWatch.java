package google.phone;

import java.util.ArrayList;
import java.util.List;

/*optimize
 * 
 * 1. 首先就像hxtang所說的，找出0-59中 0-x digit on 的數字，然後存到一個2D vector裡面
    最後的答案就是hh取y digit on，mm取 (x-y) digit on的數字做組合

2. 找出0-59中 0-x digit on 的數字可以不用把 0-59都走一遍，只要有一個 1 digit on的array
    nums[1] = {1, 2, 4, 8, 16, 32};
    剩下的array就可以用nums[y] = nums[y-1] + nums[1] 求出來 
  （只要nums[y-1]和nums[1]的所有組合，扣掉兩個數字佔有同樣bit的相加，就是nums[y]裡面的數字）
 * */
/*上边是小时，下边是分钟，最左边最significant，最右边为1。
 * 给你数字n (number of 1)，return所有可能的时间，以string为表达形式?
 */
public class BinaryWatch {
	//1 get the number of possible time
	public int getCount(int N){
		int[] hours = count(12, 4);
		int[] minutes = count(60, 6);
		int sum = 0;
		for(int i = 0; i <= 4 && i < N; i++) {
			sum += hours[i] * minutes[N-i];
		}
		return sum;
	}
	
	public int[] count(int max, int digits) {
		int[] counter = new int[digits];
		
		for(int i= 0; i < max; i++) {
			int curr = i;
			int count = 0;
			while(curr > 0) {
				if((curr & 1) == 0) count++;
				curr >>= 1;
				if(count == digits) break;
			}
			if(count < digits) counter[count]++;
		}
		return counter;
	}
	
	//print all possible times
	public static void getTime(List<Integer> times, Integer currTime, int max, int zero, int one) {
		//one, number of 1 bits, zero: number of 0 bits left
		if(one == 0 && zero == 0 && currTime <= max) {
			times.add(currTime);
		}
		if(one > 0) {
			//if next bit is 1
			currTime <<= 1;
			currTime++;
			getTime(times,currTime,max, zero, one-1);
			currTime >>= 1;
		}
		if(zero > 0) {
			currTime <<= 1;
			getTime(times, currTime, max, zero-1, one);
			currTime >>= 1;
		}
	}
	
	public static List<String> printAllTime(int N) {
	
		List<String> time = new ArrayList<String>();
		if(N >= 9) return time;
		for(int i = 0; i <= N && i<= 4; i++) {
			if(N-i > 6) continue;
			//N-i number of one for n
			List<Integer> hours = new ArrayList<Integer>();
			getTime(hours, 0, 12, i, 4-i);
			List<Integer> minutes = new ArrayList<Integer>();
			getTime(minutes, 0, 60, N-i, i-N+6);
			for(int hh : hours) {
				for(int mm: minutes) {
					String tmp = (hh < 10 ? "0" : "") + hh + ":" + (mm < 10 ? "0" : "") + mm;
					time.add(tmp);
					//System.out.println(tmp);
				}
			}
		}
		return time;
	}
	
	public static List<String> clock(int n) {
        List<String> res = new ArrayList<>();
        clockHelper(res, "0000000000", n, 0);
        return res;
}
	private static void clockHelper(List<String> res, String digits, int n, int pos) {
        if (n == 0) {
                String time = makeTime(digits);
                if (!time.equals("")) {
                        res.add(time);
                }
                return;
        }
        StringBuilder sb = new StringBuilder(digits);
        for (int i = pos; i <= digits.length() - n; i++) {
               	//sb.replace(i, i + 1, "1");
                clockHelper(res,sb.substring(0,i)+"1"+sb.substring(i+1), n-1, i+1);
                //clockHelper(res, sb.toString(), n - 1, i + 1);
                //sb.replace(i, i + 1, "0");
        }
	}
		private static String makeTime(String digits) {
        int hour = Integer.valueOf(digits.substring(0, 4), 2);
        int min = Integer.valueOf(digits.substring(4, digits.length()), 2);
        if (hour > 12 || min > 60) {
                return "";
        }
        return (hour<10 ?"0":"")+hour+ ":"+(min<10?"0":"")+min;
}
	
	public static void main(String[] args) {
		List<String> time = printAllTime(2);
		for(String t : time) {
			System.out.println(t);
		}
		List<String> time2 = clock(2);
		System.out.println();
		for(String t : time2) {
			System.out.println(t);
		}
	}
}
