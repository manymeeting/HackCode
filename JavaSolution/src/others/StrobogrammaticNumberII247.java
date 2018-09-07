package others;

import java.util.ArrayList;
import java.util.List;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

 Find all strobogrammatic numbers that are of length = n.

 Example:

 Input:  n = 2
 Output: ["11","69","88","96"]
 */

// 用dfs，总共有五对（0不能在第一位，单独判断）
public class StrobogrammaticNumberII247 {
    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<>();
        dfs(res, new char[n], 0, n-1);
        return res;
    }

    private void dfs(List<String> res, char[] chs, int l, int r)
    {
        if(l > r) //n为偶数情况下的终止条件
        {
            res.add(new String(chs));
            return;
        }

        if(l == r) //n为奇数情况下的终止条件
        {
            chs[l] = '0'; res.add(new String(chs));
            chs[l] = '1'; res.add(new String(chs));
            chs[l] = '8'; res.add(new String(chs));
            return;
        }

        if(l != 0) // 因为第一位不能为0，所以单独判断
        {
            chs[l] = '0'; chs[r] = '0';
            dfs(res, chs, l+1, r-1);
        }

        chs[l] = '1'; chs[r] = '1';
        dfs(res, chs, l+1, r-1);

        chs[l] = '8'; chs[r] = '8';
        dfs(res, chs, l+1, r-1);

        chs[l] = '6'; chs[r] = '9';
        dfs(res, chs, l+1, r-1);

        chs[l] = '9'; chs[r] = '6';
        dfs(res, chs, l+1, r-1);

    }

}
