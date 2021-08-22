package trees;

/**
 * You are given a tree with n nodes numbered from 0 to n - 1 in the form of a
 * parent array parent where parent[i] is the parent of ith node. The root of
 * the tree is node 0. Find the kth ancestor of a given node.
 * 
 * The kth ancestor of a tree node is the kth node in the path from that node to
 * the root node.
 * 
 * Implement the TreeAncestor class:
 * 
 * TreeAncestor(int n, int[] parent) Initializes the object with the number of
 * nodes in the tree and the parent array. int getKthAncestor(int node, int k)
 * return the kth ancestor of the given node node. If there is no such ancestor,
 * return -1.
 */
public class KthAncestorOfATreeNode1483 {

    /**
     * 技巧-Binary Lifting，用二维数组记录preprocess后的node跳转mapping来加速查询。注意构建mapping时的2^ith's
     * ancestor跳转迭代的思想。
     */
    int[][] mapping;
    int maxPower;

    // Build the binary lifting map: O(nlogn)
    public KthAncestorOfATreeNode1483(int n, int[] parent) {
        // log_e change to base 2
        this.maxPower = (int) (Math.log(n) / Math.log(2)) + 1;
        // Init mapping
        this.mapping = new int[maxPower][n];
        mapping[0] = parent;
        // Build mapping by transition
        for (int i = 1; i < maxPower; i++) {
            for (int j = 0; j < n; j++) {
                // j-th node's 2^(i-1)th ancestor
                int pre_ancestor = mapping[i - 1][j];
                if (pre_ancestor == -1) {
                    // No more ancestors in 1/2 step
                    mapping[i][j] = -1;
                } else {
                    // j-th node's 2^(i-1)th ancestor's 2^(i-1)th ancestor.
                    mapping[i][j] = mapping[i - 1][pre_ancestor];
                }
            }
        }
    }

    // Do search: O(logn)
    public int getKthAncestor(int node, int k) {
        int curPower = this.maxPower; // base 2
        while (k > 0 && node >= 0) {
            // Try step from 2^(maxPower), 2^(maxPower-1) ...
            if (k >= (1 << curPower)) {
                node = mapping[curPower][node];
                k -= (1 << curPower);
            } else {
                // Take smaller step
                curPower -= 1;
            }
        }
        return node;
    }
}
