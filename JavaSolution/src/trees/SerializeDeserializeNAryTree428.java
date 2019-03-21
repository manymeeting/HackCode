package trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

 For example, you may serialize the following 3-ary tree







 as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.



 Note:

 N is in the range of [1, 1000]
 Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */

// preOrder遍历，用#来表示null，同时每个node后加一个数表示有几个children（如果没有children就加上#）

public class SerializeDeserializeNAryTree428 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        List<String> list = new ArrayList<>();
        serializeHelper(root, list);
        StringBuilder sb = new StringBuilder();
        for (String str: list) {
            sb.append(str);
            sb.append(",");
        }
        if(sb.length() > 0) sb.deleteCharAt(sb.length()-1); // Delete last ","

        return sb.toString();
    }

    private void serializeHelper(Node root, List<String> list) {
        if(root == null) {
            list.add("#");
        }
        else {
            list.add(String.valueOf(root.val));
            int size = root.children.size();
            if(root.children.size() > 0) {
                list.add(String.valueOf(size));
                for (Node child : root.children) {
                    serializeHelper(child, list);
                }
            }
            else {
                list.add("#");
            }
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        ArrayDeque<String> queue = new ArrayDeque<>();
        String[] split = data.split(",");
        for (String str : split) queue.add(str);

        return deserializeHelper(queue);

    }

    private Node deserializeHelper(ArrayDeque<String> queue) {
        String s = queue.poll();
        if(s.equals("#")) {
            return null;
        }
        // children个数，可能是#或者是数字
        String numStr = queue.poll();


        Node root = new Node(Integer.valueOf(s), new ArrayList<>());
        if(numStr.equals("#")) {
            return root;
        }
        else {
            int num = Integer.valueOf(numStr);
            for (int i = 0; i < num; i++) {
                root.children.add(deserializeHelper(queue));
            }
        }

        return root;
    }
}
