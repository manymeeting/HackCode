package arrays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.

 Example 1:

 Input: [[1,3],[2,6],[8,10],[15,18]]
 Output: [[1,6],[8,10],[15,18]]
 Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 Example 2:

 Input: [[1,4],[4,5]]
 Output: [[1,5]]
 Explanation: Intervals [1,4] and [4,5] are considerred overlapping.

 */

// 先排序（List.sort(lambba)）,然后遍历，维护一个全局的start和end，
// 遇到新interval的start小于当前end时就更新end，否则把当前的start和end作为interval加到结果集

public class MergeIntervals56 {
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals.size() <= 1) return intervals;

        intervals.sort((a, b) -> (a.start - b.start));

        List<Interval> res = new ArrayList<>();

        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (Interval interval : intervals) {
            if(interval.start <= end) {
                // merge end
                end = Math.max(end, interval.end);

            }
            else {
                res.add(new Interval(start, end)); // add之前经过merge后的interval
                // 更新start，end
                start = interval.start;
                end = interval.end;
            }
        }

        // add the last interval, loop里只有在遇到范围外的interval时才会add之前的
        res.add(new Interval(start, end));
        return res;
    }
}
