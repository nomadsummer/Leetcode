package microsoft;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class CourseSchedule {

	//1 detect cycle in a directed graph
	//2, [[1,0]. [2,1]]
	//topological sorting + BFS
	public static boolean canFinish(int n, int[][] prerequisites) {
		
		//topological sorting to detect cycles
		int[] indegree = new int[n];
		for(int i = 0; i < n; i++) {
			indegree[prerequisites[i][1]]++;
		}
		//push all courses with in-degree = 0 to queue
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 0; i < n; i++) {
			if(indegree[i] == 0) {
				queue.offer(i);
			}
		}
		int number = queue.size();
		while(!queue.isEmpty()) {
			Integer curr = queue.poll();
			for(int i = 0; i < n; i++) {
				if(prerequisites[i][0] == curr) {
					indegree[prerequisites[i][0]]--;
					if(indegree[prerequisites[i][0]] == 0) {
						queue.offer(prerequisites[i][0]);
						number++;
					}
				}
			}
		}
		return number==n;
	}
	
	public int[] findOrder(int n, int[][] prerequisites) {
		// to find the order in a directed graph
		// dfs, visited[i] = 0 -> white, unvisited, visited[i] = 1 -> black, visited and no cycle detected
		//visited[i] = -1, being explored and processed
		
		int[] visited = new int[n];
		
		//build graph
		List<List<Integer>> graph = new ArrayList<List<Integer>>();
		for(int i = 0; i < n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for(int i = 0; i < prerequisites.length; i++) {
			graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0; i < n; i++) {
			if(visited[i] == 0 && hasCycle(graph, stack, i, visited)) return new int[0];
		}
		int[] order = new int[n];
		for(int i = 0; i < n; i++) {
			order[i] = stack.pop();
		}
		return order;
	}
	
	private static boolean hasCycle(List<List<Integer>> graph, Stack<Integer> stack, int pre, int[] visited) {
		visited[pre] = -1;//the node is being currently explored
		
		for(int course : graph.get(pre)) {//explore all neighbors of pre node
			if(visited[course] == -1) return true;//cycle detected
			else if(visited[course] == 0 && hasCycle(graph, stack, course, visited)) return true;
		}
		visited[pre] = 1;
		stack.push(pre);
		return false;
		
	}
}
