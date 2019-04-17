package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:

Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Example 2:

Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.
 */


// dfs+backtracking遍历，用一个StringBuilder，每次把当前数字append到sb里（递归结束后remove），
// 遇到leaf节点就把当前sb加到list中，最后把list里所有数字string相加即可

public class SumRootToLeafNums129 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int sumNumbers(TreeNode root) {
        List<String> numStrList = new ArrayList<>();
        helper(root, numStrList, new StringBuilder());
        int res = 0;
        for (String numStr : numStrList) {
            res += Integer.parseInt(numStr);
        }
        return res;
    }

    private void helper(TreeNode node, List<String> numStrList, StringBuilder sb) {
        if(node == null) return;

        sb.append(node.val);
        if (node.left == null && node.right == null) {
            numStrList.add(sb.toString());
            return;
        }
         
        if(node.left != null) {
            helper(node.left, numStrList, sb);
            sb.deleteCharAt(sb.length()-1);
        }
        if(node.right != null) {
            helper(node.right, numStrList, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
