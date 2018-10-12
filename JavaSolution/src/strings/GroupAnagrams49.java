package strings;

import java.util.*;

/**
 *
 Given an array of strings, group anagrams together.

 Example:

 Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 Output:
 [
 ["ate","eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]
 Note:

 All inputs will be in lowercase.
 The order of your output does not matter.

 */

// 用map来归类，key为排序后的char数组，value为原str

public class GroupAnagrams49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if(strs == null || strs.length == 0) return res;

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chrs = str.toCharArray();
            Arrays.sort(chrs);
            String key = String.valueOf(chrs);

            if(!map.containsKey(key)) {
                map.put(key, new ArrayList<>());

            }
            map.get(key).add(str);

        }

        for (List<String> list : map.values()) {
            res.add(list);
        }
        return res;
    }
}
