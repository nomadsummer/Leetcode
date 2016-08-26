package google.phone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import google.phone.FindAllConnectedComponentsInUndirectedGraph.UndirectedGraphNode;

public class FindAllConnectedComponentsInUndirectedGraph {

	class UnionFind {
		int[] parent;
		int[] rank;
		int count;

		UnionFind(int n) {
			this.count = n;
			rank = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		public int find(int x) {
			while (parent[x] != x) {
				parent[x] = parent[parent[x]];
			}
			return parent[x];
		}

		public boolean union(int x, int y) {
			int findX = find(x);
			int findY = find(y);
			if (findX == findY)
				return false;
			if (rank[findX] < rank[findY]) {
				parent[findX] = findY;
				count--;
			} else if (rank[findX] > rank[findY]) {
				parent[findY] = findX;
				count--;
			} else
				parent[findX] = findY;
			return true;
		}
	}

	class UndirectedGraphNode {

		int val;
		ArrayList<UndirectedGraphNode> neighbors;

		public UndirectedGraphNode(int x) {
			val = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	}

	public List<List<Integer>> findAllConnectedComponents(List<UndirectedGraphNode> nodes) {
		List<List<Integer>> connected = new ArrayList<List<Integer>>();

		// List<List<Integer>> adjMatrix = new ArrayList<List<Integer>>();
		// for(int i = 0; i < n; i++) {
		// adjMatrix.add(new ArrayList<Integer>());
		// }
		// for(int[] edge : edges) {
		// adjMatrix.get(edge[0]).add(edge[1]);
		// adjMatrix.get(edge[1]).add(edge[0]);
		// }
		int n = nodes.size();
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				List<Integer> list = new ArrayList<Integer>();
				bfs(connected, list, nodes.get(i), visited);
			}
		}
		return connected;
	}

	private void bfs(List<List<Integer>> connected, List<Integer> list, UndirectedGraphNode node, boolean[] visited) {
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();

		visited[node.val] = true;
		queue.offer(node);
		while (!queue.isEmpty()) {
			UndirectedGraphNode curr = queue.poll();
			list.add(curr.val);
			for (UndirectedGraphNode neighbor : curr.neighbors) {
				if (!visited[neighbor.val]) {
					visited[neighbor.val] = true;
					queue.offer(neighbor);
				}
			}
			connected.add(list);
		}
	}
	
	public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        // Write your code here
        
        int m = nodes.size();
        Map<UndirectedGraphNode, Boolean> visited = new HashMap<>();
        
       for (UndirectedGraphNode node : nodes){
            visited.put(node, false);
       }
        
        List<List<Integer>> result = new ArrayList<>();
        
        for (UndirectedGraphNode node : nodes){
            if (visited.get(node) == false){
                bfs(node, visited, result);
            }
        }
        
        return result;
    }
    
    public void bfs(UndirectedGraphNode node, Map<UndirectedGraphNode, Boolean> visited, List<List<Integer>> result){
        List<Integer>row = new ArrayList<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        visited.put(node, true);
        queue.offer(node);
        while (!queue.isEmpty()){
            UndirectedGraphNode u = queue.poll();
            row.add(u.val);    
            for (UndirectedGraphNode v : u.neighbors){
                if (visited.get(v) == false){
                    visited.put(v, true);
                    queue.offer(v);
                }
            }
        }
        Collections.sort(row);
        result.add(row);
        
    }
}

//------------------------我是分割线--------------------------------------
// 并查集方法
  class UnionFind{
    HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();
    UnionFind(HashSet<Integer> hashSet){
      for(Integer now : hashSet) {
        father.put(now, now);
      }
    }
  int find(int x){
    int parent =  father.get(x);
    while(parent!=father.get(parent)) {
      parent = father.get(parent);
    }
    return parent;
  }
  int compressed_find(int x){
    int parent =  father.get(x);
    while(parent!=father.get(parent)) {
      parent = father.get(parent);
    }
    int temp = -1;
    int fa = father.get(x);
    while(fa!=father.get(fa)) {
      temp = father.get(fa);
      father.put(fa, parent) ;
      fa = temp;
    }
    return parent;
      
  }
  
  void union(int x, int y){
    int fa_x = find(x);
    int fa_y = find(y);
    if(fa_x != fa_y)
      father.put(fa_x, fa_y);
  }
  List<List<Integer> >  print(HashSet<Integer> hashSet, UnionFind uf, int n) {
    List<List <Integer> > ans = new ArrayList<List<Integer>>();
  HashMap<Integer, List <Integer>> hashMap = new HashMap<Integer, List <Integer>>();
  for(int i : hashSet){
    int fa = uf.find(i);
    if(!hashMap.containsKey(fa)) {
      hashMap.put(fa,  new ArrayList<Integer>() );
    }
    List <Integer> now =  hashMap.get(fa);
    now.add(i);
    hashMap.put(fa, now);
  }
  for( List <Integer> now: hashMap.values()) {
  Collections.sort(now);
    ans.add(now);
  }
    return ans;
  }
  
  public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes){
  // Write your code here
  
    HashSet<Integer> hashSet = new HashSet<Integer>(); 
    for(UndirectedGraphNode now : nodes){
      hashSet.add(now.val);
      for(UndirectedGraphNode neighbour : now.neighbors) {
        hashSet.add(neighbour.val);
      }
    }
    UnionFind uf = new UnionFind(hashSet);

  
    for(UndirectedGraphNode now : nodes){
      
      for(UndirectedGraphNode neighbour : now.neighbors) {
        int fnow = uf.find(now.val);
      int fneighbour = uf.find(neighbour.val);
      if(fnow!=fneighbour) {
        uf.union(now.val, neighbour.val);
      }
      }
    }
    return print(hashSet , uf, nodes.size());
  }


}
