package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.

 Example:

 Input: [1,2,3,null,5,null,4]
 Output: [1, 3, 4]
 Explanation:

 1            <---
 /   \
 2     3         <---
 \     \
 5     4       <---

 */
public class BinaryTreeRightSideView199 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rightValues(root, res, 0);
        return res;
    }

    private void rightValues(TreeNode curr, List<Integer> res, int currHeight)
    {
        if(curr == null) return;

        if(res.size() == currHeight)
        {
            res.add(curr.val);
        }

        // right first，加上left是为了处理right为null但left有值的情况
        rightValues(curr.right, res, currHeight+1);
        rightValues(curr.left, res, currHeight+1);

    }

}
