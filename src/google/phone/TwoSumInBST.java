package google.phone;

import java.util.Stack;

public class TwoSumInBST {

	public static boolean twoSumBST(TreeNode root, int target) {
		if(root == null) return false;
		
		Stack<TreeNode> leftStack = new Stack<TreeNode>();
		Stack<TreeNode> rightStack = new Stack<TreeNode>();
		
		TreeNode node = root;
		while(node != null) {
			leftStack.push(node);
			node = node.left;
		}
		node = root;
		while(node != null) {
			rightStack.push(node);
			node = node.right;
		}
		TreeNode left = leftStack.peek();
		TreeNode right = leftStack.peek();
		
		while(left != right && left.val < right.val) {
			if(left.val + right.val == target) {
				return true;
			} else if(left.val + right.val < target) {
				// Find next node in normal Inorder traversal.
				leftStack.pop();
				if(left.right != null) {
					left = left.right;
					while(left != null) {
						leftStack.push(left);
						left = left.right;
					}	
				}
				if(leftStack.empty()) return false;
				left = leftStack.peek();
			} else if(left.val + right.val > target) {
				//find next node in REVERSE Inorder traversal. The only
		        // difference between above and below loop is, in below loop
		        // right subtree is traversed before left subtree
				rightStack.pop();
				if(right.left != null) {
					right = right.left;
					while(right != null) {
						rightStack.push(right);
						right = right.left;
					}
				}
				if(rightStack.empty()) return false;
				right = rightStack.peek();
			}
		}
		return false;
	}
}
