package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

 Find all the elements of [1, n] inclusive that do not appear in this array.

 Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

 Example:

 Input:
 [4,3,2,7,8,2,3,1]

 Output:
 [5,6]


 */

// loop两遍，第一遍把nums[i] - 1对应的数设为负，第二遍看还剩下哪些正数，就直接加到result里

public class FindAllNumDisaprdInArr448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1; // 有可能已经被set为负，但要根据正数值来算idx
            if(nums[idx] > 0) {
                nums[idx] = -nums[idx];
            }

        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                res.add(i+1);
            }
        }

        return res;
    }
}
