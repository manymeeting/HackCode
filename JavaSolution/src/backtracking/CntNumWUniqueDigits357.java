package backtracking;

/**
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

 Example:

 Input: 2
 Output: 91
 Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
 excluding 11,22,33,44,55,66,77,88,99
 */

// 可以用backtracking来遍历同时计数，还有一种dp的解法

public class CntNumWUniqueDigits357 {

    public int countNumbersWithUniqueDigits(int n) {
        int count = 1;

        boolean[] used = new boolean[10];
        long max = (long) Math.pow(10, n);
        // 因为0不能作为最高位，所以在helper之外先单独写一个loop来选择最高位
        for (int i = 1; i < 10; i++) {
            used[i] = true;
            count += countHelper(i, max, used);
            used[i] = false;
        }

        return count;

    }


    // 每次向后append一个没用过的digit，cnt+1
    private int countHelper(long num, long max, boolean[] used) {
        int cnt = 0;
        if(num < max) {
            cnt+=1;
        }
        else {
            return cnt;
        }

        for (int i = 0; i < 10; i++) {
            if(!used[i]) {
                used[i] = true;
                cnt += countHelper(num * 10 + i, max, used);
                used[i] = false;
            }
        }

        return cnt;

    }
}
