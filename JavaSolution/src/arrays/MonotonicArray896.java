package arrays;

/**
 * An array is monotonic if it is either monotone increasing or monotone decreasing.

 An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].

 Return true if and only if the given array A is monotonic.



 Example 1:

 Input: [1,2,2,3]
 Output: true
 Example 2:

 Input: [6,5,4,4]
 Output: true
 Example 3:

 Input: [1,3,2]
 Output: false
 Example 4:

 Input: [1,2,4,5]
 Output: true
 Example 5:

 Input: [1,1,1]
 Output: true


 Note:

 1 <= A.length <= 50000
 -100000 <= A[i] <= 100000
 */

// 用两个初始为-1的int来作为indicator，遇到相邻递减就设为2，相同不变，最后看是-2或1则为单调
public class MonotonicArray896 {
    public boolean isMonotonic(int[] A) {
        int isIncreasing = -1;
        int isDecreasing = -1;
        for (int i = 1; i < A.length; i++) {

            if(A[i] > A[i-1]) isIncreasing = 2;
            else if(A[i] == A[i-1]) continue;
            else isDecreasing = 2;
        }
        int res = isIncreasing * isDecreasing;
        return  res == 1 || res < 0;
    }
}
