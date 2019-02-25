package twopointer;

/**
 * Implement strStr().

 Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

 Example 1:

 Input: haystack = "hello", needle = "ll"
 Output: 2
 Example 2:

 Input: haystack = "aaaaa", needle = "bba"
 Output: -1
 Clarification:

 What should we return when needle is an empty string? This is a great question to ask during an interview.

 For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

 */

// Two Pointer, 遇到起始ch相同时，固定i，用一个新的j从i处开始不断向右移动，判断haystack.charAt(j) == needle.charAt(j-i)

public class ImplementstrStr28 {
    public int strStr(String haystack, String needle) {
        if(needle.length() == 0) return 0;

        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            j = i;
            while (j < haystack.length() && haystack.charAt(j) == needle.charAt(j-i)) {

                if(j - i + 1 == needle.length()) {
                    return i;
                }
                j++;
            }
        }

        return -1;
    }
}
