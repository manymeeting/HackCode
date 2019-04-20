package strings;

import java.util.*;
/**
We have a string S of lowercase letters, and an integer array shifts.

Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a'). 

For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.

Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.

Return the final string after all such shifts to S are applied.

Example 1:

Input: S = "abc", shifts = [3,5,9]
Output: "rpl"
Explanation: 
We start with "abc".
After shifting the first 1 letters of S by 3, we have "dbc".
After shifting the first 2 letters of S by 5, we have "igc".
After shifting the first 3 letters of S by 9, we have "rpl", the answer.
Note:

1 <= S.length = shifts.length <= 20000
0 <= shifts[i] <= 10 ^ 9

*/

// 从后往前遍历shifts，累加currShiftNum，注意防止累加时越界

class ShiftingLetters848 {
	public String shiftingLetters(String S, int[] shifts) {
		StringBuilder sb = new StringBuilder();
		int currShiftNum = 0;
		for (int i = S.length() - 1; i >= 0; i--) {
			currShiftNum = (currShiftNum + shifts[i]) % 26; // 在这里先对currShiftNum，防止不断累加时越界
			char ch = S.charAt(i);
			sb.insert(0, (char)('a' + (ch - 'a' + currShiftNum) % 26));
		}

		return sb.toString();
    }
}