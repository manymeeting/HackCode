package backtracking;

/**
 * Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:

 The number at the ith position is divisible by i.
 i is divisible by the number at the ith position.
 Now given N, how many beautiful arrangements can you construct?

 Example 1:
 Input: 2
 Output: 2
 Explanation:

 The first beautiful arrangement is [1, 2]:

 Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).

 Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

 The second beautiful arrangement is [2, 1]:

 Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).

 Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 Note:
 N is a positive integer and will not exceed 15.
 */

// 用backtracking尝试每一种序列组合，

// 模式：维护一个position代表满足条件的位数，每次只有在满足某个条件时才继续尝试下一位，当position超过临界值时加入结果集

public class BeautifulArrangement526 {
    private int count = 0;

    public int countArrangement(int N) {
        boolean[] used = new boolean[N + 1];
        helper(N, 1, used);

        return count;
    }

    private void helper(int N, int pos, boolean[] used) {
        if (pos > N) {
            // 找到一个每个element都满足条件的序列
            count++;
            return;
        }

        for (int i = 1; i <= N; i++) {
            if(used[i]) continue;

            if(i % pos == 0 || pos % i == 0) {
                used[i] = true;
                helper(N, pos + 1, used);
                used[i] = false;
            }
        }
    }
}
