package binarysearch;

/**
 * A sorted list A contains 1, plus some number of primes.  Then, for every p < q in the list, we consider the fraction p/q.

 What is the K-th smallest fraction considered?  Return your answer as an array of ints, where answer[0] = p and answer[1] = q.

 Examples:
 Input: A = [1, 2, 3, 5], K = 3
 Output: [2, 5]
 Explanation:
 The fractions to be considered in sorted order are:
 1/5, 1/3, 2/5, 1/2, 3/5, 2/3.
 The third fraction is 2/5.

 Input: A = [1, 7], K = 1
 Output: [1, 7]
 Note:

 A will have length between 2 and 2000.
 Each A[i] will be between 1 and 30000.
 K will be between 1 and A.length * (A.length - 1) / 2.

 */

// 需要研究
// https://blog.csdn.net/magicbean2/article/details/79733065

public class KthSmallestPrimeFraction786 {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        double l = 0, r = 1; // 所有分数值都在0-1之间
        int p = 0, q = 1;


        int n = A.length;
        while(true) {
            int cnt = 0;
            p = 0;
            double m = l + (r - l) / 2;
            // using A[i] as the numerator, and A[n - 1 - j] denominator

            for (int i = 0, j = n - 2; i < n; i++) {
                // find the first A[n - 1 - j] that A[i] / A[n - 1 - j] <= mid
                while (j >= 0 && A[i] > m * A[n - 1 - j]) j--;

                cnt += (j + 1);

                // update p and q if A[i] / A[n - 1 - j] is larger than p / q
                if (j >= 0 && p * A[n - 1 - j] < q * A[i]) {
                    p = A[i];
                    q = A[n - 1 - j];
                }
            }

            if(cnt == K) {
                return new int[]{p, q};
            }
            else if(cnt < K) {
                l = m; // 不用加偏移量，因为值域是是连续的
            }
            else {
                r = m;
            }

        }


    }
}
