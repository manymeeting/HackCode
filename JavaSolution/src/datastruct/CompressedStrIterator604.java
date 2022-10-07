package datastruct;

import java.util.ArrayDeque;

/**
 * Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.

 The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.

 next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
 hasNext() - Judge whether there is any letter needs to be uncompressed.

 Note:
 Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple test cases. Please see here for more details.

 Example:

 StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

 iterator.next(); // return 'L'
 iterator.next(); // return 'e'
 iterator.next(); // return 'e'
 iterator.next(); // return 't'
 iterator.next(); // return 'C'
 iterator.next(); // return 'o'
 iterator.next(); // return 'd'
 iterator.hasNext(); // return true
 iterator.next(); // return 'e'
 iterator.hasNext(); // return false
 iterator.next(); // return ' '
 */

// 用两个queue，一个存char，一个存出现次数，next时减去头部的次数，减到0时poll出头部的char

public class CompressedStrIterator604 {
    private ArrayDeque<Character> charQueue;
    private ArrayDeque<Integer> charRepeat;

    public CompressedStrIterator604(String compressedString) {
        charQueue = new ArrayDeque<>();
        charRepeat = new ArrayDeque<>();

        char[] chars = compressedString.toCharArray();
        int i = 0;
        int currCount = 0;
        while(i < chars.length) {
            if(chars[i] >= '0' && chars[i] <= '9') {
                // Is number
                currCount = currCount*10 + Character.getNumericValue(chars[i]);
                System.out.println(currCount);
            }
            else { // Is char
                if(currCount > 0) {
                    charRepeat.offer(currCount);
                    currCount = 0;
                }

                charQueue.offer(chars[i]);
            }

            i++;
        }
        if(currCount > 0) charRepeat.offer(currCount);

    }

    public char next() {
        if(charQueue.isEmpty()) return ' ';
        int repeat = charRepeat.poll();
        char ret = charQueue.peekFirst();
        repeat--;
        if(repeat == 0) {
            charQueue.poll();
        }
        else {
            charRepeat.addFirst(repeat);
        }
        return ret;

    }



    public boolean hasNext() {
        return !charQueue.isEmpty();
    }
}


/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

