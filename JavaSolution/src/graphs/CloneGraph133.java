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
    class UndirectedGraphNode {
        List<UndirectedGraphNode> neighbors;
        int label;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    };

    private HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return clone(node);
    }

    public UndirectedGraphNode clone(UndirectedGraphNode node)
    {
        if(node == null) return null;


        if(map.containsKey(node.label))
        {
            return map.get(node.label);
        }

        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        map.put(node.label, copy);

        for (UndirectedGraphNode neighbor : node.neighbors)
        {
            copy.neighbors.add(clone(neighbor));
        }

        return copy;
    }


}
