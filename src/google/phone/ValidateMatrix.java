package google.phone;

/*
 * Validate 一个二维整数矩阵n * n 从top left to bottom right, 是否斜着的每一条line上数字都相等。比如下面的矩阵，就是一个valid矩阵。
1 2 3 4.
5 1 2 3
6 5 1 2.
7 6 5 1

Follow up 1, 如果矩阵很大不能完整读到memory中，但是可以读至少一行怎么办？
第二题如果特别大, 就存成Map<<row,col>,value>, 然后map reduce..因为这种对比, 每次对比的数据和上次的对比的数据没有依赖关系...所以不比的数据都可以存硬盘
Follow up 2, 如果连一整行都读不到memory中怎么办，只能partially的读入一行的一部分怎么办？
逐行算rolling hash, 每往下读一行，rolling hash的计算向右shift一位开始计算，然后比较挨着的两行rolling hash值是否相等。
 */
public class ValidateMatrix {
	static int[][] matrix = new int[][]{{1,2,3,4},{5,1,2,3},{6,5,1,2},{7,6,5,1}};
	
	public static boolean validate(int[][] matirx) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
		
		for(int i = 0; i < matrix.length-1; i++) {
			for(int j = 0; j < matrix[0].length-1; j++) {
				if(matrix[i][j] != matrix[i+1][j+1]) return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		System.out.println(validate(matrix));
	}
	
}
