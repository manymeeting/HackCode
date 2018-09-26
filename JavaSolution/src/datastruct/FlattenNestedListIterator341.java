package datastruct;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**Given a nested list of integers, implement an iterator to flatten it.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Example 1:

 Input: [[1,1],2,[1,1]]
 Output: [1,1,2,1,1]
 Explanation: By calling next repeatedly until hasNext returns false,
 the order of elements returned by next should be: [1,1,2,1,1].
 Example 2:

 Input: [1,[4,[6]]]
 Output: [1,4,6]
 Explanation: By calling next repeatedly until hasNext returns false,
 the order of elements returned by next should be: [1,4,6].

 */

// 用stack（Dequeue）来处理，在hasNext里unpack尾端的元素，如果一直unpack到int则返回true，否则hasNext返回false
    
public class FlattenNestedListIterator341 implements Iterator<Integer> {
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }


    Deque<NestedInteger> stack = new ArrayDeque<>();

    public FlattenNestedListIterator341(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()) {
            NestedInteger curr = stack.peek();
            if(curr.isInteger()) {
                return true;
            }
            else  {
                stack.pop();
                for (int i = curr.getList().size()-1; i >= 0; i--) {
                    stack.push(curr.getList().get(i));
                }
            }
        }
        return false;
    }
}
