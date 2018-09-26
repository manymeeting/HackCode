package others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

 Example 1:
 Input: [4, 1, 8, 7]
 Output: True
 Explanation: (8-4) * (7-1) = 24
 Example 2:
 Input: [1, 2, 1, 2]
 Output: False
 Note:
 The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
 Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
 You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.

 */

// 用backtracking思想，每次拿出两个数，计算所有可能产生的结果，逐一放到list里进行递归，递归结束后删除，
// 用一个global变量来记录是否找到最终结果，需要注意double运算的eps，和从list中add/remove的顺序对应问题

public class The24Game679 {

    private boolean isPossible= false;

    public boolean judgePoint24(int[] nums) {

        List<Double> arr = new ArrayList<>();
        for (int num : nums) {
            arr.add((double) num);
        }
        backtracking(arr);
        return isPossible;
    }

    public void backtracking(List<Double> arr) {
        double eps = 0.001;

        if(isPossible) return; // already found

        if (arr.size() == 1) {
            if(Math.abs(arr.get(0) - 24.0) < eps) {
                isPossible = true;
            }
            return;
        }

        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < i; j++) {
                double a =  arr.get(i);
                double b  = arr.get(j);

                List<Double> resultsOfTwo = new ArrayList<>();
                resultsOfTwo.addAll(Arrays.asList(a+b, a-b, b-a, a*b));

                // 防止出现除0的情况
                if(Math.abs(a) > eps) resultsOfTwo.add(b/a);
                if(Math.abs(b) > eps) resultsOfTwo.add(a/b);

                // remove current two number before next recursion
                arr.remove(i);
                arr.remove(j);

                // 逐一尝试a和b的各种计算结果和其它数的组合
                for (double num : resultsOfTwo) {
                    arr.add(num);
                    backtracking(arr);
                    arr.remove(arr.size()-1);
                }

                // add current two number back (注意顺序要和remove顺序对称)
                arr.add(j, b);
                arr.add(i, a);
            }
        }

    }

}
