package twopointer; 
/**
 * Given an array nums of n integers and an integer target,
 * find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.

 Example:

 Given array nums = [-1, 2, 1, -4], and target = 1.

 The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */

import java.util.Arrays;
public class ThreeSumCloset16 {
   public int twoPointersSolution(int[] nums, int target) {

       // Sorting is essential for two pointer solution.
       Arrays.sort(nums);

       int cloestSum = Integer.MAX_VALUE;
       int minDistance = Integer.MAX_VALUE;
       boolean foundTarget = false;

       for (int i = 0; i < nums.length; i++) {
           int begin = i + 1;
           int end = nums.length - 1;

           while(begin < end) {
               int twoSum = nums[begin] + nums[end];
               if(twoSum == target - nums[i])
               {
                   cloestSum = target;
                   foundTarget = true;
                   break;
               }
               int possibleClosetTarget = twoSum + nums[i];
               int distanceFromTarget = Math.abs(target - possibleClosetTarget);
               if(distanceFromTarget < minDistance)
               {
                   minDistance = distanceFromTarget;
                   cloestSum = possibleClosetTarget;
               }

               if(twoSum + nums[i] < target)
               {
                   begin++;
               }
               else
               {
                   end--;
               }

           }

           if(foundTarget) break;
       }

       return cloestSum;
   }
}
