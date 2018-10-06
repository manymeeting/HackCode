package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

 The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

 Example:
 Input: [[1,2], [3], [3], []]
 Output: [[0,1,3],[0,2,3]]
 Explanation: The graph looks like this:
 0--->1
 |    |
 v    v
 2--->3
 There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 Note:

 The number of nodes in the graph will be in the range [2, 15].
 You can print different paths in any order, but you should keep the order of nodes inside one path.
 */

// dfs+backtracking遍历图，注意一开始放0节点到templist里

public class AllPathsFromSrcToTarget797 {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        List<List<Integer>> res = new ArrayList<>();

        // 放节点0到templist里作为初始值
        List<Integer> tempList = new ArrayList<>();
        tempList.add(0);

        dfs(res, graph, tempList, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res,
                     int[][] graph, List<Integer> tempList, int currNode) {

        if(currNode == graph.length) {
            res.add(new ArrayList<>(tempList));
            return;
        }

        if(graph[currNode].length == 0) return; // 没相邻节点

        for (int i = 0; i < graph[currNode].length; i++) {
            int neighbor = graph[currNode][i];

            tempList.add(neighbor);
            dfs(res, graph, tempList, neighbor);
            tempList.remove(tempList.size()-1);
        }

    }
}
