package google.phone;

import java.util.Random;

public class FlipMatrix {
	/*
	 * 我说一个flip()是O(M+N)时间的实现，空间复杂度是O(M)。其中M，N分别是矩阵的行列数。
一开始先创建一个长为M的cum数组。cum[i]代表“到第i行（含第i行）为止，矩阵中有多少个false”。比如大矩阵是：
0 1 0 0
1 0 0 1
0 0 1 0
那么cum数组就是
[3 5 9]
我们用cum数组来决定下一个随机数出现在哪一行。比如对[3 5 9]，我们先产生一个1~9之间的随机数，比如4，
然后在cum中找“第一个大于等于4”的数字，是第二个数5。那么我们就在第二行产生随机数。
然后，因为5和其前面的数3的差为2，说明这一行有2个false，那么我们再产生一个[1 2]间的随机数，
如1。 然后遍历第5行，直到找到第1个0为止。这一步花费O(N)时间

然后我们要更新cum数组，具体做法是把第二个数字和之后的数字都减1，最后cum变成[3 4 8]。这一步花费O(M)时间。

所以最后总时间是O(M+N)，空间是cum数组消耗的O(M)。
	 */
	public static void flip(boolean[][] matrix) {
		//countFalse[i] : until ith row, how many false elements existed in the matrix
		int[] countFalse = new int[matrix.length];
		int curr = 0;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(!matrix[i][j]) curr++;
			}
			countFalse[i] = curr;
		}
		int N = countFalse[matrix.length - 1];
		Random random = new Random();
		int next = random.nextInt(N);
		
		//countFalse must be sorted
		for(int i = 0; i < countFalse.length; i++) {
			if(countFalse[i] >= next) {
				//flip an element to false at ith row
				int rowToFlip = i;
				if(i > 0) {
					int countFalseCurrRow = countFalse[i] - countFalse[i-1];
					int nextCol = random.nextInt(countFalseCurrRow);
					for(int j = 0; j < nextCol; j++) {
						//first false encounter
						if(!matrix[rowToFlip][j]) {
							matrix[rowToFlip][j] = true;
						}
					}
				}
			}
		}
	}
}
