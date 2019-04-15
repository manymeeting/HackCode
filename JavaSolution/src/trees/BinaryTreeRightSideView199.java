package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

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
// 解法1（如下）：dfs，每层递归传入一个currHeight，利用结果集的size是否等于currHeight来判断是否加入当前node
// 解法2：还是用dfs，但用一个global变量记录当前高度，通过高度来决定是否将当前node加入结果集
// 解法3：用bfs，每一层取最后一个加入结果集
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
