import java.util.Arrays;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.

 Example 1:

 Input: [1,2,0]
 Output: 3
 Example 2:

 Input: [3,4,-1,1]
 Output: 2
 Example 3:

 Input: [7,8,9,11,12]
 Output: 1
 Note:

 Your algorithm should run in O(n) time and uses constant extra space.
 */
public class FirstMissingPositive41 {
    // 把数字直接放到数字-1的位置上，然后遍历一遍，找到不一致的位置即可
    public int firstMissingPositive(int[] A) {

        int i = 0;
        while(i < A.length){
            if(A[i] == i+1 || A[i] <= 0 || A[i] > A.length) i++;
            else if(A[A[i]-1] != A[i]) swap(A, i, A[i]-1);
            else i++;
        }
        i = 0;
        while(i < A.length && A[i] == i+1) i++;
        return i+1;
    }

    public void swap(int[] x, int indexA, int indexB)
    {
        int temp = x[indexA];
        x[indexA] = x[indexB];
        x[indexB] = temp;
    }


}
