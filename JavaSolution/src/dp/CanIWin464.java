package dp;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

 What if we change the game so that players cannot re-use integers?

 For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

 Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

 You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

 Example

 Input:
 maxChoosableInteger = 10
 desiredTotal = 11

 Output:
 false

 Explanation:
 No matter which integer the first player choose, the first player will lose.
 The first player can choose an integer from 1 up to 10.
 If the first player choose 1, the second player can only choose integers from 2 up to 10.
 The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 Same with other integers chosen by the first player, the second player will always win.

 */

// 用dfs + memory思想，对每一个已选择数字的组合boolean[]，存储以此为开端的胜负结果，
// 注意将boolean[]转换为bit表示的int然后作为map中key的技巧

public class CanIWin464 {

    private Map<Integer, Boolean> map;
    private boolean[] used;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) return true; // Corner case

        if((1+maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }

        map = new HashMap<>();
        used = new boolean[maxChoosableInteger+1]; // 因为能选择的数是从1开始
        return helper(desiredTotal);

    }

    private boolean helper(int target) {
        if(target <= 0) return false; // 对方选择以后target已经<=0，说明对方已赢

        int key = bitFormat(used);
        if(!map.containsKey(key)) {
            // 逐一选择剩余的number
            for (int i = 1; i < used.length; i++) {
                if(!used[i]) {
                    used[i] = true;
                    if (!helper(target - i)) {
                        map.put(key, true);
                        used[i] = false;
                        return true;
                    }
                    used[i] = false;
                }

            }

            // 都不能赢
            map.put(key, false);
        }
        return map.get(key);
    }

    // 将boolean array（根据题意长度不超过20）转换为bit表示的int
    private int bitFormat(boolean[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]) res |= 1;
            res = res << 1;
        }

        return res;
    }

}
