package google.phone;

/*
 * 假设除了用1和0表示数字的binary方式，还可以用2来表示，给一个数字，求有多少种表达方式。
 * 0=0
 * 1=01
 * 2=10,2
 * 3=11
 * 4=100,20,12
 * 5=101,21
 * 6=110,102,022
 * 
   例子：4 = 100, 20, 12 所以答案是3。 8 = 1000, 200, 120, 112 所以答案是4。
   我们可以分别讨论奇数偶数。
	如果n是奇数， 那么他的binary表示方式的最右位必然是1， 所以我们只要考虑除去最后一位剩下左边几位的表现形式，即dp[n] = dp[(n-1)/2]
	如果n是偶数，那么他的binary表示方式的最右位必然是0或2，我们分情况讨论：
     如果最右位是0，那么只要把n/2的每种表示方式后面加个0即可， 即dp[n] = dp[n/2]
     如果最右位是2，类似的可以得到dp[n] = dp[(n-2)/2]
 */
public class CountWays {

	public static int count(int n) {
		int[] count = new int[n+1];
		count[0] = 1;
		count[1] = 1;
		
		for(int i = 2; i < n + 1; i++) {
			if(i%2 == 1) {
				count[i] = count[(i-1)/2];
			} else if(i%2 == 0) {
				//if rightmost bit is 0
				count[i] = count[i/2];
				//if rightmost bit is 2
				count[i] += count[(i-2)/2];
			}
		}
		return count[n];
	}
	public static void main(String[] args) {
		System.out.println(count(3));
	}
}
