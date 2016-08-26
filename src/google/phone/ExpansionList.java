package google.phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class ExpansionList {

	/*
	 * Seed list = (1,2,3)
		Expansion list = ((1,4),(2,5),(6,7),(5,8))
		find lists in expansion list that has shared element with seed list and combine those lists with seed list into a set
		Return = (1,2,3,4,5)
	 */
	public static List<Integer> getSharedElements1(List<List<Integer>> expansions, List<Integer> seed) {
		
		List<Integer> rst = new ArrayList<Integer>();
		Set<Integer> set = new HashSet<Integer>();
		set.addAll(seed);
		
		for(List<Integer> expansion : expansions) {
			for(Integer i : expansion) {
				if(seed.contains(i)) {
					set.addAll(expansion);
				}
			}	
		}
		Iterator<Integer> iter = set.iterator();
		while(iter.hasNext()) {
			rst.add(iter.next());
		}
		return rst;
	}
	
//	Seed list = (1,2,3)
//			Expansion list = ((1,4),(2,5),(6,7),(5,8),(8,9))
//			similar request as the first problem but need to find all elements in expansion list that can be reached through shared elements.
//			Return = (1,2,3,4,5,8,9)
	public static List<Integer> getSharedElements2(List<Integer> seed, List<List<Integer>> expansions) {
		
		List<Integer> last = expansions.get(expansions.size()-1);
		int N = last.get(last.size()-1);
		System.out.println("size " + N);
		List<List<Integer>> graph = new ArrayList<List<Integer>>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for(List<Integer> list : expansions) {
			graph.get(list.get(0)).add(list.get(1));
			graph.get(list.get(1)).add(list.get(0));
		}
		List<List<Integer>> connect = new ArrayList<List<Integer>>();
		boolean[] visited = new boolean[N+1];
		for(int i = 1; i <= N; i++) {
			if(!visited[i])
					bfs(graph, connect, i, visited);
		}
		return getSharedElements1(connect, seed);
	}
	private static void bfs(List<List<Integer>> graph, List<List<Integer>> connect, int i, boolean[] visited) {
		List<Integer> list = new ArrayList<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>(); 
		queue.offer(i);
		visited[i] = true;
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			list.add(curr);
			List<Integer> neighbors = graph.get(curr);
			for(int neighbor : neighbors) {
				if(!visited[neighbor]) {
					visited[neighbor] = true;
					queue.offer(neighbor);
				}
			}
			connect.add(list);
		}
	}
	
	public static void main(String[] args) {
		List<Integer> seed = Arrays.asList(1,2,3);
		List<List<Integer>> expansions = Arrays.asList(Arrays.asList(1,4),
			Arrays.asList(2,5), Arrays.asList(6,7), Arrays.asList(5,8), Arrays.asList(8,9));
		
//		List<Integer> rst = getSharedElements1(expansions, seed);
//		for(Integer i : rst) {
//			System.out.println(i);
//		}
		List<Integer> rst2 = getSharedElements2(seed, expansions);
		for(Integer i : rst2) {
			System.out.println(i);
		}
	}
}
