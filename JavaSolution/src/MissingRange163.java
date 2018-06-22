/**
 *
 * Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper],
 * return its missing ranges.

 Example:

 Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
 Output: ["2", "4->49", "51->74", "76->99"]

 */

import java.util.ArrayList;
import java.util.List;

public class MissingRange163 {

    public List<String> findMissingRanges(int[] a, int lo, int hi) {

        List<String> ranges = new ArrayList<>();
        if(a.length == 0)
        {
            ranges.add(outputRange(lo, hi));
            return ranges;
        }

        long prev;

        // handle lower boundary
        if(a[0] > lo)
        {
            ranges.add(outputRange(lo, a[0] - 1));
            prev = a[0];
        }
        else {
            prev = lo;
        }


        // handle numbers in the middle
        for(long cur : a)
        {
            if(cur - prev > 1)
            {
                ranges.add(outputRange(prev + 1, cur - 1));

            }
            prev = cur;
        }

        // handle upper boundary
        if(hi > prev)
        {
            ranges.add(outputRange(prev + 1, hi));

        }
        return ranges;
    }


    private String outputRange(long n, long m) {
        return (n == m)?String.valueOf(n) : n + "->" + m;
    }



}
