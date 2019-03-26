package trees;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

 The encoded string should be as compact as possible.

 Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

 */

// 和普通的序列化不同之处：不需要特殊符号来记录null，利用BST的特性，在deserialize时依靠min和max来判断node是否有效，
// 一个用来避免声明global变量的trick：把一个int包裹成一个int[]，这样可以保证递归时更新同一个值

public class SerializeDeserializeBST449 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        serializeHelper(sb, root);
        return sb.deleteCharAt(sb.length()-1).toString();
    }

    private void serializeHelper(StringBuilder sb, TreeNode root) {
        if(root == null) return;
        sb.append(root.val);
        sb.append(",");
        serializeHelper(sb, root.left);
        serializeHelper(sb, root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0) return null;
        String[] dataArr = data.split(",");
        int[] pos = new int[1]; // Trick: 用array传引用的特点避免定义一个类变量
        return deserializeHelper(dataArr, pos, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }


    private TreeNode deserializeHelper(String[] data, int[] pos, int max, int min) {

        if(pos[0] < 0 || pos[0] >= data.length) {
            return null;
        }

        int val = Integer.parseInt(data[pos[0]]);
        if(val > max || val < min) {
            return null;
        }

        pos[0]++;
        TreeNode node = new TreeNode(val);
        node.left = deserializeHelper(data, pos, val, min);
        node.right = deserializeHelper(data, pos, max, val);

        return node;
    }
}


// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));