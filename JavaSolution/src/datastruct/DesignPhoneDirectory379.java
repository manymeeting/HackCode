package datastruct;

import java.util.BitSet;

/**
 * Design a Phone Directory which supports the following operations:

 get: Provide a number which is not assigned to anyone.
 check: Check if a number is available or not.
 release: Recycle or release a number.
 Example:

 // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
 PhoneDirectory directory = new PhoneDirectory(3);

 // It can return any available phone number. Here we assume it returns 0.
 directory.get();

 // Assume it returns 1.
 directory.get();

 // The number 2 is available, so return true.
 directory.check(2);

 // It returns 2, the only number that is left.
 directory.get();

 // The number 2 is no longer available, so return false.
 directory.check(2);

 // Release number 2 back to the pool.
 directory.release(2);

 // Number 2 is available again, return true.
 directory.check(2);
 */

// 用BitSet记录free的位置
public class DesignPhoneDirectory379 {

    BitSet bitSet;
    int max;
    int smallestFreeIdx;

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public DesignPhoneDirectory379(int maxNumbers) {
        max = maxNumbers;
        smallestFreeIdx = 0;
        bitSet = new BitSet(maxNumbers);
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(smallestFreeIdx == max) {
            return -1;
        }
        int ret = smallestFreeIdx;
        bitSet.set(smallestFreeIdx);
        smallestFreeIdx = bitSet.nextClearBit(smallestFreeIdx);

        return ret;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return bitSet.get(number) == false;
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if(bitSet.get(number) == false)
        {
            return;
        }

        bitSet.clear(number);
        smallestFreeIdx = Math.min(smallestFreeIdx, number);

    }
}
