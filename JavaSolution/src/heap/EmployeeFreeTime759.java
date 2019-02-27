package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * We are given a list schedule of employees, which represents the working time for each employee.

 Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

 Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

 Example 1:
 Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 Output: [[3,4]]
 Explanation:
 There are a total of three employees, and all common
 free time intervals would be [-inf, 1], [3, 4], [10, inf].
 We discard any intervals that contain inf as they aren't finite.
 Example 2:
 Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 Output: [[5,6],[7,9]]
 (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)

 Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

 Note:

 schedule and schedule[i] are lists with lengths in range [1, 50].
 0 <= schedule[i].start < schedule[i].end <= 10^8.

 */

// PQ按照start排序，然后每次比较curr的end和pq头部interval的start关系，如果有空隙就加到result里
public class EmployeeFreeTime759 {
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {


        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
        for (List<Interval> sch : schedule) {
            pq.addAll(sch);
        }

        List<Interval> res = new ArrayList<>();
        Interval curr  = pq.poll();

        while(!pq.isEmpty()) {
            if(pq.peek().start > curr.end) { // 没有重叠部分，加到result
                res.add(new Interval(curr.end, pq.peek().start));
                curr = pq.poll();
            }
            else { // 有重叠，poll出来同时决定保留哪个作为curr
                curr = pq.peek().end > curr.end ? pq.peek() : curr;
                pq.poll();
            }

        }
        return res;
    }
}
