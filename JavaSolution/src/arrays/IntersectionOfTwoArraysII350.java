package arrays;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Given two arrays, write a function to compute their intersection.

 Example:
 Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

 Note:
 Each element in the result should appear as many times as it shows in both arrays.
 The result can be in any order.

 Follow up:

 What if the given array is already sorted? How would you optimize your algorithm?

 What if nums1's size is small compared to nums2's size? Which algorithm is better?

 What if elements of nums2 are stored on disk, and the memory is limited
 such that you cannot load all elements into the memory at once?
 */

// 先排序后，两个array分别对应两个pointer，每次比较大小决定移动哪个，value一样则放入intersect中
public class IntersectionOfTwoArraysII350 {
    public int[] intersect(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        // using two pointer
        int pnt1 = 0;
        int pnt2 = 0;

        ArrayList<Integer> intersect = new ArrayList<>();
        while(pnt1 < nums1.length && pnt2 < nums2.length)
        {
            if(nums1[pnt1] < nums2[pnt2])
            {
                pnt1++;
                continue;
            }
            else{
                if(nums1[pnt1] > nums2[pnt2])
                {
                    pnt2++;
                    continue;
                }
                else
                {
                    intersect.add(nums1[pnt1]);
                    pnt1++;
                    pnt2++;
                }
            }
        }

        int[] res = new int[intersect.size()];
        for (int i = 0; i < res.length; i++)
        {
            res[i] = intersect.get(i);
        }

        return res;

    }

}
