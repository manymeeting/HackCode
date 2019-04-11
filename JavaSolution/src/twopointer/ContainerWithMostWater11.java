package twopointer;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

 Note: You may not slant the container and n is at least 2.

 Example:

 Input: [1,8,6,2,5,4,8,3,7]
 Output: 49
 */

// 用two pointer，对应的数当做两个板，每次移动较短的一侧，同时更新maxArea
public class ContainerWithMostWater11 {

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int maxArea = 0;
        while(left < right) {
            // 更新max面积
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            // 移动较小的一侧
            if(height[left] < height[right]) {
                left++;
            }
            else {
                right--;
            }
        }
        return maxArea;
    }
}
