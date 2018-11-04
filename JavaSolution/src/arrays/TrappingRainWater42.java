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

// 2-pass构造两个数组，分别存从左到右每个位置左边的max和从右到左每个位置右边的max，
// 最后遍历原数组，每个位置取左右max的最小值和当前高度相减，如果是正数则累加

// followup：如果有漏水的位置，把对应位置的左右任一个max设为0即可

public class TrappingRainWater42 {

    // 2-pass方法（优点：可以方便处理漏水的followup）
    public int trap(int[] height) {
        int len = height.length;
        if(len == 0) return 0;
        int[] leftMaxArr = new int[len];
        int[] rightMaxArr = new int[len];

        leftMaxArr[0] = height[0];
        rightMaxArr[len-1] = height[len-1];

        for (int i = 1; i < len; i++) {
            leftMaxArr[i] = Math.max(leftMaxArr[i-1], height[i]);
        }
        for (int i = len-2; i >= 0; i--) {
            rightMaxArr[i] = Math.max(rightMaxArr[i+1], height[i]);
        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            int diff = Math.min(leftMaxArr[i], rightMaxArr[i]) - height[i];
            res += diff > 0 ? diff : 0;
        }

        return res;
    }



    /* 双指针solution：while循环遍历，可以想象成两个pointer是容器的两个边，每次按短边一侧的max来计算容量，

    public int trap(int[] height) {
        int a = 0;
        int b = len - 1;

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
    */

}
