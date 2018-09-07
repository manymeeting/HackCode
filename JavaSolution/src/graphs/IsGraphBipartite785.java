package graphs;

import java.util.Arrays;

/**
 * Given an undirected graph, return true if and only if it is bipartite.

 Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets
 A and B such that every edge in the graph has one node in A and another node in B.

 The graph is given in the following form: graph[i] is a list of indexes j for which the edge between
 nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.
 There are no self edges or parallel edges: graph[i] does not contain i,
 and it doesn't contain any element twice.

 Example 1:
 Input: [[1,3], [0,2], [1,3], [0,2]]
 Output: true
 Explanation:
 The graph looks like this:
 0----1
 |    |
 |    |
 3----2
 We can divide the vertices into two groups: {0, 2} and {1, 3}.
 Example 2:
 Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 Output: false
 Explanation:
 The graph looks like this:
 0----1
 | \  |
 |  \ |
 3----2
 We cannot find a way to divide the set of nodes into two independent subsets.

 Note:

 graph will have length in range [1, 100].
 graph[i] will contain integers in range [0, graph.length - 1].
 graph[i] will not contain i or duplicate values.
 The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 */

// 用两种颜色（1和0）来"涂"每个节点，dfs遍历所有相邻节点，看是否能满足能涂上另一个颜色且在整个过程中不冲突，
// 注意为了遍历isolated节点，要用for循环遍历原始graph的节点

public class IsGraphBipartite785 {
    public boolean isBipartite(int[][] graph) {

        int n = graph.length;
        int[] colors = new int[n];

        Arrays.fill(colors, -1);

        //This graph might be a disconnected graph. So check each unvisited node.
        for (int i = 0; i < n; i++)
        {
            if(colors[i] == -1 && !dfs(graph, colors, 0, i))
            {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int[] colors, int color, int node)
    {
        if(colors[node] != -1) // 如果已经有颜色，看是不是和给的颜色match
        {
            return colors[node] == color;
        }

        // 没颜色，set一个然后遍历相连的节点
        colors[node] = color;
        for (int next: graph[node])
        {
            if(!dfs(graph, colors, 1-color, next))
            {
                return false;
            }
        }

        return true;
    }


}
