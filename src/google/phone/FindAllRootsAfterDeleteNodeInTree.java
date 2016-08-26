package google.phone;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*删除树中一些node，然后求剩下所有的tree的root*/
public class FindAllRootsAfterDeleteNodeInTree {

	public List<TreeNode> findAllRoots(TreeNode root, int[] vals) {
		
		Set<Integer> set = new HashSet<Integer> ();
		for(int val : vals) {
			set.add(val);
		}
		List<TreeNode> res = new ArrayList<TreeNode>();
		root = helper(res, set, root);
		if(root != null) res.add(root);//if at last root still exists
		return res;
		
	}
	private TreeNode helper(List<TreeNode> res,Set<Integer> set,TreeNode root) {
		if(root == null) return root;
		
		root.left = helper(res, set, root.left);
		root.right = helper(res, set, root.right);
		
		//delete root, add children as root;
		if(set.contains(root.val)) {//to be deleted
			if(root.left != null) {
				res.add(root.left);
			}
			if(root.right != null) {
				res.add(root.right);
			}
			return null;
		}
		return root;
	}
}
