package datastruct;

/**
 * Design a HashMap without using any built-in hash table libraries.

 To be specific, your design should include these functions:

 put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
 get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

 Example:

 MyHashMap hashMap = new MyHashMap();
 hashMap.put(1, 1);
 hashMap.put(2, 2);
 hashMap.get(1);            // returns 1
 hashMap.get(3);            // returns -1 (not found)
 hashMap.put(2, 1);          // update the existing value
 hashMap.get(2);            // returns 1
 hashMap.remove(2);          // remove the mapping for 2
 hashMap.get(2);            // returns -1 (not found)

 Note:

 All keys and values will be in the range of [0, 1000000].
 The number of operations will be in the range of [1, 10000].
 Please do not use the built-in HashMap library.*/

// 用bucket+list来设计，给一个长度有限的bucket数组，每个index包括一个list，
// index用number % bucketsize 来映射，

// 注意list的操作，需要维护一个prev指针，这样当遍历结束后如果没有发现exisiting的key，就可以直接在保留的prev引用上append

public class DesignHashMap706 {
    /** Initialize your data structure here. */

    private Bucket[] buckets = new Bucket[10000];
    class Bucket {
        final ListNode head = new ListNode(-1, -1);
    }

    class ListNode {
        int key, val;
        ListNode next;

        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private ListNode findInBucket(Bucket bucket, int key) {
        ListNode node = bucket.head;
        ListNode prev = null;
        while(node != null && node.key != key) {
            prev = node;
            node = node.next;
        }

        return prev;
    }

    private int getIndex(int key) {
        return key % buckets.length;
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int i = getIndex(key);
        if(buckets[i] == null) {
            buckets[i] = new Bucket();
        }
        ListNode prev = findInBucket(buckets[i], key);
        if(prev.next == null) {
            prev.next = new ListNode(key, value);
        }
        else {
            prev.next.val = value;
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int i = getIndex(key);
        if(buckets[i] == null) {
            return -1;
        }
        ListNode node = findInBucket(buckets[i], key);
        return node.next == null ? -1 : node.next.val;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int i = getIndex(key);
        if (buckets[i] == null) return;
        ListNode prev = findInBucket(buckets[i], key);
        if (prev.next == null) return;
        prev.next = prev.next.next;
    }
}


/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */