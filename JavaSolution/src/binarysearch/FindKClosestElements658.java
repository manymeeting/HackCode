package binarysearch;

import java.util.*;

/**
 * 
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b
 

Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]
 

Constraints:

1 <= k <= arr.length
1 <= arr.length <= 104
arr is sorted in ascending order.
-104 <= arr[i], x <= 104


思路：构造一个长度为k的window，然后用binary search找这个window最合适的开始位置。注意index的处理。

case 1: x - A[mid] < A[mid + k] - x, need to move window go left
-------x----A[mid]-----------------A[mid + k]----------

case 2: x - A[mid] < A[mid + k] - x, need to move window go left again
-------A[mid]----x-----------------A[mid + k]----------

case 3: x - A[mid] > A[mid + k] - x, need to move window go right
-------A[mid]------------------x---A[mid + k]----------

case 4: x - A[mid] > A[mid + k] - x, need to move window go right
-------A[mid]---------------------A[mid + k]----x-----

计算距离时不能用abs，否则有case会fail。
 */
public class FindKClosestElements658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        int left = 0; 
        // 注意right的bound，从length里减去k的长度，确保mid+k不会越界。
        int right = arr.length - k;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            if (x - arr[mid] > arr[mid+k] - x) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }

        return res;
        
    }

}
