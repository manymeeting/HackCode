package datastruct;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

 For example,
 [2,3,4], the median is 3

 [2,3], the median is (2 + 3) / 2 = 2.5

 Design a data structure that supports the following two operations:

 void addNum(int num) - Add a integer number from the data stream to the data structure.
 double findMedian() - Return the median of all elements so far.


 Example:

 addNum(1)
 addNum(2)
 findMedian() -> 1.5
 addNum(3)
 findMedian() -> 2


 Follow up:

 If all integer numbers from the stream are between 0 and 100, how would you optimize it? （用bucket来存数字的出现频率）
 If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it? （用bucket和两个list，一个存小于0的，一个存大于100的）
 */

// 用两个PQ来存，一个是大的部分，一个是小的部分，这样保证了每次中位数计算可以在常数时间内完成

public class FindMedianfromDataStream295 {
    /** initialize your data structure here. */
    private PriorityQueue<Integer> largeQueue;
    private PriorityQueue<Integer> smallQueue;
    public FindMedianfromDataStream295() {
        largeQueue = new PriorityQueue<>();
        smallQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }

    public void addNum(int num) {
        largeQueue.add(num);
        smallQueue.add(largeQueue.poll());
        if(largeQueue.size() < smallQueue.size()) {
            largeQueue.add(smallQueue.poll()); // 奇数个元素时，中间的那个在large的头部
        }
    }

    public double findMedian() {
        if(largeQueue.size() > smallQueue.size()) {
            return largeQueue.peek();
        }
        return (largeQueue.peek() + smallQueue.peek()) / 2.0; // 注意一定要写成2.0，不然会被舍去小数（当做int）
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
