package datastruct;

/**
 * You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

 Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.



 Example:

 Input:
 1---2---3---4---5---6--NULL
 |
 7---8---9---10--NULL
 |
 11--12--NULL

 Output:
 1-2-3-7-8-11-12-9-10-4-5-6-NULL
 */

// 每次遇到有child的，就用一个getTail函数来找到child list的tail位置，跟parent list相连，
// 然后回到parent中的下一个（原来child list的第一个），继续一个个检查，这样同时处理了嵌套的child list

public class FlattenMultilvDblLinkedList430 {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }

    public Node flatten(Node head) {

        Node curr = head;
        Node tail;
        while(curr !=null ){
            if(curr.child != null) {
                Node originalNext = curr.next;

                curr.next = curr.child;
                curr.child.prev = curr;
                tail = getTail(curr.child);
                tail.next = originalNext;
                if(originalNext != null) {
                    originalNext.prev = tail;
                }
                curr.child = null; // 根据要求，这里要清除child的引用
                curr = curr.next;
            }
            else {
                curr = curr.next;
            }

        }

        return head;
    }

    private Node getTail(Node node) {
        if(node == null) return null;
        Node tail = node;
        while(node != null) {
            tail = node;
            node = node.next;
        }
        return tail;
    }
}
