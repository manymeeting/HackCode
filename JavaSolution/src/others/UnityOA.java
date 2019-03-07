package others;

import java.util.HashMap;
import java.util.Map;

public class UnityOA {

    // 给一个array，把这个arr分成几个slice，
    // 要求在每段排序后把，所有段拼接起来可以让原array从小到大排列，问最大的slice个数

    public int question4(int[] nums) {
        int numOfSlice= 0;
        Map<Integer, Integer> maxBefore = new HashMap<>();
        Map<Integer, Integer> minAfter = new HashMap<>();


        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if(i == 0) {
                maxBefore.put(0, nums[0]);
            }
            else {
                maxBefore.put(i, Math.max(num, maxBefore.get(i-1)));

            }

        }

        for (int i = nums.length-1; i >= 0 ; i--) {
            int num = nums[i];
            if(i == nums.length - 1) {
                minAfter.put(nums.length-1, nums[nums.length-1]);
            }
            else {
                minAfter.put(i, Math.min(num, minAfter.get(i+1)));

            }
        }

        for (int i = 0; i < nums.length; i++) {
            if(maxBefore.get(i) <= minAfter.get(i)) {
                numOfSlice++;
            }
        }

        return numOfSlice;
    }
}
