package trees;

import java.util.List;

/**Given a n-ary tree, find its maximum depth.

 The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

 For example, given a 3-ary tree:





 We should return its max depth, which is 3.

 Note:

 The depth of the tree is at most 1000.
 The total number of nodes is at most 5000.

 * */

// 可以top-down也可以bottom-up，用dfs，每次迭代传一个当前depth，node不为空则+1，同时更新全局的maxDepth
public class MaxDepthOfNAryTree559 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    private int maxDeppth = 0;

    public int maxDepth(Node root) {
        dfs(root, 0);
        return maxDeppth;
    }

    private void dfs(Node root, int depth) {
        if(root == null) return;

        // add depth
        depth++;
        this.maxDeppth = Math.max(this.maxDeppth, depth);
        for (Node child : root.children) {
            dfs(child, depth);
        }
    }
}
