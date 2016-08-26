package microsoft;

public class RotateMatrix {

	//rotate matrix from upper left to bottom right
	
	public static void rotateBy180(int[][] matrix) {		
		//1. first assume the matrix is a n*n matrix
		int n = matrix.length;
		//rotate the matrix level by level
		for(int level = 0; level < n/2; level++) {
			 int first = level;
			 int last = n - level - 1;
			 for(int i = first; i < last; i++) {
				 //store the top element of the matrix
				 int temp = matrix[first][i];
				 //flip the bottom to the top 
				 matrix[first][i] = matrix[last][last-i + first];
				 //flip the top to the bottom
				 matrix[last][last - i + first] = temp;
				 
				 temp = matrix[last-i+first][first];
				 matrix[last-i+first][first] = matrix[i][last];
				 matrix[i][last] = temp;
			 }
		}
	}
	public static void rotateBy90(int[][] matrix) {
		int n = matrix.length;
		for(int level = 0; level < n/2; level++) {
			int first = level;
			int last = n - level - 1;
			for(int i = first; i < last; i++) {
				//store the top element of the matrix;
				int temp = matrix[first][i];
				
				matrix[first][i] = matrix[last-i+first][first];
				matrix[last-i+first][first] = matrix[last][last-i+first];
				matrix[last][last-i+first] = matrix[i][last];
				matrix[i][last] = temp;
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
		rotateBy180(matrix);
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
		matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
		rotateBy90(matrix);
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
}
