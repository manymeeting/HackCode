package datastruct;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LFUCache cache = new LFUCache( 2 );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.get(3);       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
 */
// 参考：https://www.jianshu.com/p/437f53341f67
// 通过一个map加一个嵌套的双向链表来存储node

public class LFUCache460 {

    // 外层链表的每个节点代表一组拥有同样访问次数的key，每个节点自身也是一个链表，
    // 内层链表确保上次访问时间最早的key位于内层链表的尾部。
    class Node{
        int key;
        int value;
        int freq = 0; //访问次数
        Node next;
        Node prev;

        NodeQueue nq; //所属的外层链表元素

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }

    class NodeQueue {
        NodeQueue next;
        NodeQueue prev;
        Node tail;
        Node head;

        public NodeQueue(NodeQueue next, NodeQueue prev, Node tail, Node head) {
            this.next = next;
            this.prev = prev;
            this.tail = tail;
            this.head = head;
        }
    }

    NodeQueue tail;
    int capacity;
    Map<Integer, Node> map;  //存储key-value对的HashMap

    public LFUCache460(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer, Node>();
    }

    // 更新freq，同时移动Node到适当的NodeQueue位置
    private void updateFreqAndMoveNode(Node node) {

        node.freq++; // 更新freq

        boolean isSingleNQ = false;
        if(node.nq.head == node.nq.tail) {
            isSingleNQ = true;
        }

        if(node.nq.next != null) {
            if(node.nq.next.tail.freq == node.freq) {
                // 右侧NodeQueue的访问次数与Node当前访问次数一样，将此Node置于右侧NodeQueue的头部
                removeNode(node); //从当前NodeQueue中删除Node
                // 把Node插入到右侧NodeQueue的头部
                node.prev = node.nq.next.head;
                node.nq.next.head.next = node;
                node.nq.next.head = node;
                node.nq = node.nq.next;
            }
            else if(node.nq.next.tail.freq > node.freq) {
                // 右侧NodeQueue的访问次数大于Node当前访问次数，则需要在两个NodeQueue之间插入一个新的NodeQueue
                if(!isSingleNQ) {
                    removeNode(node);
                    NodeQueue nnq = new NodeQueue(node.nq.next, node.nq, node, node);
                    node.nq.next.prev = nnq;
                    node.nq.next = nnq;
                    node.nq = nnq;
                }
                // 如果当前NodeQueue中只有一个Node，那么其实不需要任何额外操作了
            }

        }
        else {
            // 此NodeQueue的next == null，说明此NodeQueue已经位于外层链表头部了，这时候需要往外侧链表头部插入一个新的NodeQueue
            if(!isSingleNQ) {
                removeNode(node);
                NodeQueue nnq = new NodeQueue(null, node.nq, node, node);
                node.nq.next = nnq;
                node.nq = nnq;
            }
            //同样地，如果当前NodeQueue中只有一个Node，不需要任何额外操作
        }
    }

    private Node removeNode(Node node) {
        // 如果NodeQueue中只有一个Node，那么移除整个NodeQueue
        if(node.nq.head == node.nq.tail) {
            removeNQ(node.nq);
            return node;
        }

        if(node.prev != null)
            node.prev.next = node.next;
        if(node.next != null)
            node.next.prev = node.prev;

        if(node.nq.head == node)
            node.nq.head = node.prev;
        if(node.nq.tail == node)
            node.nq.tail = node.next;

        node.prev = null;
        node.next = null;
        return node;
    }

    private void removeNQ(NodeQueue nq) {
        if(nq.prev != null) {
            nq.prev.next = nq.next;
        }
        if(nq.next != null) {
            nq.next.prev = nq.prev;
        }
        if(this.tail == nq) {
            this.tail = nq.next;
        }
    }

    public int get(int key) {
        Node node = map.get(key);
        if(node == null) {
            return -1;
        }
        updateFreqAndMoveNode(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(capacity == 0) return;
        Node curr = map.get(key);
        // key已存在的情况下，更新value值，并将Node右移
        if(curr != null) {
            curr.value = value;
            updateFreqAndMoveNode(curr);
            return;
        }

        // cache已满的情况下，把外层链表尾部的内层链表的尾部Node移除
        if(map.size() == capacity) {
            map.remove(removeNode(this.tail.tail).key);
        }
        // 插入新的Node
        Node n = new Node(key, value);
        if(this.tail == null) {
            //tail为null说明此时cache中没有元素，直接把Node封装到NodeQueue里加入
            NodeQueue nq = new NodeQueue(null, null, n, n);
            this.tail = nq;
            n.nq = nq;
        } else if(this.tail.tail.freq == 0) {
            //外层链表尾部元素的访问次数是0，那么将Node加入到外层链表尾部元素的头部
            n.prev = this.tail.head;
            this.tail.head.next = n;
            n.nq = this.tail;
            this.tail.head = n;
        } else {
            //外层链表尾部元素的访问次数不是0，那么实例化一个只包含此Node的NodeQueue，加入外层链表尾部
            NodeQueue nq = new NodeQueue(this.tail, null, n, n);
            this.tail.prev = nq;
            this.tail = nq;
            n.nq = nq;
        }
        //最后把key和Node存入HashMap中
        map.put(key, n);

    }

}
/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
