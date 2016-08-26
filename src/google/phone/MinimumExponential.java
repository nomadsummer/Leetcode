package google.phone;

/*
 * input: int n.
function: 将n用2的指数表示，使得指数表达式的个数最少
output : int num（指数的最少个数）
e.g: input = 28
28 = 2^4 + 2^3 + 2^2  => num = 3
28 = 2^5 - 2^2             => num = 2
所以 output = 2
 */
/*
 * 1. trailing zeros不影响最终的结果。
        比如：1100， 11000， 11000000的最优解都是num = 2。因为对于多项式来说，trailing zeros只是在原基础上 * 2，也就是各个指数+1，但并不影响项的数目
  
  2. 对于长串1，比如111， 11111,  11111111， 可以+1 -1，变为相应的2^n - 2^0，填了一项，但多了很多个0，转变为利用性质1，这样可以极大化简。


所以我的思路是，对于一个数字n，首先转化为2进制数字，然后从二进制数最低位向最高位开始循环：

  如果这个bit = 0，那么根据上述性质1，去掉不影响结果，那么直接pass，看下一个bit；
  如果这个bit = 1，那么根据性质2，继续查找接下来的连续的1，直到下一个bit为0或者已经到了最高位，然后将下一个bit置为1，从最低位到这位的bit都变为0，然后num = num + 1; 这时候由于后面的bit都是0，根据性质1， 又可以进行化简；   

如此循环  直到结束。

可能说的不是很明白，举个例子n = 236 = (11101100) 2：
首先初始化num = 0； 从最低bit开始向高位循环，遇到0 pass， 变为 111011；（即236 = 2^2 * 59 ）
最低bit为1，向前找连续的1，找到2个，那么将倒数第3bit置为1，其余变为0，即为111100，num +1 变为1；（即 236 = 2^2 *（60 - 1） ）
最低bit为0，一直pass，变为1111，即 236 = 2^2 *（2^2 * 15 - 1）
最低bit为1，向前找连续1，直到最高位，那么变为10000，num + 1变为2，即 236 = 2^2 *（2^2 * （16 - 1） - 1）
最低bit为0，一直pass，变为1，即236 = 2^2 *（2^2 * （2^4 - 1） - 1）
 */
public class MinimumExponential {
public static int getMin(int n) {
	if(n <= 0) return 0;
	if(n == 1) return 1;
	if((n&1) == 1) {
		return 1 + Math.min(getMin(n+1), getMin(n-1));
	} else return getMin(n >> 1);
}
public static void main(String[] args) {
	System.out.println(getMin(28));
}
}
