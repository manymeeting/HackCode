package arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

 Note:

 The number of elements initialized in nums1 and nums2 are m and n respectively.
 You may assume that nums1 has enough space (size that is greater or equal to m + n)
 to hold additional elements from nums2.
 Example:

 Input:
 nums1 = [1,2,3,0,0,0], m = 3
 nums2 = [2,5,6],       n = 3

 Output: [1,2,2,3,5,6]

 **/

// 利用已知的边界index，从nums1尾部开始（根据题意，nums1尾部是空，如果从头部开始会覆盖已有元素），
// 一个个从后往前比较nums1和nums2元素，放到nums1里，最后把nums2剩余元素全放到头部即可
public class MergeSortedArray88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n- 1;
        int k = m + n - 1;

        while(i >= 0 && j >= 0)
        {
            nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }

        // put rest num2 element to the head
        while(j >= 0)
        {
            nums1[k--] = nums2[j--];
        }

    }

}
