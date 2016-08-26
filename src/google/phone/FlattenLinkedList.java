package google.phone;

/*
 * 1) Take "cur" pointer, which will point to head of the fist level of the list
2) Take "tail" pointer, which will point to end of the first level of the list
3) Repeat the below procedure while "curr" is not NULL.
    I) if current node has a child then
	a) append this new child list to the "tail"
		tail->next = cur->child
	b) find the last node of new child list and update "tail"
		tmp = cur->child;
		while (tmp->next != NULL)
			tmp = tmp->next;
		tail = tmp;
    II) move to the next node. i.e. cur = cur->next 
 */
public class FlattenLinkedList {

	static class Node {
		double val;
		Node next;
		Node down;
		Node(double val) {
			this.val = val;
		}
	}
	
	Node flatten(Node root)
    {
        // Base Cases
        if (root == null || root.next == null)
            return root;
 
        // recur for list on right
        root.next = flatten(root.next);
 
        // now merge
        root = merge(root, root.next);
 
        // return the root
        // it will be in turn merged with its left
        return root;
    }
	
	// An utility function to merge two sorted linked lists
    Node merge(Node a, Node b)
    {
        // if first linked list is empty then second
        // is the answer
        if (a == null)     return b;
 
        // if second linked list is empty then first
        // is the result
        if (b == null)      return a;
 
        // compare the data members of the two lonked lists
        // and put the larger one in the result
        Node result;
 
        if (a.val < b.val)
        {
            result = a;
            result.down =  merge(a.down, b);
        }
 
        else
        {
            result = b;
            result.down = merge(a, b.down);
        }
 
        return result;
    }
	
	public static Node flatten1(Node root) {
		
		if(root == null || root.next == null) return root;
		
		root.next = flatten1(root.next);
		root = merge1(root, root.next);
		return root;
		
	}
	private static Node merge1(Node a, Node b) {
		
		if(a == null) return b;
		if(b == null) return a;
		a.down = merge1(a.down, b);
		return a;
		
	}
	public static void main(String[] args) {
		
	}
}
