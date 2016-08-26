package google.phone;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*题目是给你一个board，里面存储user的信息，user有id和socre。
board有adduser(id, score)(返回add进去的user当前的rank), findByRank(k) (这个返回id)。
Add如果本身已经有id在board中，需要对这个id的score进行update。*/
public class RankTree {
	class Node {
		public int score;
		public int id;
		public int size;
		public Node left;
		public Node right;

		Node(int id, int score) {
			this.id = id;
			this.score = score;
		}
		int search(int score) {
			return search(root, score);
		}
		int search(Node node, int score) {
			if(node == null) return -1;
			
			if(score < node.score) {
				return search(node.left, score);
			} else if(score > node.score) {
				return search(node.right, score);
			} else return node.id;
		}
		
		Node insert(Node node, int score, int id) {
			if(node == null) {
				Node n = new Node(id, score);
				
			}
		}
	}

	public HashMap<String, Node> scoreIDMap;
	Node root;

	RankTree() {
		scoreIDMap = new HashMap<String, Node>();
		}

	

}
