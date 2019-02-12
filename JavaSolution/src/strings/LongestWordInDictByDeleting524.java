package strings;

import java.util.Collections;
import java.util.List;

/**
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

 Example 1:
 Input:
 s = "abpcplea", d = ["ale","apple","monkey","plea"]

 Output:
 "apple"
 Example 2:
 Input:
 s = "abpcplea", d = ["a","b","c"]

 Output:
 "a"
 Note:
 All the strings in the input will only contain lower-case letters.
 The size of the dictionary won't exceed 1,000.
 The length of all the strings in the input won't exceed 1,000.
 */

// 技巧：把字典里的string排序，先按长度再按字母顺序，
// 然后遍历dict中的string，
// 判断是不是sub sequence：维护一个index，当string的当前字符与dict中的当前字符匹配时index++，
// 如果index最终位置和某个dict单词长度一致则说明找到了结果，

// （还可以不排序，维护一个longest dict string，不断比较更新）

public class LongestWordInDictByDeleting524 {

    public String findLongestWord(String s, List<String> d) {
        Collections.sort(d, (a, b) -> {
            return a.length() != b.length() ? b.length() - a.length() : a.compareTo(b);
        });

        for (String dictWord : d) {
            int i = 0;
            for (char ch : s.toCharArray()) {
                if(i < dictWord.length() && ch == dictWord.charAt(i)) {
                    i++;
                }
            }

            if(i == dictWord.length()) return dictWord;
        }

        return "";

    }
}
