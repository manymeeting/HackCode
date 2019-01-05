package strings;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

 Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.



 Example:

 Input:
 paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 banned = ["hit"]
 Output: "ball"
 Explanation:
 "hit" occurs 3 times, but it is a banned word.
 "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
 Note that words in the paragraph are not case sensitive,
 that punctuation is ignored (even if adjacent to words, such as "ball,"),
 and that "hit" isn't the answer even though it occurs more because it is banned.


 Note:

 1 <= paragraph.length <= 1000.
 1 <= banned.length <= 100.
 1 <= banned[i].length <= 10.
 The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
 paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
 There are no hyphens or hyphenated words.
 Words only consist of letters, never apostrophes or other punctuation symbols.

 */

// 用正则去除标点，再分割，最后统计频率，把entrySet排序后取最大结果，
// 注意正则的使用（W+匹配非单词），以及map中按value排序的操作（entrySet转换为stream然后排序再collect）
public class MostCommonWord819 {
    public String mostCommonWord(String paragraph, String[] banned) {


        Set<String> banSet = new HashSet<>(Arrays.asList(banned));
        paragraph = paragraph.replaceAll("\\W+", " ");
        paragraph = paragraph.toLowerCase();
        String[] words = paragraph.split("\\s+");

        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            if(!banSet.contains(word)) {
                countMap.put(word, countMap.getOrDefault(word, 0) + 1);
            }
        }

        List<String> list = countMap.entrySet().stream()
                .sorted( (a, b) -> b.getValue() - a.getValue())
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        return list.get(0);

    }
}
