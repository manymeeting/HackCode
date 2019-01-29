package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

 Each LED represents a zero or one, with the least significant bit on the right.


 For example, the above binary watch reads "3:25".

 Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

 Example:

 Input: n = 1
 Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 Note:
 The order of output does not matter.
 The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */

// 准备两个包含digit的数组，一个给hour用一个给minute用，用一个helper来进行combination，最后排除掉越界的数字即可
    
public class BinaryWatch401 {
    public List<String> readBinaryWatch(int num) {

        int[] digitsH = new int[]{8, 4, 2, 1};
        int[] digitsM = new int[]{32, 16, 8, 4, 2, 1};

        List<String> res = new ArrayList<>();
        for (int i = 0; i <= num; i++) { // 注意hour和minute都可以取0
            List<Integer> hours = generateDigit(digitsH, i);
            List<Integer> minutes = generateDigit(digitsM, num - i);

            for (int hour : hours) {
                if (hour >= 12) continue;
                for (int minute : minutes) {
                    if(minute >= 60) continue;
                    res.add(hour + ":" + (minute < 10 ? "0" + minute : minute));
                }
            }
        }

        return res;

    }


    private List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        generateDigitHelper(nums, count, 0, 0, res);
        return res;
    }

    // 作用：给定可选择的数字的个数以及可选的digit，返回可能的数字相加的sum
    private void generateDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
        if(count == 0) {
            res.add(sum);
            return;
        }

        for (int i = pos; i < nums.length; i++) {

            // 直接在传参时进行数值修改，实现backtracking
            generateDigitHelper(nums, count-1, i + 1, sum + nums[i], res);
        }

    }



}
