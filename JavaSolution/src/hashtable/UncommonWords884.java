package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)

 A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.

 Return a list of all uncommon words.

 You may return the list in any order.



 Example 1:

 Input: A = "this apple is sweet", B = "this apple is sour"
 Output: ["sweet","sour"]
 Example 2:

 Input: A = "apple apple", B = "banana"
 Output: ["banana"]

 */

// 合并AB，记录只出现一次的word即可
    
public class UncommonWords884 {
    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : (A + " " + B).split(" ")){
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        ArrayList<String> res = new ArrayList<>();
        for (String s : map.keySet()) {
            if(map.get(s) == 1) res.add(s);
        }

        return res.toArray(new String[0]);
    }
}
