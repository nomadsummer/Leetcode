import java.util.ArrayList;

public class UndirectedGraphNode {

	int val;
	ArrayList<UndirectedGraphNode> neighbors;
	public UndirectedGraphNode(int x) { 
		val = x; 
		neighbors = new ArrayList<UndirectedGraphNode>(); 
	}
}
