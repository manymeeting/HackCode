package twopointer;

import java.util.*;

/**
 * 
 * You are given two strings start and target, both of length n. Each string consists only of the characters 'L', 'R', and '_' where:

The characters 'L' and 'R' represent pieces, where a piece 'L' can move to the left only if there is a blank space directly to its left, and a piece 'R' can move to the right only if there is a blank space directly to its right.
The character '_' represents a blank space that can be occupied by any of the 'L' or 'R' pieces.
Return true if it is possible to obtain the string target by moving the pieces of the string start any number of times. Otherwise, return false.

 

Example 1:

Input: start = "_L__R__R_", target = "L______RR"
Output: true
Explanation: We can obtain the string target from start by doing the following moves:
- Move the first piece one step to the left, start becomes equal to "L___R__R_".
- Move the last piece one step to the right, start becomes equal to "L___R___R".
- Move the second piece three steps to the right, start becomes equal to "L______RR".
Since it is possible to get the string target from start, we return true.
Example 2:

Input: start = "R_L_", target = "__LR"
Output: false
Explanation: The 'R' piece in the string start can move one step to the right to obtain "_RL_".
After that, no pieces can move anymore, so it is impossible to obtain the string target from start.
Example 3:

Input: start = "_R", target = "R_"
Output: false
Explanation: The piece in the string start can move only to the right, so it is impossible to obtain the string target from start.
 

Constraints:

n == start.length == target.length
1 <= n <= 105
start and target consist of the characters 'L', 'R', and '_'.

 */
public class MovePiecesToObtainAString2337 {
    


    // 解法2：基本是一样的规律，只不过用两个pointer，直接比较每个L和R的index关系，不用额外存储空间。
    public boolean canChange2(String start, String target) {
        int i = 0;
        int j = 0;
        int n = target.length();

        while(i <= n && j <= n) {
            while(i<n && start.charAt(i) == '_') i++;
            while(j<n && target.charAt(j) == '_') j++;

            if (i == n || j ==n) {
                return i == n && j == n;
            }

            if (target.charAt(j) != start.charAt(i)) {
                return false;
            }

            if (target.charAt(j) == 'L' && j > i) {
                return false;
            }
            if (target.charAt(j) == 'R' && j < i) {
                return false;
            }

            i++;
            j++;
        }

        return true;

    }


    // 解法1：找规律，排除错误条件，剩下的就是合法的。
    // Observations: 
    // 1. Each index of L in target must <= the correspnoding ith index of L in A
    // 2. Each index of R in target must >= the correspnoding ith index of R in A
    // 3. Number of L and R must be equal.
    // 4. The relative position of L and R must be the same in A and B.
    public boolean canChange(String start, String target) {
        
        List<Integer> targetLeftIndexes = new ArrayList<>();
        List<Integer> targetRightIndexes = new ArrayList<>(); 

        List<Character> targetLR = new ArrayList<>();
        for (int i = 0; i < target.length(); i++) {
            char ch = target.charAt(i);
            if (ch == 'L' || ch == 'R') {
                targetLR.add(ch);
            }
        }

        
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == 'L') {
                targetLeftIndexes.add(i);
            } else if (target.charAt(i) == 'R') {
                targetRightIndexes.add(i);
            }
        }

        int iThLeft = 0;
        int iThRight = 0;

        for (int i = 0; i < start.length(); i++) {
            char ch = start.charAt(i);
            if ((ch == 'L' || ch == 'R') && (iThLeft + iThRight) < targetLR.size()) {
                if (targetLR.get(iThLeft + iThRight) != ch) {
                    return false;
                }
            }
            if (ch == 'L') {
                if (iThLeft >= targetLeftIndexes.size() || targetLeftIndexes.get(iThLeft) > i) {
                    return false;
                }
                iThLeft++;
            }
            else if (ch == 'R') {
                if (iThRight >= targetRightIndexes.size() || targetRightIndexes.get(iThRight) < i) {
                    return false;
                }
                iThRight++;
            }
        }
        
        if (iThLeft + iThRight != targetLR.size()) {
            return false;
        }


        return true;
    }
}
