package google.phone;

/*题目是按位与两个binary tree，每个tree都是满的，比如
  rootA       rootB
  /    \        /     \
0      1     / \     / \ 
             1   0   1  0
然后输出left child 跟 left child 与 right child 跟right child 与 输出结果是  
    newRoot
    /          \
  / \         / \.
0   1       1  0
说中间层不用管，只有leaf node有值
*/
public class BitAndTree {
	public void bitAnd(TreeNode root1, TreeNode root2) {
		if(root1 == null || root2 == null) return;
		TreeNode node1  = root1, node2 = root2;
		int h1 = 0, h2 = 0;
		while(node1 != null) {
			node1 = node1.left;
			h1++;
		}
		while(node1 != null) {
			node2 = node2.left;
			h2++;
		}
		if(h1 > h2) {
			TreeNode t = root1;
			root1 = root2;
			root2 = t;
		}
		//assume root1 has smaller heights
		if(root1.left == null && root1.right == null) {
			//if reached the leave of root1
			if(root1.val == 0) {
				setToZero(root2);
			} else return;
		}
		bitAnd(root1.left,root2.left);
		bitAnd(root1.right, root2.right);
	}

	private static void setToZero(TreeNode node) {
		// TODO Auto-generated method stub
		if(node == null) return;
		
		if(node.left == null && node.right == null) {
			node.val =0;
			return;
		}
		setToZero(node.left);
		setToZero(node.right);
	}
	
    public TreeNode bitAnd3(TreeNode a, TreeNode b) {
    	if(a.left == null || b.left == null) {
    		if(a.left == null) {
    			if(a.val == 0) 
    				setToZero(b);
    			return b;
    		} else {
    			if(b.val == 0)
    				setToZero(a);
    			return a;
    		}
    	}
    	TreeNode left = bitAnd3(a.left,b.left);
    	TreeNode right = bitAnd3(a.right,b.right);
    	return left == a.left ? a : b;
    		
    }
public TreeNode bitAnd2(TreeNode a, TreeNode b) {
        if(a==null && b == null) return null;
        else if(a==null) return b;
        else if(b==null) return a;

        TreeNode ret = new TreeNode(a.val & b.val);
        if(a.left==null && b.left==null) {
            ret.left = null;
        }
        else{
            TreeNode leftA = a.left == null ? new TreeNode(a.val) : a.left;
            TreeNode leftB = b.left == null ? new TreeNode(b.val) : b.left;
            ret.left = bitAnd2(leftA, leftB);
        }

        if(a.right==null && b.right==null) {
            ret.right = null;
        }
        else {
            TreeNode rightA = a.right == null ? new TreeNode(a.val) : a.right;
            TreeNode rightB = b.right == null ? new TreeNode(b.val) : b.right;
            ret.right = bitAnd2(rightA, rightB);
        }

        return ret;
    }

	
	
}
