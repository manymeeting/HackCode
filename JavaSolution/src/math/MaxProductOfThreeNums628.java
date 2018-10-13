package math;

/**
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.

 Example 1:
 Input: [1,2,3]
 Output: 6
 Example 2:
 Input: [1,2,3,4]
 Output: 24
 Note:
 The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
 Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.

 */

// 找到前三大的和前两小的数，然后取Math.max(max1 * max2 * max3, max1 * min1 * min2)即可，
// 注意用if else的写法和顺序（max1代表最大的，min1代表最小的）
    
public class MaxProductOfThreeNums628 {
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : nums) {
            if(num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;

            }
            else if(num > max2) {
                max3 = max2;
                max2 = num;
            }
            else if(num > max3) {
                max3 = num;
            }

            if(num < min1) {
                min2 = min1;
                min1 = num;
            }
            else if(num < min2) {
                min2 = num;
            }


        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }
}
