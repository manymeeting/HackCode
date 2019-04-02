package stack;

import java.util.ArrayDeque;

/**
We are given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2, node_3, ... etc.

Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i, node_j.val > node_i.val, and j is the smallest possible choice.  If such a j does not exist, the next larger value is 0.

Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).

Note that in the example inputs (not outputs) below, arrays such as [2,1,5] represent the serialization of a linked list with a head node value of 2, second node value of 1, and third node value of 5.

 

Example 1:

Input: [2,1,5]
Output: [5,5,0]
Example 2:

Input: [2,7,4,3,5]
Output: [7,0,5,5,0]
Example 3:

Input: [1,7,5,1,9,2,5,1]
Output: [7,9,9,9,0,5,0,0]
 

Note:

1 <= node.val <= 10^9 for each node in the linked list.
The given list has length in the range [0, 10000].
*/

// 先把list转换为array方便处理，然后从后往前遍历，用一个stack来记录每个元素，一旦遇到curr<top就开始pop直到找到最大的

class NextGtNodeInLinkedList1019 {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}		
    public int[] nextLargerNodes(ListNode head) {
    	// 转换List to array, 为了方便处理
        List<Integer> list = new ArrayList<>();
        while(head != null) {
        	list.add(head.val);
        	head = head.next;
        }

        int[] nums = new int[list.size()];
        for (int i = 0; i < nums.length; i++) { nums[i] = list.get(i); }

        int[] res = new int[nums.length];

        // Use stack to record larger number
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = nums.length - 1; i >= 0; i--) {
        	int curr = nums[i];
        	if(i == nums.length - 1) {
        		res[i] = 0;
        		stack.push(curr);
        		continue;
        	}
        	int top = stack.peek();
    		if(curr >= top) { // 当curr比stack顶端大的时候，一直pop直到遇到更大的，否则不set（arr初始化为0）
    			while(!stack.isEmpty()) {
    				if(curr < stack.peek()) {
    					res[i] = stack.peek();
    					break;
    				}
    				stack.pop();
    			}
    		}
    		else {
    			res[i] = top;
    		}
    		stack.push(curr);
        }

        return res;
    }
}

