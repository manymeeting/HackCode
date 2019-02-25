package twopointer;

import java.util.HashSet;
import java.util.Set;

/**Write a function that takes a string as input and reverse only the vowels of a string.

 Example 1:

 Input: "hello"
 Output: "holle"
 Example 2:

 Input: "leetcode"
 Output: "leotcede"
 Note:
 The vowels does not include the letter "y".

 */

// Two Pointer, 根据左右两端的不容组合来决定移动哪端

public class ReverseVowelsOfAStr345 {
    public String reverseVowels(String s) {
        int i = 0; int j = s.length() - 1;
        char[] arr = s.toCharArray();
        Set<Character> vowels = new HashSet<>();
        vowels.add('a'); vowels.add('e'); vowels.add('i'); vowels.add('o'); vowels.add('u');
        vowels.add('A'); vowels.add('E'); vowels.add('I'); vowels.add('O'); vowels.add('U');
        while(i < j) {
            if(vowels.contains(arr[i]) && vowels.contains(arr[j])) {
                swap(arr, i, j);
                i++;
                j--;
            }
            else {
                if(vowels.contains(arr[i])) {
                    j--;
                }
                else if(vowels.contains(arr[j])) {
                    i++;
                }
                else {
                    i++;
                    j--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : arr) sb.append(ch);
        return sb.toString();

    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
