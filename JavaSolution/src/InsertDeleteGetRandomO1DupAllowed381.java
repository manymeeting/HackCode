import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Design a data structure that supports all following operations in average O(1) time.

 Note: Duplicate elements are allowed.
 insert(val): Inserts an item val to the collection.
 remove(val): Removes an item val from the collection if present.
 getRandom: Returns a random element from current collection of elements.
 The probability of each element being returned is linearly related to
 the number of same value the collection contains.
 Example:

 // Init an empty collection.
 RandomizedCollection collection = new RandomizedCollection();

 // Inserts 1 to the collection. Returns true as the collection did not contain 1.
 collection.insert(1);

 // Inserts another 1 to the collection. Returns false as the collection contained 1.
 Collection now contains [1,1].
 collection.insert(1);

 // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 collection.insert(2);

 // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 collection.getRandom();

 // Removes 1 from the collection, returns true. Collection now contains [1,2].
 collection.remove(1);

 // getRandom should return 1 and 2 both equally likely.
 collection.getRandom();

 */

// 在380基础上把hashmap里的location信息换成一个set来处理
public class InsertDeleteGetRandomO1DupAllowed381 {

    ArrayList<Integer> nums;
    HashMap<Integer, Set<Integer>> locs;
    java.util.Random rand = new java.util.Random();

    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1DupAllowed381() {

        nums = new ArrayList<>();
        locs = new HashMap<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contained = locs.containsKey(val);
        if(!contained)
        {
            locs.put(val, new LinkedHashSet<>());
        }
        // update position information
        locs.get(val).add(nums.size());
        nums.add(val);
        return !contained;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        boolean contained = locs.containsKey(val);
        if(!contained) return false;

        int loc = locs.get(val).iterator().next();
        locs.get(val).remove(loc);

        // is not the last
        if(loc < nums.size() - 1)
        {
            int last = nums.get(nums.size() - 1);
            // switch with current last
            nums.set(loc, last);
            locs.get(last).remove(nums.size() - 1);
            locs.get(last).add(loc);
        }

        nums.remove(nums.size() - 1);

        if(locs.get(val).isEmpty())
        {
            locs.remove(val);
        }

        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}


/**
 25
 * Your RandomizedCollection object will be instantiated and called as such:
 26
 * RandomizedCollection obj = new RandomizedCollection();
 27
 * boolean param_1 = obj.insert(val);
 28
 * boolean param_2 = obj.remove(val);
 29
 * int param_3 = obj.getRandom();
 30
 */