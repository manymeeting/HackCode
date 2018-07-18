package others;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class NextClosetTime681 {
    int diff = Integer.MAX_VALUE;
    String result = "";

    public String nextClosestTime(String time) {
        // 使用set来一次性去重
        Set<Integer> set = new HashSet<>();
        set.add(Integer.parseInt(time.substring(0, 1)));
        set.add(Integer.parseInt(time.substring(1, 2)));
        set.add(Integer.parseInt(time.substring(3, 4)));
        set.add(Integer.parseInt(time.substring(4, 5)));

        if (set.size() == 1) return time;

        List<Integer> digits = new ArrayList<>(set);
        int minute = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));

        dfs(digits, "", 0, minute);

        return result;
    }
    // 用dfs尝试所有排列。从""开始记录当前的index和在index之前的字符串，再从index位置上开始dfs（循环该位可选digit+递归至下一位）
    private void dfs(List<Integer> digits, String cur, int pos, int target) {
        // 当到最后一位时更新diff
        if (pos == 4) {
            int m = Integer.parseInt(cur.substring(0, 2)) * 60 + Integer.parseInt(cur.substring(2, 4));
            if (m == target) return;
            int d = m - target > 0 ? m - target : 1440 + m - target;
            if (d < diff) {
                diff = d;
                result = cur.substring(0, 2) + ":" + cur.substring(2, 4);
            }
            return;
        }
        // 在遍历每个数字时做合法check，跳过不合理的time
        for (int i = 0; i < digits.size(); i++) {
            if (pos == 0 && digits.get(i) > 2) continue;
            if (pos == 1 && Integer.parseInt(cur) * 10 + digits.get(i) > 23) continue;
            if (pos == 2 && digits.get(i) > 5) continue;
            if (pos == 3 && Integer.parseInt(cur.substring(2)) * 10 + digits.get(i) > 59) continue;
            dfs(digits, cur + digits.get(i), pos + 1, target);
        }
    }


}