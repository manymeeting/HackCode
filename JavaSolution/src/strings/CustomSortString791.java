package strings;

/**S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

 S was sorted in some custom order previously. We want to permute the characters of T
 so that they match the order that S was sorted. More specifically, if x occurs before y in S,
 then x should occur before y in the returned string.

 Return any permutation of T (as a string) that satisfies this property.

 Example :
 Input:
 S = "cba"
 T = "abcd"
 Output: "cbad"
 Explanation:
 "a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a".
 Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.


 Note:

 S has length at most 26, and no character is repeated in S.
 T has length at most 200.
 S and T consist of lowercase letters only.


 * */

// 用int[26]存T中字母出现频率，然后遍历S，按照T中字母出现频率来append当前字符，最后把剩下的字符都append即可
public class CustomSortString791 {

    public String customSortString(String S, String T) {

        int[] occur = new int[26];

        for (char ch : T.toCharArray())
        {
            occur[ch - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : S.toCharArray())
        {
            for (int i = 0; i < occur[ch-'a']; i++)
            {
                sb.append(ch);
            }
            occur[ch - 'a'] = 0;
        }

        for (int i = 0; i < occur.length; i++)
        {
            for (int j = 0; j < occur[i]; j++)
            {
                sb.append((char) ('a' + i));
            }
        }

        return sb.toString();
    }
}
