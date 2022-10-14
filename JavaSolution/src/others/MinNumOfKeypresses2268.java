package others;

import java.util.Arrays;

import javax.swing.text.AbstractDocument.LeafElement;

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


 */
public class MinNumOfKeypresses2268 {

    private static class LetterCount {
        int count;
        public LetterCount(int count) {
            this.count = count;
        }
    }
    public int minimumKeypresses(String s) {
        
        LetterCount[] letterCounts = new LetterCount[26];
        // Initialize the array
        for (int i = 0; i < 26; i++) {
            letterCounts[i] = new LetterCount(0);
        }

        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            letterCounts[idx].count = letterCounts[idx].count + 1;
        }

        Arrays.sort(letterCounts, (a, b) -> b.count - a.count);

        int keyPressCount = 0;
        for (int i = 0; i < 26; i++) {
            keyPressCount += letterCounts[i].count * (i / 9 + 1);
        }

        return keyPressCount;
    }

    
}
