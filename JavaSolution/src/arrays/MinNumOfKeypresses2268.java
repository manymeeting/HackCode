package arrays;

import java.util.Arrays;

/**
 * 
 * You have a keypad with 9 buttons, numbered from 1 to 9, each mapped to lowercase English letters. You can choose which characters each button is matched to as long as:

All 26 lowercase English letters are mapped to.
Each character is mapped to by exactly 1 button.
Each button maps to at most 3 characters.
To type the first character matched to a button, you press the button once. To type the second character, you press the button twice, and so on.

Given a string s, return the minimum number of keypresses needed to type s using your keypad.

Note that the characters mapped to by each button, and the order they are mapped in cannot be changed.

Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.


思路：观察可以发现，最终的count跟letter没有关系，只跟各个letter出现的频率有关系，用一个array来记录对应字母的频率，sort以后直接按照是第几轮1-9来计算即可。
 */
public class MinNumOfKeypresses2268 {

    public int minimumKeypresses(String s) {
        
        // Use Integer instead of int so that we can sort it easily.
        Integer[] letterCounts = new Integer[26];
        // Initialize the array
        for (int i = 0; i < 26; i++) {
            letterCounts[i] = 0;
        }

        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            letterCounts[idx] = letterCounts[idx] + 1;
        }

        Arrays.sort(letterCounts, (a, b) -> b - a);

        int keyPressCount = 0;
        for (int i = 0; i < 26; i++) {
            keyPressCount += letterCounts[i] * (i / 9 + 1);
        }

        return keyPressCount;
    }

    
}
