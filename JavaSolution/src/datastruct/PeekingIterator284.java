package datastruct;

import java.util.Iterator;

/**
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

 Example:

 Assume that the iterator is initialized to the beginning of the list: [1,2,3].

 Call next() gets you 1, the first element in the list.
 Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 You call next() the final time and it returns 3, the last element.
 Calling hasNext() after that should return false.
 Follow up: How would you extend your design to be generic and work with all types, not just integer?
 */

// 用一个cache变量来存next的值，每次next()时返回当前cache同时更新cache，
// 在peek时直接返回cache，注意初始化时要先调用一次next来初始化cache

public class PeekingIterator284 implements Iterator<Integer> {

    private Integer cachedVal = null;
    private Iterator<Integer> iterator;
    public PeekingIterator284(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        // Cache the first value;
        next();

    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return cachedVal;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer ret = cachedVal;
        cachedVal = iterator.hasNext() ? iterator.next() : null;
        return ret;
    }

    @Override
    public boolean hasNext() {
        return cachedVal != null;
    }

}
