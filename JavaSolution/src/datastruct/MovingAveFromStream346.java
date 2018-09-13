package datastruct;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

 Example:

 MovingAverage m = new MovingAverage(3);
 m.next(1) = 1
 m.next(10) = (1 + 10) / 2
 m.next(3) = (1 + 10 + 3) / 3
 m.next(5) = (10 + 3 + 5) / 3
 */

// 用Queue来维护window，注意queue.remove会返回被remove的元素（相比之下set.remove返回的是boolean）

public class MovingAveFromStream346 {
    private double sum = 0.0;
    private int maxSize;
    private Queue<Integer> window;

    /** Initialize your data structure here. */
    public MovingAveFromStream346(int size) {
        window = new LinkedList<>();
        maxSize = size;
    }

    public double next(int val) {
        if(window.size() == maxSize)
        {
            sum -= window.remove();
        }
        window.add(val);
        sum+=val;
        return sum / window.size();
    }
}


/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */