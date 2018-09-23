package graphs;

import java.util.*;

/**
 * For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

 Format
 The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

 You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

 Example 1 :

 Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

 0
 |
 1
 / \
 2   3

 Output: [1]
 Example 2 :

 Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

 0  1  2
 \ | /
 3
 |
 4
 |
 5

 Output: [3, 4]
 Note:

 According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
 The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 */

// 解法1：用每个点当root遍历一次，算出height，总共的复杂度是O(n^2);
// 解法2：bfs删除leave节点，直到剩余节点数<=2即为最终答案

public class MinimumHeightTrees310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(edges.length == 0) {
            return Collections.singletonList(0); //注意只有一个node的情况
        }

        List<Integer> leaves = new ArrayList<>();

        // 构建adjacent关系
        List<Set<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new HashSet<>());

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        for (int i = 0; i < adj.size(); i++) {
            if(adj.get(i).size()==1) leaves.add(i);
        }


        // 每次从n中减去当前leaves数量，每次while结束更换新的一批leaves（bfs思路）
        while(n > 2) {
            n -= leaves.size();

            List<Integer> newLeaves = new ArrayList<>();
            for (int leaf : leaves) {
                // 用Iterator来获取set中唯一的一个元素
                int leafParent = adj.get(leaf).iterator().next();
                adj.get(leafParent).remove(leaf);
                adj.get(leaf).remove(leafParent);

                if(adj.get(leafParent).size() == 1) newLeaves.add(leafParent);

            }
            leaves = newLeaves;
        }

        return leaves;
    }
}
