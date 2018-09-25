package graphs;

/**
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

 Example 1:

 Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 Output: true
 Example 2:

 Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 Output: false
 Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.*/

// 并查集的应用，通过并查集来check图中是否有环，
// 如果没有再在最后check是否满足边数 == 点数-1，不满足说明图中有不连通的点

public class GraphValidTree261 {


    public boolean validTree(int n, int[][] edges) {
        int[] connection = new int[n];
        for (int i = 0; i < n; i++) connection[i] = i;

        for (int i = 0; i < edges.length; i++) {
            int xRoot = find(connection, edges[i][0]);
            int yRoot = find(connection, edges[i][1]);

            if(xRoot == yRoot) return false;

            union(connection, xRoot, yRoot);
        }

        // 在最后检查是否有不连接的情况（边数 != 点数-1），
        // 有环的情况在上面已经排除了
        return edges.length == n - 1;
    }

    private int find(int[] connection, int x){
        while(x != connection[x]) {
            x = connection[x];
        }
        return x;
    }


    private void union(int[] connection, int x, int y) {
        int xRoot = find(connection, x);
        int yRoot = find(connection, y);
        if(xRoot == yRoot) return;

        connection[xRoot] = yRoot;
    }

}
