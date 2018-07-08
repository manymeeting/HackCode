/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

 Example:

 Input: 3
 Output: 5
 Explanation:
 Given n = 3, there are a total of 5 unique BST's:

 1         3     3      2      1
 \       /     /      / \      \
 3     2     1      1   3      2
 /     /       \                 \
 2     1         2                 3

 */
public class UniqueBinarySearchTrees96 {
    /** 动态规划，推导关系如下
     * G(n): the number of unique BST for a sequence of length n.
     * F(i, n), 1 <= i <= n: the number of unique BST,
     *  where the number i is the root of BST, and the sequence ranges from 1 to n.
     *
     * G(n) = F(1, n) + F(2, n) + ... + F(n, n).
     * G(0)=1, G(1)=1.
     *
     * The tricky part is that we could consider the number of unique BST out of sequence [1,2] as G(2),
     * and the number of of unique BST out of sequence [4, 5, 6, 7] as G(4). Therefore, F(3,7) = G(2) * G(4).
     *
     * Therefore:
     * F(i, n) = G(i-1) * G(n-i)	1 <= i <= n
     *
     * Combine above formulas:
     * G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0)
     */

    public int numTrees(int n) {
        int[] G = new int[n+1];

        G[0] = G[1] = 1;

        for(int i = 2; i <= n; i++)
        {
            for(int j = 1; j <= i; j++)
            {
                /**
                 * Use dp concept here to store previous result
                 */
                G[i] += G[j-1] * G[i-j];
                // g2 = g0 * g1
                // g2 += g1 * g0
            }
        }

        return G[n];
    }

}
