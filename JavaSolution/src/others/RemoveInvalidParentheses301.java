package others;

import java.util.*;

/***
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

 Note: The input string may contain letters other than the parentheses ( and ).

 Example 1:

 Input: "()())()"
 Output: ["()()()", "(())()"]
 Example 2:

 Input: "(a)())()"
 Output: ["(a)()()", "(a())()"]
 Example 3:

 Input: ")("
 Output: [""]

 */


// 用BFS，维护一个visited的set和一个queue，每次遇到左右括号就删一个，把删后的string放到queue里，每次while循环中判断是否valid，
// 注意一旦遇到valid的结果，就不再继续删，保证所得结果中只包含删除数量最小的

public class RemoveInvalidParentheses301 {
    public List<String> removeInvalidParentheses(String s) {

        List<String> res = new ArrayList<>();
        if(s == null) return res;

        Set<String> visited = new HashSet<>(); // 避免bfs时遇到重复
        Queue<String> queue = new LinkedList<>();

        // initialize
        queue.add(s);
        visited.add(s);

        boolean isFound = false;

        while(!queue.isEmpty()) {
            s = queue.poll();

            if(isValid(s)) {
                isFound = true; // 为了后面check时看到true就不用继续删（保证删的数量是minimum）
                res.add(s);
            }

            if(isFound) continue;

            for (int i = 0; i < s.length(); i++) {
                // 不是括号则跳过
                if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

                String t = s.substring(0, i) + s.substring(i + 1);
                if(!visited.contains(t)) { // 避免重复
                    queue.add(t);
                    visited.add(t);
                }
            }
        }

        return res;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') count++;
            if(s.charAt(i) == ')') {
                if(count == 0) return false;
                count--;
            }
        }

        return count == 0; // 注意不是return true
    }

}
