package microsoft;

public class Search2DMatrix {

	public static boolean searchMatrix(int[][] matrix, int target) {

		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;
		int m = matrix.length, n = matrix[0].length;
		for (int i = 0; i < m; i++) {
			int start = 0, end = n - 1;
			if (target > matrix[i][end]) {
				// does not exist in curr row
				continue;
			} else {
				boolean exist = binarySearch(start, end, matrix[i], target);
				if (exist)
					return true;
			}

		}
		return false;
	}

	private static boolean binarySearch(int start, int end, int[] nums, int target) {

		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (nums[mid] == target)
				return true;
			else if (nums[mid] > target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		if (nums[start] == target || nums[end] == target)
			return true;
		return false;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 5 }, { 6 } };

		boolean exist = searchMatrix(matrix, 6);
		System.out.println(exist);
	}
}
