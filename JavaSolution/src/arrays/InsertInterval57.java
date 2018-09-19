package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

 You may assume that the intervals were initially sorted according to their start times.

 Example 1:

 Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 Output: [[1,5],[6,9]]
 Example 2:

 Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 Output: [[1,2],[3,10],[12,16]]
 Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

 */

// 仔细比较start end即可
public class InsertInterval57 {

    public class Interval {
        int start;
        int end;
        Interval() {start = 0; end = 0;}
        Interval(int s, int e) {start = s; end = e;}
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        int i = 0;
        List<Interval> res = new ArrayList<>();

        // 注意while循环一定要check是否越界
        while(i < intervals.size() && intervals.get(i).end < newInterval.start)
        {
            res.add(intervals.get(i));
            i++;
        }


        while(i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            Interval curr = intervals.get(i);
            int newStart = Math.min(newInterval.start, curr.start);
            int newEnd = Math.max(newInterval.end, curr.end);
            newInterval = new Interval(newStart, newEnd);
            i++;
        }
        res.add(newInterval);

        while(i < intervals.size()) {
            res.add(intervals.get(i));
        }
        return res;
    }


}
