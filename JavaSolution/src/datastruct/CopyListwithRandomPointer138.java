package datastruct;

import java.util.HashMap;

/**
 * A linked list is given such that each node contains an additional random pointer
 * which could point to any node in the list or null.

 Return a deep copy of the list.
 */

// 类似于Clone Graph，用一个map来保存之前copy过的节点
public class CopyListwithRandomPointer138 {
    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    };


    private HashMap<Integer, RandomListNode> map = new HashMap<>();

    public RandomListNode copyRandomList(RandomListNode head) {
        return clone(head);
    }

    public RandomListNode clone(RandomListNode node){

        if(node == null) return null;

        if(map.containsKey(node.label)) return map.get(node.label);

        RandomListNode copy = new RandomListNode(node.label);
        map.put(node.label, copy);
        copy.next = clone(node.next);
        copy.random = clone(node.random);

        return copy;
    }

}
