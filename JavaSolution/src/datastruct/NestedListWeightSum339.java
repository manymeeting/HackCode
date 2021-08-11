package datastruct;

import java.util.*;
/**
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 10 
Explanation: Four 1's at depth 2, one 2 at depth 1.
Example 2:

Input: [1,[4,[6]]]
Output: 27 
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.
*/

// 写一个递归helper来处理嵌套的list，传当前的level到每一层递归中，遇到int就返回level * val

class NestedListWeightSum339 {
	// This is the interface that allows for creating nested lists.
 	// You should not implement it, or speculate about its implementation
	public interface NestedInteger {

		// @return true if this NestedInteger holds a single integer, rather than a nested list.
		public boolean isInteger();

		// @return the single integer that this NestedInteger holds, if it holds a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger();

		// Set this NestedInteger to hold a single integer.
		public void setInteger(int value);

		// Set this NestedInteger to hold a nested list and adds a nested integer to it.
		public void add(NestedInteger ni);

		// @return the nested list that this NestedInteger holds, if it holds a nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList();

	}

    public int depthSum(List<NestedInteger> nestedList) {
    	int res = 0;
    	for (int i = 0; i < nestedList.size(); i++) {
    		res += getNestedVal(1, nestedList.get(i));
    	}

    	return res;

    }

    private int getNestedVal(int level, NestedInteger nestedInt) {
    	if(nestedInt.isInteger()) {
    		return level * nestedInt.getInteger();
    	}
    	else {
    		List<NestedInteger> list = nestedInt.getList();
    		int	res = 0;
    		for (int i = 0; i < list.size(); i++) {
    			res += getNestedVal(level + 1, list.get(i));
	    	}
	    	return res;
    	}

    }

}