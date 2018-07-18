package datastruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class InsertDeleteGetRandomO1NoDup380 {
    /***
     * Design a data structure that supports all following operations in average O(1) time.

     insert(val): Inserts an item val to the set if not already present.
     remove(val): Removes an item val from the set if present.
     getRandom: Returns a random element from current set of elements.
     Each element must have the same probability of being returned.

     */

    // 用map存index来满足O(1)的查找，每次remove时为了避免移动整个list，先把last位置的元素交换到目标元素位置
    // 交换后注意更新map里last元素的index

    private HashMap<Integer, Integer> indexMap;
    private ArrayList<Integer> list;
    private Random rand;

    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1NoDup380() {
        indexMap = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(list.contains(val))
        {
            return false;
        }

        list.add(val);
        indexMap.put(val, list.size()-1);
        return true;

    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!indexMap.containsKey(val))
        {
            return false;
        }

        int index = indexMap.get(val);
        if(index < list.size() - 1)
        {
            // swap
            int last = list.get(list.size()-1);
            list.set(index, last);
            // update new index for "last"
            indexMap.put(last, index);
        }

        // clean up
        list.remove(list.size()-1);
        indexMap.remove(val);

        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        if(list.size() <= 0)
        {
            return -1;
        }
        return list.get(rand.nextInt(list.size()));
    }

}
