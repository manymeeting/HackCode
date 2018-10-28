package math;

/**
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.

 Example 1:

 Input: 12
 Output: 21


 Example 2:

 Input: 21
 Output: -1

 */

// 参考next permutation思路即可

public class NextGreaterElementIII556 {
    public int nextGreaterElement(int n) {
        String numStr = String.valueOf(n);
        char[] charArr = numStr.toCharArray();
        int[] digitArr = new int[charArr.length];
        for (int i = 0; i < digitArr.length; i++) {
            digitArr[i] = charArr[i] - '0';
        }

        int first = digitArr.length - 2;
        int second = digitArr.length - 1;
        while(first >= 0) {
            if(digitArr[first] < digitArr[second]) {
                break;
            }
            first--;
            second--;
        }
        if(first < 0) {
            return -1;
        }

        int toSwapIdx = digitArr.length - 1;
        while(digitArr[toSwapIdx] <= digitArr[first]) {
            toSwapIdx--;
        }
        swap(toSwapIdx, first, digitArr);
        reverseFrom(second, digitArr);

        String res = "";
        for (int digit : digitArr) {
            res += digit;
        }
        try {
            return Integer.parseInt(res); // 有可能超过int32范围
        }
        catch (Exception e) {
            return -1;
        }

    }

    private void swap(int a, int b, int[] nums) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    private void reverseFrom(int start, int[] nums) {
        int end = nums.length - 1;
        while(start < end) {
            swap(start, end, nums);
            start++;
            end--;
        }
    }

}
