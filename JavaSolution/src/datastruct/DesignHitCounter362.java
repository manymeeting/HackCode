package datastruct;

/**
 * Design a hit counter which counts the number of hits received in the past 5 minutes.

 Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

 It is possible that several hits arrive roughly at the same time.

 Example:

 HitCounter counter = new HitCounter();

 // hit at timestamp 1.
 counter.hit(1);

 // hit at timestamp 2.
 counter.hit(2);

 // hit at timestamp 3.
 counter.hit(3);

 // get hits at timestamp 4, should return 3.
 counter.getHits(4);

 // hit at timestamp 300.
 counter.hit(300);

 // get hits at timestamp 300, should return 4.
 counter.getHits(300);

 // get hits at timestamp 301, should return 3.
 counter.getHits(301);
 Follow up:
 What if the number of hits per second could be very large? Does your design scale?
 */


// 维护两个bucket，一个存hit，一个存time，长度均为300，
// 每次在hit时，检查timebucket里的time % 300的位置是否和timestamp相等，相等说明是当前300s的周期内，可以直接增加对于hit的数量，
// 不相等则说明是下一个周期，更新timebucket同时把hit重设为1，
// get时遍历timebucket，判断timestamp - times[i] < 300，成立则说明对应的bitbucket里的值在过去300s内，可以算入total
public class DesignHitCounter362 {
    /** Initialize your data structure here. */
    private int[] hits;
    private int[] times;
    public DesignHitCounter362() {
        hits = new int[300];
        times = new int[300];
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int idx = timestamp % 300;
        if(times[idx] != timestamp) {
            times[idx] = timestamp;
            hits[idx] = 1;
        } else {
            hits[idx] ++;
        }
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int total = 0;
        for (int i = 0; i < 300; i++) {
            if(timestamp - times[i] < 300) {
                total += hits[i];
            }
        }
        return total;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */