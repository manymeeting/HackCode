package trees;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Given an n-ary tree, return the postorder traversal of its nodes' values.


 For example, given a 3-ary tree:

 Return its postorder traversal as: [5,6,3,2,4,1].


 Note: Recursive solution is trivial, could you do it iteratively?
 */

// 递归写法很常规，注意循环的写法，用stack后，顺序在最后要reverse过来
    
public class NAryTreePostorderTraversal590 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
    private void dfs(Node root, List<Integer> res)
    {
        if(root == null) return;
        for (Node child : root.children)
        {
            dfs(child, res);
        }

        res.add(root.val);
    }

    public void iterative(Node root, List<Integer> res)
    {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        stack.add(root);

        while(!stack.isEmpty())
        {
            Node node = stack.pop();
            res.add(node.val);
            for (Node child : node.children)
            {
                stack.push(child);
            }
        }

        Collections.reverse(res);
    }


}
