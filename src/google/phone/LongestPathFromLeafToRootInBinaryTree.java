package google.phone;

import java.util.ArrayList;
import java.util.List;

public class LongestPathFromLeafToRootInBinaryTree {
	
	public TreeNode longestPath(TreeNode root) {
		List<List<TreeNode>> paths = new ArrayList<List<TreeNode>>();
		List<TreeNode> list = new ArrayList<TreeNode>();
		
		dfs(paths, list, root);
		int longest = 0;
		TreeNode leaf = null;
		for(List<TreeNode> l : paths) {
			if(l.size() > longest) {
				longest = l.size();
				leaf = l.get(l.size()-1);
			}
		}
		return leaf;
	}
	
	private void dfs(List<List<TreeNode>> paths, List<TreeNode> list, TreeNode root) {
		if(root == null) return;
		
		if(root.left == null && root.right == null) {
			list.add(root);
			paths.add(new ArrayList<TreeNode>(list));
			return;
		}
		
		list.add(root);
		dfs(paths, list, root.left);
		dfs(paths, list, root.right);
		
	}
}
