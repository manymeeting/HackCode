package datastruct;

import java.util.Random;

/**
 * Given an array of integers with possible duplicates,
 * randomly output the index of a given target number.
 * You can assume that the given target number must exist in the array.

 Note:
 The array size can be very large. Solution that uses too much extra space will not pass the judge.

 Example:

 int[] nums = new int[] {1,2,3,3,3};
 Solution solution = new Solution(nums);

 // pick(3) should return either index 2, 3, or 4 randomly.
 Each index should have equal probability of returning.
 solution.pick(3);

 // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 solution.pick(1);

 */

// 水塘抽样思想（Reservoir Sampling），
// 第一个match的index肯定先被选上，选上的index被之后的index替换的概率依次递减( if(rand.nextInt(++cnt) == 0) )
// 结果是每个index被选上的概率都是1/n

public class RandomPickIndex398 {

    int[] nums;
    Random rand;

    public RandomPickIndex398(int[] nums) {
        this.nums = nums;
        this.rand = new Random();

    }

    public int pick(int target) {
        int result = 0;
        int cnt = 0;

        for (int i = 0; i < nums.length; i++)
        {
            if(nums[i] != target) continue;
            if(rand.nextInt(++cnt) == 0)
            {
                result = i;
            }
        }

        return result;
    }

}


/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */