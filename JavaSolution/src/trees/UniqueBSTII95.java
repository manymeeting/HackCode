package trees;

import java.util.ArrayList;
import java.util.List;

/**
 *
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

 */

// 遍历1-n，当前数作为root，以root为中心取左右区间进行递归，
// 对左右list两层循环，每次把left和right加到一个新的root里，再把root加入当前res list

public class UniqueBSTII95 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new ArrayList<>(); // 特殊情况，直接返回空list，否则会出现嵌套的空list
        return helper(1, n);
    }

    private List<TreeNode> helper(int l, int r) {
        List<TreeNode> res = new ArrayList<>();
        if (l > r) {
            res.add(null);
            return res;
        }
        for (int i = l; i <= r; ++i)
            // i代表当前root
            for (TreeNode left : helper(l, i - 1))
                for (TreeNode right: helper(i + 1, r)) {
                    TreeNode root = new TreeNode(i);
                    // 注意这里直接用left和right的引用，不能根据值new一个，因为此时left和right都带有自己的subtree
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
        return res;
    }
}
