package strings;

/**
 * Given a string S and a string T, find the minimum window in S which will contain
 * all the characters in T in complexity O(n).

 Example:

 Input: S = "ADOBECODEBANC", T = "ABC"
 Output: "BANC"
 Note:

 If there is no such window in S that covers all characters in T, return the empty string "".
 If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

 */


// 用occurrence map和start end index来操作窗口，同时check窗口中字符的occur情况
public class MinimumWindowSubstring76 {
    public String minWindow(String s, String t) {
        if(s==null || t==null || s.length()==0 || t.length()==0 ) return "";

        char[] input = s.toCharArray();

        // elementBalance[i]  =   Number of element i that's required for a "valid" window
        // elementBalance[i] < 0  We saw element i less times than required in the current window
        // elementBalance[i] == 0 We saw element i exactly as many times as required in the current window
        // elementBalance[i] > 0  We saw element i more times than required in the current window
        // Note: We only care about elementBalance of required elements
        //       and ignore the balance of other elements that were found

        int[] elementBalance = new int[128];

        // Update balance for required elements
        for (char requiredElement : t.toCharArray())
        {
            elementBalance[requiredElement-'0']--; // Negative because we haven't found them yet
        }

        // Total number of required elements found in the current window
        int overallBalance = -t.length();

        // Tracks the min window found so far
        int minWindowStartIndex = 0;
        int minWindowLength = Integer.MAX_VALUE;

        int curWindowStartIndex = 0;
        for(int curWindowEndIndex = 0; curWindowEndIndex < input.length; curWindowEndIndex++) {
            char curElement = input[curWindowEndIndex];
            if (elementBalance[curElement] < 0) { // If this is a required element
                overallBalance++; // We have found a required element
            }
            elementBalance[curElement]++;

            // While all required elements are in the current window
            while (overallBalance == 0) {

                // Updated minimum window found if needed
                int curWindowLength = curWindowEndIndex - curWindowStartIndex + 1;
                if (curWindowLength < minWindowLength) {
                    minWindowLength = curWindowLength;
                    minWindowStartIndex = curWindowStartIndex;
                }

                // Shrink (minimize) this window size by incrementing the start index
                char elementToRemove = input[curWindowStartIndex];
                curWindowStartIndex++;

                if (elementBalance[elementToRemove] == 0) {
                    // We're losing the minimum required# of this element
                    overallBalance--;
                }
                elementBalance[elementToRemove]--;
            }
        }

        return minWindowLength == Integer.MAX_VALUE ? "" : s.substring(minWindowStartIndex,
                minWindowStartIndex+minWindowLength);
    }
}
