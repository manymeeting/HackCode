package hashtable;

import java.util.*;

/**
Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

Example 1:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
Output: ["Shogun"]
Explanation: The only restaurant they both like is "Shogun".
Example 2:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
Output: ["Shogun"]
Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
Note:
The length of both lists will be in the range of [1, 1000].
The length of strings in both lists will be in the range of [1, 30].
The index is starting from 0 to the list length minus 1.
No duplicates in both lists.
*/


// 先用一个map存list1中的str和idx，再遍历list2，用第二个map存重复str的idxsum

class MinIdxSumOfTwoLists599 {
	public String[] findRestaurant(String[] list1, String[] list2) {
		Map<String, Integer> idxMap1 = new HashMap<>();
		Map<String, Integer> idxSumMap = new HashMap<>();

		for (int i = 0; i < list1.length; i++) {
			String str = list1[i];
			idxMap1.put(str, i);
		}   
		for (int i = 0; i < list2.length; i++) {
			String str = list2[i];
			if(idxMap1.containsKey(str)) {
				idxSumMap.put(str, idxMap1.get(str) + i);
			}
			
		}        

		int minIdxSum = Integer.MAX_VALUE;
		for (int idxSum : idxSumMap.values()) {
			minIdxSum = Math.min(minIdxSum, idxSum);
		}

		List<String> res = new ArrayList<>();
		for (String key : idxSumMap.keySet()) {
			if(idxSumMap.get(key) == minIdxSum) {
				res.add(key);
			}
		}


		return res.toArray(new String[0]);
    }
}