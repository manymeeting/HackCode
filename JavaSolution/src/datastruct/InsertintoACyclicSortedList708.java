package datastruct;

/**Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.

 If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the cyclic list should remain sorted.

 If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node. Otherwise, you should return the original given node.

 The following example may help you understand the problem better:


 In the figure above, there is a cyclic sorted list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list


 The new node should insert between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.


 * */
// 从head开始，移动prev和curr两个指针，分析insert的condition
    
public class InsertintoACyclicSortedList708 {
    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val,Node _next) {
            val = _val;
            next = _next;
        }

    }
    public Node insert(Node head, int insertVal) {
        if(head == null) {
            Node curr = new Node(insertVal, null);
            curr.next = curr;
            return curr;
        }

        Node curr = head.next;
            Node prev = head;
        boolean founded = false;

        while(curr != head) {
            int currVal = curr.val;
            int preVal = prev.val;

            if(insertVal == preVal || insertVal <= currVal && insertVal > preVal ||
                    currVal < preVal && insertVal > preVal || currVal < preVal && insertVal < currVal){
                founded = true;
                Node node = new Node(insertVal, curr);
                prev.next = node;
                break;
            }

            prev = curr;
            curr = curr.next;
        }

        if(!founded) {
            Node node = new Node(insertVal, curr);
            prev.next = node;
        }

        return head;
    }
}
