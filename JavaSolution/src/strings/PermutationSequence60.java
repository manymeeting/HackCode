package strings;


import java.util.ArrayList;
import java.util.List;

/**
 * The set [1,2,3,...,n] contains a total of n! unique permutations.

 By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

 "123"
 "132"
 "213"
 "231"
 "312"
 "321"
 Given n and k, return the kth permutation sequence.

 Note:

 Given n will be between 1 and 9 inclusive.
 Given k will be between 1 and n! inclusive.
 Example 1:

 Input: n = 3, k = 3
 Output: "213"
 Example 2:

 Input: n = 4, k = 9
 Output: "2314"
 */

// 思路：相当于从最高位开始，每次固定一个数字，看右边数字的全排列个数x =（n-1的阶乘），
// 用k/x来得到当前位数字在所有数字里的index，然后k减去index * x，同时从number里把刚才用过的数字删掉，循环至n结束

public class PermutationSequence60 {
    public String getPermutation(int n, int k) {
        int[] fac = new int[n + 1];
        // calc factorial array
        fac[0] = 1;
        for (int i = 1; i < n; i++)
        {
            fac[i] = i * fac[i - 1];
        }

        // split numbers
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++)
        {
            numbers.add(i);
        }

        // 为了方便处理index
        k--;

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++)
        {
            int numIndex = k / fac[n-i];
            sb.append(String.valueOf(numbers.get(numIndex)));
            numbers.remove(numIndex);
            k -= fac[n-i] * numIndex;
        }


        return sb.toString();
    }
}
