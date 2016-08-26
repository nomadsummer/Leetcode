package microsoft;

public class UnionFind {
	int[] parent;
	int[] rank;
	int n;
	int count;

	UnionFind(int n) {
		this.parent = new int[n];
		this.rank = new int[n];
		this.n = n;
		this.count = n;
		// at beginning, the parent of node i is i itself
		for (int i = 0; i < n; i++) {
			this.parent[i] = i;
		}
	}

	int find(int x) {
		while (x != parent[x]) {
			parent[x] = parent[parent[x]];
		}
		return parent[x];
	}

	boolean union(int x, int y) {
		int findX = find(x);
		int findY = find(y);
		// detect a cycle in the graph
		if (findX == findY)
			return false;
		if (rank[findX] > rank[findY]) {
			parent[findY] = findX;
			count--;
		} else if (rank[findX] < findY) {
			parent[findX] = findY;
			count--;
		} else {
			parent[findX] = findY;
		}
		return true;
	}
}
