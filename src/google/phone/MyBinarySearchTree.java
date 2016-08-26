package google.phone;
	
	public class MyBinarySearchTree{
	private TreeNode root;
	public MyBinarySearchTree() {
		this.root = null;
	}
	public TreeNode search(TreeNode root, int val) {
		if(root == null) return null;
		
		if(root.val == val) return root;
		
		if(root.val < val) {//search the left subtree
			return search(root.left, val);
		}
		return search(root.right, val);
	}
	
	public boolean find(int id){
		TreeNode current = root;
		while(current!=null){
			if(current.val==id){
				return true;
			}else if(current.val>id){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return false;
	}
	
	public void insert(int val) {
		root = insertNode(root, val);
	}
	
	private TreeNode insertNode(TreeNode root, int val) {
		if(root == null) {
			root = new TreeNode(val);
			return root;
		}
		
		if(root.val < val) {
			//insert at right
			root.right = insertNode(root.right, val);
		} else root.left = insertNode(root.left, val);
		return root;
	}
	
	public void delete(int val) {
		root = deleteNode(root, val);
	}
	private TreeNode deleteNode(TreeNode root, int val){
		if(root == null) return null;
		
		if(root.val < val) {
			root.right = deleteNode(root.right, val);
		} else if(root.val > val) {
			root.left = deleteNode(root.left, val);
		} else {//find the node to delete
			//1 no left & no right children
			if(root.left == null){
				return root.right;
			} else if(root.right == null){
				return root.left;
			} else {//has both children
				TreeNode node = root.right;//must not be null
				while(node.left != null) {
					node = node.left;
				}
				root.right = deleteNode(root.right, node.val);
				return root;
			}
//			} else {
//				
//				root.left = lift(root.left, root);
//				return root;
//			}
		}
		return null;
	}
	private TreeNode lift(TreeNode node, TreeNode toRemove) {
		if(node.right == null) {
			toRemove.val = node.val;
			return node.left;
		} else {
			node.right = lift(node.right, toRemove);
			return node;
		}
	}
	
	public boolean isSubTree(TreeNode root1, TreeNode root2) {
		if(root1 == null) return false;
		if(root2 == null) return true;
		
		if(isSameTree(root1, root2)) return true;
		
		return isSubTree(root1.left, root2) || isSubTree(root1.right, root2);	
	}
	
	public boolean isSameTree(TreeNode root1, TreeNode root2) {
		if(root1 == null && root2 == null) return true;
		if(root1 == null || root2 == null) return false;
		
		return root1.val == root2.val && isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
		
	}
}

