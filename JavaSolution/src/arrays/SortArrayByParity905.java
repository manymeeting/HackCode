package arrays;

/**
 * Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.

 You may return any answer array that satisfies this condition.



 Example 1:

 Input: [3,1,2,4]
 Output: [2,4,3,1]
 The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.


 Note:

 1 <= A.length <= 5000
 0 <= A[i] <= 5000
 */

// 用two pointer来从两端检查，根据可能的四种情况移动pointer，需要时交换元素即可，注意移动pointer的方向

public class SortArrayByParity905 {
    public int[] sortArrayByParity(int[] A) {
        int i = 0;
        int j = A.length - 1;
        while (i < j) {
            if(A[i] % 2 == 0) {
                // Even first
                i++;
            }
            else {
                if(A[j] % 2 != 0) {
                    // Both odd
                    j--;
                }
                if (A[j] % 2 == 0) {
                    // Odd, Even
                    swap(A, i, j);
                    i++;
                    j--;
                }
            }
        }


        return A;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
