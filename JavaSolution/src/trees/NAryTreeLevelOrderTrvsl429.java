package trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

 For example, given a 3-ary tree:







 We should return its level order traversal:

 [
 [1],
 [3,2,4],
 [5,6]
 ]


 Note:

 The depth of the tree is at most 1000.
 The total number of nodes is at most 5000.

 */

// 典型的bfs两层while遍历
    
public class NAryTreeLevelOrderTrvsl429 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(root);


        while (!q.isEmpty()) {
            List<Integer> currList = new ArrayList<>();
            int currSize = q.size();

            while(currSize > 0) {
                Node curr = q.poll();
                currSize--;
                currList.add(curr.val);

                for (Node child : curr.children){
                    q.add(child);
                }

            }
            res.add(currList);
        }

        return res;
    }
}
