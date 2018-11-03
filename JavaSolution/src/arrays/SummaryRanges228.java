package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.

 Example 1:

 Input:  [0,1,2,4,5,7]
 Output: ["0->2","4->5","7"]
 Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
 Example 2:

 Input:  [0,2,3,4,6,8,9]
 Output: ["0","2->4","6","8->9"]
 Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 */


// 一遍循环（开头到倒数第二个数），维护两个数start和end，
// 每次遇到下一个数大于当前数+1时，就根据start和end的情况（是否相等）来更新结果集，注意最后要单独加上结尾的部分

public class SummaryRanges228 {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;


        int start = nums[0];
        int end = nums[0];
        if(nums.length == 1) {
            res.add(String.valueOf(start));
            return res;
        }
        for (int i = 0; i < nums.length-1; i++) {
            if(nums[i] + 1 == nums[i + 1]) {
                end = nums[i+1];
            }
            else {
                res.add(start == end ? String.valueOf(start) : start + "->" + end);
                start = nums[i+1];
                end = nums[i+1];
            }
        }

        // add last part
        res.add( start == end ? String.valueOf(start) : start + "->" + end);

        return res;
    }
}
