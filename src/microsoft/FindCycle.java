package microsoft;

public class FindCycle {
	
	public static boolean validTree(int n, int[][] edges) {
		UnionFind uf = new UnionFind(n);
		
		for(int[] edge : edges) {
			uf.union(edge[0], edge[1]);
			System.out.println("count after union + " + uf.count);
		}
		return uf.count == 1;
	}
	
	public static void main(String[] args) {
		int[][] edges = new int[][]{{0,1},{0,2},{0,3},{1,4}};
		System.out.println("No cycle exist" + validTree(5, edges));
	}
}
