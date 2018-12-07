package strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

 "abc" -> "bcd" -> ... -> "xyz"
 Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

 Example:

 Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 Output:
 [
 ["abc","bcd","xyz"],
 ["az","ba"],
 ["acef"],
 ["a","z"]
 ]
 */

// 把字符之间的distance序列化，用一个map来存distance的str和对应的string list，
// 注意计算distance时，要处理 za == ba 的情况（为负数时+26）

public class GroupShiftedStrings249 {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> internalDistanceMap = new HashMap<>();
        for (String str : strings) {
            char[] charArr = str.toCharArray();
            StringBuilder distanceExp = new StringBuilder();

            if(charArr.length == 1) {
                distanceExp.append("");
            }
            else {
                for (int i = 1; i < charArr.length; i++) {
                    int distance = charArr[i] - charArr[i-1];
                    distance = distance < 0 ? distance + 26 : distance; // 注意处理distance时，把shift考虑在内
                    distanceExp.append(distance);
                    distanceExp.append(",");
                }
            }

            String keyStr = distanceExp.toString();
            if(internalDistanceMap.containsKey(keyStr)) {
                internalDistanceMap.get(keyStr).add(str);
            }
            else {
                List<String> newList = new ArrayList<>();
                newList.add(str);
                internalDistanceMap.put(keyStr, newList);
            }

        }

        List<List<String>> res = new ArrayList<>();
        for (List<String> list : internalDistanceMap.values()) {
            res.add(list);
        }

        return res;
    }
}
