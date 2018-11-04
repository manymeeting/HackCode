package arrays;

import java.util.*;

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

// 用一个pq，按照start从小到大排序，一开始把所有interval都加到pq里，
// 每次从pq里poll出一个，同时拿到res的最后一个（res先存为ArrayDeque）如果curr.start <= last.end就merge，否则就加到res里

public class MergeIntervals56 {
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    public List<Interval> merge(List<Interval> intervals) {

        ArrayDeque<Interval> res = new ArrayDeque<>();
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
        pq.addAll(intervals);

        while(!pq.isEmpty()) {
            Interval curr = pq.poll();
            if(res.size() == 0){
                res.add(curr);
                continue;
            }
            Interval last =  res.getLast(); // 用ArrayDeque实现常数时间getlast
            if(curr.start <= last.end) {
                // merge interval
                last.end = Math.max(curr.end, last.end);
            }
            else {
                // add new one to res
                res.add(curr);
            }
        }


        // ArrayDeque -> ArrayList
        List<Interval> resList = new ArrayList<>();
        for (Interval interval : res) {
            resList.add(interval);
        }

        return resList;
    }
}
