package google.phone;

public class RangeSumQuery {

	//1. update O(1), sum O(mn)
	int[][] sum;
	int[][] matrix;
	int m, n;
	RangeSumQuery(int[][] matrix) {
		this.matrix = matrix;
		this.m = matrix.length;
		this.n = matrix[0].length;
		//only used 
		this.sum = new int[m+1][n+1];
//		for(int i = 0; i < m; i++) {
//			for(int j = 0; j < n; j++) {
//				sum[i+1][j+1] = matrix[i][j] +sum[i+1][j] +sum[i][j+1]-sum[i][j];
//			}
//		}
	}
	
	public void update1(int row, int col, int val) {
		matrix[row][col] = val;
	}
	public int sumRegion1(int row1, int col1, int row2, int col2) {
		int res = 0;
		for(int i = row1; i <= row2; i++) {
			for(int j = col1; j <= col2; j++) {
				res += matrix[i][j];
			}
		}
		return res;
	}
	
	public void update2(int row, int col, int val) {
		matrix[row][col] = val;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				sum[i+1][j+1] = matrix[i][j] +sum[i+1][j] +sum[i][j+1]-sum[i][j];
			}
		}
	}
	public int sumRegion2(int row1, int col1, int row2, int col2) {
		return sum[row2+1][col2+1] + sum[row1][col1] - sum[row1][col2+1] - sum[row2+1][col1];
	}
}
