package others;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string that contains only digits 0-9 and a target value,
 * return all possibilities to add binary operators (not unary) +, -, or *
 * between the digits so they evaluate to the target value.

 Example 1:

 Input: num = "123", target = 6
 Output: ["1+2+3", "1*2*3"]
 Example 2:

 Input: num = "232", target = 8
 Output: ["2*3+2", "2+3*2"]
 Example 3:

 Input: num = "105", target = 5
 Output: ["1*0+5","10-5"]
 Example 4:

 Input: num = "00", target = 0
 Output: ["0+0", "0-0", "0*0"]
 Example 5:

 Input: num = "3456237490", target = 9191
 Output: []

 * */


// dfs+for loop遍历，注意乘法运算前，要先减去之前放到path里的最后一个数

// This problem has a lot of edge cases to be considered:
//  overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
//  0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
//  a little trick is that we should save the value that is to be multiplied in the next recursion.

public class ExpressionAddOperators282 {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if(num == null ||   num.length() == 0) return res;
        helper(res, "", num, target, 0,0,0);
        return res;

    }

    private void helper(List<String> res, String path, String num,
                        int target, int pos, long currValue, long multiplied)
    {
        if(pos == num.length())
        {
            if(target == currValue)
            {
                res.add(path); return;
            }
        }

        for (int i = pos; i < num.length(); i++)
        {
            if(i != pos && num.charAt(pos) == '0') break; // number cannot start from all 0
            long cur = Long.parseLong(num.substring(pos, i+1));
            if(pos == 0)
            {
                helper(res, path + cur, num, target, i+1, cur, cur);
            }
            else
            {
                helper(res, path + "+" + cur, num, target, i+1, currValue + cur, cur);
                helper(res, path + "-" + cur, num, target, i+1, currValue - cur, -cur);
                helper(res, path + "*" + cur, num, target, i+1,
                        currValue - multiplied  + multiplied * cur,  multiplied * cur);

            }

        }
    }

}
