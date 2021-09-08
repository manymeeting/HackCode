package twopointer;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * Given an integer array nums and an integer k, return the number of good
 * subarrays of nums.
 * 
 * A good array is an array where the number of different integers in that array
 * is exactly k.
 * 
 * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3. A subarray is
 * a contiguous part of an array.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,1,2,3], k = 2 Output: 7 Explanation: Subarrays formed with
 * exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2],
 * [1,2,1,2] Example 2:
 * 
 * Input: nums = [1,2,1,3,4], k = 3 Output: 3 Explanation: Subarrays formed with
 * exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 * 
 * 
 */

// 思路：整体上是一个sliding window，但比较容易用sliding window实现的时atmostK，所以要转化一下，exactly(K) =
// atMost(K) - atMost(K-1)
public class SubarraysWithKDifferentIntegers992 {

    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostK(A, K) - atMostK(A, K - 1);
    }

    int atMostK(int[] A, int K) {
        int i = 0, res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        // 快指针：j，慢指针：i，满指针移动的条件由K和hashmap共同来判断。
        for (int j = 0; j < A.length; ++j) {
            if (count.getOrDefault(A[j], 0) == 0)
                K--;
            count.put(A[j], count.getOrDefault(A[j], 0) + 1);
            while (K < 0) {
                count.put(A[i], count.get(A[i]) - 1);
                if (count.get(A[i]) == 0)
                    K++;
                i++;
            }
            // 这里是重点，每次j往右移动一个，相当于给atmostK的result里添加了一堆新的subarray。
            // Everytime before the res += j - i + 1, you contract the range by
            // incrementing i until the invariant is satisfied that the range [i, j] has k
            // distinct elements. Now, how many new ranges does this new element A[j]
            // contribute? It contributes exactly the new ranges [i, j], [i+1, j], ... ,
            // [j-1, j], which amounts to j - i + 1 new ranges. You know those are valid
            // because each subranges in [i, j] has less than or equal to k distinct
            // elements by the invariant, and you know you are not double counting ranges
            // because they all involve the new element A[j].
            res += j - i + 1;
        }
        return res;
    }

}
