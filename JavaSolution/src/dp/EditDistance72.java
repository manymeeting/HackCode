package dp;

/**
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

 You have the following 3 operations permitted on a word:

 Insert a character
 Delete a character
 Replace a character
 Example 1:

 Input: word1 = "horse", word2 = "ros"
 Output: 3
 Explanation:
 horse -> rorse (replace 'h' with 'r')
 rorse -> rose (remove 'r')
 rose -> ros (remove 'e')
 Example 2:

 Input: word1 = "intention", word2 = "execution"
 Output: 5
 Explanation:
 intention -> inention (remove 't')
 inention -> enention (replace 'i' with 'e')
 enention -> exention (replace 'n' with 'x')
 exention -> exection (replace 'n' with 'c')
 exection -> execution (insert 'u')
 */

/**
 * DP问题
 *
 (i, j) = 1 + min { f(i, j - 1), f(i - 1, j), f(i - 1, j - 1) }

 f(i, j - 1) represents insert operation // 为何是i不变, j-1, 因为是在i前面insert了一个char来match原来j的char，原来的i现在相当于指向了和j下一位匹配的位置。
 f(i - 1, j) represents delete operation
 f(i - 1, j - 1) represents replace operation
 */
public class EditDistance72 {
    // f(i, j) :
    // minimum cost (or steps) required to convert first i characters of word1 to first j characters of word2

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] cost = new int[m+1][n+1];

        // Base case: f(0, k) = f(k, 0) = k
        for(int i = 0; i <= m; i++)
        {
            cost[i][0] = i;
        }
        for (int i = 0; i <= n; i++)
        {
            cost[0][i] = i;
        }


        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if(word1.charAt(i) == word2.charAt(j))
                {
                    cost[i+1][j+1] = cost[i][j];
                }
                else
                {
                    int a = cost[i][j];
                    int b = cost[i][j + 1];
                    int c = cost[i + 1][j];
                    // 1 + MIN(a, b, c)
                    cost[i + 1][j + 1] = a < b ? ( a < c ? a : c): (b < c ? b : c);
                    cost[i + 1][j + 1]++;
                }
            }
        }
        return cost[m][n];
    }

}
