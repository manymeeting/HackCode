package others;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

 Example 1:

 Input: [[0, 30],[5, 10],[15, 20]]
 Output: 2
 Example 2:

 Input: [[7,10],[2,4]]
 Output: 1

 */

// 先把interval排序，然后维护一个pq，按照end时间从小到大排列，
// 把第一个interval放到pq里，从第二个开始每次看pq头部的interval是否能和当前interval合并，不能合并则加当前interval到pq里，
// 最后要把当前拿出来的interval再放回去

public class MeetingRoomsII253 {
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }


    public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return 0;

        // 按start time对interval排序
        Arrays.sort(intervals, (a, b) -> (a.start - b.start));

        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> (a.end - b.end));

        pq.offer(intervals[0]); // 先把开始时间最早的meeting放到pq

        for (int i = 1; i < intervals.length; i++) { // 从第二个meeting开始检查
            Interval interval = pq.poll();

            if(intervals[i].start >= interval.end) {
                interval.end = intervals[i].end; // 合并interval （不需要新room）
            }
            else {
                pq.offer(intervals[i]); // 需要新的room
            }

            pq.add(interval); // 更新完后放回pq
        }

        return pq.size(); // 看最后剩下几个经过合并的interval，即为需要的room数量

    }
}
