package dp;

/**
 * There is a fence with n posts, each post can be painted with one of the k colors.

 You have to paint all the posts such that no more than two adjacent fence posts have the same color.

 Return the total number of ways you can paint the fence.

 Note:
 n and k are non-negative integers.

 Example:

 Input: n = 3, k = 2
 Output: 6
 Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

 post1  post2  post3
 -----      -----  -----  -----
 1         c1     c1     c2
 2         c1     c2     c1
 3         c1     c2     c2
 4         c2     c1     c1
 5         c2     c1     c2
 6         c2     c2     c1

 */

//思路: 第n位的paint可能数量 Rn = (k-1) * (Rn-1 + Rn-2)
//因为对n>=3来说有两种情况，1.和n-1不一样，2.和n-1一样且和n-2不一样

public class PaintFence276 {
    public int numWays(int n, int k) {
        if((n == 0 || k == 0) || (n >=3 && k ==1))
        {
            return 0;
        }

        int r1, r2 = 0;
        r1 = k;
        r2 = k * (k-1) + k;

        if(n == 1) return r1;
        if(n == 2) return r2;
        int res = 0;

        for (int i = 2; i < n; i++)
        {
            res = r2 * (k-1) + r1 * (k - 1);
            r1 = r2;
            r2 = res;
        }

        return res;
    }

}
