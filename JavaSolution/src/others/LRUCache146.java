package others;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LRUCache cache = new LRUCache( 2 );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4

 */

// 维护一个map，key是传进来的每个node的key，value是一个Node的引用，
// Node中包括一个key一个val，所有Node通过双向链表连接，
// 每次get或通过put来更新Node时，把目标node移到链表头部（把其它元素挤到尾部），
// 每次put新node时，如果发现已达到capacity，就删除尾部node，同时删除map中对应的entry，

// 维护两个空节点，head和tail，这样操作头尾的删除和插入时很方便

public class LRUCache146 {

    class Node {
        //int key;
        int val;
        int key; // 为什么存key：为了让map能通过key找到node的ref
        Node prev, next;
        Node(int key, int val) {
            this.val = val;
            this.key = key;
        }
    }

    final private Node head = new Node(0, 0);
    final private Node tail = new Node(0, 0);
    final private Map<Integer, Node> map = new HashMap<>();
    private int capacity = 0;

    public LRUCache146(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.next = head;

    }

    public int get(int key) {
        int res = -1;
        if(map.containsKey(key)) {
            Node node = map.get(key);
            // move node to head
            remove(node);
            insertToHead(node);
            res = node.val;
        }
        return res;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            node.val = value; // new value
            insertToHead(node);
        }
        else {
            if(map.size() == capacity) {
                map.remove(tail.prev.key); // 注意删除的时候也要删除map里的entry
                remove(tail.prev);
            }

            Node node = new Node(key, value);
            insertToHead(node);
            map.put(key, node);
        }
    }


    private void insertToHead(Node node) {
        Node headNext = head.next;
        head.next = node;
        headNext.prev = node;
        node.prev = head;
        node.next = headNext;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
