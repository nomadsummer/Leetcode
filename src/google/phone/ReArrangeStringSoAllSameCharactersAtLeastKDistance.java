package google.phone;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class ReArrangeStringSoAllSameCharactersAtLeastKDistance {

	class Node{
		char character;
		int count;
		int lastPosition;
		
		Node(char c) {
			this.character = c;
			this.count = 0;
			this.lastPosition = 0;
		}
	}
	
	public String reArrange(String s, int k) {
		if(s == null || s.length() == 0 || k <= 0) return s;
		
		Map<Character, Node> map = new HashMap<Character, Node>();
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			Node node = null;
			if(!map.containsKey(c)) {
				node = new Node(c);
			} else {
				node = map.get(c);
			}
			node.count++;
			map.put(c, node);
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>(){
			@Override
			public int compare(Node n1, Node n2) {
				if(n1.count != n2.count) return n1.count - n2.count;
				return n1.character - n2.character;
			}
		});
		for(Node node : map.values()) {
			pq.offer(node);
		}
		
		StringBuilder sb = new StringBuilder();
		Queue<Node> queue = new LinkedList<Node>();
		
		for(int i = 0; i < s.length(); i++) {
			if(!queue.isEmpty() && queue.peek().lastPosition + k < i) {
				pq.offer(queue.poll());
			}
			
			if(!pq.isEmpty()) {
				Node curr = pq.poll();
				curr.lastPosition = i;
				curr.count--;
				sb.append(curr);
				if(curr.count != 0) {
					queue.offer(curr);
				}
			}
			
		}
		return sb.toString();
	}
	
	public static String taskScheduler(String tasks, int n) {
        StringBuilder sb = new StringBuilder();
        HashMap<Character, Integer> countMap = new HashMap<Character, Integer>();
        for(char c : tasks.toCharArray()) countMap.put(c, countMap.containsKey(c) ? countMap.get(c) + 1 : 1);              // sort the task by their occurence time (N/c * c *logk). 1point 3acres 璁哄潧
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>(){
                public int compare(Map.Entry<Character,Integer> e1, Map.Entry<Character,Integer> e2) {
                        return e2.getValue() - e1.getValue();
                }
        });

        for(Map.Entry entry : countMap.entrySet()) queue.offer(entry);
        
        while(!queue.isEmpty()) {
                List<Map.Entry> refill = new ArrayList<Map.Entry>();
                int i = 0;
                for(i = 0; i < n && !queue.isEmpty(); i++) {
                        Map.Entry e = queue.poll();
                        sb.append(e.getKey());
                        int count = (Integer)e.getValue();
                        if(count > 1) refill.add(new AbstractMap.SimpleEntry<Character, Integer>((Character)e.getKey(), count - 1));
                }
                while(i++ < n && refill.size() > 0) sb.append("_");
                // refill the task which hasn't finished yet
                for(Map.Entry e : refill) queue.offer(e);
        }
        return sb.toString();
}
}
