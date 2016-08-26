package google.phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FindCycleInDirectedGraph {
	public int countCycle(int[][] edges, int num) {
		
		int[] visited = new int[num];
		List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0 ; i < num; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for(int i = 0; i < edges.length; i++) {
			graph.get(edges[i][1]).add(edges[i][0]);
		}
		int count = 0;
		for(int i = 0; i < num; i++) {
			if(visited[i] == 0 && detectCycle(graph,stack,i, visited)) {
				count++;
			}
		}
		return count;
	}

	private boolean detectCycle(List<ArrayList<Integer>> graph, Stack<Integer> stack,
			int pre, int[] visited) {
		//being visited
		visited[pre] = -1;
		for(int curr : graph.get(pre)) {
			if(visited[curr] == -1) return true;
			else if(visited[curr] == 0 && detectCycle(graph, stack, curr, visited)) return true;
		}
		visited[pre] = 1;
		stack.push(pre);
		return false;
	}
	
}
