package hashtable;

import java.util.*;

/**
 * 
 * You are given an array nums consisting of positive integers.

You have to take each integer in the array, reverse its digits, and add it to the end of the array. You should apply this operation to the original integers in nums.

Return the number of distinct integers in the final array.

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 106
 */

public class CntDistinctIntAfterReverseOperations2442 {
    public int countDistinctIntegers(int[] nums) {
        HashSet<Integer> st = new HashSet<>();

        for (int i : nums)  {
            st.add(i);
            st.add(reverseInt(i));
        }
        return st.size();
    }

    private int reverseInt(int x) {
        int rev = 0;
        while (x > 0) {
            rev = rev * 10 + (x % 10);
            x = x / 10; 
        }
        return rev;
    }
    
}
