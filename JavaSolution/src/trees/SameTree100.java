package trees;

import java.util.ArrayList;

/***
 *Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true
Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false
Example 3:

Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false

 */

public class SameTree100 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // 解法1：直接递归比较
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;

        if((p == null && q != null) || (p != null && q == null)) return false; // 有一个为null
        if(p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

    }


    // 解法2：遍历以后每个值在list里存为string格式，比较list即可

    // public boolean isSameTree(TreeNode p, TreeNode q) {
    //     ArrayList<String> vals1 = new ArrayList<>();
    //     ArrayList<String> vals2 = new ArrayList<>();

    //     traversal(p, vals1);
    //     traversal(q, vals2);

    //     if(vals1.size() != vals2.size()) return false;

    //     for (int i = 0 ; i < vals1.size(); i++)
    //     {
    //         if(!vals1.get(i).equals(vals2.get(i)))
    //         {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    // public void traversal(TreeNode root, ArrayList<String> vals)
    // {
    //     if(root == null){
    //         vals.add("null");
    //         return;
    //     }

    //     vals.add(String.valueOf(root.val));

    //     traversal(root.left, vals);
    //     traversal(root.right, vals);
    // }

}
