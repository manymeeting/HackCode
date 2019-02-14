package binarysearch;

/**
 * Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

 Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

 Examples:
 Input:
 letters = ["c", "f", "j"]
 target = "a"
 Output: "c"

 Input:
 letters = ["c", "f", "j"]
 target = "c"
 Output: "f"

 Input:
 letters = ["c", "f", "j"]
 target = "d"
 Output: "f"

 Input:
 letters = ["c", "f", "j"]
 target = "g"
 Output: "j"

 Input:
 letters = ["c", "f", "j"]
 target = "j"
 Output: "c"

 Input:
 letters = ["c", "f", "j"]
 target = "k"
 Output: "c"
 Note:
 letters has a length in range [2, 10000].
 letters consists of lowercase letters, and contains at least 2 unique letters.
 target is a lowercase letter.

 * */

// 典型二分法查找，

// 总结：如果要找完全match的，判断mid时直接用 if(arr[mid] < target)，最后的left就会落在目标上，
// 如果是找相邻的大的，要用if(arr[mid] <= target) 来控制left往右移动，
// 如果是找相邻的小的，还是if(arr[mid] < target)，但在之前加一个如果arr[mid] == target则right-1

public class SmlstLetterGtThanTarget744 {

    public char nextGreatestLetter(char[] letters, char target) {

        int left = 0;
        int right = letters.length;
        while(left < right) {
            int mid = left + (right - left) / 2;

            if(letters[mid] <= target) { // 要加<=，因为是找相邻的大的
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }


        // 没找到时返回第一个是Test case要求
        return left == letters.length ? letters[0] : letters[left];


    }
}
