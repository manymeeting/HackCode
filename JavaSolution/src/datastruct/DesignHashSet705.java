package datastruct;

/**
 * Design a HashSet without using any built-in hash table libraries.

 To be specific, your design should include these functions:

 add(value): Insert a value into the HashSet.
 contains(value) : Return whether the value exists in the HashSet or not.
 remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.

 Example:

 MyHashSet hashSet = new MyHashSet();
 hashSet.add(1);
 hashSet.add(2);
 hashSet.contains(1);    // returns true
 hashSet.contains(3);    // returns false (not found)
 hashSet.add(2);
 hashSet.contains(2);    // returns true
 hashSet.remove(2);
 hashSet.contains(2);    // returns false (already removed)

 Note:

 All values will be in the range of [0, 1000000].
 The number of operations will be in the range of [1, 10000].
 Please do not use the built-in HashSet library.

 */


// 用二维数组来实现bucket+list的结构
    
public class DesignHashSet705 {
    /** Initialize your data structure here. */
    private int bucketsNum = 1000;
    private int itemPerBucket = 1000;
    private boolean[][] table;

    public DesignHashSet705() {
        table = new boolean[bucketsNum][];
    }

    private int hash(int key) {
        return key % bucketsNum;
    }

    private int offset(int key) {
        return key / bucketsNum;
    }

    public void add(int key) {
        int hashKey = hash(key);
        if(table[hashKey] == null) {
            table[hashKey] = new boolean[itemPerBucket];
        }

        table[hashKey][offset(key)] = true;
    }

    public void remove(int key) {
        int hashKey = hash(key);
        if(table[hashKey] != null) {
            table[hashKey][offset(key)] = false;
        }

    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hashKey = hash(key);
        return table[hashKey] != null && table[hashKey][offset(key)];
    }
}
