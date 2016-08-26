package google.phone;

public class ConnectTreeNodeNextAndPre {
	TreeNode prev = null;
	public void connect(TreeNode root) {
		if(root == null) return;
		
		connect(root.left);
		if(prev != null) {
			prev.next = root;
			root.pre = prev;
		}
		prev = root;
		connect(root.right);
	}
}
