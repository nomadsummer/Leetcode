package google.phone;

/*
 * 给两个二叉树，A 和 B 。 要求判断A 是不是B 的子树。 子树的意思是： A 是 B的一部分
 */
public class SubTree {
	//check if B is a subtree of A
	public static boolean isSubTree(TreeNode A, TreeNode B) {
		if(B == null) return true;
		if(A == null) return false;
		
		if(A.val == B.val && sameTree(A, B)) {
			return true;
		}
		return isSubTree(A.left, B) || isSubTree(A.right, B);
	}

	private static boolean sameTree(TreeNode a, TreeNode b) {
		if(a == null && b == null) return true;
		if(a == null || b == null) return false;
		
		if(a.val == b.val) {
			return sameTree(a.left, b.left) && sameTree(a.right, b.right);
		}
		return false;
	}
}
