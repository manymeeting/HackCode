package arrays;

/**
 * 
 * A string s can be partitioned into groups of size k using the following procedure:

The first group consists of the first k characters of the string, the second group consists of the next k characters of the string, and so on. Each character can be a part of exactly one group.
For the last group, if the string does not have k characters remaining, a character fill is used to complete the group.
Note that the partition is done so that after removing the fill character from the last group (if it exists) and concatenating all the groups in order, the resultant string should be s.

Given the string s, the size of each group k and the character fill, return a string array denoting the composition of every group s has been divided into, using the above procedure.

 

Example 1:

Input: s = "abcdefghi", k = 3, fill = "x"
Output: ["abc","def","ghi"]
Explanation:
The first 3 characters "abc" form the first group.
The next 3 characters "def" form the second group.
The last 3 characters "ghi" form the third group.
Since all groups can be completely filled by characters from the string, we do not need to use fill.
Thus, the groups formed are "abc", "def", and "ghi".

思路简单，但是要注意array index的操作，按照index % k == 0分组时index要从1开始累加而不是0。
 */
public class DivideStrIntoGroupsSizeK2138 {
    public String[] divideString(String s, int k, char fill) {
        int numOfGroup = s.length() % k == 0 ? s.length() / k : s.length() / k + 1;
        String[] res = new String[numOfGroup];
        StringBuilder group = new StringBuilder(); 
        for (int i = 1; i <= s.length(); i++) {
            group.append(s.charAt(i - 1));
            if (i % k == 0) {
                res[i / k - 1] = group.toString();
                group = new StringBuilder();
            }
        }
        
        if (group.length() > 0) {
            while(group.length() < k) {
                group.append(fill);
            }
            res[res.length - 1] = group.toString();
        }
        
        return res;
    }
}
