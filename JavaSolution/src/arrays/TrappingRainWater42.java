package arrays;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.


 The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 In this case, 6 units of rain water (blue section) are being trapped.

 Example:
 Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 Output: 6

 */

// 2pointer，while循环遍历，可以想象成两个pointer是容器的两个边，每次按短边一侧的max来计算容量
public class TrappingRainWater42 {
    public int trap(int[] height) {
        int a = 0;
        int b = height.length - 1;

        int maxWater = 0;
        int leftMax = 0;
        int rightMax = 0;

        while(a<=b)
        {
            leftMax = Math.max(leftMax, height[a]);
            rightMax = Math.max(rightMax, height[b]);

            if(leftMax < rightMax)
            {
                maxWater += (leftMax - height[a]);
                a++;
            }
            else
            {
                maxWater += (rightMax - height[b]);
                b--;
            }
        }
        return maxWater;
    }
}
