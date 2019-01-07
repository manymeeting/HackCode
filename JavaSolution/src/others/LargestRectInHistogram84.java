package others;

import java.util.ArrayDeque;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.




 Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].




 The largest rectangle is shown in the shaded area, which has area = 10 unit.



 Example:

 Input: [2,1,5,6,2,3]
 Output: 10

 */

// REF: https://siddontang.gitbooks.io/leetcode-solution/content/array/largest_rectangle_in_histogram.html
// 观察分析可发现：可以遍历每个bar，计算以当前bar为peak时最大矩形的面积，不断更新max即可。

// 用一个stack来记录index，如果当前bar的高度小于栈顶index对应的bar的高度，则开始pop直到栈空或栈顶小于当前bar的高度，否则push。
// pop时（计算以当前bar为右边界，栈顶为peak的矩形的面积），以pop出的index位置为peak，左边界为pop之后的栈顶（表示左边第一个比peak低的位置），
// 右边界为当前遍历达到的index（只有push之后才向右移动），注意stack为空时的判断。
//
// 另外需要先在尾部加入一个0的height以便处理最右端的bar。


public class LargestRectInHistogram84 {
    public int largestRectangleArea(int[] heights) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int len = heights.length;

        // 在heights最后插入一个0，为了处理最后一个bar
        int[] newHeights = new int[heights.length + 1];
        newHeights[heights.length] = 0;
        for (int j = 0; j < len; j++)  {
            newHeights[j] = heights[j];
        }

        heights = newHeights;

        int i = 0;
        while(i < heights.length) {

            if(stack.isEmpty() || heights[i] > heights[stack.peek()]) {
                stack.push(i);
                i++;
            }
            else {
                int peakIdx = stack.peek();
                stack.pop();

                int rightBound = i;
                maxArea = Math.max(maxArea,
                        heights[peakIdx] * (stack.isEmpty() ? i : rightBound - stack.peek() - 1));
            }
        }
        return maxArea;
    }
}
