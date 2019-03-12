package trees;

import java.util.*;

/**
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

 Two trees are duplicate if they have the same structure with same node values.

 Example 1:

 1
 / \
 2   3
 /   / \
 4   2   4
 /
 4
 The following are two duplicate subtrees:

 2
 /
 4
 and

 4
 Therefore, you need to return above trees' root in the form of a list.

 */

// 二叉树怎样序列化才能重建：https://zhuanlan.zhihu.com/p/26418233
// 可以pre-order遍历或post-order遍历，但不能in-order，因为只用#表示null的情况下无法通过in-order的结果确定tree的结构，
// 比如0-1（left）和0-1（right）

public class FindDuplicateSubtrees652 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> map = new HashMap<>();
        List<TreeNode> res = new ArrayList<>();
        postOrder(root, map, res);
        return res;

    }

    private String postOrder(TreeNode root, Map<String, Integer> map, List<TreeNode> res) {
        if(root == null) return "#";
        String str = postOrder(root.left, map, res) + ","
                + postOrder(root.right, map, res)  + "," + String.valueOf(root.val);

        if(map.getOrDefault(str, 0) == 1) {
            res.add(root);
        }
        map.put(str, map.getOrDefault(str, 0) + 1);

        return str;
    }
}
