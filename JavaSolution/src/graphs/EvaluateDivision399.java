package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

 Example:
 Given a / b = 2.0, b / c = 3.0.
 queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 return [6.0, 0.5, -1.0, 1.0, -1.0 ].

 The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

 According to the example above:

 equations = [ ["a", "b"], ["b", "c"] ],
 values = [2.0, 3.0],
 queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 */


// https://www.jianshu.com/p/bb5f426006ac
// Union Find，每个节点存一个val，每次union两个节点时通过当前的比列更新val，
// 最后直接对两个node的val相除即可得到结果

public class EvaluateDivision399 {
    class Node {
        Node parent;
        List<Node> children;
        double val;

        public Node() {
            this.val = 1d;
            children = new ArrayList<>();
        }
    }
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // 用来记录node出现情况

        Map<String, Node> graph = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            double val = values[i];
            String a = equations[i][0];
            String b = equations[i][1];

            if(!graph.containsKey(a)) {
                graph.put(a, new Node());
            }
            if(!graph.containsKey(b)) {
                graph.put(b, new Node());
            }

            // Union two node
            union(graph.get(a), graph.get(b), val);
        }

        // Build result
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String a = queries[i][0];
            String b = queries[i][1];
            // Check if two nodes exist or connected
            if(!graph.containsKey(a) || !graph.containsKey(b)
                    || find(graph.get(a)) != find(graph.get(b)) ) {
                res[i] = -1;
            }
            else {
                // Calculate result
                res[i] = graph.get(a).val / graph.get(b).val;
            }
        }

        return res;
    }

    private Node find(Node node) {
        if(node.parent == null) {
            return node;
        }
        return find(node.parent);
    }

    private void union(Node node1, Node node2, double val) {

        Node root1 = find(node1);
        Node root2 = find(node2);
        if(root1 == root2) return;

        // Merge smaller set to bigger set
        if(root1.children.size() <= root2.children.size()) {
            mergeSet(node1, node2, val);
        }
        else {
            mergeSet(node2, node1, 1d / val);
        }
    }

    // 压缩路径长度，简化最终结果的运算
    // Merge node1 set to node2 set
    private void mergeSet(Node node1, Node node2, double val) {
        Node root1 = find(node1);
        Node root2 = find(node2);
        double ratio = node2.val / node1.val * val;

        for (Node child : root1.children) {
            child.val *= ratio;
            child.parent = root2;
            root2.children.add(child);
        }
        // 不要忘了root1本身
        root1.val = ratio;
        root1.parent = root2;
        root2.children.add(root1);
        root1.children.clear();

    }
}
