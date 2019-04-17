package trees;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.


 */


// 采用pre-order遍历来序列化，null记录为X，用逗号分隔，
// 反序列化时把str按逗号重新切割成的list放在一个queue里，每次poll一个出来，递归构建tree结构

public class SerializeDeserializeBinaryTree297 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    private static String NL = "X"; // for null node
    private static String DELIMITER = ",";


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if(node == null) {
            sb.append(NL).append(DELIMITER);
            return;
        }
        sb.append(node.val).append(DELIMITER);
        buildString(node.left, sb);
        buildString(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> queue = new ArrayDeque<>();
        queue.addAll(Arrays.asList(data.split(DELIMITER)));

        TreeNode root = buildTree(queue);
        return root;
    }

    private TreeNode buildTree(Deque<String> queue) {
        if(queue.isEmpty()) return null;

        String val = queue.poll();
        if(val.equals(NL)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = buildTree(queue);
        node.right = buildTree(queue);

        return node;
    }
}
