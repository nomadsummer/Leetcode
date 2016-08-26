package google.phone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class TopKConnectedComponent {

    class UndirectedGraphNode {
            int label;
            ArrayList<UndirectedGraphNode> neighbors;

            UndirectedGraphNode(int x) {
                    label = x;
                    neighbors = new ArrayList<UndirectedGraphNode>();
            }
    };

    public List<List<Integer>> getTopConnectedComponet(ArrayList<UndirectedGraphNode> nodes, int k) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            if (nodes == null || nodes.size() == 0) {
                    return res;
            }
            Set<UndirectedGraphNode> set = new HashSet<UndirectedGraphNode>();
            PriorityQueue<List<Integer>> pq = new PriorityQueue<List<Integer>>(new Comparator<List<Integer>>() {
                    public int compare(List<Integer> l1, List<Integer> l2) {
                            return l1.size() - l2.size();
                    }
            });
            for (UndirectedGraphNode v : nodes) {
                    if (!set.contains(v)) {
                            List<Integer> candidate = bfs(v, set);
                            if (pq.size() < k || pq.peek().size() < candidate.size()) {
                                    pq.poll();
                                    pq.offer(candidate);
                            }
                    }
            }
            while (!pq.isEmpty()) {
                    res.add(0, pq.poll());
            }
            return res;
    }
    
    public List<Integer> bfs(UndirectedGraphNode node, 
                    Set<UndirectedGraphNode> set) {
            List<Integer> row = new ArrayList<Integer>();
            Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
            queue.offer(node);
            set.add(node);
            while (!queue.isEmpty()) {
            	UndirectedGraphNode cur = queue.poll();
                    row.add(cur.label);
                    for (UndirectedGraphNode n : cur.neighbors) {
                            if (!set.contains(n)) {
                                    set.add(n);
                                    queue.offer(n);
                            }
                    }
            }
            Collections.sort(row);
            return row;
    }
}
