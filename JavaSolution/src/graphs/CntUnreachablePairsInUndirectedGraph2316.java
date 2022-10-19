package graphs;

import java.util.*;

/**
 * 
 * You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.

Return the number of pairs of different nodes that are unreachable from each other.


Constraints:

1 <= n <= 105
0 <= edges.length <= 2 * 10^5
edges[i].length == 2
0 <= ai, bi < n
ai != bi
There are no repeated edges.


思路：用union find找出分隔的graph数量，然后用一个graph的node总数乘以其余graph的node数之和来计算pair数量。
 */
public class CntUnreachablePairsInUndirectedGraph2316 {
    int parents[];

    private void union(int u, int v) {
        int parentU = find(u);
        int parentV = find(v);
        parents[parentU] = parentV;
    }

    private int find(int x) {
        while(x != parents[x]) {
            x = parents[x];
        }
        return x;
    }

    public long countPairs(int n, int[][] edges) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }

        Map<Integer, Integer> connectedGraphRootToSize = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(i);
            connectedGraphRootToSize.put(root, connectedGraphRootToSize.getOrDefault(root, 0) + 1);
        }

        // Calculate the number of pairs
        long res = 0;
        for (int size : connectedGraphRootToSize.values()) {
            res += size * (n - size);
        }
        
        // We counted each pair twice.
        return res / 2;
    }
}
