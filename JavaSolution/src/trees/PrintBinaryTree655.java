package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Print a binary tree in an m*n 2D string array following these rules:

The row number m should be equal to the height of the given binary tree.
The column number n should always be an odd number.
The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
Each unused space should contain an empty string "".
Print the subtrees following the same rules.
Example 1:
Input:
     1
    /
   2
Output:
[["", "1", ""],
 ["2", "", ""]]
Example 2:
Input:
     1
    / \
   2   3
    \
     4
Output:
[["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]
Example 3:
Input:
      1
     / \
    2   5
   / 
  3 
 / 
4 
Output:

[["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]

 */

// 先计算tree的height，然后构造res的结构，再递归插入节点值，
// 规律是每层递归按照left边界和right边界计算出rootIdx，然后给左右children分别传入相应的left和right边界
// 注意边界计算时，左边从0开始，右边最大是length

public class PrintBinaryTree655 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private List<List<String>> res;
    private int height = 0;
    private int levelLen = 0;

    public List<List<String>> printTree(TreeNode root) {
        res = new ArrayList<>();
        int treeHeight = findTreeHeight(root); // starts from 1
        levelLen = (int)Math.pow(2, treeHeight) - 1; // 2^h - 1
        // Build result structure
        for (int i = 0; i < treeHeight; i++) {

            List<String> levelList = new ArrayList<>();
            for (int j = 0; j < levelLen; j++){
                levelList.add("");
            }

            res.add(levelList);
        }

        helper(root, 0, levelLen, 0);
        return res;
    }

    private int findTreeHeight(TreeNode root) {
        if(root == null) return 0;
        int leftHeight = findTreeHeight(root.left);
        int rightHeight = findTreeHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // return idx
    private void helper(TreeNode root, int leftIdx, int rightIdx, int level){

        if(root == null) {
            return;
        }
        else {
            int rootIdx = (leftIdx + rightIdx) / 2;

            helper(root.left, leftIdx, rootIdx, level+1);

            List<String> levelList = res.get(level);
            levelList.remove(rootIdx);
            levelList.add(rootIdx, String.valueOf(root.val));

            helper(root.right, rootIdx, rightIdx, level+1);
        }
    }
}
