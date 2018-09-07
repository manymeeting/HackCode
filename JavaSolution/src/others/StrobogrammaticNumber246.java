package others;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

 Write a function to determine if a number is strobogrammatic. The number is represented as a string.

 Example 1:

 Input:  "69"
 Output: true
 Example 2:

 Input:  "88"
 Output: true
 Example 3:

 Input:  "962"
 Output: false
 */

// two pointer，两个指针用while循环，从两端向中间移动，每次check l和r，最后check中间位置字符

public class StrobogrammaticNumber246 {
    public boolean isStrobogrammatic(String num) {
        int l = 0, r = num.length()-1;
        while(l < r)
        {
            char lch = num.charAt(l);
            char rch = num.charAt(r);
            l++; r--;
            if((lch == '6' && rch == '9') || (lch == '9' && rch == '6') ||
                (lch == '8' && rch == '8') || (lch == '1' && rch == '1') || (lch == '0' && rch == '0' && l > 0))
            {
                continue;
            }
            return false;
        }
        if(l == r)
        {
            if(num.charAt(l) != '0' && num.charAt(l) != '1' && num.charAt(l) != '8') return false;
        }
        return true;
    }
}
