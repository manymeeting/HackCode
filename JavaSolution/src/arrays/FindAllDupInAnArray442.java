package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

 Find all the elements that appear twice in this array.

 Could you do it without extra space and in O(n) runtime?

 Example:
 Input:
 [4,3,2,7,8,2,3,1]

 Output:
 [2,3]
 */

// 根据条件限制，可以采取把数字放到对应位置的策略，遇到重复就加到result里，
// 注意遇到重复后要mark一下（设为0）来避免多次加到result里

public class FindAllDupInAnArray442 {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            while (nums[i] != i+1 && nums[i] != 0) {
                int targetIdx = nums[i] - 1;
                if(nums[targetIdx] == targetIdx+1) {
                    res.add(nums[targetIdx]);
                    nums[i] = 0;
                    break;
                }
                else {
                    swap(nums, i, targetIdx);
                }
            }
        }

        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
