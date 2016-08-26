package google.phone;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOfIslandsWithDifferentShapes {

	public static int getNumberOfIslandsWithDifferentShapes(int[][] matrix) {
		
		int m = matrix.length, n = matrix[0].length;
		boolean[][] visited = new boolean[m][n];
		Set<String> set = new HashSet<String>();
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(matrix[i][j] == 1) {
					List<Integer> list = new ArrayList<Integer>();
					dfs(matrix, visited, i, j, list);
					String key = getKey(list);
					set.add(key);
				}
			}
		}
		return set.size();
		
	}

	private static String getKey(List<Integer> list) {
		String res = "";
		int diff = list.get(0) - 0;
		for(int i : list) {
			res += (i-diff)  + "#";
		}
		return res;
	}
	private static void dfs(int[][] matrix, boolean[][] visited, int i, int j, List<Integer> list) {
		// TODO Auto-generated method stub
		int m = matrix.length, n = matrix[0].length;
		if(i < 0 || i > m-1 || j < 0 && j > n - 1 || visited[i][j] || matrix[i][j] != 1) return;
		
		visited[i][j] = true;
		int[] dx = {1,-1,0,0,1,1,-1,-1};
		int[] dy = {0,0,1,-1,1,-1,1,-1};
		
		int code = i * n + j;
		list.add(code);
		for(int x = 0; x < dx.length; x++) {
			for(int y = 0; y < dx.length; y++) {
				int nx = i + dx[x];
				int ny = j + dy[y];
				dfs(matrix, visited, nx, ny, list);
			}
		}
		
		
	}
}
