package arrays;

/**
 * Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

 The returned array must be in sorted order.

 Expected time complexity: O(n)

 Example 1:

 Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
 Output: [3,9,15,33]
 Example 2:

 Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
 Output: [-23,-5,1,7]

 */

// 利用2次曲线的性质，有两种情况，
// 1.a>0, two ends in original array are bigger than center if you learned middle school math before.
// 2.a<0, center is bigger than two ends.

public class SortTransformedArray360 {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int len = nums.length;
        int[] sorted = new int[len];

        int i = 0, j = len-1;
        int index = a >= 0 ? len - 1 : 0; // where to start fill the result array

        while(i <= j)
        {
            if(a >= 0)
            {
                // index从大的开始--
                sorted[index--] = quad(nums[i], a, b, c) >= quad(nums[j], a, b, c) ? quad(nums[i++], a, b, c) : quad(nums[j--], a, b, c);
            }
            else
            {
                sorted[index++] = quad(nums[i], a, b, c) >= quad(nums[j], a, b, c) ? quad(nums[j--], a, b, c) : quad(nums[i++], a, b, c);
            }
        }

        return sorted;


    }

    private int quad(int x, int a, int b, int c)
    {
        return a * x * x + b * x + c;
    }

}
