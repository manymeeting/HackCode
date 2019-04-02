package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


 OJ's undirected graph serialization:
 Nodes are labeled uniquely.

 We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 As an example, consider the serialized graph {0,1,2#1,2#2,2}.

 The graph has a total of three nodes, and therefore contains three parts as separated by #.

 First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 Second node is labeled as 1. Connect node 1 to node 2.
 Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 Visually, the graph looks like the following:

 1
 / \
 /   \
 0 --- 2
 / \
 \_/
 */

// DFS遍历，用一个map来存copy过的每一个节点，因为这种graph会有循环引用的情况，要确保每个节点不被重复new
public class CloneGraph133 {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    };

    private HashMap<Integer, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        return clone(node);
    }

    public Node clone(Node node)
    {
        if(node == null) return null;


        if(map.containsKey(node.val))
        {
            return map.get(node.val);
        }

        Node copy = new Node(node.val, new ArrayList<>());
        map.put(node.val, copy);

        for (Node neighbor : node.neighbors)
        {
            copy.neighbors.add(clone(neighbor));
        }

        return copy;
    }


}
